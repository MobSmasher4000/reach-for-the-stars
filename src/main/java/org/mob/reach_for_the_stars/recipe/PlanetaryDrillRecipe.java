package org.mob.reach_for_the_stars.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class PlanetaryDrillRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ResourceLocation machine;
    private final FluidStack fluidInput;
    private final ResourceLocation dimension;
    private final ItemStack output;

    public PlanetaryDrillRecipe(ResourceLocation id, ResourceLocation machine, FluidStack fluidInput, ResourceLocation dimension, ItemStack output) {
        this.id = id;
        this.machine = machine;
        this.fluidInput = fluidInput;
        this.dimension = dimension;
        this.output = output;
    }

    public ResourceLocation getMachine() { return machine; }
    public FluidStack getFluidInput() { return fluidInput; }
    public ResourceLocation getDimension() { return dimension; }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) { return false; }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) { return output.copy(); }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) { return true; }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) { return output.copy(); }

    @Override
    public ResourceLocation getId() { return id; }

    @Override
    public RecipeSerializer<?> getSerializer() { return Serializer.INSTANCE; }

    @Override
    public RecipeType<?> getType() { return Type.INSTANCE; }

    public static class Type implements RecipeType<PlanetaryDrillRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "planetary_drill";
    }

    public static class Serializer implements RecipeSerializer<PlanetaryDrillRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = resourceLocation("planetary_drill");

        @Override
        public PlanetaryDrillRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ResourceLocation machine = ResourceLocation.parse(GsonHelper.getAsString(json, "machine"));
            ResourceLocation dimension = ResourceLocation.parse(GsonHelper.getAsString(json, "dimension"));

            JsonObject fluidObj = GsonHelper.getAsJsonObject(json, "fluid");
            ResourceLocation fluidRes = ResourceLocation.parse(GsonHelper.getAsString(fluidObj, "fluid"));
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidRes);
            int amount = GsonHelper.getAsInt(fluidObj, "amount");
            FluidStack fluidInput = new FluidStack(fluid, amount);

            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            return new PlanetaryDrillRecipe(recipeId, machine, fluidInput, dimension, result);
        }

        @Override
        public @Nullable PlanetaryDrillRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            ResourceLocation machine = buffer.readResourceLocation();
            ResourceLocation dimension = buffer.readResourceLocation();
            FluidStack fluidInput = FluidStack.readFromPacket(buffer);
            ItemStack output = buffer.readItem();
            return new PlanetaryDrillRecipe(recipeId, machine, fluidInput, dimension, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, PlanetaryDrillRecipe recipe) {
            buffer.writeResourceLocation(recipe.getMachine());
            buffer.writeResourceLocation(recipe.getDimension());
            recipe.getFluidInput().writeToPacket(buffer);
            buffer.writeItem(recipe.getResultItem(null));
        }
    }
}