import java.util.*;
import java.io.*;

public class _1498_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int w = Integer.parseInt(line.nextToken());
            Comparator<Integer> comp = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            };
            TreeMap<Integer, Integer> freqs = new TreeMap<Integer, Integer>(comp);
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                int pow = Integer.parseInt(line.nextToken());
                Integer val = freqs.get(pow);
                if(val == null) val = 0;
                freqs.put(pow, val + 1);
            }
            int count = 1;
            while(true) {
                TreeMap<Integer, Integer> newfreq = new TreeMap<Integer, Integer>(comp);
                int left = w;
                for(int pow : freqs.keySet()) {
                    int floor = left / pow;
                    int val = freqs.get(pow);
                    if(floor < val) {
                        left -= floor * pow;
                        newfreq.put(pow, val - floor);
                    }else {
                        left -= pow * val;
                    }
                }
                freqs = newfreq;
                if(freqs.size() == 0) break;
                count++;
            }
            out.println(count);
        }
        in.close();
        out.close();
    }
}
