import java.util.*;
import java.io.*;

public class berries {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("berries.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		ArrayList<Integer> berrytrees = new ArrayList<Integer>();
		line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			berrytrees.add(Integer.parseInt(line.nextToken()));
		}
		in.close();
		Collections.sort(berrytrees);
		int maxb = berrytrees.get(berrytrees.size() - 1);
		ArrayList<Integer> toptrees = new ArrayList<Integer>();
		for(int i = n - 1; i >= 0 && i >= n - k; i--) {
			toptrees.add(berrytrees.get(i));
		}
		int res = 0;
		for(int i = 1; i < maxb; i++) {
			toptrees = new ArrayList<Integer>();
			for(int j = n - 1; j >= 0 && j >= n - k; j--) {
				toptrees.add(berrytrees.get(j));
			}
			int[] baskets = new int[k];
			int sum = 0;
			int treescounter = 0;
			boolean fufill = true;
			for(int j = 0; j < k; j++) {
				if(treescounter >= toptrees.size()) {
					break;
				}
				if(toptrees.get(treescounter) >= i) {
					baskets[j] = i;
					toptrees.set(treescounter, toptrees.get(treescounter) - i);
					if(j >= k / 2) {
						sum += baskets[j];
					}
					treescounter++;
				}else if(!fufill){
					baskets[j] = toptrees.get(treescounter);
					if(j >= k / 2) {
						sum += baskets[j];
					}
					treescounter++;
				}else {
					Collections.sort(toptrees, Collections.reverseOrder());
					treescounter = 0;
					if(toptrees.get(0) < i) {
						fufill = false;
					}
					j--;
				}
			}
			if(sum > res) {
				res = sum;
			}
		}
		PrintWriter out = new PrintWriter("berries.out");
		out.println(res);
		out.close();
	}
}
