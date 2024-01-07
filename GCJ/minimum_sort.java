import java.util.*;
import java.io.*;

public class minimum_sort {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(in.readLine());
        int t = Integer.parseInt(line.nextToken());
        int n = Integer.parseInt(line.nextToken());
        while(t-- > 0) {
            for(int i = 0; i < n - 1; i++) {
                int min = min_query(i, n - 1, in);
                if(min > i) {
                    swap_query(i, min, in);
                }
            }
            System.out.println("D");
            System.out.flush();
            int verdict = Integer.parseInt(in.readLine());
            if(verdict == 1) {
                continue;
            }else {
                break;
            }
        }
        in.close();
    }
    static int min_query(int l, int r, BufferedReader in) throws IOException {
        System.out.println("M " + (l + 1) + " " + (r + 1));
        System.out.flush();
        return Integer.parseInt(in.readLine()) - 1;
    }
    static void swap_query(int l, int r, BufferedReader in) throws IOException {
        System.out.println("S " + (l + 1) + " " + (r + 1));
        System.out.flush();
        in.readLine();
    }
}
