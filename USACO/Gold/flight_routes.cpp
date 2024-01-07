#include <bits/stdc++.h>
using namespace std;

int main() {
    int N;
    cin >> N;
    bool M[N][N];
    for (int i = 0; i < N; i++) {
        string line;
        if (i < N - 1) {
            cin >> line;
        }
        for (int j = 0; j < N; j++) {
            if (j <= i) {
                M[i][j] = 0;
            } else {
                M[i][j] = (line[j - i - 1] == '1' ? 1 : 0);
            }
        }
    }

    int res = 0;
    for (int i = N - 2; i >= 0; i--) {
        bool cur[N] = {0};
        for (int j = i + 1; j < N; j++) {
            if (cur[j] != M[i][j]) {
                res++;
                cur[j] = !cur[j];
                for (int k = j + 1; k < N; k++) {
                    cur[k] ^= M[j][k];
                }
            }
        }
    }
    cout << res << endl;
}