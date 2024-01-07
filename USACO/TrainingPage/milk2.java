/*
ID: StevenTan
LANG: JAVA
PROB: milk2
*/
 
import java.io.*;
import java.util.*;
public class milk2 {
	static int curindex = 0;
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("milk2.in"));
		Farmer[] data = new Farmer[Integer.parseInt(in.next())];
		int counter = 0;
		while(in.hasNext()) {
			int start = Integer.parseInt(in.next());
			int end = Integer.parseInt(in.next());
			data[counter] = new Farmer(start, end);
			counter++;
		}
		in.close();
		for(int i = 1; i < data.length; i++) {
			int cindex = i;
			while(cindex > 0 && data[cindex].start < data[cindex - 1].start) {
				Farmer temp = data[cindex];
				data[cindex] = data[cindex - 1];
				data[cindex - 1] = temp;
				cindex--;
			}
		}
		while(curindex < data.length - 1) {
			data = combineFarmers(data);
		}
		int idle;
		if(data.length == 1) {
			idle = 0;
		}else {
			idle = data[1].start - data[0].end;
		}
		int longest = data[0].end - data[0].start;
		for(int i = 0; i < data.length; i++) {
			Farmer cur = data[i];
			if(cur.end - cur.start > longest) {
				longest = cur.end - cur.start;
			}
			if(i != 0 && data.length != 1) {
				if(data[i].start - data[i - 1].end > idle) {
					idle = data[i].start - data[i - 1].end;
				}
			}
		}
		PrintWriter out = new PrintWriter("milk2.out", "UTF-8");
		out.print(longest);
		out.print(" ");
		out.print(idle);
		out.println();
		out.close();
	}
	public static Farmer[] combineFarmers(Farmer[] data) {
		if(data[curindex + 1].start <= data[curindex].end) {
			int start = data[curindex].start;
			int end;
			if(data[curindex + 1].end > data[curindex].end) {
				end = data[curindex + 1].end;
			}else {
				end = data[curindex].end;
			}
			Farmer newFarmer = new Farmer(start, end);
			Farmer[] newList = new Farmer[data.length - 1];
			newList[curindex] = newFarmer;
			boolean after = false;
			for(int i = 0; i < data.length; i++) {
				if(i == curindex) {
					i += 1;
					after = true;
					if(i >= data.length - 1) {
						break;
					}
					continue;
				}
				if(after) {
					newList[i - 1] = data[i];
				}else {
					newList[i] = data[i];
				}
			}
			return newList;
		}
		curindex += 1;
		return data;
	}
	public static class Farmer{
		int start, end;
		Farmer(int start, int end){
			this.start = start;
			this.end = end;
		}
	}
}
