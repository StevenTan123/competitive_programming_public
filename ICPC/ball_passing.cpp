#include <bits/stdc++.h>
using namespace std;

double dist(pair<int, int> p1, pair<int, int> p2) {
    return sqrt((double) (p1.first - p2.first) * (p1.first - p2.first) + (double) (p1.second - p2.second) * (p1.second - p2.second));
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N;
    string S;
    cin >> S;
    pair<int, int> pts[N];
    for (int i = 0; i < N; i++) {
        int X, Y;
        cin >> X >> Y;
        pts[i] = {X, Y};
    }

    vector<int> boys;
    vector<int> girls;
    for (int i = 0; i < N; i++) {
        if (S[i] == 'B') {
            boys.push_back(i);
        } else {
            girls.push_back(i);
        } 
    }

    double res = 0;
    for (int i = 0; i * 2 < boys.size(); i++) {
        res += dist(pts[boys[i]], pts[boys[i + boys.size() / 2]]);
    }
    for (int i = 0; i * 2 < girls.size(); i++) {
        res += dist(pts[girls[i]], pts[girls[i + girls.size() / 2]]);
    }
    cout << fixed << setprecision(10) << res << "\n";
}