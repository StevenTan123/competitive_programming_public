#include <bits/stdc++.h>
using namespace std;

const int FIB = 55;

bool find_match(int six[6]) {
    for (int i = 0; i < 4; i++) {
        for (int j = i + 1; j < 5; j++) {
            for (int k = j + 1; k < 6; k++) {
                if (six[i] + six[j] > six[k]) {
                    int others[3];
                    int p = 0;
                    for (int x = 0; x < 6; x++) {
                        if (x != i && x != j && x != k) {
                            others[p++] = x;
                        }
                    }
                    if (six[others[0]] + six[others[1]] > six[others[2]]) {
                        return true;
                    }
                }
            }
        }
    }
    return false;
}

bool possible(vector<int> &vals) {
    int m = vals.size();
    sort(vals.begin(), vals.end());

    vector<bool> marks(m);
    int min_mark = -1;
    int max_mark = -1;
    for (int i = 0; i < m - 2; i++) {
        if (vals[i] + vals[i + 1] > vals[i + 2]) {
            marks[i] = 1;
            if (min_mark == -1) {
                min_mark = i;
            }
            max_mark = i;
        }
    }

    if (min_mark == -1 || min_mark == max_mark) {
        return false;
    } else if (min_mark + 3 <= max_mark) {
        return true;
    }

    for (int i = max(0, min_mark - 3); i <= min(m - 6, max_mark); i++) {
        int six[6];
        for (int j = i; j < i + 6; j++) {
            six[j - i] = vals[j];
        }
        if (find_match(six)) {
            return true;
        }
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, q;
    cin >> n >> q;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    for (int i = 0; i < q; i++) {
        int l, r;
        cin >> l >> r;
        l--; r--;
        if (r - l >= FIB) {
            cout << "YES\n";
        } else {
            vector<int> vals(r - l + 1);
            for (int j = l; j <= r; j++) {
                vals[j - l] = a[j];
            }
            if (possible(vals)) {
                cout << "YES\n";
            } else {
                cout << "NO\n";
            }
        }
    }
}