package io.github.fernthedev.spectatortoggle;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

/**
 * Holds the data that can be
 * written and parsed as JSON by GSON
 * to be stored in a file
 */
@Getter
public class JSONLocationWrapper {

    private UUID world;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;

    public JSONLocationWrapper(Location location) {
        this.world = location.getWorld().getUID();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

}
