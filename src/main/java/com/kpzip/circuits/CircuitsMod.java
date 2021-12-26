package com.kpzip.circuits;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kpzip.circuits.item.CircuitsModItems;
import com.kpzip.circuits.util.Instances;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CircuitsMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger(Instances.MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Loading Circuits Mod");
		
		registerItems();
		
		
	}
	
	public void registerItems() {
		for (int i = 0; i < CircuitsModItems.ITEMS.size(); i++) {
			Registry.register(Registry.ITEM, new Identifier(Instances.MOD_ID, CircuitsModItems.ITEMS.getRegistryName(i)), CircuitsModItems.ITEMS.getObject(i));
		}
	}
}
