import java.util.*;
import java.io.*;

public class bracelet {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            in.readLine();
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());

            //0 if haven't seen, 1 if active, 2 if closed
            int[] active = new int[n];

            //relationship between two bracelets
            //rel[i][j] = 0 if i is encloses j
            //rel[i][j] = 1 if i within j
            //rel[i][j] = 2 if i is below j
            //rel[i][j] = 3 if i is above j
            int[][] rel = new int[n][n];

            for(int i = 0; i < n; i++) {
                Arrays.fill(rel[i], -1);
            }

            boolean possible = true;
            for(int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int k = Integer.parseInt(line.nextToken());
                int[][] occ = new int[n][2];
                for(int j = 0; j < n; j++) {
                    Arrays.fill(occ[j], -1);
                }
                for(int j = 0; j < k; j++) {
                    int color = Integer.parseInt(line.nextToken()) - 1;
                    if(occ[color][0] == -1) {
                        occ[color][0] = j;
                    }else {
                        occ[color][1] = j;
                    }
                }
                int[] preva = new int[n];
                for(int j = 0; j < n; j++) {
                    preva[j] = active[j];
                    if(occ[j][0] == -1 && occ[j][1] == -1) {
                        if(active[j] == 1) {
                            active[j] = 2;
                        }
                    }else {
                        if(active[j] == 0) {
                            active[j] = 1;
                        }else if(active[j] == 2) {
                            possible = false;
                            break;
                        }
                    }
                }
                for(int a = 0; a < n; a++) {
                    for(int b = 0; b < n; b++) {
                        if(a == b) {
                            continue;
                        }
                        int s1 = occ[a][0];
                        int e1 = occ[a][1];
                        int s2 = occ[b][0];
                        int e2 = occ[b][1];
                        if(s1 == -1 || e1 == -1 || s2 == -1 || e2 == -1) {
                            continue;
                        }
                        int type;
                        if(s1 < s2) {
                            if(s2 < e1) {
                                if(e2 > e1) {
                                    possible = false;
                                    break;
                                }else {
                                    type = 0;
                                }
                            }else {
                                type = 2;
                            }
                        }else {
                            if(s1 < e2) {
                                if(e1 > e2) {
                                    possible = false;
                                    break;
                                }else {
                                    type = 1;
                                }
                            }else {
                               type = 3;
                            }
                        }
                        if(type == 0 && preva[a] == 0 && preva[b] == 1) {
                            possible = false;
                            break;
                        }
                        if(rel[a][b] == -1 || rel[a][b] == type) {
                            rel[a][b] = type;
                        }else {
                            possible = false;
                            break;
                        }
                    }
                }
            }
            out.println(possible ? "YES" : "NO");
        }
        in.close();
        out.close();
    }
}
