import java.util.*;
import java.io.*;

public class piepie {
    public static void main(String[] args) throws Exception {
    		BufferedReader in = new BufferedReader(new FileReader("piepie.in"));
    		PrintWriter out = new PrintWriter("piepie.out");
        StringTokenizer line = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(line.nextToken());
        int d = Integer.parseInt(line.nextToken());
        pie[] bessie = new pie[n];
        pie[] elsie = new pie[n];
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            int t1 = Integer.parseInt(line.nextToken());
            int t2 = Integer.parseInt(line.nextToken());
            bessie[i] = new pie(t1, t2, i);
        }
        for(int i = 0; i < n; i++) {
            line = new StringTokenizer(in.readLine());
            int t1 = Integer.parseInt(line.nextToken());
            int t2 = Integer.parseInt(line.nextToken());
            elsie[i] = new pie(t2, t1, i + n);
        }
        ArrayDeque<Integer> bfs = new ArrayDeque<Integer>();
        int[] distances = new int[2 * n];
        Arrays.fill(distances, -1);
        TreeSet<pie> bessieset = new TreeSet<pie>();
        TreeSet<pie> elsieset = new TreeSet<pie>();
        for(int i = 0; i < n; i++) {
            if(bessie[i].t2 == 0) {
                bfs.add(i);
                distances[i] = 1;
            }else {
            		bessieset.add(bessie[i]);
            }
            if(elsie[i].t2 == 0) {
                bfs.add(i + n);
                distances[i + n] = 1;
            }else {
            		elsieset.add(elsie[i]);
            }
        }
        while(bfs.size() > 0) {
            int curpie = bfs.poll();
            if(curpie < n) {
                pie lowest = new pie(-1, bessie[curpie].t1 - d, -1);
                pie neighbor = elsieset.higher(lowest);
                while(neighbor != null && neighbor.t2 <= bessie[curpie].t1) {
                		bfs.add(neighbor.index);
                		distances[neighbor.index] = distances[curpie] + 1;
                		elsieset.remove(neighbor);
                		neighbor = elsieset.higher(lowest);
                }
            }else {
            		pie lowest = new pie(-1, elsie[curpie - n].t1 - d, -1);
            	    pie neighbor = bessieset.higher(lowest);
                while(neighbor != null && neighbor.t2 <= elsie[curpie - n].t1) {
                		bfs.add(neighbor.index);
                		distances[neighbor.index] = distances[curpie] + 1;
                		bessieset.remove(neighbor);
                		neighbor = bessieset.higher(lowest);
                }
            }
        }
        for(int i = 0; i < n; i++) {
        		out.println(distances[i]);
        }
        in.close();
        out.close();
    }
    static class pie implements Comparable<pie> {
        int t1, t2, index;
        pie(int tt1, int tt2, int ind) {
            t1 = tt1;
            t2 = tt2;
            index = ind;
        }  
        
		@Override
		public int compareTo(pie o) {
			if(t2 > o.t2) return 1;
            else if(t2 < o.t2) return -1;
            else {
                return index - o.index;
            }
		}
    }
}