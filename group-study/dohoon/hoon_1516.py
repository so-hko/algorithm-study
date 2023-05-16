from collections import deque
n = int(input())
v = [0] * (n+1)
graph = [[0] for _ in range(n+1)]
cost = [0] * (n+1)
for i in range(1,n+1):
    arr = list(map(int, input().split()))
    cost[i] = arr[0]
    arr.pop()
    del arr[0]
    for j in arr:
        graph[j].append(i)
        v[i] += 1

def sort():
    answer = [0] * (n+1)
    q = deque()
    for i in range(1, n+1):
        if v[i] == 0:
            q.append(i)

    while q:
        now = q.popleft()
        answer[now] += cost[now]
        for i in graph[now]:
            v[i] -= 1
            answer[i] = max(answer[i], answer[now])
            if v[i] == 0:
                q.append(i)

    for i in range(1,n+1):
        print(answer[i])

sort()
