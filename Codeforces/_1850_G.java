import java.util.*;
import java.io.*;

public class _1850_G {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {	
            int n = Integer.parseInt(in.readLine());
            HashMap<Integer, Integer> horz = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> vert = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> diag1 = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> diag2 = new HashMap<Integer, Integer>();
            for (int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(line.nextToken());
                int y = Integer.parseInt(line.nextToken());
                add(horz, y);
                add(vert, x);
                add(diag1, x + y);
                add(diag2, y - x);
            }
            long res = count(horz) + count(vert) + count(diag1) + count(diag2);
            out.println(res);
        }
		
        in.close();
		out.close();
	}
    static void add(HashMap<Integer, Integer> freq, int val) {
        Integer count = freq.get(val);
        if (count == null) {
            count = 0;
        }
        freq.put(val, count + 1);
    }
    static long count(HashMap<Integer, Integer> freq) {
        long res = 0;
        for (int val : freq.keySet()) {
            int count = freq.get(val);
            if (count >= 2) {
                res += (long) count * (count - 1);
            }
        }
        return res;
    }
}
