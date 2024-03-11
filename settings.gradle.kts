pluginManagement {
    repositories {
        google()
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

rootProject.name = "Experto Chevron"
include(":app")
//include(":feature-tests")
include(":feature-content")
//include(":feature-accounts")
//include(":feature-authentication")
//include(":feature-trends")
include(":feature-common")
include(":feature-contracts")
