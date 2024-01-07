import java.io.*;
import java.util.*;
public class billboard {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("billboard.in"));
		Board b1 = new Board(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
		Board b2 = new Board(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
		Board truck = new Board(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
		in.close();
		PrintWriter out = new PrintWriter("billboard.out", "UTF-8");
		out.println(b1.findArea() - overlapArea(b1, truck) + (b2.findArea() - overlapArea(b2, truck)));
		out.close();
	}
	static class Board{
		int x1, y1, x2, y2;
		Board(int x3, int y3, int x4, int y4){
			x1 = x3;
			y1 = y3;
			x2 = x4;
			y2 = y4;
		}
		int findArea() {
			return (x2 - x1) * (y2 - y1);
		}
		boolean touches(Board another) {
			if(x1 < another.x2 && x1 > another.x1 || x2 < another.x2 && x2 > another.x1 || (another.x1 < x2 && another.x1 > x1 || another.x2 < x2 && another.x2 > x1)) {
				if(y1 < another.y2 && y1 > another.y1 || y2 < another.y2 && y2 > another.y1 || (another.y1 < y2 && another.y1 > y1 || another.y2 < y2 && another.y2 > y1)) {
					return true;
				}
			}
			return false;
		}
	}
	static int overlapArea(Board one, Board two) {
		if(!one.touches(two)) {
			return 0;
		}
		int nx1 = Math.max(one.x1, two.x1);
		int ny1 = Math.max(one.y1, two.y1);
		int nx2 = Math.min(one.x2, two.x2);
		int ny2 = Math.min(one.y2, two.y2);
		Board nboard = new Board(nx1, ny1, nx2, ny2);
		return nboard.findArea();
	}
}
