import java.util.*;
import java.io.*;

public class protagonist {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = Integer.parseInt(in.readLine());
		for(int i = 0; i < t; i++) {
			int[][] input = new int[3][2];
			for(int j = 0; j < 3; j++) {
				StringTokenizer line = new StringTokenizer(in.readLine());
				input[j][0] = Integer.parseInt(line.nextToken());
				input[j][1] = Integer.parseInt(line.nextToken());
			}
			if(input[2][0] > input[2][1]) {
				int temp = input[2][0];
				input[2][0] = input[2][1];
				input[2][1] = temp;
				temp = input[1][0];
				input[1][0] = input[1][1];
				input[1][1] = temp;
			}
			long maxweapons = 0;
			for(int j = -1; j < input[1][0]; j++) {
				if((j + 1) * input[2][0] > input[0][0]) {
					break;
				}
				long lightme = j + 1;
				long heavyme = (input[0][0] - lightme * input[2][0]) / input[2][1];
				if(heavyme > input[1][1]) {
					heavyme = input[1][1];
				}
				long lightfriend = input[0][1] / input[2][0];
				if(lightfriend > input[1][0] - j - 1) {
					lightfriend = input[1][0] - j - 1;
				}
				long heavyfriend = (input[0][1] - (lightfriend * input[2][0])) / input[2][1];
				if(heavyfriend > input[1][1] - heavyme) {
					heavyfriend = input[1][1] - heavyme;
				}
				long weaponcount = lightme + heavyme + lightfriend + heavyfriend;
				maxweapons = Math.max(maxweapons, weaponcount);
			}
			out.println(maxweapons);
		}
		in.close();
		out.close();
	}
}
