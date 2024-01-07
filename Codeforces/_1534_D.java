import java.util.*;
import java.io.*;

public class _1534_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] q0 = query(0, n, in);
        int even = 0;
        int odd = 0;
        for(int i = 1; i < n; i++) {
            if(q0[i] % 2 == 1) {
                odd++;
            }else {
                even++;
            }
        }
        int parity = 0;
        if(odd < even) {
            parity = 1;
        }
        int[] par = new int[n];
        for(int i = 1; i < n; i++) {
            if(q0[i] % 2 == parity) {
                int[] q = query(i, n, in);
                for(int j = 0; j < n; j++) {
                    if(q[j] == 1) {
                        if(q0[j] < q0[i]) {
                            par[i] = j; 
                        }else {
                            par[j] = i;
                        }
                    }
                }
            }
        }
        System.out.println("!");
        for(int i = 1; i < n; i++) {
            System.out.println((par[i] + 1) + " " + (i + 1));
        }
        System.out.flush();
        in.close();
    }
    static int[] query(int node, int n, BufferedReader in) throws IOException{
        System.out.println("? " + (node + 1));
        System.out.flush();
        StringTokenizer line = new StringTokenizer(in.readLine());
        int[] q = new int[n];
        for(int i = 0; i < n; i++) {
            q[i] = Integer.parseInt(line.nextToken());
        }
        return q;
    }
}