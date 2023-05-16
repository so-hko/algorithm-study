n, m = map(int, input().split())
byte, cost = [0], [0]
byte += list(map(int, input().split()))
cost += list(map(int, input().split()))
k = sum(cost)
dp = [[0 for _ in range(k+1)] for _ in range(n+1)]

for i in range(1,n+1):
    b = byte[i]
    c = cost[i]

    for j in range(1, k+1):
        if j < c:
            dp[i][j] = dp[i-1][j]
        else:
            dp[i][j] = max(b+dp[i-1][j-c], dp[i-1][j])
        if dp[i][j] >= m:
            k = min(k, j)
if m == 0:
    print(0)
else:
    print(k)

