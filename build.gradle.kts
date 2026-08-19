plugins {
    java
    id("xyz.srnyx.gradle-galaxy") version "c73e7ed"
    id("com.gradleup.shadow") version "9.6.1"
    id("me.modmuss50.mod-publish-plugin") version "675051c"
    id("io.papermc.hangar-publish-plugin") version "0.1.4"
    id("xyz.jpenilla.run-paper") version "3.1.0"
}

group = "xyz.srnyx"
description = "Plugin that allows you to randomize and swap inventories"

galaxy {
    minecraft {
        spigotAPI("1.8.8")
        annoyingAPI("f783bf4")

        pluginYml {
            developerData(SRNYX)
            permissionPrefix = "impulsive"

            command("impulsive") {
                aliases.addAll("ii", "impinv", "impulsiveinventories")
                description = "Main command for ImpulsiveInventories"

                permission("command") {
                    description = "Allows the player to use /impulsive"
                }
            }
        }

        platformPublishing {
            github("srnyx/impulsive-inventories")
            modrinth("y1g9LETP")
            hangar("ImpulsiveInventories")
            spigot("113437")
            curseforge("933924")

            projectData("impulsive-inventories")
        }
    }
}
