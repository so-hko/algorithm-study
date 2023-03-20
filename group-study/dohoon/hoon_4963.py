import sys
sys.setrecursionlimit(10000)
def island(y, x):
    if x >= w or x < 0 or y >= h or y < 0:
        return False

    if arr[y][x] == 1:
        arr[y][x] = 0
        island(y-1, x)
        island(y+1, x)
        island(y, x-1)
        island(y, x+1)

        island(y-1, x-1)
        island(y-1, x+1)
        island(y+1, x-1)
        island(y+1, x+1)
        return True
    return False

while True:
    w, h = map(int, input().split())
    if w != 0 or h != 0:
        count = 0
        arr = [list(map(int, input().split())) for _ in range(h)]

        for i in range(h):
            for j in range(w):
                if island(i, j):
                    count += 1
        print(count)
        continue
    break
