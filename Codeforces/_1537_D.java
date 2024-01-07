import java.util.*;
import java.io.*;

public class _1537_D {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int count = 0;
            while(n % 2 == 0) {
                n /= 2;
                count++;
            }
            if(count > 0) {
                if(n == 1) {
                    if(count % 2 == 0) {
                        out.println("Alice");
                    }else {
                        out.println("Bob");
                    }
                }else {
                    out.println("Alice");
                }
            }else {
                out.println("Bob");
            }
		}
		in.close();
		out.close();
	}
}
