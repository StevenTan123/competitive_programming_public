import java.util.*;
import java.io.*;

public class _484_A {
    public static void main(String[] args) throws IOException {
        long[] pows = new long[63];
        pows[0] = 1;
        for(int i = 1; i < 63; i++) pows[i] = pows[i - 1] * 2;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            long l = Long.parseLong(line.nextToken());
            long r = Long.parseLong(line.nextToken());
            long origr = r;
            int count = 0;
            long pow = 1;
            while(pow <= r) {
                pow *= 2;
                count++;
            }
            int[] lbits = new int[count];
            int[] rbits = new int[count];
            int pointer = count - 1;
            while(l > 0) {
                lbits[pointer] = (int)(l % 2);
                l = l >> 1;
                pointer--;
            }
            int rcount = 0;
            pointer = count - 1;
            while(r > 0) {
                rbits[pointer] = (int) (r % 2);
                rcount += rbits[pointer];
                r = r >> 1;
                pointer--;
            }
            int first = count;
            for(int j = 0; j < count; j++) {
                if(lbits[j] != rbits[j]) {
                    first = j;
                    break;
                }
            }
            long res = 0;
            int pcount = 0;
            for(int j = 0; j < count; j++) {
                if(j <= first) {
                    pcount += lbits[j];
                    res += lbits[j] * pows[count - j - 1]; 
                }else {
                    pcount++;
                    res += pows[count - j - 1];
                }
            }
            if(pcount >= rcount) {
                out.println(res);
            }else {
                out.println(origr);
            }
        }
        in.close();
        out.close();
    }
}
