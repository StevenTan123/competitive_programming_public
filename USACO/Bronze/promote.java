import java.util.*;
import java.io.*;

public class promote {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("promote.in"));
		PrintWriter out = new PrintWriter("promote.out");
		int[][] divisions = new int[4][2];
		int startreg = 0;
		int endreg = 0;
		for(int i = 0; i < 4; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			divisions[i][0] = Integer.parseInt(line.nextToken());
			divisions[i][1] = Integer.parseInt(line.nextToken());
			startreg += divisions[i][0];
			endreg += divisions[i][1];
		}
		int extra = endreg - startreg;
		for(int i = 0; i < 3; i++) {
			extra = extra + divisions[i][0] - divisions[i][1];
			out.println(extra);
		}
		in.close();
		out.close();
	}
}
