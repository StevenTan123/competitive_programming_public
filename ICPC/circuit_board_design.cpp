#include <bits/stdc++.h>
using namespace std;

const double theta = 0.0002;

pair<double, double> draw_subtree(vector<int> tree[], int node, int prev, double x, double y, double x2, double y2, pair<double, double> coords[]) {
    double xb = x2;
    double yb = y2;
    double mag = sqrt((yb - y) * (yb - y) + (xb - x) * (xb - x));
    for (int child : tree[node]) {
        if (child == prev) {
            continue;
        }
        
        double ang = atan((yb - y) / (xb - x)) + theta;
        double cosa = cos(ang);
        double sina = sin(ang);

        xb = x + cosa * mag;
        yb = y + sina * mag;
        
        coords[child] = {x + cosa, y + sina};
        pair<double, double> highest = draw_subtree(tree, child, node, coords[child].first, coords[child].second, xb + cosa, yb + sina, coords);
        xb = highest.first;
        yb = highest.second;
        mag = sqrt((yb - y) * (yb - y) + (xb - x) * (xb - x));
    }
    return {xb, yb};
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout << fixed << setprecision(12);

    int n;
    cin >> n;
    vector<int> tree[n];
    for (int i = 0; i < n - 1; i++) {
        int a, b;
        cin >> a >> b;
        tree[--a].push_back(--b);
        tree[b].push_back(a);
    }

    pair<double, double> coords[n];
    draw_subtree(tree, 0, -1, 0, 0, 1, 0, coords);
    for (int i = 0; i < n; i++) {
        cout << coords[i].first << " " << coords[i].second << "\n";
    }
}