#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int p[n];
    int inds[n + 1];
    for (int i = 0; i < n; i++) {
        cin >> p[i];
        inds[p[i]] = i;
    }

    int l[n];
    stack<int> st1;
    for (int i = 0; i < n; i++) {
        while (!st1.empty() && p[i] > st1.top()) {
            st1.pop();
        }
        l[i] = (st1.empty() ? 0 : inds[st1.top()] + 1);
        st1.push(p[i]);
    }
    int r[n];
    stack<int> st2;
    for (int i = n - 1; i >= 0; i--) {
        while (!st2.empty() && p[i] > st2.top()) {
            st2.pop();
        }
        r[i] = (st2.empty() ? n - 1 : inds[st2.top()] - 1);
        st2.push(p[i]);
    }
    
    long long res = 0;
    for (int i = 0; i < n; i++) {
        int ldiff = i - l[i];
        int rdiff = r[i] - i;
        if (ldiff < rdiff) {
            for (int j = l[i]; j < i; j++) {
                if (inds[p[i] - p[j]] > i && inds[p[i] - p[j]] <= r[i]) {
                    res++;
                }
            }
        } else {
            for (int j = i + 1; j <= r[i]; j++) {
                if (inds[p[i] - p[j]] < i && inds[p[i] - p[j]] >= l[i]) {
                    res++;
                }
            }
        }
    }
    cout << res << endl;
}