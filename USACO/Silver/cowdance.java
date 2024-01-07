import java.io.*;
import java.util.*;

public class cowdance {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		int tmax = Integer.parseInt(line.nextToken());
		int[] cows = new int[n];
		for(int i = 0; i < n; i++) {
			cows[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		int res = -1;
		int leftb = 1;
		int rightb = n + 1;
		while(res == -1 && leftb < rightb) {
			int breakp = (leftb + rightb - 1) / 2;
			boolean intime = inTime(breakp, tmax, cows);
			if(intime) {
				if(breakp == 1 || !inTime(breakp - 1, tmax, cows)) {
					res = breakp;
				}
				rightb = breakp;
			}else {
				leftb = breakp + 1;
			}
		}
		if(res == -1) {
			res = leftb;
		}
		PrintWriter out = new PrintWriter("cowdance.out");
		System.out.println(res);
		out.close();
	}
	static boolean inTime(int k, int tmax, int[] cows) {
		PriorityQueue<stagecow> onstage = new PriorityQueue<stagecow>(new Comparator<stagecow>() {
			public int compare(stagecow o1, stagecow o2) {
				return Integer.compare(o1.duration, o2.duration);
			}
		});
		for(int i = 0; i < k; i++) {
			onstage.add(new stagecow(cows[i]));
		}
		int telap = 0;
		int cur = k;
		if(cur >= cows.length) {
			return true;
		}
		while(telap <= tmax) {
			stagecow min = onstage.poll();
			int posavail = 1;
			while(onstage.contains(min)) {
				posavail++;
				onstage.remove(min);
			}
			telap += min.duration;
			if(telap > tmax) {
				return false;
			}
			int max = 0;
			for(stagecow c : onstage) {
				c.duration -= min.duration;
				if(c.duration > max) {
					max = c.duration;
				}
			}
			for(int i = 0; i < posavail; i++) {
				onstage.add(new stagecow(cows[cur]));
				if(cows[cur] > max) {
					max = cur;
				}
				cur++;
				if(cur >= cows.length) {
					if(max + telap <= tmax) {
						return true;
					}else {
						return false;
					}
				}
			}
		}
		return false;
	}
	static class stagecow {
		int duration;
		stagecow(int duration) {
			this.duration = duration;
		}
	}
}
