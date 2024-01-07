import java.util.*;
import java.io.*;

public class cowpatibility {
    public static void main(String[] args) throws IOException {
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        BufferedReader in = new BufferedReader(new FileReader("cowpatibility.in"));
        PrintWriter out = new PrintWriter("cowpatibility.out");
        int n = Integer.parseInt(in.readLine());
        int[][] cows = new int[n][5];
        HashMap<ArrayList<Integer>, Integer> freqs = new HashMap<ArrayList<Integer>, Integer>();
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int j = 0; j < 5; j++) {
                cows[i][j] = Integer.parseInt(line.nextToken());
            }
            Arrays.sort(cows[i]);
            for(int j = 1; j < 32; j++) {
                ArrayList<Integer> cursub = new ArrayList<Integer>();
                for(int k = 0; k < 5; k++) {
                    if(j >> k > 0) {
                        if((j >> k & 1) == 1) {
                            cursub.add(cows[i][4 - k]);
                        }
                    }else {
                        break;
                    }
                }
                Integer freq = freqs.get(cursub);
                if(freq == null) freq = 0;
                freqs.put(cursub, freq + 1);
            }
        }
        long[] pie = new long[5];
        for(ArrayList<Integer> subset : freqs.keySet()) {
            int freq = freqs.get(subset);
            pie[subset.size() - 1] += freq * (freq - 1);
        }
        long res = 0;
        for(int i = 0; i < 5; i++) {
            if(i % 2 == 0) {
                res += pie[i];
            }else {
                res -= pie[i];
            }
        }
        out.println((n * (n - 1) - res) / 2);
        in.close();
        out.close();
    }
}
