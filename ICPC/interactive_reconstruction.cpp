#include <bits/stdc++.h>
using namespace std;

const int MAXN = 30005;
const int MAXQ = 15;
bool queries[MAXQ][MAXN];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int len = 1;
    for (int i = 0; i < MAXQ; i++) {
        for (int j = 0; j < MAXN; j++) {
            if ((j / len) % 2 == 0) {
                queries[i][j] = 0;
            } else {
                queries[i][j] = 1;
            }
        }
        len *= 2;
    }

    int N;
    cin >> N;
    int deg[N];
    cout << "QUERY " << string(N, '1') << endl;
    deque<int> leaves;
    for (int i = 0; i < N; i++) {
        cin >> deg[i];
        if (deg[i] == 1) {
            leaves.push_back(i);
        }
    }

    int responses[MAXQ][N];
    for (int i = 0; i < MAXQ; i++) {
        stringstream ss;
        ss << "QUERY ";
        for (int j = 0; j < N; j++) {
            ss << queries[i][j];
        }
        cout << ss.str() << endl;
        for (int j = 0; j < N; j++) {
            cin >> responses[i][j];
        }
    }

    int par[N];
    while (!leaves.empty()) {
        int leaf = leaves.front();
        leaves.pop_front();
        if (deg[leaf] == 0) {
            par[leaf] = -1;
            continue;
        }

        int l = 0;
        int r = (1 << MAXQ);
        for (int i = MAXQ - 1; i >= 0; i--) {
            int m = (l + r) / 2;
            if (responses[i][leaf] > 0) {
                l = m;
            } else {
                r = m;
            }
        }
        par[leaf] = l;
        deg[l]--;
        if (deg[l] <= 1) {
            leaves.push_back(l);
        }

        for (int i = 0; i < MAXQ; i++) {
            if (queries[i][leaf]) {
                responses[i][l]--;
            }
        }
    }

    cout << "ANSWER" << endl;
    for (int i = 0; i < N; i++) {
        if (par[i] != -1) {
            cout << par[i] + 1 << " " << i + 1 << endl;
        }
    }
}