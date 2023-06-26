import sys
import heapq
input = sys.stdin.readline

n = int(input())
schedules = [list(map(int, input().split())) for _ in range(n)]
schedules.sort()
h = []
for start, end in schedules:
    if h and h[0] <= start:
        heapq.heappop(h)
    heapq.heappush(h, end)

print(len(h))
