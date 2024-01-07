import java.util.*;
import java.io.*;

public class _1594_E2 {
    static long[] pow2, dp;
    static long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int k = Integer.parseInt(in.readLine());
        int n = Integer.parseInt(in.readLine());
        pow2 = new long[k + 1];
        pow2[0] = 1;
        for(int i = 1; i <= k; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }
        dp = new long[k + 1];
        dp[1] = 1;
        for(int i = 2; i <= k; i++) {
            dp[i] = modmult(4, modmult(dp[i - 1], dp[i - 1]));
        }
        HashMap<Long, Integer>[] filled = new HashMap[k + 1];
        for(int i = 1; i <= k; i++) {
            filled[i] = new HashMap<Long, Integer>();
        }
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            long node = Long.parseLong(line.nextToken());
            int level = -1;
            for(int j = 0; j < 60; j++) {
                if(node >= pow2[j] && node < pow2[j + 1]) {
                    level = j + 1;
                    break;
                }
            }
            String color = line.nextToken();
            int c = 2;
            if(color.equals("white") || color.equals("yellow")) {
                c = 0;
            }else if(color.equals("green") || color.equals("blue")) {
                c = 1;
            }
            filled[level].put(node, c);
        }
        TreeSet<State> states = new TreeSet<State>();
        for(int i = k; i >= 1; i--) {
            for(State cur : states) {
                Integer color = filled[i].get(cur.node);
                if(color != null) {
                    for(int j = 0; j < 3; j++) {
                        if(j != color) {
                            cur.counts[j] = 0;
                        }
                    }
                }
            }
            for(long node : filled[i].keySet()) {
                long[] counts = new long[3];
                counts[filled[i].get(node)] = dp[k - i + 1];
                State add = new State(counts, node);
                if(!states.contains(add)) {
                    states.add(add);
                }
            }
            if(i == 1) {
                break;
            }
            TreeSet<State> next_states = new TreeSet<State>();
            boolean skip = false;
            for(State cur : states) {
                if(skip) {
                    skip = false;
                    continue;
                }
                State next = states.higher(cur);
                if(next != null && cur.node / 2 == next.node / 2) {
                    long[] new_counts = merge_counts(cur.counts, next.counts);
                    next_states.add(new State(new_counts, cur.node / 2));
                    skip = true;
                    continue;
                }
                long[] counts = new long[] {dp[k - i + 1], dp[k - i + 1], dp[k - i + 1]};
                long[] new_counts = merge_counts(cur.counts, counts);
                next_states.add(new State(new_counts, cur.node / 2));
            }
            states = next_states;
        }
        long res = 0;
        if(states.size() == 0) {
            res = modmult(dp[k], 3);
        }else {
            long[] counts = states.first().counts;
            for(int i = 0; i < 3; i++) {
                res = modadd(res, counts[i]);
            }
        }
        long exp = pow2[k] - 1;
        long pow = binpow(2, exp - n);
        out.println(modmult(res, pow));
        in.close();
        out.close();
    }   
    static long[] merge_counts(long[] counts1, long[] counts2) {
        return new long[] {modmult(modadd(counts1[1], counts1[2]), modadd(counts2[1], counts2[2])),
                           modmult(modadd(counts1[0], counts1[2]), modadd(counts2[0], counts2[2])),
                           modmult(modadd(counts1[0], counts1[1]), modadd(counts2[0], counts2[1]))};
    }
    static class State implements Comparable<State> {
        long[] counts;
        long node;
        State(long[] c, long n) {
            counts = c;
            node = n;
        }
        @Override
        public int compareTo(State o) {
            return Long.compare(node, o.node);
        }
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
    static long modinv(long a) {
        return binpow(a, MOD - 2);
    }
    static long binpow(long a, long b) {
        if(b == 0) {
            return 1;
        }
        long small = binpow(a, b / 2);
        if(b % 2 == 0) {
            return modmult(small, small);
        }else {
            return modmult(modmult(small, small), a);
        }
    }
}
