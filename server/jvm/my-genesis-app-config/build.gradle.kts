dependencies {
    compileOnly("global.genesis:genesis-dictionary")
    compileOnly("global.genesis:genesis-process")
    compileOnly("global.genesis:genesis-pal-execution")
    compileOnly(project(path = ":my-genesis-app-dictionary-cache", configuration = "codeGen"))
}

description = "my-genesis-app-config"
