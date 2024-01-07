import java.util.*;
import java.io.*;
public class backforth {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("backforth.in"));
		ArrayList<Integer> buckets1 = new ArrayList<Integer>();
		ArrayList<Integer> buckets2 = new ArrayList<Integer>();
		for(int i = 0; i < 20; i++) {
			if(i < 10) {
				buckets1.add(in.nextInt());
			}else {
				buckets2.add(in.nextInt());
			}
		}
		in.close();
		HashSet<Integer> poss = new HashSet<Integer>();
		recurse(poss, buckets1, buckets2, 0, 1000);
		PrintWriter out = new PrintWriter("backforth.out");
		out.println(poss.size());
		out.close();
	}
	static void recurse(HashSet<Integer> poss, ArrayList<Integer> buckets1, ArrayList<Integer> buckets2, int level, int b1s) {
		if(level >= 3) {
			for(int i = 0; i < buckets2.size(); i++) {
				poss.add(b1s + buckets2.get(i));
			}
			return;
		}
		if(level % 2 == 0) {
			for(int i = 0; i < buckets1.size(); i++) {
				int temp = buckets1.get(i);
				buckets1.remove(i);
				buckets2.add(0, temp);
				b1s -= temp;
				ArrayList<Integer> copy1 = new ArrayList<Integer>();
				ArrayList<Integer> copy2 = new ArrayList<Integer>();
				for(int j = 0; j < buckets1.size(); j++) {
					copy1.add(buckets1.get(j));
				}
				for(int j = 0; j < buckets2.size(); j++) {
					copy2.add(buckets2.get(j));
				}
				recurse(poss, copy1, copy2, level + 1, b1s);
				buckets1.add(i, temp);
				buckets2.remove(0);
				b1s += temp;
			}
		}else {
			for(int i = 0; i < buckets2.size(); i++) {
				int temp = buckets2.get(i);
				buckets2.remove(i);
				buckets1.add(0, temp);
				b1s += temp;
				ArrayList<Integer> copy1 = new ArrayList<Integer>();
				ArrayList<Integer> copy2 = new ArrayList<Integer>();
				for(int j = 0; j < buckets1.size(); j++) {
					copy1.add(buckets1.get(j));
				}
				for(int j = 0; j < buckets2.size(); j++) {
					copy2.add(buckets2.get(j));
				}
				recurse(poss, copy1, copy2, level + 1, b1s);
				buckets2.add(i, temp);
				buckets1.remove(0);
				b1s -= temp;
			}
		}
	}
}
