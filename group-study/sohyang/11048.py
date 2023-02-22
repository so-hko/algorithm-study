# input row, col, maze value
n, m = map(int, input().split())
maze = [list(map(int, input().split())) for _ in range(n)]


# all searching
# Comparison of numbers of cases to reach (a,b) and substitution with larger number

for i in range(1,n):
    maze[i][0] += maze[i-1][0]
for i in range(1,m):
    maze[0][i] += maze[0][i-1]

#print(maze)

for i in range(1,n):
    for j in range(1,m):
        if maze[i-1][j] + maze[i][j] >= maze[i][j-1] + maze[i][j]:
            maze[i][j] = maze[i-1][j] + maze[i][j]
        else:
            maze[i][j] = maze[i][j-1] + maze[i][j]
#print(maze)
print(maze[n-1][m-1])