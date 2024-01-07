import java.util.*;
import java.io.*;

public class cowjog_gold {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cowjog.in"));
        PrintWriter out = new PrintWriter("cowjog.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int t = Integer.parseInt(line.nextToken());
        TreeSet<Lane> lanes = new TreeSet<Lane>();
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            long end = Integer.parseInt(line.nextToken()) + (long)t * Integer.parseInt(line.nextToken());
            Lane cur = new Lane(end, i);
            Lane lower = lanes.lower(cur);
            if(lower != null) {
                lanes.remove(lower);
            }
            lanes.add(cur);
        }
        out.println(lanes.size());
        in.close();
        out.close();
    }
    static class Lane implements Comparable<Lane> {
        long end;
        int id;
        Lane(long e, int i) {
            end = e;
            id = i;
        }
        @Override
        public int compareTo(Lane o) {
            if(end > o.end) {
                return 1;
            }else if(end < o.end) {
                return -1;
            }else {
                return o.id - id;
            }
        }
    }
}
