#include <bits/stdc++.h>
using namespace std;

const int MAXN = 50;

int main() {
    int count = 0;
    for (int i = 0; i <= MAXN; i++) {
        for (int j = 0; j <= MAXN; j++) {
            if (i == 0 && j == 0) {
                continue;
            }
            for (int k = 0; k <= MAXN; k++) {
                for (int l = 0; l <= MAXN; l++) {
                    if ((k == 0 && l == 0) || (k == i && l == j)) {
                        continue;
                    }
                    int a = k - i;
                    int b = l - j;
                    if (a * k + b * l == 0 || i * k + j * l == 0 || a * i + b * j == 0) {
                        count++;
                    }
                }
            } 
        }
    }
    cout << count / 2 << endl;
}