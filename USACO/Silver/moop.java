import java.util.*;
import java.io.*;

public class moop {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("moop.in"));
		int n = Integer.parseInt(in.readLine());
		TreeSet<component> components = new TreeSet<component>();
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(line.nextToken());
			int y = Integer.parseInt(line.nextToken());
			components.add(new component(x, y));
		}
		in.close();
		boolean done = false;
		while(!done) {
			done = true;
			component prev = null;
			TreeSet<component> combined = new TreeSet<component>();
			for(component c : components) {
				if(prev != null) {
					if(prev.miny <= c.maxy) {
						done = false;
						prev.maxy = Math.max(prev.maxy, c.maxy);
						prev.miny = Math.min(prev.miny, c.miny);
						c = prev;
					}
				}
				if(!combined.contains(c)) combined.add(c);
				prev = c;
			}
			if(!done) {
				components = combined;
			}
		}
		PrintWriter out = new PrintWriter("moop.out");
		out.println(components.size());
		out.close();
	}
	static class component implements Comparable<component> {
		int fx, fy, miny, maxy;
		component(int x, int y) {
			fx = x;
			fy = y;
			miny = y;
			maxy = y;
		}
		public int compareTo(component c) {
			if(fx > c.fx) {
				return 1;
			}else if(c.fx > fx) {
				return -1;
			}else {
				return fy - c.fy;
			}
		}
	}
}
