#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int srows[n] = {0};
    int scols[n] = {0};
    bool grid[n][n];
    for (int i = 0; i < n; i++) {
        string row;
        cin >> row;
        for (int j = 0; j < n; j++) {
            if (row[j] == '-') {
                grid[i][j] = 0;
                srows[i]--;
                scols[j]--;
            } else {
                grid[i][j] = 1; 
            }
        }
    }
    int rows[n];
    int cols[n];
    bool possible = true;
    for (int i = 0; i < n; i++) {
        cin >> rows[i];
        rows[i] -= srows[i];
        if (rows[i] < 0) {
            possible = false;
        }
    }
    for (int i = 0; i < n; i++) {
        cin >> cols[i];
        cols[i] -= scols[i];
        if (cols[i] < 0) {
            possible = false;
        }
    }

    if (possible) {
        vector<vector<bool>> marked(n, vector<bool>(n, 0));
        for (int i = 0; i <= n; i++) {
            vector<pair<int, int>> col_sort;
            for (int j = 0; j < n; j++) {
                if (cols[j] != 0) {
                    col_sort.push_back({cols[j], j});
                }
            }
            if (i == n) {
                if (col_sort.size() > 0) {
                    possible = false;
                }
                break;
            }
            sort(col_sort.begin(), col_sort.end());
            if (rows[i] > col_sort.size()) {
                possible = false;
                break;
            } else {
                for (int j = 0; j < rows[i]; j++) {
                    int col = col_sort[col_sort.size() - j - 1].second;
                    marked[i][col] = 1;
                    cols[col]--;
                }
            }
        }
        if (possible) {
            cout << "Yes" << endl;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((grid[i][j] && marked[i][j]) || (!grid[i][j] && !marked[i][j])) {
                        cout << 1;
                    } else {
                        cout << 0;
                    }
                }
                cout << endl;
            }
        } else {
            cout << "No" << endl;
        }
    } else {
        cout << "No" << endl;
    }
}