package com.kpzip.circuits.item;

import com.kpzip.circuits.item.customItems.ItemResistor;
import com.kpzip.circuits.util.Instances;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public final class CircuitsModItemGroups {
	
	public static final ItemGroup MATERIALS = FabricItemGroupBuilder.create(
			new Identifier(Instances.MOD_ID, "circuits_materials")).icon(
			() -> new ItemStack(CircuitsModItems.COPPER_WIRE)).build();
	
	public static final ItemGroup COMPONENTS = FabricItemGroupBuilder.create(
			new Identifier(Instances.MOD_ID, "circuits_components")).icon(
			() -> new ItemStack(CircuitsModItems.RESISTOR))
			.appendItems(stacks -> {
				
				ItemStack resistor = new ItemStack(CircuitsModItems.RESISTOR);
				NbtCompound nbt = resistor.getNbt();
				
				NbtCompound nbt_resistor_unknown = nbt.copy();
				nbt_resistor_unknown.putString(ItemResistor.RESISTANCE_NBT_KEY, "");
				ItemStack resistor_unknown = resistor.copy();
				resistor_unknown.setNbt(nbt_resistor_unknown);
				stacks.add(resistor_unknown);
				
				NbtCompound nbt_resistor_1 = nbt.copy();
				nbt_resistor_1.putString(ItemResistor.RESISTANCE_NBT_KEY, "1");
				ItemStack resistor_1 = resistor.copy();
				resistor_unknown.setNbt(nbt_resistor_1);
				stacks.add(resistor_1);
			})
			.build();
}
