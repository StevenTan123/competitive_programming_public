import java.io.*;
import java.util.*;

public class helpcross {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("helpcross.in"));
		PrintWriter out = new PrintWriter("helpcross.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int c = Integer.parseInt(line.nextToken());
		int n = Integer.parseInt(line.nextToken());
		int[] chickens = new int[c];
		for(int i = 0; i < c; i++) {
			chickens[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(chickens);
		event[] events = new event[2 * n];
		event[][] epairs = new event[n][2];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			epairs[i][0] = new event(0, Integer.parseInt(line.nextToken()), i);
			epairs[i][1] = new event(1, Integer.parseInt(line.nextToken()), i);
			events[i * 2] = epairs[i][0];
			events[i * 2 + 1] = epairs[i][1];
		}
		Arrays.sort(events, new Comparator<event>() {
			@Override
			public int compare(event o1, event o2) {
				if(o1.pos == o2.pos) return o1.type - o2.type;
				return o1.pos - o2.pos;
			}
		});
		TreeSet<event> active = new TreeSet<event>(new Comparator<event>() {
			@Override
			public int compare(event o1, event o2) {
				if(epairs[o1.ind][1].pos == epairs[o2.ind][1].pos) return o1.ind - o2.ind;
				return epairs[o1.ind][1].pos - epairs[o2.ind][1].pos;
			}
		});
		int epnt = 0;
		int matches = 0;
		for(int i = 0; i < c; i++) {
			while(epnt < events.length && events[epnt].pos <= chickens[i]) {
				if(events[epnt].pos == chickens[i] && events[epnt].type == 1) break;
				if(events[epnt].type == 0) {
					active.add(events[epnt]);
				}else {
					if(active.contains(epairs[events[epnt].ind][0])) active.remove(epairs[events[epnt].ind][0]);
				}
				epnt++;
			}
			if(active.size() > 0) {
				active.pollFirst();
				matches++;
			}
		}
		out.println(matches);
		in.close();
		out.close();
	}
	static class event {
		int type, pos, ind;
		event(int t, int p, int i) {
			type = t;
			pos = p;
			ind = i;
		}
	}
}
