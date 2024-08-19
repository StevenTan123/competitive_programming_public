import java.util.*;

public class Kuhn {
    //n is size of first set of vertices, k of the second
    //graph length n, graph[i] stores vertices from second part connected from vertex i of the first part
    //mt is length k, mt[i] stores vertex from first part connected to vertex i of second part in final matching
    //used is length n, used[i] stores if vertex i is used
    public static boolean try_kuhn(int v, ArrayList<Integer>[] graph, int[] mt, boolean[] used) {
        if(used[v]) {
            return false;
        }
        used[v] = true;
        for(int nei : graph[v]) {
            if(mt[nei] == -1 || try_kuhn(mt[nei], graph, mt, used)) {
                mt[nei] = v;
                return true;
            }
        }
        return false;
    }
    public static void kuhn(int n, int k, int[] mt, ArrayList<Integer>[] graph) {
        Arrays.fill(mt, -1);
        for(int v = 0; v < n; v++) {
            try_kuhn(v, graph, mt, new boolean[n]);
        }
    }
}
