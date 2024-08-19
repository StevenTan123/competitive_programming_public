#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, x, y;
    cin >> n >> x >> y;
    int chosen[x];
    for (int i = 0; i < x; i++) {
        cin >> chosen[i];
        chosen[i]--;
    }
    sort(chosen, chosen + x);

    vector<int> even_gaps;
    vector<int> odd_gaps;
    int res = 0;
    for (int i = 0; i < x; i++) {
        int gap = chosen[(i + 1) % x] - chosen[i];
        gap = (gap + n) % n;
        if (gap == 2) {
            res++;
        } else if (gap > 2) {
            if (gap % 2 == 0) {
                even_gaps.push_back(gap);
            } else {
                odd_gaps.push_back(gap);
            }
        }
    }
    res += x - 2;
    sort(even_gaps.begin(), even_gaps.end());
    sort(odd_gaps.begin(), odd_gaps.end());
    
    for (int gap : even_gaps) {
        int moves = gap / 2 - 1;
        if (moves <= y) {
            y -= moves;
            res += moves + gap / 2;
        } else {
            res += 2 * y;
            y = 0;
            break;
        }
    }

    for (int gap : odd_gaps) {
        int moves = gap / 2;
        if (moves <= y) {
            y -= moves;
            res += moves * 2;
        } else {
            res += 2 * y;
            y = 0;
            break;
        }
    }
    cout << res << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}