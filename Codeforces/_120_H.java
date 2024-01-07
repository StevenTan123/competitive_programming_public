import java.util.*;
import java.io.*;

public class _120_H {
    static int kcount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int n = Integer.parseInt(in.readLine());
        String[] words = new String[n];
        HashMap<String, Integer> shorts = new HashMap<String, Integer>();
        HashSet<Integer>[] graph = new HashSet[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new HashSet<Integer>();
        }
        for(int i = 0; i < n; i++) {
            words[i] = in.readLine();
            genShorts(i, words[i], shorts, new ArrayList<Integer>(), graph);
        }
        String[] reverse = new String[kcount];
        for(String s : shorts.keySet()) {
            reverse[shorts.get(s)] = s;
        }
        int[] mt = new int[kcount];
        Arrays.fill(mt, -1);
        for(int v = 0; v < n; v++) {
            try_kuhn(v, graph, mt, new boolean[n]);
        }
        String[] res = new String[n];
        int count = 0;
        for(int i = 0; i < kcount; i++) {
            if(mt[i] > -1) {
                count++;
                res[mt[i]] = reverse[i];
            }
        }
        if(count == n) {
            for(int i = 0; i < n; i++) {
                out.println(res[i]);
            }
        }else {
            out.println(-1);
        }
        in.close();
        out.close();
    }
    static void genShorts(int v, String s, HashMap<String, Integer> shorts, ArrayList<Integer> indices, HashSet<Integer>[] graph) {
        if(indices.size() > 0) {
            String curshort = "";
            for(int index : indices) {
                curshort += s.charAt(index);
            }
            if(!shorts.containsKey(curshort)) {
                shorts.put(curshort, kcount);
                kcount++;
            }
            graph[v].add(shorts.get(curshort));
        }
        if(indices.size() >= 4) {
            return;
        }
        int start = indices.size() > 0 ? indices.get(indices.size() - 1) : -1;
        for(int i = start + 1; i < s.length(); i++) {
            indices.add(i);
            genShorts(v, s, shorts, indices, graph);
            indices.remove(indices.size() - 1);
        }
    }
    public static boolean try_kuhn(int v, HashSet<Integer>[] graph, int[] mt, boolean[] used) {
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
}
