//
//  ViewModel.swift
//  pos-sdk
//
//  Created by Bastiaan Verhaar on 24/03/2025.
//

import OSLog
import PayNlPOSSdkSwift
import SwiftUI

class ViewModel: ObservableObject {
    @Published var isReady = false
    @Published var amount = 0
    @Published var currency = "EUR"
    
    private let posService = PosService()
    private let logger = Logger(subsystem: "com.paynl.example.pos-sdk", category: "ViewModel")
    
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
    
    func initSDK(_ attempt: Int = 0) {
        Task {
            do {
                let result = try await self.posService.initSdk()
                switch result {
                case .needsLogin:
                    guard attempt < 2 else {
                        self.logger.error("Failed to init SDK")
                        return
                    }
                    self.loginViaCreds(attempt: attempt)
                    break
                case .readyForPayment:
                    // The SDK is ready to start payment
                    self.logger.info("Ready to accept payments!")
                    self.isReady = true
                    break
                }
            } catch {
                if let error = error as? PayNlSVError {
                    self.logger.error("Got error from SDK: \(error.code) - \(error.description)")
                    return
                }
                
                self.logger.error("Unknown error from PAY.POS sdk: \(error.localizedDescription)")
            }
        }
    }
    
    func loginViaCreds(attempt: Int) {
        self.logger.info("Logging in using credentials...")
        let x = Bundle.main.infoDictionary
        guard Configuration.aCode != "", Configuration.slCode != "", Configuration.slSecret != "" else {
            self.logger.error("Missing credentials -> Copy Config_example to Config and fill in the empty values")
            return
        }
        
        Task {
            do {
                try await self.posService.loginViaCredentials(aCode: Configuration.aCode, serviceCode: Configuration.slCode, serviceSecret: Configuration.slSecret)
                
                self.logger.info("Login successfull!")
                self.initSDK(attempt + 1)
            } catch {
                if let error = error as? PayNlSVError {
                    self.logger.error("Got error from SDK: \(error.code) - \(error.description)")
                    return
                }
                
                self.logger.error("Unknown error from PAY.POS sdk: \(error.localizedDescription)")
            }
        }
    }
}
