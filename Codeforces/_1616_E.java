import java.util.*;
import java.io.*;

public class _1616_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String a = in.readLine();
            String b = in.readLine();
            HashMap<Character, ArrayDeque<Integer>> b_inds = new HashMap<Character, ArrayDeque<Integer>>();
            for(int i = 0; i < n; i++) {
                char c = b.charAt(i);
                if(!b_inds.containsKey(c)) {
                    b_inds.put(c, new ArrayDeque<Integer>());
                }
                b_inds.get(c).add(i);
            }
            HashMap<Character, ArrayDeque<Integer>> a_inds = new HashMap<Character, ArrayDeque<Integer>>();
            int[] match = new int[n];
            Arrays.fill(match, -1);
            for(int i = 0; i < n; i++) {
                char c = a.charAt(i);
                ArrayDeque<Integer> cur = b_inds.get(c);
                if(cur != null && cur.size() > 0) {
                    match[cur.poll()] = i;
                }
                if(!a_inds.containsKey(c)) {
                    a_inds.put(c, new ArrayDeque<Integer>());
                }
                a_inds.get(c).add(i);
            }
            int[] start = new int[n];
            Arrays.fill(start, 1);
            BIT bit = new BIT(start);
            long moves = 0;
            long min = Long.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                int closest = Integer.MAX_VALUE;
                int val = (int)b.charAt(i);
                for(int j = 97; j < val; j++) {
                    char c = (char)j;
                    if(a_inds.containsKey(c) && a_inds.get(c).size() > 0) {
                        int swaps = bit.sum(0, a_inds.get(c).peek()) - 1;
                        closest = Math.min(closest, swaps);
                    }
                }
                if(closest != Integer.MAX_VALUE) {
                    min = Math.min(min, moves + closest);
                }
                if(match[i] == -1) {
                    break;
                }else {
                    bit.update(match[i], -1);
                    moves += bit.sum(0, match[i]);
                    a_inds.get(b.charAt(i)).poll();
                }
            }
            if(min == Long.MAX_VALUE) {
                out.println(-1);
            }else {
                out.println(min);
            }
        }
        in.close();
        out.close();
    }
    static class BIT {
        int[] bit;
        BIT(int[] a) {
            bit = new int[a.length + 1];
            for(int i = 0; i < a.length; i++) {
                update(i, a[i]);
            }
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
