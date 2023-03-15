"""
체커
N개의 체커가 엄청 큰 보드 위에 있다.
i번 체커는 (xi, yi)에 있다. 
같은 칸에 여러 체커가 있을 수도 있다. 
체커를 한 번 움직이는 것은 그 체커를 위, 왼쪽, 오른쪽, 아래 중의 한 방향으로 한 칸 움직이는 것이다.
입력 | 첫째 줄에 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에 각 체커의 x좌표와 y좌표가 주어진다. 이 값은 1,000,000보다 작거나 같은 자연수이다.
출력 | 첫째 줄에 수 N개를 출력한다. k번째 수는 적어도 k개의 체커가 같은 칸에 모이도록 체커를 이동해야 하는 최소 횟수이다.
example)
4
15 14
15 16
14 15
16 15

0 2 3 4

4
1 1
2 1
4 1
9 1

0 1 3 10
"""

#입력
n = int(input())
coords = [list(map(int, input().split())) for _ in range(n)]

#점들이 모일 수 있는 모든 좌표 값 구하기
x_list = []
y_list = []
for i in range(len(coords)):
    x_list.append(coords[i][0])
    y_list.append(coords[i][1])
x_list = list(set(x_list))
y_list = list(set(y_list))



min = [-1] *n
for x1 in x_list:
    for y1 in y_list:
        distance = []
        for x2,y2 in coords:
            distance.append(abs(x1-x2) + abs(y1-y2))
        distance.sort()
        
        tmp = 0
        for i in range(n):
            tmp += distance[i]
            if min[i] == -1:
                min[i] = tmp
            elif min[i] > tmp:
                min[i] = tmp
            else :
                continue
print(*min)