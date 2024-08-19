#include <bits/stdc++.h>
using namespace std;

int main() {
    int max_pal = 0;
    for (int i = 0; i <= 999; i++) {
        for (int j = 0; j <= 999; j++) {
            int prod = i * j;
            vector<int> digits;
            while (prod > 0) {
                digits.push_back(prod % 10);
                prod /= 10;
            }
            bool pal = true;
            for (int k = 0; k < digits.size(); k++) {
                if (digits[k] != digits[digits.size() - k - 1]) {
                    pal = false;
                }
            }
            if (pal) {
                max_pal = max(max_pal, i * j);
            }
        }
    }
    cout << max_pal << endl;
}