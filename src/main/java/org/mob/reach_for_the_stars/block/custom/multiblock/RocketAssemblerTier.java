package org.mob.reach_for_the_stars.block.custom.multiblock;

import net.minecraft.util.StringRepresentable;
import org.mob.reach_for_the_stars.util.MultiblockPattern;

import java.util.function.Supplier;

public enum RocketAssemblerTier implements StringRepresentable {
    T1("t1", () -> MultiblockPatterns.T1_ROCKET_ASSEMBLER, 1),
    T2("t2", () -> MultiblockPatterns.T2_ROCKET_ASSEMBLER, 2),
    T3("t3", () -> MultiblockPatterns.T3_ROCKET_ASSEMBLER, 3),
    T4("t4", () -> MultiblockPatterns.T4_ROCKET_ASSEMBLER, 4),
    T5("t5", () -> MultiblockPatterns.T5_ROCKET_ASSEMBLER, 5);

    private final String name;
    private final Supplier<MultiblockPattern> patternSupplier;
    private final int tier;

    RocketAssemblerTier(String name, Supplier<MultiblockPattern> patternSupplier, int tier) {
        this.name = name;
        this.patternSupplier = patternSupplier;
        this.tier = tier;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public MultiblockPattern getPattern() {
        return this.patternSupplier.get();
    }

    public int getMachineTier(){return this.tier;}
}