#include <bits/stdc++.h>
using namespace std;

long long count_single(char f, long long l) {
    if (f == 'h') {
        return max((long long) 0, (l - 2) / 2);
    } else if (f == 'a') {
        return max((long long) 0, (l - 3) / 2);
    }
    return 0;
}

struct var {
    bool single;
    char first, last;
    long long pre, suf, mid;
    var() {}
    var(bool s, char f, char l, long long pr, long long su, long long m) : 
        single(s), first(f), last(l), pre(pr), suf(su), mid(m) {}
    var(string val) : first('_'), last('_'), mid(0) {
        single = true;
        for (int i = 0; i < val.length(); i++) {
            if ((val[i] != 'a' && val[i] != 'h') || (i > 0 && val[i] == val[i - 1])) {
                single = false;
                pre = i;
                break;
            }
        }
        if (single) {
            pre = val.length();
            suf = val.length();
        } else {
            for (int i = val.length() - 1; i >= 0; i--) {
                if ((val[i] != 'a' && val[i] != 'h') || (i < val.length() - 1 && val[i] == val[i + 1])) {
                    suf = val.length() - i - 1;
                    break;
                }
            }
        }
        if (pre > 0) {
            first = val[0];
        }
        if (suf > 0) {
            last = val[val.size() - suf];
        }
    }
    long long count() {
        if (single) {
            return count_single(first, pre);    
        } else {
            return count_single(first, pre) + mid + count_single(last, suf);
        }
    }
};

var combine(var &v1, var &v2) {
    char end1 = '_';
    if ((v1.last == 'a' && v1.suf % 2 == 1) || (v1.last == 'h' && v1.suf % 2 == 0)) {
        end1 = 'a';
    } else if ((v1.last == 'a' && v1.suf % 2 == 0) || (v1.last == 'h' && v1.suf % 2 == 1)) {
        end1 = 'h';
    }
    if (v1.single && v2.single) {
        if (end1 == v2.first) {
            return var(false, v1.first, v2.first, v1.pre, v2.suf, 0);
        } else {
            return var(true, v1.first, v1.first, v1.pre + v2.pre, v1.pre + v2.pre, 0);
        }
    } else if (v1.single && !v2.single) {
        if (end1 == v2.first) {
            return var(false, v1.first, v2.last, v1.pre, v2.suf, count_single(v2.first, v2.pre) + v2.mid);
        } else {
            return var(false, v1.first, v2.last, v1.pre + v2.pre, v2.suf, v2.mid);
        }
    } else if (!v1.single && v2.single) {
        if (end1 == '_' || end1 == v2.first) {
            return var(false, v1.first, v2.first, v1.pre, v2.pre, v1.mid + count_single(v1.last, v1.suf));
        } else {
            return var(false, v1.first, v1.last, v1.pre, v1.suf + v2.pre, v1.mid);
        }
    } else {
        if (end1 == '_' || v2.first == '_' || end1 == v2.first) {
            return var(false, v1.first, v2.last, v1.pre, v2.suf, v1.mid + count_single(v1.last, v1.suf) + count_single(v2.first, v2.pre) + v2.mid);
        } else {
            return var(false, v1.first, v2.last, v1.pre, v2.suf, v1.mid + count_single(v1.last, v1.suf + v2.pre) + v2.mid);
        }
    }
}

void solve() {
    int n;
    cin >> n;
    map<string, var> vars;
    var res;
    for (int i = 0; i < n; i++) {
        string name, op;
        cin >> name >> op;
        if (op == ":=") {
            string val;
            cin >> val;
            vars[name] = var(val);
        } else {
            string name1, name2;
            cin >> name1;
            cin >> name2; cin >> name2;
            var v1 = vars[name1];
            var v2 = vars[name2];
            vars[name] = combine(v1, v2);
        }
        if (i == n - 1) {
            res = vars[name];
        }
    }
    cout << res.count() << endl;;
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