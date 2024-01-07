import java.util.*;
import java.io.*;

public class photoshoot {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int N = Integer.parseInt(in.readLine());
		String s = in.readLine();

        ArrayList<Integer> reduced = new ArrayList<Integer>();
        for (int i = 0; i < N; i += 2) {
            char a = s.charAt(i);
            char b = s.charAt(i + 1);
            if (a != b) {
                if (b == 'G') {
                    reduced.add(1);
                } else {
                    reduced.add(0);
                }
            } 
        }

        int last = 0;
        int count = 1;
        for (int i = 1; i < reduced.size(); i++) {
            if (reduced.get(i) != reduced.get(i - 1)) {
                count++;
            }
            if (reduced.get(i) == 0) {
                last = count;
            }
        }
        out.println(last);

		in.close();
		out.close();
	}
}
