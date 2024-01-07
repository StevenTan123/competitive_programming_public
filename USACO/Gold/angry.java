import java.util.*;
import java.io.*;

public class angry {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("angry.in"));
        PrintWriter out = new PrintWriter("angry.out");
        int n = Integer.parseInt(in.readLine());
        int[] haybales = new int[n];
        for(int i = 0; i < n; i++) {
            haybales[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(haybales);
        int[] haybales2 = new int[n];
        for(int i = 0; i < n; i++) haybales2[i] = haybales[n - i - 1];
        int[] forward = new int[n];
        int[] backward2 = new int[n];
        minpower(haybales, forward);
        minpower(haybales2, backward2);
        int[] backward = new int[n];
        for(int i = 0; i < n; i++) backward[i] = backward2[n - i - 1];
        int l = 0;
        int r = n - 1;
        double res = Integer.MAX_VALUE;
        while(l < r) {
            res = Math.min(res, Math.max((haybales[r] - haybales[l]) / (double)2, Math.max(forward[l] + 1, backward[r] + 1)));
            if(forward[l + 1] < backward[r - 1]) {
                l++;
            }else {
                r--;
            }
        }
        int integ = (int)(res * 10);
        out.println(integ / 10 + "." + integ % 10);
        in.close();
        out.close();
    }
    static void minpower(int[] haybales, int[] arr) {
        int lpointer = 0;
        arr[0] = -1;
        for(int i = 1; i < haybales.length; i++) {
            while(lpointer < i && Math.abs(haybales[i] - haybales[lpointer]) > arr[lpointer] + 1) {
                lpointer++;
            }
            if(lpointer < i) {
                arr[i] = Math.min(Math.abs(haybales[i] - haybales[lpointer - 1]), arr[lpointer] + 1);
            }else {
                arr[i] = Math.abs(haybales[i] - haybales[lpointer - 1]);
            }
        }
    }
}
