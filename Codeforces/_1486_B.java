import java.util.*;
import java.io.*;

public class _1486_B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			int[][] xhouses = new int[n][2];
			int[][] yhouses = new int[n][2];
			for(int j = 0; j < n; j++) {
				StringTokenizer line = new StringTokenizer(in.readLine());
				xhouses[j][0] = Integer.parseInt(line.nextToken());
				xhouses[j][1] = Integer.parseInt(line.nextToken());
				yhouses[j][0] = xhouses[j][1];
				yhouses[j][1] = xhouses[j][0];
			}
			Comparator<int[]> comp = new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			};
			Arrays.sort(xhouses, comp);
			Arrays.sort(yhouses, comp);
			int[] horz = sweep(xhouses, n);
			int[] vert = sweep(yhouses, n);
			long res = horz[1] - horz[0] + 1;
			res *= vert[1] - vert[0] + 1;
			out.println(res);
		}
		in.close();
		out.close();
	}
	static int[] sweep(int[][] houses, int n) {
		int l = -1;
		int r = -1;
		int counter = 0;
		int preveq = -1;
		while(counter < n) {
			if(counter < n - 1 && houses[counter + 1][0] == houses[counter][0]) {
				if(preveq == -1) preveq = counter;
				counter++;
				continue;
			}else {
				int numeq = preveq == -1 ? 1 : (counter - preveq + 1);
				preveq = -1;
				int numbef = counter - numeq + 1;
				int numaft = n - counter - 1;
				if(l == -1 && numbef + numeq >= numaft) {
					l = houses[counter][0];
				}
				if(r == -1 && numbef + numeq > numaft) {
					r = houses[counter][0];
				}
			}
			counter++;
		}
		return new int[] {l, r};
	}
}
