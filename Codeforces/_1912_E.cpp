#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    long long p, q;
    cin >> p >> q;

    // Strategy: Make p and q both even. First, if they differ in parity, add 12 
    // (since 12 and 21 have an odd difference). Then, if they are both odd, add 1.
    stringstream ss;
    if ((p - q) % 2 != 0) {
        p -= 12;
        q -= 21;
        ss << "12+";
    }
    if (p % 2 != 0) {
        p--;
        q--;
        ss << "1+";
    }
    while (p % 10 == 0 || q % 10 == 0) {
        ss << "2+";
        p -= 2;
        q -= 2;
    }

    // Now, for p, if it's positive we can write (p/2)+(p/2)-0, and otherwise 0-(-p/2)-(-p/2).
    // We do a similar thing for q.
    if (p > 0) {
        long long half = p / 2;
        ss << half << '+' << half << "-0+";
    } else if (p < 0) {
        long long half = -p / 2;
        ss << "0-" << half << '-' << half << '+';
    }
    if (q > 0) {
        long long half = q / 2;
        stringstream rev;
        while (half > 0) {
            rev << (half % 10);
            half /= 10;
        }
        string half_str = rev.str();
        ss << "0-" << half_str << '+' << half_str << '+';
    } else if (q < 0) {
        long long half = -q / 2;
        stringstream rev;
        while (half > 0) {
            rev << (half % 10);
            half /= 10;
        }
        string half_str = rev.str();
        ss << half_str << '-' << half_str << "-0+";
    }
    ss << '0'; 
    cout << ss.str() << endl;
}