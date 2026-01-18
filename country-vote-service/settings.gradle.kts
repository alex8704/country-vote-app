pluginManagement {
	repositories {
		maven { url = uri("https://repo.spring.io/snapshot") }
		gradlePluginPortal()
	}
}


rootProject.name = "country-vote-service"

include(":domain")
include(":application")
include(":bootstrap")

include(":entrypoint-webflux")
project(":entrypoint-webflux").projectDir = file("./adapters/inbound/entrypoint-webflux")

include(":persistence-postgres")
project(":persistence-postgres").projectDir = file("./adapters/outbound/persistence-postgres")

include(":consumer-restcountries")
project(":consumer-restcountries").projectDir = file("./adapters/outbound/consumer-restcountries")