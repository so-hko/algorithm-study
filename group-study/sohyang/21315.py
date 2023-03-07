n = int(input())
final = list(map(int, input().split()))
array = list(range(1,n+1))

# (2,k)-섞기
def mixMagic(array,k,end):
    tmp1 = array[0:end+1]
    tmp2 = array[end+1:]
    moved = tmp1[end-(2**k-1):] + tmp1[0:end-(2**k-1)]
    end = len(tmp1[end-(2**k-1):])-1
    moved = moved + tmp2
    k = k - 1
    if k == -1:
        return moved
    else:
        return mixMagic(moved,k,end)

# 가장 큰 k값 구하기
def fineK(n):
    k = 0
    while(2**k < n):
        k = k + 1
    return k-1

# 전수조사
k = fineK(n)
for i in range(1,k+1):
    moved1 = mixMagic(array,i,len(array)-1)
    for j in range(1,k+1):
        moved2 = mixMagic(moved1,j,len(moved1)-1)
        if (moved2 == final):
            print(i , j)
            break
        else : 
            continue
