#include <bits/stdc++.h>
using namespace std;

long long pow62 = 1;
const int check = 100;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    for (int i = 0; i < 62; i++) {
        pow62 *= 2;
    }

    int s;
    cin >> s;
    if (s == 0) {
        cout << "? flip" << endl;
        cin >> s;
    }
    for (int i = 0; i < check; i++) {
        cout << "? right" << endl;
        cin >> s;
        if (s == 1) {
            cout << "? flip" << endl;
            cin >> s;
        }
    }
    for (int i = 0; i < check; i++) {
        cout << "? left" << endl;
        cin >> s;
    }
    // train is less than length of check
    if (s == 0) {
        int n = 0;
        while (s == 0) {
            cout << "? flip" << endl;
            cin >> s;
            cout << "? right" << endl;
            cin >> s;
            n++;
        }
        cout << "! " << n;
    } else {
        cout << "? flip" << endl;
        cin >> s;

        long long hash = 0;
        for (int i = 0; i < 63; i++) {
            hash = hash * 2 + rand() % 2;
        }
        long long cur = hash;
        for (int i = 0; i < 63; i++) {
            if (cur % 2 == 1) {
                cout << "? flip" << endl;
                cin >> s;
            } 
            cout << "? right" << endl;
            cin >> s;
            cur /= 2;
        }
        
        int count = 0;
        cur = hash;
        while (true) {
            cur >>= 1;
            cur += s * pow62;
            if (cur == hash) {
                break;
            }
            cout << "? right" << endl;
            cin >> s;
            count++;
        }
        cout << "! " << count + 1 << endl;
    }
}