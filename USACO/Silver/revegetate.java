import java.util.*;
import java.io.*;
public class revegetate {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("revegetate.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(line.nextToken());
		int M = Integer.parseInt(line.nextToken());
		Cow[] cows = new Cow[M];
		ArrayList<ArrayList<Integer>> pastures = new ArrayList<ArrayList<Integer>>(); 
		for(int i = 0; i < N; i++) {
			pastures.add(new ArrayList<>());
		}
		for(int i = 0; i < M; i++) {
			line = new StringTokenizer(in.readLine());
			String type = line.nextToken();
			int p1 = Integer.parseInt(line.nextToken()) - 1;
			int p2 = Integer.parseInt(line.nextToken()) - 1;
			pastures.get(p1).add(i);
			pastures.get(p2).add(i);
			cows[i] = new Cow(type, p1, p2);
		}
		in.close();
		int[] visited = new int[N];
		int res = 0;
		passByRef contr = new passByRef();
		for(int i = 0; i < N; i++) {
			if(visited[i] == 0) {
				res++;
				findAllLinks(visited, pastures, cows, i, contr, 0);
				if(contr.c == true) {
					res = 0;
					break;
				}
			}
		}
		String binary = res == 0 ? "0":"1";
		for(int i = 0; i < res; i++) {
			binary += "0";
		}
		PrintWriter out = new PrintWriter("revegetate.out");
		out.println(binary);
		out.close();
	}
	static void findAllLinks(int[] visited, ArrayList<ArrayList<Integer>> pastures, Cow[] cows, int cur, passByRef contr, int ntoset)  {
		if(contr.c == true) {
			return;
		}
		if(visited[cur] != 0) {
			if(ntoset != visited[cur]) {
				contr.c = true;
			}
			return;
		}
		if(ntoset == 0) {
			visited[cur] = 1;
		}else {
			visited[cur] = ntoset;
		}
		ArrayList<Integer> curp = pastures.get(cur);
		for(int i = 0; i < curp.size(); i++) {
			int temp = ntoset;
			Cow curcow = cows[curp.get(i)];
			int other = cur == curcow.p1 ? curcow.p2 : curcow.p1;
			if(ntoset == 0) {
				if(curcow.type.equals("D")) {
					ntoset = 2;
				}else {
					ntoset = 1;
				}
			}else {
				if(curcow.type.equals("D")) {
					ntoset = ntoset == 1 ? 2:1;
				}
			}
			findAllLinks(visited, pastures, cows, other, contr, ntoset);
			ntoset = temp;
		}
	}
	static class Cow {
		int p1, p2;
		String type;
		Cow(String type, int p1, int p2){
			this.type = type;
			this.p1 = p1;
			this.p2 = p2;
		}
	}
	static class passByRef{
		boolean c;
		passByRef(){
			c = false;
		}
	}
}
