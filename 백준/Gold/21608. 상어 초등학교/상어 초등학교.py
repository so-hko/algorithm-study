n = int(input())
students = [list(map(int,input().split())) for _ in range(n*n)]

dx = [-1,0,0,1]
dy = [0,-1,1,0]

res = [[0 for j in range(n)] for i in range(n)]

def isnotout(x,y):
    if x < 0 or y < 0:
        return False
    elif x >= n or y >= n :
        return False
    else:
        return True
        
def con1n2(students):
    popularCnt = [[-1 for _ in range(n)] for _ in range(n)]
    emptyCnt = [[-1 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if res[i][j] == 0:
                popularCnt[i][j] += 1
                emptyCnt[i][j] += 1
                for x in range(len(dx)):
                    if isnotout(i+dx[x], j+dy[x]):
                        if res[i+dx[x]][j+dy[x]] in students:
                            popularCnt[i][j] += 1
                        if res[i+dx[x]][j+dy[x]] == 0:
                            emptyCnt[i][j] += 1
            #print(emptyCnt)
    m = -2
    count = 0
    tmp = []
    for i in range(n):
        for j in range(n):
            if popularCnt[i][j] == m :
                count += 1
                tmp.append([i,j,emptyCnt[i][j]])
            elif popularCnt[i][j] > m :
                m = popularCnt[i][j]
                count = 1
                tmp = []
                tmp.append([i,j,emptyCnt[i][j]])
        # print("emptyCnt:", emptyCnt)
    return count,tmp
    
"""if count > 1: con2 실행 - tmp의 3번째 요소(인접한 칸 중에서 비어있는 칸)가 가장 큰 것"""
"""if 2를 만족하는 칸도 여러 개인 경우 : 행 이랑 열 가장 작은 칸으로.."""

def con2n3(tmp):
    
    cnt = 0
    max = -2
    x = 0
    y = 0
    for i in tmp:
        if i[2] > max:
            cnt = 1
            max = i[2]
            x = i[0]
            y = i[1]
        elif i[2] == max:
            cnt += 1
        # print("tmp:", tmp)
        # print("max",max)
    
    return x, y

for i in range(n*n):
    count ,tmp = con1n2(students[i][1:])
    if count > 1:
        x,y = con2n3(tmp)
        res[x][y] = students[i][0]
    elif count == 1:
        x = tmp[0][0]
        y = tmp[0][1]
        res[x][y] = students[i][0]

    # print(res)

satisfy = [[0 for _ in range(n)] for _ in range(n)]
for i in range(n):
    for j in range(n):
        for sn in students:
            if sn[0] == res[i][j]:
                popularStu = sn[1:]
        for x in range(len(dx)):
            if isnotout(i+dx[x], j+dy[x])and res[i+dx[x]][j+dy[x]] in popularStu:
                satisfy[i][j] += 1
satScore = 0
for i in range(n):
    for j in range(n):
        if satisfy[i][j] == 0:
            continue
        else: satScore += 10**(satisfy[i][j]-1)

print(satScore)
