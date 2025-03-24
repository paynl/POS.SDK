//
//  PayNlPosService.swift
//  pos-sdk
//
//  Created by Bastiaan Verhaar on 24/03/2025.
//

import PayNlPOSSdkSwift

class PayNLService {
    private let posService: PosService
    init() {
        self.posService = PosService()
    }
    
    public func initSdk() async {
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
