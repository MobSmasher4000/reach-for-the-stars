package org.mob.reach_for_the_stars.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import java.util.function.Consumer;

public class BaseFluidType extends FluidType {
    private final int tintColor;
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;

    public BaseFluidType(Properties properties, int tintColor, ResourceLocation stillTexture, ResourceLocation flowingTexture) {
        super(properties);
        this.tintColor = tintColor;
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() { return stillTexture; }

            @Override
            public ResourceLocation getFlowingTexture() { return flowingTexture; }

            @Override
            public int getTintColor() { return tintColor; }
        });
    }
}