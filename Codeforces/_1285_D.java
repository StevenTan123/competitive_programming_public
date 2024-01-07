import java.util.*;
import java.io.*;

public class _1285_D {
    static final int[] pow = new int[31];
    public static void main(String[] args) throws IOException {
        pow[0] = 1;
        for(int i = 1; i < 31; i++) {
            pow[i] = pow[i - 1] * 2;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        ArrayList<Integer> a = new ArrayList<Integer>();
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a.add(Integer.parseInt(line.nextToken()));
        }
        out.println(min_max(a, 30));
        in.close();
        out.close();
    }
    static int min_max(ArrayList<Integer> nums, int bits) {
        if(bits == 0) {
            return 0;
        }
        ArrayList<Integer> zeros = new ArrayList<Integer>();
        ArrayList<Integer> ones = new ArrayList<Integer>();
        for(int num : nums) {
            int bit = num >> (bits - 1);
            if(bit == 0) {
                zeros.add(num);
            }else {
                ones.add(num - pow[bits - 1]);
            }
        }
        if(zeros.size() > 0 && ones.size() > 0) {
            return pow[bits - 1] + Math.min(min_max(zeros, bits - 1), min_max(ones, bits - 1));
        }else if(zeros.size() == 0) {
            return min_max(ones, bits - 1);
        }else {
            return min_max(zeros, bits - 1);
        }
    }
}
