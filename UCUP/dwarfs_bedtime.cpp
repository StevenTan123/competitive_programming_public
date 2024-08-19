#include <bits/stdc++.h>
using namespace std;

int n;
vector<int> sleep_times;

string convert(int time) {
    string hour = to_string(time / 60);
    string min = to_string(time % 60);
    if (hour.length() == 1) {
        hour = "0" + hour;
    }
    if (min.length() == 1) {
        min = "0" + min;
    }
    return hour + ":" + min;
}

bool ask(int dwarf, int time) {
    cout << "at " << convert(time) << " check " << dwarf + 1 << endl;
    string response;
    cin >> response;
    return response == "awake";
    /*
    if (sleep_times[dwarf] < 720) {
        return time < sleep_times[dwarf] || time >= sleep_times[dwarf] + 720;
    } else {
        return time >= sleep_times[dwarf] - 720 && time < sleep_times[dwarf];
    }
    */
}

struct State {
    bool found, want;
    int minute, window, id;
    State(bool f, bool w, int m, int wind, int i) : found(f), want(w), minute(m), window(wind), id(i) {}
    bool operator<(const State &o) const {
        return minute > o.minute;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n;
    /*sleep_times.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> sleep_times[i];
    }*/

    priority_queue<State> pq;
    for (int i = 0; i < n; i++) {
        bool awake = ask(i, 0);
        pq.emplace(false, !awake, 45, 44, i);
    }

    vector<int> res(n, -1);
    while (!pq.empty()) {
        State cur = pq.top();
        pq.pop();

        if (cur.minute >= 1440) {
            if (cur.want) {
                res[cur.id] = 720;
            } else {
                res[cur.id] = 0;
            }
            continue;
        }
        if (cur.found) {
            bool awake = ask(cur.id, cur.minute);
            if (awake == cur.want) {
                res[cur.id] = cur.minute;
                if (cur.want) {
                    res[cur.id] = (cur.minute + 720) % 1440;
                }
            } else {
                cur.minute++;
                pq.push(cur);
            }
        } else {
            bool awake = ask(cur.id, cur.minute);
            if (awake == cur.want) {
                cur.found = true;
                cur.minute += 720 - cur.window;
                cur.want = !cur.want;
                pq.push(cur);
            } else {
                cur.minute += cur.window;
                cur.window--;
                pq.push(cur);
            }
        }
    }

    cout << "answer" << endl;
    for (int i = 0; i < n; i++) {
        cout << convert(res[i]) << endl;
    }
}