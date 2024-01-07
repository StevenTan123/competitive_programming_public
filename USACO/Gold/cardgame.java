import java.util.*;
import java.io.*;

public class cardgame {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cardgame.in"));
		int n = Integer.parseInt(in.readLine());
		TreeSet<Integer> ohalf1 = new TreeSet<Integer>();
		TreeSet<Integer> ohalf2 = new TreeSet<Integer>();
		int[] allcards = new int[2 * n];
		for(int i = 0; i < n; i++) {
			int card = Integer.parseInt(in.readLine());
			allcards[card - 1] = 1;
			if(i < n / 2) {
				ohalf1.add(card);
			}else {
				ohalf2.add(card);
			}
		}
		in.close();
		int[] bessie = new int[n];
		int counter = 0;
		for(int i = 0; i < 2 * n; i++) {
			if(allcards[i] == 0) {
				bessie[counter] = i + 1;
				counter++;
			}
		}
		int res = 0;
		for(int i = 0; i < n; i++) {
			if(ohalf2.size() > 0 && bessie[i] < ohalf2.first()) {
				res++;
				ohalf2.pollFirst();
			}else if(ohalf2.size() > 0){
				ohalf2.pollFirst();
				i--;
			}else {
				if(ohalf1.size() > 0 && bessie[i] > ohalf1.first()) {
					res++;
					ohalf1.pollFirst();
				}
			}
		}
		PrintWriter out = new PrintWriter("cardgame.out");
		out.println(res);
		out.close();
	}
}
