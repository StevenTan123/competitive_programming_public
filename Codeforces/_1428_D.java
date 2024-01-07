import java.util.*;
import java.io.*;

public class _1428_D {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[] a = new int[n];
		StringTokenizer line = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(line.nextToken());
		}
		ArrayList<Point> targets = new ArrayList<Point>();
		TreeSet<Point> usable = new TreeSet<Point>();
		int oneopen = n - 1;
		for(int i = n - 1; i >= 0; i--) {
			if(a[i] == 1) {
				if(oneopen < 0) {
					out.println(-1);
					in.close();
					out.close();
					return;
				}
				Point add = new Point(oneopen, i, false);
				targets.add(add);
				usable.add(add);
				oneopen--;
			}else if(a[i] == 2) {
				Point cur = null;
				if(usable.size() <= 0 || (cur = usable.lower(new Point(Integer.MAX_VALUE, Integer.MAX_VALUE, false))) == null) {
					out.println(-1);
					in.close();
					out.close();
					return;
				}else {
					usable.remove(cur);
					Point add = new Point(cur.r, i, true);
					targets.add(add);
					usable.add(add);
				}
			}else if(a[i] == 3) {
				if(oneopen < 0 || usable.size() <= 0) {
					out.println(-1);
					in.close();
					out.close();
					return;
				}
				Point cur = usable.lower(new Point(Integer.MAX_VALUE, Integer.MAX_VALUE, true));
				usable.remove(cur);
				Point add = new Point(oneopen, i, true);
				targets.add(add);
				targets.add(new Point(oneopen, cur.c, false));
				usable.add(add);
				oneopen--;
			}
		}
		out.println(targets.size());
		for(int i = 0; i < targets.size(); i++) {
			Point curtarget = targets.get(i);
			out.println((curtarget.r + 1) + " " + (curtarget.c + 1));
		}
		in.close();
		out.close();
	}
	static class Point implements Comparable<Point> {
		int r, c;
		boolean three;
		Point(int rr, int cc, boolean t) {
			r = rr;
			c = cc;
			three = t;
		}
		@Override
		public int compareTo(Point o) {
			if(three == o.three) {
				return r - o.r;
			}else {
				return three ? 1 : -1;
			}
		}
	}
}
