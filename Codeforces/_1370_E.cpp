#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    string s, t;
    cin >> s >> t;
    int arr[n];
    int diff = 0;
    for (int i = 0; i < n; i++) {
        if (s[i] == t[i]) {
            arr[i] = 0;
        } else if (s[i] == '0') {
            arr[i] = 1;
            diff++;
        } else {
            arr[i] = -1;
            diff--;
        }
    }
    if (diff != 0) {
        cout << "-1\n";
    } else {
        int res = 0;
        int min_sum = 0;
        int max_sum = 0;
        for (int i = 0; i < n; i++) {
            if (max_sum + arr[i] < 0) {
                max_sum = 0;
            } else {
                max_sum += arr[i];
            }
            if (min_sum + arr[i] > 0) {
                min_sum = 0;
            } else {
                min_sum += arr[i];
            }
            res = max(res, max(max_sum, -min_sum));
        }
        cout << res << "\n";
    }
}