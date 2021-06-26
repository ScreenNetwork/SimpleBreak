package com.screennetwork.simplebreak.data;

import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import java.util.List;

@Data
public class RewardBlock {

    private final MaterialData materialData;

    private final List<Reward> rewards;

    public void run(Player player) {
        for (Reward reward : rewards) {
            reward.execute(player);
        }
    }

}
