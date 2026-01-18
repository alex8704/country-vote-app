plugins {
	java
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management)
}

group = "dev.loopstudio"
version = "0.0.1-SNAPSHOT"
description = "Technical Challenge"

allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/snapshot") }
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "jacoco")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencyManagement {
        imports {
            mavenBom(rootProject.libs.spring.boot.dependencies.get().toString())
        }
    }

    tasks.withType<Test> {
        jvmArgs = jvmArgs + "-XX:+AllowRedefinitionToAddDeleteMethods" + "-XX:+EnableDynamicAgentLoading"
        useJUnitPlatform()
        finalizedBy("jacocoTestReport")
    }

    tasks.withType<JacocoReport> {
        dependsOn(tasks.test)

        sourceDirectories.setFrom(files("src/main/java"))
        classDirectories.setFrom(files("build/classes/java/main"))
        executionData.setFrom(files("build/jacoco/test.exec"))
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.add("-parameters")
    }

    tasks.register<JacocoReport>("jacocoRootReport") {
        group = "verification"
        description = "Generates a Jacoco coverage report for all subprojects."

        dependsOn(subprojects.map { it.tasks.matching { task -> task.name == "test" } })

        val classDirs = subprojects.map { project ->
            fileTree("${project.layout.buildDirectory.get()}/classes/java/main") {
                exclude(
                    "**/Q*",
                    "**/generated/**"
                )
            }
        }

        val sourceDirs = subprojects.map { project ->
            project.fileTree("${project.projectDir}/src/main/java")
        }

        val executionFiles = subprojects.map { project ->
            project.file("${project.layout.buildDirectory.get()}/jacoco/test.exec")
        }

        sourceDirectories.setFrom(sourceDirs)
        classDirectories.setFrom(classDirs)
        executionData.setFrom(executionFiles)
    }

    dependencies {
        implementation("io.projectreactor:reactor-core")
        implementation("io.projectreactor.addons:reactor-extra")
        implementation("org.slf4j:slf4j-api")
        annotationProcessor("org.projectlombok:lombok")

        testImplementation(rootProject.libs.blockhound.junit.platform)
        testImplementation("io.projectreactor:reactor-test")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("net.datafaker:datafaker:2.5.0")

        compileOnly("org.projectlombok:lombok")
        testCompileOnly("org.projectlombok:lombok")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testAnnotationProcessor("org.projectlombok:lombok")
    }

}

