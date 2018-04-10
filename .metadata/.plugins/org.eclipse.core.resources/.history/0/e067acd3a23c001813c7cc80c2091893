import java.util.Scanner;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class Partitioning{
	
	static Cluster cluster;
	static Session session;
	static ResultSet resultSet;
	
	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect();
		session.execute("CREATE KEYSPACE IF NOT EXISTS keyspace2 WITH replication " + "= {'class':'SimpleStrategy','replication_factor':1}; ");
		session.execute("USE keyspace2");
		session.execute("CREATE TABLE IF NOT EXISTS users (name text PRIMARY KEY, age int);");
		session.execute("INSERT INTO users (name, age) VALUES ('Abhishek', 22);");
		session.execute("INSERT INTO users (name, age) VALUES ('Swapnil', 31);");
		session.execute("INSERT INTO users (name, age) VALUES ('Madhura', 52);");
		session.execute("INSERT INTO users (name, age) VALUES ('Hansraj', 21);");
		session.execute("INSERT INTO users (name, age) VALUES ('Amruta', 29);");
		
		/*Round Robin*/
		resultSet = session.execute("SELECT * FROM users;");
		System.out.println("Round Robin Partitioning");
		int diskNo = 0, totalDisk = 4, i = 0;
		System.out.println("Round Robin :: ");
		for(Row rows : resultSet){
			diskNo = i++ % totalDisk;											//FORMULA
			System.out.println("Disk " + diskNo + " : " + rows.getString("name") + " " +rows.getInt("age"));
		}
		
		/*Hash*/
		int age;
		resultSet = session.execute("SELECT * FROM users;");
		System.out.println("\nHash Partitioning");
		for(Row rows : resultSet){
			age = rows.getInt("age");
			diskNo = age % totalDisk;											//FORMULA
			System.out.println("Disk " + diskNo + " : " + rows.getString("name") + " " +rows.getInt("age"));
		}
		
		/*Range*/
		int vector, n = totalDisk, flag;
		int[] pVector = new int[n - 1];
		resultSet = session.execute("SELECT * FROM users;");
		System.out.println("\nRange Partitioning");
		System.out.println("\nPlease enter Partitioning vector values :: ");
		for(i = 0;i <= n - 2;i++){
			System.out.print("Enter v " + i + " : ");
			pVector[i] = Integer.parseInt(sc.next());
		}
		for(Row rows : resultSet){
			vector = rows.getInt("age");
			flag = 0;
			for(i = 0;i < n - 2;i++){
				if(pVector[i] <= vector && vector < pVector[i + 1]){
					diskNo = i + 1;
					flag = 1;
					break;
				}
			}
			if(flag == 0){
				if(vector < pVector[0]){
					diskNo = 0;
				}
				else if(vector >= pVector[n - 2]){
					diskNo = n - 1;
				}
			}
			System.out.println("Disk " + diskNo + " : " + rows.getString("name") + " " +rows.getInt("age"));
		}
		
		session.close();
		cluster.close();
		sc.close();
	}
	
}