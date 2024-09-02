package view;

import model.World;

/**
 * ConsoleView is an implementation of the View interface that displays the state of the World object
 * in a textual console format.
 */
public class ConsoleView implements View {

	/**
	 * Updates the console output to reflect the current state of the World object.
	 * This method prints a textual representation of the World's state to the console,
	 * including player position, pursuers' positions, start and end points, walls, and
	 * game outcome messages.
	 *
	 * @param world the World object containing the updated state.
	 */
	@Override
	public void update(World world) {

		// Extracting player and pursuers positions
		int playerX = world.getPlayerX();
		int playerY = world.getPlayerY();
		int startX = world.getStartX();
		int startY = world.getStartY();
		int endX = world.getEndX();
		int endY = world.getEndY();
		int pursuer1X = world.getPursuer1X();
		int pursuer1Y = world.getPursuer1Y();
		int pursuer2X = world.getPursuer2X();
		int pursuer2Y = world.getPursuer2Y();
		int pursuer3X = world.getPursuer3X();
		int pursuer3Y = world.getPursuer3Y();
		int pursuer4X = world.getPursuer4X();
		int pursuer4Y = world.getPursuer4Y();

		// Getting walls information
		boolean[][] walls = world.getWalls();

		// Iterating through each cell in the world grid
		for (int row = 0; row < world.getHeight(); row++) {
			for (int col = 0; col < world.getWidth(); col++) {

				// Checking different modes to display appropriate characters
				if (world.getMode1()) {
					if (row == pursuer1Y && col == pursuer1X) {
						System.out.print("*"); // Print pursuer 1 symbol
					} else if (row == pursuer2Y && col == pursuer2X) {
						System.out.print("*"); // Print pursuer 2 symbol
					} else if (row == playerY && col == playerX) {
						System.out.print("#"); // Print player symbol
					} else if (row == startY && col == startX) {
						System.out.print("S"); // Print start symbol
					} else if (row == endY && col == endX) {
						System.out.print("E"); // Print end symbol
					} else if (walls[col][row]) {
						System.out.print("+"); // Print wall symbol
					} else {
						System.out.print("."); // Print empty space symbol
					}
				} else if (world.getMode2()) {
					if (row == pursuer1Y && col == pursuer1X) {
						System.out.print("*"); // Print pursuer 1 symbol
					} else if (row == pursuer2Y && col == pursuer2X) {
						System.out.print("*"); // Print pursuer 2 symbol
					} else if (row == pursuer3Y && col == pursuer3X) {
						System.out.print("*"); // Print pursuer 3 symbol
					} else if (row == playerY && col == playerX) {
						System.out.print("#"); // Print player symbol
					} else if (row == startY && col == startX) {
						System.out.print("S"); // Print start symbol
					} else if (row == endY && col == endX) {
						System.out.print("E"); // Print end symbol
					} else if (walls[col][row]) {
						System.out.print("+"); // Print wall symbol
					} else {
						System.out.print("."); // Print empty space symbol
					}
				} else if (world.getMode3()) {
					if (row == pursuer1Y && col == pursuer1X) {
						System.out.print("*"); // Print pursuer 1 symbol
					} else if (row == pursuer2Y && col == pursuer2X) {
						System.out.print("*"); // Print pursuer 2 symbol
					} else if (row == pursuer3Y && col == pursuer3X) {
						System.out.print("*"); // Print pursuer 3 symbol
					} else if (row == pursuer4Y && col == pursuer4X) {
						System.out.print("*"); // Print pursuer 4 symbol
					} else if (row == playerY && col == playerX) {
						System.out.print("#"); // Print player symbol
					} else if (row == startY && col == startX) {
						System.out.print("S"); // Print start symbol
					} else if (row == endY && col == endX) {
						System.out.print("E"); // Print end symbol
					} else if (walls[col][row]) {
						System.out.print("+"); // Print wall symbol
					} else {
						System.out.print("."); // Print empty space symbol
					}
				}
			}

			System.out.println(); // Move to the next line after printing each row
		}

		System.out.println(); // Print an additional blank line for separation

		// Displaying game outcome messages if the game has been won or lost
		if (world.getWin()) {
			System.out.println("Congratulations, you have won! Press ENTER to restart and ESC to leave");
		}

		if (world.getLost()) {
			System.out.println("Commiserations, you have lost! Press ENTER to restart and ESC to leave");
		}
	}
}