#include <bits/stdc++.h>
using namespace std;
 
void solve() {
    int a, b, xk, yk, xq, yq;
    cin >> a >> b >> xk >> yk >> xq >> yq;
    
    vector<pair<int, int>> coords;
    for (int i = 0; i < 2; i++) {
        int sign1 = i == 0 ? -1 : 1;
        for (int j = 0; j < 2; j++) {
            int sign2 = j == 0 ? -1 : 1;
            coords.push_back({xk + sign1 * a, yk + sign2 * b});
            if (a != b) {
                coords.push_back({xk + sign1 * b, yk + sign2 * a});
            }
        }
    }
 
    int count = 0;
    for (pair<int, int> coord : coords) {
        int diffx = abs(xq - coord.first);
        int diffy = abs(yq - coord.second);
        if ((diffx == a && diffy == b) || (diffx == b && diffy == a)) {
            count++;
        }
    }
    cout << count << endl;
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