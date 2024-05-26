
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposedVersion: String by project
val postgresql_version: String by project
plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.8"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

group = "com.SportEventsApp"
version = "0.0.1"

application {
    mainClass.set("com.SportEventsApp.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}


repositories {
    mavenCentral()
    google()
}

ktor {
    docker {
        jreVersion.set(JavaVersion.VERSION_21)
        localImageName.set("sportEvents-back")
        imageTag.set("0.0.1-preview")

        portMappings.set(
            listOf(
                io.ktor.plugin.features.DockerPortMapping(
                    outsideDocker = 80,
                    insideDocker = 8080,
                    io.ktor.plugin.features.DockerPortMappingProtocol.TCP
                )
            )
        )
    }

    fatJar {
        archiveFileName.set("fat.jar")
    }
}

dependencies {

    implementation("io.ktor:ktor-server-core-jvm:$ktor_version") {
        exclude(group = "com.google.guava", module = "guava")
    }
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version") {
        exclude(group = "com.google.guava", module = "guava")
    }
    implementation("io.ktor:ktor-server-cio-jvm:$ktor_version") {
        exclude(group = "com.google.guava", module = "guava")
    }
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version") {
        exclude(group = "com.google.guava", module = "guava")
    }

//    implementation("io.ktor:ktor-server-core-jvm")
//    implementation("io.ktor:ktor-server-content-negotiation-jvm")
//    implementation("io.ktor:ktor-server-cio-jvm")
//    implementation ("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.postgresql:postgresql:$postgresql_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("com.google.guava:guava:32.1.2-jre")
}
