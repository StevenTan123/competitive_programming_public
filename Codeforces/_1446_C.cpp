#include <bits/stdc++.h>
using namespace std;

const int MAXB = 32;

int min_remove(vector<stack<int>*> &bits) {
    if (bits.size() <= 2) {
        return 0;
    }
    int max_bit = 0;
    for (int i = 0; i < bits.size(); i++) {
        if (!bits[i]->empty()) {
            max_bit = max(max_bit, bits[i]->top());
        }
    }

    vector<stack<int>*> one;
    vector<stack<int>*> two;
    for (int i = 0; i < bits.size(); i++) {
        if (!bits[i]->empty() && bits[i]->top() == max_bit) {
            one.push_back(bits[i]);
        } else {
            two.push_back(bits[i]);
        }
    }

    for (stack<int> *stk : one) {
        stk->pop();
    }
    int min_rem_one = max(0, (int) two.size() - 1) + min_remove(one);
    int min_rem_two = max(0, (int) one.size() - 1) + min_remove(two);
    return min(min_rem_one, min_rem_two);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    vector<stack<int>*> bits(n);
    for (int i = 0; i < n; i++) {
        int a;
        cin >> a;
        bits[i] = new stack<int>();
        for (int j = 0; j < MAXB; j++) {
            if (a & (1 << j)) {
                bits[i]->push(j);
            }
        }
    }

    cout << min_remove(bits) << endl;

    for (stack<int> *stk : bits) {
        delete stk;
    }
}