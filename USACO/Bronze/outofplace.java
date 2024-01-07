import java.util.*;
import java.io.*;
public class outofplace {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("outofplace.in"));
		int[] list = new int[in.nextInt()];
		for(int i = 0; i < list.length; i++) {
			list[i] = in.nextInt();
		}
		in.close();
		int[] copy = list.clone();
		Arrays.sort(copy);
		int swapCount = -1;
		for(int i = 0; i < list.length; i++) {
			if(list[i] != copy[i]) {
				swapCount++;
			}
		}
		PrintWriter out = new PrintWriter("outofplace.out");
		out.println(swapCount);
		out.close();
	}
}
