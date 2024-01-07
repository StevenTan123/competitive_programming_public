import java.util.*;
import java.io.*;

public class _1333_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        String s = in.readLine();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'R') {
                a[i] = 1;
            }
        }
        ArrayList<Integer>[] moves = new ArrayList[k + 1];
        //dp[i] stores the time it takes the previous 1 to clear out to position i
        int[] dp = new int[n];
        int count = 0;
        int end = 0;
        int total = 0;
        boolean possible = true;
        for(int i = n - 1; i >= 0; i--) {
            if(a[i] == 1) {
                int[] new_dp = new int[n];
                for(int j = i + 1; j < n - count; j++) {
                    new_dp[j] = Math.max(new_dp[j - 1] + 1, (j < n - 1 ? dp[j + 1] : 0) + 1);
                    if(j > i) {
                        if(new_dp[j] > k) {
                            possible = false;
                            break;
                        }
                        if(moves[new_dp[j]] == null) {
                            moves[new_dp[j]] = new ArrayList<Integer>();
                        }
                        moves[new_dp[j]].add(j - 1);
                        end = Math.max(end, new_dp[j]);
                    }
                }
                dp = new_dp;
                total += n - count - i - 1;
                count++;
            }
        }
        if(!possible || k > total) {
            out.println(-1);
        }else {
            int left = k - end;
            for(int i = 1; i <= end; i++) {
                ArrayList<Integer> wave = new ArrayList<Integer>();
                for(int move : moves[i]) {
                    if(left > 0) {
                        out.println(1 + " " + (move + 1));
                        left--;
                    }else {
                        wave.add(move + 1);
                    }
                }
                if(wave.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(wave.size());
                    for(int move : wave) {
                        sb.append(' ');
                        sb.append(move);
                    }
                    out.println(sb.toString());
                }else {
                    left++;
                }
            }
        }
        in.close();
        out.close();
    }
}
