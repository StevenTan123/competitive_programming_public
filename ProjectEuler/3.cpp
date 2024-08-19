#include <bits/stdc++.h>
using namespace std;

int main() {
    long long num = 600851475143l;
    long long max_pf = 0;
    for (int i = 2; i * i <= num; i++) {
        while (num % i == 0) {
            max_pf = max(max_pf, (long long) i);
            num /= i;
        }
    }
    max_pf = max(max_pf, num);
    cout << max_pf << endl;
}