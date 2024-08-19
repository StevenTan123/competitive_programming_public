#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, a, b;
    cin >> n >> a >> b;
    int min_count = 0;
    int max_count = 0;
    for (int i = 0; i < n - 1; i++) {
        int val;
        cin >> val;
        if (val == a) min_count++;
        if (val == b) max_count++;
    }
    if (min_count == 0 && max_count == 0) {
        cout << "-1\n";
    } else if (min_count == 0) {
        cout << a << "\n";
    } else if (max_count == 0) {
        cout << b << "\n";;
    } else {
        for (int i = a; i <= b; i++) {
            cout << i << "\n";
        }
    }
}