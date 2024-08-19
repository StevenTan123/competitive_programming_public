#include <bits/stdc++.h>
using namespace std;

const int MAXN = 200005;
const int SQRT = 350;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    int x[n];
    int y[n];
    for (int i = 0; i < n; i++) {
        cin >> x[i] >> y[i];
    }
    vector<int> prev(n, -1);
    vector<tuple<int, int, int>> segs;
    for (int i = 0; i < m; i++) {
        int op, k;
        cin >> op >> k;
        k--;
        if (op == 1) {
            prev[k] = i;
        } else {
            segs.emplace_back(prev[k], i, k);
            prev[k] = -1;
        }
    }
    for (int i = 0; i < n; i++) {
        if (prev[i] != -1) {
            segs.emplace_back(prev[i], m, i);
        }
    }

    vector<int> add(m + 1);
    vector<vector<int>> add2(m + 1, vector<int>(SQRT));
    for (auto [l, r, ind] : segs) {
        if (x[ind] + y[ind] >= SQRT) {
            bool last = 0;
            for (int i = l; i <= r; i += x[ind] + y[ind]) {
                if (i + x[ind] <= r) {
                    add[i + x[ind]]++;
                    last = 1;
                }
                if (i + x[ind] + y[ind] <= r) {
                    add[i + x[ind] + y[ind]]--;
                    last = 0;
                }
            }
            if (last) {
                add[r]--;
            }
        } else {
            if (l + x[ind] <= m) {
                add2[l + x[ind]][x[ind] + y[ind]]++;
            }
            if (l + x[ind] + y[ind] <= m) {
                add2[l + x[ind] + y[ind]][x[ind] + y[ind]]--;
            }
            int before = l + (r - l) / (x[ind] + y[ind]) * (x[ind] + y[ind]);
            if (before + x[ind] > r) {
                if (before + x[ind] <= m) {
                    add2[before + x[ind]][x[ind] + y[ind]]--; 
                }
                if (before + x[ind] + y[ind] <= m) {
                    add2[before + x[ind] + y[ind]][x[ind] + y[ind]]++; 
                }    
            } else {
                add[r]--;
                if (before + x[ind] + y[ind] <= m) {
                    add2[before + x[ind] + y[ind]][x[ind] + y[ind]]++; 
                }
                if (before + x[ind] + y[ind] + x[ind] <= m) {
                    add2[before + x[ind] + y[ind] + x[ind]][x[ind] + y[ind]]--; 
                }
            }
        }
    }

    int cnt = 0;
    for (int i = 0; i < m; i++) {
        cnt += add[i];
        for (int j = 1; j < SQRT; j++) {
            cnt += add2[i][j];
            if (i + j <= m) {
                add2[i + j][j] += add2[i][j];
            }
        }
        cout << cnt << " ";
    }
    cout << "\n";
}