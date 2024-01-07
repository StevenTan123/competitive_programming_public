import java.util.*;
import java.io.*;

public class _1492_B {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(in.readLine());
            int[] p = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] pmax = new int[n];
            for(int j = 0; j < n; j++) {
                p[j] = Integer.parseInt(line.nextToken());
                if(j == 0) pmax[j] = p[j];
                else pmax[j] = Math.max(pmax[j - 1], p[j]);
            }
            ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
            int curtarget = pmax[n - 1];
            ArrayList<Integer> curlist = new ArrayList<Integer>();
            for(int j = n - 1; j >= 0; j--) {
                curlist.add(p[j]);
                if(p[j] == curtarget) {
                    res.add(curlist);
                    if(j > 0) curtarget = pmax[j - 1];
                    curlist = new ArrayList<Integer>();
                }
            }
            res.add(curlist);
            StringBuilder sb = new StringBuilder();
            for(ArrayList<Integer> clist : res) {
                for(int j = clist.size() - 1; j >= 0; j--) {
                    sb.append(clist.get(j));
                    sb.append(' ');
                }
            }
            out.println(sb.toString());
		}
		in.close();
		out.close();
	}
}
