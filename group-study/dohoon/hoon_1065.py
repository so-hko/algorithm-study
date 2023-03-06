n = int(input())
total = 0

if n <= 99:
    print(n)

else:
    for i in range(100,n+1):
        a = i // 100
        b = i % 100 // 10
        c = i % 100 % 10
        if b-a == c-b:
            total += 1
    print(total+99)
