import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;

import java.awt.Color;

public class MyFirstBot extends Bot {

    // starting the bot
    public static void main(String[] args) {
        new MyFirstBot().start();
    }

    @Override
    public void run() {
        // the bot colors
        setBodyColor(Color.RED);
        setTurretColor(Color.BLACK);
        setRadarColor(new Color(173, 216, 230)); // light blue
        setBulletColor(Color.RED);
        setScanColor(Color.BLACK);

        // Main loop
        while (isRunning()) {
            // Move forward
            forward(100);

            // gun rotation and scan precision
            // the spin
            turnGunRight(20);

            // back movement
            back(100);

            // better rotation
            turnGunRight(20);
        }
    }

    // Fire with power 2 - better damage
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
