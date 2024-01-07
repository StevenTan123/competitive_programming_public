public class st_range_add_global_max {
    static int M = 1 << 18;
    // Global max stored at c[1][0]
    static int[][] c = new int[M << 1][2];

    // Inserts v at i
    static void insert(int i, int v) {
        for (i += M; i > 0; i >>= 1) {
            c[i][0] = v;
            v = Math.max(v, c[i ^ 1][0]);
        }
    }
    // Adds v to 0...i
    static void add(int i, int v) {
        for (i += M + 1; i > 1; i >>= 1) {
            int v1 = (i & 1) > 0 ? v : 0;
            c[i ^ 1][0] += v1;
            c[i ^ 1][1] += v1;
            c[i >> 1][0] = Math.max(c[i][0], c[i ^ 1][0]) + c[i >> 1][1];
        }
    }
}
