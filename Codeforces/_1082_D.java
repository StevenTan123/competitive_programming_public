import java.util.*;
import java.io.*;

public class _1082_D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        int[][] a = new int[n][2];
        int[] left = new int[n];
        StringTokenizer line = new StringTokenizer(in.readLine());
        for(int i = 0; i < n; i++) {
            a[i][0] = Integer.parseInt(line.nextToken());
            a[i][1] = i;
            left[i] = a[i][0];
        }
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return a1[0] - a2[0];
            }
        });
        ArrayList<Pair> edges = new ArrayList<Pair>();
        ArrayList<Integer> backbone = new ArrayList<Integer>();
        boolean possible = true;
        int count = 0;
        for(int i = n - 1; i >= 0; i--) {
            if(left[a[i][1]] > 1) {
                backbone.add(a[i][1]);
                if(i < n - 1) {
                    edges.add(new Pair(a[i][1], a[i + 1][1]));
                    left[a[i][1]]--;
                    left[a[i + 1][1]]--;
                }
            }else if(count < 2) {
                if(backbone.size() == 0) {
                    possible = false;
                    break;
                }
                if(count == 0) {
                    int first = backbone.get(0);
                    left[first]--;
                    edges.add(new Pair(a[i][1], first));
                    backbone.add(0, a[i][1]);
                    left[a[i][1]]--;
                }else {
                    int last = backbone.get(backbone.size() - 1);
                    left[last]--;
                    edges.add(new Pair(a[i][1], last));
                    backbone.add(a[i][1]);
                    left[a[i][1]]--;
                }
                count++;
            }else {
                boolean possible2 = false;
                for(int j = 0; j < backbone.size(); j++) {
                    int node = backbone.get(j);
                    if(left[node] > 0) {
                        left[node]--;
                        edges.add(new Pair(a[i][1], node));
                        possible2 = true;
                        break;
                    }
                }
                if(!possible2) {
                    possible = false;
                    break;
                }
            }
        }
        if(possible) {
            out.println("YES " + (backbone.size() - 1));
            out.println(edges.size());
            for(Pair edge : edges) {
                edge.a++;
                edge.b++;
                out.println(edge.a + " " + edge.b);
            }
        }else {
            out.println("NO");
        }
        in.close();
        out.close();
    }
    static class Pair {
        int a, b;
        Pair(int aa, int bb) {
            a = aa;
            b = bb;
        }
    }
}
