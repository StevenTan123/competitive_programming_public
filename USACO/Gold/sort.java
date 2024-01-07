import java.util.*;
import java.io.*;

public class sort {
	public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sort.in"));
		PrintWriter out = new PrintWriter("sort.out");
		int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        int[][] sorted = new int[n][2];
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(in.readLine());
            sorted[i][0] = a[i];
            sorted[i][1] = i;
        }
        Arrays.sort(sorted, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });
        int[] bit = new int[n + 1];
        int max = 0;
        for(int i = 0; i < n - 1; i++) {
            update(bit, sorted[i][1], 1);
            int m = sum(bit, n - 1) - sum(bit, i);
            max = Math.max(max, m);
        }
        out.println(max);
		in.close();
		out.close();
	}
    static void update(int[] bit, int index, int add) {
		index += 1;
		while(index < bit.length) {
			bit[index] += add;
			index += index & -index;
		}
	}
	static int sum(int[] bit, int index) {
		index += 1;
		int res = 0;
		while(index > 0) {
			res += bit[index];
			index -= index & -index;
		}
		return res;
	}
}
