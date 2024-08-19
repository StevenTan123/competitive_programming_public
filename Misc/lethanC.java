import java.util.*;
import java.io.*;

public class lethanC {
    static int n;
    static int[] dists;
    static int[] res;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        dists = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            dists[i] = Integer.parseInt(line.nextToken());
        }
        res = null;
        gen_perms(new ArrayList<Integer>());
        if(res == null) {
            out.println("NO");
        }else {
            out.println("YES");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 2 * n; i++) {
                sb.append(res[i]);
                sb.append(' ');
            }
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static void gen_perms(ArrayList<Integer> perm) {
        if(res != null) {
            return;
        }
        if(perm.size() == n) {
            possible(perm);
            return;
        }
        for(int i = 0; i < n; i++) {
            if(!perm.contains(i)) {
                perm.add(i);
                gen_perms(perm);
                perm.remove(perm.size() - 1);
            }
        }
    }
    static void possible(ArrayList<Integer> perm) {
        int[] arr = new int[2 * n];
        int p = 0;
        boolean possible = true;
        for(int i = 0; i < 2 * n; i++) {
            if(p >= n) {
                break;
            }
            if(arr[i] == 0) {
                int cur = perm.get(p);
                arr[i] = cur + 1;
                if(i + dists[cur] < 2 * n && arr[i + dists[cur]] == 0) {
                    arr[i + dists[cur]] = cur + 1;
                }else {
                    possible = false;
                    break;
                }
                p++;
            }
        }
        if(possible) {
            res = arr;
        }
    }
}
