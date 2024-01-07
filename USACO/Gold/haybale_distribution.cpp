#include <bits/stdc++.h>
using namespace std;

int main() {
    int N;
    cin >> N;
    int x[N];
    for (int i = 0; i < N; i++) {
        cin >> x[i];
    }
    sort(x, x + N);

    vector<int> dists;
    vector<int> counts;
    vector<int> psum;
    int prev = 0;
    for (int i = 1; i <= N; i++) {
        if (i == N || x[i] > x[i - 1]) {
            counts.push_back(i - prev);
            if (prev > 0) {
                dists.push_back(x[prev] - x[prev - 1]);
                psum.push_back(psum[psum.size() - 1] + i - prev);
            } else {
                psum.push_back(i - prev);
            }
            prev = i;
        }
    }
    long long dist_left[counts.size()] = {0};
    int num_points = counts[0];
    long long tot = 0;
    for (int i = 1; i < counts.size(); i++) {
        tot += (long long) dists[i - 1] * num_points;
        num_points += counts[i];
        dist_left[i] = tot;
    }
    long long dist_right[counts.size()] = {0};
    num_points = counts[counts.size() - 1];
    tot = 0;
    for (int i = counts.size() - 2; i >= 0; i--) {
        tot += (long long) dists[i] * num_points;
        num_points += counts[i];
        dist_right[i] = tot;
    }
    
    int Q;
    cin >> Q;
    for (int i = 0; i < Q; i++) {
        int a, b;
        cin >> a >> b;

        int l = 0;
        int r = counts.size() - 1;
        int point = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            int to_left = (m == 0 ? 0 : psum[m - 1]);
            int to_right = N - counts[m] - to_left;
            long long Aa = (long long) to_left * a;
            long long ac = (long long) a * counts[m];
            long long Bb = (long long) to_right * b;
            long long bc = (long long) b * counts[m];
            if (Aa <= Bb + bc && Bb <= Aa + ac) {
                point = m;
                break;
            } else if (Aa > Bb + bc) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        cout << dist_left[point] * a + dist_right[point] * b << endl;
    }
}