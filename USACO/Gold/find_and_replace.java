import java.io.*;
import java.util.*;

public class find_and_replace {
    static char[] chars;
    static String[] reps;
    static long[][] lengths, sums;
    static int[][] next;
    static long orig_l;
    static int[] res;
    static int[] seen;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        long l = Long.parseLong(line.nextToken()) - 1;
        orig_l = l;
        long r = Long.parseLong(line.nextToken()) - 1;
        int n = Integer.parseInt(line.nextToken());
        chars = new char[n];
        reps = new String[n];
        for (int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            chars[i] = line.nextToken().charAt(0);
            reps[i] = line.nextToken();
        }
        next = new int[n][26];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (j == (int) (chars[i]) - 97) {
                    next[i][j] = i;
                } else {
                    if (i == n - 1) {
                        next[i][j] = n;
                    } else {
                        next[i][j] = next[i + 1][j];
                    }
                }
            }
        }

        // lengths[i][j] is the final length of character j if transformed with
        // operations after i
        lengths = new long[n][26];
        sums = new long[n][];
        sums[n - 1] = new long[reps[n - 1].length() + 1];
        for (int i = 0; i < sums[n - 1].length; i++) {
            sums[n - 1][i] = i;
        }
        Arrays.fill(lengths[n - 1], 1);
        lengths[n - 1][(int) (chars[n - 1]) - 97] = reps[n - 1].length();
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                lengths[i][j] = lengths[i + 1][j];
            }
            sums[i] = new long[reps[i].length() + 1];
            int c1 = (int) (chars[i]) - 97;
            lengths[i][c1] = 0;
            for (int j = 0; j < reps[i].length(); j++) {
                sums[i][j] = lengths[i][c1];
                int c2 = (int) (reps[i].charAt(j)) - 97;
                lengths[i][c1] += lengths[i + 1][c2];
                if (lengths[i][c1] > r) {
                    lengths[i][c1] = r + 1;
                }
            }
            sums[i][reps[i].length()] = lengths[i][c1];
        }

        seen = new int[n];
        Arrays.fill(seen, -1);
        res = new int[(int) (r - l + 1)];
        find_chars(l, r, 0, 0, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            sb.append((char) (res[i] + 97));
        }
        out.println(sb.toString());
        in.close();
        out.close();
    }

    static void find_chars(long l, long r, long cur_ind, int cur_char, int op) {
        if (op >= sums.length) {
            res[(int)(l - orig_l)] = cur_char;
            return;
        }
        if (r - l + 1 == lengths[op][cur_char]) {
            if (seen[op] != -1) {
                for (int i = 0; i <= r - l; i++) {
                    res[(int)(l - orig_l) + i] = res[seen[op] + i];
                }
                return;
            } else {
                seen[op] = (int)(l - orig_l);
            }
        }
        long prev = l;
        int ind = binsearch(sums[op], cur_ind, l);
        for (int i = ind; i < sums[op].length; i++) {
            if (cur_ind + sums[op][i] > l) {
                int new_char = (int) (reps[op].charAt(i - 1)) - 97;
                find_chars(prev, Math.min(cur_ind + sums[op][i] - 1, r), cur_ind + sums[op][i - 1], new_char,
                        (op == next.length - 1) ? next.length : next[op + 1][new_char]);
                prev = cur_ind + sums[op][i];
            }
            if (cur_ind + sums[op][i] > r) {
                break;
            }
        }
    }

    // Largest index with value smaller than val
    static int binsearch(long[] arr, long add, long val) {
        int l = 0;
        int r = arr.length - 1;
        int res = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] + add <= val) {
                res = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }
}