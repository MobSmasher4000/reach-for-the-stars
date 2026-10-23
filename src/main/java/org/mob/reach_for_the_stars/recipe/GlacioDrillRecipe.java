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
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class GlacioDrillRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final FluidStack fluidInput;
    private final ItemStack output;
    private final ResourceLocation dimension;

    public GlacioDrillRecipe(ResourceLocation id, FluidStack fluidInput, ItemStack output, ResourceLocation dimension) {
        this.id = id;
        this.fluidInput = fluidInput;
        this.output = output;
        this.dimension = dimension;
    }

    public FluidStack getFluidInput() { return this.fluidInput; }
    public ItemStack getResultItem() { return this.output.copy(); }
    public ResourceLocation getDimension() { return this.dimension; }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return !pLevel.isClientSide();
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) { return true; }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() { return id; }

    @Override
    public RecipeSerializer<?> getSerializer() { return Serializer.INSTANCE; }

    @Override
    public RecipeType<?> getType() { return Type.INSTANCE; }

    public static class Type implements RecipeType<GlacioDrillRecipe>{
        private Type(){}
        public static final GlacioDrillRecipe.Type INSTANCE = new GlacioDrillRecipe.Type();
        public static final String ID = "glacio_drill";
    }

    public static class Serializer implements RecipeSerializer<GlacioDrillRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = resourceLocation("glacio_drill");

        @Override
        public GlacioDrillRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            // Read "fluid" object
            JsonObject fluidObj = GsonHelper.getAsJsonObject(pSerializedRecipe, "fluid");
            ResourceLocation fluidRes = ResourceLocation.parse(GsonHelper.getAsString(fluidObj, "fluid"));
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidRes);
            int amount = GsonHelper.getAsInt(fluidObj, "amount");
            FluidStack fluidInput = new FluidStack(fluid, amount);

            // Read "result" object
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

            ResourceLocation dimension = ResourceLocation.parse(GsonHelper.getAsString(pSerializedRecipe, "dimension"));

            return new GlacioDrillRecipe(pRecipeId, fluidInput, output, dimension);
        }

        @Override
        public @Nullable GlacioDrillRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            FluidStack fluidInput = FluidStack.readFromPacket(pBuffer);
            ItemStack output = pBuffer.readItem();
            ResourceLocation dimension = pBuffer.readResourceLocation();
            return new GlacioDrillRecipe(pRecipeId, fluidInput, output, dimension);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, GlacioDrillRecipe pRecipe) {
            pRecipe.fluidInput.writeToPacket(pBuffer);
            pBuffer.writeItem(pRecipe.output);
            pBuffer.writeResourceLocation(pRecipe.dimension);
        }
    }
}