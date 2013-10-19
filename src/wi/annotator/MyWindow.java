package wi.annotator;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MyWindow extends JFrame {

	
	private JButton jButton2;
	private JButton jButton0;
	private JButton jButton1;
	private JPanel jPanel0;

	public static void main(String[] args) {
		new MyWindow().setVisible(true);
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("jButton2");
		}
		return jButton2;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0");
		}
		return jButton0;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("jButton1");
		}
		return jButton1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GridLayout(0,1));
			jPanel0.add(getJButton2());
			jPanel0.add(getJButton0());
			jPanel0.add(getJButton1());
		}
		return jPanel0;
	}

	private void initComponents() {
		add(getJPanel0(), BorderLayout.CENTER);
		setSize(320, 240);
	}

	public MyWindow() {
		initComponents();
	}

}
