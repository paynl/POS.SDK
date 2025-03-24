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
            VStack {
                HStack {
                    Spacer()
                    Text("\(Double(viewModel.amount) / 100, format: .currency(code: viewModel.currency))")
                        .font(.largeTitle)
                        .fontWeight(.bold)
                    Spacer()
                }
            }
            .frame(maxWidth: .infinity, maxHeight: 300)
            VStack {
                GeometryReader { geo in
                    LazyVGrid(columns: columns) {
                        ForEach(1..<13) { value in
                            PaymentButton(value: value, geo: geo) { x in viewModel.addToAmount(x) }
                        }
                    }
                }
                .padding(.bottom, 60)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                
                Button(action: {}) {
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
                }
                .tint(.payPrimary)
                .buttonStyle(.borderedProminent)
            }
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
