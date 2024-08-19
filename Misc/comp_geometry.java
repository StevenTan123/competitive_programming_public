import java.util.*;
import java.io.*;

public class comp_geometry {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int T = Integer.parseInt(in.readLine());
		while (T-- > 0) {	
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            long[] x = new long[2 * n];
            long[] y = new long[2 * n];
            for (int i = 0; i < n; i++) {
                line = new StringTokenizer(in.readLine());
                x[i] = Integer.parseInt(line.nextToken());
                y[i] = Integer.parseInt(line.nextToken());
                x[i + n] = x[i];
                y[i + n] = y[i];
            }
            int a = k + 1;
            int b = 0;
            int c = k;
            long poly_area = shoelace(x, y, b, c);
            long tri_area = 0;
            long res = 0;
            for (int i = 0; i < n; i++) {
                a = Math.max(a, c + 1);
                tri_area = triangle_area(x[a], y[a], x[b], y[b], x[c], y[c]);
                while (a < b + n) {
                    a++;
                    long new_tri_area = triangle_area(x[a], y[a], x[b], y[b], x[c], y[c]);
                    if (new_tri_area > tri_area) {
                        tri_area = new_tri_area;
                    } else {
                        a--;
                        break;
                    }
                }
                res = Math.max(res, Math.abs(poly_area) + tri_area);

                poly_area += x[c] * y[c + 1] + x[c + 1] * y[b + 1] - x[b] * y[b + 1] - x[c] * y[b];
                poly_area -= y[c] * x[c + 1] + y[c + 1] * x[b + 1] - y[b] * x[b + 1] - y[c] * x[b]; 

                b++;
                c++;
            }
            out.println((double) res / 2);
		}
		
        in.close();
		out.close();
	}
    static long shoelace(long[] x, long[] y, int a, int b) {
        long term1 = 0;
        long term2 = 0;
        for (int i = a; i < b; i++) {
            term1 += x[i] * y[i + 1];
            term2 += y[i] * x[i + 1];
        }
        term1 += x[b] * y[a];
        term2 += y[b] * x[a];
        return term1 - term2;
    }
    static long triangle_area(long x1, long y1, long x2, long y2, long x3, long y3) {
        return Math.abs( (x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1) );
    }
}
