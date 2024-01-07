import java.util.*;
import java.io.*;

public class palindromic_poster {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer line = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(line.nextToken());
        int M = Integer.parseInt(line.nextToken());
        int R = Integer.parseInt(line.nextToken());
        int C = Integer.parseInt(line.nextToken());
        
        if (R < N && C < M) {
            char[][] poster = new char[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    char c = (char)((i + j) % 25 + 98);
                    poster[i][j] = c;
                }
            }
            for (int i = 0; i < R; i++) {
                Arrays.fill(poster[i], 'a');
            }
            for (int i = 0; i < C; i++) {
                for (int j = 0; j < N; j++) {
                    poster[j][i] = 'a';
                }
            }
            for (int i = 0; i < N; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < M; j++) {
                    sb.append(poster[i][j]);
                }
                out.println(sb.toString());
            }
        } else if (R == N) {
            char[][] poster = solve_case(N, M, R, C);
            if (poster == null) {
                out.println("IMPOSSIBLE");
            } else {
                for (int i = 0; i < N; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < M; j++) {
                        sb.append(poster[i][j]);
                    }
                    out.println(sb.toString());
                }
            }
        } else {
            char[][] poster = solve_case(M, N, C, R);
            if (poster == null) {
                out.println("IMPOSSIBLE");
            } else {
                poster = transpose(poster);
                for (int i = 0; i < N; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < M; j++) {
                        sb.append(poster[i][j]);
                    }
                    out.println(sb.toString());
                }
            }
        }

        in.close();
        out.close();
    }

    static char[][] transpose(char[][] poster) {
        char[][] res = new char[poster[0].length][poster.length];
        for (int i = 0; i < poster.length; i++) {
            for (int j = 0; j < poster[i].length; j++) {
                res[j][i] = poster[i][j];
            }
        }
        return res;
    }

    static char[][] solve_case(int N, int M, int R, int C) {
        if (M % 2 == 0 && C % 2 == 1) {
            return null;
        } else {
            char[][] poster = new char[N][M];
            for (int i = 0; i < N; i++) {
                char cur = (char) (i % 26 + 97);
                Arrays.fill(poster[i], cur);
            }
            int change = C / 2;
            for (int i = 0; i < change; i++) {
                for (int j = 0; j < R; j++) {
                    poster[j][i] = 'a';
                    poster[j][M - i - 1] = 'a';
                }
            }
            if (C % 2 == 1) {
                int ind = M / 2;
                for (int i = 0; i < R; i++) {
                    poster[i][ind] = 'a';
                }
            }
            return poster;
        }
    }
}
