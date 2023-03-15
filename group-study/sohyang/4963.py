import sys
# input 입력 속도 빠르게
input = sys.stdin.readline
# 재귀 호출 제한 풀기
sys.setrecursionlimit(10**6)

# find num of lands
def find_lands(i, j, M, check):
    if i >= len(M) or j >= len(M[0]) :
        return
    elif i == -1 or j == -1:
        return
    
    if check[i][j] == -1:
        if M[i][j] == 1:
            check[i][j] = 1
            find_lands(i-1, j-1, M, check)
            find_lands(i-1,j, M, check)
            find_lands(i-1, j+1, M, check)
            find_lands(i, j-1, M, check)
            find_lands(i, j+1, M, check)
            find_lands(i+1, j-1, M, check)
            find_lands(i+1, j, M, check)
            find_lands(i+1, j+1, M, check)
        elif M[i][j] == 0:
            check[i][j] = 0
            return
    if check[i][j] > -1:
        return
    
# execute until input 0 0
while True:
    x,y = map(int,input().split())
    if x==0 and y==0:
        break
    M = [list(map(int, input().split())) for _ in range(y)]
    check = [[-1 for col in range(x)] for row in range(y)]
    count = 0
    for i in range(len(M)):
        for j in range(len(M[i])):
            if check[i][j] == -1:
                find_lands(i,j,M,check)
                if check[i][j] == 1:
                    count += 1
            else:
                pass
    print(count)


