import java.util.*;
import java.io.*;

public class sleepy {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter out = new PrintWriter("sleepy.out");
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken()) - 1;
        }
        int[] bit = new int[n + 1];
        int prev = n;
        int index = 0;
        for(int i = n - 1; i >= 0; i--) {
            if(a[i] > prev) {
                index = i + 1;
                break;
            }else {
                update(bit, a[i], 1);
            }
            prev = a[i];
        }
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < index; i++) {
            int move = index - 1 - i + sum(bit, a[i]);
            update(bit, a[i], 1);
            res.append(move);
            if(i < index - 1) res.append(' ');
        }
        out.println(index);
        out.println(res.toString());
        in.close();
        out.close();
    }
    static void update(int[] bit, int index, int add) {
		index += 1;
		while(index < bit.length) {
			bit[index] += add;
			index += index & -index;
		}
	}
	static int sum(int[] bit, int index) {
		index += 1;
		int res = 0;
		while(index > 0) {
			res += bit[index];
			index -= index & -index;
		}
		return res;
	}
}
