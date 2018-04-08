import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Point {

	public double x;
	public double y;
	public int clusterNumber;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
		this.clusterNumber = 0;
	}

	public static double Distance(Point p, Point centroid) {
		return Math.sqrt(Math.pow((centroid.y - p.y), 2) + Math.pow((centroid.x - p.x), 2));
	}

	public static Point generateRandomPoint(int min, int max) {
		Random random = new Random();
		double x = min + (max - min) * random.nextDouble();
		double y = min + (max - min) * random.nextDouble();
		return new Point(x, y);
	}

	public static List<Point> generateRandomPointList(int min, int max, int n) {
		List<Point> points = new ArrayList<Point>();
		for (int i = 0; i < n; i++)
			points.add(generateRandomPoint(min, max));
		return points;
	}

}
