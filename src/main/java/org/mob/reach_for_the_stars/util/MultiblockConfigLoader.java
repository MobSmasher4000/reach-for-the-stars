package org.mob.reach_for_the_stars.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class MultiblockConfigLoader {

    public static MultiblockPattern loadFromConfig(String fileName) {
        MultiblockPattern.Builder builder = MultiblockPattern.builder();
        
        // Target: config/reach_for_the_stars/
        Path configDir = FMLPaths.CONFIGDIR.get().resolve("reach_for_the_stars");
        File file = configDir.resolve(fileName).toFile();

        try {
            // Create folder if missing
            if (!Files.exists(configDir)) {
                Files.createDirectories(configDir);
            }

            if (!file.exists()) {
                throw new RuntimeException("Missing required blueprint! Place " + fileName + " in " + configDir.toString());
            }

            try (FileReader reader = new FileReader(file)) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                JsonObject layout = json.getAsJsonObject("layout");

                // Parse Layers
                JsonArray layersArray = layout.getAsJsonArray("layers");
                for (JsonElement layerElement : layersArray) {
                    JsonArray rowArray = layerElement.getAsJsonArray();
                    String[] rowStrings = new String[rowArray.size()];
                    
                    for (int r = 0; r < rowArray.size(); r++) {
                        rowStrings[r] = rowArray.get(r).getAsString();
                    }
                    builder.layer(rowStrings);
                }

                // Parse the 'key' dictionary
                JsonObject keyObj = layout.getAsJsonObject("key");
                for (Map.Entry<String, JsonElement> entry : keyObj.entrySet()) {
                    char character = entry.getKey().charAt(0);
                    String blockId = entry.getValue().getAsJsonObject().get("block").getAsString();
                    
                    // Convert the string (e.g., "minecraft:stone") to a ResourceLocation
                    ResourceLocation res = ResourceLocation.parse(blockId);
                    
                    // Fetch the physical block from the Forge Registry
                    Block block = ForgeRegistries.BLOCKS.getValue(res);

                    if (block != null) {
                        builder.where(character, 
                                state -> state.is(block), 
                                block.defaultBlockState()
                        );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.build();
    }
}