import java.util.*;
import java.io.*;

public class _1525_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[][] x = new int[n][2];
            int[] dir = new int[n];
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                x[i][0] = Integer.parseInt(line.nextToken());
                x[i][1] = i;
            }
            Arrays.sort(x, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            });
            line = new StringTokenizer(in.readLine());
            for(int i = 0; i < n; i++) {
                char c = line.nextToken().charAt(0);
                if(c == 'R') {
                    dir[i] = 1;
                }
            }
            int[] res = new int[n];
            Arrays.fill(res, -1);
            find_times(x, dir, 0, res, m);
            find_times(x, dir, 1, res, m);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(res[i]);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static void find_times(int[][] x, int[] dir, int parity, int[] res, int m) {
        ArrayList<Robot> left = new ArrayList<Robot>();
        for(int i = 0; i < x.length; i++) {
            if(x[i][0] % 2 == parity) {
                Robot top = left.size() > 0 ? left.get(left.size() - 1) : null;
                if(top != null && top.d == 1 && dir[x[i][1]] == 0) {
                    int time = (x[i][0] - top.x) / 2;
                    res[top.i] = time;
                    res[x[i][1]] = time;
                    left.remove(left.size() - 1);
                }else {
                    left.add(new Robot(x[i][0], dir[x[i][1]], x[i][1]));
                }
            }
        }
        for(int i = 1; i < left.size(); i += 2) {
            Robot cur = left.get(i);
            Robot prev = left.get(i - 1);
            if(cur.d == 1) {
                break;
            }
            int time = prev.x + (cur.x - prev.x) / 2;
            res[prev.i] = time;
            res[cur.i] = time;
        }
        for(int i = left.size() - 2; i >= 0; i -= 2) {
            Robot cur = left.get(i);
            Robot prev = left.get(i + 1);
            if(cur.d == 0) {
                break;
            }
            int time = m - prev.x + (prev.x - cur.x) / 2;
            res[prev.i] = time;
            res[cur.i] = time;
        }
        int start = left.size();
        for(int i = 0; i < left.size(); i++) {
            if(left.get(i).d == 1) {
                start = i;
                break;
            }
        }
        int end = left.size() - start;
        if(start % 2 == 1 && end % 2 == 1) {
            Robot one = left.get(start - 1);
            Robot two = left.get(start);
            int time = (m + m - two.x + one.x) / 2;
            res[one.i] = time;
            res[two.i] = time;
        }
    }
    static class Robot {
        int x, d, i;
        Robot(int xx, int dd, int ii) {
            x = xx;
            d = dd;
            i = ii;
        }
    }
}
