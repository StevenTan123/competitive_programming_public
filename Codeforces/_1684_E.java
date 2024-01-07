import java.util.*;
import java.io.*;

public class _1684_E {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		while(t-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            HashSet<Integer> aset = new HashSet<Integer>();
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                aset.add(a[i]);
            }
            int p = 0;
            int holes = 0;
            while(holes <= k) {
                if(!aset.contains(p)) {
                    holes++;
                }
                p++;
            }
            p--;
            HashMap<Integer, Integer> after = new HashMap<Integer, Integer>();
            for(int i = 0; i < n; i++) {
                if(a[i] >= p) {
                    Integer count = after.get(a[i]);
                    if(count == null) {
                        count = 0;
                    }
                    after.put(a[i], count + 1);
                }
            }
            ArrayList<Integer> freqs = new ArrayList<Integer>();
            for(int key : after.keySet()) {
                freqs.add(after.get(key));
            }
            Collections.sort(freqs);
            int left = freqs.size();
            for(int freq : freqs) {
                if(freq <= k) {
                    k -= freq;
                    left--;
                }else {
                    break;
                }
            }
            out.println(left);
		}
		in.close();
		out.close();
	}
}
