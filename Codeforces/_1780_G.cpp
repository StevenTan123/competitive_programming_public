#include <bits/stdc++.h>
using namespace std;

int find(int par[], int node) {
    if (par[node] == node) {
        return node;
    }
    par[node] = find(par, par[node]);
    return par[node];
}

void merge(int par[], int sizes[], int a, int b) {
    a = find(par, a);
    b = find(par, b);
    if (a != b) {
        if (sizes[a] > sizes[b]) {
            swap(a, b);
        }
        par[a] = b;
        sizes[b] += sizes[a];
    }
}

vector<int> sort_cyclic_shifts(string const& s) {
    int n = s.size();
    const int alphabet = 256;
    vector<int> p(n), c(n), cnt(max(alphabet, n), 0);
    for (int i = 0; i < n; i++)
        cnt[s[i]]++;
    for (int i = 1; i < alphabet; i++)
        cnt[i] += cnt[i-1];
    for (int i = 0; i < n; i++)
        p[--cnt[s[i]]] = i;
    c[p[0]] = 0;
    int classes = 1;
    for (int i = 1; i < n; i++) {
        if (s[p[i]] != s[p[i-1]])
            classes++;
        c[p[i]] = classes - 1;
    }
    vector<int> pn(n), cn(n);
    for (int h = 0; (1 << h) < n; ++h) {
        for (int i = 0; i < n; i++) {
            pn[i] = p[i] - (1 << h);
            if (pn[i] < 0)
                pn[i] += n;
        }
        fill(cnt.begin(), cnt.begin() + classes, 0);
        for (int i = 0; i < n; i++)
            cnt[c[pn[i]]]++;
        for (int i = 1; i < classes; i++)
            cnt[i] += cnt[i-1];
        for (int i = n-1; i >= 0; i--)
            p[--cnt[c[pn[i]]]] = pn[i];
        cn[p[0]] = 0;
        classes = 1;
        for (int i = 1; i < n; i++) {
            pair<int, int> cur = {c[p[i]], c[(p[i] + (1 << h)) % n]};
            pair<int, int> prev = {c[p[i-1]], c[(p[i-1] + (1 << h)) % n]};
            if (cur != prev)
                ++classes;
            cn[p[i]] = classes - 1;
        }
        c.swap(cn);
    }
    return p;
}

vector<int> lcp_construction(string const& s, vector<int> const& p) {
    int n = s.size();
    vector<int> rank(n, 0);
    for (int i = 0; i < n; i++)
        rank[p[i]] = i;

    int k = 0;
    vector<int> lcp(n-1, 0);
    for (int i = 0; i < n; i++) {
        if (rank[i] == n - 1) {
            k = 0;
            continue;
        }
        int j = p[rank[i] + 1];
        while (i + k < n && j + k < n && s[i+k] == s[j+k])
            k++;
        lcp[rank[i]] = k;
        if (k)
            k--;
    }
    return lcp;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    string s;
    cin >> s;
    s += "$";
    vector<int> sa = sort_cyclic_shifts(s);
    
    pair<int, int> sorted[n - 1];
    vector<int> lcp_arr = lcp_construction(s, sa);
    for (int i = 2; i < n + 1; i++) {
        sorted[i - 2] = {lcp_arr[i - 1], i - 2};
    }    
    sort(sorted, sorted + (n - 1));

    int par[n];
    int sizes[n];
    for (int i = 0; i < n; i++) {
        par[i] = i;
        sizes[i] = 1;
    }
    vector<int> comps_cnt(n + 1);
    comps_cnt[1] = n;

    int lcp_p = n - 2;
    long long res = 0;
    for (int i = n; i >= 1; i--) {
        while (lcp_p >= 0 && sorted[lcp_p].first >= i) {
            auto [lcp, ind] = sorted[lcp_p];
            
            int size1 = sizes[find(par, ind)];
            int size2 = sizes[find(par, ind + 1)];
            comps_cnt[size1]--;
            comps_cnt[size2]--;
            comps_cnt[size1 + size2]++;
            merge(par, sizes, ind, ind + 1);

            lcp_p--;
        }
        for (int j = i; j <= n; j += i) {
            res += (long long) j * comps_cnt[j];
        }
    }
    cout << res << "\n";
}