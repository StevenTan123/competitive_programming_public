import java.util.*;
import java.io.*;

public class _1552_C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            int[][] chords = new int[n][2];
            HashSet<Integer> used = new HashSet<Integer>();
            for(int i = 0; i < k; i++) {
                line = new StringTokenizer(in.readLine());
                chords[i][0] = Integer.parseInt(line.nextToken()) - 1;
                chords[i][1] = Integer.parseInt(line.nextToken()) - 1;
                used.add(chords[i][0]);
                used.add(chords[i][1]);
            }
            int[] left = new int[2 * (n - k)];
            int p = 0;
            for(int i = 0; i < 2 * n; i++) {
                if(!used.contains(i)) {
                    left[p] = i;
                    p++;
                }
            }
            for(int i = 0; i < left.length / 2; i++) {
                int o = i + left.length / 2;
                chords[k + i][0] = left[i];
                chords[k + i][1] = left[o];
            }
            int res = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i != j && intersect(i, j, chords)) {
                        res++;
                    }
                }
            }
            out.println(res / 2);
        }
        in.close();
        out.close();
    }
    static boolean intersect(int a, int b, int[][] chords) {
        int mina = Math.min(chords[a][0], chords[a][1]);
        int maxa = Math.max(chords[a][0], chords[a][1]);
        int minb = Math.min(chords[b][0], chords[b][1]);
        int maxb = Math.max(chords[b][0], chords[b][1]);
        if(maxa < maxb && maxa > minb && mina < minb) {
            return true;
        }else if(maxb < maxa && maxb > mina && minb < mina) {
            return true;
        }else {
            return false;
        }
    }
}
