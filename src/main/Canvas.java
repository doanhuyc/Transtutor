package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Seb Doan at https://github.com/doanhuyc on 4/4/2022
 */
public class Canvas extends JFrame {
	private static final Font FONT = new Font("Times Roman", Font.BOLD | Font.ITALIC, 22);

	Canvas() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel jPanel1 = new JPanel();
		add(jPanel1);

		JPanel jPanel2 = new JPanel();
		jPanel2.setPreferredSize(new Dimension(10, 100));
		add(jPanel2, BorderLayout.NORTH);

		jPanel1.setPreferredSize(new java.awt.Dimension(200, 200));
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - 400) / 2, (screenSize.height - 352) / 2, 300, 352);

		String str = "Java Java Java Java Java";
		String[] word = str.split(" ");
		JLabel[] serviceName = new JLabel[str.length()];
		String name;
		for (int j = 0; j < word.length; j++) {
			name = word[j];
			serviceName[j] = new JLabel(name) {
				protected void paintComponent(Graphics g) {

					Graphics2D g2 = (Graphics2D) g;
					g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g2.setColor(getRandomColor());
					AffineTransform aT = g2.getTransform();
					Shape oldshape = g2.getClip();
					aT.rotate(Math.PI / 2);
					g2.setTransform(aT);
					g2.setClip(oldshape);
					super.paintComponent(g);
				}
			};
			serviceName[j].setFont(FONT);
			serviceName[j].setForeground(getRandomColor());
			serviceName[j].setPreferredSize(new Dimension(100, 100));
			jPanel1.add(serviceName[j]);
		}
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> new Canvas().setVisible(true));
	}

	private int getRandomNumberFrom0To255() {
		return (int) Math.floor(Math.random() * (255 + 1));
	}

	private Color getRandomColor() {
		return new Color(getRandomNumberFrom0To255(), getRandomNumberFrom0To255(), getRandomNumberFrom0To255());
	}
}
