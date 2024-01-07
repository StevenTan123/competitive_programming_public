import java.util.*;
import java.io.*;

public class _1810_E {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int t = Integer.parseInt(in.readLine());
		while (t-- > 0) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(line.nextToken());
            int m = Integer.parseInt(line.nextToken());
            int[] a = new int[n];
            line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            ArrayList<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < m; i++) {
                line = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(line.nextToken()) - 1;
                int v = Integer.parseInt(line.nextToken()) - 1;
                graph[u].add(v);
                graph[v].add(u);
            }
            boolean possible = false;
            int[] visited = new int[n];
            Arrays.fill(visited, -1);
            for (int i = 0; i < n; i++) {
                if (visited[i] == -1) {
                    if (search(graph, a, visited, i)) {
                        possible = true;
                        break;
                    }
                }
            }	
            out.println(possible ? "YES" : "NO");
		}

		in.close();
		out.close();
	}
    static boolean search(ArrayList<Integer>[] graph, int[] a, int[] visited, int start) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                return a[x] - a[y];
            }
        });
        int count = 0;
        pq.add(start);
        while (pq.size() > 0) {
            int cur = pq.poll();
            if (visited[cur] == start || count < a[cur]) {
                continue;
            }
            visited[cur] = start;
            count++;
            for (int nei : graph[cur]) {
                pq.add(nei);
            }
        }
        return count == a.length;
    }
}
