import java.util.*;
import java.io.*;

public class squares {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("squares.in"));
		PrintWriter out = new PrintWriter("squares.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		Point[] points = new Point[n];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			Point cur = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
			points[i] = cur;
		}
		Arrays.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.x == o2.x) return o1.y - o2.y;
				return o1.x - o2.x;
			}
		});
		TreeSet<Point> active = new TreeSet<Point>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.y == o2.y) return o1.x - o2.x;
				return o1.y - o2.y;
			}
		});
		int lpointer = 0;
		long area = -1;
		boolean toomany = false;
		for(int i = 0; i < n; i++) {
			Point curpoint = points[i];
			while(points[lpointer].x + k <= curpoint.x) {
				active.remove(points[lpointer]);
				lpointer++;
			}
			Point higher = active.higher(curpoint);
			Point lower = active.lower(curpoint);
			if(higher != null && higher.y - k < curpoint.y) {
				if(area == -1) {
					area = (curpoint.y - (higher.y - k)) * (higher.x + k - curpoint.x);
				}else {
					toomany = true;
					break;
				}
			}
			if(lower != null && curpoint.y - k < lower.y) {
				if(area == -1) {
					area = (lower.y - (curpoint.y - k)) * (lower.x + k - curpoint.x);
				}else {
					toomany = true;
					break;
				}
			}
			active.add(curpoint);
		}
		if(toomany) {
			out.println(-1);
		}else if(area == -1) {
			out.println(0);
		}else {
			out.println(area);
		}
		in.close();
		out.close();
	}
	static class Point {
		int x, y;
		Point(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}
}
