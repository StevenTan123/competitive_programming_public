import java.util.*;
import java.io.*;

public class crepecart {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(line.nextToken());
            int q = Integer.parseInt(line.nextToken());
            int[][] pointsx = new int[p][3];
            int[][] pointsy = new int[p][3];
            for(int i = 0; i < p; i++) {
                line = new StringTokenizer(in.readLine());
                pointsx[i][0] = Integer.parseInt(line.nextToken());
                pointsx[i][1] = Integer.parseInt(line.nextToken());
                char c = line.nextToken().charAt(0);
                if(c == 'E') pointsx[i][2] = 0;
                else if(c == 'W') pointsx[i][2] = 1;
                else if(c == 'N') pointsx[i][2] = 2;
                else pointsx[i][2] = 3;
                pointsy[i][0] = pointsx[i][1];
                pointsy[i][1] = pointsx[i][0];
                pointsy[i][2] = pointsx[i][2];
            }
            Comparator<int[]> comp = new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            };
            Arrays.sort(pointsx, comp);
            Arrays.sort(pointsy, comp);
            int x = sweep(pointsx, 0);
            int y = sweep(pointsy, 1);
            String res = "Case #" + t + ": ";
            out.println(res + x + " " + y);
        }
        in.close();
        out.close();
    }
    static int sweep(int[][] points, int type) {
        int max = converge(points, type, 0);
        int coord = 0;
        for(int i = 0; i < points.length; i++) {
            if(type == 0) {
                if(points[i][2] >= 2) continue;
            }else {
                if(points[i][2] < 2) continue;
            }
            int num = converge(points, type, points[i][0] + 1);
            if(num > max) {
                max = num;
                coord = points[i][0] + 1;
            }
        }
        return coord;
    }
    static int converge(int[][] points, int type, int coord) {
        int res = 0;
        for(int i = 0; i < points.length; i++) {
            if(type == 0) {
                if(points[i][2] >= 2) continue;
            }else {
                if(points[i][2] < 2) continue;
            }
            if(points[i][0] < coord && points[i][2] % 2 == 0) res++;
            if(points[i][0] > coord && points[i][2] % 2 == 1) res++;
        }
        return res;
    }
}
