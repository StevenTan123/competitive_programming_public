import java.io.*;
import java.util.*;
public class measurement_bronze {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("measurement.in"));
		Measurement[] ms = new Measurement[in.nextInt()];
		int counter = 0;
		in.nextLine();
		while(in.hasNextLine()) {
			String[] lineSegs = in.nextLine().split(" ");
			boolean increase = (lineSegs[2].charAt(0) == '+') ? true : false;
			int amount = Character.getNumericValue(lineSegs[2].charAt(1));
			ms[counter] = new Measurement(lineSegs[1], Integer.parseInt(lineSegs[0]), amount, increase);
			counter++;
		}
		in.close();
		for(int i = 1; i < ms.length; i++) {
			int curindex = i;
			while(curindex > 0 && ms[curindex].day < ms[curindex - 1].day) {
				Measurement temp = ms[curindex];
				ms[curindex] = ms[curindex - 1];
				ms[curindex - 1] = temp;
				curindex--;
			}
		}
		ArrayList<Measurement> noRep = new ArrayList<>();
		for(int i = 0; i < ms.length; i++) {
			boolean dup = false;
			for(int j = 0; j < i; j++) {
				if(ms[j].name.equals(ms[i].name)) {
					dup = true;
				}
			}
			if(!dup) {
				noRep.add(new Measurement(ms[i].name, 0, 0, false));
			}
		}
		boolean[] winners = new boolean[noRep.size()];
		for(int i = 0; i < winners.length; i++) {
			winners[i] = true;
		}
		int dns = 0;
		for(int i = 0; i < ms.length; i++) {
			int index = i;
			for(int j = 0; j < i; j++) {
				if(noRep.get(j).name.equals(ms[i].name)) {
					index = j;
					break;
				}
			}
			if(ms[i].increase) {
				noRep.get(index).rate += ms[i].amount;
			}else {
				noRep.get(index).rate -= ms[i].amount;
			}
			int max = 0;
			for(int j = 0; j < noRep.size(); j++) {
				if(noRep.get(j).rate > max) {
					max = noRep.get(j).rate;
				}
			}
			boolean changed = false; 
			for(int j = 0; j < noRep.size(); j++) {
				if(noRep.get(j).rate >= max) {
					if(winners[j] == false) {
						changed = true; 
					}
					winners[j] = true;
				}else {
					if(winners[j] == true) {
						changed = true;
					}
					winners[j] = false;
				}
			}
			if(changed) {
				dns++;	
			}
		}
		PrintWriter out = new PrintWriter("measurement.out", "UTF-8");
		out.println(dns);
		out.close();
	}
	static class Measurement{
		String name;
		int day, amount;
		boolean increase;
		int rate = 7;
		Measurement(String name, int day, int amount, boolean increase){
			this.name = name;
			this.day = day;
			this.amount = amount;
			this.increase = increase;
		}
		void printAll() {
			System.out.print(day + " " + name + " " + increase + amount);
			System.out.println("");
		}
	}
}