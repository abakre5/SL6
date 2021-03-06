import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Point implements Serializable{

	static final long serialVersionUID = 1L;
	public int x;
	public int y;
	
	public Point(int x, int y){
		super();
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return  String.format("(%d, %d)", this.x , this.y);
	}
	
}
