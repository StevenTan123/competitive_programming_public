import java.util.*;
import java.io.*;

public class squid_game_ripoff {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int n = Integer.parseInt(in.readLine());
		int[][] ranges = new int[n][2];
        Event[] events = new Event[n * 2];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            ranges[i][0] = Integer.parseInt(line.nextToken());
            ranges[i][1] = Integer.parseInt(line.nextToken());
            events[i * 2] = new Event(ranges[i][0], ranges[i][1] - ranges[i][0] + 1, 0);
            events[i * 2 + 1] = new Event(ranges[i][1] + 1, ranges[i][1] - ranges[i][0] + 1, 1);
        }
        Arrays.sort(events);
        int prev = -1;
        long cur = 0;
        long prod = 1;
        long prod_less = 1;
        double ans = 0;
        for(int i = 0; i < n * 2; i++) {
            if(events[i].x != prev) {
                if(prev != -1) {
                    ans += (double)cur / prod * (events[i].x - prev);
                    out.println(cur + " " + prod + " " + (events[i].x - prev));
                }
                prev = events[i].x;
            }
            if(events[i].t == 0) {
                cur = cur * (events[i].l - 1) + prod_less;
                prod *= events[i].l;
                prod_less *= events[i].l - 1;
            }else {
                prod /= events[i].l;
                prod_less /= events[i].l - 1;
                cur = (cur - prod_less) / (events[i].l - 1);
            }
        }
        out.println(ans);
        int num_trials = 100000;
        long sum = 0;
        for(int i = 0; i < num_trials; i++) {
            sum += run_trial(ranges);
        }
        out.println((double)sum / num_trials);
		in.close();
		out.close();
	}
    static int run_trial(int[][] ranges) {
        Random rand = new Random();
        HashSet<Integer> unique = new HashSet<Integer>();
        HashSet<Integer> seen = new HashSet<Integer>();
        for(int i = 0; i < ranges.length; i++) {
            int num = rand.nextInt(ranges[i][1] - ranges[i][0] + 1) + ranges[i][0];
            if(seen.contains(num)) {
                unique.remove(num);
            }else {
                unique.add(num);
                seen.add(num);
            }
        }
        return unique.size();
    }
    static class Event implements Comparable<Event> {
        int x, l, t;
        Event(int xx, int ll, int tt) {
            x = xx;
            l = ll;
            t = tt;
        }
        @Override
        public int compareTo(Event o) {
            return x - o.x;
        }
    }
}
