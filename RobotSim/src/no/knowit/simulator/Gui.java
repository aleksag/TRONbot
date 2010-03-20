package no.knowit.simulator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import no.knowit.robot.Robot;

public class Gui extends JPanel {

	private static final long serialVersionUID = 1321321L;
	private boolean running = true;
	Robot r = null;

	public Gui() {

	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("TRONBotSim");
		frame.setSize(1024, 768);
		frame.setBackground(Color.black);
		Gui gui = new Gui();
		frame.setContentPane(gui);
		frame.setVisible(true);
		try {
			gui.startSimulation();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	AiThread thread;

	private void startSimulation() throws InterruptedException {
		r = new Robot(0, 0);
		RobotAi ai = new RobotAi();
		this.thread = new AiThread(ai, r,this);
		this.thread.start();
	}

	public void paintComponent(Graphics g) {
		clear(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.green);
		if (r != null) {
			r.render(g);
		}
	}

	protected void clear(Graphics g) {
		super.paintComponent(g);
	}
}

class AiThread extends Thread {
	private RobotAi ai;
	private Robot r;
	public boolean isRunning = true;
	private Gui gui;
	public AiThread(RobotAi ai, Robot r,Gui gui) {
		this.ai = ai;
		this.r = r;
		this.gui = gui;
	}

	public void run() {
		while (isRunning) {
			ai.updateRobot(r);
			gui.repaint();
			System.out.println("TICK");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
