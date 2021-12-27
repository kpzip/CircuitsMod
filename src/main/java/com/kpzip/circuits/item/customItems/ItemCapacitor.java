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

public class ItemCapacitor extends Item {
	
	public static final String CAPACITANCE_NBT_KEY = "capacitance";
	
	public static final int[] CREATIVE_TAB_CAPACITANCES = new int[]{0, 1, 100, 500, 1000, 10000, 100000, 1000000, 10000000, 100000000};
	

	public ItemCapacitor(Settings settings) {
		super(settings);
	}
	
	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		
		int capacitance = getItemStackCapacitance(stack);
		
		if (capacitance <= 0) {
			tooltip.add(new TranslatableText("item.circuits.capacitor.tooltip.0").formatted(Formatting.GRAY));
		}
		else {
			String text = "";
			
			//Set metric prefixes
			if (capacitance >= 1000) {
				text = String.valueOf(capacitance/1000000.0f);
				text = text.substring(0, text.length() >= 7 ? 7 : text.length());
				text += " " + FunnySymbols.MEW + "F";
			}
			else {
				text = String.valueOf(capacitance);
				text += " pF";
			}
			
			//remove the decimal point and anything after that to remove things like 10. and 10.0
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
			
			tooltip.add(new LiteralText(text).formatted(Formatting.GRAY));
		}
		
	}
	
	public static ItemStack setItemStackCapacitance(ItemStack stack, int capacitance) {
		if (stack.getItem() instanceof ItemCapacitor) {
			NbtCompound nbt = stack.getNbt();
			
			//Make sure NBT is non-null to avoid null pointer exception
			if (nbt == null) {
				nbt = new NbtCompound();
				stack.setNbt(nbt);
			}
			nbt.putInt(CAPACITANCE_NBT_KEY, capacitance);
			
			return stack;
		}
		else {
			throw new IllegalArgumentException("Cannot set the capacitance of a non-capacitor item.");
		}
	}
	
	public static int getItemStackCapacitance(ItemStack stack) {
		if (stack.getItem() instanceof ItemCapacitor) {
			NbtCompound nbt = stack.getNbt();
			
			//Make sure NBT is non-null to avoid null pointer exception
			if (nbt == null) {
				nbt = new NbtCompound();
				stack.setNbt(nbt);
			}
			if (!nbt.contains(CAPACITANCE_NBT_KEY)) {
				nbt.putInt(CAPACITANCE_NBT_KEY, 0);
			}
			return nbt.getInt(CAPACITANCE_NBT_KEY);
		}
		else {
			throw new IllegalArgumentException("Cannot find the capacitcance of a non-capacitor item.");
		}
	}
	
	public static ItemStack[] getItemStacksForCreativeTab() {
		ItemStack[] items = new ItemStack[CREATIVE_TAB_CAPACITANCES.length];
		NbtCompound nbt;
		ItemStack capacitor;
		for (int i = 0; i < items.length; i++) {
			nbt = new NbtCompound();
			nbt.putInt(CAPACITANCE_NBT_KEY, CREATIVE_TAB_CAPACITANCES[i]);
			capacitor = new ItemStack(CircuitsModItems.CAPACITOR, 1);
			capacitor.setNbt(nbt);
			items[(items.length - 1) - i] = capacitor;
		}
		return items;
	}

}
