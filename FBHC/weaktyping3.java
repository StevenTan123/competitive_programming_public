import java.util.*;
import java.io.*;

public class weaktyping3 {
    static final long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("weak_typing_chapter_3_input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int tt = Integer.parseInt(in.readLine());
        for(int t = 1; t <= tt; t++) {
            int k = Integer.parseInt(in.readLine());
            String u = in.readLine();
            State cur = new State(0, -1, -1, 0, 0, 0, 0, ' ', ' ');
            for(int i = 0; i < k; i++) {
                char c = u.charAt(i);
                if(c == '.') {
                    State.concatenate(cur, cur);
                }else {
                    int first = -1;
                    int last = -1;
                    char fchar = ' ';
                    char lchar = ' ';
                    if(c != 'F') {
                        first = 1;
                        last = 1;
                        fchar = c;
                        lchar = c;
                    }
                    State new_state = new State(1, first, last, 0, 0, 0, 0, fchar, lchar);
                    State.concatenate(cur, new_state);
                }
            }
            String res = "Case #" + t + ": ";
            out.println(res + cur.res);
        }
        in.close();
        out.close();
    }
    static long modadd(long a, long b) {
        return (a + b + MOD) % MOD;
    }
    static long modmult(long a, long b) {
        return a * b % MOD;
    }
    static class State {
        long length, first, last, left, right, switches, res;
        char fchar, lchar;
        State(long len, long fir, long las, long lef, long rig, long swi, long r, char fch, char lch) {
            length = len;
            first = fir;
            last = las;
            left = lef;
            right = rig;
            switches = swi;
            res = r;
            fchar = fch;
            lchar = lch;
        }
        static void concatenate(State a, State b) {
            a.res = modadd(modadd(a.res, b.res), modadd(modmult(a.left, b.length), modmult(a.length, b.right)));
            a.left = modadd(modadd(a.left, b.left), modmult(a.length, b.switches));
            a.right = modadd(modadd(a.right, b.right), modmult(b.length, a.switches));
            a.switches = modadd(a.switches, b.switches);
            if(a.last != -1 && b.first != -1 && a.lchar != b.fchar) {
                a.res = modadd(a.res, modmult(a.last, b.length - b.first + 1));
                a.left = modadd(a.left, a.last);
                a.right = modadd(a.right, b.length - b.first + 1);
                a.switches = modadd(a.switches, 1);
            }
            if(a.first == -1 && b.first != -1) {
                a.first = modadd(b.first, a.length);
                a.fchar = b.fchar;
            }
            if(b.last != -1) {
                a.last = modadd(b.last, a.length);
                a.lchar = b.lchar;
            }
            a.length = modadd(a.length, b.length);
        }
    }
}
