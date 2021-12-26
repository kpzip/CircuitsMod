package com.kpzip.circuits.item;

import com.kpzip.circuits.util.RegistryDictionary;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public final class CircuitsModItems {
	
	public static final RegistryDictionary<Item> ITEMS = new RegistryDictionary<Item>();
	
	public static final Item COPPER_WIRE = ITEMS.add(new Item(new FabricItemSettings().group(CircuitsModItemGroups.MATERIALS)), "copper_wire");

}
