#include <bits/stdc++.h>

using namespace std;

struct Vertex
{
    int left, right;
    int sum = 0;
    Vertex *left_child = nullptr, *right_child = nullptr;

    Vertex(int lb, int rb)
    {
        left = lb;
        right = rb;
    }

    void extend()
    {
        if (!left_child && left + 1 < right)
        {
            int t = (left + right) / 2;
            left_child = new Vertex(left, t);
            right_child = new Vertex(t, right);
        }
    }

    void add(int k, int x)
    {
        extend();
        sum += x;
        if (left_child)
        {
            if (k < left_child->right)
                left_child->add(k, x);
            else
                right_child->add(k, x);
        }
    }

    int get_sum(int lq, int rq)
    {
        if (lq <= left && right <= rq)
            return sum;
        if (max(left, lq) >= min(right, rq))
            return 0;
        extend();
        return left_child->get_sum(lq, rq) + right_child->get_sum(lq, rq);
    }
};

bool overlap(pair<int, int> a, pair<int, int> b)
{
    return !(a.first > b.second || b.first > a.second);
}

int main()
{
    int n, q;
    cin >> n >> q;

    set<pair<int, int>> ranges_arr[q + 1];
    Vertex st = Vertex(0, 1000000005);

    for (int i = 0; i < q; i++)
    {
        char c;
        cin >> c;

        // Query command
        if (c == '?')
        {

            int k;
            cin >> k;
            cout << st.get_sum(1, k + 1) << endl;
            fflush(stdout);
        }
        else
        {

            int l, r, x;
            cin >> l >> r >> x;
            pair<int, int> cur = {
                l,
                r};
            set<pair<int, int>> &ranges = ranges_arr[x];
            set<pair<int, int>>::iterator it = ranges.lower_bound(cur);
            if (it == ranges.end() && ranges.size() > 0)
            {
                it = --ranges.end();
            }
            else if (it != ranges.end() && it != ranges.begin())
            {
                it--;
            }

            set<pair<int, int>>::iterator first = ranges.end();
            set<pair<int, int>>::iterator last = ranges.end();
            set<pair<int, int>>::iterator overlap_first = ranges.end();
            set<pair<int, int>>::iterator overlap_last = ranges.end();
            bool covered = false;
            while (it != ranges.end())
            {
                if (overlap(*it, cur))
                {

                    st.add(it->first, -1);
                    st.add(it->second + 1, 1);

                    if (it->first < l)
                    {
                        overlap_first = it;
                    }
                    else if (it->second <= r)
                    {
                        if (first == ranges.end())
                        {
                            first = it;
                        }
                        last = it;
                    }
                    if (it->second > r)
                    {
                        if (it->first < l)
                        {
                            covered = true;
                        }
                        overlap_last = it;
                        break;
                    }
                }
                else if (it->first > r)
                {
                    break;
                }
                it++;
            }

            // Insert command
            if (c == '+')
            {

                if (first != ranges.end())
                {
                    ranges.erase(first, ++last);
                }

                int newl = l;
                int newr = r;

                int l2 = overlap_last->first;
                int r2 = overlap_last->second;
                if (overlap_first != ranges.end())
                {
                    ranges.erase(overlap_first);
                    newl = overlap_first->first;
                }
                if (covered || overlap_last != ranges.end())
                {
                    if (!covered)
                    {
                        ranges.erase(overlap_last);
                    }
                    newr = r2;
                }
                ranges.insert({newl,
                               newr});
                st.add(newl, 1);
                st.add(newr + 1, -1);

                // Remove command
            }
            else
            {

                if (first != ranges.end())
                {
                    ranges.erase(first, ++last);
                }

                int l2 = overlap_last->first;
                int r2 = overlap_last->second;
                if (overlap_first != ranges.end())
                {
                    pair<int, int> newl = {
                        overlap_first->first,
                        l - 1};
                    ranges.erase(overlap_first);
                    ranges.insert(newl);
                    st.add(overlap_first->first, 1);
                    st.add(l, -1);
                }
                if (covered || overlap_last != ranges.end())
                {
                    pair<int, int> newr = {
                        r + 1,
                        r2};
                    if (!covered)
                    {
                        ranges.erase(overlap_last);
                    }
                    ranges.insert(newr);
                    st.add(r + 1, 1);
                    st.add(r2 + 1, -1);
                }
            }
        }
    }
    return 0;
}