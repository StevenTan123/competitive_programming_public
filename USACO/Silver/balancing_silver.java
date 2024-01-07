import java.util.*;
import java.io.*;

public class balancing_silver {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("balancing.in"));
		PrintWriter out = new PrintWriter("balancing.out");
		int n = Integer.parseInt(in.readLine());
		TreeSet<point> xsort = new TreeSet<point>(new Comparator<point>() {
			@Override
			public int compare(point o1, point o2) {
				if(o1.x == o2.x) return o1.y - o2.y;
				return o1.x - o2.x;
			}
		});
		TreeSet<point> rxsort = new TreeSet<point>(new Comparator<point>() {
			@Override
			public int compare(point o1, point o2) {
				if(o1.x == o2.x) return o1.y - o2.y;
				return o2.x - o1.x;
			}
		});
		TreeSet<point> ysort = new TreeSet<point>(new Comparator<point>() {
			@Override
			public int compare(point o1, point o2) {
				if(o1.y == o2.y) return o1.x - o2.x;
				return o1.y - o2.y;
			}
		});
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			point cur = new point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
			xsort.add(cur);
			rxsort.add(cur);
			ysort.add(cur);
		}
		int min = Integer.MAX_VALUE;
		for(point horz : ysort) {
			int horzline = horz.y - 1;
			min = Math.min(min, vertsweep(horzline, xsort, rxsort));
		}
		out.println(min);
		in.close();
		out.close();
	}
	static int vertsweep(int horzline, TreeSet<point> xsort, TreeSet<point> rxsort) {
		ArrayList<Integer> mins = new ArrayList<Integer>();
		int above = 0;
		int below = 0;
		int prevx = -1;
		for(point vert : xsort) {
			if(vert.x != prevx) {
				mins.add(Math.max(above, below));
			}
			prevx = vert.x;
			if(vert.y > horzline) {
				above++;
			}else {
				below++;
			}
		}
		mins.add(Math.max(above, below));
		above = 0;
		below = 0;
		int counter = mins.size() - 1;
		prevx = -1;
		int min = Integer.MAX_VALUE;
		for(point vert : rxsort) {
			if(vert.x != prevx) {
				int maxcur = Math.max(Math.max(above, below), mins.get(counter));
				min = Math.min(min, maxcur);
				counter--;
			}
			prevx = vert.x;
			if(vert.y > horzline) {
				above++;
			}else {
				below++;
			}
		}
		return min;
	}
	static class point {
		int x, y;
		point(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}
}
