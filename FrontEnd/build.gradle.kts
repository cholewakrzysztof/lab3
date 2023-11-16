plugins {
    id("java")
}

group = "pwr.student.FrontEnd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":BackEnd"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.xerial:sqlite-jdbc:3.44.0.0")
}

tasks.test {
    useJUnitPlatform()
}