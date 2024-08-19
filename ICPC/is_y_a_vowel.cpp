#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    string s;
    cin >> s;
    int count_vowel = 0;
    int count_y = 0;
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u') {
            count_vowel++;
        }
        if (s[i] == 'y') {
            count_y++;
        }
    }
    cout << count_vowel << " " << (count_vowel + count_y) << endl;
}