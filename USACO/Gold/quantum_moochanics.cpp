#include <bits/stdc++.h>
using namespace std;

long long ceil_div(long long a, long long b) {
    long long res = a / b;
    if (a % b != 0) {
        res++;
    }
    return res;
}

void solve() {
    int N;
    cin >> N;
    long long p[N];
    set<int> active;
    for (int i = 0; i < N; i++) {
        cin >> p[i];
        active.insert(i);
    }
    int s[N];
    for (int i = 0; i < N; i++) {
        cin >> s[i];
    }
    
    priority_queue<tuple<long long, int, int>> pairs;
    for (int i = 0; i < N - 1; i++) {
        long long moves = ceil_div(p[i + 1] - p[i], s[i] + s[i + 1]) * 2;
        if (i % 2 == 0) {
            moves--;
        }
        pairs.emplace(-moves, i, i + 1);
    }

    long long res[N];
    while (!pairs.empty()) {
        auto [moves, a, b] = pairs.top();
        moves *= -1;
        pairs.pop();

        if (!active.count(a) || !active.count(b)) {
            continue;
        }

        res[a] = moves;
        res[b] = moves;

        active.erase(b);
        auto it = active.lower_bound(a);
        int left = -1;
        int right = -1;
        if (it != active.begin()) {
            left = *prev(it, 1);
        }
        it++;
        if (it != active.end()) {
            right = *it;
        }
        active.erase(a);

        if (left != -1 && right != 1) {
            long long nmoves = ceil_div(p[right] - p[left], s[left] + s[right]) * 2;
            if (left % 2 == 0) {
                nmoves--;
            }
            pairs.emplace(-nmoves, left, right);
        }
    }

    for (int i = 0; i < N; i++) {
        cout << res[i];
        if (i < N - 1) {
            cout << " ";
        }
    }
    cout << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T;
    cin >> T;
    while (T--) {
        solve();
    }
}