import java.util.*;
import java.io.*;

public class yearofcow {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int[] ancestors = new int[n + 1];
        for(int i = 0; i < n; i++) {
            ancestors[i] = Integer.parseInt(in.readLine());
        }
        ancestors[n] = 0;
        Arrays.sort(ancestors);
        ArrayList<Integer> gaps = new ArrayList<Integer>();
        int top = 0;
        int pointer = n;
        int prev = -1;
        while(pointer >= 0) {
            int ceil = ancestors[pointer] / 12;
            if(ancestors[pointer] % 12 != 0) ceil++;
            ceil *= 12;
            if(pointer == n) top = ceil;
            while(pointer >= 0 && ancestors[pointer] >= ceil - 12) {
                pointer--;
            }
            if(prev > -1) {
                gaps.add(prev - ceil);
            }
            prev = ceil - 12;
        }
        Collections.sort(gaps);
        int res = top;
        for(int i = 0; i < k - 1; i++) {
            if(gaps.size() - i - 1 >= 0) res -= gaps.get(gaps.size() - i - 1);
        }
        out.println(res);
        in.close();
        out.close();
    }
}
