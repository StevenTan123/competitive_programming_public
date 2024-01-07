#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream in("sort.in");
    ofstream out("sort.out");
    
    int n;
    in >> n;
    int a[n];
    pair<int, int> sorted[n];
    for (int i = 0; i < n; i++) {
        in >> a[i];
        sorted[i] = {a[i], i};
    }
    sort(sorted, sorted + n);
    int max_ind[n];
    long long res = 0;
    for (int i = 0; i < n; i++) {
        max_ind[i] = max(sorted[i].second, i > 0 ? max_ind[i - 1] : 0);
        int cur = i > 0 ? (max_ind[i - 1] - i + 1) : (i - 1);
        cur = max(cur, max_ind[i] - i);
        if (cur == 0) {
            cur++;
        }
        res += cur;
    }
    out << res << endl;
}