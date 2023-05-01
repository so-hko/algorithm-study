import sys
import heapq
input = sys.stdin.readline

left = []
right = []
n = int(input())
for i in range(n):
    k = int(input())

    if len(left) == len(right):
        heapq.heappush(left, -k)
    else:
        heapq.heappush(right, k)

    if len(right) > 0 and right[0] < -left[0]:
        left_k = heapq.heappop(left)
        right_k = heapq.heappop(right)

        heapq.heappush(left, -right_k)
        heapq.heappush(right, -left_k)

    print(-left[0])
