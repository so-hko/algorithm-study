n = int(input())
home = sorted(list(map(int, input().split())))

if n // 2 == 0:
    print(home[n//2])
else:
    print(home[(n-1)//2])
