import java.util.*;
import java.io.*;

public class _1526_D {
    static String res;
    static long max;
    static char[] chars = new char[] { 'A', 'N', 'T', 'O' };
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            String s = in.readLine();
            int[] a = new int[s.length()];
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(c == 'A') {
                    a[i] = 0;
                }else if(c == 'N') {
                    a[i] = 1;
                }else if(c == 'T') {
                    a[i] = 2;
                }else {
                    a[i] = 3;
                }
            }
            max = -1;
            perm(new ArrayList<Integer>(), a);
            out.println(res);
        }
        in.close();
        out.close();
    }
    static void perm(ArrayList<Integer> p, int[] a) {
        if(p.size() == 4) {
            inversions(p, a);
            return;
        }
        for(int i = 0; i < 4; i++) {
            if(!p.contains(i)) {
                p.add(i);
                perm(p, a);
                p.remove(p.size() - 1);
            }
        }
    }
    static void inversions(ArrayList<Integer> p, int[] a) {
        int[] to = new int[a.length];
        int[] inds = new int[a.length];
        int ind = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < a.length; j++) {
                if(a[j] == p.get(i)) {
                    to[j] = ind;
                    inds[ind] = j;
                    sb.append(chars[p.get(i)]);
                    ind++;
                }
            }
        }
        long invs = 0;
        BIT bit = new BIT(a.length);
        for(int i = 0; i < a.length; i++) {
            invs += bit.sum(inds[i], a.length - 1);
            bit.update(inds[i], 1);
        }
        if(invs > max) {
            max = invs;
            res = sb.toString();
        }
    }
    static class BIT {
        int[] bit;
        BIT(int n) {
            bit = new int[n + 1];
        }
        void update(int index, int add) {
            index++;
            while(index < bit.length) {
                bit[index] += add;
                index += index & -index;
            }
        }
        int sum(int l, int r) {
            return psum(r) - (l == 0 ? 0 : psum(l - 1));
        }
        int psum(int index) {
            index++;
            int res = 0;
            while(index > 0) {
                res += bit[index];
                index -= index & -index;
            }
            return res;
        }
    }
}
