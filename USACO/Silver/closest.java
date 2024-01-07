import java.util.*;
import java.io.*;

public class closest {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int k = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int n = Integer.parseInt(line.nextToken());
        int[][] fields = new int[k][2];
        for(int i = 0; i < k; i++) {
            line = new StringTokenizer(in.readLine());
            fields[i][0] = Integer.parseInt(line.nextToken());
            fields[i][1] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(fields, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int[] f = new int[m + 2];
        for(int i = 0; i < m; i++) {
            f[i] = Integer.parseInt(in.readLine());
        }
        f[m] = -1000000005;
        f[m + 1] = 2000000005;
        Arrays.sort(f);
        int p = 0;
        long[] best = new long[2 * (m + 1)];
        for(int i = 1; i < m + 2; i++) {
            ArrayList<Event> events = new ArrayList<Event>();
            long total = 0;
            while(p < k && fields[p][0] < f[i]) {
                int dist = Math.min(fields[p][0] - f[i - 1], f[i] - fields[p][0]);
                Event start = new Event(0, fields[p][0] - dist, fields[p][1], null);
                events.add(start);
                events.add(new Event(1, fields[p][0] + dist, fields[p][1], start));
                total += fields[p][1];
                p++;
            }
            Collections.sort(events);
            TreeSet<Event> active = new TreeSet<Event>();
            long cur = 0;
            long max = 0;
            for(Event e : events) {
                if(e.type == 0) {
                    cur += e.weight;
                    active.add(e);
                }else {
                    cur -= e.weight;
                    active.remove(e.start);
                }
                max = Math.max(max, cur);
            }
            best[(i - 1) * 2] = max;
            best[(i - 1) * 2 + 1] = total - max;
        }
        Arrays.sort(best);
        long res = 0;
        for(int i = 2 * (m + 1) - 1; i >= Math.max(0, 2 * (m + 1) - n); i--) {
            res += best[i];
        }
        out.println(res);
        in.close();
        out.close();
    }
    static class Event implements Comparable<Event> {
        int type, pos, weight;
        Event start;
        Event(int t, int p, int w, Event s) {
            type = t;
            pos = p;
            weight = w;
            start = s;
        }
        @Override
        public int compareTo(Event o) {
            if(pos == o.pos) {
                return o.type - type;
            }
            return pos - o.pos;
        }
    }
}
