import java.util.*;
import java.io.*;
public class grassplanting {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("planting.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int[] connections = new int[Integer.parseInt(line.nextToken())];
		int counter = connections.length - 1;
		while(counter > 0) {
			line = new StringTokenizer(in.readLine());
			int f1 = Integer.parseInt(line.nextToken());
			int f2 = Integer.parseInt(line.nextToken());
			connections[f1 - 1]++;
			connections[f2 - 1]++;
			counter--;
		}
		in.close();
		int max = 0;
		for(int i = 0; i < connections.length; i++) {
			if(connections[i] > max) {
				max = connections[i];
			}
		}
		PrintWriter out = new PrintWriter("planting.out");
		out.println(max + 1);
		out.close();
	}
}
