import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PageRank {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		double dampFactor = 0.85;						//Google Damping Factor

		System.out.print("Enter number of webpages :: ");
		int n = sc.nextInt();
		int total = 5;
		HashMap<Integer, ArrayList<Integer>> links = new HashMap<Integer, ArrayList<Integer>>();

		sc.nextLine();
		
		for(int i = 0;i < n;i++){
			System.out.print("Enter pages connects to " + i + " ::  ");
			ArrayList<Integer> values = new ArrayList<Integer>();
			String str = "";
			str = sc.nextLine();
			String pageList[] = str.split(" ");
			for(int j = 0;j < pageList.length;j++)
				values.add(Integer.parseInt(pageList[j]));
			links.put(i, values);
		}
		
		sc.close();
		
		System.out.println("Matrix of links :: " + links);
		
		ArrayList<Double> pageRank = new ArrayList<Double>();
		ArrayList<Double> tempPageRank = new ArrayList<Double>();
		
		System.out.println("Init PageRank are ");
		for(int i = 0;i < n;i++){
			pageRank.add(i, (double)1/n);
			tempPageRank.add(i, (double)1/n);
			System.out.println("Page " + i + " : " + pageRank.get(i));
		}
		
		
		for(int iterations = 1;iterations <= total;iterations++){
			for(int i = 0;i < n;i++){
				double temp = (1 - dampFactor) / n;
				for(int j = 0;j < n;j++){
					if(links.get(j).contains(i)){
						temp += dampFactor * (pageRank.get(j) * (1/links.get(j).size()));
					}
				}
				tempPageRank.set(i, temp);
				pageRank.set(i, tempPageRank.get(i));
			}
			
			System.out.println("\nPageRank after iteration " + iterations + " :: ");
			for(int i = 0;i < n;i++){
				System.out.println("Page " + i + " : " + pageRank.get(i));
			}
		}
		
	}

}
