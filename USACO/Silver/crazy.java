import java.util.*;
import java.io.*;

public class crazy {
	
	static point outofbounds = new point(1, 1000005);
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("crazy.in"));
		PrintWriter out = new PrintWriter("crazy.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int c = Integer.parseInt(line.nextToken());
		segment[] fences = new segment[n];
		point[] cows = new point[c];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			point p1 = new point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
			point p2 = new point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
			fences[i] = new segment(p1, p2);
		}
		for(int i = 0; i < c; i++) {
			line = new StringTokenizer(in.readLine());
			cows[i] = new point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
		}
		ArrayList<ArrayList<Integer>> polygons = new ArrayList<ArrayList<Integer>>();
		boolean[] visited = new boolean[n];
		for(int i = 0; i < n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			ArrayList<Integer> curpolygon = new ArrayList<Integer>();
			curpolygon.add(i);
			int indseg = i;
			boolean end = false;
			while(!end) {
				boolean found = false;
				for(int j = 0; j < n; j++) {
					if(visited[j]) continue;
					if(samepoint(fences[indseg].p1, fences[j].p1) || 
					   samepoint(fences[indseg].p1, fences[j].p2) || 
					   samepoint(fences[indseg].p2, fences[j].p1) || 
					   samepoint(fences[indseg].p2, fences[j].p2)) {
						visited[j] = true;
						curpolygon.add(j);
						indseg = j;
						found = true;
						break;
					}
				}
				if(!found) end = true;
			}
			polygons.add(curpolygon);
		}
		String[] polygon_sets = new String[c];
		for(int i = 0; i < c; i++) {
			point cow = cows[i];
			ArrayList<Integer> curset = new ArrayList<Integer>();
			for(int j = 0; j < polygons.size(); j++) {
				ArrayList<Integer> curpolygon = polygons.get(j);
				if(p_in_poly(cow, fences, curpolygon)) {
					curset.add(j);
				}
			}
			Collections.sort(curset);
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < curset.size(); j++) {
				sb.append(curset.get(j));
				sb.append(' ');
			}
			polygon_sets[i] = sb.toString();
		}
		Arrays.sort(polygon_sets);
		int prevstart = 0;
		int max = 0;
		for(int i = 1; i < c; i++) {
			if(!polygon_sets[i].equals(polygon_sets[prevstart])) {
				max = Math.max(max, i - prevstart);
				prevstart = i;
			}
		}
		max = Math.max(max, c - prevstart);
		out.println(max);
		in.close();
		out.close();
	}
	static boolean p_in_poly(point p, segment[] fences, ArrayList<Integer> polygon) {
		int intersect_count = 0;
		for(int i = 0; i < polygon.size(); i++) {
			segment ray = new segment(p, outofbounds);
			if(intersect(ray, fences[polygon.get(i)])) {
				intersect_count++;
			}
		}
		if(intersect_count % 2 == 0) {
			return false;
		}
		return true;
	}
	static boolean samepoint(point p1, point p2) {
		return p1.x == p2.x && p1.y == p2.y;
	}
	static boolean intersect(segment s1, segment s2) {
		int o1 = orientation(s1.p1, s1.p2, s2.p1); 
		int o2 = orientation(s1.p1, s1.p2, s2.p2); 
		int o3 = orientation(s2.p1, s2.p2, s1.p1); 
		int o4 = orientation(s2.p1, s2.p2, s1.p2);
		if(o1 != o2 && o3 != o4) {
			return true;
		}
		if(o1 == 0 && o2 == 0 && o3 == 0 && o4 == 0) {
			if(onsegment(s1.p1, s2) || onsegment(s1.p2, s2)) {
				return true;
			}
		}
		return false;
	}
	static int orientation(point p1, point p2, point p3) {
		long val = (p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y);
		if(val == 0) return 0;
		return val > 0 ? 1 : -1;
	}
	static boolean onsegment(point p, segment s) {
		if(p.x >= Math.min(s.p1.x, s.p2.x) &&
		   p.x <= Math.max(s.p1.x, s.p2.x) && 
		   p.y >= Math.min(s.p1.y, s.p2.y) && 
		   p.y <= Math.max(s.p1.y, s.p2.y)) {
			return true;
		}
		return false;
	}
	static class point {
		int x, y;
		point(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}
	static class segment {
		point p1, p2;
		segment(point pp1, point pp2) {
			p1 = pp1;
			p2 = pp2;
		}
	}
}
