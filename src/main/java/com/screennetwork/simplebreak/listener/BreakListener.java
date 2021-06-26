package com.screennetwork.simplebreak.listener;

import com.screennetwork.simplebreak.SimpleBreakPlugin;
import com.screennetwork.simplebreak.data.RewardBlock;
import com.screennetwork.simplebreak.registry.impl.RewardBlockRegistry;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@AllArgsConstructor
public class BreakListener implements Listener {

    private static final String world = SimpleBreakPlugin.getInstance().getConfig().getString("world");

    private final RewardBlockRegistry rewardBlockRegistry;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        final Block block = event.getBlock();

        if (!block.getWorld().getName().equalsIgnoreCase(world)) return;

        final RewardBlock rewardBlock = rewardBlockRegistry.getRewardBlock(block);
        if (rewardBlock == null) return;

        final Player player = event.getPlayer();

        rewardBlock.run(player);

        event.setCancelled(true);
        block.setType(Material.AIR);

    }

}
