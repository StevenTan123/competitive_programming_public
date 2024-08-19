#include <bits/stdc++.h>
using namespace std;

int main() {
    long long sum = 0;
    int one = 1;
    int two = 1;
    while (two <= 4000000) {
        if (two % 2 == 0) {
            sum += two;
        }
        int temp = two;
        two += one;
        one = temp;
    }
    cout << sum << endl;
}