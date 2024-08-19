import java.util.*;
import java.io.*;

public class daniel_gift {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int N = Integer.parseInt(in.readLine());
		int[][] boxes = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] box = new int[3];
            for (int j = 0; j < 3; j++) {
                box[j] = Integer.parseInt(line.nextToken());
            }
            Arrays.sort(box);
            boxes[i] = box;
        }
        Arrays.sort(boxes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                for (int i = 0; i < 3; i++) {
                    if (a[i] != b[i]) {
                        return a[i] - b[i];
                    }
                }
                return 0;
            }
        });
        boolean possible = true;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (boxes[i][j] <= boxes[i - 1][j]) {
                    possible = false;
                    break;
                }
            }
        }
        out.println(possible ? "YES" : "NO");

		in.close();
		out.close();
	}
}
