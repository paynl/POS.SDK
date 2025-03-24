//
//  ViewModel.swift
//  pos-sdk
//
//  Created by Bastiaan Verhaar on 24/03/2025.
//

import PayNlPOSSdkSwift
import SwiftUI

class ViewModel: ObservableObject {
    @Published var amount = 0
    @Published var currency = "EUR"
    
    private let posService = PosService()
    
    func addToAmount(_ value: Int) {
        var amountStr = String(amount)
        
        if value <= 9 {
            amountStr.append(String(value))
        } else if value == 10 {
            amountStr.removeLast()
        } else if value == 11 {
            amountStr.append("0")
        } else if value == 12 {
            amountStr.append("00")
        }
        
        
        self.amount = Int(amountStr) ?? 0
    }
    
    func initSDK() {
        Task {
            do {
                let result = try await self.posService.initSdk()
                switch result {
                case .needsLogin:
                    // Start login flow
                    break
                case .readyForPayment:
                    // The SDK is ready to start payment
                    break
                }
            } catch {
                if let error = error as? SVError {
    //                print("Got error from SDK: \(error.code) - \(error.description)")
                    return
                }
                
                print("Unknown error from PAY.POS sdk: \(error.localizedDescription)")
            }
        }
    }
}
