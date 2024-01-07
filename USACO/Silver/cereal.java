import java.util.*;
import java.io.*;

public class cereal {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cereal.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());
		HashMap<Integer, ArrayList<Integer>> cowsbycereal = new HashMap<Integer, ArrayList<Integer>>();
		int[][] cows = new int[n][2];
		for(int i = 0; i < n; i++) {
			line = new StringTokenizer(in.readLine());
			cows[i][0] = Integer.parseInt(line.nextToken());
			cows[i][1] = Integer.parseInt(line.nextToken());
			ArrayList<Integer> val = cowsbycereal.get(cows[i][0]);
			ArrayList<Integer> val2 = cowsbycereal.get(cows[i][1]);
			if(val == null) val = new ArrayList<Integer>();
			if(val2 == null) val2 = new ArrayList<Integer>();
			val.add(i);
			val2.add(i);
			cowsbycereal.put(cows[i][0], val);
			cowsbycereal.put(cows[i][1], val2);
		}
		in.close();
		boolean[] cerealsused = new boolean[m + 1];
		int[] fulfilled = new int[n];
		int tfulfilled = 0;
		for(int i = 0; i < n; i++) {
			if(!cerealsused[cows[i][0]]) {
				fulfilled[i] = 0;
				cerealsused[cows[i][0]] = true;
				tfulfilled++;
			}else if(!cerealsused[cows[i][1]]) {
				fulfilled[i] = 1;
				cerealsused[cows[i][1]] = true;
				tfulfilled++;
			}else {
				fulfilled[i] = 2;
			}
		}
		PrintWriter out = new PrintWriter("cereal.out");
		out.println(tfulfilled);
		for(int i = 1; i < n; i++) {
			if(fulfilled[i - 1] == 2) {
				continue;
			}
			if(fulfilled[i - 1] == 2) {
				out.println(tfulfilled);
				continue;
			}
			tfulfilled--;
			int cereal = cows[i - 1][fulfilled[i - 1]];
			int add = fill(cows, fulfilled, cowsbycereal, cereal);
			tfulfilled += add;
			out.println(tfulfilled);
		}
		out.close();
	}
	//cereal is the cereal that is now up for grabs
	static int fill(int[][] cows, int[] fulfilled, HashMap<Integer, ArrayList<Integer>> cowsbycereal, int cereal) {
		//all cows that had this cereal as first of second choice
		ArrayList<Integer> curval = cowsbycereal.get(cereal);
		int ret = 0;
		for(int cow : curval) {
			int choice = 0;
			if(cows[cow][1] == cereal) {
				choice = 1;
			}
			//choice is what choice the cereal was for this cow
			//since the cereal is now up for grabs, if this cow could use it
			//then that would be good
			if(fulfilled[cow] > choice) {
				if(fulfilled[cow] == 2) {
					ret++;
				}
				if(choice == 0 && fulfilled[cow] == 1) {
					fulfilled[cow] = choice;
					ret += fill(cows, fulfilled, cowsbycereal, cows[cow][1]);
				}else {
					fulfilled[cow] = choice;
				}
				break;
			}
		}
		return ret;
	}
}
