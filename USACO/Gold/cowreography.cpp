#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, K;
    cin >> N >> K;
    string s, t;
    cin >> s >> t;
    vector<int> one_s;
    vector<int> one_t;
    for (int i = 0; i < N; i++) {
        if (s[i] == '1') {
            one_s.push_back(i);
        }
        if (t[i] == '1') {
            one_t.push_back(i);
        }
    }

    deque<pair<int, int>> s_dq;
    deque<pair<int, int>> t_dq;
    int s_ind = 0;
    int t_ind = 0;
    long long res = 0;
    for (int i = 0; i < N; i++) {
        while (!s_dq.empty()) {
            auto [ind, cnt] = s_dq.front();
            if (ind < i - K) {
                s_dq.pop_front();
                if (!s_dq.empty() && s_dq.back().first == ind + K) {
                    s_dq.back().second += cnt;
                } else {
                    s_dq.emplace_back(ind + K, cnt);
                }
                res += cnt;
            } else {
                break;
            }
        }
        while (!t_dq.empty()) {
            auto [ind, cnt] = t_dq.front();
            if (ind < i - K) {
                t_dq.pop_front();
                if (!t_dq.empty() && t_dq.back().first == ind + K) {
                    t_dq.back().second += cnt;
                } else {
                    t_dq.emplace_back(ind + K, cnt);
                }
                res += cnt;
            } else {
                break;
            }
        }
        if (s_ind < one_s.size() && t_ind < one_t.size() && one_s[s_ind] == i && one_t[t_ind] == i) {
            s_ind++;
            t_ind++;
            continue;
        }
        if (s_ind < one_s.size() && one_s[s_ind] == i) {
            if (t_dq.empty()) {
                s_dq.emplace_back(i, 1);
            } else {
                res++;
                if (t_dq.front().second == 1) {
                    t_dq.pop_front();
                } else {
                    t_dq.front().second--;
                }
            }
            s_ind++;
        }
        if (t_ind < one_t.size() && one_t[t_ind] == i) {
            if (s_dq.empty()) {
                t_dq.emplace_back(i, 1);
            } else {
                res++;
                if (s_dq.front().second == 1) {
                    s_dq.pop_front();
                } else {
                    s_dq.front().second--;
                }
            }
            t_ind++;
        }
    }
    cout << res << "\n";
}