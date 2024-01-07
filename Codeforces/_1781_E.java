import java.util.*;
import java.io.*;

public class _1781_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            Rect[] rects = new Rect[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(in.readLine());
                rects[i] = new Rect(Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()), 
                        Integer.parseInt(line.nextToken()), Integer.parseInt(line.nextToken()), i);
            }
            Arrays.sort(rects);

            int c1 = 0;
            int c2 = 0;
            Stack<Rect> stack1 = new Stack<Rect>();
            Stack<Rect> stack2 = new Stack<Rect>();
            Stack<Rect> stack = new Stack<Rect>();
            for (int i = 0; i < n; i++) {
                Rect cur = rects[i];
                if (cur.u != cur.d) {
                    if (cur.r <= c1) {
                        cur.u = 2;
                    }
                    if (cur.r <= c2) {
                        cur.d = 1;
                        if (cur.u == 2) {
                            cur.remove = true;
                        }
                    }
                    if (cur.u == 1 && cur.d == 2) {
                        while (stack1.size() > 0) {
                            Rect prev = stack1.peek();
                            if (prev.l >= cur.l) {
                                prev.remove = true;
                            } else if (prev.r >= cur.l) {
                                prev.r = cur.l - 1;
                            } else {
                                break;
                            }
                            stack1.pop();
                        }
                        while (stack2.size() > 0) {
                            Rect prev = stack2.peek();
                            if (prev.l >= cur.l) {
                                prev.remove = true;
                            } else if (prev.r >= cur.l) {
                                prev.r = cur.l - 1;
                            } else {
                                break;
                            }
                            stack2.pop();
                        }
                        while (stack.size() > 0) {
                            Rect prev = stack.peek();
                            if (prev.l >= cur.l) {
                                prev.remove = true;
                            } else if (prev.r >= cur.l) {
                                prev.r = cur.l - 1;
                            } else {
                                break;
                            }
                            stack.pop();
                        }
                        stack.push(cur);
                        c1 = cur.r;
                        c2 = cur.r;   
                    }
                }
                if (!cur.remove && cur.u == cur.d) {
                    if (cur.u == 1) {
                        if (cur.r <= c1) {
                            cur.remove = true;
                        } else {
                            if (cur.l <= c1) {
                                cur.l = c1 + 1;
                            }
                            stack1.push(cur);
                            c1 = cur.r;
                        }
                    }
                    if (cur.u == 2) {
                        if (cur.r <= c2) {
                            cur.remove = true;
                        } else {
                            if (cur.l <= c2) {
                                cur.l = c2 + 1;
                            }
                            stack2.push(cur);
                            c2 = cur.r;
                        }
                    }
                }
            }
            int area = 0;
            Rect[] res = new Rect[n];
            for (int i = 0; i < n; i++) {
                if (rects[i].remove == false) {
                    area += (rects[i].d - rects[i].u + 1) * (rects[i].r - rects[i].l + 1);
                    res[rects[i].id] = rects[i];
                }
            }
            out.println(area);
            for (int i = 0; i < n; i++) {
                if (res[i] != null) {
                    out.println(res[i].u + " " + res[i].l + " " + res[i].d + " " + res[i].r);
                } else {
                    out.println(0 + " " + 0 + " " + 0 + " " + 0);
                }
            }
        }

        in.close();
        out.close();
    }
    static class Rect implements Comparable<Rect> {
        int u, l, d, r, id;
        boolean remove = false;
        Rect(int uu, int ll, int dd, int rr, int idd) {
            u = uu;
            l = ll;
            d = dd;
            r = rr;
            id = idd;
        }
        @Override
        public int compareTo(Rect o) {
            return l - o.l;
        }
    }
}
