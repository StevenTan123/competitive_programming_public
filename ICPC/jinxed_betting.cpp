#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    long long p[n];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
    }
    sort(p, p + n);
    for (int i = 0; i < n / 2; i++) {
        swap(p[i], p[n - i - 1]);
    }

    int log2[n + 1];
    log2[1] = 0;
    for (int i = 2; i <= n; i++) {
        log2[i] = log2[i / 2] + 1;
    }

    long long moves = 0;
    int broke = n;
    for (int i = 2; i < n; i++) {
        if (p[i] < p[i - 1]) {
            long long add = (log2[i - 1] + 1) * (p[i - 1] - p[i]);
            if (p[0] - (moves + add) < p[i]) {
                broke = i;
                break;
            }
            moves += add;
        }
    }
    int sub = log2[broke - 1] + 1;
    long long left = p[0] - moves;
    long long right = p[broke - 1];
    
    long long floor = (left - right) / (sub - 1);
    moves += floor * sub;
    left -= floor * sub;
    right -= floor;
    moves += left - right;
    cout << moves << endl;
}