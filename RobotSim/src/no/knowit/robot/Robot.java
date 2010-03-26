package no.knowit.robot;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import no.knowit.simulator.Gui;

public class Robot {
	private double speed;
	double rotation;
	double posx;
	double posy;
	Rectangle2D rect;
	private Sonar sonar;
	int width = 40;
	int height = 40;

	public Robot(int x, int y) {
		posx = x;
		posy = y;
		speed = 0.5;
		rotation = Math.toRadians(0);
	}

	public void step() {
		//TODO: finn ut hvordan denne henger sammen med rotasjon av guiobjektet
		sonar.tick();
		posx += (Math.cos((-rotation)) * speed);
		posy -= (Math.sin((-rotation)) * speed);
		
	}

	public void step(int num) {
		for (int i = 0; i < num; i++) {
			step();
		}
	}

	private void rot(int dir) {
		rotation += Math.toRadians(dir);
	}

	public void rotateLeft() {
		rot(-1);
	}

	public void rotateLeft(int deg) {
		for (int i = 0; i < deg; i++) {
			rot(-1);
		}
	}

	public void rotateRight() {
		rot(1);
	}

	public void rotateRight(int deg) {
		for (int i = 0; i < deg; i++) {
			rot(1);
		}
	}

	public void setSonar(Sonar sonar) {
		this.sonar = sonar;
	}

	public Sonar getSonar() {
		return sonar;
	}

	public void render(Graphics g) {
		  Graphics2D g2d = (Graphics2D) g;
		  int x = (int)Math.floor(posx);
		  int y = (int)Math.floor(posy);
		  int wheelWidth = width/3;
		  int wheelHeight = height/6;
		  int offset = 3;
		  
		  g2d.rotate(this.rotation, x,y);
		  Rectangle2D rect = new Rectangle2D.Double(x-width/2, y-height/2, width, height);
		  this.rect = rect;
		  g2d.draw(rect);
		  g2d.drawRect((x+width/2)-wheelWidth-offset, (y+height/2), wheelWidth, wheelHeight);
		  g2d.drawRect((x-(width/2))+offset, (y+height/2), wheelWidth, wheelHeight);
		  g2d.drawRect((x+width/2)-wheelWidth-offset, (y-height/2)-wheelHeight, wheelWidth, wheelHeight);
		  g2d.drawRect((x-(width/2))+offset, (y-height/2)-wheelHeight, wheelWidth, wheelHeight);
		  sonar.render(g);
		  
		  
	}

	public Rectangle2D getRect() {
		return rect;
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
}
