#include <bits/stdc++.h>
using namespace std;

const int MAXN = 200005;
int first[6] {1, 2, 2, 3, 3, 4};
int res[MAXN];

void solve() {
    int n;
    cin >> n;

    int *use = res;
    if (n <= 6) {
        use = first; 
    }
    set<int> unique;
    for (int i = 0; i < n; i++) {
        unique.insert(use[i]);
    }
    
    cout << unique.size() << "\n";
    for (int i = 0; i < n; i++) {
        cout << use[i] << " ";
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 0; i < MAXN; i++) {
        int cur = i + 1;
        if (cur & 1) {
            if (cur & 2) {
                res[i] = 1;
            } else {
                res[i] = 2;
            }
        } else {    
            if (cur & 2) {
                res[i] = 3;
            } else {
                res[i] = 4;
            }
        }
    }

    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}