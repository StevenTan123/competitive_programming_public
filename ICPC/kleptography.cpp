#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, m;
    cin >> n >> m;
    
    string last, cipher;
    cin >> last;
    cin >> cipher;

    char a[m];
    for (int i = 0; i < n; i++) {
        a[m - n + i] = last[i];
    }

    for (int i = n; i < m + n; i += n) {
        for (int j = m - i - 1; j >= max(m - i - n, 0); j--) {
            a[j] = (cipher[j + n] - a[j + n] + 26) % 26 + 97;
        }
    }

    for (int i = 0; i < m; i++) {
        cout << a[i];
    }
    cout << endl;
}