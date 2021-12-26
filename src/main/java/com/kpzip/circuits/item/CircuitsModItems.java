package com.kpzip.circuits.item;

import com.kpzip.circuits.item.customItems.ItemValuedComponent;
import com.kpzip.circuits.util.RegistryDictionary;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public final class CircuitsModItems {
	
	public static final RegistryDictionary<Item> ITEMS = new RegistryDictionary<Item>();
	
	public static final Item COPPER_WIRE = ITEMS.add(new Item(new FabricItemSettings().group(CircuitsModItemGroups.MATERIALS)), "copper_wire");
	
	public static final Item RESISTOR_UNKNOWN = ITEMS.add(new ItemValuedComponent(new FabricItemSettings().group(CircuitsModItemGroups.MATERIALS), "?", "resistor"), "resistor_unknown");
	
	public static final Item RESISTOR_1 = ITEMS.add(new ItemValuedComponent(new FabricItemSettings().group(CircuitsModItemGroups.MATERIALS), "1", "resistor"), "resistor1ohm");
	
}
