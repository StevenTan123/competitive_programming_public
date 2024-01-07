import java.io.*;
import java.util.*;

public class where {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("where.in"));
		int n = Integer.parseInt(in.readLine());
		char[][] field = new char[n][n];
		for(int i = 0; i < n; i++) {
			String line = in.readLine();
			for(int j = 0; j < n; j++) {
				field[i][j] = line.charAt(j);
			}
		}
		in.close();
		int[][] blobs = new int[n][n];
		int id = 1;
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				if(blobs[r][c] == 0) {
					floodfill(field, r, c, field[r][c], blobs, n, id);
					id++;
				}
			}
		}
		ArrayList<pcl> pcls = new ArrayList<pcl>();
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				for(int r2 = n - 1; r2 >= row; r2--) {
					for(int c2 = n - 1; c2 >= col; c2--) {
						boolean issub = false;
						pcl curpcl = new pcl(row, col, r2, c2);
						for(int i = 0; i < pcls.size(); i++) {
							if(curpcl.issub(pcls.get(i))) {
								issub = true;
								break;
							}
						}
						if(issub) {
							continue;
						}
						if(ispcl(field, blobs, row, col, r2, c2)) {
							pcls.add(curpcl);
						}
					}
				}
			}
		}
		PrintWriter out = new PrintWriter("where.out");
		System.out.println(pcls.size());
		System.out.println(ispcl(field, blobs, 0, 0, 3, 3));
		out.close();
	}
	static boolean ispcl(char[][] field, int[][] blobs, int r, int c, int r2, int c2) {
		HashSet<blob> blobseen = new HashSet<blob>();
		for(int i = r; i <= r2; i++) {
			for(int j = c; j <= c2; j++) {
				blob cur = new blob(field[i][j], blobs[i][j]);
				if(!blobseen.contains(cur)) {
					blobseen.add(cur);
				}
			}
		}
		if(blobseen.size() < 3) {
			return false;
		}
		char color1 = ' ';
		char color2 = ' ';
		int count1 = 0;
		int count2 = 0;
		int counter = 0;
		Iterator<blob> it = blobseen.iterator();
		boolean ppcl = true;
		while(it.hasNext()) {
			blob cur = it.next();
			if(counter == 0) {
				color1 = cur.token;
				count1++;
			}else if(cur.token == color1) {
				count1++;
			}else if(counter > 0 && cur.token != color1 && color2 == ' ') {
				color2 = cur.token;
				count2++;
			}else if(cur.token == color2) {
				count2++;
			}else {
				ppcl = false;
				break;
			}
			if(count1 > 1 && count2 > 1) {
				ppcl = false;
				break;
			}
			counter++;
		}
		if((count1 == 1 && count2 > 1) || (count2 == 1 && count1 > 1)) {
		}else {
			ppcl = false;
		}
		return ppcl;
	}
	static void floodfill(char[][] field, int r, int c, char token, int[][] blobs, int n, int id) {
		if(r < 0 || r >= n || c < 0 || c >= n) {
			return;
		}
		if(field[r][c] != token) {
			return;
		}
		if(blobs[r][c] == id) {
			return;
		}
		blobs[r][c] = id;
		floodfill(field, r + 1, c, token, blobs, n, id);
		floodfill(field, r - 1, c, token, blobs, n, id);
		floodfill(field, r, c + 1, token, blobs, n, id);
		floodfill(field, r, c - 1, token, blobs, n, id);
	}
	static class blob {
		char token;
		int id;
		blob(char token, int id) {
			this.token = token;
			this.id = id;
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof blob) {
				blob o = (blob)obj;
				if(token == o.token && id == o.id) {
					return true;
				}
			}
			return false;
		}
		@Override
		public int hashCode() {
			return (int)token - 64 + id * 27;
		}
	}
	static class pcl {
		int r, c, r2, c2;
		pcl(int r, int c, int r2, int c2){
			this.r = r;
			this.c = c;
			this.r2 = r2; 
			this.c2 = c2;
		}
		boolean issub(pcl o) {
			if(r >= o.r && r2 <= o.r2 && c >= o.c && c2 <= o.c2) {
				return true;
			}
			return false;
		}
		void printFields() {
			System.out.print(r + " " + c + " " + r2 + " " + c2);
			System.out.println();
		}
	}
}
