import java.util.*;
import java.io.*;

public class stampede {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("stampede.in"));
		int n = Integer.parseInt(in.readLine());
		int[][] cows = new int[n][3];
		TreeSet<event> events = new TreeSet<event>(new Comparator<event>() {
			public int compare(event o1, event o2) {
				if(o1.x > o2.x) {
					return 1;
				}else if(o2.x > o1.x){
					return -1;
				}else {
					return o1.y - o2.y;
				}
			}
		});
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			cows[i][0] = Integer.parseInt(line.nextToken());
			cows[i][1] = Integer.parseInt(line.nextToken());
			cows[i][2] = Integer.parseInt(line.nextToken());
			events.add(new event(i, cows[i][2] * (Math.abs(cows[i][0]) - 1), cows[i][1], true));
			events.add(new event(i, cows[i][2] * Math.abs(cows[i][0]), cows[i][1], false));
		}
		in.close();
		TreeSet<event> cur = new TreeSet<event>(new Comparator<event>() {
			public int compare(event o1, event o2) {
				return o1.y - o2.y;
			}
		});
		boolean[] counted = new boolean[n];
		int res = 0;
		for(event e : events) {
			if(e.start) {
				cur.add(e);
			}else {
				cur.remove(new event(0, 0, e.y, false));
			}
			if(events.higher(e) != null && events.higher(e).x == e.x) {
				continue;
			}
			if(cur.size() > 0) {
				int firstCow = cur.first().cowid;
				if(!counted[firstCow]) {
					res++;
					counted[firstCow] = true;
				}
			}
		}
		PrintWriter out = new PrintWriter("stampede.out");
		out.println(res);
		out.close();
	}
	static class event {
		int cowid, x, y;
		boolean start;
		event(int c, int x, int y, boolean s) {
			cowid = c;
			this.x = x;
			this.y = y;
			start = s;
		}
	}
}
