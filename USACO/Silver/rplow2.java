import java.util.*;
import java.io.*;

public class rplow2 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(line.nextToken());
		int y = Integer.parseInt(line.nextToken());
		int n = Integer.parseInt(line.nextToken());
		vertSide[][] rects = new vertSide[n][2];
		vertSide[] sorted = new vertSide[2 * n];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(line.nextToken());
			int y1 = Integer.parseInt(line.nextToken());
			int x2 = Integer.parseInt(line.nextToken());
			int y2 = Integer.parseInt(line.nextToken());
			vertSide left = new vertSide(x1, y2, y1, false, i);
			vertSide right = new vertSide(x2, y2, y1, true, i);
			sorted[i * 2] = left;
			sorted[i * 2 + 1] = right;
			rects[i][0] = left;
			rects[i][1] = right;
		}
		Arrays.sort(sorted, new Comparator<vertSide>() {
			@Override
			public int compare(vertSide o1, vertSide o2) {
				if(o1.x == o2.x) {
					if(o1.right == o2.right) return o1.index - o2.index;
					return o1.right ? 1 : -1;
				}
				return o1.x - o2.x;
			}
		});
		TreeSet<vertSide> active = new TreeSet<vertSide>(new Comparator<vertSide>() {
			@Override
			public int compare(vertSide o1, vertSide o2) {
				if(o2.y1 == o1.y1) {
					if(o1.right == o2.right) return o1.index - o2.index;
					return o1.right ? 1 : -1;
				}
				return o2.y1 - o1.y1;
			}
		});
		long area = 0;
		boolean prevStart = false;
		for(int i = 0; i < 2 * n; i++) {
			int verttotal = 0;
			int starty = -1;
			int endy = -1;
			for(vertSide v : active) {
				if(starty == -1) {
					starty = v.y1;
					endy = v.y2;
				}else {
					if(v.y1 < endy) {
						verttotal += starty - endy + 1;
						starty = v.y1;
						endy = v.y2;
					}else {
						endy = Math.min(endy, v.y2);
					}
				}
			}
			if(starty != -1) verttotal += starty - endy + 1;
			if(i > 0) {
				int horz = sorted[i].x - sorted[i - 1].x;
				if(prevStart && sorted[i].right) horz++;
				if(!prevStart && !sorted[i].right) horz--;
				area += verttotal * horz;
			}
			if(sorted[i].right) {
				active.remove(rects[sorted[i].index][0]);
				prevStart = false;
			}else {
				active.add(sorted[i]);
				prevStart = true;
			}
		}
		out.println(area);
		in.close();
		out.close();
	}
	static class vertSide {
		int x, y1, y2, index;
		boolean right;
		vertSide(int xx, int yy1, int yy2, boolean r, int i) {
			x = xx;
			y1 = yy1;
			y2 = yy2;
			right = r;
			index = i;
		}
	}
}
