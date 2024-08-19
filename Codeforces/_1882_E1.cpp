#include <bits/stdc++.h>
using namespace std;

void add_swap(int n, vector<int> &moves, int i, int j) {
    moves.push_back(i + 1);
    moves.push_back(j - i);
    moves.push_back(n - j);
}

vector<int> solve_arr(int n, int a[]) {
    vector<int> moves;
    for (int i = 0; i < n - 1; i++) {
        int min_ind = i;
        for (int j = i + 1; j < n; j++) {
            if (a[j] < a[min_ind]) {
                min_ind = j;
            }
        }
        if (min_ind > i) {
            swap(a[i], a[min_ind]);
            add_swap(n, moves, i, min_ind);
        }
    }
    return moves;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    int b[m];
    for (int i = 0; i < m; i++) {
        cin >> b[i];
    }
    
    vector<int> a_moves = solve_arr(n, a);
    vector<int> b_moves = solve_arr(m, b);
    int diff = abs((int) a_moves.size() - (int) b_moves.size());
    if (diff % 2 == 1) {
        if (n % 2 == 1) {
            for (int i = 1; i <= n; i++) {
                a_moves.push_back(1);
            }
        } else if (m % 2 == 1) {
            for (int i = 1; i <= m; i++) {
                b_moves.push_back(1);
            }
        } else {
            cout << "-1\n";
            return 0;
        }
    }
    if (a_moves.size() < b_moves.size()) {
        for (unsigned i = a_moves.size(); i < b_moves.size(); i += 2) {
            a_moves.push_back(1);
            a_moves.push_back(n);
        }
    } else {
        for (unsigned i = b_moves.size(); i < a_moves.size(); i += 2) {
            b_moves.push_back(1);
            b_moves.push_back(m);
        }
    }    

    cout << a_moves.size() << "\n";
    for (int i = 0; i < a_moves.size(); i++) {
        cout << a_moves[i] << " " << b_moves[i] << "\n";
    }
}