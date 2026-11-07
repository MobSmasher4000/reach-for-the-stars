package org.mob.reach_for_the_stars.util;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.mob.reach_for_the_stars.block.custom.multiblock.MultiblockPatterns;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("rfts")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("reload")
                        .executes(context -> {

                            MultiblockPatterns.init();

                            context.getSource().sendSuccess(() -> 
                                Component.literal("Successfully reloaded all Multiblock JSONs!"), true);
                            
                            return 1;
                        })
                )
        );
    }
}