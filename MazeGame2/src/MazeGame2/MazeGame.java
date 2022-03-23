package MazeGame2;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class MazeGame extends JFrame implements ActionListener{
	int sizeX = 10;
	int sizeY = 10;
	int cellSize = 20;
	Color c1 = Color.black;
	Color c2 = Color.cyan;

	JFrame frame = new JFrame();
	JButton playButton = new JButton("Play");
	String[] difficulty = { "Easy", "Medium", "Hard"};
    JComboBox<String> cd = new JComboBox<String>(difficulty);    
    String[] theme = {"black/cyan", "green/white", "yellow/black"};
    JComboBox<String> cs = new JComboBox<String>(theme);
	
	public MazeGame() {
		startScreen();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MazeGame mg = new MazeGame();
				mg.setVisible(true);
			}
		});
	}
	
	public void initUI() {
		
		Maze m1 = new Maze(sizeX,sizeY);
		setTitle("Maze Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(sizeX*cellSize + 200, sizeY*cellSize + 75);
		
		Display disp = new Display(m1, cellSize, c1, c2);
		add(disp);
		addKeyListener(disp);
		setContentPane(disp);
		disp.setFocusable(true);
		setLocationRelativeTo(null);
	}
	
	public void startScreen()  {
		
		JPanel panel = new JPanel();
	    panel.setLayout(null);
	    frame.add(panel);
	    panel.setBackground(Color.cyan);
	    JLabel diff = new JLabel("Select Difficulty");
	    diff.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel.add(diff);
	    JLabel theme = new JLabel("Select Theme");
	    theme.setAlignmentX(Component.CENTER_ALIGNMENT);
	    panel.add(theme);
	    
	    
	    cd.setBounds(200,270,200,40);
	    panel.add(cd);
	    cs.setBounds(200,330,200,40);
	    panel.add(cs);
	    
		playButton.setBounds(200,200,200,40);
		playButton.setFocusable(false);
		playButton.addActionListener(this);
		
		panel.setBackground(Color.cyan);
		panel.add(playButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		panel.setSize(600,600);
		frame.setLayout(null);
		frame.setVisible(true);
		
		cd.addActionListener(this);
		cs.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playButton) {
			initUI();
			frame.dispose();
		}
		if (e.getSource() == cd) {
			if (cd.getSelectedItem() == "Easy") {
				sizeX = 10;
				sizeY = 10;
			}
			else if (cd.getSelectedItem() == "Medium") {
				sizeX = 20;
				sizeY = 20;
			}
			else if (cd.getSelectedItem() == "Hard") {
				sizeX = 30;
				sizeY = 30;
			}
		}
		if (e.getSource() == cs) {
			if (cd.getSelectedItem() == "black/cyan") {
				c1 = Color.black;
				c2 = Color.cyan;
			}
			else if (cd.getSelectedItem() == "green/white") {
				c1 = Color.green;
				c2 = Color.white;
			}
			else if (cd.getSelectedItem() == "yellow/black") {
				c1 = Color.yellow;
				c2 = Color.black;
			}
		}
	}
}
