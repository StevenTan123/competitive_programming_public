import java.util.*;
import java.io.*;

public class _1600_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        int lbound = n - 1;
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
            if(i > 0 && a[i] <= a[i - 1] && lbound == n - 1) {
                lbound = i - 1;
            }
        }
        int rbound = 0;
        for(int i = n - 2; i >= 0; i--) {
            if(a[i] <= a[i + 1]) {
                rbound = i + 1;
                break;
            }
        }
        int l = 0;
        int r = n - 1;
        int winner = -1;
        int moves = 0;
        while(true) {
            if(a[l] > a[r]) {
                if((lbound - l + 1) % 2 == 1) {
                    winner = moves % 2;
                    break;
                }else {
                    r--;
                    if(r < rbound) {
                        winner = moves % 2;
                        break;
                    }
                }
            }else if (a[r] > a[l]) {
                if((r - rbound + 1) % 2 == 1) {
                    winner = moves % 2;
                    break;
                }else {
                    l++;
                    if (l > lbound) {
                        winner = moves % 2;
                        break;
                    }
                }
            }else {
                if((lbound - l + 1) % 2 == 1 || (r - rbound + 1) % 2 == 1) {
                    winner = moves % 2;
                }else {
                    winner = (moves + 1) % 2;
                }
                break;
            }
            moves++;
        }
        out.println(winner == 0 ? "Alice" : "Bob");
        in.close();
        out.close();
    }
}