#include <bits/stdc++.h>
using namespace std;

const int MAXN = 1000005;
const int K = 21;
int log_two[MAXN];

struct SparseTable {
    int N;
    int **mi;
    SparseTable(int n, int *a) : N(n) {
        mi = new int*[N];
        for (int i = 0; i < N; i++) {
            mi[i] = new int[K + 1];
            mi[i][0] = a[i];
        }
        for (int j = 1; j <= K; j++) {
            for(int i = 0; i + (1 << j) <= N; i++) {
                mi[i][j] = min(mi[i][j - 1], mi[i + (1 << (j - 1))][j - 1]);
            }
        }
    }
    ~SparseTable() {
        for (int i = 0; i < N; i++) delete[] mi[i];
        delete[] mi;
    }
    int rmq(int l, int r) {
        int j = log_two[r - l + 1];
        return min(mi[l][j], mi[r - (1 << j) + 1][j]);
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    log_two[1] = 0;
    for (int i = 2; i < MAXN; i++) {
        log_two[i] = log_two[i / 2] + 1;
    }
}