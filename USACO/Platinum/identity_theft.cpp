#include <bits/stdc++.h>
using namespace std;

struct Vertex {
    int left, right, cnt;
    Vertex() : left(-1), right(-1), cnt(0) {}
};

vector<Vertex> trie(1);
vector<int> depths(1);
// Priority queue of leaf depths in subtree of each node that have bitstrings ending there.
vector<priority_queue<int, std::vector<int>, std::greater<int>> *> pq1;
// Priority queue of leaf depths in subtree of each node that don't have bitstrings ending there.
vector<priority_queue<int, std::vector<int>, std::greater<int>> *> pq2;

long long res = 0;

void add_string(string &s) {
    int v = 0;
    int depth = 0;
    for (char ch : s) {
        if (ch == '0') {
            if (trie[v].left == -1) {
                trie[v].left = trie.size();
                trie.emplace_back();
                depths.push_back(0);
            }
            v = trie[v].left;
        } else {
            if (trie[v].right == -1) {
                trie[v].right = trie.size();
                trie.emplace_back();
                depths.push_back(0);
            }
            v = trie[v].right;
        }
        depths[v] = ++depth;
    }
    trie[v].cnt++;
}

// Merge priority queues at indices one and two into a priority queue at index to.
void merge(vector<priority_queue<int, std::vector<int>, std::greater<int>> *> &pqs, int one, int two, int to) {
    if (pqs[one]->size() <= pqs[two]->size()) {
        pqs[to] = pqs[two];
        while (!pqs[one]->empty()) {
            pqs[two]->push(pqs[one]->top());
            pqs[one]->pop();
        }
    } else {
        pqs[to] = pqs[one];
        while (!pqs[two]->empty()) {
            pqs[one]->push(pqs[two]->top());
            pqs[two]->pop();
        }
    }
}

void dfs(int cur) {
    if (trie[cur].left == -1 && trie[cur].right == -1) {
        pq1[cur] = new priority_queue<int, std::vector<int>, std::greater<int>>();
        pq2[cur] = new priority_queue<int, std::vector<int>, std::greater<int>>();
        pq1[cur]->push(depths[cur]);
        trie[cur].cnt--;
    } else if (trie[cur].left == -1) {
        dfs(trie[cur].right);
        pq1[cur] = pq1[trie[cur].right];
        pq2[cur] = pq2[trie[cur].right];
        pq2[cur]->push(depths[cur] + 1);
    } else if (trie[cur].right == -1) {
        dfs(trie[cur].left);
        pq1[cur] = pq1[trie[cur].left];
        pq2[cur] = pq2[trie[cur].left];
        pq2[cur]->push(depths[cur] + 1);
    } else {
        dfs(trie[cur].left);
        dfs(trie[cur].right);
        merge(pq1, trie[cur].left, trie[cur].right, cur);
        merge(pq2, trie[cur].left, trie[cur].right, cur);
    }
    for (int i = 0; i < trie[cur].cnt; i++) {
        int top1 = (pq1[cur]->empty() ? INT_MAX : pq1[cur]->top());
        int top2 = (pq2[cur]->empty() ? INT_MAX : pq2[cur]->top());
        if (top2 - 2 <= top1) {
            pq2[cur]->pop();
            pq1[cur]->push(top2);
            res += top2 - depths[cur];
        } else {
            pq1[cur]->pop();
            pq1[cur]->push(top1 + 1);
            pq1[cur]->push(top1 + 1);
            res += top1 - depths[cur] + 2;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    string bitstrs[n];
    for (int i = 0; i < n; i++) {
        cin >> bitstrs[i];
        add_string(bitstrs[i]);
    }
    pq1.resize(trie.size());
    pq2.resize(trie.size());
    dfs(0);
    cout << res << "\n";
}