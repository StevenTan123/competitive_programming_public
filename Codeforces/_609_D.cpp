#include <bits/stdc++.h>
using namespace std;

const int MAXN = 200005;
int a[MAXN];
int b[MAXN];
pair<int, int> gadgets[MAXN];
int n, m, k, s;
vector<pair<int, int>> bought;

bool works(int ind) {
    int min_dollars = 0;
    int min_pounds = 0;
    
    for (int i = 1; i <= ind; i++) {
        if (a[i] < a[min_dollars]) {
            min_dollars = i;
        }
        if (b[i] < b[min_pounds]) {
            min_pounds = i;
        }
    }
    vector<pair<long long, int>> costs(m);
    for (int i = 0; i < m; i++) {
        long long cost = (gadgets[i].first == 1 ? (long long) gadgets[i].second * a[min_dollars] : (long long) gadgets[i].second * b[min_pounds]);
        costs[i] = {cost, i};
    }
    sort(costs.begin(), costs.end());

    long long spent = 0;
    for (int i = 0; i < k; i++) {
        spent += costs[i].first;
    }
    if (spent <= s) {
        bought.clear();
        for (int i = 0; i < k; i++) {
            bought.emplace_back((gadgets[costs[i].second].first == 1 ? min_dollars + 1 : min_pounds + 1), costs[i].second + 1);
        }
        return true;
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m >> k >> s;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> b[i];
    }
    for (int i = 0; i < m; i++) {
        int t, c;
        cin >> t >> c;
        gadgets[i] = {t, c};
    }

    int l = 0;
    int r = n - 1;
    int res = -1;
    while (l <= r) {
        int mid = (l + r) / 2;
        if (works(mid)) {
            res = mid;
            r = mid - 1;
        } else {
            l = mid + 1;
        }
    }
    if (res == -1) {
        cout << "-1\n";
    } else {
        cout << res + 1 << "\n";
        for (auto [day, gadget] : bought) {
            cout << gadget << " " << day << "\n";
        }
    }
}