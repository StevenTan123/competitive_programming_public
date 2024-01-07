import java.util.*;
import java.io.*;

public class _1834_E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(line.nextToken());
            }
            int nlogn = n * 25;
            boolean[] good = new boolean[nlogn];

            DoublyLinkedList LCMs = new DoublyLinkedList(new Node(a[0]));
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    if (LCMs == null) {
                        LCMs = new DoublyLinkedList(new Node(a[i]));
                    } else {
                        LCMs.add(new Node(a[i]));
                    }
                }
                Node cur = LCMs.head;
                if (a[i] >= nlogn) {
                    LCMs = null;
                    continue;
                } else {
                    good[a[i]] = true;
                }
                while (cur.next != null) {
                    long lcm = lcm(cur.next.val, a[i]);
                    if (lcm >= nlogn) {
                        LCMs.tail = cur.next;
                        cur.next.next = null;
                        break;
                    } else {
                        if ((int) lcm == cur.val) {
                            LCMs.remove(cur.next);
                        } else {
                            cur.next.val = (int) lcm;
                            good[cur.next.val] = true;
                            cur = cur.next;
                        }
                    }
                }
            }
            int res = 0;
            for (int i = 1; i < nlogn; i++) {
                if (!good[i]) {
                    res = i;
                    break;
                }
            }
            out.println(res);
        }
        in.close();
        out.close();
    }
    static long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    static long lcm(int a, int b) {
        return (long) a * b / gcd(a, b);
    }
    static class DoublyLinkedList {
        Node head, tail;
        DoublyLinkedList(Node h) {
            head = h;
            tail = h;
        }
        // remove node x
        void remove(Node x) {
            if (x.prev != null) {
                x.prev.next = x.next;
            }
            if (x.next != null) {
                x.next.prev = x.prev;
            }
        }
        // add node x to head
        void add(Node x) {
            head.prev = x;
            x.next = head;
            head = x;
        }
    }
    static class Node {
        int val;
        Node prev, next;
        Node(int v) {
            val = v;
        }
    }           
}
