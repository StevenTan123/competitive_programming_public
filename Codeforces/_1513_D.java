import java.util.*;
import java.io.*;

public class _1513_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int p = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            int[][] sorted = new int[n][2];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                sorted[i][0] = a[i];
                sorted[i][1] = i;
            }
            Arrays.sort(sorted, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            });
            int[] parents = new int[n];
            int[] dists = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            long res = 0;
            for (int i = 0; i < n; i++) {
                if (sorted[i][0] >= p) {
                    break;
                }
                int j = sorted[i][1] - 1;
                while (j >= 0) {
                    if (a[j] % sorted[i][0] != 0 || find(parents, sorted[i][1]) == find(parents, j)) {
                        break;
                    }
                    union(parents, dists, sorted[i][1], j);
                    res += sorted[i][0];
                    j--;
                }
                j = sorted[i][1] + 1;
                while (j < n) {
                    if (a[j] % sorted[i][0] != 0 || find(parents, sorted[i][1]) == find(parents, j)) {
                        break;
                    }
                    union(parents, dists, sorted[i][1], j);
                    res += sorted[i][0];
                    j++;
                }
            }
            HashSet<Integer> comps = new HashSet<Integer>();
            for (int i = 0; i < n; i++) {
                comps.add(find(parents, i));
            }
            res += (long) (comps.size() - 1) * p;
            out.println(res);
        }
        in.close();
        out.close();
    }

    static int find(int[] parents, int node) {
        if (parents[node] == node) {
            return node;
        }
        parents[node] = find(parents, parents[node]);
        return parents[node];
    }

    static void union(int[] parents, int[] dists, int a, int b) {
        int roota = find(parents, a);
        int rootb = find(parents, b);
        if (dists[a] > dists[b]) {
            parents[rootb] = roota;
        } else if (dists[b] > dists[a]) {
            parents[roota] = rootb;
        } else {
            parents[rootb] = roota;
            dists[roota] += 1;
        }
    }
}
