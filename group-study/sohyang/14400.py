"""
영선이는 이번에 편의점으로 창업을 하려고 계획 중이다. 
이번 창업을 위해 많은 준비를 하고 있는데, 아직 편의점을 세울 위치를 결정을 하지 못했다. 
영선이는 미리 시장조사를 하여, 주요 고객들이 어느 위치에 존재하는지 파악을 하였고, 모든 고객들의 거리의 합을 최소로 하려한다. 
두 위치의 거리는 |x1-x2|+|y1-y2|로 정의한다.
n명의 주요 고객들의 위치 (xi,yi)이 주어질 때, 모든 고객들의 거리 합을 최소로 하는 위치에 편의점을 세울 때, 그 최소 거리 합을 출력하시오.
입력 | 첫째 줄에는 주요 고객들의 수n이 주어진다.(1≤n≤100,000) 다음 n줄에는 고객들의 위치 (x,y)가 주어진다.(-1,000,000≤x,y≤1,000,000)
출력 | 모든 고객들의 거리 합을 최소로 하는 위치에 편의점을 세울 때, 그 최소 거리 합을 출력하시오.
input
5 
2 2
3 4
5 6 
1 9
-2 -8
output
30
"""

n = int(input())
loc = [list(map(int, input().split())) for _ in range(n)]
def getXYList(loc):
    x = []
    y = []
    for i in range(len(loc)):
        x.append(loc[i][0])
        y.append(loc[i][1])
    x.sort()
    y.sort()
    return x , y
x,y = getXYList(loc)
x = x[(len(x)-1)//2]
y = y[(len(y)-1)//2]
tmp = 0
for i in range(len(loc)):
    tmp = tmp + abs(loc[i][0]-x)+abs(loc[i][1]-y)
print(tmp)