import java.util.*;
import java.io.*;

public class abcs {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] x = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            HashSet<Integer> unique = new HashSet<Integer>();
            ArrayList<Integer> possible = new ArrayList<Integer>();
            for(int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(line.nextToken());
                if(!unique.contains(x[i])) {
                    unique.add(x[i]);
                    possible.add(x[i]);
                }
            }
            possible = expand(possible, unique);
            possible = expand(possible, unique);
            int res = 0;
            for(int i = 0; i < possible.size(); i++) {
                int a = possible.get(i);
                if(works(a, a, a, x)) res++;
                for(int j = i + 1; j < possible.size(); j++) {
                    int b = possible.get(j);
                    if(works(a, a, b, x)) res++;
                    if(works(a, b, b, x)) res++;
                    for(int k = j + 1; k < possible.size(); k++) {
                        int c = possible.get(k);
                        if(works(a, b, c, x)) res++;
                    }
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static ArrayList<Integer> expand(ArrayList<Integer> possible, HashSet<Integer> unique) {
        ArrayList<Integer> res = new ArrayList<Integer>(possible);
        for(int i = 0; i < possible.size(); i++) {
            for(int j = i + 1; j < possible.size(); j++) {
                int add = Math.abs(possible.get(j) - possible.get(i));
                if(!unique.contains(add)) {
                    unique.add(add);
                    res.add(add);
                }
            }
        }
        return res;
    }
    static boolean works(int a, int b, int c, int[] x) {
        Arrays.sort(x);
        int[] seven = new int[] {a, b, c, a + b, b + c, c + a, a + b + c};
        Arrays.sort(seven);
        int p1 = 0;
        int p2 = 0;
        while(p1 < x.length && p2 < 7) {
            if(x[p1] == seven[p2]) p1++;
            p2++;
        }
        return p1 == x.length;
    }
}
