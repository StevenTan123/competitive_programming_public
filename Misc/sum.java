import java.util.*;
import java.io.*;

public class sum {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
        int a = Integer.parseInt(line.nextToken());
        int b = Integer.parseInt(line.nextToken());
        out.println(a + b);
		in.close();
		out.close();
	}
}
