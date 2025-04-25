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
                      url: "https://paysoftpos.blob.core.windows.net/apk/spm-test11.zip",
                      checksum: "32d1c66f1c9e8f9ec434433803b9c85d95831278a0bceca87f17be480e51aa99"
        )

    ]
)
