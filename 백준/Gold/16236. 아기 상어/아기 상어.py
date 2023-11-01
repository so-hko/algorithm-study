from collections import deque
n=int(input())
board = [list(map(int,input().split())) for _ in range(n)]
w = 2 # 아기상어크기
enum = 0 # 아기상어가 먹은 물고기수
time = 0
visited = [[0 for _ in range(n)] for _ in range(n)]

def isOut(x,y):
    if x < 0  or x >= n :  return True
    elif y < 0  or y >= n : return True
    else : return False
        
def bfs(x,y):
    fcoord = []
    global w
    global enum
    global time

    dx = [ -1, 0, 0, 1 ]
    dy = [ 0, -1, 1, 0 ]
    q = deque()
    q.append([x,y,0])
    visited[x][y] = 1
    while q : 
        x, y, cnt = q.popleft()
        for i in range(4) :
            nx = x + dx[i]
            ny = y + dy[i]
            # 아기 상어가 잡아먹을 수 있는 물고기가 있다면
            if (not isOut(nx,ny) and visited[nx][ny] == 0 and board[nx][ny] != 0 and board[nx][ny] < w) :
                visited[nx][ny] = 1
                fcoord.append([cnt+1, nx,ny])
                    
            # 아기 상어가 잡아먹을 수 있는 물고기가 없으면
            elif (not isOut(nx,ny) and visited[nx][ny] == 0 and board[nx][ny] == 0) or (not isOut(nx,ny) and visited[nx][ny] == 0 and board[nx][ny] == w) :
                visited[nx][ny] = 1
                q.append([nx,ny,cnt+1])
                
    return fcoord


while True :
    sx = 0
    sy = 0
    for i in range(n) :
        for j in range(n) :
            if board[i][j] == 9 :
                sx = i
                sy = j
                break      
    #print("aaa  : ", sx, sy)
    #print("bfs 이전 board : ", board)
    fcoord = bfs(sx, sy)
    visited = [[0 for _ in range(n)] for _ in range(n)]
        # 아기상어 위치 갱신
    if fcoord :
        board[sx][sy] = 0
        fcoord.sort()
        #print(fcoord)
        #print("eat : ", fcoord[0][1] , fcoord[0][2])
        board[fcoord[0][1]][fcoord[0][2]] = 9
        # 아기상어가 먹은 물고기수 갱신 , 아기상어몸무게 만큼 먹었으면 아기상어 몸무게 증가
        #print("enum : ", enum)
        #print("w : ", w)
        enum += 1  
        #print("time ::: ", time)
        #print(ox , " - > " , fcoord[0], " ", ox , " - > " , fcoord[1])
        if enum == w :
            w += 1
            enum = 0
        time += fcoord[0][0]
    #print("bfs 이후 board : ", board)
    else:
        print(time)
        break
        

