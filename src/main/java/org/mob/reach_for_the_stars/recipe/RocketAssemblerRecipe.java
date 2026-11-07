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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static org.mob.reach_for_the_stars.Reach_for_the_stars.resourceLocation;

public class RocketAssemblerRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final int tier;
    private final NonNullList<Ingredient> inputs;
    private final List<Integer> inputCounts;
    private final ItemStack output;

    public RocketAssemblerRecipe(ResourceLocation id, int tier, NonNullList<Ingredient> inputs, List<Integer> inputCounts, ItemStack output) {
        this.id = id;
        this.tier = tier;
        this.inputs = inputs;
        this.inputCounts = inputCounts;
        this.output = output;
    }

    public int getTier() {
        return this.tier;
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

        int[] availableCounts = new int[container.getContainerSize()];
        for (int i = 0; i < container.getContainerSize(); i++) {
            availableCounts[i] = container.getItem(i).getCount();
        }

        for (int i = 0; i < inputs.size(); i++) {
            Ingredient ingredient = inputs.get(i);
            int remainingNeeded = inputCounts.get(i);

            for (int slot = 0; slot < container.getContainerSize(); slot++) {
                if (remainingNeeded <= 0) break;

                ItemStack stack = container.getItem(slot);

                if (!stack.isEmpty() && availableCounts[slot] > 0 && ingredient.test(stack)) {
                    int take = Math.min(availableCounts[slot], remainingNeeded);
                    availableCounts[slot] -= take;
                    remainingNeeded -= take;
                }
            }

            if (remainingNeeded > 0) {
                return false;
            }
        }
        return true;
    }

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

    public static class Type implements RecipeType<RocketAssemblerRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "rocket_assembler";
    }

    public static class Serializer implements RecipeSerializer<RocketAssemblerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = resourceLocation("rocket_assembler");

        @Override
        public RocketAssemblerRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            int tier = GsonHelper.getAsInt(json, "tier");

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

            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            return new RocketAssemblerRecipe(recipeId, tier, inputs, counts, result);
        }

        @Override
        public @Nullable RocketAssemblerRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int tier = buffer.readInt();

            int size = buffer.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(size, Ingredient.EMPTY);
            List<Integer> counts = new ArrayList<>();
            
            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
                counts.add(buffer.readVarInt());
            }
            
            ItemStack output = buffer.readItem();
            return new RocketAssemblerRecipe(recipeId, tier, inputs, counts, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, RocketAssemblerRecipe recipe) {
            buffer.writeInt(recipe.getTier());

            buffer.writeVarInt(recipe.getIngredientsList().size());
            for (int i = 0; i < recipe.getIngredientsList().size(); i++) {
                recipe.getIngredientsList().get(i).toNetwork(buffer);
                buffer.writeVarInt(recipe.getInputCounts().get(i));
            }
            
            buffer.writeItem(recipe.getResultItem(null));
        }
    }
}