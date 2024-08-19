import java.util.*;
import java.io.*;

public class alex_revolt {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int N = Integer.parseInt(in.readLine());
        int[][] mat = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = in.readLine();
            for (int j = 0; j < N; j++) {
                mat[i][j] = line.charAt(j) == '0' ? 0 : 1;
            }
        }
        
        boolean[] nei = new boolean[N];
        int host = 0;
        for (int i = 1; i < N; i++) {
            if (mat[i][host] == 1) {
                nei[i] = true;
            } else {
                boolean change = true;
                for (int j = 0; j < i; j++) {
                    if (mat[i][j] == 1 && nei[j]) {
                        change = false;
                        break;
                    }
                }
                if (change) {
                    host = i;
                    for (int j = 0; j < i; j++) {
                        if (mat[j][i] == 1) {
                            nei[j] = true;
                        } else {
                            nei[j] = false;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (i == host) {
                out.println(0 + " " + (i + 1));
            } else if (nei[i]) {
                out.println(1 + " " + (i + 1) + " " + (host + 1));
            } else {
                for (int j = 0; j < N; j++) {
                    if (mat[i][j] == 1 && nei[j]) {
                        out.println(2 + " " + (i + 1) + " " + (j + 1) + " " + (host + 1));
                        break;
                    }
                }
            }
        }

		in.close();
		out.close();
	}
}
