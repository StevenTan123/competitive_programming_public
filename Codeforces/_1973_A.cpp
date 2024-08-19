#include <bits/stdc++.h>
using namespace std;

void solve() {
    int p1, p2, p3;
    cin >> p1 >> p2 >> p3;

    if ((p1 + p2 + p3) % 2 == 1) {
        cout << "-1\n";
        return;
    }
    
    priority_queue<int> pq;
    if (p1 > 0) pq.push(p1);
    if (p2 > 0) pq.push(p2);
    if (p3 > 0) pq.push(p3);
    int cnt = 0;
    while (pq.size() >= 2) {
        int one = pq.top();
        pq.pop();
        int two = pq.top();
        pq.pop();
        one--; two--;
        cnt++;
        if (one > 0) {
            pq.push(one);
        }
        if (two > 0) {
            pq.push(two);
        }
    }
    cout << cnt << "\n";
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