import java.util.*;
import java.io.*;

public class measurement {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("measurement.in"));
		PrintWriter out = new PrintWriter("measurement.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int g = Integer.parseInt(line.nextToken());
		int[][] measurements = new int[n][3];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			measurements[i][0] = Integer.parseInt(line.nextToken());
			measurements[i][1] = Integer.parseInt(line.nextToken());
			measurements[i][2] = Integer.parseInt(line.nextToken());
		}
		Arrays.sort(measurements, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		HashMap<Integer, cow> cows = new HashMap<Integer, cow>();
		TreeSet<cow> sorted = new TreeSet<cow>();
		sorted.add(new cow(1000000001, g));
		HashSet<Integer> days = new HashSet<Integer>();
		for(int i = 0; i < n; i++) {
			boolean topbefore = false;
			boolean multiple = false;
			cow cur;
			if(cows.containsKey(measurements[i][1])) {
				cur = cows.get(measurements[i][1]);
				if(cur.milk == sorted.first().milk) {
					topbefore = true;
					if(sorted.higher(sorted.first()).milk == cur.milk) {
						multiple = true;
					}
				}
				sorted.remove(cur);
				cur.milk += measurements[i][2];
				sorted.add(cur);
			}else {
				if(g >= sorted.first().milk) {
					topbefore = true;
					if(g == sorted.first().milk) {
						multiple = true;
					}
				}
				cur = new cow(measurements[i][1], g + measurements[i][2]);
				cows.put(cur.ID, cur);
				sorted.add(cur);
			}
			boolean multiple2 = false;
			if(sorted.size() > 1 && sorted.higher(sorted.first()).milk == sorted.first().milk) multiple2 = true;
			if(topbefore && measurements[i][2] < 0 && (cur.milk != sorted.first().milk || multiple2)) {
				days.add(measurements[i][0]);
			}else if((!topbefore || multiple) && cur.milk == sorted.first().milk) {
				days.add(measurements[i][0]);
			}
		}
		out.println(days.size());
		in.close();
		out.close();
	}
	static class cow implements Comparable<cow> {
		int ID;
		long milk;
		cow(int i, long m) {
			ID = i;
			milk = m;
		}
		@Override
		public int compareTo(cow o) {
			if(milk == o.milk) return ID - o.ID;
			return (int)(o.milk - milk);
		}
	}
}
