import java.util.*;
import java.io.*;

public class apple_catching {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        Event[] sorted = new Event[n];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int type = Integer.parseInt(line.nextToken());
            int x = Integer.parseInt(line.nextToken());
            int y = Integer.parseInt(line.nextToken());
            int num = Integer.parseInt(line.nextToken());
            sorted[i] = new Event(type, x - y, x + y, num);
        }
        Arrays.sort(sorted, new Comparator<Event>() {
            @Override
            public int compare(Event a, Event b) {
                if(a.x == b.x) {
                    if(a.t == b.t) {
                        return a.y - b.y;
                    }
                    return a.t - b.t;
                }
                return a.x - b.x;
            }
        });
        TreeSet<Event> active = new TreeSet<Event>(new Comparator<Event>() {
            @Override
            public int compare(Event a, Event b) {
                if(a.y == b.y) {
                    return a.x - b.x;
                }
                return a.y - b.y;
            }
        });
        int res = 0;
        for(int i = 0; i < n; i++) {
            if(sorted[i].t == 1) {
                active.add(sorted[i]);
            }else {
                Event lower = active.lower(sorted[i]);
                while(lower != null && lower.n <= sorted[i].n) {
                    sorted[i].n -= lower.n;
                    res += lower.n;
                    active.remove(lower);
                    lower = active.lower(lower);
                }
                if(lower != null) {
                    lower.n -= sorted[i].n;
                    res += sorted[i].n;
                }
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static class Event {
        int t, x, y, n;
        Event(int type, int xx, int yy, int nn) {
            t = type;
            x = xx;
            y = yy;
            n = nn;
        }
    }
}