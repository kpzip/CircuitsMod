package com.kpzip.circuits.item.customItems;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class ItemResistor extends Item {
	
	public static final String RESISTANCE_NBT_KEY = "resistance";
	
	/* Valid Resistance Values:
	 * ""
	 * "1"
	 * "10"
	 * "100"
	 * "500"
	 * "1k"
	 * "5k"
	 * "10k"
	 * "100k"
	 * "500k"
	 * "1M"
	 */

	public ItemResistor(Settings settings) {
		super(settings);
	}
	
	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		NbtCompound nbt = stack.getNbt();
		if (!nbt.contains(RESISTANCE_NBT_KEY)) {
			nbt.putString(RESISTANCE_NBT_KEY, "");
		}
		tooltip.add(new TranslatableText("item.circuits.resistor.tooltip.v" + nbt.getString(RESISTANCE_NBT_KEY)));
	}

}
