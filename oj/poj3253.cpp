#include <iostream>
#include <algorithm>

using namespace std;
const int MAX_N = 20001;
int N;
int L[MAX_N];
int SUM_L;

void solve()
{
    long long costs = 0;
    while (N > 1)
    {
        int mini1 = 0;
        int mini2 = 1;
        if (L[mini1] > L[mini2])
        {
            swap(mini1, mini2);
        }
        for (int i = 2; i < N; i++)
        {
            if (L[i] < L[mini1])
            {
                mini2 = mini1;
                mini1 = i;
            }
            else if (L[i] < L[mini2])
            {
                mini2 = i;
            }
        }
        int t = L[mini1] + L[mini2];
        costs += t;

        if (mini1 == N - 1)
            swap(mini1, mini2);
        L[mini1] = t;
        L[mini2] = L[N-1];
        N--;
    }
    printf("%lld", costs);
}

int main()
{
#ifndef ONLINE_JUDGE
    freopen("poj3253.in", "r", stdin);
    freopen("stdout.txt", "w", stdout);
#endif

    while (scanf("%d", &N) != EOF)
    {
        SUM_L = 0;
        for (int i = 0; i < N; i++)
        {
            scanf("%d", L + i);
        }
        solve();
    }

#ifndef ONLINE_JUDGE
    fclose(stdin);
    fclose(stdout);
#endif
    return 0;
}