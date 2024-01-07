import java.io.*;
import java.util.*;
public class sort {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("sort.in"));
		int n = Integer.parseInt(in.readLine());
		int[] a = new int[n];
		int[] sorteda = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(in.readLine());
			sorteda[i] = a[i];
		}
		in.close();
		Arrays.sort(sorteda);
		HashMap<Integer, Integer> positions = new HashMap<Integer, Integer>();
		for(int i = 0; i < n; i++) {
			positions.put(sorteda[i], i);
		}
		int max = 0;
		for(int i = 0; i < n; i++) {
			int dif = i - positions.get(a[i]);
			if(dif > max) {
				max = dif;
			}
		}
		PrintWriter out = new PrintWriter("sort.out");
		out.println(max + 1);
		out.close();
	}
}
