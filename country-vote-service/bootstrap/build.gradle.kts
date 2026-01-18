plugins {
    id("org.springframework.boot") version "4.0.2-SNAPSHOT"
}

apply(plugin = "org.springframework.boot")

tasks.register<Copy>("explodedJar") {
    with(tasks.jar.get())
    into(layout.buildDirectory.dir("exploded"))
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = true
    archiveFileName.set("${rootProject.name}.${archiveExtension.get()}")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":entrypoint-webflux"))
    implementation(project(":persistence-postgres"))
    implementation(project(":consumer-restcountries"))

    configurations {
        all {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
        }
    }

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-actuator-test")



}
