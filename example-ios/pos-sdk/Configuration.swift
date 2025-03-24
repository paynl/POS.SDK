//
//  Configuration.swift
//  pos-sdk
//
//  Created by Bastiaan Verhaar on 24/03/2025.
//

import Foundation

class Configuration {
    private static let info = Bundle.main.infoDictionary ?? [:]
    
    public static let aCode = Configuration.info["A_CODE"] as? String ?? ""
    public static let slCode = Configuration.info["SL_CODE"] as? String ?? ""
    public static let slSecret = Configuration.info["SL_SECRET"] as? String ?? ""
}
