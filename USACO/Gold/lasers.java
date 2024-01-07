
import java.util.*;
import java.io.*;

public class lasers {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("lasers.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		Point laser = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
		Point barn = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
		Point[] fences = new Point[n+2];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			fences[i] = new Point(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()));
		}
		fences[n] = laser;
		fences[n+1] = barn;
		in.close();
		HashMap<Integer, ArrayList<Point>> columns = new HashMap<Integer, ArrayList<Point>>();
		for(int i = 0; i < n+2; i++) {
			if(!columns.containsKey(fences[i].x)) {
				columns.put(fences[i].x, new ArrayList<Point>());
			}
			columns.get(fences[i].x).add(fences[i]);
		}
		HashMap<Integer, ArrayList<Point>> rows = new HashMap<Integer, ArrayList<Point>>();
		for(int i = 0; i < n+2; i++) {
			if(!rows.containsKey(fences[i].y)) {
				rows.put(fences[i].y, new ArrayList<Point>());
			}
			rows.get(fences[i].y).add(fences[i]);
		}
		HashMap<Integer, Integer> viscols = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> visrows = new HashMap<Integer, Integer>();
		Deque<bfsevent> bfs = new ArrayDeque<bfsevent>();
		bfs.add(new bfsevent(laser, 0, true));
		bfs.add(new bfsevent(laser, 0, false));
		int res = 0;
		while(bfs.size() > 0) {
			bfsevent p = bfs.poll();
			HashMap<Integer, Integer> source = p.direction ? visrows : viscols;
			int coord = p.direction ? p.cur.y : p.cur.x;
			if(source.containsKey(coord) && source.get(coord) > p.degree) {
				continue;
			}
			source.put(coord, p.degree);
			if(p.cur == barn) {
				res = p.degree;
				break;
			}
			ArrayList<Point> next = p.direction ? columns.get(p.cur.x) : rows.get(p.cur.y);
			for(Point pp : next) {
				if(pp != p.cur) {
					bfs.add(new bfsevent(pp, p.degree + 1, !p.direction));
				}
			}
		}
		PrintWriter out = new PrintWriter("lasers.out"); 
		out.println(res - 1);
		out.close();
	}
	static class bfsevent {
		Point cur;
		int degree;
		boolean direction;
		bfsevent(Point c, int d, boolean dir) {
			cur = c;
			degree = d;
			direction = dir;
		}
	}
	static class Point {
		int x, y;
		Point(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}
}