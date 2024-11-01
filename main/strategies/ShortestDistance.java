package main.strategies;

import java.util.List;

import main.game.map.Map;
import main.game.map.Point;

public class ShortestDistance implements Strategy {

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextSteps, Map map) {
		Point destination = map.getTreasureLocation();
		if (destination == null) {
			return null;
		}

		Point closestPoint = null;
		int shortestDistance = Integer.MAX_VALUE;

		for (Point step : possibleNextSteps) {
			if (map.isValidMove(step)) {
				int distance = calculateManhattanDistance(step, destination);
				if (distance < shortestDistance) {
					shortestDistance = distance;
					closestPoint = step;
				}
			}
		}

		if (closestPoint != null && closestPoint.equals(destination)) {
			return closestPoint;
		}

		return closestPoint;
	}

	private int calculateManhattanDistance(Point a, Point b) {
		return Math.abs(a.getPositionX() - b.getPositionX()) + Math.abs(a.getPositionY() - b.getPositionY());
	}
}
