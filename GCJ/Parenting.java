import java.util.*;
import java.io.*;

public class Parenting {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int n = Integer.parseInt(in.readLine());
            int[][] activities = new int[n][4];
            for(int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                activities[i][0] = Integer.parseInt(line.nextToken());
                activities[i][1] = Integer.parseInt(line.nextToken());
                activities[i][2] = i;
            }
            Arrays.sort(activities, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            int end1 = 0;
            int end2 = 0;
            boolean possible = true;
            for(int i = 0; i < n; i++) {
                if(activities[i][0] >= end1) {
                    end1 = activities[i][1];
                    activities[i][3] = 0;
                }else if(activities[i][0] >= end2) {
                    end2 = activities[i][1];
                    activities[i][3] = 1;
                }else {
                    possible = false;
                    break;
                }
            }
            if(!possible) {
                out.println("Case #" + t + ": IMPOSSIBLE");
                continue;
            }
            Arrays.sort(activities, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[2] - o2[2];
                }
            });
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++) {
                sb.append(activities[i][3] == 0 ? 'C' : 'J');
            }
            out.println("Case #" + t + ": " + sb.toString());
        }
        in.close();
        out.close();
    }
}
