import java.util.*;
import java.io.*;

public class snowboots {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter out = new PrintWriter("snowboots.out");
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int b = Integer.parseInt(line.nextToken());
		Node[] snow = new Node[n];
		LinkedList tiles = new LinkedList();
		int[][] boots = new int[b][3];
		line = new StringTokenizer(in.readLine());
		int maxseparate = 1;
		for(int i = 0; i < n; i++) {
			Node cur = new Node(i, Integer.parseInt(line.nextToken()));
			if(i == 0) {
				tiles.head = cur;
				tiles.tail = cur;
			}
			snow[i] = cur;
			tiles.add(cur);
		}
		for(int i = 0; i < b; i++) {
			line = new StringTokenizer(in.readLine());
			boots[i][0] = Integer.parseInt(line.nextToken());
			boots[i][1] = Integer.parseInt(line.nextToken());
			boots[i][2] = i;
		}
		Arrays.sort(snow);
		Arrays.sort(boots, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int snowpointer = n - 1;
		int[] bootscan = new int[b];
		for(int i = b - 1; i >= 0; i--) {
			int curdepth = boots[i][0];
			while(snow[snowpointer].depth > curdepth) {
				Node prev = snow[snowpointer].prev;
				Node next = snow[snowpointer].next;
				tiles.remove(snow[snowpointer]);
				maxseparate = Math.max(maxseparate, next.index - prev.index);
				snowpointer--;
			}
			if(maxseparate <= boots[i][1]) {
				bootscan[boots[i][2]] = 1;
			}
		}
		for(int i = 0; i < b; i++) {
			out.println(bootscan[i]);
		}
		in.close();
		out.close();
	}
	static class Node implements Comparable<Node> {
		int index, depth;
		Node prev, next;
		Node(int i, int d) {
			index = i;
			depth = d;
		}
		@Override
		public int compareTo(Node o) {
			return depth - o.depth;
		}
	}
	static class LinkedList {
		Node head;
		Node tail;
		void add(Node a) {
			tail.next = a;
			a.prev = tail;
			tail = a;
		}
		void remove(Node a) {
			a.prev.next = a.next;
			a.next.prev = a.prev;
			a.next = null;
			a.prev = null;
		}
	}
}
