//
//  ContentView.swift
//  pos sdk
//
//  Created by Bastiaan Verhaar on 24/03/2025.
//

import SwiftUI

struct ContentView: View {
    @StateObject var viewModel = ViewModel()
    
    let columns = [
        GridItem(.flexible()),
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    
    
    var body: some View {
        VStack {
            Image("paynl", bundle: Bundle.main)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(height: 75)
            VStack {
                HStack {
                    Spacer()
                    Text("\(Double(viewModel.amount) / 100, format: .currency(code: viewModel.currency))")
                        .font(.largeTitle)
                        .fontWeight(.bold)
                    Spacer()
                }
            }
            .frame(maxWidth: .infinity, maxHeight: 250)
            VStack {
                GeometryReader { geo in
                    LazyVGrid(columns: columns) {
                        ForEach(1..<13) { value in
                            PaymentButton(value: value, geo: geo, disabled: value != 10 ? viewModel.amount >= 999999 : viewModel.amount == 0) { x in viewModel.addToAmount(x) }
                        }
                    }
                }
                .padding(.bottom, 60)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                
                Button(action: { viewModel.startPayment() }) {
                    Text("Tap to Pay on iPhone")
                        .fontWeight(.bold)
                        .padding(.vertical, 10)
                        .foregroundStyle(.white)
                        .frame(maxWidth: .infinity)
                        .overlay(
                            Image(systemName: "wave.3.right.circle.fill")
                                .padding()
                                .font(.title3)
                                .foregroundStyle(.white),
                            alignment: .leading)
                        .overlay(!$viewModel.isReady.wrappedValue || $viewModel.isPaying.wrappedValue ? ProgressView()
                            .progressViewStyle(.circular) : nil,
                                 alignment: .trailing)
                }
                .tint(.payPrimary)
                .buttonStyle(.borderedProminent)
                .disabled(!$viewModel.isReady.wrappedValue || $viewModel.isPaying.wrappedValue || $viewModel.amount.wrappedValue == 0)
            }
        }
        .padding()
        .sheet(isPresented: $viewModel.showingTransactionResult) {
            VStack {
                if viewModel.paymentSuccess {
                    Image(systemName: "checkmark.circle.fill")
                        .resizable()
                        .frame(width: 64, height: 64)
                        .foregroundStyle(Color(red: 0, green: 127/255, blue: 82/255)) //007F52
                } else {
                    Image(systemName: "xmark.circle.fill")
                        .resizable()
                        .frame(width: 64, height: 64)
                        .foregroundStyle(Color(red: 197/255, green: 54/255, blue: 44/255)) //C5362C
                }
                
                Text(viewModel.payerMessage)
                    .font(.title)
                    .foregroundStyle(.black)
                
                if viewModel.paymentSuccess {
                    Text(viewModel.ticketDecoded)
                    Spacer()
                }
            }
                .padding(.top, 40)
                .presentationDetents([.medium])
                .presentationDragIndicator(.visible)
        }
    }
}

#Preview {
    ContentView()
}
