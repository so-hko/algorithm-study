import sys
import math
import heapq
input = sys.stdin.readline
v, e, p = map(int, input().split())
graph = [[] for _ in range(v+1)]
INF = math.inf
for _ in range(e):
    a, b, c = map(int, input().split())
    graph[a].append([b,c])
    graph[b].append([a,c])

def func(start, end):
    distance = [INF for _ in range(v+1)]
    distance[start] = 0
    q = []
    heapq.heappush(q, [0,start])
    while q:
        dist, value = heapq.heappop(q)
        if dist > distance[value]:
            continue
        for i, j in graph[value]:
            i_distance = dist + j
            if i_distance < distance[i]:
                distance[i] = i_distance
                heapq.heappush(q, [i_distance, i])
    return distance[end]

if func(1, v) != func(1, p) + func(p, v):
    print('GOOD BYE')
else:  # 최소거리 = 1~건우 + 건우~목적지
    print('SAVE HIM')
