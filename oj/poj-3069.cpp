#include <iostream>
#include <algorithm>
using namespace std;
const int MAX_LEN = 1001;
int len;
unsigned int point[MAX_LEN];
int R;
void solve()
{
    sort(point, point + len);
    int last = 0;
    int counter = 0;
    int i = 0;
    while (i < len)
    {
        last = point[i++];
        while (i < len && point[i] <= last + R)
        {
            i++;
        }
        last = point[i - 1];
        while (i < len && point[i] <= last + R)
        {
            i++;
        }
        counter++;
    }

    printf("%d\n", counter);
}

int main()
{
#ifndef ONLINE_JUDGE
    freopen("poj-3069.in", "r", stdin);
    freopen("poj-3069.txt", "w", stdout);
#endif
    while (scanf("%d %d", &R, &len), R != -1)
    {
        for (int i = 0; i < len; i++)
        {
            scanf("%d", point + i);
        }
        solve();
    }
#ifndef ONLINE_JUDGE
    fclose(stdin);
    fclose(stdout);
#endif
    return 0;
}