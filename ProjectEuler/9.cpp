#include <bits/stdc++.h>
using namespace std;

int main() {
    for (int i = 1; i < 1000; i++) {
        for (int j = 1; j < 1000; j++) {
            int sum_sq = i * i + j * j;
            if (floor(sqrt(sum_sq)) == ceil(sqrt(sum_sq))) {
                int val = floor(sqrt(sum_sq));
                if (i + j + val == 1000) {
                    cout << i * j * val << endl;
                }
            }
        }
    }
    cout << "End Program" << endl;
}