#bfs, 인구이동
from collections import deque

n, l, r = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
total = 0

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

def migration(a, b):
    q = deque()
    temp = []
    q.append([a,b])
    temp.append([a,b])

    while q:
        x,y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0<=nx<n and 0<=ny<n and v[nx][ny] == 0:
                if l <= abs(arr[nx][ny] - arr[x][y]) <= r:
                    v[nx][ny] = 1
                    q.append((nx, ny))
                    temp.append((nx, ny))
    return temp

while True:
    v = [list(0 for _ in range(n+1)) for _ in range(n+1)]
    flag = 0
    for i in range(n):
        for j in range(n):
            if v[i][j] == 0:
                v[i][j] = 1
                open_country = migration(i, j)

                if len(open_country) > 1:
                    flag = 1

                    population = sum([arr[x][y] for x,y in open_country]) // len(open_country)
                    for x,y in open_country:
                        arr[x][y] = population

    if flag == 0:
        break
    total += 1

print(total)
