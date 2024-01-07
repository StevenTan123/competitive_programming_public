#include <bits/stdc++.h>
using namespace std;

long long cost(int val, int breaks) {
    int floor = val / (breaks + 1);
    int mod = val % (breaks + 1);
    long long res = (long long) mod * (floor + 1) * (floor + 1) + (long long) (breaks + 1 - mod) * floor * floor;
    return res; 
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, k;
    cin >> n >> k;
    int a[n];
    long long res = 0;
    priority_queue<tuple<long long, int, int>> pq;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        long long sq = (long long) a[i] * a[i];
        res += sq;
        pq.push({sq - cost(a[i], 1), a[i], 1});
    }


    for (int i = 0; i < k - n; i++) {
        tuple<long long, int, int> next = pq.top();
        pq.pop();

        res -= get<0>(next);
        long long imp = cost(get<1>(next), get<2>(next)) - cost(get<1>(next), get<2>(next) + 1);
        pq.push({imp, get<1>(next), get<2>(next) + 1});
    }
    cout << res << "\n";
}