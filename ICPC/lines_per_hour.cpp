#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, lph;
    cin >> n >> lph;
    int loc[n];
    for (int i = 0; i < n; i++) {
        cin >> loc[i];
    }
    sort(loc, loc + n);

    int max_lines = lph * 5;
    int cum = 0;
    int res = 0;
    for (int i = 0; i < n; i++) {
        cum += loc[i];
        if (cum > max_lines) {
            break;
        }
        res++;
    }
    cout << res << endl;
}