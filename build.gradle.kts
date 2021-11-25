plugins {
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.serialization") version "1.5.31"
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    implementation("org.jetbrains.kotlinx:dataframe:0.8.0-dev-426-0.10.3.26")

    // https://mvnrepository.com/artifact/org.jetbrains.lets-plot/lets-plot-kotlin-jvm
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:3.1.0")

    // https://mvnrepository.com/artifact/org.jetbrains.lets-plot/lets-plot-image-export
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:2.2.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}