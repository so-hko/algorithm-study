from collections import deque
""" Input """
n, m = map(int,input().split())
boards = [list(map(int,input().split())) for _ in range(n)]

score = 0

numBlocks = 0
numRainbowBlocks = 0
blockCoords = []

def isOut(x,y):
    if x < 0 or x >= n: return True
    elif y < 0 or y >= n: return True
    else: return False

def findGroup(x,y,color):
    numBlocks = 1
    numRainbowBlocks = 0
    blockCoords = [[x,y]]
    rainbowCoords = []
    dx = [-1, 0, 0, 1]
    dy = [0, -1, 1, 0]
    q = deque()
    q.append([x,y])
    while q:
        x,y = q.popleft()
        for a in range(4):
            i = x+dx[a]
            j = y+dy[a]
            # 그룹에 속하는 일반 블록이면
            if not isOut(i,j) and visited[i][j] == 0 and boards[i][j] == color:
                visited[i][j] = 1
                q.append([i,j])
                numBlocks += 1
                blockCoords.append([i,j])
            # 무지개 블록이면
            elif not isOut(i,j) and visited[i][j] == 0 and boards[i][j] == 0:
                visited[i][j] = 1
                q.append([i,j])
                numBlocks += 1
                numRainbowBlocks += 1
                blockCoords.append([i,j])
                rainbowCoords.append([i,j])
    # 무지개 블록의 방문기록은 0으로 초기화 해주기
    for i,j in rainbowCoords:
        visited[i][j] = 0
        
    return numBlocks, numRainbowBlocks, blockCoords
    
def remove(blockCoords):
    for i, j in blockCoords:
        boards[i][j] = -2 # 비워져있는 건 -2

def gravitate():
    for j in range(n):
        for i in reversed(range(n-1)):
            if (0 <= boards[i][j] <= m) and boards[i+1][j] == -2:
                x = i+1
                while not isOut(x,j) and boards[x][j] == -2:
                    boards[x][j] = boards[x-1][j]
                    boards[x-1][j] = -2
                    x = x+1
    return 0
    """
def gravitate(boards):
    for i in range(n-2, -1, -1):
        for j in range(n):
            if boards[i][j] > -1: 
                r = i
                while True:
                    if 0<=r+1<n and boards[r+1][j] == -2:  # 다음행이 인덱스 범위 안이면서 -2이면 아래로 다운
                        boards[r+1][j] = boards[r][j]
                        boards[r][j] = -2
                        r += 1
                    else:
                        break
                            """


def rotate(boards):
    tmpB = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if not isOut(n-j-1,i):
                tmpB[n-j-1][i] = boards[i][j]
    # boards = tmpB
    return tmpB

while True:
    visited = [[0 for _ in range(n)] for _ in range(n)]
    # 블록 그룹들 모두 탐색
    for i in range(n):
        for j in range(n):
            if visited[i][j] == 0 and (1 <= boards[i][j] <= m):
                visited[i][j] = 1
                nB, nRB, bC = findGroup(i,j,boards[i][j])
                # 크기가 가장 큰 블록 그룹 찾기
                if (i==0 and j==0) or (nB > numBlocks) or (nB == numBlocks and nRB >= numRainbowBlocks):
                    numBlocks = nB
                    numRainbowBlocks = nRB
                    blockCoords = bC

    # 블록 그룹이 하나도 없다면 종료
    if numBlocks <= 1:
        break
        
    # 그룹이 존재한다면, 찾은 블록 그룹 제거 -> 중력적용 -> 90회전 -> 중력적용
    #print("remove 수행 전 boards : ", boards)
    remove(blockCoords)
    #print("remove 수행 후 boards : ", boards)
    score += numBlocks ** 2
    #print(score)
    
    gravitate()
    #print("gravitate1 수행 후 boards : ", boards) 
    boards = rotate(boards)
    #print("rotate 수행 후 boards : ", boards)
    gravitate()
    #print("gravitate2 수행 후 boards : ", boards)
    #print("visited 출력 : ", visited)
    numBlocks = 0
    numRainbowBlocks = 0
    blockCoords = []

print(score)
            
            
            
