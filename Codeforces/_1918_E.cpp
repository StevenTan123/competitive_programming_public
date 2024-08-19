#include <bits/stdc++.h>
using namespace std;

void quicksort(int order[], int l, int r) {
    if (l >= r) {
        return;
    }
    int pivot = rand() % (r - l + 1) + l;
    cout << "? " << order[pivot] + 1 << endl;
    string in;
    cin >> in;
    while (in != "=") {
        cout << "? " << order[pivot] + 1 << endl;
        cin >> in;
    }

    swap(order[pivot], order[r]);
    int ind = l - 1;
    for (int i = l; i < r; i++) {
        cout << "? " << order[i] + 1 << endl;
        cin >> in;
        if (in == "<") {
            ind++;
            swap(order[i], order[ind]);
        }
        cout << "? " << order[r] + 1 << endl;
        cin >> in;
    }
    swap(order[r], order[ind + 1]);
    
    quicksort(order, l, ind);
    quicksort(order, ind + 2, r);
}

void solve() {
    int n;
    cin >> n;

    int order[n];
    for (int i = 0; i < n; i++) {
        order[i] = i;
    }
    quicksort(order, 0, n - 1);

    int res[n];
    for (int i = 0; i < n; i++) {
        res[order[i]] = i + 1;
    }
    cout << "! ";
    for (int i = 0; i < n; i++) {
        cout << res[i] << " ";
    }
    cout << endl;
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