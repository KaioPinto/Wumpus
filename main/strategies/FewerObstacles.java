package main.strategies;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import main.game.map.*;

public class FewerObstacles implements Strategy{

	ArrayList<Point> movimentos = new ArrayList<>();

	/**
	 * N is the next location
	 * p1 p2 p3
	 * p4 N p5
	 * p6 p7 p8
	 */

	@Override
	public Point evaluatePossbileNextStep(List<Point> possibleNextStep, Map map) {
		Iterator<Point> it= possibleNextStep.iterator();
		int min = Integer.MAX_VALUE;
		Point selectedPoint = null;
		while(it.hasNext()){
			Point p = it.next();
			int count = evaluetePoint(p, map);
			if (count < min){
				min = count;
				selectedPoint = p;
			}
			}
		movimentos.add(selectedPoint);

		if (comparaMovimento(selectedPoint)){
			return null;
		}

		return selectedPoint;
	}

	private int evaluetePoint(Point p, Map map) {
		int count = 0;


		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(p.getPositionX() -1, p.getPositionY() -1));
		points.add(new Point(p.getPositionX() -1, p.getPositionY() ));
		points.add(new Point(p.getPositionX() -1 , p.getPositionY() +1));
		points.add(new Point(p.getPositionX() , p.getPositionY() -1));
		points.add(new Point(p.getPositionX() , p.getPositionY() +1));
		points.add(new Point(p.getPositionX() + 1, p.getPositionY() -1));
		points.add(new Point(p.getPositionX() + 1, p.getPositionY()));
		points.add(new Point(p.getPositionX() + 1, p.getPositionY() +1 ));

		for(int i = 0; i < points.size(); i++){
			Point currentPoint = points.get(i);
			int [] scenarioSize = map.getScenarioSize();
			Point location = map.getRobotLocation();
			if(
					currentPoint.getPositionX() == location.getPositionX() && currentPoint.getPositionY() == location.getPositionY() ||
					currentPoint.getPositionX() < 0 || currentPoint.getPositionX() >= scenarioSize[0]
					|| currentPoint.getPositionY() < 0 || currentPoint.getPositionY() >= map.getScenarioSize()[1] ){
				continue;


			}else {
				if(map.get(currentPoint).equals(Rock.CHARACTER) ){
					count++;

				}
				else if(map.get(currentPoint).equals(Monster.CHARACTER)){
					count+=2;

				}else{
					if(map.get(currentPoint).equals(TreasureChest.CHARACTER)){
						count = 0;

					}
				}



			}
		}
		return count;

	}


	public boolean comparaMovimento(Point proximo) {
		int cont = 0;
		for (Point p : movimentos) {
			if (p.getPositionX() == proximo.getPositionX() && p.getPositionY() == proximo.getPositionY()) {
				cont++;
			}
			if (cont == 4) {
				System.out.println("Robo ficou preso");
				return true;
			}
		}
		return false;
	}


}
