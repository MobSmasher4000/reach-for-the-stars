package org.mob.reach_for_the_stars.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import org.mob.reach_for_the_stars.Reach_for_the_stars;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class FuelDistilleryRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final FluidStack inputFluid;
    private final FluidStack outputFluid;

    public FuelDistilleryRecipe(ResourceLocation id, FluidStack inputFluid, FluidStack outputFluid) {
        this.id = id;
        this.inputFluid = inputFluid;
        this.outputFluid = outputFluid;
    }

    public FluidStack getInputFluid() {
        return inputFluid;
    }

    public FluidStack getOutputFluid() {
        return outputFluid;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return false;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<FuelDistilleryRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "fuel_distillery";
    }

    public static class Serializer implements RecipeSerializer<FuelDistilleryRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = resourceLocation("fuel_distillery");

        @Override
        public FuelDistilleryRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            // Read "fluid" object
            JsonObject fluidObj = GsonHelper.getAsJsonObject(pSerializedRecipe, "fluid");
            ResourceLocation inputId = ResourceLocation.parse(GsonHelper.getAsString(fluidObj, "fluid"));
            int inputAmount = GsonHelper.getAsInt(fluidObj, "amount");
            Fluid inputFluid = ForgeRegistries.FLUIDS.getValue(inputId);
            
            // Read "result" object
            JsonObject resultObj = GsonHelper.getAsJsonObject(pSerializedRecipe, "result");
            ResourceLocation outputId = ResourceLocation.parse(GsonHelper.getAsString(resultObj, "fluid"));
            int outputAmount = GsonHelper.getAsInt(resultObj, "amount");
            Fluid outputFluid = ForgeRegistries.FLUIDS.getValue(outputId);

            return new FuelDistilleryRecipe(pRecipeId, 
                    new FluidStack(inputFluid, inputAmount), 
                    new FluidStack(outputFluid, outputAmount));
        }

        @Override
        public @Nullable FuelDistilleryRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            FluidStack input = pBuffer.readFluidStack();
            FluidStack output = pBuffer.readFluidStack();
            return new FuelDistilleryRecipe(pRecipeId, input, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, FuelDistilleryRecipe pRecipe) {
            pBuffer.writeFluidStack(pRecipe.inputFluid);
            pBuffer.writeFluidStack(pRecipe.outputFluid);
        }
    }
}