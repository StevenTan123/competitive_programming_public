import java.util.*;
import java.io.*;

public class _1484_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            StringTokenizer line = new StringTokenizer(in.readLine());
            int[] a = new int[n];
            int[] difs = new int[n - 1];
            for(int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
                if(i > 0) difs[i - 1] = a[i] - a[i - 1];
            }
            int neggap = 1;
            int posgap = -1;
            int zerocount = 0;
            boolean possible = true;
            for(int i = 0; i < n - 1; i++) {
                if(difs[i] < 0) {
                    if(neggap == 1) {
                        neggap = difs[i];
                    }else {
                        if(difs[i] != neggap) {
                            possible = false;
                            break;
                        }
                    }
                }else if(difs[i] > 0) {
                    if(posgap == -1) {
                        posgap = difs[i];
                    }else {
                        if(difs[i] != posgap) {
                            possible = false;
                            break;
                        }
                    }
                }else {
                    zerocount++;
                }
            }
            if((zerocount > 0 && zerocount < n - 1) || !possible) {
                out.println(-1);
                continue;
            }
            if(zerocount == n - 1 || posgap == -1 || neggap == 1) {
                out.println(0);
                continue;
            }
            for(int i = 0; i < n; i++) {
                if(a[i] >= posgap - neggap) possible = false;
            }
            if(!possible) {
                out.println(-1);
                continue;
            }
            out.println((posgap - neggap) + " " + posgap);
        }
        in.close();
        out.close();
    }
}
