import java.util.*;
import java.io.*;

public class triangles {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("triangles.in"));
		HashMap<Integer, TreeSet<Integer>> columns = new HashMap<Integer, TreeSet<Integer>>();
		HashMap<Integer, TreeSet<Integer>> rows = new HashMap<Integer, TreeSet<Integer>>();
		int n = Integer.parseInt(in.readLine());
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(line.nextToken());
			int y = Integer.parseInt(line.nextToken());
			if(columns.containsKey(x)) {
				columns.get(x).add(y);
			}else {
				TreeSet<Integer> col = new TreeSet<Integer>();
				col.add(y);
				columns.put(x, col);
			}
			if(rows.containsKey(y)) {
				rows.get(y).add(x);
			}else {
				TreeSet<Integer> row = new TreeSet<Integer>();
				row.add(x);
				rows.put(y, row);
			}
		}
		in.close();
		HashMap<Integer, Long> rightAngles = new HashMap<Integer, Long>();
		for(int x : columns.keySet()) {
			TreeSet<Integer> yvalues = columns.get(x);
			if(yvalues.size() < 2) {
				continue;
			}
			long sumheights1 = 0;
			int counter = 0;
			int first = 0;
			for(int y : yvalues) {
				if(counter == 0) {
					first = y;
					counter++;
					continue;
				}
				sumheights1 += Math.abs(y - first);
			}
			counter = 1;
			long prevs = sumheights1;
			int prev = first;
			rightAngles.put((x + 10000) * 20001 + first + 10000, sumheights1);
			for(int y : yvalues) {
				if(counter == 1) {
					counter++;
					continue;
				}
				long cursumheights = prevs + (2 * (counter - 1) - yvalues.size()) * (y - prev);
				rightAngles.put((x + 10000) * 20001 + y + 10000, cursumheights);
				prevs = cursumheights;
				prev = y;
				counter++;
			}
		}
		long res = 0;
		for(int y : rows.keySet()) {
			TreeSet<Integer> xvalues = rows.get(y);
			if(xvalues.size() < 2) {
				continue;
			}
			long sumbases1 = 0;
			int counter = 0;
			int first = 0;
			for(int x : xvalues) {
				if(counter == 0) {
					first = x;
					counter++;
					continue;
				}
				sumbases1 += Math.abs(x - first);
			}
			counter = 1;
			long prevs = sumbases1;
			int prev = first;
			int key = (first + 10000) * 20001 + y + 10000;
			if(rightAngles.containsKey(key)) {
				res += rightAngles.get(key) * sumbases1;
			}
			for(int x : xvalues) {
				if(counter == 1) {
					counter++;
					continue;
				}
				long cursumbases = prevs + (2 * (counter - 1) - xvalues.size()) * (x - prev);
				key = (x + 10000) * 20001 + y + 10000;
				if(rightAngles.containsKey(key)) {
					res += rightAngles.get(key) * cursumbases;
				}
				prevs = cursumbases;
				prev = x;
				counter++;
			}
		}
		res = res % ((long)Math.pow(10, 9) + 7);
		PrintWriter out = new PrintWriter("triangles.out");
		out.println((long)res);
		out.close();
	}
}
