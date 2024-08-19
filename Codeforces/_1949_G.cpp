#include <bits/stdc++.h>
using namespace std;

int n;
string s, t;

void add_steps(vector<int> &steps, vector<string> &instructions) {
    for (int i = 0; i < steps.size(); i++) {
        instructions.push_back("DRIVE " + to_string(steps[i] + 1));
        if (t[steps[i]] != '-') {
            instructions.push_back("DROPOFF");
        }
        if (i + 1 < steps.size()) {
            instructions.push_back("PICKUP");
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    cin >> t >> s;
    vector<bool> visited(n, 0);

    vector<string> res;
    while (1) {
        vector<int> steps;
        bool found = false;
        char prev = ' ';
        for (int i = 0; i < n; i++) {
            if (!visited[i] && s[i] != '-' && t[i] == '-') {
                found = true;
                visited[i] = 1;
                steps.push_back(i);
                prev = s[i];
                break;
            }
        }
        if (!found) {
            break;
        }
        while (1) {
            found = false;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && prev == t[i] && s[i] != '-' && s[i] != t[i]) {
                    found = true;
                    visited[i] = 1;
                    steps.push_back(i);
                    prev = s[i];
                    break;
                }
            }
            if (!found) {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i] && prev == t[i] && s[i] != t[i]) {
                visited[i] = 1;
                steps.push_back(i);
                break;
            }
        }
        add_steps(steps, res);
    }
    cout << res.size() << "\n";
    for (string &step : res) {
        cout << step << "\n";
    }
}