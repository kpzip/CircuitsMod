package com.kpzip.circuits.item.customItems;

import java.util.List;

import com.kpzip.circuits.util.Instances;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class ItemValuedComponent extends Item {
	
	private String value;
	private String type;
	
	public ItemValuedComponent(Settings settings, String value, String type) {
		super(settings);
		this.value = value;
		this.type = type;
	}
	
	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new TranslatableText("item." + Instances.MOD_ID + "." + type + ".tooltip.v" + value));
	}
	
	

}
