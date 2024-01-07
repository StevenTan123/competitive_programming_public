import java.util.*;
import java.io.*;

public class _321_C {
    static ArrayList<Integer>[] tree, centroid_tree;
    static int[] sizes, depths;
    static boolean[] removed;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        n = Integer.parseInt(in.readLine());
        tree = new ArrayList[n];
        centroid_tree = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
            centroid_tree[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < n - 1; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int v1 = Integer.parseInt(line.nextToken()) - 1;
            int v2 = Integer.parseInt(line.nextToken()) - 1;
            tree[v1].add(v2);
            tree[v2].add(v1);
        }
        sizes = new int[n];
        depths = new int[n];
        removed = new boolean[n];
        gen_centroid(0, 0);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            if(depths[i] > 25) {
                sb = new StringBuilder("Impossible!");
                break;
            }else {
                sb.append((char)(depths[i] + 65));
                sb.append(' ');
            }
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static int find_sizes(int node, int prev) {
        int sum = 1;
        for(int nei : tree[node]) {
            if(nei != prev && !removed[nei]) {
                sum += find_sizes(nei, node);
            }
        }
        return sizes[node] = sum;
    }
    static int find_centroid(int node, int prev, int size) {
        for(int nei : tree[node]) {
            if(nei != prev && !removed[nei]) {
                if(sizes[nei] * 2 > size) {
                    return find_centroid(nei, node, size);
                }
            }
        }
        return node;
    }
    static int gen_centroid(int node, int depth) {
        int centroid = find_centroid(node, -1, find_sizes(node, -1));
        removed[centroid] = true;
        for(int nei : tree[centroid]) {
            if(!removed[nei]) {
                centroid_tree[centroid].add(gen_centroid(nei, depth + 1));
            }
        }
        depths[centroid] = depth;
        return centroid;
    }
}
