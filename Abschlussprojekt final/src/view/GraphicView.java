package view;

import model.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * GraphicView is an implementation of the View interface that displays the state of the World object
 * using graphical components.
 */
public class GraphicView extends JPanel implements View {

	private final int WIDTH;
	private final int HEIGHT;
	private Dimension fieldDimension;
	private World world;

	private BufferedImage playerImage;
	private BufferedImage backgroundImage;
	private BufferedImage wallImage;
	private BufferedImage startImage;
	private BufferedImage endImage;
	private BufferedImage winImage;
	private BufferedImage pursuerImage;

	/**
	 * Constructs a GraphicView with specified dimensions and field dimensions.
	 *
	 * @param width           the width of the graphical view.
	 * @param height          the height of the graphical view.
	 * @param fieldDimension  the dimension of each field in the grid.
	 * @param world           the World object to display.
	 */
	public GraphicView(int width, int height, Dimension fieldDimension, World world) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.fieldDimension = fieldDimension;
		this.bg = new Rectangle(WIDTH, HEIGHT);
		this.world = world;

		// Load images for various elements
		try {
			playerImage = ImageIO.read(getClass().getResource("playerImage.jpeg"));
			backgroundImage = ImageIO.read(getClass().getResource("backgroundImage.jpeg"));
			wallImage = ImageIO.read(getClass().getResource("wallImage.jpeg"));
			startImage = ImageIO.read(getClass().getResource("startImage-endImage.jpeg"));
			endImage = ImageIO.read(getClass().getResource("startImage-endImage.jpeg"));
			winImage = ImageIO.read(getClass().getResource("winImage.jpeg"));
			pursuerImage = ImageIO.read(getClass().getResource("pursuer.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private final Rectangle bg;
	private final Rectangle player = new Rectangle(1, 1);
	private final Rectangle pursuer1 = new Rectangle(1, 1);
	private final Rectangle pursuer2 = new Rectangle(1, 1);
	private final Rectangle pursuer3 = new Rectangle(1, 1);
	private final Rectangle pursuer4 = new Rectangle(1, 1);

	/**
	 * Paints the graphical representation of the World object on the JPanel.
	 *
	 * @param g the Graphics context in which to paint.
	 */
	@Override
	public void paint(Graphics g) {

		// Check if the game is in mode selection or gameplay mode
		if (!world.getMode1() && !world.getMode2() && !world.getMode3()) {
			// Display mode selection screen
			if (winImage != null) {
				g.drawImage(winImage, 0, 0, WIDTH, HEIGHT, null);
			} else {
				g.setColor(Color.WHITE);
				g.fillRect(bg.x, bg.y, bg.width, bg.height);
			}

			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Choose a difficulty!", 165, HEIGHT / 2 - 25);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Press 1 for Easy", 185, HEIGHT / 2 + 50);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Press 2 for Medium", 165, HEIGHT / 2 + 100);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Press 3 for Difficult", 165, HEIGHT / 2 + 150);
		} else {
			// Display gameplay screen

			// Draw background image or fill with white if image not available
			if (backgroundImage != null) {
				g.drawImage(backgroundImage, bg.x, bg.y, bg.width + 41, bg.height + 40, null);
			} else {
				g.setColor(Color.WHITE);
				g.fillRect(bg.x, bg.y, bg.width, bg.height);
			}

			boolean[][] walls = world.getWalls();

			// Draw walls
			for (int row = 0; row < world.getHeight(); row++) {
				for (int col = 0; col < world.getWidth(); col++) {
					if (walls[col][row]) {
						if (wallImage != null) {
							g.drawImage(wallImage, fieldDimension.width * col, fieldDimension.height * row, fieldDimension.width, fieldDimension.height, null);
						} else {
							g.setColor(Color.GRAY);
							g.fillRect(fieldDimension.width * col, fieldDimension.height * row, fieldDimension.width, fieldDimension.height);
						}
					}
				}
			}

			// Draw start and end points
			if (startImage != null) {
				g.drawImage(startImage, fieldDimension.width * world.getStartX(), fieldDimension.height * world.getStartY(), fieldDimension.width, fieldDimension.height, null);
			} else {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(fieldDimension.width * world.getStartX(), fieldDimension.height * world.getStartY(), fieldDimension.width, fieldDimension.height);
			}

			if (endImage != null) {
				g.drawImage(endImage, fieldDimension.width * world.getEndX(), fieldDimension.height * world.getEndY(), fieldDimension.width, fieldDimension.height, null);
			} else {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(fieldDimension.width * world.getEndX(), fieldDimension.height * world.getEndY(), fieldDimension.width, fieldDimension.height);
			}

			// Draw player and pursuers
			if (playerImage != null) {
				g.drawImage(playerImage, player.x, player.y, player.width, player.height, null);
			} else {
				g.setColor(Color.BLACK);
				g.fillRect(player.x, player.y, player.width, player.height);
			}

			if (pursuerImage != null) {
				g.drawImage(pursuerImage, pursuer1.x, pursuer1.y, pursuer1.width, pursuer1.height, null);
			} else {
				g.setColor(Color.RED);
				g.fillRect(pursuer1.x, pursuer1.y, pursuer1.width, pursuer1.height);
			}

			if (pursuerImage != null) {
				g.drawImage(pursuerImage, pursuer2.x, pursuer2.y, pursuer2.width, pursuer2.height, null);
			} else {
				g.setColor(Color.RED);
				g.fillRect(pursuer2.x, pursuer2.y, pursuer2.width, pursuer2.height);
			}

			if (!world.getMode1()) {
				if (pursuerImage != null) {
					g.drawImage(pursuerImage, pursuer3.x, pursuer3.y, pursuer3.width, pursuer3.height, null);
				} else {
					g.setColor(Color.RED);
					g.fillRect(pursuer3.x, pursuer3.y, pursuer3.width, pursuer3.height);
				}
			}

			if (world.getMode3()) {
				if (pursuerImage != null) {
					g.drawImage(pursuerImage, pursuer4.x, pursuer4.y, pursuer4.width, pursuer4.height, null);
				} else {
					g.setColor(Color.RED);
					g.fillRect(pursuer4.x, pursuer4.y, pursuer4.width, pursuer4.height);
				}
			}
		}

		// Display win message if the player has won
		if (world.getWin()) {
			if (winImage != null) {
				g.drawImage(winImage, 0, 0, WIDTH, HEIGHT, null);
			} else {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, WIDTH, HEIGHT);
			}

			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Congratulations, you have won!", 75, HEIGHT / 2 - 25);
		}

		// Display loss message if the player has lost
		if (world.getLost()) {
			if (winImage != null) {
				g.drawImage(winImage, 0, 0, WIDTH, HEIGHT, null);
			} else {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, WIDTH, HEIGHT);
			}

			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("Commiserations, you have lost!", 75, HEIGHT / 2 - 25);
		}
	}

	/**
	 * Updates the state of the GraphicView based on the updated World object.
	 * This method updates the positions of the player and pursuers on the graphical view
	 * and triggers a repaint of the view.
	 *
	 * @param world the World object containing the updated state.
	 */
	@Override
	public void update(World world) {

		// Update player position
		player.setSize(fieldDimension);
		player.setLocation((int) (world.getPlayerX() * fieldDimension.width), (int) (world.getPlayerY() * fieldDimension.height));

		// Update pursuer positions based on game mode
		if (world.getMode1()) {
			pursuer1.setSize(fieldDimension);
			pursuer1.setLocation((int) (world.getPursuer1X() * fieldDimension.width), (int) (world.getPursuer1Y() * fieldDimension.height));
			pursuer2.setSize(fieldDimension);
			pursuer2.setLocation((int) (world.getPursuer2X() * fieldDimension.width), (int) (world.getPursuer2Y() * fieldDimension.height));
		} else if (world.getMode2()) {
			pursuer1.setSize(fieldDimension);
			pursuer1.setLocation((int) (world.getPursuer1X() * fieldDimension.width), (int) (world.getPursuer1Y() * fieldDimension.height));
			pursuer2.setSize(fieldDimension);
			pursuer2.setLocation((int) (world.getPursuer2X() * fieldDimension.width), (int) (world.getPursuer2Y() * fieldDimension.height));
			pursuer3.setSize(fieldDimension);
			pursuer3.setLocation((int) (world.getPursuer3X() * fieldDimension.width), (int) (world.getPursuer3Y() * fieldDimension.height));
		} else if (world.getMode3()) {
			pursuer1.setSize(fieldDimension);
			pursuer1.setLocation((int) (world.getPursuer1X() * fieldDimension.width), (int) (world.getPursuer1Y() * fieldDimension.height));
			pursuer2.setSize(fieldDimension);
			pursuer2.setLocation((int) (world.getPursuer2X() * fieldDimension.width), (int) (world.getPursuer2Y() * fieldDimension.height));
			pursuer3.setSize(fieldDimension);
			pursuer3.setLocation((int) (world.getPursuer3X() * fieldDimension.width), (int) (world.getPursuer3Y() * fieldDimension.height));
			pursuer4.setSize(fieldDimension);
			pursuer4.setLocation((int) (world.getPursuer4X() * fieldDimension.width), (int) (world.getPursuer4Y() * fieldDimension.height));
		}

		// Trigger repaint of the graphical view
		repaint();
	}
}