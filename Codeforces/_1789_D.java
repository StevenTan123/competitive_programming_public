import java.util.*;
import java.io.*;

public class _1789_D {
	public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String astr = in.readLine();
            String bstr = in.readLine();
            boolean reverse = false;
            if (astr.charAt(n - 1) == '1' || bstr.charAt(n - 1) == '1') {
                reverse = true;
            }
            int[] a = new int[n];
            int[] b = new int[n];
            int asum = 0;
            int bsum = 0;
            for (int i = 0; i < n; i++) {
                if (!reverse) {
                    a[i] = astr.charAt(i) == '0' ? 0 : 1;
                    b[i] = bstr.charAt(i) == '0' ? 0 : 1;
                } else {
                    a[i] = astr.charAt(n - i - 1) == '0' ? 0 : 1;
                    b[i] = bstr.charAt(n - i - 1) == '0' ? 0 : 1;
                }
                asum += a[i];
                bsum += b[i];
            }
            
            if (asum == 0 && bsum > 0 || asum > 0 && bsum == 0) {
                out.println(-1);
            } else if (asum == 0 && bsum == 0) {
                out.println(0);
            } else {
                ArrayList<Integer> ops = new ArrayList<Integer>();
                int first_a = -1;
                int first_b = -1;
                for (int i = 0; i < n; i++) {
                    if (first_a == -1 && a[i] == 1) {
                        first_a = i;
                    }
                    if (first_b == -1 && b[i] ==1 ) {
                        first_b = i;
                    } 
                }
                if (first_b < first_a) {
                    ops.add(first_a - first_b);
                    apply_op(a, first_a - first_b);
                }
                int start = Math.min(first_a, first_b);
                for (int i = start + 1; i < n; i++) {
                    if (a[i] != b[i]) {
                        ops.add(-i + start);
                        apply_op(a, -i + start);
                    }
                }
                int last = start;
                for (int i = start + 1; i < n; i++) {
                    if (a[i] == 1) {
                        last = i;
                    }
                }
                if (b[start] == 0 && last > start) {
                    for (int i = start; i >= 0; i--) {
                        if (a[i] != b[i]) {
                            ops.add(last - i);
                            apply_op(a, last - i);
                        }
                    }
                }
                out.println(ops.size());
                if (ops.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int op : ops) {
                        if (reverse) {
                            sb.append(-op);
                        } else {
                            sb.append(op);
                        }
                        sb.append(' ');
                    }
                    out.println(sb.toString());
                }
            }
        }

        in.close();
        out.close();
    }
    static void apply_op(int[] a, int shift) {
        if (shift >= 0) {
            for (int i = 0; i < a.length - shift; i++) {
                a[i] ^= a[i + shift];
            }
        } else {
            shift *= -1;
            for (int i = a.length - 1; i >= shift; i--) {
                a[i] ^= a[i - shift];
            }
        }
    }
}
