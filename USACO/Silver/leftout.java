import java.io.*;
public class leftout {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("leftout.in"));
		int n = Integer.parseInt(in.readLine());
		int[][] cows = new int[n][n];
		for(int i = 0; i < n; i++) {
			String line = in.readLine();
			for(int j = 0; j < n; j++) {
				if(line.charAt(j) == 'R') {
					cows[i][j] = 1;
				}else {
					cows[i][j] = 0;
				}
			}
		}
		in.close();
		PrintWriter out = new PrintWriter("leftout.out");
		if(n == 2) {
			int scount = 0;
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 2; j++) {
					if(cows[i][j] == 1) {
						scount++;
					}
				}
			}
			if(scount == 1 || scount == 3) {
				out.println("1 1");
			}else {
				out.println(-1);
			}
			out.close();
			return;
		}
		int type = 0;
		int type2 = -1;
		int tcount = 1;
		int t2count = 0;
		boolean noChance = false;
		for(int i = 1; i < n; i++) {
			boolean same = true;
			boolean same2 = true;
			boolean opposite = true;
			boolean opposite2 = true;
			if(cows[i][0] == cows[type][0]) {
				opposite = false;
			}
			if(type2 > -1 && cows[i][0] == cows[type2][0]) {
				opposite2 = false;
			}
			for(int j = 1; j < n; j++) {
				if((cows[i][j] == cows[type][j] && opposite) || (cows[i][j] != cows[type][j] && !opposite)) {
					same = false;
				}
				if(type2 > -1) {
					if((cows[i][j] == cows[type2][j] && opposite2) || (cows[i][j] != cows[type2][j] && !opposite2)) {
						same2 = false;
					}
				}
			}
			if(type2 > -1) {
				if(!same && !same2) {
					noChance = true;
					break;
				}
				if(same) {
					tcount++;
				}
				if(same2) {
					t2count++;
				}
				if(tcount > 1 && t2count > 1) {
					noChance = true;
					break;
				}
			}else {
				if(!same) {
					type2 = i;
					t2count++;
				}
			}
		}
		if(noChance || type2 == -1) {
			out.println(-1);
		}else {
			int diffcount = 0;
			for(int i = 0; i < n; i++) {
				if(cows[type][i] != cows[type2][i]) {
					diffcount++;
				}
			}
			if(diffcount > 1 && diffcount < n - 1) {
				out.println(-1);
			}else {
				int nondomtype = tcount < t2count ? type : type2;
				for(int i = 0; i < n; i++) {
					if(cows[type][i] != cows[type2][i] && diffcount == 1) {
						out.println(nondomtype + 1 + " " + (i + 1));
						break;
					}
					if(cows[type][i] == cows[type2][i] && diffcount == n - 1) {
						out.println(nondomtype + 1 + " " + (i + 1));
						break;
					}
				}
			}
		}
		out.close();
	}
}
