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
import redis.clients.jedis.Jedis;

@AllArgsConstructor
public class BreakListener implements Listener {

    private static final String world = SimpleBreakPlugin.getInstance().getConfig().getString("world");

    private final SimpleBreakPlugin plugin;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        final Block block = event.getBlock();

        if (!block.getWorld().getName().equalsIgnoreCase(world)) return;

        final RewardBlock rewardBlock = plugin.getRewardBlockRegistry().getRewardBlock(block);
        if (rewardBlock == null) return;

        final Player player = event.getPlayer();

        final int i = plugin.getAntiNuke().get(player.getUniqueId());
        if (i == 280) {
            send(player, "Nuker abusivo [28/s]");
            return;
        } else if (i == 250) {
            send(player, "Nuker [25/s]");
            return;
        } else if (i == 230) {
            send(player, "Possível nuker [23/s]");
            return;
        }

        rewardBlock.run(player);

        event.setCancelled(true);
        block.setType(Material.AIR);

        plugin.getAntiNuke().add(player.getUniqueId());

    }

    private void send(Player player, String reason) {
        final long l = plugin.getAntiNuke().warns(player.getUniqueId());
        if (l + 30000L < System.currentTimeMillis()) {
            try (Jedis jedis = plugin.getJedisPool().getResource()) {
                jedis.publish("report:", player.getName() + ":" + reason);
                plugin.getAntiNuke().report(player.getUniqueId());
            }
        }
    }

}
