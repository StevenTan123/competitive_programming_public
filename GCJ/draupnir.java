import java.util.*;
import java.io.*;

public class draupnir {
    static long[] pow210 = new long[3];
    static long[] pow50 = new long[6];
    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 6; i++) {
            if(i < 3) pow210[i] = (long)Math.pow(2, (int)(210 / (i + 4)));
            pow50[i] = (long)Math.pow(2, (int)(50 / (i + 1)));
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(in.readLine());
        int t = Integer.parseInt(line.nextToken());
        int w = Integer.parseInt(line.nextToken());
        while(t-- > 0) {
            long day50 = query(50, in);
            long day210 = query(210, in);
            long[] vals = new long[6];
            for(int i = 0; i < 3; i++) {
                vals[i + 3] = day210 / pow210[i];
                day210 = day210 % pow210[i];
            }
            for(int i = 3; i < 6; i++) {
                day50 -= vals[i] * pow50[i];
            }
            for(int i = 0; i < 3; i++) {
                vals[i] = day50 / pow50[i];
                day50 = day50 % pow50[i];
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 6; i++) {
                sb.append(vals[i]);
                if(i < 5) sb.append(' ');
            }
            System.out.println(sb.toString());
            System.out.flush();
            int verdict = Integer.parseInt(in.readLine());
            if(verdict < 0) return;
        }
        in.close();
    }
    static long query(int day, BufferedReader in) throws IOException {
        System.out.println(day);
        System.out.flush();
        return Long.parseLong(in.readLine());
    }
}
