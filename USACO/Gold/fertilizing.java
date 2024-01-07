import java.util.*;
import java.io.*;

public class fertilizing {
    static int[] a, time, depth;
    static long[] fert, res_0;
    static int T;
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        T = Integer.parseInt(line.nextToken());

        ArrayList<Integer>[] tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        a = new int[N];
        for (int i = 1; i < N; i++) {
            line = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(line.nextToken()) - 1;
            tree[p].add(i);
            a[i] = Integer.parseInt(line.nextToken());
        }

        time = new int[N];
        depth = new int[N];
        fert = new long[N];

        dfs_setup(tree, 0);

        res_0 = new long[N];
        dfs_0(tree, 0);

        int min_time = 2 * (N - 1);
        if (T == 0) {
            out.println(min_time + " " + res_0[0]);
        } else {
            min_time -= depth[0];
            out.println(min_time + " " + dfs_1(tree, 0));
        }

        in.close();
        out.close();
    }

    static long dfs_0(ArrayList<Integer>[] tree, int cur) {
        if (tree[cur].size() == 0) {
            return 0;
        }

        long res = 0;
        Integer[] children = new Integer[tree[cur].size()];
        int p = 0;
        for (int nei : tree[cur]) {
            res += dfs_0(tree, nei);
            children[p] = nei;
            p++;
        }
        Arrays.sort(children, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                long val1 = fert[b] * (time[a] + 2);
                long val2 = fert[a] * (time[b] + 2);
                if (val1 < val2) {
                    return -1;
                } else if (val2 < val1) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        int t = 1;
        for (int i = 0; i < children.length; i++) {
            res += t * fert[children[i]];
            t += 2 + time[children[i]];
        }

        res_0[cur] = res;
        return res;
    }

    static long dfs_1(ArrayList<Integer>[] tree, int cur) {
        if (tree[cur].size() == 0) {
            return 0;
        }

        Integer[] children = new Integer[tree[cur].size()];
        int p = 0;
        for (int nei : tree[cur]) {
            children[p] = nei;
            p++;
        }

        Arrays.sort(children, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                long val1 = fert[b] * (time[a] + 2);
                long val2 = fert[a] * (time[b] + 2);
                if (val1 < val2) {
                    return -1;
                } else if (val2 < val1) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        long[] dfs_1 = new long[p];
        Arrays.fill(dfs_1, -1);
        for (int i = 0; i < p; i++) {
            if (depth[children[i]] + 1 == depth[cur]) {
                dfs_1[i] = dfs_1(tree, children[i]);
            }
        }

        int[] tp = new int[p];
        long[] fertp = new long[p];
        for (int i = 0; i < p; i++) {
            int prev_t = i > 0 ? tp[i - 1] : 1;
            tp[i] = prev_t + time[children[i]] + 2;
            fertp[i] = (i > 0 ? fertp[i - 1] : 0) + fert[children[i]];
        }

        long min_fert = Long.MAX_VALUE;
        for (int i = 0; i < p; i++) {
            if (dfs_1[i] != -1) {
                int t_suff = tp[p - 1] - tp[i];
                long fert_suff = fertp[p - 1] - fertp[i];
                long cur_fert = res_0[cur] + t_suff * fert[children[i]] - fert_suff * (time[children[i]] + 2) 
                    - res_0[children[i]] + dfs_1[i];
                if (cur_fert < min_fert) {
                    min_fert = cur_fert;
                }
            }
        }

        return min_fert;
    }

    static void dfs_setup(ArrayList<Integer>[] tree, int cur) {
        fert[cur] = a[cur];
        for (int nei : tree[cur]) {
            dfs_setup(tree, nei);
            time[cur] += time[nei] + 2;
            depth[cur] = Math.max(depth[cur], depth[nei] + 1);
            fert[cur] += fert[nei];
        }
    }
}