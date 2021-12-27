package com.kpzip.circuits.item.customItems;

import java.util.List;

import com.kpzip.circuits.item.CircuitsModItems;
import com.kpzip.circuits.util.FunnySymbols;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class ItemResistor extends Item {
	
	public static final String RESISTANCE_NBT_KEY = "resistance";
	
	public static final int[] CREATIVE_TAB_RESISTANCES = new int[]{0, 1, 10, 100, 500, 1000, 5000, 10000, 50000, 100000, 500000, 1000000, 5000000, 10000000};
	

	public ItemResistor(Settings settings) {
		super(settings);
	}
	
	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		/*
		 * TODO
		 * This code should probably be optimized or the result should be cached in some way since this function runs
		 * every frame
		 */
		
		//Get the resistance of the itemStack
		int resistance = getItemStackResistance(stack);
		
		//If the resistance is 0, assume that it is undefined and add just write "Unknown Resistance"
		if (resistance <= 0) {
			tooltip.add(new TranslatableText("item.circuits.resistor.tooltip.0").formatted(Formatting.GRAY));
		}
		else {
			String text = "";
			
			//Set metric prefixes
			if (resistance >= 1000000) {
				text = String.valueOf(resistance/1000000.0f);
				text = text.substring(0, text.length() >= 5 ? 5 : text.length());
				text += " M" + FunnySymbols.OMEGA;
			}
			else if (resistance >= 1000) {
				text = String.valueOf(resistance/1000.0f);
				text = text.substring(0, text.length() >= 4 ? 4 : text.length());
				text += " k" + FunnySymbols.OMEGA;
			}
			else {
				text = String.valueOf(resistance);
				text += " " + FunnySymbols.OMEGA;
			}
			
			//remove unnecessary decimal points to get rid of things like 5. and 8.0
			String[] split = text.split("\\.");
			if (split.length > 1) {
				boolean doesNotNeedDecimal = true;
				for (int i = 0; i < split[1].split(" ")[0].length(); i++) {
					if (split[1].charAt(i) != '0') {
						doesNotNeedDecimal = false;
					}
				}
				if (doesNotNeedDecimal) {
					text = split[0] + " " + split[1].split(" ")[1];
				}
			}
			
			//add the tooltip with the formatted text
			//This does not need to use a translatable text component since it used only numbers and SI unit symbols
			tooltip.add(new LiteralText(text).formatted(Formatting.GRAY));
		}
		
	}
	
	public static ItemStack setItemStackResistance(ItemStack stack, int resistance) {
		if (stack.getItem() instanceof ItemResistor) {
			NbtCompound nbt = stack.getNbt();
			
			//Make sure NBT is non-null to avoid null pointer exception
			if (nbt == null) {
				nbt = new NbtCompound();
				stack.setNbt(nbt);
			}
			nbt.putInt(RESISTANCE_NBT_KEY, resistance);
			
			return stack;
		}
		else {
			throw new IllegalArgumentException("Cannot set the resistance of a non-resistor item.");
		}
	}
	
	public static int getItemStackResistance(ItemStack stack) {
		if (stack.getItem() instanceof ItemResistor) {
			NbtCompound nbt = stack.getNbt();
			
			//Make sure NBT is non-null to avoid null pointer exception
			if (nbt == null) {
				nbt = new NbtCompound();
				stack.setNbt(nbt);
			}
			if (!nbt.contains(RESISTANCE_NBT_KEY)) {
				nbt.putInt(RESISTANCE_NBT_KEY, 0);
			}
			return nbt.getInt(RESISTANCE_NBT_KEY);
		}
		else {
			throw new IllegalArgumentException("Cannot find the resistance of a non-resistor item.");
		}
	}
	
	//Uses the list of resistance values and generates an array of ItemStacks that need to be added to the creative tab
	public static ItemStack[] getItemStacksForCreativeTab() {
		ItemStack[] items = new ItemStack[CREATIVE_TAB_RESISTANCES.length];
		NbtCompound nbt;
		ItemStack resistor;
		for (int i = 0; i < items.length; i++) {
			nbt = new NbtCompound();
			nbt.putInt(RESISTANCE_NBT_KEY, CREATIVE_TAB_RESISTANCES[i]);
			resistor = new ItemStack(CircuitsModItems.RESISTOR, 1);
			resistor.setNbt(nbt);
			items[(items.length - 1) - i] = resistor;
		}
		return items;
	}

}
