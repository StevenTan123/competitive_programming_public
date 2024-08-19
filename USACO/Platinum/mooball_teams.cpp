#include <bits/stdc++.h>
using namespace std;

const long long MOD = 1000000007;
const int MAXN = 200005;
long long pow2[MAXN];
long long inv2[MAXN];

long long binpow(long long a, long long b) {
    if (b == 0) {
        return 1;
    }
    long long c = binpow(a, b / 2);
    if (b % 2 == 0) {
        return c * c % MOD;
    } else {
        return c * c % MOD * a % MOD;
    }
}

long long modinv(long long a) {
    return binpow(a, MOD - 2);
}

struct LazySegTree {
    long long *a, *t, *lazy;
    LazySegTree(int n, long long _a[]) {
        a = new long long[n];
        for (int i = 0; i < n; i++) {
            a[i] = _a[i];
        }
        t = new long long[n * 4];
        lazy = new long long[n * 4];
        for (int i = 0; i < n * 4; i++) {
            lazy[i] = 1;
        }
        construct(1, 0, n - 1);
    }
    ~LazySegTree() {
        delete[] a; delete[] t; delete[] lazy;
    }
    void construct(int v, int l, int r) {
        if (l == r) {
            t[v] = a[l];
        } else {
            int m = (l + r) / 2;
            construct(v * 2, l, m);
            construct(v * 2 + 1, m + 1, r);
            t[v] = (t[v * 2] + t[v * 2 + 1]) % MOD;
        }
    }
    void push(int v, int l, int r) {
        if (l < r) {
            lazy[v * 2] = lazy[v * 2] * lazy[v] % MOD;
            lazy[v * 2 + 1] = lazy[v * 2 + 1] * lazy[v] % MOD;
            t[v * 2] = t[v * 2] * lazy[v] % MOD;
            t[v * 2 + 1] = t[v * 2 + 1] * lazy[v] % MOD;
        }
        lazy[v] = 1;
    }
    void update(int v, int l, int r, int ql, int qr, long long mult) {
        if (ql > qr) {
            return;
        } else if (l == ql && r == qr) {
            t[v] = t[v] * mult % MOD;
            lazy[v] = lazy[v] * mult % MOD;
        } else {
            push(v, l, r);
            int m = (l + r) / 2;
            update(v * 2, l, m, ql, min(m, qr), mult);
            update(v * 2 + 1, m + 1, r, max(ql, m + 1), qr, mult);
            t[v] = (t[v * 2] + t[v * 2 + 1]) % MOD;
        }
    }
    long long sum(int v, int l, int r, int ql, int qr) {
        if (ql > qr) {
            return 0;
        } else if (l == ql && r == qr) {
            return t[v];
        } else {
            push(v, l, r);
            int m = (l + r) / 2;
            long long left = sum(v * 2, l, m, ql, min(m, qr));
            long long right = sum(v * 2 + 1, m + 1, r, max(ql, m + 1), qr);
            return (left + right) % MOD;
        }
    }
};

long long count_inter(int N, pair<int, int> pts[]) {
    long long one[N];
    long long GI_start[N];
    for (int i = 0; i < N; i++) {
        one[i] = 1;
        GI_start[i] = pow2[i];
    }
    LazySegTree G(N, GI_start);
    LazySegTree H(N, one);
    LazySegTree I(N, GI_start);
    long long inter = 0;
    for (int i = 0; i < N - 1; i++) {
        int prevy = pts[i - 1].second;
        if (i > 0) {
            H.update(1, 0, N - 1, 0, prevy, 2);
            G.update(1, 0, N - 1, 0, prevy, 2);
            if (prevy > 0) {
                I.update(1, 0, N - 1, 0, prevy - 1, 2);
            }
        }

        int y = pts[i].second;
        if (y < N - 1) {
            G.update(1, 0, N - 1, y + 1, N - 1, inv2[1]);
            I.update(1, 0, N - 1, y + 1, N - 1, inv2[1]);
        }

        inter += G.sum(1, 0, N - 1, y, y) - H.sum(1, 0, N - 1, y, y);
        if (y - 1 >= 0) {
            inter += G.sum(1, 0, N - 1, 0, y - 1) - H.sum(1, 0, N - 1, 0, y - 1) 
                   - I.sum(1, 0, N - 1, 0, y - 1) + H.sum(1, 0, N - 1, 1, y);
        }
        inter = (inter % MOD + MOD) % MOD;
    }
    return inter;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    pow2[0] = 1;
    inv2[0] = 1;
    long long half = modinv(2);
    for (int i = 1; i < MAXN; i++) {
        pow2[i] = (pow2[i - 1] * 2) % MOD;
        inv2[i] = (inv2[i - 1] * half) % MOD;
    }

    int N;
    cin >> N;
    pair<int, int> pts[N];
    pair<int, int> ypts[N];
    for (int i = 0; i < N; i++) {
        int x, y;
        cin >> x >> y;
        pts[i] = {x - 1, y - 1};
        ypts[i] = {x - 1, N - y};
    }
    sort(pts, pts + N);
    sort(ypts, ypts + N);
    
    long long res = 0;
    for (int i = 0; i < N - 1; i++) {
        res += 2 * pow2[i] * (pow2[N - i - 1] - 1) % MOD;
        res %= MOD;
    }
    
    long long inter = (count_inter(N, pts) + count_inter(N, ypts)) % MOD;
    res = (res - inter + MOD) % MOD;
    res = res * 2 % MOD;
    cout << res << endl;
}