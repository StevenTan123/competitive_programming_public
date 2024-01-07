import java.util.*;
import java.io.*;

public class one_pizza {
	public static void main(String[] args) throws IOException {
		//BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter out = new PrintWriter(System.out);
		BufferedReader in = new BufferedReader(new FileReader("d_difficult.in.txt"));
		PrintWriter out = new PrintWriter("test.out");
		int c = Integer.parseInt(in.readLine());
		String[][] likes = new String[c][];
		String[][] dislikes = new String[c][];
		HashMap<String, ArrayList<Integer>> like_topping = new HashMap<String, ArrayList<Integer>>();
		HashMap<String, ArrayList<Integer>> dis_topping = new HashMap<String, ArrayList<Integer>>();
        for(int i = 0; i < c; i++) {
			StringTokenizer line1 = new StringTokenizer(in.readLine());
			StringTokenizer line2 = new StringTokenizer(in.readLine());
			int l = Integer.parseInt(line1.nextToken());
			int d = Integer.parseInt(line2.nextToken());
			likes[i] = new String[l];
			dislikes[i] = new String[d];
			for(int j = 0; j < l; j++) {
				String topping = line1.nextToken();
				if(!like_topping.containsKey(topping)) {
					like_topping.put(topping, new ArrayList<Integer>());
				}
				like_topping.get(topping).add(i);
				likes[i][j] = topping;
			}
			for(int j = 0; j < d; j++) {
				String topping = line2.nextToken();
				if(!dis_topping.containsKey(topping)) {
					dis_topping.put(topping, new ArrayList<Integer>());
				}
				dis_topping.get(topping).add(i);
				dislikes[i][j] = topping;
			}
		}
		int[] order = new int[c];
		for(int i = 0; i < c; i++) {
			order[i] = i;
		}
		for(int i = 0; i < c; i++) {
			Random rand = new Random();
			int ind = rand.nextInt(c);
			int temp = order[i];
			order[i] = order[ind];
			order[ind] = temp;
		}
		HashSet<Integer> banned = new HashSet<Integer>();
		for(int i = 0; i < c; i++) {
			int ind = order[i];
			if(banned.contains(ind)) {
				continue;
			}
			for(String like : likes[ind]) {
				ArrayList<Integer> illegal = dis_topping.get(like);
				if(illegal == null) {
					illegal = new ArrayList<Integer>();
				}
				for(int ban : illegal) {
					banned.add(ban);
				}
			}
			for(String dis : dislikes[ind]) {
				ArrayList<Integer> illegal = like_topping.get(dis);
				if(illegal == null) {
					illegal = new ArrayList<Integer>();
				}
				for(int ban : illegal) {
					banned.add(ban);
				}
			}
		}
		HashSet<String> ingredients = new HashSet<String>();
		for(int i = 0; i < c; i++) {
			if(!banned.contains(i)) {
				for(String like : likes[i]) {
					ingredients.add(like);
				}
			}
		}
		StringBuilder res = new StringBuilder();
		res.append(ingredients.size());
		for(String s : ingredients) {
			res.append(' ');
			res.append(s);
		}
		out.println(res.toString());
		in.close();
		out.close();
	}
}
