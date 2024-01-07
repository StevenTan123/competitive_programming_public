import java.util.*;
import java.io.*;

public class _1879_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(in.readLine());
        int[] par = new int[n];
        int[] degree = new int[n];
        ArrayList<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        StringTokenizer line = new StringTokenizer(in.readLine());
        for (int i = 1; i < n; i++) {
            par[i] = Integer.parseInt(line.nextToken()) - 1;
            degree[par[i]]++;
            degree[i]++;
            tree[par[i]].add(i);
        }

        int k = 0;
        int[] edge_colors = new int[n];
        if (degree[0] == n - 1) {
            k = 1;
            for (int i = 1; i < n; i++) {
                edge_colors[i] = 1;
            }
        } else {
            boolean two_colorable = true;
            for (int i = 0; i < n; i++) {
                if (par[i] != i && degree[i] == 2) {
                    int child = tree[i].get(0);
                    if (edge_colors[child] != 0 && edge_colors[child] != 2) {
                        two_colorable = false;
                        break;
                    }
                    edge_colors[child] = 2;
                    
                    int cur = i;
                    int color = 1;
                    while (par[cur] != cur) {
                        if (edge_colors[cur] == color) {
                            break;
                        } else if (edge_colors[cur] == 0) {
                            edge_colors[cur] = color;
                        } else {
                            two_colorable = false;
                            break;
                        }
                        cur = par[cur];
                        color = 3 - color;
                    }
                    if (!two_colorable) {
                        break;
                    }
                }
            }
            if (!two_colorable) {
                edge_colors = new int[n];
                three_color(tree, 0, 0, edge_colors);
                k = 3;
            } else {
                two_color(tree, 0, 0, edge_colors);
                k = 2;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            sb.append(edge_colors[i]);
            sb.append(' ');
        }
        System.out.println(k);
        System.out.flush();
        System.out.println(sb.toString());
        System.out.flush();
    
        int[] input = new int[3];
        while (true) {
            int status = Integer.parseInt(in.readLine());
            if (status == 1 || status == -1) {
                break;
            }
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < k; i++) {
                input[i] = Integer.parseInt(line.nextToken());
            }
            if (k == 1) {
                System.out.println(1);
                System.out.flush();
            } else if (k == 2) {
                if (input[0] == 1) {
                    System.out.println(1);
                    System.out.flush();
                } else {
                    System.out.println(2);
                    System.out.flush();
                }
            } else {
                if (input[0] == 1 && input[1] == 1 && input[2] == 0) {
                    System.out.println(1);
                    System.out.flush();
                } else if (input[0] == 0 && input[1] == 1 && input[2] == 1) {
                    System.out.println(2);
                    System.out.flush();
                } else if (input[0] == 1 && input[1] == 0 && input[2] == 1) {
                    System.out.println(3);
                    System.out.flush();
                } else if (input[0] == 1) {
                    System.out.println(1);
                    System.out.flush();
                } else if (input[1] == 1) {
                    System.out.println(2);
                    System.out.flush();
                } else if (input[2] == 1) {
                    System.out.println(3);
                    System.out.flush();
                }
            }
        }

        in.close();
    }
    static void two_color(ArrayList<Integer>[] tree, int cur, int color, int[] edge_colors) {
        for (int child : tree[cur]) {
            if (edge_colors[child] == 0) {
                edge_colors[child] = color == 1 ? 2 : 1;
            }
            two_color(tree, child, edge_colors[child], edge_colors);
        }
    }
    static void three_color(ArrayList<Integer>[] tree, int cur, int color, int[] edge_colors) {
        if (tree[cur].size() > 1) {
            for (int child : tree[cur]) {
                edge_colors[child] = color == 1 ? 2 : 1;
                three_color(tree, child, edge_colors[child], edge_colors);
            }
        } else if (tree[cur].size() == 1) {
            int child = tree[cur].get(0);
            edge_colors[child] = color + 1;
            if (edge_colors[child] == 4) {
                edge_colors[child] = 1;
            }
            three_color(tree, child, edge_colors[child], edge_colors);
        }
    }
}
