#include <bits/stdc++.h>
using namespace std;

const int K = 26;

struct Vertex {
    map<char, int> next;
    int count1, count2;

    Vertex() : count1(0), count2(0) {}
};

void add_string(vector<Vertex> &trie, string const& s, bool type) {
    int v = 0;
    for (char ch : s) {
        if (!trie[v].next.count(ch)) {
            trie[v].next[ch] = trie.size();
            trie.emplace_back();
        }
        v = trie[v].next[ch];
        if (type) {
            trie[v].count1++;
        } else {
            trie[v].count2++;
        }
    }
}

long long dfs(vector<Vertex> &trie, int v) {
    long long ret = 0;
    if (v != 0) {
        ret = (long long) trie[v].count1 * trie[v].count2;
    }
    for (int i = 0; i < K; i++) {
        char ch = i + 'a';
        if (trie[v].next.count(ch)) {
            ret += dfs(trie, trie[v].next[ch]);
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    vector<Vertex> trie(1);
    long long total = 0;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        total += (long long) s.length() * 2 * n;
        string s_rev(s);
        reverse(s_rev.begin(), s_rev.end());
        add_string(trie, s, true);
        add_string(trie, s_rev, false);
    }
    
    long long res = dfs(trie, 0);
    cout << total - res * 2 << endl;
}