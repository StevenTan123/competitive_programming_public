#include <bits/stdc++.h>
using namespace std;

int count_arr[635][200005] = {0};
void solve()
{
    int n;
    cin >> n;
    int a[n] = {0};
    int b[n] = {0};
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }
    for (int i = 0; i < n; i++)
    {
        cin >> b[i];
    }

    int fl_sq = 1;
    while (fl_sq * fl_sq <= 2 * n)
    {
        fl_sq++;
    }
    fl_sq--;

    // count_arr[x][y] counts the number of indices i with a_i = x and b_i = y, only x up to sqrt(n)
    for (int i = 0; i < n; i++)
    {
        if (a[i] <= fl_sq)
        {
            count_arr[a[i]][b[i]]++;
        }
    }

    long long count1 = 0;
    long long count2 = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 1; j <= fl_sq; j++)
        {
            int prod = a[i] * j;
            if (prod > 2 * n)
            {
                break;
            }
            if (prod - b[i] > 0 && prod - b[i] <= n)
            {
                if (j == a[i] && prod == b[i] * 2)
                {
                    count1 += max(count_arr[j][prod - b[i]] - 1, 0);
                }
                else if (a[i] <= fl_sq)
                {
                    count1 += count_arr[j][prod - b[i]];
                }
                else
                {
                    count2 += count_arr[j][prod - b[i]];
                }
            }
        }
    }
    long long res = count1 / 2 + count2;
    cout << res << "\n";

    for (int i = 0; i < n; i++)
    {
        if (a[i] <= fl_sq)
        {
            count_arr[a[i]][b[i]]--;
        }
    }
}

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        solve();
    }
}