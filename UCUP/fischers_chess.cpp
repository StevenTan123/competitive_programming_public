#include <bits/stdc++.h>
using namespace std;

vector<string> boards;

bool valid(string &board) {
    vector<int> b_inds;
    vector<int> r_inds;
    int k_ind;
    for (int i = 0; i < board.size(); i++) {
        if (board[i] == 'B') {
            b_inds.push_back(i);
        } else if (board[i] == 'K') {
            k_ind = i;
        } else if (board[i] == 'R') {
            r_inds.push_back(i);
        }
    }
    return ((b_inds[0] % 2) != (b_inds[1] % 2) && k_ind > r_inds[0] && k_ind < r_inds[1]);
}

int match_cnt(string &a, string &b) {
    int ret = 0;
    for (int i = 0; i < a.size(); i++) {
        ret += (a[i] == b[i]);
    }
    return ret;
}

int solve() {
    vector<string> left(boards.begin(), boards.end());
    int cnt = 0;
    while (true) {
        // cnts[i][j] = # of boards left matching j characters with board i
        vector<vector<int>> cnts(boards.size(), vector<int>(9));
        vector<int> max_cnt(boards.size());
        int min_max = 0;
        for (int i = 0; i < boards.size(); i++) {
            for (int j = 0; j < left.size(); j++) {
                cnts[i][match_cnt(left[i], left[j])]++;
            }
            for (int j = 0; j < 9; j++) {
                max_cnt[i] = max(max_cnt[i], cnts[i][j]);
            }
            if (max_cnt[i] < max_cnt[min_max] || max_cnt[i] == max_cnt[min_max] && cnts[i][8] > 0) {
                min_max = i;
            }
        }
        cout << boards[min_max] << endl;
        
        int match;
        cin >> match;
        if (match == 8) {
            return 0;
        }
        if (++cnt >= 6) {
            return 1;
        }

        vector<string> next_left;
        for (string board : left) {
            if (match_cnt(boards[min_max], board) == match) {
                next_left.push_back(board);
            }
        }
        left = next_left;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string cur_board = "BBKNNQRR";
    do {
        if (valid(cur_board)) {
            boards.push_back(cur_board);
        }
    } while (next_permutation(cur_board.begin(), cur_board.end()));

    string status;
    int game;
    cin >> status >> game;
    while (true) {
        if (solve()) {
            break;
        }
        cin >> status;
        if (status == "END") {
            break;
        }
        cin >> game;
    }
}