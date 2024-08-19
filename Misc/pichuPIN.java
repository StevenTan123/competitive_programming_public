import java.util.*;
import java.io.*;

public class pichuPIN {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        out.println(n * 2 - 1);
        in.close();
        out.close();
    }
}
