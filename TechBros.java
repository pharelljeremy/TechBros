import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;
import dev.robocode.tankroyale.botapi.graphics.Color;

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
        setRadarColor(Color.ORANGE); 
        setBulletColor(Color.YELLOW);
        setScanColor(Color.PURPLE);

        // Repeat while the bot is running (SPIN BOT)
        while (isRunning()) {
            // Tell the game that when we take move, we'll also want to turn right... a lot
            setTurnRight(10_000);
            // Limit our speed to 5
            setMaxSpeed(5);
            // Start moving (and turning)
            forward(10_000);
        }
    }

    // Spotted another bot - Shoot it down! (FIRE BOT)
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        fire(3);
    }

    // We hit another bot -> if it's our fault, we'll stop turning and moving,
    // so we need to turn again to keep spinning.
    @Override
    public void onHitBot(HitBotEvent e) {
        var direction = directionTo(e.getX(), e.getY());
        var bearing = calcBearing(direction);
        if (bearing > -10 && bearing < 10) {
            fire(4);
        }
        if (e.isRammed()) {
            turnRight(10);
        }
    }

    /* We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate the bearing to the direction of the bullet
        double bearing = calcBearing(e.getBullet().getDirection());

        // Turn 90 degrees to the bullet direction based on the bearing
        turnLeft(90 - bearing);
		// Turn Rate (VELOCITY BOT)
		setTurnRate(5);
    }
	*/
}
