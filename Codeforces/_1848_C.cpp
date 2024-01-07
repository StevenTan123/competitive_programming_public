#include <bits/stdc++.h>
using namespace std;
 
int count(int a, int b) {
    int res = 0;
    while (true) {
        if (a == 0) {
            break;
        }
        if (b == 0) {
            res++;
            break;
        }
        if (a == b) {
            res += 2;
            break;
        }
        int newa, newb;
        if (b > a / 2) {
            res++;
            newa = b;
            newb = abs(b - a);
        } else if ((a / b) % 2 == 0) {
            newa = a % b;
            newb = b;
        } else {
            res += 2;
            newa = a % b;
            newb = abs(a % b - b);
        }
        a = newa;
        b = newb;
    }
    return res % 3;
}
 
void solve()
{
    int n;
    cin >> n;
    int a[n];
    int b[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> b[i];
    }
    bool possible = true;
    int unique = -1;
    for (int i = 0; i < n; i++) {
        int mod3 = count(a[i], b[i]);
        if (a[i] != 0 || b[i] != 0) {
            if (unique == -1 || mod3 == unique) {
                unique = mod3;
            } else {
                possible = false;
                break;
            }
        }
    } 
    if (possible) {
        cout << "YES" << "\n";
    } else {
        cout << "NO" << "\n";
    }
}
 
int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        solve();
    }
}