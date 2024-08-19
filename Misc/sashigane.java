import java.util.*;
import java.io.*;

public class sashigane {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int bi = Integer.parseInt(line.nextToken());
        int bj = Integer.parseInt(line.nextToken());

        out.println("Yes");
        out.println(n - 1);

        int minr = bi;
        int maxr = bi;
        int minc = bj;
        int maxc = bj;
        while (maxr - minr + 1 < n) {
            int len = maxr - minr + 1;
            // top left
            if (minr > 1 && minc > 1) {
                out.println((minr - 1) + " " + (minc - 1) + " " + len + " " + len);
                minr--;
                minc--;
            // top right
            } else if (minr > 1 && maxc < n) {
                out.println((minr - 1) + " " + (maxc + 1) + " " + len + " " + (-len));
                minr--;
                maxc++;
            // bottom left
            } else if (maxr < n && minc > 1) {
                out.println((maxr + 1) + " " + (minc - 1) + " " + (-len) + " " + len);
                maxr++;
                minc--;
            // bottom right
            } else {
                out.println((maxr + 1) + " " + (maxc + 1) + " " + (-len) + " " + (-len));
                maxr++;
                maxc++;
            }
        }

		in.close();
		out.close();
	}
}
