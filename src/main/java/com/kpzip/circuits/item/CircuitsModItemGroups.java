package com.kpzip.circuits.item;

import com.kpzip.circuits.item.customItems.ItemCapacitor;
import com.kpzip.circuits.item.customItems.ItemResistor;
import com.kpzip.circuits.util.Instances;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public final class CircuitsModItemGroups {
	
	public static final ItemGroup MATERIALS = FabricItemGroupBuilder.create(
			new Identifier(Instances.MOD_ID, "circuits_materials")).icon(
			() -> new ItemStack(CircuitsModItems.COPPER_WIRE)).build();
	
	public static final ItemGroup COMPONENTS = FabricItemGroupBuilder.create(
			new Identifier(Instances.MOD_ID, "circuits_components")).icon(
			() -> new ItemStack(CircuitsModItems.RESISTOR))
			.appendItems(stacks -> {
				
				
				//Add Capacitors
				ItemStack[] capacitors = ItemCapacitor.getItemStacksForCreativeTab();
				for (ItemStack e : capacitors) {
					stacks.add(e);
				}
				
				//Add Resistors
				ItemStack[] resistors = ItemResistor.getItemStacksForCreativeTab();
				for (ItemStack e : resistors) {
					stacks.add(e);
				}
				
			})
			.build();
}
