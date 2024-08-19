import java.util.*;
import java.io.*;

public class DSU {
    static int find(int[] parents, int node) {
        if(parents[node] == node) {
            return node;
        }
        parents[node] = find(parents, parents[node]);
        return parents[node];
    }
    static void union(int[] parents, int[] dists, int a, int b) {
        int roota = find(parents, a);
        int rootb = find(parents, b);
        if(dists[a] > dists[b]) {
            parents[rootb] = roota;
        }else if(dists[b] > dists[a]) {
            parents[roota] = rootb;
        }else {
            parents[rootb] = roota;
            dists[roota] += 1;
        }
    }
}
