#include <iostream>
#include <algorithm>
#include <queue>
#include <stdexcept>

using namespace std;

const int MAX_N = 10000;

int L, P, N;
int A[MAX_N + 1], B[MAX_N + 1];
template <class T>
class my_queue
{
  private:
    T heap[MAX_N + 1];
    unsigned int size = 0;

  public:
    // push last
    // adjust
    void push(const T &x)
    {
        if (size >= MAX_N + 1)
        {
            throw out_of_range("");
        }
        int i = size++;
        while (i > 0)
        {
            int p = (i - 1) / 2;
            if (heap[p] <= x)
                break;
            heap[i] = heap[p];
            i = p;
        }
        heap[i] = x;
    }
    // remove head[0]
    // pull head[--size]
    // adjust
    void pop()
    {
        if (size == 0)
        {
            throw out_of_range("");
        }
        int x = heap[--size];
        int i = 0;
        while (i * 2 + 1 < size)
        {
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            if (right < size && heap[left] > heap[right])
            {
                left = right;
            }
            if (heap[left] >= x)
            {
                break;
            }
            heap[i] = heap[left];
            i = left;
        }
        heap[i] = x;
    }
    T top()
    {
        if (size == 0)
        {
            throw out_of_range("");
        }
        return heap[0];
    }
    unsigned int length()
    {
        return size;
    }
    bool empty()
    {
        return size == 0;
    }
};

void solve()
{
    int ans = 0;
    int tank = P;
    int pos = 0;
    A[N] = L;
    B[N] = 0;
    N++;

    my_queue<int> que;
    for (int i = 0; i < N; i++)
    {
        int d = L - A[i] - pos;
        while (tank - d < 0)
        {
            if (que.empty())
            {
                printf("-1");
                return;
            }
            tank += que.top();
            que.pop();
            ans++;
        }
        tank -= d;
        que.push(B[i]);
        pos = L - A[i];
    }
    printf("%d\n", ans);
}

int main()
{
#ifndef ONLINE_JUDGE
    freopen("poj2431.in", "r", stdin);
    freopen("stdout.txt", "w", stdout);
#endif
    scanf("%d", &N);
    for (int i = N - 1; i >= 0; i--)
    {
        scanf("%d %d", A + i, B + i);
    }
    scanf("%d %d", &L, &P);
    solve();
#ifndef ONLINE_JUDGE
    fclose(stdin);
    fclose(stdout);
#endif
    return 0;
}