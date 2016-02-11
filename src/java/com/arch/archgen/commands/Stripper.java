package com.arch.archgen.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.arch.archgen.config.Config;
import com.arch.archgen.lib.G;

import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class Stripper implements ICommand {
	private List alias;
	public static final LinkedList<Block> bList = new LinkedList<Block>();
	private Block b;
	private int range, rangeUp;
	
	public static void init() {
		if (Config.doGen) {
			for (int i = 0; i < G.stoneArray.size(); i ++) {
				bList.add(G.stoneArray.get(i));
			}
			for (int i = 0; i < G.sandArray.size(); i ++) {
				bList.add(G.sandArray.get(i));
			}
		}
		bList.add(Blocks.stone);
		bList.add(Blocks.grass);
		bList.add(Blocks.dirt);
		bList.add(Blocks.snow);
		bList.add(Blocks.snow_layer);
		bList.add(Blocks.red_flower);
		bList.add(Blocks.yellow_flower);
		bList.add(Blocks.log);
		bList.add(Blocks.leaves);
		bList.add(Blocks.leaves2);
		bList.add(Blocks.log2);
		bList.add(Blocks.grass);
		bList.add(Blocks.tallgrass);
		bList.add(Blocks.cobblestone);
		bList.add(Blocks.sand);
		bList.add(Blocks.sandstone);
		bList.add(Blocks.gravel);
		bList.add(Blocks.lava);
		bList.add(Blocks.flowing_lava);
		bList.add(Blocks.water);
		bList.add(Blocks.flowing_water);
		bList.add(Blocks.mossy_cobblestone);
		bList.add(Blocks.torch);
		bList.add(Blocks.planks);
		bList.add(Blocks.fence);
		bList.add(Blocks.rail);
		bList.add(Blocks.obsidian);
		bList.add(Blocks.clay);
		bList.add(Blocks.netherrack);
		bList.add(Blocks.hardened_clay);
		bList.add(Blocks.stained_hardened_clay);
		bList.add(Blocks.soul_sand);
		bList.add(Blocks.end_stone);
	}
	
	public Stripper()
	{
		this.alias = new ArrayList();
	    this.alias.add("stripper");
	    this.alias.add("strip");
	}

	@Override
	public String getCommandName() {
		return "strip";
	}

	@Override
	public String getCommandUsage(ICommandSender cSender) {
		return "strip <range> <none / x>";
	}
	
	@Override
	public List getCommandAliases() {
		return this.alias;
	}

	@Override
	public void processCommand(ICommandSender cSender, String[] par) {
		if (par.length > 2 || par.length < 1) {
			cSender.addChatMessage(new ChatComponentText("Invalid arguments. Only one or two parameters are allowed."));
			return;
		}
		else if (!par[0].matches("\\d+")) {
			cSender.addChatMessage(new ChatComponentText("Invalid arguments. First parameter must be a number."));
			return;
		}
		else if (Integer.parseInt(par[0].toString()) > 100 || Integer.parseInt(par[0].toString()) < 1) {
			cSender.addChatMessage(new ChatComponentText("Invalid arguments. Number must be positive and no more than 100."));
			return;
		}
		else {
			cSender.addChatMessage(new ChatComponentText("Valid arguments. Stripping starting."));
			if (cSender instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer) cSender;
				World w = DimensionManager.getWorld(p.dimension);
				range = Integer.parseInt(par[0].toString());
				if (par.length != 1 && par[1] == "x")
					rangeUp = range;
				else
					rangeUp = (int) Math.min(255 - p.posY, Math.max(range / 4, 16));
				for (int x = (int) (p.posX - range); x <= p.posX + range; x ++) {
					for (int y = 1; y <= p.posY + rangeUp; y ++) {
						for (int z = (int) (p.posZ - range); z <= p.posZ + range; z ++) {
							b = w.getBlock(x, y, z);
							if (b != Blocks.air && bList.contains(b))
								w.setBlockToAir(x, y, z);
						}
					}
				}
			}
			cSender.addChatMessage(new ChatComponentText("Stripping over. Hope you enjoyed."));
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender cSender) {
		if (cSender instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) cSender;
			if (MinecraftServer.getServer().getConfigurationManager().func_152596_g(p.getGameProfile())) {
				return true;
			}
			else
				return false;
		 }
		else
			return false;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender cSender, String[] par) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] par, int i) {
		return false;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}
