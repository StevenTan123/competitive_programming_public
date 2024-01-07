import java.util.*;
import java.io.*;

public class _1495_B {
    static int[] inc, dec;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        inc = new int[n];
        dec = new int[n];
        int p = 0;
        int maxi = 0;
        int counti = 0;
        int indi = 0;
        for(int i = 0; i < n; i++) {
            if(i > 0 && a[i] < a[i - 1]) {
                p = i;
            }
            inc[i] = i - p + 1;
            if(inc[i] > maxi) {
                maxi = inc[i];
                indi = i;
                counti = 1;
            }else if(inc[i] == maxi) {
                counti++;
            }
        }
        p = n - 1;
        int maxd = 0;
        int countd = 0;
        int indd = 0;
        for(int i = n - 1; i >= 0; i--) {
            if(i < n - 1 && a[i] < a[i + 1]) {
                p = i;
            }
            dec[i] = p - i + 1;
            if(dec[i] > maxd) {
                maxd = dec[i];
                indd = i;
                countd = 1;
            }else if(dec[i] == maxd) {
                countd++;
            }
        }
        if(maxi == maxd && counti == 1 && countd == 1) {
            if(indi == indd && maxi % 2 == 1) {
                out.println(1);
            }else {
                out.println(0);
            }
        }else {
            out.println(0);
        }
        in.close();
        out.close();
    }
}
