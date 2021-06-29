package com.screennetwork.simplebreak.data;

import com.screennetwork.simplebreak.utils.ActionBar;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

@Data
public class Reward {

    private final String name;

    private final List<String> commands;

    private final double chance;

    public void execute(Player player) {
        if ((Math.random() * 100) > chance) return;

        for (String command : commands) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
              command.replace("{player}", player.getName()));
        }

        ActionBar.sendMessage(player, "§6+" + name + ".");
    }

}
