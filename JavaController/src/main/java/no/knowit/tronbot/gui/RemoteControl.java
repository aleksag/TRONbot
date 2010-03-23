package no.knowit.tronbot.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import no.knowit.tronbot.communication.MessageController;
import no.knowit.tronbot.communication.Payload;

public class RemoteControl {

	private JFrame frame;
	private MessageController controller;


	/**
	 * Create the application.
	 */
	public RemoteControl(MessageController messageController) {
		this.controller = messageController;
		initialize();
		frame.setVisible(true);
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
					controller.sendMessage(Payload.STOP);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						controller.sendMessage(Payload.LEFT);
					} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						controller.sendMessage(Payload.RIGHT);
					} else if (e.getKeyCode() == KeyEvent.VK_UP) {
						controller.sendMessage(Payload.FORWARD);
					} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						controller.sendMessage(Payload.BACKWARD);
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
					controller.sendMessage(Payload.STOP);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					controller.sendMessage(Payload.FORWARD);
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
					controller.sendMessage(Payload.STOP);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					controller.sendMessage(Payload.BACKWARD);
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
					controller.sendMessage(Payload.STOP);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					controller.sendMessage(Payload.LEFT);
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
					controller.sendMessage(Payload.STOP);
					controller.sendMessage(Payload.STOP);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					controller.sendMessage(Payload.RIGHT);
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
