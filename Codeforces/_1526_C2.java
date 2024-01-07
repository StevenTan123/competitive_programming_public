import java.util.*;
import java.io.*;

public class _1526_C2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        long sum = 0;
        PriorityQueue<Integer> sorted = new PriorityQueue<Integer>();
        int sub = 0;
        for(int i = 0; i < n; i++) {
            sum += a[i];
            sorted.add(a[i]);
            while(sum < 0) {
                int smallest = sorted.poll();
                sum -= smallest;
                sub++;
            }
        }
        out.println(n - sub);
        in.close();
        out.close();
    }
}
