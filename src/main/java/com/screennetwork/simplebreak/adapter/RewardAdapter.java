package com.screennetwork.simplebreak.adapter;

import com.screennetwork.simplebreak.data.Reward;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class RewardAdapter {

    public List<Reward> read(ConfigurationSection section) {
        final List<Reward> rewards = new ArrayList<>();
        for (String key : section.getKeys(false)) {
            final ConfigurationSection rewardSection = section.getConfigurationSection(key);

            rewards.add(
              new Reward(
                key,
                rewardSection.getStringList("commands"),
                rewardSection.getDouble("chance")
              )
            );
        }

        return rewards;
    }

}
