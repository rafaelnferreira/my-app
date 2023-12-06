plugins {
    id("global.genesis.deploy")
}

description = "my-genesis-app-deploy"

dependencies {
    genesisServer(
        group = "global.genesis",
        name = "genesis-distribution",
        version = properties["genesisVersion"].toString(),
        classifier = "bin",
        ext = "zip"
    )
    genesisServer(
        group = "global.genesis",
        name = "auth-distribution",
        version = properties["authVersion"].toString(),
        classifier = "bin",
        ext = "zip"
    )
    genesisServer(
        group = "global.genesis",
        name = "reporting-distribution",
        version = properties["reportingVersion"].toString(),
        classifier = "bin",
        ext = "zip"
    )

    genesisServer(project(":my-genesis-app-distribution", "distribution"))
    genesisServer(project(":my-genesis-app-site-specific", "distribution"))
    genesisWeb(":client")

    api(project(":my-genesis-app-eventhandler"))
    api(project(":my-genesis-app-messages"))
    // Add additional dependencies on other external distributions here
}
tasks {
    copyDependencies {
        enabled = false
    }
}
