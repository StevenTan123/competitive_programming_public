import java.util.*;
import java.io.*;

public class goldmine2 {
    public static void main(String[] args) throws IOException {
        //BufferedReader in = new BufferedReader(new FileReader("gold_mine_chapter_1_input.txt"));
        //PrintWriter out = new PrintWriter("output.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[] c = new int[n];
            line = new StringTokenizer(in.readLine());
            ArrayList<Integer>[] tree = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                c[i] = Integer.parseInt(line.nextToken());
                tree[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i < n - 1; i++) {
                line = new StringTokenizer(in.readLine());
                int v1 = Integer.parseInt(line.nextToken()) - 1;
                int v2 = Integer.parseInt(line.nextToken()) - 1;
                tree[v1].add(v2);
                tree[v2].add(v1);
            }
            int sum = 0;

            while(k > 1) {

            }
            String res = "Case #" + t + ": ";

        }
        in.close();
        out.close();
    }
    static int bestSingle(ArrayList<Integer>[] tree, int[] c) {
        int[] subtrees = findLengths(tree, c);
        int maxInd = 0;
        for(int i = 1; i < subtrees.length; i++) {
            if(subtrees[i] > subtrees[maxInd]) {
                maxInd = i;
            }
        }
        if(subtrees.length > 0) {
            remove
        }
        return -1;
    }
    static int[] findLengths(ArrayList<Integer>[] tree, int[] c) {
        int[] subtrees = new int[tree[0].size()];
        for(int i = 0; i < tree[0].size(); i++) {
            subtrees[i] = dfs(tree, new HashSet<Integer>(), c, tree[0].get(i), 0);
        }
        Arrays.sort(subtrees);
        return subtrees;
    }
    static int dfs(ArrayList<Integer>[] tree, HashSet<Integer> visited, int[] c, int node, int sum) {
        if(visited.contains(node) || node == 0) {
            return 0;
        }
        visited.add(node);
        sum += c[node];
        int max = 0;
        for(int nei : tree[node]) {
            max = Math.max(max, dfs(tree, visited, c, nei, sum));
        }
        if(tree[node].size() == 1) {
            return sum;
        }else {
            return max;
        }
    }
    static int remove(ArrayList<Integer>[] tree, HashSet<Integer> visited, int[] c, int node, int sum) {
        if(visited.contains(node) || node == 0) {
            return 0;
        }
        visited.add(node);
        sum += c[node];
        int max = 0;
        int maxNode = -1;
        for(int nei : tree[node]) {
            int cur = dfs(tree, visited, c, nei, sum);
            if(cur > max) {
                max = cur;
                maxNode = nei;
            }
        }
        if(tree[node].size() == 1) {
            return sum;
        }else {
            if(maxNode > -1) {
                tree[node].remove(maxNode);
                tree[maxNode].remove(node);
            }
            return max;
        }
    }
}