import java.util.*;
import java.io.*;

public class digit {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(in.readLine());
			String num = in.readLine();
			int[] digits = new int[n];
			for(int j = 0; j < n; j++) {
				digits[j] = Character.getNumericValue(num.charAt(j));
			}
			boolean razewins = true;
			if(n % 2 == 0) {
				for(int j = 0; j < n; j++) {
					if((j + 1) % 2 == 0 && digits[j] % 2 == 0) {
						razewins = false;
						break;
					}
				}
			}else {
				razewins = false;
				for(int j = 0; j < n; j++) {
					if((j + 1) % 2 == 1 && digits[j] % 2 == 1) {
						razewins = true;
						break;
					}
				}
			}
			out.println(razewins ? 1 : 2);
		}
		in.close();
		out.close();
	}
}
