import java.util.*;
import java.io.*;

public class dishes {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("dishes.in"));
        PrintWriter out = new PrintWriter("dishes.out");
        int n = Integer.parseInt(in.readLine());
        int[] dishes = new int[n];
        for(int i = 0; i < n; i++) dishes[i] = Integer.parseInt(in.readLine());
        ArrayList<ArrayList<Integer>> piles = new ArrayList<ArrayList<Integer>>();
        piles.add(new ArrayList<Integer>());
        piles.get(0).add(dishes[0]);
        int maxtook = -1;
        int res = n;
        for(int i = 1; i < n; i++) {
            if(dishes[i] < maxtook) {
                res = i;
                break;
            }
            int pile = bsearch1(piles, dishes[i]);
            if(pile == piles.size()) {
                piles.add(new ArrayList<Integer>());
                piles.get(piles.size() - 1).add(dishes[i]);
                continue;
            }
            ArrayList<Integer> p = piles.get(pile);
            int ind = bsearch2(p, dishes[i]);
            if(ind == p.size() - 1) {
                p.add(dishes[i]);
            }else {
                ArrayList<ArrayList<Integer>> piles2 = new ArrayList<ArrayList<Integer>>();
                maxtook = p.get(ind + 1);
                ArrayList<Integer> add = new ArrayList<Integer>();
                for(int j = 0; j < ind + 1; j++) {
                    add.add(piles.get(pile).get(j));
                }
                piles2.add(add);
                for(int j = pile + 1; j < piles.size(); j++) {
                    piles2.add(piles.get(j));
                }
                piles = piles2;
            }
        }
        out.println(res);
        in.close();
        out.close();
    }
    //binary search for leftmost pile greater than the target
    static int bsearch1(ArrayList<ArrayList<Integer>> piles, int target) {
        int l = 0;
        int r = piles.size() - 1;
        int res = piles.size();
        while(l <= r) {
            int mid = (l + r) / 2;
            int val = piles.get(mid).get(0);
            if(val > target) {
                res = mid;
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return res;
    }
    //binary search within pile for largest index greater than the target
    static int bsearch2(ArrayList<Integer> pile, int target) {
        int l = 0;
        int r = pile.size() - 1;
        int res = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            int val = pile.get(mid);
            if(val > target) {
                res = mid;
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        return res;
    }
}
