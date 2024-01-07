import java.util.*;
import java.io.*;

public class _231_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int[] cam = new int[3];
        for(int i = 0; i < 3; i++) cam[i] = Integer.parseInt(line.nextToken());
        line = new StringTokenizer(in.readLine());
        int[] point = new int[3];
        for(int i = 0; i < 3; i++) point[i] = Integer.parseInt(line.nextToken());
        int[] a = new int[6];
        line = new StringTokenizer(in.readLine());
        for(int i = 0; i < 6; i++) a[i] = Integer.parseInt(line.nextToken());
        int res = 0;
        if(cam[0] > point[0]) res += a[5];
        else if(cam[0] < 0) res += a[4];
        if(cam[1] > point[1]) res += a[1];
        else if(cam[1] < 0) res += a[0];
        if(cam[2] > point[2]) res += a[3];
        else if(cam[2] < 0) res += a[2];
        out.println(res);
        in.close();
        out.close();
    }
}
