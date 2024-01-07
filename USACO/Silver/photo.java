import java.util.*;
import java.io.*;

public class photo {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("photo.in"));
        PrintWriter out = new PrintWriter("photo.out");
        int n = Integer.parseInt(in.readLine());
        HashMap<Integer, ArrayList<Integer>> indeces = new HashMap<Integer, ArrayList<Integer>>();
        Integer[] ids = new Integer[n];
        for(int i = 0; i < 5 * n; i++) {
            int id = Integer.parseInt(in.readLine());
            if(i < n) ids[i] = id;
            if(indeces.containsKey(id)) {
                indeces.get(id).add(i % n);
            }else {
                ArrayList<Integer> cur = new ArrayList<Integer>();
                cur.add(i % n);
                indeces.put(id, cur);
            }
        }
        Arrays.sort(ids, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				ArrayList<Integer> indeces1 = indeces.get(o1);
				ArrayList<Integer> indeces2 = indeces.get(o2);
				int ind1count = 0;
				int ind2count = 0;
				for(int i = 0; i < 5; i++) {
					int ind1 = indeces1.get(i);
					int ind2 = indeces2.get(i);
					if(ind2 > ind1) ind2count++;
					else ind1count++;
				}
				return ind2count - ind1count;
			}
        });
        for(int i = 0; i < n; i++) {
        		out.println(ids[i]);
        }
        in.close();
        out.close();
    }
}