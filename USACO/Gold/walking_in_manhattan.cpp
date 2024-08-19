#include <bits/stdc++.h>
using namespace std;

const int MAXLOG = 20;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, Q;
    cin >> N >> Q;
    vector<int> horz;
    vector<int> vert;
    map<int, int> horz_map;
    map<int, int> vert_map;
    for (int i = 0; i < N; i++) {
        char type;
        int c;
        cin >> type >> c;
        if (type == 'H') {
            horz.push_back(c);
        } else {
            vert.push_back(c);
        }
    }
    sort(horz.begin(), horz.end());
    sort(vert.begin(), vert.end());
    for (int i = 0; i < horz.size(); i++) {
        horz_map[horz[i]] = i;
    }
    for (int i = 0; i < vert.size(); i++) {
        vert_map[vert[i]] = i;
    }

    int y_jmp[horz.size()][MAXLOG];
    int x_jmp[vert.size()][MAXLOG];
    int prev_even = horz.size();
    int prev_odd = horz.size();
    for (int i = horz.size() - 1; i >= 0; i--) {
        if (horz[i] % 2 == 0) {
            y_jmp[i][0] = prev_odd;
            prev_even = i;
        } else {
            y_jmp[i][0] = prev_even;
            prev_odd = i;
        }
        for (int j = 1; j < MAXLOG; j++) {
            y_jmp[i][j] = (y_jmp[i][j - 1] == horz.size() ? horz.size() : y_jmp[y_jmp[i][j - 1]][j - 1]);
        }
    }
    prev_even = vert.size();
    prev_odd = vert.size();
    for (int i = vert.size() - 1; i >= 0; i--) {
        if (vert[i] % 2 == 0) {
            x_jmp[i][0] = prev_odd;
            prev_even = i;
        } else {
            x_jmp[i][0] = prev_even;
            prev_odd = i;
        }
        for (int j = 1; j < MAXLOG; j++) {
            x_jmp[i][j] = (x_jmp[i][j - 1] == vert.size() ? vert.size() : x_jmp[x_jmp[i][j - 1]][j - 1]);
        }
    }

    for (int i = 0; i < Q; i++) {
        int x, y, d;
        cin >> x >> y >> d;

        auto x_it = vert_map.lower_bound(x);
        auto y_it = horz_map.lower_bound(y);
        if (y_it == horz_map.end()) {
            cout << x << " " << y + d << endl;
            continue;
        }
        if (x_it == vert_map.end()) {
            cout << x + d << " " << y << endl;
            continue;
        } 
        
        int x_cur = x_it->second;
        int y_cur = y_it->second;
        bool odd = false;
        if (y < horz[y_cur]) {
            int dist = horz[y_cur] - y;
            if (dist >= d) {
                cout << x << " " << y + d << endl;
                continue;
            }
            odd = dist % 2;
            d -= dist;
        } else if (x < vert[x_cur]) {
            int dist = vert[x_cur] - x;
            if (dist >= d) {
                cout << x + d << " " << y << endl;
                continue;
            }
            odd = dist % 2;
            d -= dist;
        }
        
        for (int j = MAXLOG - 1; j >= 0; j--) {
            int x_nxt = x_jmp[x_cur][j];
            int y_nxt = y_jmp[y_cur][j];
            if (x_nxt < vert.size() && y_nxt < horz.size()) {
                int dist = (vert[x_nxt] - vert[x_cur]) + (horz[y_nxt] - horz[y_cur]);
                if (dist <= d) {
                    x_cur = x_nxt;
                    y_cur = y_nxt;
                    d -= dist;
                }
            }
        }

        int x_final = vert[x_cur];
        int y_final = horz[y_cur];
        if (!odd) {
            int y_nxt = y_jmp[y_cur][0];
            if (y_nxt == horz.size()) {
                y_final += d;
            } else {
                int add = min(d, horz[y_nxt] - y_final);
                y_final += add;
                x_final += d - add;
            }
        } else {
            int x_nxt = x_jmp[x_cur][0];
            if (x_nxt == vert.size()) {
                x_final += d;
            } else {
                int add = min(d, vert[x_nxt] - x_final);
                x_final += add;
                y_final += d - add;
            }
        }
        cout << x_final << " " << y_final << endl;
    }
}