import java.util.*;
import java.io.*;

public class _1530_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            String s = in.readLine();
            TreeMap<Character, Integer> freqs = new TreeMap<Character, Integer>();
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                Integer freq = freqs.get(c);
                if(freq == null) freq = 0;
                freqs.put(c, freq + 1);
            }
            if(freqs.size() == 1) {
                out.println(s);
            }else {
                char min = (char)123;
                for(char c : freqs.keySet()) {
                    if(freqs.get(c) == 1) {
                        if(c < min) {
                            min = c;
                        }
                    }
                }
                if(min != (char)123) {
                    ArrayList<Character> sorted = new ArrayList<Character>(); 
                    for(int i = 0; i < s.length(); i++) {
                        char c = s.charAt(i);
                        if(c != min) {
                            sorted.add(c);
                        }
                    }
                    Collections.sort(sorted);
                    StringBuilder sb = new StringBuilder();
                    sb.append(min);
                    for(char c : sorted) {
                        sb.append(c);
                    }
                    out.println(sb.toString());
                }else {
                    char[] res = new char[s.length()];
                    Arrays.fill(res, ' ');
                    char smallest = freqs.firstKey();
                    int freq = freqs.get(smallest);
                    ArrayList<Character> sorted = new ArrayList<Character>();
                    for(int i = 0; i < s.length(); i++) {
                        char c = s.charAt(i);
                        if(c != smallest) {
                            sorted.add(c);
                        }
                    }
                    Collections.sort(sorted);
                    if(freq - (s.length() - freq) > 2) {
                        if(freqs.size() > 2) {
                            char c1 = freqs.higherKey(smallest);
                            char c2 = freqs.higherKey(c1);
                            res[0] = smallest;
                            freq--;
                            res[1] = sorted.get(0);
                            int p = 1;
                            boolean first = true;
                            boolean first2 = true;
                            for(int i = 2; i < s.length(); i++) {
                                if(freq > 0) {
                                    res[i] = smallest;
                                    freq--;
                                }else {
                                    if(first) {
                                        res[i] = c2;
                                        first = false;
                                        continue;
                                    }
                                    if(first2 && sorted.get(p) == c2) {
                                        p++;
                                        first2 = false;
                                    }
                                    res[i] = sorted.get(p);
                                    p++;
                                }
                            }
                        }else {
                            char smallest2 = freqs.higherKey(smallest);
                            int freq2 = freqs.get(smallest2);
                            res[0] = smallest;
                            freq--;
                            for(int i = 1; i < s.length(); i++) {
                                if(freq2 > 0) {
                                    res[i] = smallest2;
                                    freq2--;
                                }else {
                                    res[i] = smallest;
                                }
                            }
                        }
                    }else {
                        int p = 0;
                        for(int i = 0; i < s.length(); i++) {
                            if(i < 2) {
                                res[i] = smallest;
                                freq--;
                            }else {
                                if(freq > 0 && i % 2 == 1) {
                                    res[i] = smallest;
                                    freq--;
                                }else {
                                    res[i] = sorted.get(p);
                                    p++;
                                }
                            }
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < s.length(); i++) {
                        sb.append(res[i]);
                    }
                    out.println(sb.toString());
                }
            }
        }
        in.close();
        out.close();
    }
}
