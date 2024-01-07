import java.util.*;
import java.io.*;

public class bullseye {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(in.readLine());
        int t = Integer.parseInt(line.nextToken());
        int a = Integer.parseInt(line.nextToken());
        int b = Integer.parseInt(line.nextToken());
        while(t-- > 0) {
            int[] bounds = new int[4];
            boolean exit = false;
            for(int i = 0; i < 4; i++) {
                if(i < 2) {
                    bounds[i] = boundary(i, 0, 1000000000, in);
                }else {
                    bounds[i] = boundary(i, -1000000000, 0, in);
                }
                if(bounds[i] == -1) {
                    exit = true;
                    break;
                }
            }
            if(exit) continue;
            long xcoord = (bounds[0] + bounds[2]) / 2;
            long ycoord = (bounds[1] + bounds[3]) / 2;
            boolean found = false;
            for(long i = xcoord - 5; i <= xcoord + 5; i++) {
                for(long j = ycoord - 5; j <= ycoord + 5; j++) {
                    System.out.println(i + " " + j);
                    System.out.flush();
                    String verdict = in.readLine();
                    if(verdict.equals("CENTER")) {
                        found = true;
                        break;
                    }
                }
                if(found) {
                    break;
                }
            }
        }
        in.close();
    }
    static int boundary(int type, int l, int r, BufferedReader in) throws IOException {
        int res = -1;
        while(l <= r) {
            int avg = (l + r) / 2;
            if(type % 2 == 0) {
                System.out.println(avg + " " + 0);
            }else {
                System.out.println(0 + " " + avg);
            }
            System.out.flush();
            String verdict = in.readLine();
            if(verdict.equals("HIT")) {
                if(type < 2) {
                    res = avg;
                    l = avg + 1;
                }else {
                    res = avg;
                    r = avg - 1;
                }
            }else if(verdict.equals("MISS")) {
                if(type < 2) {
                    r = avg - 1;
                }else {
                    l = avg + 1;
                }
            }else {
                return -1;
            }
        }
        return res;
    }
}
