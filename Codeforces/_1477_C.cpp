#include <bits/stdc++.h>
using namespace std;

int sign_dot(int x1, int y1, int x2, int y2) {
    long long dot_prod = (long long) x1 * x2 + (long long) y1 * y2;
    if (dot_prod < 0) {
        return -1;
    } else if (dot_prod == 0) {
        return 0;
    } else {
        return 1;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    int x[n];
    int y[n];
    int p[n];
    for (int i = 0; i < n; i++) {
        cin >> x[i] >> y[i];
        p[i] = i + 1;
    }

    for (int i = 1; i < n - 1; i++) {
        for (int j = i; j >= 1; j--) {
            int x1 = x[j] - x[j - 1];
            int y1 = y[j] - y[j - 1];
            int x2 = x[j + 1] - x[j];
            int y2 = y[j + 1] - y[j];
            if (sign_dot(-x1, -y1, x2, y2) <= 0) {
                swap(x[j], x[j + 1]);
                swap(y[j], y[j + 1]);
                swap(p[j], p[j + 1]);
            }
        }
    }

    for (int i = 0; i < n; i++) {
        cout << p[i] << " ";
    }
    cout << endl;
}