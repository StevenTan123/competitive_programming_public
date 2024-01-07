import java.util.*;
import java.io.*;
	
public class highcard {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("highcard.in"));
		int n = Integer.parseInt(in.readLine());
		int[] opponent = new int[n];
		int[] allcards = new int[2 * n];
		for(int i = 0; i < n; i++) {
			opponent[i] = Integer.parseInt(in.readLine());
			allcards[opponent[i] - 1] = 1;
		}
		in.close();
		Arrays.sort(opponent);
		int[] bessie = new int[n];
		int counter = 0;
		for(int i = 0; i < 2 * n; i++) {
			if(allcards[i] == 0) {
				bessie[counter] = i + 1;
				counter++;
			}
		}
		int bind = 0;
		int oind = 0;
		int res = 0;
		while(bind < n && oind < n) {
			if(bessie[bind] > opponent[oind]) {
				res++;
				bind++;
				oind++;
			}else {
				bind++;
			}
		}
		PrintWriter out = new PrintWriter("highcard.out");
		out.println(res);
		out.close();
	}
}
