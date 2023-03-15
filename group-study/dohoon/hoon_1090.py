import math

n = int(input())
checker = [list(map(int, input().split())) for _ in range(n)]
arr = [round(math.pow(2, 30))]  * (n+1)

for i in range(n):                              # x좌표
    for j in range(n):                          # y좌표
        x, y = checker[i][0], checker[j][1]
        move = []                               # (x,y)와 각 checker 좌표 거리(움직임)를 저장

        for k in range(n):                            
            move.append(abs(x - checker[k][0]) + abs(y - checker[k][1]))
        move.sort()
        distance = 0
        for l in range(n):                      # 같은 칸에 모이기 위한 좌표 값들의 움직임 합을 구하는 구문
            distance += move[l]
            if distance < arr[l+1]:             # 최소 이동 횟수를 구함
                arr[l+1] = distance

print(*arr[1:])                                 # *은 unpacking / 배열 출력 시 [] 없애줌
