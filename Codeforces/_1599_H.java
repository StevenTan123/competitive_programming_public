import java.util.*;
import java.io.*;

public class _1599_H {
    static final int MAX = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int[] corners = new int[4];
        int[][] corner_coords = new int[][] { {1, 1}, {MAX, 1}, {1, MAX}, {MAX, MAX} };
        for (int i = 0; i < 4; i++) {
            corners[i] = query(in, corner_coords[i][0], corner_coords[i][1]);
            if (corners[i] == -1) {
                return;
            }
        }

        int[] left_edge = find_edge_point(corners[2], corners[0]);
        int[] top_edge = find_edge_point(corners[3], corners[2]);
        int temp = top_edge[1];
        top_edge[1] = MAX - top_edge[0] + 1;
        top_edge[0] = temp;

        int dist_left = query(in, left_edge[0], left_edge[1]);
        if (dist_left == -1) {
            return;
        }
        int dist_top = query(in, top_edge[0], top_edge[1]);
        if (dist_top == -1) {
            return;
        }

        int x = left_edge[0] + dist_left;
        int y = top_edge[1] - dist_top;
        int p = x + (MAX - x + MAX - y) - corners[3];
        int q = y - ((MAX - p + y - 1) - corners[1]);
        System.out.println("! " + x + " " + q + " " + p + " " + y);
        System.out.flush();

        in.close();
    }

    // Given top left distance and bottom left distance, return 
    // x and y coordinates of an edge point to try.
    static int[] find_edge_point(int dist1, int dist2) {
        int x = 1;
        int y = 1;
        int top_left = MAX - (dist1 - 1);
        int bottom_left = 1 + dist2 - 1;
        if (top_left > bottom_left) {
            y = bottom_left;
        } else {
            int diff = bottom_left - top_left;
            x = 1 + diff / 2;
            y = top_left + diff / 2;
        }
        return new int[] {x, y};
    }

    static int query(BufferedReader in, int x, int y) throws IOException {
        System.out.println("? " + x + " " + y);
        System.out.flush();
        return Integer.parseInt(in.readLine());
    }
}
