#include <bits/stdc++.h>
using namespace std;

void calc_max_problems(int a[], int n, long long time, int max_probs[])
{
    priority_queue<int> pq;
    long long sum = 0;
    for (int i = 0; i < n; i++)
    {
        sum += a[i];
        pq.push(a[i]);
        if (sum > time)
        {
            sum -= pq.top();
            pq.pop();
        }
        max_probs[i] = pq.size();
    }
}

bool ok(int a[], int rev[], int n, int k, long long time)
{
    int forward[n];
    int backward[n];
    calc_max_problems(a, n, time, forward);
    calc_max_problems(rev, n, time, backward);

    bool possible = false;
    for (int i = 0; i < n - 1; i++)
    {
        if (forward[i] + backward[n - i - 2] >= k)
        {
            possible = true;
            break;
        }
    }
    if (max(forward[n - 1], backward[n - 1]) >= k)
    {
        possible = true;
    }
    return possible;
}

void solve()
{
    int n, k;
    long long sum;
    cin >> n;
    cin >> k;
    int a[n], rev[n];
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
        rev[n - i - 1] = a[i];
        sum += a[i];
    }

    long long l = 1;
    long long r = sum;
    long long ans = sum;
    while (l <= r)
    {
        long long m = (l + r) / 2;
        if (ok(a, rev, n, k, m))
        {
            ans = m;
            r = m - 1;
        }
        else
        {
            l = m + 1;
        }
    }
    cout << ans << "\n";
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