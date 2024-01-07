import java.util.*;
import java.io.*;

public class what_is_n {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int count = 0;
		if (n <= 5) {
			count++;
		}
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= i; j++) {
				if (i + j == n) {
					count++;
				}
			}
		}
		out.println(count);
		in.close();
		out.close();
	}
}
