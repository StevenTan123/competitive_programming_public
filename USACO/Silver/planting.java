import java.util.*;
import java.io.*;
public class planting {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("planting.in"));
		StringTokenizer line = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(line.nextToken());
		HashMap<Integer, field> paths = new HashMap<Integer, field>();
		for(int i = 0; i < n; i++) {
			paths.put(i + 1, new field());
			if(i == 0) {
				paths.get(1).grassType = 1;
			}
		}
		int counter = n - 1;
		while(counter > 0) {
			line = new StringTokenizer(in.readLine());
			int f1 = Integer.parseInt(line.nextToken());
			int f2 = Integer.parseInt(line.nextToken());
			paths.get(f1).connected.add(f2);
			paths.get(f2).connected.add(f1);
			counter--;
		}
		in.close();
		intCopy res = new intCopy(1);
		recurse(paths, 1, res);
		PrintWriter out = new PrintWriter("planting.out");
		out.println(res.val);
		out.close();
	}
	static void recurse(HashMap<Integer, field> paths, Integer k, intCopy gTyUsed) {
		if(k != 1 && paths.get(k).grassType != -1) {
			return;
		}
		if(k != 1) {
			Set<Integer> con2 = conbet2(k, paths);
			boolean found = false;
			for(int i = 1; i <= gTyUsed.val; i++) {
				if(!con2.contains(i)) {
					found = true;
					paths.get(k).grassType = i;
				}
			}
			if(!found) {
				gTyUsed.val += 1;
				paths.get(k).grassType = gTyUsed.val;
			}
		}
		field value = paths.get(k);
		for(int i = 0; i < value.connected.size(); i++) {
			recurse(paths, value.connected.get(i), gTyUsed);
		}
	}
	static Set<Integer> conbet2(Integer k, HashMap<Integer, field> paths){
		HashSet<Integer> result = new HashSet<Integer>();
		field value = paths.get(k);
		for(int i = 0; i < value.connected.size(); i++) {
			Integer curVal = value.connected.get(i);
			field value2 = paths.get(curVal);
			result.add(value2.grassType);
			for(int j = 0; j < value2.connected.size(); j++) {
				Integer curVal2 = value2.connected.get(j);
				if(curVal2.intValue() != k.intValue()) {
					result.add(paths.get(curVal2).grassType);
				}
			}
		}
		return result;
	}
	//make un-primitive so when passed into function it changes automatically
	static class intCopy{
		int val;
		intCopy(int val){
			this.val = val;
		}
	}	
	static class field{
		ArrayList<Integer> connected;
		int grassType;
		field(){
			connected = new ArrayList<Integer>();
			grassType = -1;
		}
	}
}