#include <bits/stdc++.h>
using namespace std;

// returns true if x as a number is less than y
bool comp(string x, string y) {
    if (x.length() < y.length()) {
        return true;
    } else if (x.length() > y.length()) {
        return false;
    } else {
        for (int i = 0; i < x.length(); i++) {
            if (x[i] < y[i]) {
                return true;
            } else if (x[i] > y[i]) {
                return false;
            }
        }
        return false;
    }
}

string minimize(stringstream &ss, int left) {
    while (left > 0) {
        int dig = min(left, 9);
        left -= dig;
        ss << dig;
    }
    string num = ss.str();
    reverse(num.begin(), num.end());
    return num;
}

void solve() {
    int n, k;
    cin >> n >> k;

    string min_int = ".";

    // Case 1: No carries
    for (int i = 0; i <= 9 - k; i++) {
        int dig_sum = 0;
        for (int j = i; j <= i + k; j++) {
            dig_sum += j;
        }
        int left = n - dig_sum;
        if (left >= 0 && left % (k + 1) == 0) {
            left /= k + 1;
            stringstream ss;
            ss << i;
            string num = minimize(ss, left);
            if (min_int[0] == '.' || comp(num, min_int)) {
                min_int = num;
            }
        }
    }

    // Case 2: Carries, brute forcing number of 9's
    for (int nines = 0; nines <= 20; nines++) {
        for (int i = 9 - k + 1; i <= 9; i++) {
            int end_sum = nines * 9;
            for (int j = i; j <= i + k; j++) {
                end_sum += j % 10;
            }

            int left = n - end_sum;
            int num_below = 9 - i + 1;
            // Let y = sum of rest of digits. Need y * num_below + (y + 1) * (k + 1 - num_below) = left
            int RHS = num_below + left - (k + 1);
            if (RHS >= 0 && RHS % (k + 1) == 0) {
                left = RHS / (k + 1);
                stringstream ss;
                ss << i;
                for (int j = 0; j < nines; j++) {
                    ss << 9;
                }
                string num;
                if (left > 8) {
                    ss << 8;
                    left -= 8;
                    num = minimize(ss, left);
                } else {
                    if (left > 0) {
                        ss << left;
                    }
                    num = ss.str();
                    reverse(num.begin(), num.end());
                }
                if (min_int[0] == '.' || comp(num, min_int)) {
                    min_int = num;
                }
            }
        }
    }

    if (min_int[0] == '.') {
        cout << -1 << endl;
    } else {
        cout << min_int << endl;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}