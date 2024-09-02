package model;

import java.util.*;

/**
 * The Direction enum represents cardinal directions and provides utility methods for directions.
 */
public enum Direction {
	/**
	 * Represents no movement.
	 */
	NONE(0, 0),

	/**
	 * Represents upward movement.
	 */
	UP(0, -1),

	/**
	 * Represents downward movement.
	 */
	DOWN(0, 1),

	/**
	 * Represents leftward movement.
	 */
	LEFT(-1, 0),

	/**
	 * Represents rightward movement.
	 */
	RIGHT(1, 0);

	/**
	 * Delta X (horizontal movement) associated with this direction.
	 */
	public final int deltaX;

	/**
	 * Delta Y (vertical movement) associated with this direction.
	 */
	public final int deltaY;

	// Array of all directions
	private static final Direction[] values = {UP, DOWN, LEFT, RIGHT};

	// Random number generator for obtaining random directions
	private static final Random random = new Random();

	/**
	 * Constructs a Direction with specified deltaX and deltaY.
	 *
	 * @param deltaX the change in X coordinate associated with this direction
	 * @param deltaY the change in Y coordinate associated with this direction
	 */
	private Direction(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	/**
	 * Returns a random Direction from the predefined set of values.
	 *
	 * @return a random Direction
	 */
	public static Direction getRandomDirection() {
		int randomIndex = random.nextInt(values.length);
		return values[randomIndex];
	}

	//////////////////////////////// Dijkstra Algorithm ///////////////////////////////////////////

	/**
	 * Performs Dijkstra's algorithm to find the shortest path from pursuerCoords to playerCoords.
	 *
	 * @param world         the World object representing the game world
	 * @param playerCoords  the coordinates of the player [x, y]
	 * @param pursuerCoords the coordinates of the pursuer [x, y]
	 * @param walls         a 2D boolean array representing the walls in the world
	 * @return a list of int arrays representing the coordinates of the path from pursuer to player
	 */
	public static List<int[]> dijkstra(World world, int[] playerCoords, int[] pursuerCoords, boolean[][] walls) {
		// Swap playerCoords and pursuerCoords
		int[] temp = playerCoords;
		playerCoords = pursuerCoords;
		pursuerCoords = temp;

		int width = world.getWidth();
		int height = world.getHeight();

		// Priority queue for Dijkstra's algorithm
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2])); // {x, y, cost}
		pq.offer(new int[]{playerCoords[0], playerCoords[1], 0});

		// Array to store minimum cost to reach each cell
		int[][] minCost = new int[width][height];
		for (int[] row : minCost) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		minCost[playerCoords[0]][playerCoords[1]] = 0;

		// Array to store parent cell for each cell in the path
		int[][][] parent = new int[width][height][2]; // {parentX, parentY}
		parent[playerCoords[0]][playerCoords[1]] = new int[]{-1, -1};

		// Perform Dijkstra's algorithm
		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int x = current[0];
			int y = current[1];
			int cost = current[2];

			if (cost > minCost[x][y]) {
				continue;
			}

			// Explore all valid neighbors
			for (Direction dir : Direction.values()) {
				int newX = x + dir.deltaX;
				int newY = y + dir.deltaY;

				if (newX >= 0 && newX < width && newY >= 0 && newY < height && !walls[newX][newY]) {
					int newCost = cost + 1;

					if (newCost < minCost[newX][newY]) {
						minCost[newX][newY] = newCost;
						pq.offer(new int[]{newX, newY, newCost});
						parent[newX][newY] = new int[]{x, y};
					}
				}
			}
		}

		// Reconstruct path from pursuer to player using parent array
		List<int[]> path = new ArrayList<>();
		int[] current = pursuerCoords;
		while (!Arrays.equals(current, playerCoords)) {
			path.add(current);
			current = parent[current[0]][current[1]];
		}
		path.add(playerCoords);
		Collections.reverse(path);

		return path;
	}

	/**
	 * Calculates the list of Directions representing the path from pursuer to player using Dijkstra's algorithm.
	 *
	 * @param world         the World object representing the game world
	 * @param playerCoords  the coordinates of the player [x, y]
	 * @param pursuerCoords the coordinates of the pursuer [x, y]
	 * @param walls         a 2D boolean array representing the walls in the world
	 * @return a list of Directions representing the path from pursuer to player
	 */
	public static List<Direction> dijkstraDirections(World world, int[] playerCoords, int[] pursuerCoords, boolean[][] walls) {
		List<Direction> directions = new ArrayList<>();
		List<int[]> path = dijkstra(world, playerCoords, pursuerCoords, walls);

		// Convert path to list of Directions
		for (int i = 1; i < path.size(); i++) {
			int[] current = path.get(i);
			int[] previous = path.get(i - 1);

			int dx = current[0] - previous[0];
			int dy = current[1] - previous[1];

			if (dx == 0 && dy == -1) {
				directions.add(UP);
			} else if (dx == 0 && dy == 1) {
				directions.add(DOWN);
			} else if (dx == -1 && dy == 0) {
				directions.add(LEFT);
			} else if (dx == 1 && dy == 0) {
				directions.add(RIGHT);
			}
		}

		return directions;
	}
}