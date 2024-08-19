#include <bits/stdc++.h>
using namespace std;

const long long MOD = 998244353;

struct Node {
    Node *prev = nullptr, *next = nullptr;
    int val;
    Node(int v) : val(v) {}
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, q;
    cin >> n >> q;
    int l[q];
    int r[q];
    for (int i = 0; i < q; i++) {
        cin >> l[i];
        l[i]--;
    }
    for (int i = 0; i < q; i++) {
        cin >> r[i];
        r[i]--;
    }

    vector<Node *> ll(n, nullptr);
    ll[n - 1] = new Node(n - 1);
    for (int i = 0; i < q; i++) {
        if (ll[l[i]]) {
            Node *cur = ll[l[i]];
            Node *add = new Node(r[i]);
            ll[r[i]] = add;
            Node *right = cur->next;
            cur->next = add;
            add->prev = cur;
            add->next = right;
            if (right) {
                right->prev = add;
            }
        } else {
            Node *cur = ll[r[i]];
            Node *add = new Node(l[i]);
            ll[l[i]] = add;
            Node *left = cur->prev;
            cur->prev = add;
            add->next = cur;
            add->prev = left;
            if (left) {
                left->next = add;
            }
        }
    }

    Node *cur = ll[n - 1];
    while (cur->prev) {
        cur = cur->prev;
    }
    int cnt = 0;
    set<int> fixed;
    vector<int> cnts(n);
    while (cur) {
        if (cnt == 0) {
            cnts[cur->val]++;
        } else {
            cnts[max(cur->val, cur->prev->val)]++;
        }
        if (!cur->next) {
            cnts[cur->val]++;
        }
        fixed.insert(cur->val);
        cur = cur->next;
        cnt++;
    }
    for (int i = n - 2; i >= 0; i--) {
        cnts[i] += cnts[i + 1];
    }
    long long res = 1;
    int extra_slots = 0;
    for (int i = n - 1; i >= 0; i--) {
        if (!fixed.count(i)) {
            res *= cnts[i] + extra_slots;
            res %= MOD;
            extra_slots++;
        }
    }    
    cout << res << "\n";
}