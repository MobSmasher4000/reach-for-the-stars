package org.mob.reach_for_the_stars.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
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

import java.util.ArrayList;
import java.util.List;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class SkyblockBuilderRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final FluidStack fluidInput;
    private final NonNullList<Ingredient> inputs;
    private final List<Integer> inputCounts;
    private final ItemStack output;

    public SkyblockBuilderRecipe(ResourceLocation id, FluidStack fluidInput, NonNullList<Ingredient> inputs, List<Integer> inputCounts, ItemStack output) {
        this.id = id;
        this.fluidInput = fluidInput;
        this.inputs = inputs;
        this.inputCounts = inputCounts;
        this.output = output;
    }

    public FluidStack getFluidInput() {
        return this.fluidInput;
    }

    public NonNullList<Ingredient> getIngredientsList() {
        return this.inputs;
    }

    public List<Integer> getInputCounts() {
        return this.inputCounts;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if (level.isClientSide()) return false;

        // Create a fake tally of the item counts in each slot so it doesn't double-count items
        int[] availableCounts = new int[container.getContainerSize()];
        for (int i = 0; i < container.getContainerSize(); i++) {
            availableCounts[i] = container.getItem(i).getCount();
        }

        // Check every ingredient the recipe requires
        for (int i = 0; i < inputs.size(); i++) {
            Ingredient ingredient = inputs.get(i);
            int remainingNeeded = inputCounts.get(i);

            // Look through all slots to fulfill this ingredient
            for (int slot = 0; slot < container.getContainerSize(); slot++) {
                if (remainingNeeded <= 0) break; // Found enough of this ingredient!

                ItemStack stack = container.getItem(slot);

                // If this slot has the right item, and hasn't been completely used up yet
                if (!stack.isEmpty() && availableCounts[slot] > 0 && ingredient.test(stack)) {
                    // Take however much we need, up to the amount left in the slot
                    int take = Math.min(availableCounts[slot], remainingNeeded);
                    availableCounts[slot] -= take;
                    remainingNeeded -= take;
                }
            }

            // If we checked every slot and STILL need more of this ingredient, the recipe fails
            if (remainingNeeded > 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
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

    public static class Type implements RecipeType<SkyblockBuilderRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "skyblock_builder";
    }

    public static class Serializer implements RecipeSerializer<SkyblockBuilderRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = resourceLocation("skyblock_builder");

        @Override
        public SkyblockBuilderRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            // Read Fluid
            JsonObject fluidObj = GsonHelper.getAsJsonObject(json, "fluid");
            ResourceLocation fluidRes = ResourceLocation.parse(GsonHelper.getAsString(fluidObj, "fluid"));
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidRes);
            int amount = GsonHelper.getAsInt(fluidObj, "amount");
            FluidStack fluidInput = new FluidStack(fluid, amount);

            // Read Ingredients
            JsonArray ingredientsJson = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.create();
            List<Integer> counts = new ArrayList<>();

            for (JsonElement element : ingredientsJson) {
                JsonObject entry = element.getAsJsonObject();
                Ingredient ingredient = Ingredient.fromJson(entry.get("ingredient"));
                int count = GsonHelper.getAsInt(entry, "count", 1);
                inputs.add(ingredient);
                counts.add(count);
            }

            // Read Result
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            return new SkyblockBuilderRecipe(recipeId, fluidInput, inputs, counts, result);
        }

        @Override
        public @Nullable SkyblockBuilderRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            FluidStack fluidInput = FluidStack.readFromPacket(buffer);

            int size = buffer.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(size, Ingredient.EMPTY);
            List<Integer> counts = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
                counts.add(buffer.readVarInt());
            }
            ItemStack output = buffer.readItem();
            
            return new SkyblockBuilderRecipe(recipeId, fluidInput, inputs, counts, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, SkyblockBuilderRecipe recipe) {
            recipe.getFluidInput().writeToPacket(buffer);

            buffer.writeVarInt(recipe.getIngredientsList().size());
            for (int i = 0; i < recipe.getIngredientsList().size(); i++) {
                recipe.getIngredientsList().get(i).toNetwork(buffer);
                buffer.writeVarInt(recipe.getInputCounts().get(i));
            }
            buffer.writeItem(recipe.getResultItem(null));
        }
    }
}