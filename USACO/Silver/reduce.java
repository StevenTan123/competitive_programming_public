import java.util.*;
import java.awt.Point;
import java.io.*;

public class reduce {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("reduce.in"));
		int n = Integer.parseInt(in.readLine());
		ArrayList<Point> cows = new ArrayList<Point>();
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(line.nextToken());
			int y = Integer.parseInt(line.nextToken());
			cows.add(new Point(x, y));
		}
		in.close();
		for(int i = 0; i < 3; i++) {
			Collections.sort(cows, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					return Integer.compare(o1.x, o2.x);
				}
			});
			Point highX = cows.get(cows.size() - 1);
			Point lowX = cows.get(0);
			Collections.sort(cows, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					return Integer.compare(o1.y, o2.y);
				}
			});
			Point highY = cows.get(cows.size() - 1);
			Point lowY = cows.get(0);
			//right, left, top, bottom
			int[] areas = new int[4];
			Point[] pointsRemove = new Point[] {highX, lowX, highY, lowY};
			cows.remove(highX);
			areas[0] = area(cows);
			cows.add(highX);
			cows.remove(lowX);
			areas[1] = area(cows);
			cows.add(lowX);
			cows.remove(highY);
			areas[2] = area(cows);
			cows.add(highY);
			cows.remove(lowY);
			areas[3] = area(cows);
			cows.add(lowY);
			int min = 0;
			for(int j = 0; j < areas.length; j++) {
				if(areas[j] < areas[min]) {
					min = j;
				}
			}
			cows.remove(pointsRemove[min]);
		}
		PrintWriter out = new PrintWriter("reduce.out");
		out.println(area(cows));
		out.close();
	}
	public static int area(ArrayList<Point> cows) {
		Collections.sort(cows, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return Integer.compare(o1.x, o2.x);
			}
		});
		Point highX = cows.get(cows.size() - 1);
		Point lowX = cows.get(0);
		Collections.sort(cows, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return Integer.compare(o1.y, o2.y);
			}
		});
		Point highY = cows.get(cows.size() - 1);
		Point lowY = cows.get(0);
		return (highX.x - lowX.x) * (highY.y - lowY.y);
	}
}
