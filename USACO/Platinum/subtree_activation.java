import java.util.*;
import java.io.*;

public class subtree_activation {
    // A is min moves start off end off
    // B is min moves start off end on (equivalent to start on end off)
    // S is the size of subtree
    static long[] A, B, S;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int N = Integer.parseInt(in.readLine());
        ArrayList<Integer>[] tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 1; i < N; i++) {
            int p = Integer.parseInt(line.nextToken()) - 1;
            tree[p].add(i);
        }

        A = new long[N];
        B = new long[N];
        S = new long[N];
        dfs(tree, 0);
        out.println(A[0]);

        in.close();
        out.close();
    }

    static void dfs(ArrayList<Integer>[] tree, int node) {
        long[] sort = new long[tree[node].size()];
        S[node] = 1;
        long sum = 0;
        for (int i = 0; i < tree[node].size(); i++) {
            int nei = tree[node].get(i);
            dfs(tree, nei);
            S[node] += S[nei];
            sum += A[nei];
            sort[i] = A[nei] - B[nei] + S[nei];
        }
        A[node] = sum + 2 * S[node];
        B[node] = sum + S[node];
        Arrays.sort(sort);

        if (tree[node].size() == 0) {
            return;
        } else if (tree[node].size() == 1) {
            A[node] = B[tree[node].get(0)] + S[node] + 1;
        } else {
            A[node] -= sort[sort.length - 1] + sort[sort.length - 2];
        }

        for (int nei : tree[node]) {
            A[node] = Math.min(A[node], sum + S[node] * 2 - S[nei] * 2);
        }

        B[node] -= sort[sort.length - 1];
    }
}
