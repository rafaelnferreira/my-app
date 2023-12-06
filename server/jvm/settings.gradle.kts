rootProject.name = "genesisproduct-my-genesis-app"

buildCache {
    local {
        directory = File(rootDir.parentFile.parent, "build-cache")
        removeUnusedEntriesAfterDays = 30
        isEnabled = true
    }
}

pluginManagement {
    pluginManagement {
        val genesisVersion: String by settings
        val deployPluginVersion: String by settings
        plugins {
            id("global.genesis.build") version genesisVersion
            id("global.genesis.deploy") version deployPluginVersion
        }
    }
    repositories {
        mavenLocal {
            // VERY IMPORTANT!!! EXCLUDE AGRONA AS IT IS A POM DEPENDENCY AND DOES NOT PLAY NICELY WITH MAVEN LOCAL!
            content {
                excludeGroup("org.agrona")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            val repoUrl = if(extra.properties["clientSpecific"] == "true") {
                "https://genesisglobal.jfrog.io/genesisglobal/libs-release-client"
            } else {
                "https://genesisglobal.jfrog.io/genesisglobal/dev-repo"
            }
            url = uri(repoUrl)
            credentials {
                username = extra.properties["genesisArtifactoryUser"].toString()
                password = extra.properties["genesisArtifactoryPassword"].toString()
            }
        }
    }
}



include("my-genesis-app-config")
include("my-genesis-app-messages")
include("my-genesis-app-eventhandler")
include("my-genesis-app-script-config")
include("my-genesis-app-distribution")
include("my-genesis-app-dictionary-cache")
include("my-genesis-app-dictionary-cache:my-genesis-app-generated-sysdef")
include("my-genesis-app-dictionary-cache:my-genesis-app-generated-fields")
include("my-genesis-app-dictionary-cache:my-genesis-app-generated-dao")
include("my-genesis-app-dictionary-cache:my-genesis-app-generated-hft")
include("my-genesis-app-dictionary-cache:my-genesis-app-generated-view")
include("my-genesis-app-deploy")
include("my-genesis-app-site-specific")

includeBuild("../../client")
