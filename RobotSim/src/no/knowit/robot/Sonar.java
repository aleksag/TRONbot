package no.knowit.robot;

import java.awt.Color;
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
	int sonarLength = 500;
	Line2D[] lines = new Line2D[180];
	public Sonar(Robot r, Level l){
		robot = r;
		level = l;
	}
	public void tick(){
		for(int i = 0; i < 180;i++){
			angle = i;
			Point p = new Point((int)robot.posx,(int)robot.posy);
			Point p2 = new Point(p.x+(int)Math.cos(i) * 100,p.y-(int)Math.sin(i) * 100);
			
			
			distances[angle] = (int) p.distance(p2);
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Point p = new Point((int)robot.posx,(int)robot.posy);
		Color oldColor = g2d.getColor();
		Color newColor = new Color (0, 50, 0);
		g2d.setColor(newColor);
		for(int i = 0; i < 180;i++){
			
			int deg = i-90;
			int posx = (int) (p.x+(Math.cos((Math.toRadians(deg))) * sonarLength));
			int posy = (int) (p.y-(Math.sin((-Math.toRadians(deg))) * sonarLength));
			g2d.drawLine(p.x, p.y, posx, posy);
		}
		g2d.setColor(oldColor);
	}
}
