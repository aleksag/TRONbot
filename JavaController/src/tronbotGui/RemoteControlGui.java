package tronbotGui;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.midi.ControllerEventListener;
import javax.swing.JFrame;
import javax.swing.JButton;

import tronbotController.TronbotController;

public class RemoteControlGui {

	private JFrame frame;
	private static TronbotController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoteControlGui window = new RemoteControlGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			controller = new TronbotController("/dev/ttyUSB0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public RemoteControlGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFocusable(true);
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					controller.run(TronbotController.STOP);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == e.VK_LEFT) {
						controller.run(TronbotController.LEFT);
					} else if (e.getKeyCode() == e.VK_RIGHT) {
						controller.run(TronbotController.RIGHT);
					} else if (e.getKeyCode() == e.VK_UP) {
						controller.run(TronbotController.FORWARD);
					} else if (e.getKeyCode() == e.VK_DOWN) {
						controller.run(TronbotController.BACKWARD);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnForward = new JButton("Forward");

		btnForward.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					controller.run(TronbotController.STOP);
					controller.run(TronbotController.STOP);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					controller.run(TronbotController.FORWARD);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		btnForward.setBounds(180, 83, 85, 27);
		frame.getContentPane().add(btnForward);

		JButton btnBackward = new JButton("Backward");
		btnBackward.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					controller.run(TronbotController.STOP);
					controller.run(TronbotController.STOP);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					controller.run(TronbotController.BACKWARD);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		btnBackward.setBounds(180, 147, 85, 27);
		frame.getContentPane().add(btnBackward);

		JButton btnLeft = new JButton("Left");
		btnLeft.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					controller.run(TronbotController.STOP);
					controller.run(TronbotController.STOP);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					controller.run(TronbotController.LEFT);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		btnLeft.setBounds(90, 114, 85, 27);
		frame.getContentPane().add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnRight.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				try {
					controller.run(TronbotController.STOP);
					controller.run(TronbotController.STOP);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					controller.run(TronbotController.RIGHT);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		btnRight.setBounds(270, 114, 85, 27);
		frame.getContentPane().add(btnRight);
	}
}
