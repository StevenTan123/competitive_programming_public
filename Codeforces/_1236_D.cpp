#include <bits/stdc++.h>
using namespace std;

int bsearch(vector<int> &arr, int x) {
    int l = 0;
    int r = arr.size() - 1;
    int res = -1;
    while (l <= r) {
        int m = (l + r) / 2;
        if (arr[m] < x) {
            res = m;
            l = m + 1;
        } else {
            r = m - 1;
        }
    }
    return res;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m, k;
    cin >> n >> m >> k;

    vector<int> rows[n];
    vector<int> cols[m];
    for (int i = 0; i < k; i++) {
        int r, c;
        cin >> r >> c;
        r--; c--;
        rows[r].push_back(c);
        cols[c].push_back(r);
    }
    for (int i = 0; i < n; i++) {
        sort(rows[i].begin(), rows[i].end());
    }
    for (int i = 0; i < m; i++) {
        sort(cols[i].begin(), cols[i].end());
    }

    int minr = 0; int maxr = n; int minc = -1; int maxc = m;
    int r = 0; int c = 0; int dir = 0;
    long long cnt = 1;
    while (true) {
        if (dir == 0) {
            // right
            int ind = bsearch(rows[r], c) + 1;
            int bound = min(ind < rows[r].size() ? rows[r][ind] - 1 : m - 1, maxc - 1);
            if (!(r == 0 && c == 0) && bound <= c) {
                break;
            }
            cnt += bound - c;
            c = bound;
            maxc = min(maxc, bound);
        } else if (dir == 1) {
            // down
            int ind = bsearch(cols[c], r) + 1;
            int bound = min(ind < cols[c].size() ? cols[c][ind] - 1 : n - 1, maxr - 1);
            if (bound <= r) {
                break;
            }
            cnt += bound - r;
            r = bound;
            maxr = min(maxr, bound);
        } else if (dir == 2) {
            // left
            int ind = bsearch(rows[r], c);
            int bound = max(ind >= 0 ? rows[r][ind] + 1 : 0, minc + 1);
            if (bound >= c) {
                break;
            }
            cnt += c - bound;
            c = bound;
            minc = max(minc, bound);
        } else {
            // up
            int ind = bsearch(cols[c], r);
            int bound = max(ind >= 0 ? cols[c][ind] + 1 : 0, minr + 1);
            if (bound >= r) {
                break;
            }
            cnt += r - bound;
            r = bound;
            minr = max(minr, bound);
        }
        dir = (dir + 1) % 4;
    }
    if ((long long) n * m - cnt == k) {
        cout << "Yes\n";
    } else {
        cout << "No\n";
    }
}