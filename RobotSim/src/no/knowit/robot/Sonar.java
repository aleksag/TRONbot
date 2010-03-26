package no.knowit.robot;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

import no.knowit.simulator.Level;

public class Sonar {
	int angle;
	int distance;
	Robot robot;
	Level level;
	int[] distances = new int[180];
	Line2D[] lines = new Line2D[180];
	public Sonar(Robot r, Level l){
		robot = r;
		level = l;
	}
	public void tick(){
		for(int i = 0; i < 180;i++){
			angle = i;
			Point p = new Point((int)robot.posx,(int)robot.posy);
			int a = (int) this.angle;
			Point p2 = new Point(p.x+(int)Math.cos(a) * 100,p.y-(int)Math.sin(a) * 100);
			lines[angle] = new Line2D.Double(p, p2);
			distances[angle] = (int) p.distance(p2);
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0; i < lines.length;i++){
			Line2D line =lines[i];
			
			g2d.drawLine((int)line.getX1(), (int)line.getY1(), (int)line.getX2(), (int)line.getY2());
		}
	}
}
