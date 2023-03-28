import sys, copy
n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
result = 0

def left(board):
    for i in range(n):
        pointer = 0
        for j in range(1,n):
            if board[i][j] != 0:
                temp = board [i][j]
                board[i][j] = 0

                if board[i][pointer] == 0:
                    board[i][pointer] = temp

                elif board[i][pointer] == temp:
                    board[i][pointer] *= 2
                    pointer += 1

                else:
                    board[i][pointer] = temp
                    pointer += 1
    return board

def right(board):
    for i in range(n):
        pointer = n-1
        for j in range(n-1,-1,-1):
            if board[i][j] != 0:
                temp = board[i][j]
                board[i][j] = 0

                if board[i][pointer] == 0:
                    board[i][pointer] = temp

                elif board[i][pointer] == temp:
                    board[i][pointer] *= 2
                    pointer -= 1

                else:
                    board[i][pointer] = temp
                    pointer -= 1
    return board


def up(board):
    for j in range(n):
        pointer = 0
        for i in range(n):
            if board[i][j] != 0:
                temp = board[i][j]
                board[i][j] = 0

                if board[pointer][j] == 0:
                    board[pointer][j] = temp

                elif board[pointer][j] == temp:
                    board[pointer][j] *= 2
                    pointer += 1

                else:
                    board[pointer][j] = temp
                    pointer += 1
    return board

def down(board):
    for j in range(n):
        pointer = n-1
        for i in range(n-1, -1, -1):
            if board[i][j] != 0:
                temp = board[i][j]
                board[i][j] = 0

                if board[pointer][j] == 0:
                    board[pointer][j] = temp

                elif board[pointer][j] == temp:
                    board[pointer][j] *= 2
                    pointer -= 1

                else:
                    board[pointer][j] = temp
                    pointer -= 1
    return board

def dfs(arr, n):
    board = copy.deepcopy(arr)
    if n == 5:
        return max(map(max, arr))

    return max(dfs(up(board), n+1), dfs(down(board), n+1), dfs(left(board), n+1), dfs(right(board), n+1))

print(dfs(arr, 0))
