import java.util.*;
import java.io.*;

public class _1482_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] h = new int[n];
        int[] b = new int[n];
        StringTokenizer hline = new StringTokenizer(in.readLine());
        StringTokenizer bline = new StringTokenizer(in.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
        int[] prev = new int[n];
        for(int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(hline.nextToken());
            b[i] = Integer.parseInt(bline.nextToken());
            while(stack.size() > 0 && h[stack.peek()] > h[i]) {
                stack.pop();
            }
            if(stack.size() == 0) {
                prev[i] = -1;
            }else {
                prev[i] = stack.peek();
            }
            stack.push(i);
        }
        ArrayDeque<State> dp = new ArrayDeque<State>();
        dp.push(new State(0, 0, b[0]));
        dp.push(new State(n, b[0], b[0]));
        for(int i = 1; i < n; i++) {
            long max = Long.MIN_VALUE;
            while(dp.size() > 0 && dp.peek().min_ind > prev[i]) {
                max = Math.max(max, dp.pop().max);
            }
            long prev_best = dp.size() > 0 ? dp.peek().best : Long.MIN_VALUE;
            long new_best = Math.max(prev_best, max + b[i]);
            dp.push(new State(i, max, new_best));
            dp.push(new State(n, new_best, new_best));
        }
        out.println(dp.peek().best);
        in.close();
        out.close();
    }
    static class State {
        int min_ind;
        long max, best;
        State(int mi, long m, long b) {
            min_ind = mi;
            max = m;
            best = b;
        }
    }
}
