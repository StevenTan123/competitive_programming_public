import java.util.*;
import java.io.*;
public class convention2 {
	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new File("convention2.in"));
		PriorityQueue<EventCow> events = new PriorityQueue<EventCow>();
		int size = in.nextInt();
		for(int i = 0; i < size; i++) {
			events.add(new EventCow(new Cow(in.nextInt(), in.nextInt(), i + 1), false));
		}
		in.close();
		PriorityQueue<WaitingCow> waiting = new PriorityQueue<WaitingCow>();
		boolean pastureEmpty = true;
		int maxtime = 0;
		while(events.size() > 0) {
			EventCow cur = events.poll();
			if(pastureEmpty && cur.leaveType == false) {
				pastureEmpty = false;
				events.add(new EventCow(new Cow(cur.cow.arrival + cur.cow.timeat, -1, -1), true));
			}else if(!pastureEmpty && cur.leaveType == false) {
				waiting.add(new WaitingCow(cur.cow));
			}else if(cur.leaveType == true){
				if(waiting.size() == 0) {
					pastureEmpty = true;
				}else {
					pastureEmpty = false;
					WaitingCow goingToEat = waiting.poll();
					if(cur.cow.arrival - goingToEat.cow.arrival > maxtime) {
						maxtime = cur.cow.arrival - goingToEat.cow.arrival;
					}
					events.add(new EventCow(new Cow(cur.cow.arrival + goingToEat.cow.timeat, -1, -1), true));
				}
			}
		}
		PrintWriter out = new PrintWriter("convention2.out");
		out.println(maxtime);
		out.close();
	}
	static class Cow{
		int arrival, timeat, priority, leave;
		Cow(int arrival, int timeat, int priority){
			this.arrival = arrival;
			this.timeat = timeat;
			this.priority = priority;
		}
	}
	static class WaitingCow implements Comparable<WaitingCow>{
		Cow cow;
		WaitingCow(Cow cow){
			this.cow = cow;
		}
		public int compareTo(WaitingCow other) {
			if(cow.priority < other.cow.priority) {
				return -1;
			}else if(cow.priority > other.cow.priority) {
				return 1;
			}else {
				return 0;
			}
		}
	}
	static class EventCow implements Comparable<EventCow>{
		Cow cow;
		boolean leaveType;
		EventCow(Cow cow, boolean leaveType){
			this.cow = cow;
			this.leaveType = leaveType;
		}
		public int compareTo(EventCow other) {
			if(cow.arrival < other.cow.arrival) {
				return -1;
			}else if(cow.arrival > other.cow.arrival) {
				return 1;
			}else {
				return 0;
			}
		}
	}
}
