package com.screennetwork.simplebreak.registry.impl;

import com.screennetwork.simplebreak.SimpleBreakPlugin;
import com.screennetwork.simplebreak.data.RewardBlock;
import com.screennetwork.simplebreak.registry.Registry;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class RewardBlockRegistry extends Registry<RewardBlock> {

    private final SimpleBreakPlugin plugin;

    public RewardBlockRegistry(SimpleBreakPlugin plugin) {
        this.plugin = plugin;

        loadRegistry();
    }

    public void loadRegistry() {
        clear();

        final FileConfiguration config = plugin.getConfig();
        final ConfigurationSection rewardSection = config.getConfigurationSection("rewardblocks");
        if (rewardSection == null) return;

        for (String key : rewardSection.getKeys(false)) {
            final ConfigurationSection section = rewardSection.getConfigurationSection(key);

            add(plugin.getRewardBlockAdapter().read(section));
        }
    }

    public RewardBlock getRewardBlock(Block block) {
        for (RewardBlock rewardBlock : getAll()) {
            if (rewardBlock.getMaterialData().equals(block.getState().getData())) return rewardBlock;
        }
        return null;
    }

}
