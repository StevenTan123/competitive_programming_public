import java.util.*;
import java.io.*;

public class _1839_E {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		StringTokenizer line = new StringTokenizer(in.readLine());
        int[] a = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            sum += a[i];
        }

        boolean[][] dp = new boolean[n][sum + 1];
        dp[0][0] = true;
        dp[0][a[0]] = true;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= sum; j++) {
                if (dp[i][j]) {
                    dp[i + 1][j] = true;
                    dp[i + 1][j + a[i + 1]] = true;
                }
            }
        }

        if (sum % 2 == 0 && dp[n - 1][sum / 2]) {
            TreeSet<Integer> set1 = new TreeSet<Integer>();
            int cur_sum = sum / 2;
            for (int i = n - 1; i > 0; i--) {
                if (cur_sum - a[i] >= 0 && dp[i - 1][cur_sum - a[i]]) {
                    cur_sum -= a[i];
                    set1.add(i);
                }
            }
            if (cur_sum == a[0]) {
                set1.add(0);
            }

            TreeSet<Integer> set2 = new TreeSet<Integer>();
            for (int i = 0; i < n; i++) {
                if (!set1.contains(i)) {
                    set2.add(i);
                }
            }

            System.out.println("Second");
            System.out.flush();
            while (set1.size() > 0 || set2.size() > 0) {
                int i = Integer.parseInt(in.readLine()) - 1;
                if (set1.contains(i)) {
                    int j = set2.first();
                    int min = Math.min(a[i], a[j]);
                    a[i] -= min;
                    a[j] -= min;
                    if (a[i] == 0) {
                        set1.remove(i);
                    }
                    if (a[j] == 0) {
                        set2.remove(j);
                    }
                    System.out.println(j + 1);
                } else {
                    int j = set1.first();
                    int min = Math.min(a[i], a[j]);
                    a[i] -= min;
                    a[j] -= min;
                    if (a[i] == 0) {
                        set2.remove(i);
                    }
                    if (a[j] == 0) {
                        set1.remove(j);
                    }
                    System.out.println(j + 1);
                }
                System.out.flush();
            }
            in.readLine();
        } else {
            System.out.println("First");
            System.out.flush();

            TreeSet<Integer> left = new TreeSet<Integer>();
            for (int i = 0; i < n; i++) {
                left.add(i);
            }

            while (left.size() > 0) {
                int i = left.pollFirst();
                System.out.println(i + 1);
                System.out.flush();

                int j = Integer.parseInt(in.readLine()) - 1;
                if (j == -1) {
                    break;
                }
                left.remove(j);

                int min = Math.min(a[i], a[j]);
                a[i] -= min;
                a[j] -= min;
                if (a[i] > 0) {
                    left.add(i);
                }
                if (a[j] > 0) {
                    left.add(j);
                }
            }
        }

		in.close();
	}
}
