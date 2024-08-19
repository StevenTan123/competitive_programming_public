#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N;
    int a[2 * N];
    for (int i = 0; i < N; i++) {
        cin >> a[i];
        a[i + N] = a[i];
    }

    stack<int> st;
    int prev[2 * N];
    for (int i = 0; i < 2 * N; i++) {
        while (!st.empty() && a[i] < a[st.top()]) {
            st.pop();
        }
        if (i >= N) {
            prev[i] = st.top();
        }
        st.push(i);
    }
    while (!st.empty()) st.pop();
    int next[N];
    for (int i = 2 * N - 1; i >= 0; i--) {
        while (!st.empty() && a[i] <= a[st.top()]) {
            st.pop();
        }
        if (i < N) {
            if (st.empty()) {
                next[i] = i + N;
            } else {
                next[i] = st.top();
            }
        }
        st.push(i);
    }
    
    vector<long long> arith(N + 1);
    for (int i = 0; i < N; i++) {
        int left = N + i - prev[N + i];
        int right = next[i] - i;
        int mn = min(left, right);
        int mx = max(left, right);
        
        arith[1] += a[i];
        if (mn + 1 <= N) {
            arith[mn + 1] -= a[i];
            
            if (mx + 1 <= N) {
                arith[mx + 1] -= a[i];

                if (mx + mn + 1 <= N) {
                    arith[mx + mn + 1] += a[i];
                }
            }
        }
    }

    long long val = 0;
    long long diff = 0;
    for (int i = 1; i <= N + 1; i++) {
        if (i < N + 1) {
            diff += arith[i];
            val += diff;
        }
        if (i >= 2) {
            cout << val << "\n";
        }
    }
}