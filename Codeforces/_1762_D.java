import java.util.*;
import java.io.*;

public class _1762_D {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int ind1 = 1;
            int ind2 = 2;
            for (int i = 3; i <= n; i++) {
                System.out.println("? " + ind1 + " " + ind2);
                System.out.flush();
                int gcd1 = Integer.parseInt(in.readLine());

                System.out.println("? " + ind1 + " " + i);
                System.out.flush();
                int gcd2 = Integer.parseInt(in.readLine());

                if (gcd1 == gcd2) {
                    ind1 = i;
                } else if (gcd1 < gcd2) {
                    ind2 = i;
                }
            }
            System.out.println("! " + ind1 + " " + ind2);
            System.out.flush();
            in.readLine();
		}
		
        in.close();
		out.close();
	}
}
