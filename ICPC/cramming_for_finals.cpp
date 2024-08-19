#include <bits/stdc++.h>
using namespace std;

const int MAX_LEN = 5000000;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int r, c, d, n;
    cin >> r >> c >> d >> n;

    pair<int, int> circles[n];
    vector<tuple<int, int, int>> segs;
    for (int i = 0; i < n; i++) {
        int rr, cc;
        cin >> rr >> cc;
        circles[i] = {rr - 1, cc - 1};
        segs.emplace_back(circles[i].first, circles[i].second, n + 1);
        segs.emplace_back(circles[i].first, circles[i].second + 1, -(n + 1));
    }

    if (r > MAX_LEN) {
        cout << 0 << endl;
        return 0;
    }

    for (int i = 0; i < n; i++) {
        auto [rr, cc] = circles[i];
        for (int y = -d; y <= d; y++) {
            if (y + rr >= 0 && y + rr < r) {
                int x = sqrt(d * d - y * y);
                segs.emplace_back(y + rr, max(0, cc - x), 1);
                segs.emplace_back(y + rr, min(c, cc + x + 1), -1);
            }
        }
    }
    sort(segs.begin(), segs.end());
    
    int segs_p = 0;
    int min_inter = n + 1;
    for (int i = 0; i < r; i++) {
        int cnt = 0;
        int start = -1;
        int end = -1;
        while (segs_p < (int) segs.size() && get<0>(segs[segs_p]) == i) {
            auto [row, x, add] = segs[segs_p];
            if (start == -1) {
                start = x;
            }
            end = x;

            cnt += add;
            if (segs_p == (int) segs.size() - 1 || x < get<1>(segs[segs_p + 1])) {
                if (x < c) {
                    min_inter = min(min_inter, cnt);
                }
            }
            segs_p++;
        }
        
        if (start > 0 || (end != -1 && end < c)) {
            min_inter = 0;
        }
        if (min_inter == 0) {
            break;
        }
    }
    cout << min_inter << endl;
}