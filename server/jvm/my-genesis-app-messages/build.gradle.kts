dependencies {
    implementation("global.genesis:genesis-messages")
    compileOnly(project(path = ":my-genesis-app-dictionary-cache", configuration = "codeGen"))
}

description = "my-genesis-app-messages"