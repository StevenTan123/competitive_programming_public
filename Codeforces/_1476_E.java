import java.util.*;
import java.io.*;

public class _1476_E {
    static int[] pow2 = new int[5];
    static int[] pow27 = new int[5];
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer> ans;
    static int n, m, k;
    public static void main(String[] args) throws IOException {
        pow2[0] = 1;
        pow27[0] = 1;
        for(int i = 1; i < 5; i++) {
            pow2[i] = pow2[i - 1] * 2;
            pow27[i] = pow27[i - 1] * 27;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        n = Integer.parseInt(line.nextToken());
        m = Integer.parseInt(line.nextToken());
        k = Integer.parseInt(line.nextToken());
        graph = new ArrayList[n];
        String[] patterns = new String[n];
        int[] pat_hash = new int[n];
        int[] pat_set = new int[pow27[k]];
        Arrays.fill(pat_set, -1);
        for(int i = 0; i < n; i++) {
            patterns[i] = in.readLine();
            int hash = hash(patterns[i], 31);
            pat_hash[i] = hash;
            pat_set[hash] = i;
            graph[i] = new ArrayList<Integer>();
        }
        boolean possible = true;
        for(int i = 0; i < m; i++) {
            line = new StringTokenizer(in.readLine());
            String s = line.nextToken();
            int mt = Integer.parseInt(line.nextToken()) - 1;
            boolean match = true;
            for(int j = 0; j < k; j++) {
                if(patterns[mt].charAt(j) != '_' && patterns[mt].charAt(j) != s.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if(!match) {
                possible = false;
                break;
            }
            for(int j = 0; j < pow2[k]; j++) {
                int hash = hash(s, j);
                if(hash != pat_hash[mt] && pat_set[hash] != -1) {
                    graph[mt].add(pat_set[hash]);
                }
            }
        }
        if(!possible) {
            out.println("NO");
        }else {
            int[] visited = new int[n];
            boolean cyclic = false;
            for(int i = 0; i < n; i++) {
                if(visited[i] == 0) {
                    cyclic |= cyclic(i, visited);
                }
            }
            if(cyclic) {
                out.println("NO");
            }else {
                ans = new ArrayList<Integer>();
                toposort();
                StringBuilder res = new StringBuilder();
                for(int i = n - 1; i >= 0; i--) {
                    res.append(ans.get(i));
                    res.append(' ');
                }
                out.println("YES");
                out.println(res.toString());
            }
        }
        in.close();
        out.close();
    }
    static void toposort() {
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, visited);
            }
        }
    }
    static void dfs(int node, boolean[] visited) {
        visited[node] = true;
        for(int nei : graph[node]) {
            if(!visited[nei]) {
                dfs(nei, visited);
            }
        }
        ans.add(node + 1);
    }
    static boolean cyclic(int node, int[] visited) {
        if(visited[node] == 1) {
            return true;
        }else if(visited[node] == 2) {
            return false;
        }
        visited[node] = 1;
        boolean cyclic = false;
        for(int nei : graph[node]) {
            cyclic |= cyclic(nei, visited);
        }
        visited[node] = 2;
        return cyclic;
    }
    static int hash(String s, int bitmask) {
        int hash = 0;
        for(int j = 0; j < s.length(); j++) {
            if(bitmask % 2 == 1) {
                char c = s.charAt(j);
                if(c != '_') {
                    hash += pow27[j] * ((int) c - 96);
                }
            }
            bitmask /= 2;
        }
        return hash;
    }
}
