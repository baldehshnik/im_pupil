pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "I'm pupil"
include(":app")
include(":navigation:admin")
include(":navigation:pupil")
include(":navigation:common")
include(":data:common")
include(":data:accounts")
include(":data:admin")
include(":data:pupil")
include(":features:admin:home")
include(":features:admin:account")
include(":features:admin:services")
