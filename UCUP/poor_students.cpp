#include <bits/stdc++.h>
using namespace std;

struct Student {
    int cost, id;
    Student() : cost(-1), id(-1) {}
    Student(int c, int i) : cost(c), id(i) {}
    bool operator<(const Student &o) const {
        if (cost == o.cost) {
            return id < o.id;
        }
        return cost < o.cost;
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, k;
    cin >> n >> k;
    vector<vector<int>> c(n, vector<int>(k));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < k; j++) {
            cin >> c[i][j];
        }
    }
    vector<int> a(k);
    for (int i = 0; i < k; i++) {
        cin >> a[i];
    }

    // match[i] = exam student i is matched to, -1 if not matched yet
    vector<int> match(n, -1);
    // entry_sets[i] stores the set of unmatched students that could be matched to exam i,
    // sorted by their match costs.
    vector<set<Student>> entry_sets(k);
    // pair_sets[i][j] stores the set of students currently matched to exam i that can be
    // transferred to match exam j, sorted by their transfer costs.
    vector<vector<set<Student>>> pair_sets(k, vector<set<Student>>(k));

    // Initialize entry sets
    for (int i = 0; i < k; i++) {
        for (int j = 0; j < n; j++) {
            entry_sets[i].insert(Student(c[j][i], j));
        }
    }

    // Max flow min cost
    long long cost = 0;
    while (true) {
        // 0th node is a dummy node with edge weights to every exam that can be taken
        // by unmatched student. Edges in this graph represent a student that can be
        // transferred between the exams.
        vector<vector<Student>> graph(k + 1, vector<Student>(k + 1));
        for (int i = 0; i < k; i++) {
            graph[0][i + 1] = *entry_sets[i].begin();
            for (int j = 0; j < k; j++) {
                if (i != j) {
                    graph[i + 1][j + 1] = *pair_sets[i][j].begin();
                }
            }
        }

        // Bellman-Ford to find shortest path in this graph. This path corresponds to
        // an augmenting path in the flow network.
        // dp[i][j] = {min distance, previous node} of a path from node 0 to node i
        // of length <= j.
        vector<vector<pair<long long, int>>> dp(k + 1, vector<pair<long long, int>>(k + 1, {LONG_LONG_MAX, -1}));
        dp[0][0] = {0, -1};
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= k; j++) {
                dp[j][i].first = dp[j][i - 1].first;
                dp[j][i].second = j;
            }
            for (int j = 0; j <= k; j++) {
                for (int x = 0; x <= k; x++) {
                    // j -> x
                    long long cur_dist = dp[j][i - 1].first + graph[j][x].cost;
                    if (cur_dist < dp[x][i].first) {
                        dp[x][i].first = cur_dist;
                        dp[x][i].second = j;
                    }
                }
            }
        }

        int cur = -1;
        for (int i = 1; i <= k; i++) {
            if (a[i - 1] > 0 && (cur == -1 || dp[i - 1][k].first < dp[cur][k].first)) {
                cur = i;
            }
        }
        if (cur == -1) {
            break;
        }
        a[cur - 1]--;
        
        vector<Student> path;
        vector<int> exams;
        for (int i = k; i >= 0; i--) {
            int prev = dp[cur][i].second;
            if (prev == -1) {
                break;
            }
            if (prev != cur) {
                path.push_back(graph[prev][cur]);
                exams.push_back(cur - 1);
            }
            cur = prev;
        }

        reverse(path.begin(), path.end());
        reverse(exams.begin(), exams.end());
        for (int i = 0; i < path.size(); i++) {
            // Student will be unmatched from its current exam,
            // add to the available set of that exam.
            entry_sets[match[path[i].id]].insert(Student(c[path[i].id][match[path[i].id]], path[i].id));
            
            // Since student was just matched to a new exam, remove student
            // from the available set of that exam.
            entry_sets[exams[i]].erase(Student(c[path[i].id][exams[i]], path[i].id));

            match[path[i].id] = exams[i];
        }
    }
    cout << cost << "\n";   
}