import java.util.*;
import java.io.*;

public class twisty_passages {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int k = Integer.parseInt(line.nextToken());
            in.readLine();
            if(n <= k) {
                int sum = 0;
                for(int i = 0; i < n; i++) {
                    System.out.println("T " + (i + 1));
                    System.out.flush();

                    line = new StringTokenizer(in.readLine());
                    line.nextToken();
                    sum += Integer.parseInt(line.nextToken());
                }
                System.out.println("E " + (sum / 2));
                System.out.flush();
            }else {
                int[] degrees = new int[n + 1];
                Arrays.fill(degrees, -1);
                int sum = 0;
                for(int i = 0; i < k / 2; i++) {
                    System.out.println("T " + (rand.nextInt(n) + 1));
                    System.out.flush();

                    line = new StringTokenizer(in.readLine());
                    int room = Integer.parseInt(line.nextToken());
                    int degree = Integer.parseInt(line.nextToken());
                    degrees[room] = degree;
                    sum += degree;

                    System.out.println("W");
                    System.out.flush();   
                    
                    line = new StringTokenizer(in.readLine());
                    room = Integer.parseInt(line.nextToken());
                    degree = Integer.parseInt(line.nextToken());
                    degrees[room] = degree;
                }
                double avg = (double)sum / (k / 2);
                double res = 0;
                for(int i = 1; i <= n; i++) {
                    if(degrees[i] == -1) {
                        res += avg;
                    }else {
                        res += degrees[i];
                    }
                }
                System.out.println("E " + (long)(res / 2));
            }
        }
        in.close();
    }
}
