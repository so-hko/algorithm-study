import math
N,M,K = map(int, input().split())
fbs = [list(map(int, input().split())) for _ in range(M)]
for fb in fbs:
    fb[0] = fb[0]-1
    fb[1] = fb[1]-1
dx = [-1,-1,0,1,1,1,0,-1]
dy = [0,1,1,1,0,-1,-1,-1]
ans = 0


def move():
    bloc = [[[0 for _ in range(7)] for _ in range(N)] for _ in range(N)]
    global fbs
    tfb = []
    for fb in fbs:
        tdc = 0
        s = fb[3]
        d = fb[4]
        fb[0] = ( fb[0] + dx[d] * s ) % N
        fb[1] = ( fb[1] + dy[d] * s ) % N

        
        if bloc[fb[0]][fb[1]][5] == 0: #cnt
            if fb[4] % 2 == 0:
                tdc = 1
            tmp = [fb[0],fb[1],fb[2],fb[3],fb[4],1,tdc]
            bloc[fb[0]][fb[1]] = tmp
        else:
            if fb[4] % 2 == 0:
                tdc = 1
            bloc[fb[0]][fb[1]][2] += fb[2]
            bloc[fb[0]][fb[1]][3] += fb[3]
            bloc[fb[0]][fb[1]][5] += 1 #count
            bloc[fb[0]][fb[1]][6] += tdc

    for i in range(N):
        for j in range(N):
            if bloc[i][j][5] != 0: #cnt
                if bloc[i][j][5] >= 2:
                    tm = math.floor(bloc[i][j][2]/5)
                    ts = math.floor(bloc[i][j][3]/bloc[i][j][5])              
                    if tm != 0:
                        if bloc[i][j][6] == bloc[i][j][5] or bloc[i][j][6] == 0:
                            for x in range(0,7,2):
                                tmp = [i,j,tm,ts,x]
                                tfb.append(tmp)
                        else:
                            for x in range(1,8,2):
                                tmp = [i,j,tm,ts,x]
                                tfb.append(tmp)
                else :
                    tfb.append(bloc[i][j])
    #print("bloc : ", bloc)
    fbs = tfb[:]
    
for _ in range(K):
    move()
    #print("fbs L:::: ",fbs)
for fb in fbs:
    ans += fb[2]
print(ans)