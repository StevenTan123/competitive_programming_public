import java.util.*;
import java.io.*;

public class pichu_trinominoe {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        int M = Integer.parseInt(line.nextToken());
        int res = (N * M - (N % 3) * (M % 3)) / 3;
        if (N % 3 == 2 && M % 3 == 2 && N >= 5 && M >= 5) {
            res++;
        }
        out.println(res);

		in.close();
		out.close();
	}
}
