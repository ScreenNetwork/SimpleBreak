package com.screennetwork.simplebreak;

import com.screennetwork.simplebreak.adapter.RewardAdapter;
import com.screennetwork.simplebreak.adapter.RewardBlockAdapter;
import com.screennetwork.simplebreak.listener.BreakListener;
import com.screennetwork.simplebreak.registry.impl.RewardBlockRegistry;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class SimpleBreakPlugin extends JavaPlugin {

    private RewardAdapter rewardAdapter;
    private RewardBlockAdapter rewardBlockAdapter;

    private RewardBlockRegistry rewardBlockRegistry;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        rewardAdapter = new RewardAdapter();
        rewardBlockAdapter = new RewardBlockAdapter(rewardAdapter);

        rewardBlockRegistry = new RewardBlockRegistry(this);

        registerListener(
          new BreakListener(rewardBlockRegistry)
        );
    }

    private void registerListener(Listener... listeners) {
        final PluginManager pluginManager = getServer().getPluginManager();

        for (Listener listener : listeners) pluginManager.registerEvents(listener, this);
    }

    public static SimpleBreakPlugin getInstance() {
        return getPlugin(SimpleBreakPlugin.class);
    }

}
