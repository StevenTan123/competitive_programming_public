import java.util.*;
import java.io.*;

public class meta_game {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("meta_game_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //PrintWriter out = new PrintWriter(System.out);
        
        int tt = Integer.parseInt(in.readLine());
        for (int t = 1; t <= tt; t++) {
            int N = Integer.parseInt(in.readLine());
            int[] arr = new int[4 * N];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(line.nextToken());
                arr[i + 2 * N] = arr[i];
            }
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++) {
                arr[i + N] = Integer.parseInt(line.nextToken());
                arr[i + 3 * N] = arr[i + N];
            }

            int ans = -1;
            for (int i = 0; i < 2 * N; i++) {
                if (N % 2 == 1) {
                    if (arr[i + N / 2] != arr[i + N + N / 2]) {
                        continue;
                    }
                }
                boolean works = true;
                for (int j = 0; j < N / 2; j++) {
                    int start = i + j;
                    if (arr[start] != arr[i + 2 * N - j - 1]) {
                        works = false;
                        break;
                    }

                    int end = i + N - 1 - j;
                    if (arr[end] != arr[i + N + j]) {
                        works = false;
                        break;
                    }

                    if (arr[start] >= arr[start + N]) {
                        works = false;
                        break;
                    }

                    if (arr[end] <= arr[end + N]) {
                        works = false;
                        break;
                    }
                }
                if (works) {
                    ans = i;
                    break;
                }
            }

            String res = "Case #" + t + ": ";
            out.println(res + ans);
        }
        
        in.close();
        out.close();
    }
}
