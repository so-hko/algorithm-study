n = int(input())
tp = [[0,0]]
pay = [0 for i in range(n+2)]

for i in range(n):
    t, p = map(int, input().split())
    tp.append([t,p])

for i in range(n-1,-1,-1):
    if i+tp[i+1][0] > n:
        pay[i+1] = pay[i+2]
    else:
        pay[i+1] = max(pay[i+2], tp[i+1][1]+pay[i+1+tp[i+1][0]])

print(pay[1])
