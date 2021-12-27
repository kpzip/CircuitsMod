package com.kpzip.circuits.item;

import com.kpzip.circuits.item.customItems.ItemCapacitor;
import com.kpzip.circuits.item.customItems.ItemResistor;
import com.kpzip.circuits.util.RegistryDictionary;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public final class CircuitsModItems {
	
	public static final RegistryDictionary<Item> ITEMS = new RegistryDictionary<Item>();
	
	
	
	
	//Materials
	public static final Item COPPER_WIRE = ITEMS.add(new Item(new FabricItemSettings().group(CircuitsModItemGroups.MATERIALS)), "copper_wire");
	
	//Components
	public static final Item RESISTOR = ITEMS.add(new ItemResistor(new FabricItemSettings()), "resistor");
	public static final Item CAPACITOR = ITEMS.add(new ItemCapacitor(new FabricItemSettings()), "capacitor");
	public static final Item TRANSISTOR = ITEMS.add(new Item(new FabricItemSettings()), "transistor");
	
}
