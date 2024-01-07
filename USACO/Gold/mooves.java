import java.util.*;
import java.io.*;

public class mooves {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		long m = Long.parseLong(line.nextToken());
		int[][] pairs = new int[k][2];
		for(int i = 0; i < k; i++) {
			line = new StringTokenizer(in.readLine());
			pairs[i][0] = Integer.parseInt(line.nextToken()) - 1;
			pairs[i][1] = Integer.parseInt(line.nextToken()) - 1;
		}
		int[] order = new int[n];
		int[] perm = new int[n];
		HashSet[] visited = new HashSet[n];
		HashSet[] modvis = new HashSet[n];
		for(int i = 0; i < n; i++) {
			visited[i] = new HashSet<Integer>();
			visited[i].add(i);
			modvis[i] = new HashSet<Integer>();
			modvis[i].add(i);
			order[i] = i;
		}
		long numcyc = m / k;
		long mod = m % k;
		for(int i = 0; i < k; i++) {
			if(i < mod) {
				modvis[order[pairs[i][0]]].add(pairs[i][1]);
				modvis[order[pairs[i][1]]].add(pairs[i][0]);
			}
			visited[order[pairs[i][0]]].add(pairs[i][1]);
			visited[order[pairs[i][1]]].add(pairs[i][0]);
			int temp = order[pairs[i][0]];
			order[pairs[i][0]] = order[pairs[i][1]];
			order[pairs[i][1]] = temp;
		}
		for(int i = 0; i < n; i++) {
			perm[order[i]] = i;
		}
		int[] res = new int[n];
		boolean[] visited2 = new boolean[n];
		for(int i = 0; i < n; i++) {
			if(!visited2[i]) {
				cycle(i, perm, pairs, visited, modvis, res, numcyc, mod, visited2);
			}
		}
		for(int i = 0; i < n; i++) {
			out.println(res[i]);
		}
		in.close();
		out.close();
	}
	static void cycle(int start, int[] perm, int[][] pairs, HashSet[] visited, HashSet[] modvis, int[] res, long numcyc2, long mod, boolean[] visited2) {
		int node = start;
		int cyclen = 0;
		ArrayList<Integer> cycle = new ArrayList<Integer>();
		while(cyclen == 0 || node != start) {
			visited2[node] = true;
			cycle.add(node);
			node = perm[node];
			cyclen++;
		}
		int numcyc = (int)Math.min(numcyc2, cyclen);
		HashMap<Integer, Integer> combine = new HashMap<Integer, Integer>();
		for(int i = 0; i < numcyc; i++) {
			HashSet<Integer> curvisits = visited[cycle.get(i)];
			for(int visit : curvisits) {
				Integer freq = combine.get(visit);
				if(freq == null) freq = 0;
				combine.put(visit, freq + 1);
			}
		}
		HashSet<Integer> addmod = modvis[cycle.get(numcyc % cyclen)];
		for(int add : addmod) {
			Integer freq = combine.get(add);
			if(freq == null) freq = 0;
			combine.put(add, freq + 1);
		}
		for(int i = 0; i < cyclen; i++) {
			res[cycle.get(i)] = combine.size();
			HashSet<Integer> removemod = modvis[cycle.get((i + numcyc) % cyclen)];
			for(int drop : removemod) {
				Integer freq = combine.get(drop);
				if(freq == 1) {
					combine.remove(drop);
				}else {
					combine.put(drop, freq - 1);
				}
			}
			addmod = modvis[cycle.get((i + numcyc + 1) % cyclen)];
			for(int add : addmod) {
				Integer freq = combine.get(add);
				if(freq == null) freq = 0;
				combine.put(add, freq + 1);
			}
			if(numcyc > 0) {
				HashSet<Integer> dropset = visited[cycle.get(i)];
				for(int drop : dropset) {
					Integer freq = combine.get(drop);
					if(freq == 1) {
						combine.remove(drop);
					}else {
						combine.put(drop, freq - 1);
					}
				}
				HashSet<Integer> addset = visited[cycle.get((i + numcyc) % cyclen)];
				for(int add : addset) {
					Integer freq = combine.get(add);
					if(freq == null) freq = 0;
					combine.put(add, freq + 1);
				}
			}
		}
	}
}
