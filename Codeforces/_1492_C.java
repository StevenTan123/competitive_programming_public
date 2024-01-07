import java.util.*;
import java.io.*;

public class _1492_C {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        String s = in.readLine();
        String t = in.readLine();
        int[] fmatch = new int[n];
        int[] bmatch = new int[n];
        int fpointer = 0;
        int bpointer = m - 1;
        for(int i = 0; i < n; i++) {
            if(fpointer < m && s.charAt(i) == t.charAt(fpointer)) {
                fpointer++;
            }
            fmatch[i] = fpointer;
            int j = n - i - 1;
            if(bpointer >= 0 && s.charAt(j) == t.charAt(bpointer)) {
                bpointer--;
            }
            bmatch[j] = m - 1 - bpointer;
        }
        HashMap<Integer, Integer> focc = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> bocc = new HashMap<Integer, Integer>();
        for(int i = 0; i < n; i++) {
            if(!focc.containsKey(fmatch[i])) focc.put(fmatch[i], i);
            int j = n - i - 1;
            if(!bocc.containsKey(bmatch[j])) bocc.put(bmatch[j], j);
        }
        int max = 0;
        for(int i = 1; i < m; i++) {
            max = Math.max(max, bocc.get(m - i) - focc.get(i));
        }
        out.println(max);
		in.close();
		out.close();
	}
}
