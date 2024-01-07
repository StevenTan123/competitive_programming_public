#include <bits/stdc++.h>
using namespace std;

struct BIT {
    int *bit;
    int len;
    BIT(int n) {
        bit = new int[n + 1];
        len = n + 1;
        for (int i = 0; i <= n; i++) {
            bit[i] = 0;
        }
    }
    ~BIT() {
        delete[] bit;
    }
    void update(int index, int add) {
        index++;
        while (index < len) {
            bit[index] += add;
            index += index & -index;
        }
    }
    int sum(int l, int r) {
        return psum(r) - (l == 0 ? 0 : psum(l - 1));
    }
    int psum(int index) {
        index++;
        int res = 0;
        while (index > 0) {
            res += bit[index];      
            index -= index & -index;
        }
        return res;
    }
};
