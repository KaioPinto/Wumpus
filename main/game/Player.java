package main.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import main.game.map.Map;
import main.game.map.Point;
import main.strategies.Strategy;

public class Player {
	public static final String CHARACTER = "W";
	private Strategy strategy;
	public Player(Strategy strategy) {
		this.strategy = strategy;
	}

	public Point evaluatePossbileNextStep(Map map) {
		Point robotLocation = map.getRobotLocation();
		List<Point> possibleNextSteps = new ArrayList<>();
		possibleNextSteps.add(new Point(robotLocation.getPositionX(), robotLocation.getPositionY() + 1)); // Cima
		possibleNextSteps.add(new Point(robotLocation.getPositionX() + 1, robotLocation.getPositionY())); // Direita
		possibleNextSteps.add(new Point(robotLocation.getPositionX() - 1, robotLocation.getPositionY())); // Baixo
		possibleNextSteps.add(new Point(robotLocation.getPositionX(), robotLocation.getPositionY() - 1)); // Esquerda

		List<Point> validNextSteps = new LinkedList<>();
		for (Point p : possibleNextSteps) {

			if (map.isValidMove(p)) {
				validNextSteps.add(p);
			}
		}
		return this.strategy.evaluatePossbileNextStep(validNextSteps, map);
	}

}
