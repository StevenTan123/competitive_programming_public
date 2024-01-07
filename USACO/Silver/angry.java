import java.util.*;
import java.io.*;

public class angry {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("angry.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int k = Integer.parseInt(line.nextToken());
		int[] haybales = new int[n];
		for(int i = 0; i < n; i++) {
			haybales[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(haybales);
		in.close();
		int leftBound = 1;
		int rightBound = 1000000000;
		while(leftBound < rightBound) {
			int middle = (leftBound + rightBound) / 2;
			if(radiusCompletes(middle, k, haybales)) {
				rightBound = middle;
			}else {
				leftBound = middle + 1;
			}
		}
		PrintWriter out = new PrintWriter("angry.out");
		out.println(leftBound);
		out.close();
	}
	static boolean radiusCompletes(int r, int k, int[] haybales) {
		int prev = 0;
		int usedCows = 1;
		for(int i = 0; i < haybales.length; i++) {
			if(haybales[i] - haybales[prev] > r * 2) {
				usedCows++;
				if(usedCows > k) {
					return false;
				}
				prev = i;
			}
		}
		return true;
	}
}
