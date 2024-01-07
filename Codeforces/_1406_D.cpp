#include <bits/stdc++.h>
using namespace std;

long long half(long long x) {
    return max(x / 2, x - x / 2);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    long long a[n];
    long long diff[n];
    long long pos_sum = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        diff[i] = a[i] - (i > 0 ? a[i - 1] : 0);
        if (diff[i] > 0 || i == 0) pos_sum += diff[i];
    }

    int q;
    cin >> q;
    cout << half(pos_sum) << "\n";
    for (int i = 0; i < q; i++) {
        int l, r, x;
        cin >> l >> r >> x;
        l--; r--;

        if (diff[l] > 0 || l == 0) pos_sum -= diff[l];
        diff[l] += x;
        if (diff[l] > 0 || l == 0) pos_sum += diff[l];

        if (r < n - 1) {
            if (diff[r + 1] > 0) pos_sum -= diff[r + 1];
            diff[r + 1] -= x;
            if (diff[r + 1] > 0) pos_sum += diff[r + 1];
        }
        cout << half(pos_sum) << "\n";
    }
}