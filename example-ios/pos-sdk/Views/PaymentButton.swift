//
//  PaymentButton.swift
//  pos-sdk
//
//  Created by Bastiaan Verhaar on 24/03/2025.
//

import SwiftUI
struct PaymentButton: View {
    var value: Int
    var geo: GeometryProxy
    var disabled: Bool
    var action: (Int) -> Void
    
    var body: some View {
        Button(action: { action(value) }) {
            ZStack {
                RoundedRectangle(cornerRadius: 16)
                    .fill(self.disabled ? Color.gray : .payPrimary)
                    .frame(height: (geo.size.height / 4).rounded())
                
                if value != 10 {
                    Text(getText())
                        .fontWeight(.bold)
                        .font(.title)
                        .foregroundStyle(.white)
                } else {
                    Image(systemName: "arrow.left.circle.fill")
                        .foregroundStyle(.white)
                        .font(.title)
                }
            }
        }
        .disabled(self.disabled)
        .buttonStyle(.plain)
    }
    
    private func getText() -> String {
        switch value {
        case 11:
            return "0"
        case 12:
            return "00"
        default:
            return "\(value)"
            
        }
    }
}
