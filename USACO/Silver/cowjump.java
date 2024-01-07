import java.io.*;
import java.util.*;
public class cowjump {
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader("cowjump.in"));
		int n = Integer.parseInt(in.readLine());
		hurdle[] hurdles = new hurdle[n];
		point[] endpoints = new point[n*2];
		for(int i = 0; i < n; i++) {
			StringTokenizer line = new StringTokenizer(in.readLine());
			long x1 = Long.parseLong(line.nextToken());
			long y1 = Long.parseLong(line.nextToken());
			long x2 = Long.parseLong(line.nextToken());
			long y2 = Long.parseLong(line.nextToken());
			point p1 = new point(x1, y1, i);
			point p2 = new point(x2, y2, i);
			hurdles[i] = new hurdle(p1, p2, i);
			endpoints[i*2] = p1;
			endpoints[i*2+1] = p2;
		}
		Arrays.sort(endpoints, new Comparator<point>(){
			@Override
			public int compare(point a, point b) {
		        if (a.x < b.x) {
		            return -1;
		        }
		        if (a.x > b.x) {
		            return 1;
		        }
		        if (a.y < b.y) {
		            return -1;
		        }
		        if (a.y > b.y) {
		            return 1;
		        }
		        return 0;
		    }
		});
		in.close();
		TreeSet<point> active = new TreeSet<point>(new Comparator<point>() {
			@Override
			public int compare(point a, point b) {
		        if (a.y < b.y) {
		            return -1;
		        }
		        if (a.y > b.y) {
		            return 1;
		        }
		        if (a.x < b.x) {
		            return -1;
		        }
		        if (a.x > b.x) {
		            return 1;
		        }
		        return 0;
		    }
		});
		hurdle ans1 = null;
		hurdle ans2 = null;
		for(int i = 0; i < endpoints.length; i++) {
			point curp = endpoints[i];
			hurdle curh = hurdles[curp.hurdleind];
			point otherp = curp == curh.p1 ? curh.p2 : curh.p1;
			if(active.contains(otherp)) {
				point above = active.higher(curp);
				point below = active.lower(curp);
				active.remove(otherp);
				if(above != null && below != null) {
					hurdle aboveh = hurdles[above.hurdleind];
					hurdle belowh = hurdles[below.hurdleind];
					if(intersect(aboveh, belowh)) {
						ans1 = aboveh;
						ans2 = belowh;
						break;
					}
				}
			}else {
				point above = active.higher(curp);
				point below = active.lower(curp);
				if(above != null) {
					hurdle aboveh = hurdles[above.hurdleind];
					if(intersect(curh, aboveh)) {
						ans1 = curh;
						ans2 = aboveh;
						break;
					}
				}
				if(below != null) {
					hurdle belowh = hurdles[below.hurdleind];
					if(intersect(curh, belowh)) {
						ans1 = curh;
						ans2 = belowh;
					}
				}
				active.add(curp);
			}
		}
		int ans1count = 0;
		int ans2count = 0;
		for(int i = 0; i < hurdles.length; i++) {
			if(hurdles[i] != ans1) {
				if(intersect(hurdles[i], ans1)) {
					ans1count++;
				}
			}
			if(hurdles[i] != ans2) {
				if(intersect(hurdles[i], ans2)) {
					ans2count++;
				}
			}
		}
		PrintWriter out = new PrintWriter("cowjump.out");
		if(ans1count > ans2count) {
			out.println(ans1.index + 1);
		}else if(ans2count > ans1count) {
			out.println(ans2.index + 1);
		}else {
			if(ans2.index < ans1.index) {
				out.println(ans2.index + 1);
			}else {
				out.println(ans1.index + 1);
			}
		}
		out.close();
	}
	static int orientation(point p, point q, point r) {
		long val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y); 
	    if (val == 0) {
	    		return 0; 
	    }else if(val > 0) {
	    	    return 1;
	    }else {
	    		return 2;
	    }
	}
	static boolean onSeg(point p, point q, point r) {
		if(q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(p.y, p.y) && q.y >= Math.min(p.y, p.y)) {
			return true;
		}
		return false;
	}
	static boolean intersect(hurdle one, hurdle two) {
		int o1 = orientation(one.p1, one.p2, two.p1);
		int o2 = orientation(one.p1, one.p2, two.p2);
		int o3 = orientation(two.p1, two.p2, one.p1);
		int o4 = orientation(two.p1, two.p2, one.p2);
		if(o1 != o2 && o3 != o4) {
			return true;
		}
		if(o1 == 0 && onSeg(one.p1, two.p1, one.p2)) {
			return true;
		}
		if(o2 == 0 && onSeg(one.p1, two.p2, one.p2)) {
			return true;
		}
		if(o3 == 0 && onSeg(two.p1, one.p1, two.p2)) {
			return true;
		}
		if(o4 == 0 && onSeg(two.p1, one.p2, two.p2)) {
			return true;
		}
		return false;
	}
	static class point{
		long x, y;
		int hurdleind;
		point(long x, long y, int hurdleind){
			this.x = x;
			this.y = y;
			this.hurdleind = hurdleind;
		}
	}
	static class hurdle{
		point p1, p2;
		int index;
		hurdle(point p1, point p2, int index){
			this.p1 = p1;
			this.p2 = p2;
			this.index = index;
		}
	}
}
