package no.knowit.simulator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Obstacle {
	Shape shape;
	Rectangle boundingBox;

	public void initRandom() {
		shape = makeRandomShape();
	}

	private Shape makeRandomShape() {
		int i = (int) (Math.random() * 100 % 4);
		switch (i) {
		case 0:
			Rectangle2D rect = new Rectangle2D.Double();
			rect.setRect(0, 0, Math.random()*100, Math.random()*100);
			return rect;
		case 1:
			RoundRectangle2D roundRect = new RoundRectangle2D.Float();
			roundRect.setRoundRect(0, 0, Math.random()*100, Math.random()*100, 10, 10);
			return roundRect;
		case 2:
			double width = Math.random()*100;
			Ellipse2D circle = new Ellipse2D.Double(0, 0, width, width);
			return circle;
		case 3:
			Polygon triangle = new Polygon();
			return triangle;
		default:
			break;
		}
		return null;
	}

	public void render(Graphics g){
		if(shape.getBounds().getX() == 0){
			double width = g.getClip().getBounds().getWidth();
			double height = g.getClip().getBounds().getHeight();
			if(shape instanceof Rectangle2D){
				Rectangle2D rect = (Rectangle2D)shape;
				rect.setRect(Math.random()*width, Math.random()*height, rect.getWidth(), rect.getHeight());
				shape = rect;
			}else if(shape instanceof RoundRectangle2D){
				RoundRectangle2D rect = (RoundRectangle2D)shape;
				rect.setRoundRect(Math.random()*width, Math.random()*width, rect.getWidth(), rect.getHeight(), 10, 10);
				shape = rect;
			}else if(shape instanceof Ellipse2D){
				Ellipse2D circle = (Ellipse2D)shape;
				circle = new Ellipse2D.Double(Math.random()*width,Math.round(Math.random()*height),circle.getWidth(),circle.getHeight());
				shape = circle; 
			}
		}
		((Graphics2D)g).fill(shape);
	}
}
