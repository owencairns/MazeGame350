package MazeGame2;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class StartScreen {
	Color c1 = Color.black;
	Color c2 = Color.cyan;

	JFrame frame = new JFrame();
	JButton playButton = new JButton("Play");
	String[] difficulty = { "Easy", "Medium", "Hard"};
    JComboBox<String> cd = new JComboBox<String>(difficulty);    
    String[] theme = {"black/cyan", "green/white", "yellow/black"};
    JComboBox<String> cs = new JComboBox<String>(theme);
}
