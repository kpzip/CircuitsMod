package com.kpzip.circuits.item.customItems;

import java.util.List;

import com.kpzip.circuits.item.CircuitsModItems;

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
	
	public static final int[] CREATIVE_TAB_RESISTANCES = new int[]{0, 1, 10, 100, 500, 1000};
	

	public ItemResistor(Settings settings) {
		super(settings);
	}
	
	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		
		int resistance = getItemStackResistance(stack);
		
		if (resistance <= 0) {
			tooltip.add(new TranslatableText("item.circuits.resistor.tooltip.0").formatted(Formatting.GRAY));
		}
		else {
			String text = "";
			if (resistance >= 1000000) {
				text = String.valueOf(resistance/1000000.0f).substring(0, String.valueOf(resistance/1000000.0f).length() >= 4 ? 4 : String.valueOf(resistance/1000000.0f).length());
				text += " MΩ";
			}
			else if (resistance >= 1000) {
				text = String.valueOf(resistance/1000.0f).substring(0, String.valueOf(resistance/1000.0f).length() >= 3 ? 3 : String.valueOf(resistance/1000.0f).length());
				text += " kΩ";
			}
			else {
				text = String.valueOf(resistance);
				text += " Ω";
			}
			tooltip.add(new LiteralText(text).formatted(Formatting.GRAY));
		}
		
	}
	
	public static ItemStack setItemStackResistance(ItemStack stack, int resistance) {
		if (stack.getItem() instanceof ItemResistor) {
			NbtCompound nbt = stack.getNbt();
			
			//Make sure NBT is non-null to avoid null pointer exception
			if (nbt == null) {
				nbt = new NbtCompound();
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
	
	public static ItemStack[] getItemStacksForCreativeTab() {
		ItemStack[] items = new ItemStack[CREATIVE_TAB_RESISTANCES.length];
		for (int i = 0; i > items.length; i++) {
			NbtCompound nbt = new NbtCompound();
			nbt.putString(ItemResistor.RESISTANCE_NBT_KEY, "");
			ItemStack resistor = new ItemStack(CircuitsModItems.RESISTOR);
			resistor.setNbt(nbt);
			items[(items.length - 1) - i] = resistor;
		}
		return items;
	}

}
