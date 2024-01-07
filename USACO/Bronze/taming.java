import java.io.*;
import java.util.*;
public class taming {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("taming.in"));
		PrintWriter out = new PrintWriter("taming.out");
		int[] logs = new int[in.nextInt()];
		for(int i = 0; i < logs.length; i++) {
			logs[i] = in.nextInt();
		}
		in.close();
		if(logs[0] > 0) {
			out.println(-1);
			out.close();
			return;
		}
		logs[0] = 0;
		int min = 0;
		int max = 0;
		int cur = -1;
		for(int i = logs.length - 1; i >= 0; i--) {
			if(cur != -1 && logs[i] != -1 && logs[i] != cur) {
				out.println(-1);
				out.close();
				return;
			}
			if(cur == -1) {
				cur = logs[i];
			}
			if(logs[i] == -1) {
				logs[i] = cur;
			}
			if(logs[i] == 0) {
				min++;
			}
			if(logs[i] == -1) {
				max++;
			}
			if(cur > -1) {
				cur--;
			}
		}
		out.println(min + " " + (min + max));
		out.close();
	}
}
