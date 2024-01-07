import java.util.*;
import java.io.*;

public class _1493_B {
    static HashMap<Integer, Integer> reversed = new HashMap<Integer, Integer>();
    public static void main(String[] args) throws IOException {
        reversed.put(1, 1);
        reversed.put(8, 8);
        reversed.put(0, 0);
        reversed.put(2, 5);
        reversed.put(5, 2);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int h = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            String time = in.readLine();
            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(3, 5));
            int fhour = -1;
            int fmin = -1;
            while(fhour == -1) {
                int nhour = reverse(min, h);
                int nmin = reverse(hour, m);
                if(nhour != -1 && nmin != -1) {
                    fhour = hour;
                    fmin = min;
                    break;
                }
                if(min == m - 1) {
                    if(hour == h - 1) {
                        hour = 0;
                    }else {
                        hour++;
                    }
                    min = 0;
                }else {
                    min++;
                }
            }
            String fh = fhour + "";
            if(fhour < 10) fh = "0" + fh;
            String fm = fmin + "";
            if(fmin < 10) fm = "0" + fm;
            StringBuilder sb = new StringBuilder();
            sb.append(fh);
            sb.append(':');
            sb.append(fm);
            out.println(sb.toString());
        }
        in.close();
        out.close();
    }
    static int reverse(int num, int other) {
        int[] digits = new int[2];
        int pointer = 1;
        while(num != 0) {
            digits[pointer] = num % 10;
            num /= 10;
            pointer--;
        }
        int[] digits2 = new int[2];
        if(reversed.containsKey(digits[0])) {
            digits2[1] = reversed.get(digits[0]);
        }else {
            return -1;
        }
        if(reversed.containsKey(digits[1])) {
            digits2[0] = reversed.get(digits[1]);
        }else {
            return -1;
        }
        int res = digits2[0] * 10 + digits2[1];
        if(res < other) return res;
        return -1;
    }
}
