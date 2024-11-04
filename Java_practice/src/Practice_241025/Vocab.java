package Practice_241025;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class Vocab extends JFrame {
	

	private JLabel wordLabel1, wordLabel2, wordLabel3, wordLabel4;
	private boolean isWord1Clicked = false, isWord2Clicked = false, isWord3Clicked = false, isWord4Clicked = false;

	public Vocab() {
		setTitle("Vocabulary App");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2, 2));
		initUI();
	}

	private void initUI() {
		wordLabel1 = new JLabel("apple", SwingConstants.CENTER);
		wordLabel2 = new JLabel("house", SwingConstants.CENTER);
		wordLabel3 = new JLabel("banana", SwingConstants.CENTER);
		wordLabel4 = new JLabel("ice", SwingConstants.CENTER);

		wordLabel1.setFont(new Font("Malgun Gothic", Font.PLAIN, 24));
		wordLabel2.setFont(new Font("Malgun Gothic", Font.PLAIN, 24));
		wordLabel3.setFont(new Font("Malgun Gothic", Font.PLAIN, 24));
		wordLabel4.setFont(new Font("Malgun Gothic", Font.PLAIN, 24));

		wordLabel1.setOpaque(true);
		wordLabel2.setOpaque(true);
		wordLabel3.setOpaque(true);
		wordLabel4.setOpaque(true);

		wordLabel1.setBackground(Color.LIGHT_GRAY);
		wordLabel2.setBackground(Color.LIGHT_GRAY);
		wordLabel3.setBackground(Color.LIGHT_GRAY);
		wordLabel4.setBackground(Color.LIGHT_GRAY);

		wordLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				toggleWord1();
			}
		});

		wordLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				toggleWord2();
			}
		});
		wordLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				toggleWord3();
			}
		});
		wordLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				toggleWord4();
			}
		});

		add(wordLabel1);
		add(wordLabel2);
		add(wordLabel3);
		add(wordLabel4);
	}

	private void toggleWord1() {
		if (isWord1Clicked) {
			wordLabel1.setText("apple");
		} else {
			wordLabel1.setText("사과");
		}
		isWord1Clicked = !isWord1Clicked;
	}

	private void toggleWord2() {
		if (isWord2Clicked) {
			wordLabel2.setText("house");
		} else {
			wordLabel2.setText("집");
		}
		isWord2Clicked = !isWord2Clicked;
	}

	private void toggleWord3() {
		if (isWord3Clicked) {
			wordLabel3.setText("banana");
		} else {
			wordLabel3.setText("바나나");
		}
		isWord3Clicked = !isWord3Clicked;
	}

	private void toggleWord4() {
		if (isWord4Clicked) {
			wordLabel4.setText("ice");
		} else {
			wordLabel4.setText("얼음");
		}
		isWord4Clicked = !isWord4Clicked;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Vocab app = new Vocab();
			app.setVisible(true);
		});
	}
}
