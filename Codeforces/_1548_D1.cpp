#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    // cnt[i][j] = # points with (x/2) % 2 == i && (y/2) % 2 == j
    int cnt[2][2] = {{0, 0}, {0, 0}};
    for (int i = 0; i < n; i++) {
        int x, y;
        cin >> x >> y;
        cnt[(x / 2) % 2][(y / 2) % 2]++;
    }
    
    long long res = 0;
    for (int i = 0; i < 4; i++) {

        int xi = i / 2; int yi = i % 2;
        if (cnt[xi][yi] >= 3) {
            res += (long long) cnt[xi][yi] * (cnt[xi][yi] - 1) * (cnt[xi][yi] - 2) / 6;
        }

        for (int j = i + 1; j < 4; j++) {

            int xj = j / 2; int yj = j % 2;
            if (cnt[xi][yi] >= 2) {
                res += (long long) cnt[xi][yi] * (cnt[xi][yi] - 1) / 2 * cnt[xj][yj];
            }
            if (cnt[xj][yj] >= 2) {
                res += (long long) cnt[xj][yj] * (cnt[xj][yj] - 1) / 2 * cnt[xi][yi];
            }

            for (int k = j + 1; k < 4; k++) {  
                int xk = k / 2; int yk = k % 2;
                
                // three points have parities (xi, yi), (xj, yj), (xk, yk)
                // parity of the number of boundary points
                int b_par = (xi == xj && yi == yj ? 0 : 1) + (xj == xk && yj == yk ? 0 : 1) + (xk == xi && yk == yi ? 0 : 1);
                if (b_par % 2 == 0) {
                    res += (long long) cnt[xi][yi] * cnt[xj][yj] * cnt[xk][yk];
                }
            }
        }
    }
    cout << res << endl;
}