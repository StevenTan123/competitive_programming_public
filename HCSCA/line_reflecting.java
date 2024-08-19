import java.util.*;
import java.io.*;

public class line_reflecting {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());

        line = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(line.nextToken());
        int y = Integer.parseInt(line.nextToken());

        int[] a = new int[n];
        line = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }

        int[] b = new int[m];
        line = new StringTokenizer(in.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(line.nextToken());
        }

        out.println(max1D(x, a) + max1D(y, b));

        in.close();
        out.close();
    }

    static long max1D(int start, int[] a) {
        long high = Long.MIN_VALUE;
        long low = Long.MAX_VALUE;
        Arrays.sort(a);

        // Case 1: Get as high of a coordinate as possible
        int l = 0;
        int r = a.length - 1;
        int count = 0;
        long sum = 0;
        while (r >= l) {
            if (count % 2 == 0) {
                sum += 2 * a[r];
                high = Math.max(high, sum - start);
                r--;
            } else {
                sum -= 2 * a[l];
                high = Math.max(high, sum + start);
                l++;
            }
            count++;
        }

        // Case 2: Get as low of a coordinate as possible
        l = 0;
        r = a.length - 1;
        count = 0;
        sum = 0;
        while (r >= l) {
            if (count % 2 == 0) {
                sum += 2 * a[l];
                low = Math.min(low, sum - start);
                l++;
            } else {
                sum -= 2 * a[r];
                low = Math.min(low, sum + start);
                r--;
            }
            count++;
        }

        return Math.max(high - start, start - low);
    }
}
