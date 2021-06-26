package com.screennetwork.simplebreak.adapter;

import com.screennetwork.simplebreak.data.Reward;
import com.screennetwork.simplebreak.data.RewardBlock;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.material.MaterialData;

import java.util.List;

@RequiredArgsConstructor
public class RewardBlockAdapter {

    private final RewardAdapter rewardAdapter;

    public RewardBlock read(ConfigurationSection section) {
        final String[] material = section.getString("material").split(":");

        final MaterialData materialData = new MaterialData(
          Material.getMaterial(Integer.parseInt(material[0])),
          Byte.parseByte(material[1])
        );

        final ConfigurationSection rewardSection = section.getConfigurationSection("rewards");
        if (rewardSection == null) return null;

        final List<Reward> rewards = rewardAdapter.read(rewardSection);

        return new RewardBlock(
          materialData,
          rewards
        );

    }

}
