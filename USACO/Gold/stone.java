import java.util.*;
import java.io.*;

public class stone {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        StringTokenizer line = new StringTokenizer(in.readLine());
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(line.nextToken());
        }
        Arrays.sort(a);
        int[][] occurences = new int[1000005][2];
        int pointer = 0;
        for(int i = 0; i < occurences.length; i++) {
            while(pointer < n && a[pointer] < i) {
                pointer++;
            }
            if(pointer >= n) {
                occurences[i][0] = n;
                occurences[i][1] = n;
            }else if(a[pointer] > i) {
                occurences[i][0] = pointer;
                occurences[i][1] = pointer;
            }else {
                occurences[i][0] = pointer;
                while(pointer < n && a[pointer] == i) {
                    pointer++;
                }
                occurences[i][1] = pointer;
            }
        }
        long res = 0;
        for(int i = 1; i < occurences.length; i++) {
            int[][] odds = new int[2][2];
            int numodd = 0;
            for(int j = 1; j * i < occurences.length; j++) {
                int l = occurences[j * i][0];
                int rval = j * i + i - 1;
                int r = rval >= occurences.length ? n : occurences[rval][1];
                int num = r - l;
                if(num % 2 == 1) {
                    if(numodd >= 2) {
                        numodd++;
                        break;
                    }else {
                        odds[numodd][0] = j;
                        odds[numodd][1] = num;
                        numodd++;
                    }
                }
            }
            if(numodd == 2 && odds[1][0] == odds[0][0] + 1) {
                res += odds[1][1];
            }else if(numodd == 1 && odds[0][0] == 1) {
                res += odds[0][1];
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
}
