n,m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
cloud = [[0 for _ in range(n)] for _ in range(n)]
clcoords = [[n-1, 0], [n-1, 1], [n-2, 0], [n-2, 1]]
ans = 0
dx = [0,-1,-1,-1,0,1,1,1]
dy = [-1,-1,0,1,1,1,0,-1]

def isOut(x,y):
    if x < 0 or x >= n : return True
    elif y < 0 or y >= n : return True
    else : return False
        
def magicDance(d,s):
    global clcoords
    # 1. move the clouds
    for c in clcoords:
        x = ( c[0] + dx[d-1] * s ) % n
        y = ( c[1] + dy[d-1] * s ) % n
        cloud[x][y] = 1
        # 2. Rain falls, and increase the amount of water by 1
        board[x][y] += 1

    # 3. disappear the clouds
    clcoords = []
    
    # 4. water copy bug magic
    for i in range(n):
        for j in range(n):
            if cloud[i][j] == 1:
                for d in range(1,8,2):
                    if ( not isOut(i+dx[d],j+dy[d]) ) and ( board[i+dx[d]][j+dy[d]] > 0 ) :
                        board[i][j] += 1
                        
    # 5. The amount of water is reduced by 2, without the cloud disappeared in step 3.
    for i in range(n):
        for j in range(n):
            if cloud[i][j] == 0:
                if board[i][j] >= 2:
                    board[i][j] -= 2
                    clcoords.append([i,j])
for i in range(m):
    d,s = map(int,input().split())
    magicDance(d,s)
    cloud = [[0 for _ in range(n)] for _ in range(n)]
    
for i in range(n):
    for j in range(n):
        ans += board[i][j]

print(ans)