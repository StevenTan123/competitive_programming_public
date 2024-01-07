import java.util.*;
import java.io.*;
public class mountains_silver {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("mountains.in"));
		int[][] mountains = new int[in.nextInt()][2];
		int counter = 0;
		while(in.hasNext()) {
			mountains[counter][0] = in.nextInt();
			mountains[counter][1] = in.nextInt();
			counter++;
		}
		in.close();
		Arrays.sort(mountains, new Comparator<int[]>(){
			public int compare(int[] a, int[] b) {
				int c1 = a[0] - a[1];
				int c2 = b[0] - b[1]; 
				if(c1 == c2) {
					if(a[0] + a[1] > b[0] + b[1]) {
						return -1;
					}else {
						return 1;
					}
				}
				return Integer.compare(c1, c2);
			}
		});
		int visible = 1;
		int index = 0;
		for(int i = 1; i < mountains.length; i++) {
			if(!(mountains[index][0] + mountains[index][1] >= mountains[i][0] + mountains[i][1])) {
				visible++;
				index = i;
			}
		}
		PrintWriter out = new PrintWriter("mountains.out");
		out.println(visible);
		out.close();
	}
}
