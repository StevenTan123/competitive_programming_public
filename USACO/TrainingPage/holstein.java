/*
ID: tanstev1
LANG: JAVA
TASK: holstein
 */
import java.io.*;
import java.util.*;

public class holstein {
	static int[] minfeeds;
	static int minum;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
		int v = Integer.parseInt(in.readLine());
		int[] vitamins = new int[v];
		StringTokenizer line = new StringTokenizer(in.readLine());
		for(int i = 0; i < v; i++) {
			vitamins[i] = Integer.parseInt(line.nextToken());
		}
		int g = Integer.parseInt(in.readLine());
		int[][] feeds = new int[g][v];
		for(int i = 0; i < g; i++) {
			line = new StringTokenizer(in.readLine());
			for(int j = 0; j < v; j++) {
				feeds[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		in.close();
		minfeeds = new int[g];
		minum = Integer.MAX_VALUE;
		findAll(vitamins, feeds, 0, new int[g]);
		PrintWriter out = new PrintWriter("holstein.out");
		out.print(minum);
		for(int i = 0; i < g; i++) {
			if(minfeeds[i] == 1) {	
				out.print(" " + (i + 1));
			}
		}
		out.println();
		out.close();
	}
	public static void findAll(int[] vitamins, int[][] feeds, int curLevel, int[] curFeeds) {
		if(curLevel >= curFeeds.length) {
			int numfeeds = 0;
			for(int i = 0; i < curLevel; i++) {
				if(curFeeds[i] == 1) {
					numfeeds++;
				}
			}
			if(numfeeds <= minum) {
				boolean feedFufills = true;
				for(int i = 0; i < vitamins.length; i++) {
					if(vitamins[i] > 0) {
						feedFufills = false;
					}
				}
				if(feedFufills) {
					minum = numfeeds;
					for(int i = 0; i < curFeeds.length; i++) {
						minfeeds[i] = curFeeds[i];
					}
				}
			}
			return;
		}
		findAll(vitamins, feeds, curLevel + 1, curFeeds);
		int[] newFeed = new int[feeds.length];
		for(int i = 0; i < feeds.length; i++) {
			newFeed[i] = curFeeds[i];
		}
		newFeed[curLevel] = 1;
		int[] newvits = new int[vitamins.length];
		for(int i = 0; i < vitamins.length; i++) {
			newvits[i] = vitamins[i];
			newvits[i] -= feeds[curLevel][i];
		}
		findAll(newvits, feeds, curLevel + 1, newFeed);
	}
}
