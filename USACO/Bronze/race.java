import java.util.*;
import java.io.*;

public class race {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("race.in"));
        PrintWriter out = new PrintWriter("race.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int k = Integer.parseInt(line.nextToken());
        int n = Integer.parseInt(line.nextToken());
        for(int i = 0; i < n; i++) {
            out.println(time(k, Integer.parseInt(in.readLine())));
        }
        in.close();
        out.close();
    }
    static int time(int k, int x) {
        int counter = 1;
        int sum = 0;
        while(true) {
            sum += counter;
            if(counter > x) {
                if(sum + sum(x, counter - 1) >= k) {
                    return counter + counter - x;
                }else if(sum + sum(x, counter) >= k) {
                    return counter + counter - x + 1;
                }
            }else {
                if(sum >= k) {
                    return counter;
                }
                if(counter == x) {
                    if(sum + counter >= k) {
                        return counter + 1;
                    }
                }
            }
            counter++;
        }
    }
    static int sum(int a, int b) {
        return (a + b) * (b - a + 1) / 2;
    }
}
