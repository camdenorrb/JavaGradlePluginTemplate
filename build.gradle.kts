plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "dev.twelveoclock"
version = "1.0.0"

repositories {

    mavenCentral()

    maven("https://hub.spigotmc.org/nexus/content/repositories/public/") {
        name = "SpigotMC"
    }
}

dependencies {

    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")

    implementation("org.jetbrains:annotations:22.0.0")

    // Jackson
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-toml:2.13.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("com.github.seeseemelk:MockBukkit-v1.17:1.10.4")
}


tasks {

    test {
        useJUnitPlatform()
    }

    // TODO: Change the second parameter to your plugin's package.
    shadowJar {
        relocate("com.fasterxml", "dev.twelveoclock.plugintemplate.libs.com.fasterxml")
        relocate("org.jetbrains", "dev.twelveoclock.plugintemplate.libs.org.jetbrains")
        relocate("org.intellij", "dev.twelveoclock.plugintemplate.libs.org.intellij")
    }
}

