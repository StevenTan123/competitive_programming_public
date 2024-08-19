#include <bits/stdc++.h>
using namespace std;

void solve() {
    string s;
    cin >> s;

    vector<char> run_c;
    vector<int> run_len;
    int prev = 0;
    for (int i = 0; i < s.length(); i++) {
        if (i == s.length() - 1 || s[i] != s[i + 1]) {
            run_c.push_back(s[i]);
            run_len.push_back(i - prev + 1);
            prev = i + 1;
        }
    }

    if (run_c.size() % 2 == 0) {
        cout << "YES\n";
        cout << run_c.size() / 2 << "\n";
        for (int i = 0; i < run_c.size(); i += 2) {
            stringstream ss;
            for (int j = 0; j < run_len[i]; j++) {
                ss << run_c[i];
            }
            for (int j = 0; j < run_len[i + 1]; j++) {
                ss << run_c[i + 1];
            }
            cout << ss.str() << " ";
        }
        cout << "\n";
    } else if (run_c.size() == 1) {
        cout << "NO\n";
    } else {
        int ind = -1;
        for (int i = 2; i < run_c.size(); i++) {
            if (run_c[i] != run_c[i % 2] || run_len[i] != run_len[i % 2]) {
                ind = i;
                if (ind % 2 == 1) {
                    ind++;
                }
                break;
            }
        }

        if (ind != -1) {

            cout << "YES\n";
            vector<string> res;
            stringstream pre;
            for (int i = 0; i <= ind; i++) {
                for (int j = 0; j < run_len[i]; j++) {
                    pre << run_c[i];
                }
            }
            res.push_back(pre.str());

            for (int i = ind + 1; i < run_c.size(); i += 2) {
                stringstream ss;
                for (int j = 0; j < run_len[i]; j++) {
                    ss << run_c[i];
                }
                for (int j = 0; j < run_len[i + 1]; j++) {
                    ss << run_c[i + 1];
                }
                res.push_back(ss.str());
            }

            cout << res.size() << "\n";
            for (string part : res) {
                cout << part << " ";
            }
            cout << "\n";

        } else {

            if (run_len[1] > 1) {

                cout << "YES\n";
                vector<string> res;

                stringstream pre1;
                for (int i = 0; i < run_len[0]; i++) {
                    pre1 << run_c[0];
                }
                pre1 << run_c[1];
                res.push_back(pre1.str());

                stringstream pre2;
                for (int i = 0; i < run_len[1] - 1; i++) {
                    pre2 << run_c[1];
                }
                for (int i = 0; i < run_len[2]; i++) {
                    pre2 << run_c[2];
                }
                res.push_back(pre2.str());

                for (int i = 3; i < run_c.size(); i += 2) {
                    stringstream ss;
                    for (int j = 0; j < run_len[i]; j++) {
                        ss << run_c[i];
                    }
                    for (int j = 0; j < run_len[i + 1]; j++) {
                        ss << run_c[i + 1];
                    }
                    res.push_back(ss.str());
                }

                cout << res.size() << "\n";
                for (string part : res) {
                    cout << part << " ";
                }
                cout << "\n";

            } else {

                if (run_len[0] == 1 || run_c.size() == 3) {
                    cout << "NO\n";
                } else {

                    cout << "YES\n";
                    vector<string> res;

                    stringstream pre1;
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < run_len[i]; j++) {
                            pre1 << run_c[i];
                        }
                    }
                    pre1 << run_c[2];
                    res.push_back(pre1.str());

                    stringstream pre2;
                    for (int i = 0; i < run_len[2] - 1; i++) {
                        pre2 << run_c[2];
                    }
                    for (int i = 3; i < 5; i++) {
                        for (int j = 0; j < run_len[i]; j++) {
                            pre2 << run_c[i];
                        }
                    }
                    res.push_back(pre2.str());

                    for (int i = 5; i < run_c.size(); i += 2) {
                        stringstream ss;
                        for (int j = 0; j < run_len[i]; j++) {
                            ss << run_c[i];
                        }
                        for (int j = 0; j < run_len[i + 1]; j++) {
                            ss << run_c[i + 1];
                        }
                        res.push_back(ss.str());
                    }

                    cout << res.size() << "\n";
                    for (string part : res) {
                        cout << part << " ";
                    }
                    cout << "\n";

                }

            }

        }
    }
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