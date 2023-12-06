
// Add your genesis config dependencies here
dependencies {
    implementation("global.genesis:auth-config:${properties["authVersion"]}")
    implementation("global.genesis:reporting-config:${properties["reportingVersion"]}")
}

description = "my-genesis-app-dictionary-cache"
