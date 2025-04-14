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
    @Published var isPaying = false
    @Published var showingTransactionResult = false
    @Published var paymentSuccess = false
    @Published var ticketDecoded = ""
    @Published var payerMessage = ""
    @Published var amount = 0
    @Published var currency = "EUR"
    
    private let posService: PosService
    private let logger: Logger
    init() {
        self.posService = PosService()
        self.logger = Logger(subsystem: "com.paynl.example.pos-sdk", category: "ViewModel")
        
        self.initSDK(0)
    }
    
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
            let result = await self.posService.initSdk(integrationId: "00000000-0000-0000-0000-000000000000")
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
                self.readyForPayments()
                break
                
            case .failed(let error):
                self.logger.error("Got error from SDK: \(error.code) - \(error.description)")
                break
            }
            
        }
    }
    
    func startPayment() {
        DispatchQueue.main.async {
            self.isPaying = true
        }
        
        Task {
            do {
                let transaction = PayNlTransaction(amount: PayNlTransactionAmount(value: self.amount, currency: self.currency))
                let result = try await self.posService.startPayment(transaction: transaction, service: nil)
                self.donePaying(result.payerMessage.isEmpty ? "Unknown error occured..." : result.payerMessage)
                
                guard result.statusAction == .paid else {
                    self.logger.warning("Payment failed...")
                    return
                }
                
                guard let ticketDecoded = Data(base64Encoded: result.ticket),
                      let ticket = String(data: ticketDecoded, encoding: .utf8) else {
                    self.logger.warning("No ticket data")
                    return
                }
                
                self.paymentSuccess(ticket)
                self.logger.info("Payment successfull!")
            } catch {
                self.donePaying("Unknown error occured...")
                
                if let error = error as? PayNlSVError {
                    switch error {
                    case .TRANSACTION_CANCELLED:
                        self.logger.error("Transaction cancelled...")
                        return
                        
                    default:
                        self.logger.error("Got error from SDK: \(error.code) - \(error.description)")
                        return
                    }
                }
                
                self.logger.error("Unknown error from PAY.POS sdk: \(error.localizedDescription)")
            }
        }
    }
    
    private func donePaying(_ payerMessage: String) {
        DispatchQueue.main.async {
            self.isPaying = false
            self.paymentSuccess = false
            self.payerMessage = payerMessage
            self.amount = 0
            self.ticketDecoded = ""
            self.showingTransactionResult = true
            
        }
    }
    
    private func paymentSuccess(_ ticket: String) {
        DispatchQueue.main.async {
            self.ticketDecoded = ticket
            self.paymentSuccess = true
        }
    }
    
    private func loginViaCreds(attempt: Int) {
        self.logger.info("Logging in using credentials...")
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
    
    private func readyForPayments() {
        DispatchQueue.main.async {
            self.isReady = true
            self.logger.info("Ready to accept payments!")
        }
    }
}
