import java.util.*;
import java.io.*;

public class _1787_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int x = Integer.parseInt(line.nextToken());
            int xor = 0;
            for (int i = 1; i <= n; i++) {
                xor ^= i;
            }
            if (k == 1 && xor == x) {
                out.println("YES");
                StringBuilder sb = new StringBuilder();
                sb.append(n);
                for (int i = 1; i <= n; i++) {
                    sb.append(' ');
                    sb.append(i);
                }
                out.println(sb.toString());
            } else if (k % 2 == 0 && xor == 0 || k % 2 == 1 && xor == x) {
                int non_xor = 0;
                HashSet<Integer> non = new HashSet<Integer>();
                ArrayList<Sub> subs = new ArrayList<Sub>();
                for (int i = 1; i <= n; i++) {
                    if (i == x) {
                        non.add(i);
                        continue;
                    }
                    int other = x ^ i;
                    if (other > i && other <= n) {
                        Sub cur = new Sub();
                        cur.a.add(i);
                        cur.a.add(other);
                        subs.add(cur);
                    } else if (other > n) {
                        non.add(i);
                        non_xor ^= i;
                    }
                }
                Sub cur = new Sub();
                for (int val : non) {
                    cur.a.add(val);
                }
                subs.add(cur);
                if (subs.size() >= k) {
                    out.println("YES");

                    ArrayList<Integer> last = new ArrayList<Integer>();
                    for (int i = 0; i < subs.size(); i++) {
                        if (i < k - 1) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(subs.get(i).a.size());
                            for (int val : subs.get(i).a) {
                                sb.append(' ');
                                sb.append(val);
                            }
                            out.println(sb.toString());
                        } else {
                            for (int val : subs.get(i).a) {
                                last.add(val);
                            }
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(last.size());
                    for (int val : last) {
                        sb.append(' ');
                        sb.append(val);
                    }
                    out.println(sb.toString());
                } else {
                    out.println("NO");
                }
            } else {
                out.println("NO");
            }
        }
        in.close();
        out.close();
    }

    static class Sub {
        ArrayList<Integer> a;

        Sub() {
            a = new ArrayList<Integer>();
        }
    }
}
