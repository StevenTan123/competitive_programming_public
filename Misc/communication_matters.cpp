#include <bits/stdc++.h>
using namespace std;

double exp_guesses(vector<double> p) {
    double res = 0;
    priority_queue<double> pq;
    for (double val : p) {
        pq.push(-val);
    }
    while (pq.size() > 1) {
        double one = -pq.top();
        pq.pop();
        double two = -pq.top();
        pq.pop();
        res += one + two;
        pq.push(-(one + two));
    }
    return res;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    double p[n][n];
    vector<double> p1(n, 0);
    vector<double> p2(n, 0);
    vector<double> pj;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> p[i][j];
            pj.push_back(p[i][j]);
            p1[i] += p[i][j];
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            p2[i] += p[j][i];
        }
    }

    double x = exp_guesses(p1);
    double y = exp_guesses(p2);
    double z = exp_guesses(pj);
    cout << x + y - z << "\n";
}