import java.util.*;
import java.io.*;
public class mixmilk {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("mixmilk.in"));
		int[] cow1 = new int[] {in.nextInt(), in.nextInt()};
		int[] cow2 = new int[] {in.nextInt(), in.nextInt()};
		int[] cow3 = new int[] {in.nextInt(), in.nextInt()};
		in.close();
		int counter = 0;
		for(int i = 0; i < 100; i++) {
			if(counter > 2) {
				counter = 0;
			}
			if(counter == 0) {
				dump(cow1, cow2);
			}else if(counter == 1) {
				dump(cow2, cow3);
			}else {
				dump(cow3, cow1);
			}
			counter++;
		}
		PrintWriter out = new PrintWriter("mixmilk.out");
		out.println(cow1[1]);
		out.println(cow2[1]);
		out.println(cow3[1]);
		out.close();
	}
	static void dump(int[] cow1, int[] cow2) {
		int leftover = cow2[0] - cow2[1];
		if(cow1[1] >= leftover) {
			cow2[1] += leftover;
			cow1[1] -= leftover;
		}else {
			cow2[1] += cow1[1];
			cow1[1] = 0;
		}
	}
}
