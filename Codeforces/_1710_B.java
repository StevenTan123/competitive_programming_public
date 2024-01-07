import java.util.*;
import java.io.*;

public class _1710_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());

        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            long[][] rains = new long[n][2];
            Event[] events = new Event[3 * n];
            for (int i = 0; i < n; i++) {
                line = new StringTokenizer(in.readLine());
                rains[i][0] = Integer.parseInt(line.nextToken());
                rains[i][1] = Integer.parseInt(line.nextToken());
                events[i * 3] = new Event(0, rains[i][0] - rains[i][1], i);
                events[i * 3 + 1] = new Event(1, rains[i][0], i);
                events[i * 3 + 2] = new Event(2, rains[i][0] + rains[i][1], i);
            }
            Arrays.sort(events);
            ArrayList<Pair> peaks = new ArrayList<Pair>();
            long height = 0;
            int slope = 0;
            for (int i = 0; i < 3 * n; i++) {
                long dist = i > 0 ? events[i].pos - events[i - 1].pos : 0;
                height += slope * dist;
                if (events[i].type == 0) {
                    slope++;
                } else if (events[i].type == 1) {
                    if (height > m) {
                        peaks.add(new Pair(events[i].pos, height - m));
                    }
                    slope -= 2;
                } else {
                    slope++;
                }
            }

            boolean[] possible = new boolean[n];
            Arrays.fill(possible, true);
            ArrayList<Event> sorted = new ArrayList<Event>();
            for (Pair peak : peaks) {
                Event add = new Event(0, peak.a, -1);
                add.height = peak.b;
                sorted.add(add);
            }
            for (int i = 0; i < n; i++) {
                sorted.add(new Event(1, rains[i][0], i));
            }
            Collections.sort(sorted);

            long min_start = Long.MAX_VALUE;
            for (int i = 0; i < sorted.size(); i++) {
                Event cur = sorted.get(i);
                if (cur.type == 0) {
                    min_start = Math.min(min_start, cur.pos - cur.height);
                } else {
                    if (cur.pos - rains[cur.ind][1] > min_start) {
                        possible[cur.ind] = false;
                    }
                }
            }
            long max_end = Long.MIN_VALUE;
            for (int i = sorted.size() - 1; i >= 0; i--) {
                Event cur = sorted.get(i);
                if (cur.type == 0) {
                    max_end = Math.max(max_end, cur.pos + cur.height);
                } else {
                    if (cur.pos + rains[cur.ind][1] < max_end) {
                        possible[cur.ind] = false;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(possible[i] ? '1' : '0');
            }
            out.println(sb.toString());
		}

        in.close();
        out.close();
    }
    static class Event implements Comparable<Event> {
        int type, ind;
        long pos, height;
        Event(int t, long p, int i) {
            type = t;
            pos = p;
            ind = i;
        }
        @Override
        public int compareTo(Event o) {
            if (pos == o.pos) {
                return type - o.type;
            }
            if (pos > o.pos) {
                return 1;
            } else if (pos < o.pos) {
                return -1;
            }
            return 0;
        }
    }
    static class Pair {
        long a;
        long b;
        Pair(long aa, long bb) {
            a = aa;
            b = bb;
        }
    }
}
