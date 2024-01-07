#include <bits/stdc++.h>
using namespace std;

const int MAXN = 100005;

vector<int> dist_uninf(MAXN, MAXN);
int depths[MAXN];
int T;
bool all_infected;

struct CentroidDecomp {
    int n;
    int *dists, *sizes;
    bool *visited;
    vector<pair<int, int>> *pars, *subtree;
    vector<vector<int>> tree;
    CentroidDecomp(int _n, vector<vector<int>> &_tree) : n(_n), tree(_tree) {
        dists = new int[n];
        sizes = new int[n];
        visited = new bool[n];
        pars = new vector<pair<int, int>>[n];
        subtree = new vector<pair<int, int>>[n];
        construct();
    }
    ~CentroidDecomp() {
        delete[] dists; delete[] sizes; delete[] visited; delete[] pars; delete[] subtree;
    }
    void construct() {
        for (int i = 0; i < n; i++) {
            visited[i] = 0;
            pars[i].clear();
            subtree[i].clear();
        }
        build(0);
        for (int i = 0; i < n; i++) {
            sort(subtree[i].begin(), subtree[i].end());
            for (int j = 1; j < (int) subtree[i].size(); j++) {
                if (depths[subtree[i][j - 1].second] < depths[subtree[i][j].second]) {
                    subtree[i][j].second = subtree[i][j - 1].second;
                }
            }
        }
    }
    void dfs_sizes(int cur, int prev) {
        sizes[cur] = 1;
        for (int nei : tree[cur]) {
            if (nei != prev && !visited[nei]) {
                dfs_sizes(nei, cur);
                sizes[cur] += sizes[nei];
            }
        }
    }
    void dfs(int start, int cur, int prev) {
        dists[cur] = (prev == -1 ? 0 : dists[prev] + 1);
        pars[cur].push_back({start, dists[cur]});
        if (all_infected || dist_uninf[cur] > T) {
            subtree[start].push_back({dists[cur], cur});
        }
        for (int nei : tree[cur]) {
            if (nei != prev && !visited[nei]) {
                dfs(start, nei, cur);
            }
        }
    }
    int find_centroid(int cur, int prev, int size) {
        for (int nei : tree[cur]) {
            if (nei != prev && !visited[nei]) {
                if (sizes[nei] * 2 > size) {
                    return find_centroid(nei, cur, size);
                }
            }
        }
        return cur;
    }
    void build(int cur) {
        dfs_sizes(cur, -1);
        int centroid = find_centroid(cur, -1, sizes[cur]);
        dfs(centroid, centroid, -1);
        visited[centroid] = 1;
        for (int nei : tree[centroid]) {
            if (!visited[nei]) {
                build(nei);
            }
        }
    }
};

void dfs_depths(vector<vector<int>> &tree, int cur, int prev) {
    if (prev == -1) depths[cur] = 0;
    else depths[cur] = depths[prev] + 1;
    for (int nei : tree[cur]) {
        if (nei != prev) dfs_depths(tree, nei, cur);
    }
}

// Returns node in subtree with distance <= dist
int bsearch(vector<pair<int, int>> &subtree, int dist) {
    int l = 0;
    int r = subtree.size() - 1;
    int res = -1;
    while (l <= r) {
        int m = (l + r) / 2;
        if (subtree[m].first <= dist) {
            res = subtree[m].second;
            l = m + 1;
        } else {
            r = m - 1;
        }
    }
    return res;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    string istr;
    cin >> N >> istr;
    bool infected[N];
    all_infected = true;
    queue<pair<int, int>> BFS;
    for (int i = 0; i < N; i++) {
        infected[i] = istr[i] - '0';
        all_infected = all_infected && infected[i];
        if (!infected[i]) {
            BFS.push({i, 0});
        }
    }
    vector<vector<int>> tree(N);
    for (int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        tree[--u].push_back(--v);
        tree[v].push_back(u);
    }
    
    dfs_depths(tree, 0, -1);
    vector<pair<int, int>> sorted;
    for (int i = 0; i < N; i++) {
        sorted.push_back({depths[i], i});
    }
    sort(sorted.begin(), sorted.end());
    reverse(sorted.begin(), sorted.end());
    
    while (!BFS.empty()) {
        pair<int, int> cur = BFS.front();
        BFS.pop();
        if (dist_uninf[cur.first] != MAXN) {
            continue;
        }
        dist_uninf[cur.first] = cur.second;
        for (int nei : tree[cur.first]) {
            BFS.push({nei, cur.second + 1});
        }
    }

    CentroidDecomp cd(N, tree);

    int Q;
    cin >> Q;
    while (Q-- > 0) {
        cin >> T;
        for (int i = 0; i < N; i++) {
            if (infected[i] && dist_uninf[i] > T) {
                BFS.push({i, 0});
            }
        }
        
        bool visited[N] = {0};
        while(!BFS.empty()) {
            pair<int, int> cur = BFS.front();
            BFS.pop();
            if (visited[cur.first] || cur.second > T) {
                continue;
            }
            visited[cur.first] = 1;
            for (int nei : tree[cur.first]) {
                BFS.push({nei, cur.second + 1});
            }
        }
        bool possible = true;
        for (int i = 0; i < N; i++) {
            if (infected[i] && !visited[i]) {
                possible = false;
                break;
            }
        }

        if (possible) {
            if (!all_infected) {
                cd.construct();
            }

            // Stores the closest starting node in the centroid subtree of each node
            vector<int> closest(N, MAXN);

            int count = 0;
            // Loop through nodes from highest depth to lowest depth
            for (pair<int, int> p : sorted) {
                int cur = p.second;
                if (!infected[cur]) continue;
                int closest_start = MAXN;
                for (pair<int, int> centroid : cd.pars[cur]) {
                    closest_start = min(closest_start, centroid.second + closest[centroid.first]);
                }
                
                // Found the highest depth infected node that is not covered
                if (closest_start > T) {
                    // Find the lowest depth starting node with distance <= T from the current
                    int min_depth = -1;
                    for (pair<int, int> centroid : cd.pars[cur]) {
                        int dist = T - centroid.second;
                        int use = bsearch(cd.subtree[centroid.first], dist);
                        if (use != -1) {
                            if (min_depth == -1 || depths[use] < depths[min_depth]) {
                                min_depth = use;
                            }
                        }
                    }

                    for (pair<int, int> centroid : cd.pars[min_depth]) {
                        closest[centroid.first] = min(closest[centroid.first], centroid.second);
                    }
                    count++;
                }
            }
            cout << count << "\n"; 
        } else {
            cout << "-1\n";
        }
    }
}