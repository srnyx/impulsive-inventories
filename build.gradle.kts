import xyz.srnyx.gradlegalaxy.utility.setupAnnoyingAPI
import xyz.srnyx.gradlegalaxy.utility.spigotAPI


plugins {
    java
    id("xyz.srnyx.gradle-galaxy") version "1.1.2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

setupAnnoyingAPI("98c5b99579", "xyz.srnyx", "1.0.0", "Plugin that allows you to randomize and swap inventories")
spigotAPI("1.8.8")
