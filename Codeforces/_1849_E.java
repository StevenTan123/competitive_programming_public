import java.util.*;
import java.io.*;

public class _1849_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        int n = Integer.parseInt(in.readLine());
        int[] p = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(line.nextToken()) - 1;
        }

        int[] min_left = new int[n];
        int[] min_right = new int[n];
        int[] max_left = new int[n];
        int[] max_right = new int[n];
        
        Stack<Integer> increase_right = new Stack<Integer>();
        Stack<Integer> decrease_right = new Stack<Integer>();
        for (int i = 0; i < n; i++) {
            while (increase_right.size() > 0 && p[increase_right.peek()] > p[i]) {
                increase_right.pop();
            }
            min_left[i] = increase_right.size() > 0 ? increase_right.peek() + 1 : 0;
            increase_right.push(i);

            while (decrease_right.size() > 0 && p[decrease_right.peek()] < p[i]) {
                decrease_right.pop();
            }
            max_left[i] = decrease_right.size() > 0 ? decrease_right.peek() + 1 : 0;
            decrease_right.push(i);
        }

        Stack<Integer> increase_left = new Stack<Integer>();
        Stack<Integer> decrease_left = new Stack<Integer>();
        for (int i = n - 1; i >= 0; i--) {
            while (increase_left.size() > 0 && p[increase_left.peek()] > p[i]) {
                increase_left.pop();
            }
            min_right[i] = increase_left.size() > 0 ? increase_left.peek() - 1: n - 1;
            increase_left.push(i);

            while (decrease_left.size() > 0 && p[decrease_left.peek()] < p[i]) {
                decrease_left.pop();
            }
            max_right[i] = decrease_left.size() > 0 ? decrease_left.peek() - 1 : n - 1;
            decrease_left.push(i);
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            if (i - max_left[i] < max_right[i] - i) {
                for (int j = max_left[i]; j < i; j++) {
                    int new_left = Math.max(max_left[i], min_left[j]);
                    int new_right = Math.min(max_right[i], min_right[j]);
                    if (new_right >= i) {
                        res += (long)(j - new_left + 1) * (new_right - i + 1);
                    }
                }
            } else {
                res += (long)(max_right[i] - i + 1) * (i - max_left[i] + 1) - 1;
                for (int j = i + 1; j <= max_right[i]; j++) {
                    int new_left = Math.max(max_left[i], min_left[j]);
                    int new_right = Math.min(max_right[i], min_right[j]);
                    if (new_left <= i) {
                        res -= (long)(new_right - j + 1) * (i - new_left + 1);
                    }
                }
            }
        }
        out.println(res);

        in.close();
        out.close();
    }
}
