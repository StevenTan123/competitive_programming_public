import java.util.*;
import java.io.*;

public class swap {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("swap.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int[][] swaps = new int[m][2];
		for(int i = 0; i < m; i++) {
			line = new StringTokenizer(in.readLine());
			swaps[i][0] = Integer.parseInt(line.nextToken()) - 1;
			swaps[i][1] = Integer.parseInt(line.nextToken()) - 1;
		}
		in.close();
		int[] cows = new int[n];
		for(int i = 0; i < n; i++) {
			cows[i] = i;
		}
		performProcess(cows, swaps);
		PrintWriter out = new PrintWriter("swap.out");
		int[] cycleLengths = new int[n];
		ArrayList[] inCycles = new ArrayList[n];
		int[] startCycles = new int[n];
		for(int i = 0; i < n; i++) {
			if(cycleLengths[i] > 0) {
				out.println((int)inCycles[i].get((startCycles[i] + k % cycleLengths[i]) % inCycles[i].size()) + 1);
				continue;
			}
			ArrayList<Integer> inCycle = new ArrayList<Integer>();
			int valpos = i;
			cycleLengths[i] = 1;
			inCycle.add(valpos);
			HashMap<Integer, Integer> cycleStarts = new HashMap<Integer, Integer>(); 
			cycleStarts.put(valpos, 0);
			while(cows[valpos] != i) {
				cycleLengths[i]++;
				valpos = cows[valpos];
				cycleStarts.put(valpos, cycleLengths[i] - 1);
				inCycle.add(valpos);
			}
			inCycles[i] = inCycle;
			for(int same : inCycle) {
				cycleLengths[same] = cycleLengths[i];
				inCycles[same] = inCycle;
				startCycles[same] = cycleStarts.get(same);
			}
			out.println(inCycle.get(k % cycleLengths[i]) + 1);
		}
		out.close();
	}
	static void performProcess(int[] cows, int[][] swaps) {
		for(int i = 0; i < swaps.length; i++) {
			for(int j = swaps[i][0]; j <= (swaps[i][0] + swaps[i][1]) / 2; j++) {
				int swapwith = swaps[i][1] - (j - swaps[i][0]);
				int temp = cows[swapwith];
				cows[swapwith] = cows[j];
				cows[j] = temp;
			}
		}
	}
}
