n, m = map(int, input().split())
maze = []               # 사탕 위치 배열

for i in range(n):
    maze.append(list(map(int, input().split())))

candy = [[0 for j in range(m+1)] for i in range(n+1)]               # 가져온 사탕 개수 저장

for i in range(1, n+1):
    for j in range(1, m+1):
        candy[i][j] = maze[i-1][j-1] + max(candy[i-1][j-1], candy[i-1][j], candy[i][j-1])

print(candy[n][m])
