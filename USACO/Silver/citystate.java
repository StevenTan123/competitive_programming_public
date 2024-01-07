import java.util.*;
import java.io.*;

public class citystate {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("citystate.in"));
		HashMap<String, Integer> numPairs = new HashMap<String, Integer>();
		int res = 0;
		int n = Integer.parseInt(in.readLine());
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			String city = line.nextToken().substring(0, 2);
			String state = line.nextToken();
			String cs = city + state;
			if(!city.equals(state)) {
				if(!numPairs.containsKey(cs)) {
					numPairs.put(cs, 0);
				}
				numPairs.put(cs, numPairs.get(cs) + 1);
			}
		}
		for(String key : numPairs.keySet()) {
			String matchKey = key.substring(2) + key.substring(0, 2);
			if(numPairs.containsKey(matchKey)) {
				res += numPairs.get(key) * numPairs.get(matchKey);
			}
		}
		in.close();
		PrintWriter out = new PrintWriter("citystate.out");
		out.println(res / 2);
		out.close();
	}
}
