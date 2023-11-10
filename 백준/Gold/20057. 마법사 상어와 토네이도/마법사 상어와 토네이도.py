import math
n = int(input())
board = [list(map(int,input().split())) for _ in range(n)]
answer = 0

# index out of range
def isOut(x,y):
    if x < 0 or x >= n :  return True
    elif y < 0 or y >= n  : return True
    else :  return False
        
# sand spread
def spread(d,x,y):
    global answer
    global board
    dx = [0, 1, -1]
    dy = [-1, 0, 1]
    sand = board[x][y]
    board[x][y] = 0
    s10 = math.floor(sand * 0.1)
    s7 = math.floor(sand * 0.07)
    s5 = math.floor(sand * 0.05)
    s2 = math.floor(sand * 0.02)
    s1 = math.floor(sand * 0.01)
    alpha = math.floor(sand - (s10*2 + s7*2 + s5 + s2*2 + s1*2))
    
    if d == 0 or d == 2 :
        # s7, s2
        for i in range(1,3,1):  # 1,2
            for j in range(1,3,1):  # 1,2
                cx = x + dx[i] * j
                cy = y + dy[1] * j
                if j == 1 and not isOut(cx,cy) :
                    board[cx][cy] += s7
                elif j == 1 and isOut(cx,cy):
                    answer += s7
                elif  j == 2 and not isOut(cx,cy):
                    board[cx][cy] += s2
                elif j == 2 and isOut(cx,cy) :
                    answer += s2
        # s1, s10 o
        for i in range(1,3,1):  # 1,2
            for j in range(0,3,2):  # 0,2
                cx = x + dx[i]
                cy = y + dy[j]
                if d == 0 and j == 0 :    
                    if not isOut(cx,cy) :
                        board[cx][cy] += s10
                    elif isOut(cx,cy) :
                        answer += s10
                elif d == 2 and j == 0 :  
                    if not isOut(cx,cy) :
                        board[cx][cy] += s1
                    elif isOut(cx,cy) :
                        answer += s1
                elif d == 0 and j == 2 :  
                    if not isOut(cx,cy) :
                        board[cx][cy] += s1
                    elif isOut(cx,cy) :
                        answer += s1
                elif d == 2 and j == 2 :  
                    if not isOut(cx,cy) :
                        board[cx][cy] += s10
                    elif isOut(cx,cy) :
                        answer += s10
        # alpha, s5 o
        for j in range(1,3,1):  # 1,2
            if d == 0 :
                cx = x + dx[0] * j
                cy = y + dy[0] * j
            elif d == 2 :
                cx = x + dx[0] * j
                cy = y + dy[2] * j                
            if j == 1 :
                if not isOut(cx,cy) :
                    board[cx][cy] += alpha
                elif isOut(cx,cy) :
                    answer += alpha
            elif j == 2 :
                if not isOut(cx,cy) :
                    board[cx][cy] += s5
                elif isOut(cx,cy) :
                    answer += s5
                
    if d == 1 or d == 3 :
        # s7, s2 o
        for i in range(0,3,2):  # 0,2
            for j in range(1,3,1):  # 1,2
                cx = x + dx[0] * j
                cy = y + dy[i] * j
                if j == 1 :
                    if not isOut(cx,cy) :
                        board[cx][cy] += s7
                    elif isOut(cx,cy) :
                        answer += s7
                elif j == 2 :
                    if not isOut(cx,cy) :
                        board[cx][cy] += s2
                    elif isOut(cx,cy) :
                        answer += s2
        # s1, s10 o
        for i in range(1,3,1):  # 1,2
            for j in range(0,3,2):  # 0,2
                cx = x + dx[i]
                cy = y + dy[j]
                if d == 1 and i == 1 :    
                    if not isOut(cx,cy) :
                        board[cx][cy] += s10
                    elif isOut(cx,cy) : 
                        answer += s10
                elif d == 3 and i == 1 :  
                    if not isOut(cx,cy) :  
                        board[cx][cy] += s1
                    elif isOut(cx,cy) :  
                        answer += s1
                elif d == 1 and i == 2 :  
                    if not isOut(cx,cy) : 
                        board[cx][cy] += s1
                    elif isOut(cx,cy) : 
                        answer += s1
                elif d == 3 and i == 2 :  
                    if not isOut(cx,cy) :  
                        board[cx][cy] += s10
                    elif isOut(cx,cy) : 
                        answer += s10
                    
        # alpha, s5 o
        for j in range(1,3,1):  # 1,2
            if d == 1 :
                cx = x + dx[1] * j
                cy = y + dy[1] * j
            elif d == 3 :
                cx = x + dx[2] * j
                cy = y + dy[1] * j                
            if j == 1 :
                if not isOut(cx,cy) :
                    board[cx][cy] += alpha
                elif isOut(cx,cy) :
                    answer += alpha
            elif j == 2 :
                if not isOut(cx,cy) :
                    board[cx][cy] += s5
                elif isOut(cx,cy) :
                    answer += s5

    
# move tornado
def move():
    global answer
    global board
    dx = [0, 1, 0, -1]  # directions
    dy = [-1, 0, 1, 0]
    cx = n // 2  # start coordinates
    cy = n // 2
    c = 1
    s = 0
    for i in range(n*2-1):  # turnabout
        d = i % 4
        c += 1
        if c % 2 == 0 :
            s += 1
            c = 0
        if s == n :
            s -= 1
        for j in range(s):
            cx = cx + dx[d]
            cy = cy + dy[d]
            # print("[cx , cy] : [", cx, " , ",cy, "]") # Check if the tornado function works well
            if board[cx][cy] != 0 :
                spread(d,cx,cy)  # 'spread' function execution with direction info
                #for x in range(n):
                    #print(board[x])
                #print()

                
move()
print(answer)
    