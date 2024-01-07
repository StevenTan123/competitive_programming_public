#include <bits/stdc++.h>
using namespace std;

const int MAXN = 100005;
long long pre[MAXN], dp[MAXN];
int a[MAXN], dp2[MAXN];
char ops[MAXN];

void fill_ops(int l, int r) {
    while (l < r && a[l] == 1) {
        ops[l] = '+';
        l++;
    }
    while (r > l && a[r] == 1) {
        ops[r - 1] = '+';
        r--;
    }
    long long prod = 1;
    long long lim = (r - l + 1) * 20;
    for (int i = l; i <= r; i++) {
        prod *= a[i];
        pre[i] = prod;
        if (prod > lim) {
            break;   
        }
    }
    if (prod > lim) {
        for (int i = l; i < r; i++) {
            ops[i] = '*';
        }
    } else {
        vector<int> prev;
        prev.push_back(l);
        for (int i = l; i <= r; i++) {
            dp[i] = a[i] + (i > l ? dp[i - 1] : 0);
            dp2[i] = i - 1;
            if (a[i] == 1) {
                continue;
            }
            for (int ind : prev) {
                long long next = ind == l ? pre[i] : (dp[ind - 1] + pre[i] / pre[ind - 1]);
                if (next > dp[i]) {
                    dp[i] = next;
                    dp2[i] = ind - 1;
                }
            }
            prev.push_back(i);
        }
        int cur = r;
        for (int i = cur; i >= l; i--) {
            if (i == cur) {
                if (i < r) {
                    ops[i] = '+';
                }
                cur = dp2[cur];
            } else if (i < r) {
                ops[i] = '*';
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    string s;
    cin >> s;
    bool add = false, sub = false, mul = false;
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '+') add = true;
        else if (s[i] == '-') sub = true;
        else mul = true;
    }

    if (s.length() == 1 || !mul) {
        char op = s[0];
        if (add) op = '+';
        for (int i = 0; i < n - 1; i++) {
            ops[i] = op;
        }
    } else if (!add) {
        bool seen_zero = false;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] == 0) {
                seen_zero = true;
            }
            if (a[i + 1] != 0 || seen_zero) ops[i] = '*';
            else ops[i] = '-';
        }
    } else {
        int prev = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                if (i - 1 > prev) fill_ops(prev, i - 1);
                prev = i + 1;

                if (i > 0) ops[i - 1] = '+';
                if (i < n - 1) ops[i] = '+';
            }
            if (i == n - 1 && i > prev) {
                fill_ops(prev, i);
            }
        }
    }
    
    for (int i = 0; i < n - 1; i++) {
        printf("%d%c", a[i], ops[i]);
    }
    printf("%d\n", a[n - 1]);
}