import java.util.*;
import java.io.*;

public class _1850_C {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                String line = in.readLine();
                for (int j = 0; j < 8; j++) {
                    if (line.charAt(j) != '.') {
                        res.append(line.charAt(j));
                    }
                }
            }
            out.println(res.toString());
		}
		
        in.close();
		out.close();
	}
}
