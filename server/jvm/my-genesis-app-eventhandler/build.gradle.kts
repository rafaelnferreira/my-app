dependencies {
    implementation("global.genesis:genesis-pal-execution")
    implementation("global.genesis:genesis-eventhandler")
    implementation(project(":my-genesis-app-messages"))
    api("global.genesis:genesis-db")
    compileOnly(project(":my-genesis-app-config"))
    compileOnly(project(path = ":my-genesis-app-dictionary-cache", configuration = "codeGen"))
    testImplementation("global.genesis:genesis-dbtest")
    testImplementation("global.genesis:genesis-testsupport")
    testImplementation(project(path = ":my-genesis-app-dictionary-cache", configuration = "codeGen"))
}

description = "my-genesis-app-eventhandler"