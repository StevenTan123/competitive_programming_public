import java.util.*;
import java.io.*;
public class homework {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("homework.in"));
		int[] scores = new int[in.nextInt()];
		for(int i = 0; i < scores.length; i++) {
			scores[i] = in.nextInt();
		}
		in.close();
		StoreBlock[] memoize = new StoreBlock[scores.length];
		for(int i = memoize.length - 2; i >= 1; i--) {
			if(i == memoize.length - 2) {
				memoize[i] = new StoreBlock(Math.min(scores[i], scores[i + 1]), Math.max(scores[i], scores[i + 1]), 1);
			}else {
				StoreBlock prev = memoize[i + 1];
				if(scores[i] < prev.minval) {
					float curav = (prev.average * prev.homeworks + prev.minval) / (prev.homeworks + 1);
					memoize[i] = new StoreBlock(scores[i], curav, prev.homeworks + 1);
				}else {
					float curav = (prev.average * prev.homeworks + scores[i]) / (prev.homeworks + 1);
					memoize[i] = new StoreBlock(prev.minval, curav, prev.homeworks + 1);
				}
			}
		}
		PriorityQueue<StoreBlock> res = new PriorityQueue<StoreBlock>();
		for(int i = 1; i < memoize.length - 1; i++) {
			memoize[i].k = memoize.length - (memoize[i].homeworks + 1);
			res.add(memoize[i]);
		}
		int[] finalres = new int[res.size()];
		StoreBlock first = res.poll();
		finalres[0] = first.k;
		StoreBlock cur = res.poll();
		int counter = 1;
		while(cur.average == first.average) {
			finalres[counter] = cur.k;
			if(res.size() > 0) {
				cur = res.poll();
			}else {
				break;
			}
			counter++;
		}
		PrintWriter out = new PrintWriter("homework.out");
		Arrays.sort(finalres);
		for(int i = 0; i < finalres.length; i++) {
			if(finalres[i] == 0) {
				continue;
			}
			out.println(finalres[i]);
		}
		out.close();
	}
	static class StoreBlock implements Comparable<StoreBlock>{
		int minval, homeworks, k;
		float average;
		StoreBlock(int minval, float average, int homeworks){
			this.minval = minval;
			this.average = average;
			this.homeworks = homeworks;
		}
		public int compareTo(StoreBlock other) {
			if(average > other.average) {
				return -1;
			}else if(average < other.average) {
				return 1;
			}else {
				return 0;
			}
		}
	}
}
