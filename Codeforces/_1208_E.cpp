#include <bits/stdc++.h>
using namespace std;

const int MAXN = 1000005;
const int K = 21;
int log_two[MAXN];

struct SparseTable {
    int N;
    int **ma;
    SparseTable(int n, int *a) : N(n) {
        ma = new int*[N];
        for (int i = 0; i < N; i++) {
            ma[i] = new int[K + 1];
            ma[i][0] = a[i];
        }
        for (int j = 1; j <= K; j++) {
            for(int i = 0; i + (1 << j) <= N; i++) {
                ma[i][j] = max(ma[i][j - 1], ma[i + (1 << (j - 1))][j - 1]);
            }
        }
    }
    ~SparseTable() {
        for (int i = 0; i < N; i++) delete[] ma[i];
        delete[] ma;
    }
    int maxq(int l, int r) {
        int j = log_two[r - l + 1];
        return max(ma[l][j], ma[r - (1 << j) + 1][j]);
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    log_two[1] = 0;
    for (int i = 2; i < MAXN; i++) {
        log_two[i] = log_two[i / 2] + 1;
    }

    int n, w;
    cin >> n >> w;
    // Tells us at each column, which arrays have updated max values they can contribute.
    vector<int> updates[w];
    for (int i = 0; i < n; i++) {
        int l;
        cin >> l;
        int a[l];
        for (int j = 0; j < l; j++) {
            cin >> a[j];
        }
        SparseTable st(l, a);

        vector<int> inds;
        if (2 * l + 2 >= w) {
            for (int j = 0; j < w; j++) {
                inds.push_back(j);
            }
        } else {
            for (int j = 0; j <= l; j++) {
                inds.push_back(j);
            }
            for (int j = w - l - 1; j < w; j++) {
                inds.push_back(j);
            }
        }

        int prev = 0;
        for (int j : inds) {
            bool out = false;
            int leftmost = j - (w - l);
            if (leftmost < 0) {
                leftmost = 0;
                out = true;
            }
            int rightmost = j;
            if (rightmost >= l) {
                rightmost = l - 1;
                out = true;
            }
            int max_cur = st.maxq(leftmost, rightmost);
            if (out) {
                max_cur = max(max_cur, 0);
            }
            updates[j].push_back(max_cur - prev);
            prev = max_cur;
        }
    }

    long long res = 0;
    for (int i = 0; i < w; i++) {
        for (int update : updates[i]) {
            res += update;
        }
        cout << res << " ";
    }
    cout << endl;
}

