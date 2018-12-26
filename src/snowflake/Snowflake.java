package snowflake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Snowflake extends JPanel implements ActionListener {

    public Timer timer = new Timer(20, this);
    public static JFrame jframe = new JFrame();
    public Dimension dim;
    ArrayList<Particle> particles = new ArrayList<Particle>();
    Particle p;
    public int ticks;
    public Point center;
    public int width, height;

    public Snowflake() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe.add(this);
        jframe.setVisible(true);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(dim.width / 2, dim.width / 2);
        width = dim.width / 2;
        height = dim.width / 2;
        jframe.setLocation(dim.width / 4, dim.height / 15);
        p = new Particle();
        ticks = 0;
        timer.start();
    }

    public static void main(String[] args) {
        Snowflake snowflake = new Snowflake();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.translate(width / 2, height / 2);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(-width, -height, width * 2, height * 2);
        g2d.setColor(Color.WHITE);
        g2d.rotate(Math.PI/6);
        for (int i = 0; i < 6; i++) {
            g2d.rotate(Math.PI / 3);
            for (Particle particle : particles) {
                g2d.fillOval(particle.pos.x, particle.pos.y, (int) particle.radius * 2, (int) particle.radius * 2);
                g2d.fillOval(particle.pos.x, -particle.pos.y, (int) particle.radius * 2, (int) particle.radius * 2);
            }
            g2d.fillOval(p.pos.x, p.pos.y, (int) p.radius * 2, (int) p.radius * 2);
            g2d.fillOval(p.pos.x, -p.pos.y, (int) p.radius * 2, (int) p.radius * 2);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();



        // if (p.intersect(particles) || p.finished()) {
        //     particles.add(p);
        //     p = new Particle();
        // }

        // p.update(); 

        while (!p.intersect(particles) && !p.finished()) {
            p.update();            
        }

        particles.add(p);
        p = new Particle();
    }

}