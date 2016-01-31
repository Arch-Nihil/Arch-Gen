package com.arch.archgen.blocks;

import com.arch.archgen.lib.G;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class BasicItemBlocks extends ItemBlockWithMetadata {
	 public BasicItemBlocks (Block b) { 
		 super(b, b); 
	 }
	 
	 @Override
	 public String getUnlocalizedName(ItemStack iStack) {
		 String uN = this.getUnlocalizedName();
		 String stringX = uN.substring(5) + G.sizeArray[iStack.getItemDamage() + 1];
		 boolean isBricks = false;
		 
		 for (int i = 0; i < G.bricksArray.size(); i ++) {
			 if (uN.equals((G.bricksArray.get(i)).getUnlocalizedName())) {
				isBricks = true;
				break;
			 }
		 }
		 if (!isBricks)
			 stringX = uN.substring(5) + "-" + iStack.getItemDamage();
			 
		return stringX;
	 }
}