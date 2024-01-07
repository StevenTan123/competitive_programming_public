import java.util.*;
import java.io.*;

public class _559_B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String a = in.readLine();
		String b = in.readLine();
		out.println(equivalent(a, b) ? "YES" : "NO");
		in.close();
		out.close();
	}
	static boolean equivalent(String a, String b) {
		if(a.length() % 2 != 0) {
			return a.equals(b);
		}
		if(a.equals(b)) return true;
		String a1 = a.substring(0, a.length() / 2);
		String a2 = a.substring(a.length() / 2);
		String b1 = b.substring(0, b.length() / 2);
		String b2 = b.substring(b.length() / 2);
		return (equivalent(a1, b2) && equivalent(a2, b1)) || (equivalent(a1, b1) && equivalent(a2, b2));
	}
}
