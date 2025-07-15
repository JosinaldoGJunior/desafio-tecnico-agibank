plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "josinaldo_junior"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allure {
    version.set("2.27.0")
}

dependencies {
    testImplementation("io.rest-assured:rest-assured:5.3.2")
    testImplementation("io.rest-assured:json-schema-validator:5.3.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("io.qameta.allure:allure-junit5:2.27.0")
    testImplementation("io.qameta.allure:allure-rest-assured:2.27.0")
}

tasks.test {
    useJUnitPlatform()
}