import java.util.*;
import java.io.*;

public class Vestigium {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        int total = t;
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[][] arr = new int[n][n];
            int trace = 0;
            for(int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                for(int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(line.nextToken());
                    if(i == j) trace += arr[i][j];
                }
            }
            int r = 0;
            int c = 0;
            for(int i = 0; i < n; i++) {
                HashSet<Integer> uniquer = new HashSet<Integer>();
                HashSet<Integer> uniquec = new HashSet<Integer>();
                boolean duper = false;
                boolean dupec = false;
                for(int j = 0; j < n; j++) {
                    if(uniquer.contains(arr[i][j])) {
                        duper = true;
                    }else {
                        uniquer.add(arr[i][j]);
                    }
                    if(uniquec.contains(arr[j][i])) {
                        dupec = true;
                    }else {
                        uniquec.add(arr[j][i]);
                    }
                }
                if(duper) r++;
                if(dupec) c++;
            }
            out.println("Case #" + (total - t) + ": " + trace + " " + r + " " + c);
        }
        in.close();
        out.close();
    }
}
