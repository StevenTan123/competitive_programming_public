import java.util.*;
import java.io.*;

public class cowrect {
    static int n, max, area;
    static int[][] points;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cowrect.in"));
        PrintWriter out = new PrintWriter("cowrect.out");
        n = Integer.parseInt(in.readLine());
        points = new int[n][3];
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            points[i][0] = Integer.parseInt(line.nextToken());
            points[i][1] = Integer.parseInt(line.nextToken());
            if(line.nextToken().equals("G")) {
                points[i][2] = 1;
            }
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    if(a[2] != b[2]) {
                        return b[2] - a[2];
                    }
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });
        max = 0;
        area = Integer.MAX_VALUE;
        int prevx = -1;
        for(int i = 0; i < n; i++) {
            if(points[i][0] > prevx) {
                prevx = points[i][0];
                find_rect(i);
            }
        }
        out.println(max);
        out.println(area);
        in.close();
        out.close();
    }
    static void find_rect(int start) {
        TreeSet<Cow> active = new TreeSet<Cow>();
        for(int i = start; i < n; i++) {
            active.add(new Cow(points[i][0], points[i][1], points[i][2]));
            int prevH = -1;
            int count = 0;
            int prevG = -1;
            for(Cow c : active) {
                if(c.t == 0) {
                    if(c.y > prevG) {
                        count++;
                        if(prevH == -1) {
                            prevH = c.y;
                        }
                    }
                    if(prevH != -1) {
                        int cur_area = (c.y - prevH) * (points[i][0] - points[start][0]);
                        if(count > max) {
                            max = count;
                            area = cur_area;
                        }else if(count == max) {
                            area = Math.min(area, cur_area);
                        }
                    }
                }else {
                    count = 0;
                    prevH = -1;
                    prevG = c.y;
                }
            }
        }
    }
    static class Cow implements Comparable<Cow> {
        int x, y, t;
        Cow(int xx, int yy, int tt) {
            x = xx;
            y = yy;
            t = tt;
        }
        @Override
        public int compareTo(Cow o) {
            if(y == o.y) {
                if(t != o.t) {
                    return o.t - t;
                }
                return x - o.x;
            }
            return y - o.y;
        }
    }
}
