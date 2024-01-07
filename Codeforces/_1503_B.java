import java.util.*;
import java.io.*;

public class _1503_B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        Pair[] set1 = new Pair[n * n / 2];
        Pair[] set2 = new Pair[n * n - set1.length];
        int c1 = 0;
        int c2 = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if((i + j) % 2 == 0) {
                    set2[c2] = new Pair(i, j);
                    c2++;
                }else {
                    set1[c1] = new Pair(i, j);
                    c1++;
                }
            }
        }
        c1 = 0;
        c2 = 0;
        for(int i = 0; i < n * n; i++) {
            if(c1 >= set1.length || c2 >= set2.length) {
                break;
            }
            int prevent = oppmove(in);
            if(prevent == 1) {
                int r = set2[c2].a;
                int c = set2[c2].b;
                move(r, c, 2);
                c2++;
            }else {
                int r = set1[c1].a;
                int c = set1[c1].b;
                move(r, c, 1);
                c1++;
            }
        }
        Pair[] uset;
        int c;
        int p;
        if(c1 >= set1.length) {
            uset = set2;
            c = c2;
            p = 1;
        }else {
            uset = set1;
            c = c1;
            p = 2;
        }
        while(c < uset.length) {
            int prevent = oppmove(in);
            int move = 1;
            for(int i = 1; i <= 3; i++) {
                if(i != prevent && i != p) move = i;
            }
            move(uset[c].a, uset[c].b, move);
            c++;
        }
        in.close();
    }
    static void move(int i, int j, int c) {
        i++;
        j++;
        System.out.println(c + " " + i + " " + j);
        System.out.flush();
    }
    static int oppmove(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }
    static class Pair {
        int a, b;
        Pair(int aa, int bb) {
            a = aa;
            b = bb;
        }
    }
}
