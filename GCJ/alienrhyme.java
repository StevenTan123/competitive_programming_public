import java.util.*;
import java.io.*;

public class alienrhyme {
    static int counter;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            counter = 1;
            int n = Integer.parseInt(in.readLine());
            ArrayList<String> strs = new ArrayList<String>();
            for(int i = 0; i < n; i++) {
                String str = in.readLine();
                StringBuilder sb = new StringBuilder();
                for(int j = str.length() - 1; j >= 0; j--) sb.append(str.charAt(j));
                strs.add(sb.toString());
            }
            ArrayList<Integer>[] trie = new ArrayList[50005];
            for(int i = 0; i < 50005; i++) trie[i] = new ArrayList<Integer>();
            boolean[] end = new boolean[50005];
            buildtrie(trie, end, strs, 0, 0);
            int unpaired = dfs(trie, end, 0);
            String res = "Case #" + t + ": ";
            out.println(res + (n - unpaired));
        }
        in.close();
        out.close();
    }
    static int dfs(ArrayList<Integer>[] trie, boolean[] end, int curnode) {
        int sum = 0;
        for(int nei : trie[curnode]) {
            sum += dfs(trie, end, nei);
        }
        if(curnode == 0) return sum;
        if(end[curnode]) sum++;
        if(sum >= 2) sum -= 2;
        return sum;
    }
    static void buildtrie(ArrayList<Integer>[] trie, boolean[] end, ArrayList<String> strs, int pointer, int curnode) {
        HashMap<Character, ArrayList<String>> groups = new HashMap<Character, ArrayList<String>>();
        HashSet<Character> flagged = new HashSet<Character>();
        for(String s : strs) {
            if(pointer >= s.length()) continue;
            char c = s.charAt(pointer);
            if(pointer == s.length() - 1) flagged.add(c);
            if(groups.containsKey(c)) groups.get(c).add(s);
            else {
                ArrayList<String> group = new ArrayList<String>();
                group.add(s);
                groups.put(c, group);
            }
        }
        for(char c : groups.keySet()) {
            trie[curnode].add(counter);
            if(flagged.contains(c)) end[counter] = true;
            counter++;
            buildtrie(trie, end, groups.get(c), pointer + 1, counter - 1);
        }
    }
}
