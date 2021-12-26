package com.kpzip.circuits.item;

import com.kpzip.circuits.util.Instances;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public final class CircuitsModItemGroups {
	
	public static final ItemGroup MATERIALS = FabricItemGroupBuilder.build(
			new Identifier(Instances.MOD_ID, "circuits_materials"),
			() -> new ItemStack(CircuitsModItems.COPPER_WIRE));
}
