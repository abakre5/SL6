import java.util.ArrayList;
import java.util.List;


public class Cluster {

	public List<Point> points;
	public Point centroid;
	int id;
	static int ids = 0;
	
	
	public Cluster(){
		this.id = ids++;
		this.centroid = null;
		this.points = new ArrayList<Point>();
	}
	
	public void Clear(){
		points.clear();
	}
	
	public void plotCluser(){
		System.out.println("Cluster :: " + id);
		System.out.println("Centroid :: (" + centroid.x + " , " + centroid.y + ")");
		System.out.println("Points :: [");
		for (int i = 0; i < points.size(); i++) {
			System.out.println("(" + points.get(i).x + " , " + points.get(i).y + ")");
		}
		System.out.println("]");
	}
	
}
