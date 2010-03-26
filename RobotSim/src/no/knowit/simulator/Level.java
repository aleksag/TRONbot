package no.knowit.simulator;

import java.awt.Graphics;

import no.knowit.robot.Robot;

public class Level {
	Obstacle objects[];

	public Level(int numberOfObjects) {
		objects = new Obstacle[numberOfObjects];
		init();
	}

	private void init() {
		for (int i = 0; i < objects.length; i++) {
			objects[i] = new Obstacle();
			objects[i].initRandom();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < objects.length; i++) {
			objects[i].render(g);
		}
	}

	public boolean robotIntersects(Robot r) {
		for (int i = 0; i < objects.length; i++) {
			if (r.getRect() != null) {
				if (objects[i].shape.intersects(r.getRect())) {
					return true;
				}
			}
		}
		return false;
	}
}
