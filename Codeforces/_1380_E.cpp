#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;
    set<int>* towers[m];
    for (int i = 0; i < m; i++) {
        towers[i] = new set<int>;
    }
    for (int i = 1; i <= n; i++) {
        int tower;
        cin >> tower;
        towers[tower - 1]->insert(i);
    }

    int gaps[m] = {0};
    int total = 0;
    for (int i = 0; i < m; i++) {
        int prev = -1;
        for (int disc : *towers[i]) {
            if (prev != -1 && disc > prev + 1) {
                gaps[i]++;
            }
            prev = disc;
        }
        gaps[i]++;
        total += gaps[i];
    }

    cout << total - 1 << "\n";
    for (int i = 0; i < m - 1; i++) {
        int a, b;
        cin >> a >> b;
        a--; b--;

        bool swapped = false;
        if (towers[a]->size() > towers[b]->size()) {
            swap(a, b);
            swapped = true;
        }

        int orig = gaps[b];
        for (int disc : *towers[a]) {
            gaps[b]++;
            auto above = towers[b]->lower_bound(disc);
            if (above != towers[b]->end()) {
                if (disc + 1 == *above) {
                    gaps[b]--;
                }
            }
            if (above != towers[b]->begin()) {
                above--;
                if (*above + 1 == disc) {
                    gaps[b]--;
                }
            }
            towers[b]->insert(disc);
        }
        
        total += gaps[b] - orig - gaps[a];
        delete towers[a];
        towers[a] = nullptr;
        gaps[a] = 0;
        if (!swapped) {
            swap(gaps[a], gaps[b]);
            swap(towers[a], towers[b]);
        }
        cout << total - 1 << "\n";
    }

    for (int i = 0; i < m; i++) {
        delete towers[i];
    }
}