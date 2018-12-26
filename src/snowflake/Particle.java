package snowflake;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Particle {
    Point pos;
    float radius;
    Random r;
    
    public Particle() {
        r = new Random();
        // pos = new Point(Snowflake.jframe.getContentPane().getWidth(), Snowflake.jframe.getContentPane().getHeight() / 2);
        pos = new Point(Snowflake.jframe.getContentPane().getWidth() / 2, 0);
        radius = 3;
    }

    public void update() {
        pos.x--;
        int randomInt = r.nextInt(11) - 5;
        pos.y += randomInt;
        if (pos.y > (int)(Math.tan(Math.PI / 3) * pos.x)) {
            pos.y = (int) (Math.tan(Math.PI / 3) * pos.x);
        }
        if (pos.y < -(int) (Math.tan(Math.PI / 3) * pos.x)) {
            pos.y = -(int) (Math.tan(Math.PI / 3) * pos.x);
        }
    }

    public boolean intersect(ArrayList<Particle> ap) {
        double distance;
        for (Particle p : ap) {
            distance = Math.hypot(p.pos.x + radius - this.pos.x - radius, p.pos.y + radius - this.pos.y - radius);
            if (distance < radius * 2) {
                return true;
            }
        }
        return false;
    }

    public boolean finished() {
        return pos.x < 1;
    }
}