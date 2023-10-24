from collections import deque

n, m = map(int,input().split())
boards = [list(map(int,input().split())) for _ in range(n)]
score = 0

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
        
    return [numBlocks, numRainbowBlocks, blockCoords]
    
def remove(blockCoords):
    for i, j in blockCoords:
        boards[i][j] = -2 # 비워져있는 건 -2

def gravitate(boards):
    for j in range(n):
        for i in reversed(range(n-1)):
            if (0 <= boards[i][j] <= m) and boards[i+1][j] == -2:
                x = i+1
                while not isOut(x,j) and boards[x][j] == -2:
                    boards[x][j] = boards[x-1][j]
                    boards[x-1][j] = -2
                    x = x+1


def rotate(boards):
    tmpB = [[0 for _ in range(n)] for _ in range(n)]
    for j in reversed(range(n)):
        for i in range(n):
            if not isOut(n-j-1,i):
                tmpB[n-j-1][i] = boards[i][j]
    boards = tmpB
    return tmpB

while True:
    visited = [[0 for _ in range(n)] for _ in range(n)]
    blocks = []
    # 블록 그룹들 모두 탐색
    for i in range(n):
        for j in range(n):
            if boards[i][j] > 0 and not visited[i][j]:  # 일반블록이면서 방문 안했으면
                visited[i][j] = 1  # 방문
                block_info = findGroup(i, j, boards[i][j])  # 인접한 블록 찾기
                # block_info = [블록크기, 무지개블록 개수, 블록좌표]
                if block_info[0] >= 2:
                    blocks.append(block_info)
    blocks.sort(reverse=True)

    # 블록 그룹이 하나도 없다면 종료
    if not blocks:
        break

    remove(blocks[0][2])
    score += blocks[0][0]**2
    
    # 4. 중력
    gravitate(boards)

    # 5. 90회전
    boards = rotate(boards)

    # 6. 중력
    gravitate(boards)

print(score)
            
            
            
