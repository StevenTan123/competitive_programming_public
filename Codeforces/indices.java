import java.io.*;
import java.util.*;

public class indices {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    int t = Integer.parseInt(in.readLine());
    for(int i = 0; i < t; i++) {
      int n = Integer.parseInt(in.readLine());
      StringTokenizer line = new StringTokenizer(in.readLine());
      int[] arr = new int[n];
      for(int j = 0; j < n; j++) {
        arr[j] = Integer.parseInt(line.nextToken());
      }
      int[] prefixf = new int[n];
      int[] prefixb = new int[n];
      int minf = 0;
      int minb = n - 1;
      for(int j = 0; j < n; j++) {
        if(arr[j] < arr[minf]) minf = j;
        if(arr[n - j - 1] < arr[minb]) minb = n - j - 1; 
        prefixf[j] = minf;
        prefixb[n - j - 1] = minb;
      }
      boolean res = false;
      for(int j = 1; j < n - 1; j++) {
        if(arr[j] > arr[prefixf[j - 1]] && arr[j] > arr[prefixb[j + 1]]) {
          res = true;
          out.println("YES");
          out.println((prefixf[j - 1] + 1) + " " + (j + 1) + " " + (prefixb[j + 1] + 1));
          break;
        }
      }
      if(!res) {
        out.println("NO");
      }
    }
    in.close();
    out.close();
  }
}