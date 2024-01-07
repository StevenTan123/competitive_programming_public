import java.util.*;
import java.io.*;

public class walk_gold {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("walk.in"));
        PrintWriter out = new PrintWriter("walk.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        long ans = ((long)(k - 1) * 2019201913 + (long)n * 2019201949) % 2019201997;
        out.println(ans);
        in.close();
        out.close();
    }
}
