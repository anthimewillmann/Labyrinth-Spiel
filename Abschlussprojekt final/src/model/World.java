package model;

import view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game world with players, pursuers, walls, and game state management.
 * Handles movement, game rules, and interaction with views.
 */
public class World {
	// Game state flags
	private boolean win = false;
	private boolean lost = false;
	private boolean mode1 = false;
	private boolean mode2 = false;
	private boolean mode3 = false;

	// Dimensions of the world grid
	private final int width;
	private final int height;

	// Player coordinates
	private int playerX = 0;
	private int playerY = 7;

	// Starting and ending coordinates
	private final int startX = 0;
	private final int startY = 7;
	private final int endX = 14;
	private final int endY = 7;

	// Pursuer coordinates
	private int pursuer1X;
	private int pursuer1Y;
	private int pursuer2X;
	private int pursuer2Y;
	private int pursuer3X;
	private int pursuer3Y;
	private int pursuer4X;
	private int pursuer4Y;

	// List of views observing the world
	private final ArrayList<View> views = new ArrayList<>();

	// 2D array indicating presence of walls
	private boolean[][] walls;

	// Flags for pursuers to move randomly or with calculated path
	private boolean pursuer1UseRandomMove = true;
	private boolean pursuer2UseRandomMove = true;
	private boolean pursuer3UseRandomMove = true;

	// Index counters for modes
	private int indexEasy = 2;
	private int indexMedium = 1;

	/**
	 * Constructs the game world with specified dimensions.
	 *
	 * @param width  Width of the world grid.
	 * @param height Height of the world grid.
	 */
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.walls = new boolean[width][height];

		// Initializes the wall positions in the world grid.
		walls[1][1] = true;
		walls[2][1] = true;
		walls[3][1] = true;
		walls[3][2] = true;
		walls[1][2] = true;
		walls[1][3] = true;
		walls[1][4] = true;
		walls[1][5] = true;
		walls[1][6] = true;
		walls[5][0] = true;
		walls[6][0] = true;
		walls[5][1] = true;
		walls[5][2] = true;
		walls[3][4] = true;
		walls[4][4] = true;
		walls[5][4] = true;
		walls[6][4] = true;
		walls[7][4] = true;
		walls[8][4] = true;
		walls[0][8] = true;
		walls[1][8] = true;
		walls[2][8] = true;
		walls[3][8] = true;
		walls[3][7] = true;
		walls[3][6] = true;
		walls[8][0] = true;
		walls[8][1] = true;
		walls[8][2] = true;
		walls[7][2] = true;
		walls[10][2] = true;
		walls[10][1] = true;
		walls[10][3] = true;
		walls[10][4] = true;
		walls[10][5] = true;
		walls[10][6] = true;
		walls[9][6] = true;
		walls[11][1] = true;
		walls[1][10] = true;
		walls[1][11] = true;
		walls[1][12] = true;
		walls[1][13] = true;
		walls[2][10] = true;
		walls[3][10] = true;
		walls[4][10] = true;
		walls[5][10] = true;
		walls[6][10] = true;
		walls[7][10] = true;
		walls[7][11] = true;
		walls[13][12] = true;
		walls[14][12] = true;
		walls[13][13] = true;
		walls[11][11] = true;
		walls[11][12] = true;
		walls[11][13] = true;
		walls[11][14] = true;
		walls[9][12] = true;
		walls[9][13] = true;
		walls[8][13] = true;
		walls[7][13] = true;
		walls[6][13] = true;
		walls[5][13] = true;
		walls[5][12] = true;
		walls[4][12] = true;
		walls[3][12] = true;
		walls[3][13] = true;
		walls[5][6] = true;
		walls[5][7] = true;
		walls[5][8] = true;
		walls[5][9] = true;
		walls[6][6] = true;
		walls[7][6] = true;
		walls[7][7] = true;
		walls[7][8] = true;
		walls[8][8] = true;
		walls[9][8] = true;
		walls[10][8] = true;
		walls[9][9] = true;
		walls[9][10] = true;
		walls[10][9] = true;
		walls[11][9] = true;
		walls[12][9] = true;
		walls[13][9] = true;
		walls[13][10] = true;
		walls[12][3] = true;
		walls[12][5] = true;
		walls[12][6] = true;
		walls[12][7] = true;
		walls[12][8] = true;
		walls[13][5] = true;
		walls[13][3] = true;
		walls[14][3] = true;
		walls[13][2] = true;
		walls[13][1] = true;
		walls[0][2] = true;
	}

	///////////////////////////////////////////////////////////////////////////// Getters and Setters

	/**
	 * Returns the width of the world grid.
	 *
	 * @return Width of the world.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the world grid.
	 *
	 * @return Height of the world.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the current X coordinate of the player.
	 *
	 * @return X coordinate of the player.
	 */
	public int getPlayerX() {
		return playerX;
	}

	/**
	 * Sets the X coordinate of the player and ensures it stays within bounds.
	 *
	 * @param playerX New X coordinate of the player.
	 */
	public void setPlayerX(int playerX) {
		playerX = Math.max(0, playerX);
		playerX = Math.min(getWidth() - 1, playerX);
		this.playerX = playerX;

//		// Check game win and lost conditions
		checkGameWin();
		checkGameLost();
	}

	/**
	 * Returns the current Y coordinate of the player.
	 *
	 * @return Y coordinate of the player.
	 */
	public int getPlayerY() {
		return playerY;
	}

	/**
	 * Sets the Y coordinate of the player and ensures it stays within bounds.
	 *
	 * @param playerY New Y coordinate of the player.
	 */
	public void setPlayerY(int playerY) {
		playerY = Math.max(0, playerY);
		playerY = Math.min(getHeight() - 1, playerY);
		this.playerY = playerY;

//		// Check game win and lost conditions
		checkGameWin();
		checkGameLost();
	}

	/**
	 * Returns the starting X coordinate.
	 *
	 * @return Starting X coordinate.
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * Returns the starting Y coordinate.
	 *
	 * @return Starting Y coordinate.
	 */
	public int getStartY() {
		return startY;
	}

	/**
	 * Returns the ending X coordinate.
	 *
	 * @return Ending X coordinate.
	 */
	public int getEndX() {
		return endX;
	}

	/**
	 * Returns the ending Y coordinate.
	 *
	 * @return Ending Y coordinate.
	 */
	public int getEndY() {
		return endY;
	}

	/**
	 * Returns the 2D array representing the positions of walls.
	 *
	 * @return 2D array of boolean indicating wall positions.
	 */
	public boolean[][] getWalls() {
		return walls;
	}

	/**
	 * Returns the X coordinate of pursuer 1.
	 *
	 * @return X coordinate of pursuer 1.
	 */
	public int getPursuer1X() {
		return pursuer1X;
	}

	/**
	 * Sets the X coordinate of pursuer 1 and ensures it stays within bounds.
	 *
	 * @param pursuer1X New X coordinate of pursuer 1.
	 */
	public void setPursuer1X(int pursuer1X) {
		pursuer1X = Math.max(0, pursuer1X);
		pursuer1X = Math.min(getWidth() - 1, pursuer1X);
		this.pursuer1X = pursuer1X;
	}

	/**
	 * Returns the Y coordinate of pursuer 1.
	 *
	 * @return Y coordinate of pursuer 1.
	 */
	public int getPursuer1Y() {
		return pursuer1Y;
	}

	/**
	 * Sets the Y coordinate of pursuer 1 and ensures it stays within bounds.
	 *
	 * @param pursuer1Y New Y coordinate of pursuer 1.
	 */
	public void setPursuer1Y(int pursuer1Y) {
		pursuer1Y = Math.max(0, pursuer1Y);
		pursuer1Y = Math.min(getHeight() - 1, pursuer1Y);
		this.pursuer1Y = pursuer1Y;
	}

	/**
	 * Returns the X coordinate of pursuer 2.
	 *
	 * @return X coordinate of pursuer 2.
	 */
	public int getPursuer2X() {
		return pursuer2X;
	}

	/**
	 * Sets the X coordinate of pursuer 2 and ensures it stays within bounds.
	 *
	 * @param pursuer2X New X coordinate of pursuer 2.
	 */
	public void setPursuer2X(int pursuer2X) {
		pursuer2X = Math.max(0, pursuer2X);
		pursuer2X = Math.min(getWidth() - 1, pursuer2X);
		this.pursuer2X = pursuer2X;
	}

	/**
	 * Returns the Y coordinate of pursuer 2.
	 *
	 * @return Y coordinate of pursuer 2.
	 */
	public int getPursuer2Y() {
		return pursuer2Y;
	}

	/**
	 * Sets the Y coordinate of pursuer 2 and ensures it stays within bounds.
	 *
	 * @param pursuer2Y New Y coordinate of pursuer 2.
	 */
	public void setPursuer2Y(int pursuer2Y) {
		pursuer2Y = Math.max(0, pursuer2Y);
		pursuer2Y = Math.min(getHeight() - 1, pursuer2Y);
		this.pursuer2Y = pursuer2Y;
	}

	/**
	 * Returns the X coordinate of pursuer 3.
	 *
	 * @return X coordinate of pursuer 3.
	 */
	public int getPursuer3X() {
		return pursuer3X;
	}

	/**
	 * Sets the X coordinate of pursuer 3 and ensures it stays within bounds.
	 *
	 * @param pursuer3X New X coordinate of pursuer 3.
	 */
	public void setPursuer3X(int pursuer3X) {
		pursuer3X = Math.max(0, pursuer3X);
		pursuer3X = Math.min(getWidth() - 1, pursuer3X);
		this.pursuer3X = pursuer3X;
	}

	/**
	 * Returns the Y coordinate of pursuer 3.
	 *
	 * @return Y coordinate of pursuer 3.
	 */
	public int getPursuer3Y() {
		return pursuer3Y;
	}

	/**
	 * Sets the Y coordinate of pursuer 3 and ensures it stays within bounds.
	 *
	 * @param pursuer3Y New Y coordinate of pursuer 3.
	 */
	public void setPursuer3Y(int pursuer3Y) {
		pursuer3Y = Math.max(0, pursuer3Y);
		pursuer3Y = Math.min(getHeight() - 1, pursuer3Y);
		this.pursuer3Y = pursuer3Y;
	}

	/**
	 * Returns the X coordinate of pursuer 4.
	 *
	 * @return X coordinate of pursuer 4.
	 */
	public int getPursuer4X() {
		return pursuer4X;
	}

	/**
	 * Sets the X coordinate of pursuer 4 and ensures it stays within bounds.
	 *
	 * @param pursuer4X New X coordinate of pursuer 4.
	 */
	public void setPursuer4X(int pursuer4X) {
		pursuer4X = Math.max(0, pursuer4X);
		pursuer4X = Math.min(getWidth() - 1, pursuer4X);
		this.pursuer4X = pursuer4X;
	}

	/**
	 * Returns the Y coordinate of pursuer 4.
	 *
	 * @return Y coordinate of pursuer 4.
	 */
	public int getPursuer4Y() {
		return pursuer4Y;
	}

	/**
	 * Sets the Y coordinate of pursuer 4 and ensures it stays within bounds.
	 *
	 * @param pursuer4Y New Y coordinate of pursuer 4.
	 */
	public void setPursuer4Y(int pursuer4Y) {
		pursuer4Y = Math.max(0, pursuer4Y);
		pursuer4Y = Math.min(getHeight() - 1, pursuer4Y);
		this.pursuer4Y = pursuer4Y;
	}

	/**
	 * Returns whether the player has won the game.
	 *
	 * @return true if the player has reached the end position, otherwise false
	 */
	public boolean getWin() {
		return win;
	}

	/**
	 * Returns whether the player has lost the game.
	 *
	 * @return true if the player has collided with any pursuer, otherwise false
	 */
	public boolean getLost() {
		return lost;
	}

	/**
	 * Resets the game state to the beginning:
	 * - Resets the player's position to the starting point.
	 * - Resets pursuers' positions based on the current game mode.
	 * - Resets win and lost flags to false.
	 * - Updates all registered views with the new game state.
	 */
	public void restartGame() {
		playerX = startX;
		playerY = startY;

		// Reset pursuer positions based on the selected mode
		if (getMode1()) {
			setPursuer1X(0);
			setPursuer1Y(4);

			setPursuer2X(4);
			setPursuer2Y(3);
		}

		if (getMode2()) {
			setPursuer1X(0);
			setPursuer1Y(3);

			setPursuer2X(4);
			setPursuer2Y(2);

			setPursuer3X(8);
			setPursuer3Y(7);
		}

		if (getMode3()) {
			setPursuer1X(0);
			setPursuer1Y(1);

			setPursuer2X(14);
			setPursuer2Y(2);

			setPursuer3X(4);
			setPursuer3Y(13);

			setPursuer4X(6);
			setPursuer4Y(7);
		}

		// Reset game outcome flags
		win = false;
		lost = false;

		// Update all views with the new game state
		updateViews();
	}

	/**
	 * Checks if the game win condition is met (player reaches the end).
	 */
	public void checkGameWin() {
		if (playerX == endX && playerY == endY) {
			win = true;
		}
	}

	/**
	 * Checks if the game lost condition is met (player is caught by pursuers).
	 */
	public void checkGameLost() {
		if ((playerX == pursuer1X && playerY == pursuer1Y) || (playerX == pursuer2X && playerY == pursuer2Y) || (playerX == pursuer3X && playerY == pursuer3Y) || (playerX == pursuer4X && playerY == pursuer4Y)) {
			lost = true;
		}
	}

	///////////////////////////////////////////////////////////////////////////// Difficulty Management

	/**
	 * Returns whether the game is in mode 1 (easy mode).
	 *
	 * @return true if in mode 1, otherwise false
	 */
	public boolean getMode1() {
		return mode1;
	}

	/**
	 * Sets the game mode to mode 1 (easy mode).
	 * Calls {@link #easyMode()} and updates all registered views.
	 *
	 * @param value true to set mode 1, false otherwise
	 */
	public void setMode1(boolean value) {
		mode1 = value;
		easyMode();
		updateViews();
	}

	/**
	 * Returns whether the game is in mode 2 (medium mode).
	 *
	 * @return true if in mode 2, otherwise false
	 */
	public boolean getMode2() {
		return mode2;
	}

	/**
	 * Sets the game mode to mode 2 (medium mode).
	 * Calls {@link #mediumMode()} and updates all registered views.
	 *
	 * @param value true to set mode 2, false otherwise
	 */
	public void setMode2(boolean value) {
		mode2 = value;
		mediumMode();
		updateViews();
	}

	/**
	 * Returns whether the game is in mode 3 (difficult mode).
	 *
	 * @return true if in mode 3, otherwise false
	 */
	public boolean getMode3() {
		return mode3;
	}

	/**
	 * Sets the game mode to mode 3 (difficult mode).
	 * Calls {@link #difficultMode()} and updates all registered views.
	 *
	 * @param value true to set mode 3, false otherwise
	 */
	public void setMode3(boolean value) {
		mode3 = value;
		difficultMode();
		updateViews();
	}

	/**
	 * Sets pursuers' positions for easy mode.
	 */
	public void easyMode() {
		setPursuer1X(0);
		setPursuer1Y(4);

		setPursuer2X(4);
		setPursuer2Y(3);
	}

	/**
	 * Sets pursuers' positions for medium mode.
	 */
	public void mediumMode() {
		setPursuer1X(0);
		setPursuer1Y(3);

		setPursuer2X(4);
		setPursuer2Y(2);

		setPursuer3X(8);
		setPursuer3Y(7);
	}

	/**
	 * Sets pursuers' positions for difficult mode.
	 */
	public void difficultMode() {
		setPursuer1X(0);
		setPursuer1Y(1);

		setPursuer2X(14);
		setPursuer2Y(2);

		setPursuer3X(4);
		setPursuer3Y(13);

		setPursuer4X(6);
		setPursuer4Y(7);
	}

	///////////////////////////////////////////////////////////////////////////// Player Management

	/**
	 * Moves the player in the specified direction if the game is active and the move is valid.
	 * Handles pursuers' movements based on the current game mode.
	 * Updates all registered views.
	 *
	 * @param direction the direction in which to move the player
	 */
	public void movePlayer(Direction direction) {

		if (win || lost) {
			updateViews();
			return;
		}

		if (!getMode1() && !getMode2() && !getMode3()) {
			return;
		}

		if (!walls[getPlayerX() + direction.deltaX][getPlayerY() + direction.deltaY]) {
			setPlayerX(getPlayerX() + direction.deltaX);
			setPlayerY(getPlayerY() + direction.deltaY);
		}

		if (mode1 && !mode2 && !mode3) {
			checkGameLost();
			if (!getLost()) {
				if (indexEasy == 2) {
					movePursuer1(Direction.getRandomDirection());
					movePursuer2(Direction.getRandomDirection());
					indexEasy -= 3;
				}
			}

			indexEasy += 1;

		} else if (!mode1 && mode2 && !mode3) {
			checkGameLost();
			if (!getLost()) {
				if (indexMedium == 1) {
					if (pursuer1UseRandomMove) {
						movePursuer1(Direction.getRandomDirection());
					} else {
						List<Direction> path1 = Direction.dijkstraDirections(this, new int[]{getPlayerX(), getPlayerY()}, new int[]{getPursuer1X(), getPursuer1Y()}, getWalls());
						movePursuer1(path1.get(0));
					}

					if (pursuer2UseRandomMove) {
						movePursuer2(Direction.getRandomDirection());
					} else {
						List<Direction> path2 = Direction.dijkstraDirections(this, new int[]{getPlayerX(), getPlayerY()}, new int[]{getPursuer2X(), getPursuer2Y()}, getWalls());
						movePursuer2(path2.get(0));
					}

					if (pursuer3UseRandomMove) {
						movePursuer3(Direction.getRandomDirection());
					} else {
						List<Direction> path3 = Direction.dijkstraDirections(this, new int[]{getPlayerX(), getPlayerY()}, new int[]{getPursuer3X(), getPursuer3Y()}, getWalls());
						movePursuer3(path3.get(0));
					}

					pursuer1UseRandomMove = !pursuer1UseRandomMove;
					pursuer2UseRandomMove = !pursuer2UseRandomMove;
					pursuer3UseRandomMove = !pursuer3UseRandomMove;

					indexMedium -= 2;
				}
			}

			indexMedium += 1;

		} else if (!mode1 && !mode2 && mode3) {
			checkGameLost();
			if (!getLost()) {
				List<Direction> path1 = Direction.dijkstraDirections(this, new int[]{getPlayerX(), getPlayerY()}, new int[]{getPursuer1X(), getPursuer1Y()}, getWalls());
				movePursuer1(path1.get(0));

				List<Direction> path2 = Direction.dijkstraDirections(this, new int[]{getPlayerX(), getPlayerY()}, new int[]{getPursuer2X(), getPursuer2Y()}, getWalls());
				movePursuer2(path2.get(0));

				List<Direction> path3 = Direction.dijkstraDirections(this, new int[]{getPlayerX(), getPlayerY()}, new int[]{getPursuer3X(), getPursuer3Y()}, getWalls());
				movePursuer3(path3.get(0));

				List<Direction> path4 = Direction.dijkstraDirections(this, new int[]{getPlayerX(), getPlayerY()}, new int[]{getPursuer4X(), getPursuer4Y()}, getWalls());
				movePursuer4(path4.get(0));
			}
		}

		// Check game win and lost conditions
		checkGameWin();
		checkGameLost();

		updateViews();
	}

	///////////////////////////////////////////////////////////////////////////// Pursuer Management

	/**
	 * Moves pursuer 1 in the specified direction if the move is valid.
	 *
	 * @param direction the direction in which to move pursuer 1
	 */
	public void movePursuer1(Direction direction) {
		if ((getPursuer1X() + direction.deltaX) >= 0 && (getPursuer1X() + direction.deltaX) < walls.length && (getPursuer1Y() + direction.deltaY) >= 0 && (getPursuer1Y() + direction.deltaY) < walls[0].length) {
			if (!walls[getPursuer1X() + direction.deltaX][getPursuer1Y() + direction.deltaY]) {
				setPursuer1X(getPursuer1X() + direction.deltaX);
				setPursuer1Y(getPursuer1Y() + direction.deltaY);
			}
		}
	}

	/**
	 * Moves pursuer 2 in the specified direction if the move is valid.
	 *
	 * @param direction the direction in which to move pursuer 2
	 */
	public void movePursuer2(Direction direction) {
		if ((getPursuer2X() + direction.deltaX) >= 0 && (getPursuer2X() + direction.deltaX) < walls.length && (getPursuer2Y() + direction.deltaY) >= 0 && (getPursuer2Y() + direction.deltaY) < walls[0].length) {
			if (!walls[getPursuer2X() + direction.deltaX][getPursuer2Y() + direction.deltaY]) {
				setPursuer2X(getPursuer2X() + direction.deltaX);
				setPursuer2Y(getPursuer2Y() + direction.deltaY);
			}
		}
	}

	/**
	 * Moves pursuer 3 in the specified direction if the move is valid.
	 *
	 * @param direction the direction in which to move pursuer 3
	 */
	public void movePursuer3(Direction direction) {
		if ((getPursuer3X() + direction.deltaX) >= 0 && (getPursuer3X() + direction.deltaX) < walls.length && (getPursuer3Y() + direction.deltaY) >= 0 && (getPursuer3Y() + direction.deltaY) < walls[0].length) {
			if (!walls[getPursuer3X() + direction.deltaX][getPursuer3Y() + direction.deltaY]) {
				setPursuer3X(getPursuer3X() + direction.deltaX);
				setPursuer3Y(getPursuer3Y() + direction.deltaY);
			}
		}
	}

	/**
	 * Moves pursuer 4 in the specified direction if the move is valid.
	 *
	 * @param direction the direction in which to move pursuer 4
	 */
	public void movePursuer4(Direction direction) {
		if ((getPursuer4X() + direction.deltaX) >= 0 && (getPursuer4X() + direction.deltaX) < walls.length && (getPursuer4Y() + direction.deltaY) >= 0 && (getPursuer4Y() + direction.deltaY) < walls[0].length) {
			if (!walls[getPursuer4X() + direction.deltaX][getPursuer4Y() + direction.deltaY]) {
				setPursuer4X(getPursuer4X() + direction.deltaX);
				setPursuer4Y(getPursuer4Y() + direction.deltaY);
			}
		}
	}

	///////////////////////////////////////////////////////////////////////////// View Management

	/**
	 * Registers a view to receive updates from this world.
	 * Immediately updates the view with the current game state.
	 *
	 * @param view the view to register
	 */
	public void registerView(View view) {
		views.add(view);
		view.update(this);
	}

	/**
	 * Updates all registered views with the current game state.
	 */
	private void updateViews() {
		for (int i = 0; i < views.size(); i++) {
			views.get(i).update(this);
		}
	}
}