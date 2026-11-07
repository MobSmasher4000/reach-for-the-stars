package org.mob.reach_for_the_stars.block.custom.multiblock;

import net.minecraft.util.StringRepresentable;
import org.mob.reach_for_the_stars.util.MultiblockPattern;

import java.util.function.Supplier;

public enum DrillTier implements StringRepresentable {
    STEEL("steel", () -> MultiblockPatterns.STEEL_DRILL),
    DESH("desh", () -> MultiblockPatterns.DESH_DRILL),
    OSTRUM("ostrum", () -> MultiblockPatterns.OSTRUM_DRILL),
    CALORITE("calorite", () -> MultiblockPatterns.CALORITE_DRILL),
    GLACIO("glacio", () -> MultiblockPatterns.GLACIO_DRILL);

    private final String name;
    private final Supplier<MultiblockPattern> patternSupplier;

    DrillTier(String name, Supplier<MultiblockPattern> patternSupplier) {
        this.name = name;
        this.patternSupplier = patternSupplier;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public MultiblockPattern getPattern() {
        return this.patternSupplier.get();
    }
}