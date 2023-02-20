n = int(input())
if n==1:
    print(1%10007)
if n==2:
    print(2%10007)
if n>=3:
    curr, next = 1,2
    for _ in range(n-1):
        curr, next = next, curr + next
    print(curr%10007)