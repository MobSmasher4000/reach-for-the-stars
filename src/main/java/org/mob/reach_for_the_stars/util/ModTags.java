package org.mob.reach_for_the_stars.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;


public class ModTags {
    public static class Blocks{

        public static TagKey<Block> createTag(String name){
            return BlockTags.create(resourceLocation(name));
        }
    }

    public static class Items{
//        public static final TagKey<Item> RESOURCE_GENERATOR = createTag("resource_gen");

        public static TagKey<Item> createTag(String name){
            return ItemTags.create(resourceLocation(name));
        }

    }

}
