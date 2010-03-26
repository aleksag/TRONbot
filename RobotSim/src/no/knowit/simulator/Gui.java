package no.knowit.simulator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import no.knowit.robot.Robot;
import no.knowit.robot.Sonar;

public class Gui extends JPanel {

	private static final long serialVersionUID = 1321321L;
	private boolean running = true;
	Robot r = null;
	Level l = null;
	public Gui() {
		
	}
	

	public static void main(String args[]) {
		
		final JFrame frame = new JFrame("TRONBotSim");
		frame.setSize(1024, 768);
		frame.setBackground(Color.black);
		final Gui gui = new Gui();
		gui.setBackground(Color.black);
		frame.setContentPane(gui);
		frame.setVisible(true);
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				gui.killThreads();
				frame.dispose();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
				gui.r.rotateLeft();
				}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					gui.r.rotateRight();	
				}
				
			}
		});
		try {
			gui.startSimulation();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	AiThread thread;

	private void startSimulation() throws InterruptedException {
		r = new Robot(100, 	100);
		l = new Level(25);
		r.setSonar(new Sonar(r,l));
		RobotAi ai = new RobotAi();
		this.thread = new AiThread(ai, r,this);
		this.thread.start();
	}
	public void killThreads() {
		this.thread.isRunning = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void paintComponent(Graphics g) {
		clear(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.green);
		if(l != null){
			l.render(g);
		}
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
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
