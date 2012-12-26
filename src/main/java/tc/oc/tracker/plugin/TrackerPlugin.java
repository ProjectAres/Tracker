package tc.oc.tracker.plugin;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import tc.oc.tracker.Trackers;
import tc.oc.tracker.trackers.ExplosiveTracker;
import tc.oc.tracker.trackers.base.SimpleExplosiveTracker;

public class TrackerPlugin extends JavaPlugin {
    @Override
    public void onDisable() {
        Trackers.getManager().clearTracker(ExplosiveTracker.class, SimpleExplosiveTracker.class);
    }

    @Override
    public void onEnable() {
        // basic operation listeners
        this.registerEvents(new WorldListener(Trackers.getManager()));

        // tracker setup
        ExplosiveTracker explosiveTracker = new SimpleExplosiveTracker();

        this.registerEvents(new ExplosiveListener(explosiveTracker));
        Trackers.getManager().setRealTracker(ExplosiveTracker.class, explosiveTracker);
    }

    private void registerEvents(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }
}
