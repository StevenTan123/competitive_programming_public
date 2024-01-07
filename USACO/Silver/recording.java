import java.io.*;
import java.util.*;

public class recording {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("recording.in"));
		int n = Integer.parseInt(in.readLine());
		int[][] programs = new int[n][2];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			programs[i][0] = Integer.parseInt(line.nextToken());
			programs[i][1] = Integer.parseInt(line.nextToken());
		}
		in.close();
		Arrays.sort(programs, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if(o1[0] > o2[0]) {
					return 1;
				}else if(o2[0] > o1[0]) {
					return -1;
				}else {
					return o1[1] - o2[1];
				}
			}
		});
		
	}
}
