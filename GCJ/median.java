import java.util.*;
import java.io.*;

public class median {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(in.readLine());
        int tt = Integer.parseInt(line.nextToken());
        int n = Integer.parseInt(line.nextToken());
        int q = Integer.parseInt(line.nextToken());
        for(int t = 1; t <= tt; t++) {
            ArrayList<Integer> sorted = new ArrayList<Integer>();
            sorted.add(1);
            sorted.add(2);
            for(int i = 3; i <= n; i++) {
                int l = 0;
                int r = sorted.size() - 1;
                int res = -1;
                while(l < r) {
                    int mid = (l + r) / 2;
                    int median = query(sorted.get(mid), sorted.get(r), i, in);
                    if(median < 0) return;
                    if(median == sorted.get(mid)) {
                        if(l == mid) {
                            res = l;
                            break;
                        }
                        r = mid;
                    }else if(median == sorted.get(r)) {
                        res = r + 1;
                        break;
                    }else {
                        if(l == mid) {
                            res = l + 1;
                            break;
                        }
                        l = mid;
                    }
                }
                sorted.add(res, i);
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < sorted.size(); i++) {
                sb.append(sorted.get(i));
                if(i < sorted.size() - 1) sb.append(' ');
            }
            System.out.println(sb.toString());
            System.out.flush();
            int ret = Integer.parseInt(in.readLine());
            if(ret < 0) return;
        }
        in.close();
    }
    static int query(int a, int b, int c, BufferedReader in) throws IOException {
        System.out.println(a + " " + b + " " + c);
        System.out.flush();
        return Integer.parseInt(in.readLine());
    }
}
