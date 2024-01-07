import java.util.*;
import java.io.*;

public class _1148_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        ArrayList<Triple> group1 = new ArrayList<Triple>();
        ArrayList<Triple> group2 = new ArrayList<Triple>();
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            Triple cur = new Triple(a, b, i);
            if(a < b) {
                group1.add(cur);
            }else {
                group2.add(cur);
            }
        }
        Comparator<Triple> one = new Comparator<Triple>() {
            @Override
            public int compare(Triple a, Triple b) {
                return b.a - a.a;
            }
        };
        Comparator<Triple> two = new Comparator<Triple>() {
            @Override
            public int compare(Triple a, Triple b) {
                return a.a - b.a;
            }
        };
        ArrayList<Triple> use;
        if(group1.size() > group2.size()) {
            Collections.sort(group1, one);
            use = group1;
        }else {
            Collections.sort(group2, two);
            use = group2;
        }
        out.println(use.size());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < use.size(); i++) {
            sb.append(use.get(i).c + 1);
            if(i < use.size() - 1) sb.append(' ');
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }
    static class Triple {
        int a, b, c;
        Triple(int aa, int bb, int cc) {
            a = aa;
            b = bb;
            c = cc;
        }
    }
}
