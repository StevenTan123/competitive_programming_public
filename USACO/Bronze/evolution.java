import java.util.*;
import java.io.*;

public class evolution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("evolution.in"));
        PrintWriter out = new PrintWriter("evolution.out");
        int n = Integer.parseInt(in.readLine());
        int idc = 0;
        HashMap<String, Integer> ID = new HashMap<String, Integer>();
        HashSet<Integer>[] cows = new HashSet[n];
        //cows[i] is the set of cows which have characteristic i
        HashSet<Integer>[] bycha = new HashSet[1000];
        for(int i = 0; i < 1000; i++) bycha[i] = new HashSet<Integer>();
        for(int i = 0; i < n; i++) cows[i] = new HashSet<Integer>();
        for(int i = 0; i < n; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(line.nextToken());
            for(int j = 0; j < k; j++) {
                String cur = line.nextToken();
                if(ID.containsKey(cur)) {
                    bycha[ID.get(cur)].add(i);
                    cows[i].add(ID.get(cur));
                }else {
                    bycha[idc].add(i);
                    cows[i].add(idc);
                    ID.put(cur, idc);
                    idc++;
                }
            }
        }
        boolean possible = true;
        for(int i = 0; i < idc; i++) {
            HashSet<Integer> cha = bycha[i];
            for(int j = 0; j < idc; j++) {
                if(i == j) continue;
                int count = 0;
                for(int cow : cha) {
                    if(cows[cow].contains(j)) count++;
                }
                //characteristic j must be in subtree of characteristic i
                if(count > 0 && count < cha.size()) {
                    HashSet<Integer> ncha = bycha[j];
                    for(int cow : ncha) {
                        if(!cows[cow].contains(i)) {
                            possible = false;
                        }
                    }
                }
            }
        }
        out.println(possible ? "yes" : "no");
        in.close();
        out.close();
    }
}
