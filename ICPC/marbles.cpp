#include <bits/stdc++.h>
using namespace std;

const int MAXDIM = 105;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N;
    int r[N];
    int c[N];
    for (int i = 0; i < N; i++) {
        cin >> r[i] >> c[i];
    }
    int grundy[MAXDIM][MAXDIM];
    int large = 1000000;
    for (int i = 0; i < MAXDIM; i++) {
        for (int j = 0; j < MAXDIM; j++) {
            if (i == 0 || j == 0 || i == j) {
                grundy[i][j] = large++;
            } else {
                set<int> to;
                for (int x = 1; x <= i; x++) {
                    to.insert(grundy[i - x][j]);
                }
                for (int x = 1; x <= j; x++) {
                    to.insert(grundy[i][j - x]);
                }
                for (int x = 1; x <= min(i, j); x++) {
                    to.insert(grundy[i - x][j - x]);
                }
                int mex = 0;
                while (to.count(mex)) {
                    mex++;
                }
                grundy[i][j] = mex;
            }
        }
    }
    int xor_grundy = 0;
    for (int i = 0; i < N; i++) {
        xor_grundy ^= grundy[r[i]][c[i]];
    } 
    cout << (xor_grundy == 0 ? "N\n" : "Y\n");
}