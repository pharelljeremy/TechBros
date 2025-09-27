import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;

import java.awt.Color;

public class MyFirstBot extends Bot {

    // Main method to start the bot
    public static void main(String[] args) {
        new MyFirstBot().start();
    }

    @Override
    public void run() {
        // Set bot colors: body red, turret black, radar light blue, bullets red
        setBodyColor(Color.RED);
        setTurretColor(Color.BLACK);
        setRadarColor(new Color(173, 216, 230)); // light blue
        setBulletColor(Color.RED);
        setScanColor(Color.BLACK);

        // Main loop
        while (isRunning()) {
            // Move forward
            forward(100);

            // Rotate gun slowly to scan precisely
            // Changing from 360 degrees fast spin to a slower continuous scan
            turnGunRight(20);

            // Move back to avoid being predictable
            back(100);

            // Continue scanning but slower rotation for better tracking precision
            turnGunRight(20);
        }
    }

    // Fire with power 2 for better damage while conserving energy compared to fire(1)
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        fire(2);
    }

    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate bearing to bullet direction
        double bearing = calcBearing(e.getBullet().getDirection());

        // Turn perpendicular to bullet to avoid further hits
        turnRight(90 - bearing);
    }
}

//blah blah