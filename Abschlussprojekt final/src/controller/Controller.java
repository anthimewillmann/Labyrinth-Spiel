package controller;

import model.Direction;
import model.World;
import view.View;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

/**
 * The Controller class manages user input events (keyboard and mouse) and updates the World accordingly.
 */
public class Controller extends JFrame implements KeyListener, ActionListener, MouseListener {

	private World world; // The game world
	private List<View> views; // List of views to be updated

	/**
	 * Constructs a Controller object with a specified World instance.
	 *
	 * @param world the World object to control.
	 */
	public Controller(World world) {
		this.world = world;

		addKeyListener(this); // Register this class as a key listener
		addMouseListener(this); // Register this class as a mouse listener
	}

	// KeyListener methods

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used in this implementation
	}

	/**
	 * Handles key pressed events from the user.
	 *
	 * @param e the KeyEvent object containing the details of the key press.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				world.movePlayer(Direction.UP); // Move player up
				break;

			case KeyEvent.VK_DOWN:
				world.movePlayer(Direction.DOWN); // Move player down
				break;

			case KeyEvent.VK_LEFT:
				world.movePlayer(Direction.LEFT); // Move player left
				break;

			case KeyEvent.VK_RIGHT:
				world.movePlayer(Direction.RIGHT); // Move player right
				break;

			case KeyEvent.VK_ENTER:
				if (world.getWin() || world.getLost()) {
					world.restartGame(); // Restart game if win or lost

					// Reset game modes
					world.setMode1(false);
					world.setMode2(false);
					world.setMode3(false);
				} else {
					world.restartGame(); // Otherwise, just restart the game
				}
				break;

			case KeyEvent.VK_ESCAPE:
				System.exit(0); // Exit the game on ESC key press

			case KeyEvent.VK_1:
				world.setMode1(true); // Set game mode 1 (easy)
				break;

			case KeyEvent.VK_2:
				world.setMode2(true); // Set game mode 2 (medium)
				break;

			case KeyEvent.VK_3:
				world.setMode3(true); // Set game mode 3 (difficult)
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not used in this implementation
	}

	// ActionListener method

	@Override
	public void actionPerformed(ActionEvent e) {
		// Not used in this implementation
	}

	// MouseListener methods

	@Override
	public void mouseClicked(MouseEvent e) {
		// Not used in this implementation
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Not used in this implementation
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Not used in this implementation
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Not used in this implementation
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Not used in this implementation
	}
}