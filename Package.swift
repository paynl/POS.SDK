// swift-tools-version: 6.0
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "PayNlPOSSdkSwiftWrapper",
    platforms: [ .iOS(.v18) ],
    products: [
        // Products define the executables and libraries a package produces, making them visible to other packages.
        .library(
            name: "PayNlPOSSdkSwiftWrapper",
            targets: ["PayNlPOSSdkSwift"]),
    ],
    targets: [
        // Targets are the basic building blocks of a package, defining a module or a test suite.
        // Targets can depend on other targets in this package and products from dependencies.
        .binaryTarget(name: "PayNlPOSSdkSwift",
                      url: "https://paysoftpos.blob.core.windows.net/apk/pos-sdk-swift-package-manager-0.0.26.zip",
                      checksum: "1b5dd7b1871cf302e493db4174b2923d7d49e85cc66a0ec2d4e12b25a7951fcc"
        )

    ]
)
