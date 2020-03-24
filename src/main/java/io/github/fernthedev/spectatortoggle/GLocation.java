package io.github.fernthedev.spectatortoggle;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

@Getter
public class GLocation {

    private UUID world;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;

    public GLocation(Location location) {
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
