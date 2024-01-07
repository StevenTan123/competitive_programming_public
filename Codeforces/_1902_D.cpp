#include <bits/stdc++.h>
using namespace std;

typedef set<tuple<int, int, int>> tset;
typedef set<tuple<int, int, int>>::iterator tset_it;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, q;
    cin >> n >> q;
    string s;
    cin >> s;

    // {x, y, ind}
    tset forw;
    tset backw;
    int x = 0;
    int y = 0;
    pair<int, int> fcoords[n];
    pair<int, int> bcoords[n];
    forw.insert({0, 0, -1});
    for (int i = 0; i < n; i++) {
        if (s[i] == 'R') x++;
        else if (s[i] == 'U') y++;
        else if (s[i] == 'L') x--;
        else y--;
        forw.insert({x, y, i});
        fcoords[i] = {x, y};
    }
    x = 0;
    y = 0;
    backw.insert({0, 0, n});
    for (int i = n - 1; i >= 0; i--) {
        if (s[i] == 'R') x++;
        else if (s[i] == 'U') y++;
        else if (s[i] == 'L') x--;
        else y--;
        backw.insert({x, y, i});
        bcoords[i] = {x, y};
    }

    for (int i = 0; i < q; i++) {
        int qx, qy, l, r;
        cin >> qx >> qy >> l >> r;
        l--; r--;

        bool visited = false;
        tset_it first = forw.lower_bound({qx, qy, -1});
        if (first != forw.end() && get<0>(*first) == qx && get<1>(*first) == qy && get<2>(*first) <= l - 1) {
            visited = true;
        }
        tset_it last = forw.lower_bound({qx, qy, r});
        if (last != forw.end() && get<0>(*last) == qx && get<1>(*last) == qy) {
            visited = true;
        }

        int startx = 0;
        int starty = 0;
        int endx = 0;
        int endy = 0;
        if (l > 0) {
            startx = fcoords[l - 1].first;
            starty = fcoords[l - 1].second;
        }
        if (r < n - 1) {
            endx = bcoords[r + 1].first;
            endy = bcoords[r + 1].second;
        }
        int qqx = qx - startx + endx;
        int qqy = qy - starty + endy;
        tset_it mid = backw.lower_bound({qqx, qqy, l});
        if (mid != backw.end() && get<0>(*mid) == qqx && get<1>(*mid) == qqy && get<2>(*mid) <= r) {
            visited = true;
        }

        cout << (visited ? "YES" : "NO") << endl;
    }
}