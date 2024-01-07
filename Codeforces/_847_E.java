import java.util.*;
import java.io.*;

public class _847_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        ArrayList<Integer> ps = new ArrayList<Integer>();
        String s = in.readLine();
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '*') {
                a[i] = 1;
            }else if(s.charAt(i) == 'P') {
                ps.add(i);
            }
        }
        int l = 1;
        int r = 2 * n;
        int res = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(ok(a, ps, mid)) {
                res = mid;
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    static boolean ok(int[] a, ArrayList<Integer> ps, int time) {
        int start = 0;
        for(int p : ps) {
            int pointer = start;
            int first = -1;
            while(pointer < a.length) {
                if(a[pointer] == 0) {
                    pointer++;
                    continue;
                }
                if(first == -1) first = pointer;
                if(cost(p, first, pointer) > time) {
                    break;
                }
                pointer++;
            }
            start = pointer;
            if(pointer >= a.length) return true;
        }
        return false;
    }
    static int cost(int ind, int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        if(a >= ind && b >= ind) {
            return max - ind;
        }else if(a <= ind && b <= ind) {
            return ind - min;
        }else {
            return Math.min(max - ind, ind - min) + max - min;
        }
    }
}
