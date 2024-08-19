#include <bits/stdc++.h>
using namespace std;

bool topo_sort(vector<vector<int>> &graph, vector<int> &ord, vector<int> in_deg, int sign) {
    set<pair<int, int>> st;
    for (int i = 0; i < graph.size(); i++) {
        st.insert({in_deg[i], i * sign});
    }
    while (!st.empty()) {
        auto [deg, node] = *st.begin();
        if (deg != 0) {
            return false;
        }
        st.erase(st.begin());

        ord.push_back(node * sign);
        for (int nei : graph[node * sign]) {
            st.erase({in_deg[nei], nei * sign});
            st.insert({--in_deg[nei], nei * sign});
        }
    }
    return true;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i]--;
    }
    int b[n];
    for (int i = 0; i < n; i++) {
        cin >> b[i];
        b[i]--;
    }

    int m = 0;
    vector<int> in_deg(n);
    vector<vector<int>> graph(n);
    stack<int> stk;
    for (int i = 0; i < n; i++) {
        while (!stk.empty() && stk.top() <= a[i]) {
            stk.pop();
        }
        if (!stk.empty()) {
            graph[stk.top()].push_back(a[i]);
            in_deg[a[i]]++;
            m++;
        }
        stk.push(a[i]);
    }

    while (!stk.empty()) {
        stk.pop();
    }
    for (int i = 0; i < n; i++) {
        while (!stk.empty() && stk.top() >= b[i]) {
            stk.pop();
        }
        if (!stk.empty()) {
            graph[stk.top()].push_back(b[i]);
            in_deg[b[i]]++;
            m++;
        }
        stk.push(b[i]);
    }

    vector<int> a_ord;
    vector<int> b_ord;
    bool works = topo_sort(graph, a_ord, in_deg, 1) && topo_sort(graph, b_ord, in_deg, -1);
    if (works) {
        for (int i = 0; i < n; i++) {
            if (a_ord[i] != a[i] || b_ord[i] != b[i]) {
                works = false;
                break;
            }
        }
    }
    if (works) {
        cout << "YES\n";
        cout << m << "\n";
        for (int i = 0; i < n; i++) {
            for (int nei : graph[i]) {
                cout << i + 1 << " " << nei + 1 << endl;
            }
        }
    } else {
        cout << "NO\n";
    }
}