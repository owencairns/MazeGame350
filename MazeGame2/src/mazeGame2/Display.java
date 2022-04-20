package mazeGame2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.*;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

/**
 * The Display Class creates the main display for the maze game. It take the
 * generated maze (Maze.java) and implements it visually The maze is formatted
 * based on size and takes user input
 * 
 * @author Owen, Kren, Alex
 */
@SuppressWarnings("serial")
public class Display extends JPanel implements KeyListener {
	Maze m1;
	int offsetX = 10;
	int offsetY = 10;
	int cellSize = 20;
	Color c1 = Color.black;
	Color c2 = Color.cyan;
	int stroke = 5;

	Integer moveCounter = 0;

	int pointX = 0;
	int pointY = 0;
	int oldX = 0;
	int oldY = 0;

	boolean erase;

	/*
	 * Class Constructor
	 */
	public Display() {
		m1 = new Maze();
		pointX = offsetX + cellSize / 2;
		pointY = offsetY + cellSize / 2;
		oldX = pointX;
		oldY = pointY;
		addKeyListener(this);
	}

	/*
	 * Class Constructor which takes Maze object as input
	 */
	public Display(Maze m2) {
		m1 = m2;
		pointX = offsetX + cellSize / 2;
		pointY = offsetY + cellSize / 2;
		oldX = pointX;
		oldY = pointY;
		addKeyListener(this);
	}

	/*
	 * Class Constructor which takes Maze object, cellSize
	 */
	public Display(Maze m2, int cellSize2) {
		m1 = m2;
		cellSize = cellSize2;
		pointX = offsetX + cellSize / 2;
		pointY = offsetY + cellSize / 2;
		oldX = pointX;
		oldY = pointY;
		addKeyListener(this);
	}

	/*
	 * Class Constructor which takes Maze object, cellSize, color1 and color2
	 */
	public Display(Maze m2, int cellSize2, Color color1, Color color2) {
		m1 = m2;
		cellSize = cellSize2;
		c1 = color1;
		c2 = color2;
		pointX = offsetX + cellSize / 2;
		pointY = offsetY + cellSize / 2;
		oldX = pointX;
		oldY = pointY;
		addKeyListener(this);
	}

	/*
	 * Class to draw the maze
	 * 
	 * @param Graphics g base doDrawing class for GUI
	 */
	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(c1);

		Dimension size = getSize();
		Insets insets = getInsets();

		int w = size.width - insets.left - insets.right;
		int h = size.height - insets.top - insets.bottom;

		g2d.setBackground(c2);
		g2d.clearRect(0, 0, w, h);

		g.setColor(Color.white);
		g.fillRect(offsetX, offsetY, cellSize, cellSize);

        g.setColor(Color.white);
		g.fillRect(m1.sizeX * cellSize - offsetX, m1.sizeY * cellSize - offsetY, cellSize, cellSize);

		g.setColor(c1);

		Path2D mazeShape = new Path2D.Double();

		int x;
		int y;

		/*
		 * Loops through the Maze object and draws lines and sets cells to give visual
		 * representation
		 */
		for (Integer i = 0; i < m1.sizeX; i++) {
			x = i * cellSize + offsetX;
			for (Integer j = 0; j < m1.sizeY; j++) {
				y = j * cellSize + offsetY;

				g2d.setStroke(new BasicStroke(stroke));

				if (m1.cells[i][j].walls[0] == 1) {
					mazeShape.moveTo(x, y);
					mazeShape.lineTo(x + cellSize, y);
					g2d.drawLine(x, y, x + cellSize, y);
				}
				if (m1.cells[i][j].walls[1] == 1) {
					mazeShape.moveTo(x + cellSize, y);
					mazeShape.lineTo(x + cellSize, y + cellSize);
					g2d.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
				}
				if (m1.cells[i][j].walls[2] == 1) {
					mazeShape.moveTo(x, y + cellSize);
					mazeShape.lineTo(x + cellSize, y + cellSize);
					g2d.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
				}
				if (m1.cells[i][j].walls[3] == 1) {
					mazeShape.moveTo(x, y);
					mazeShape.lineTo(x, y + cellSize);
					g2d.drawLine(x, y, x, y + cellSize);
				}
			}
		}

		/*
		 * handles the movement of the player against a wall also handles the movement
		 * count
		 */
		x = (oldX - offsetX - cellSize / 2) / cellSize;
		y = (oldY - offsetY - cellSize / 2) / cellSize;

		if (x >= 0 && x < m1.sizeX && oldX > pointX && m1.cells[x][y].walls[3] == 1) {
			pointX = oldX;
			pointY = oldY;
		} else if (x >= 0 && x < m1.sizeX && oldX < pointX && m1.cells[x][y].walls[1] == 1) {
			pointX = oldX;
			pointY = oldY;
		} else if (y >= 0 && y < m1.sizeY && oldY > pointY && m1.cells[x][y].walls[0] == 1) {
			pointX = oldX;
			pointY = oldY;
		} else if (y >= 0 && y < m1.sizeY && oldY < pointY && m1.cells[x][y].walls[2] == 1) {
			pointX = oldX;
			pointY = oldY;
		}

		if (pointX != oldX || pointY != oldY) {
			moveCounter++;
		}

		/*
		 * 
		 * On screen text which gives user instructions
		 * 
		 */
		Font myFont = new Font("Courier New", 1, 14);
		g.setFont(myFont);
		g2d.drawString("Moves: " + moveCounter.toString(), m1.sizeX * cellSize + offsetX + 20, 20);
		g2d.drawString("To Move: ", m1.sizeX * cellSize + offsetX + 20, 60);
		g2d.drawString("Use Arrow Keys", m1.sizeX * cellSize + offsetX + 20, 78);
		g2d.drawString("or WASD", m1.sizeX * cellSize + offsetX + 20, 90);
		g2d.drawString("Press 'Esc' ", m1.sizeX * cellSize + offsetX + 20, 132);
		g2d.drawString("to quit", m1.sizeX * cellSize + offsetX + 20, 144);

		if (y == m1.sizeY - 1 && x == m1.sizeX - 1) {
			System.out.println("Winner!");
			g2d.drawString("You Won!", m1.sizeX * cellSize + offsetX + 20, 184);
		}

		g.setColor(Color.black);
		g.fillRect(pointX - offsetX / 3, pointY - offsetY / 3, cellSize / 3, cellSize / 3);

	}

	/*
	 * @Override for paintComponent element of JPanel
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	/*
	 * 
	 * Handles the player movement based on key pressed
	 * 
	 * @param KeyEvent key reads the input from user keyboard
	 * 
	 */
	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent key) {
		oldX = pointX;
		oldY = pointY;

		if (key.getKeyChar() == 's' || key.getKeyCode() == key.VK_DOWN) {
			pointY = pointY + cellSize;
			if (pointY > getBounds().height) {
				pointY = getBounds().height;
			}
		}
		if (key.getKeyChar() == 'w' || key.getKeyCode() == key.VK_UP) {
			pointY = pointY - cellSize;
			if (pointY < 0) {
				pointY = 0;
			}
		}
		if (key.getKeyChar() == 'a' || key.getKeyCode() == key.VK_LEFT) {
			pointX = pointX - cellSize;
			if (pointX < 0) {
				pointX = 0;
			}
		}
		if (key.getKeyChar() == 'd' || key.getKeyCode() == key.VK_RIGHT) {
			pointX = pointX + cellSize;
			if (pointX > getBounds().width) {
				pointX = getBounds().width;
			}
		}
		if (key.getKeyCode() == key.VK_ESCAPE) {
			System.exit(0);
		}

		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
