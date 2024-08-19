import java.util.*;
import java.io.*;

public class ritwin_comparisons {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
        int n = Integer.parseInt(in.readLine());
        boolean contradict = false;
        int equal = Integer.MIN_VALUE;
        int left = Integer.MIN_VALUE;
        int right = Integer.MAX_VALUE;
        ArrayList<Integer> banned = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            String op = line.nextToken();
            int y = Integer.parseInt(line.nextToken());
            if (op.equals("==")) {
                if (equal == Integer.MIN_VALUE) {
                    equal = y;
                } else {
                    contradict = true;
                    break;
                }
            } else if (op.equals("<")) {
                right = Math.min(right, y - 1);
            } else if (op.equals(">")) {
                left = Math.max(left, y + 1);
            } else if (op.equals("<=")) {
                right = Math.min(right, y);
            } else if (op.equals(">=")) {
                left = Math.max(left, y);
            } else {
                banned.add(y);
            }
        }
        if (left > right || equal != Integer.MIN_VALUE && (equal < left || equal > right) || banned.contains(equal)) {
            contradict = true;
        }
        if (contradict) {
            out.println(0);
        } else {
            if (equal != Integer.MIN_VALUE) {
                out.println(1);
            } else {
                int ret = right - left + 1;
                for (int ban : banned) {
                    if (ban >= left && ban <= right) {
                        ret--;
                    }
                }
                out.println(ret);
            }
        }

		in.close();
		out.close();
	}
}
