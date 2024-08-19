#include <bits/stdc++.h>
using namespace std;
 
void fill(int n, int start, pair<int, int> a[], int x[], int y[], int which[]) {
    for (int i = start; i < n; i++) {
        auto [ai, ind] = a[i];
        x[i] = i + 1;
        if (ai == 0) {
            y[i] = 1;
            which[i] = i;
        } else if (y[i - 1] - (ai - 1) >= 1) {
            y[i] = y[i - 1] - (ai - 1);
            which[i] = i - 1;
        } else if (y[i - 1] + (ai - 1) <= n) {
            y[i] = y[i - 1] + (ai - 1);
            which[i] = i - 1;
        } else if (i >= ai) {
            which[i] = i - ai;
            y[i] = y[which[i]];
        } else {
            y[i] = 1 + (ai - i);
            which[i] = 0;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
 
    int n;
    cin >> n;
    pair<int, int> a[n];
    vector<int> prev(n + 1, -1);
    int zero = -1;
    int find1 = -1; int find2 = -1;
    for (int i = 0; i < n; i++) {
        cin >> a[i].first;
        a[i].second = i;
        if (a[i].first == 0) {
            zero = i;
        }
        if (prev[a[i].first] != -1) {
            find1 = prev[a[i].first];
            find2 = i;
        }
        prev[a[i].first] = i;
    }

    int x[n];
    int y[n];
    int which[n];
    if (zero != -1) {
        swap(a[0], a[zero]);
        x[0] = 1; y[0] = 1; which[0] = 0;
        fill(n, 1, a, x, y, which);
    } else if (find1 != -1) {
        if (find1 == 0) {
            swap(a[1], a[find2]);
        } else if (find2 == 0) {
            swap(a[1], a[find1]);
        } else {
            swap(a[0], a[find1]);
            swap(a[1], a[find2]);
        }
        x[0] = 1; y[0] = 1; which[0] = 1;
        x[1] = 2; y[1] = a[0].first; which[1] = 0;
        fill(n, 2, a, x, y, which);
    } else if (n == 2) {
        cout << "NO\n";
        return 0;
    } else {
        sort(a, a + n);
        swap(a[0], a[n - 1]);
        x[0] = 1; y[0] = 1; which[0] = n - 1;
        for (int i = 1; i < n - 1; i++) {
            x[i] = i + 1;
            y[i] = 2;
            which[i] = 0;
        }
        x[n - 1] = n; y[n - 1] = 2; which[n - 1] = n - 2;
    }

    pair<int, int> res_coords[n];
    int res_which[n];
    for (int i = 0; i < n; i++) {
        res_coords[a[i].second] = {x[i], y[i]};
        res_which[a[i].second] = a[which[i]].second;
    }
 
    cout << "YES\n";
    for (int i = 0; i < n; i++) {
        cout << res_coords[i].first << " " << res_coords[i].second << "\n";
    }
    for (int i = 0; i < n; i++) {
        cout << res_which[i] + 1 << " ";
    }
    cout << "\n";
}