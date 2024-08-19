#include <bits/stdc++.h>
using namespace std;
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    vector<vector<bool>> grid(21, vector<bool>(21, false));
    for (int i = 0; i < 21; i++) {
        string line;
        cin >> line;
        for (int j = 0; j < 21; j++) {
            if (line[j] == '1') {
                grid[i][j] = true;
            }
        }
    }
    
    int res = 0;
    for (int i = 0; i < 18; i++) {
        for (int j = 0; j < 18; j++) {
            int sum = 0;
            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < 4; b++) {
                    sum += grid[i + a][j + b];
                }
            } 
            res = max(res, sum);
        }
    }
    cout << res << endl;
}