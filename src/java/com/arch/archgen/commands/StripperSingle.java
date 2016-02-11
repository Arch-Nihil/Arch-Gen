package com.arch.archgen.commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class StripperSingle implements ICommand {
	private List alias;
	private String[] bName;
	private Block b, bToFind;
	private int range, rangeUp;
	
	public StripperSingle()
	{
		this.alias = new ArrayList();
	    this.alias.add("strippersingle");
	    this.alias.add("strips");
	}

	@Override
	public String getCommandName() {
		return "sstrip";
	}

	@Override
	public String getCommandUsage(ICommandSender cSender) {
		return "strips <range> <block> <none / x>";
	}
	
	@Override
	public List getCommandAliases() {
		return this.alias;
	}

	@Override
	public void processCommand(ICommandSender cSender, String[] par) {
		if (par.length < 2 || par.length > 3) {
			cSender.addChatMessage(new ChatComponentText("Invalid arguments. Only two or three parameters are allowed."));
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
			if (par[1].contains(":")) {
				bName = par[1].split(":");
				if (GameRegistry.findBlock(bName[0], bName[1]) == null)
					cSender.addChatMessage(new ChatComponentText("Invalid arguments. Block not found."));
				else {
					bToFind = GameRegistry.findBlock(bName[0], bName[1]);
					cSender.addChatMessage(new ChatComponentText("Valid arguments. Stripping starting."));
					if (cSender instanceof EntityPlayer) {
						EntityPlayer p = (EntityPlayer) cSender;
						World w = DimensionManager.getWorld(p.dimension);
						range = Integer.parseInt(par[0].toString());
						if (par.length != 2 && par[2] == "x")
							rangeUp = range;
						else
							rangeUp = (int) Math.min(255 - p.posY, Math.max(range / 4, 16));
						for (int x = (int) (p.posX - range); x <= p.posX + range; x ++) {
							for (int y = 1; y <= p.posY + rangeUp; y ++) {
								for (int z = (int) (p.posZ - range); z <= p.posZ + range; z ++) {
									b = w.getBlock(x, y, z);
									if (b == bToFind)
										w.setBlockToAir(x, y, z);
								}
							}
						}
					}
					cSender.addChatMessage(new ChatComponentText("Stripping over. Hope you enjoyed."));
				}
			}
			else
				cSender.addChatMessage(new ChatComponentText("Invalid arguments. Format for block is ModID:BlockName."));
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
