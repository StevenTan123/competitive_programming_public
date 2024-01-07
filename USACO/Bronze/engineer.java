import java.util.*;
import java.io.*;

public class engineer {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            in.readLine();
            StringTokenizer line = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(line.nextToken());
            int M = Integer.parseInt(line.nextToken());
            ArrayList<String> inputs = new ArrayList<String>();
            ArrayList<Integer> outputs = new ArrayList<Integer>();
            for (int i = 0; i < M; i++) {
                line = new StringTokenizer(in.readLine());
                inputs.add(line.nextToken());
                outputs.add(Integer.parseInt(line.nextToken()));
            }
            ArrayList<Statement> statements = new ArrayList<Statement>();
            boolean possible = true;
            while (true) {
                ArrayList<Integer> left = filter(M, inputs, statements);
                Statement cur = distinguish(N, inputs, outputs, left);
                if (cur == null) {
                    possible = false;
                    break;
                } else if(cur.done) {
                    if (left.size() == N) {
                        possible = false;
                    }
                    break;
                }
                statements.add(cur);
            }
            out.println(possible ? "OK" : "LIE");
        }
        in.close();
        out.close();
    }
    static ArrayList<Integer> filter(int M, ArrayList<String> inputs, ArrayList<Statement> statements) {
        HashSet<Integer> out = new HashSet<Integer>();
        for (Statement s : statements) {
            for (int i = 0; i < inputs.size(); i++) {
                int digit = Character.getNumericValue(inputs.get(i).charAt(s.ind)); 
                if (digit == s.eq) {
                    out.add(i);
                }
            }
        }
        ArrayList<Integer> left = new ArrayList<Integer>();
        for (int i = 0; i < M; i++) {
            if (!out.contains(i)) {
                left.add(i);
            }
        }
        return left;
    }
    static Statement distinguish(int N, ArrayList<String> inputs, ArrayList<Integer> outputs, ArrayList<Integer> inds) {
        for (int i = 0; i < N; i++) {
            int map0 = -1;
            int map1 = -1;
            for (int j : inds) {
                int in = Character.getNumericValue(inputs.get(j).charAt(i));
                int out = outputs.get(j);
                if (in == 0) {
                    if (map0 == -1) {
                        map0 = out;
                    } else if (map0 != out) {
                        map0 = -2;
                    }
                } else {
                    if (map1 == -1) {
                        map1 = out;
                    } else if (map1 != out) {
                        map1 = -2;
                    }
                }
            }
            if (map0 >= 0) {
                return new Statement(i, 0, map0);
            } else if (map1 >= 0) {
                return new Statement(i, 1, map1);
            }
        }
        boolean equal = true;
        for (int i : inds) {
            if (outputs.get(i) != outputs.get(0)) {
                equal = false;
            }
        }
        if (equal) {
            return new Statement();
        }
        return null;
    }
    static class Statement {
        int ind, eq, ret;
        boolean done = false;
        Statement(int i, int e, int r) {
            ind = i;
            eq = e;
            ret = r;
        }
        Statement() {
            done = true;
        }
    }
}
