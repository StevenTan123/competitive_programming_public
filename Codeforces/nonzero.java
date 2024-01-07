import java.util.*;
import java.io.*;
 
public class nonzero {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[] a = new int[n];
		StringTokenizer line = new StringTokenizer(in.readLine());
		long[] prefix = new long[n];
		HashMap<Long, ArrayList<Integer>> segs = new HashMap<Long, ArrayList<Integer>>();
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(line.nextToken());
			prefix[i] = (i > 0) ? prefix[i - 1] + a[i] : a[i];
			if(segs.containsKey(prefix[i])) {
				ArrayList<Integer> val = segs.get(prefix[i]);
				val.add(i);
				segs.put(prefix[i], val);
			}else {
				ArrayList<Integer> val = new ArrayList<Integer>();
				if(prefix[i] == 0) val.add(-1);
				val.add(i);
				segs.put(prefix[i], val);
			}
		}
		TreeSet<event> events = new TreeSet<event>();
		for(long key : segs.keySet()) {
			ArrayList<Integer> val = segs.get(key);
			event prev = new event(1, val.get(0) + 1);
			events.add(prev);
			for(int i = 1; i < val.size(); i++) {
				event event1 = new event(0, val.get(i));
				prev.counter = event1;
				event1.counter = prev;
				events.add(event1);
				event event2 = new event(1, val.get(i) + 1);
				events.add(event2);
				prev = event2;
			}
		}
		HashSet<event> active = new HashSet<event>();
		int count = 0;
		for(event e : events) {
			if(e.start == 0) {
				if(active.contains(e.counter)) {
					count++;
					active = new HashSet<event>();
				}
			}else {
				active.add(e);
			}
		}
		out.println(count);
		in.close();
		out.close();
	}
	static class event implements Comparable<event> {
		//s = 0 for end events and s = 1 for start events
		int start, index;
		event counter;
		event(int s, int i) {
			start = s;
			index = i;
		}
		@Override
		public int compareTo(event o) {
			if(index == o.index) return start - o.start;
			else return index - o.index;
		}
	}
}