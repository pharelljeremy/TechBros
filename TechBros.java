import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;

public class TechBros extends Bot {

    // The main method starts our bot
    public static void main(String[] args) {
        new TechBros().start();
    }

    // When a new round is started -> initialize and movement
    @Override
    public void run() {
        //bot colour
        setBodyColor(Color.RED);
        setTurretColor(Color.BLACK);
        setRadarColor(new Color(173, 216, 230)); 
        setBulletColor(Color.RED);
        setScanColor(Color.BLACK);

        // Repeat while the bot is running
        while (isRunning()) {
            forward(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }

    // Spotted another bot - Shoot it down!
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        fire(1);
    }

    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate the bearing to the direction of the bullet
        double bearing = calcBearing(e.getBullet().getDirection());

        // Turn 90 degrees to the bullet direction based on the bearing
        turnLeft(90 - bearing);
    }
}
