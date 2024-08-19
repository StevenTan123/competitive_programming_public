#include <bits/stdc++.h>
using namespace std;

const int BLK_SZ = 350;

void calc_range(int l, int r, int a[], int cnt[], int out[]) {
    for (int i = r - 1; i >= l; i--) {
        if (i + a[i] >= r) {
            cnt[i] = 1;
            out[i] = i;
        } else {
            cnt[i] = cnt[i + a[i]] + 1;
            out[i] = out[i + a[i]];
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    int n_blk = (n + BLK_SZ - 1) / BLK_SZ;
    int cnt[n];
    int out[n];
    for (int i = 0; i < n_blk; i++) {
        calc_range(i * BLK_SZ, min((i + 1) * BLK_SZ, n), a, cnt, out);
    }

    for (int i = 0; i < m; i++) {
        int t, hole;
        cin >> t >> hole;
        hole--;
        if (t == 0) {
            int pow;
            cin >> pow;
            a[hole] = pow;
            int block = hole / BLK_SZ;
            calc_range(block * BLK_SZ, min((block + 1) * BLK_SZ, n), a, cnt, out);
        } else {
            int cur = hole;
            int last_hole = -1;
            int num_jmps = 0;
            while (cur < n) {
                num_jmps += cnt[cur];
                last_hole = out[cur];
                cur = out[cur] + a[out[cur]];
            }
            cout << last_hole + 1 << " " << num_jmps << "\n";
        }
    }
}