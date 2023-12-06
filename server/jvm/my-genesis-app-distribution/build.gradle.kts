plugins {
    distribution
}

dependencies {
    implementation(project(":my-genesis-app-config"))
    implementation(project(":my-genesis-app-dictionary-cache"))
    implementation(project(":my-genesis-app-eventhandler"))
    implementation(project(":my-genesis-app-messages"))
    implementation(project(":my-genesis-app-script-config"))
}

description = "my-genesis-app-distribution"

distributions {
    main {
        contents {
            // Octal conversion for file permissions
            val libPermissions = "600".toInt(8)
            val scriptPermissions = "700".toInt(8)
            into("my-genesis-app/bin") {
                from(configurations.runtimeClasspath)
                exclude("my-genesis-app-config*.jar")
                exclude("my-genesis-app-script-config*.jar")
                exclude("my-genesis-app-distribution*.jar")
                include("my-genesis-app-*.jar")
            }
            into("my-genesis-app/lib") {
                from("${project.rootProject.buildDir}/dependencies")
                duplicatesStrategy = DuplicatesStrategy.EXCLUDE

                exclude("genesis-*.jar")
                exclude("my-genesis-app-*.jar")
                exclude("*.zip")

                fileMode = libPermissions
            }
            into("my-genesis-app/cfg") {
                from("${project.rootProject.projectDir}/my-genesis-app-config/src/main/resources/cfg")
                from(project.layout.buildDirectory.dir("generated/product-details"))
                filter(
                    org.apache.tools.ant.filters.FixCrLfFilter::class,
                    "eol" to org.apache.tools.ant.filters.FixCrLfFilter.CrLf.newInstance("lf")
                )
            }
            into("my-genesis-app/scripts") {
                from("${project.rootProject.projectDir}/my-genesis-app-config/src/main/resources/scripts")
                from("${project.rootProject.projectDir}/my-genesis-app-script-config/src/main/resources/scripts")
                filter(
                    org.apache.tools.ant.filters.FixCrLfFilter::class,
                    "eol" to org.apache.tools.ant.filters.FixCrLfFilter.CrLf.newInstance("lf")
                )
                fileMode = scriptPermissions
            }
            // Removes intermediate folder called with the same name as the zip archive.
            into("/")
        }
    }
}

val distribution by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

// To give custom name to the distribution package
tasks {
    distZip {
        archiveBaseName.set("genesisproduct-my-genesis-app")
        archiveClassifier.set("bin")
        archiveExtension.set("zip")
        inputs.files(rootProject.getTasksByName("copyDependencies", true))
    }
    distTar {
        enabled = false
    }
    copyDependencies {
        enabled = false
    }
}

artifacts {
    val distzip = tasks.distZip.get()
    add("distribution", distzip.archiveFile) {
        builtBy(distzip)
    }
}

publishing {
    publications {
        create<MavenPublication>("my-genesis-appServerDistribution") {
            artifact(tasks.distZip.get())
        }
    }
}

description = "my-genesis-app-distribution"
