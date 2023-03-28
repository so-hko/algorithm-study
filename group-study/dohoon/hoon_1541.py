a = input().split('-')
arr = []
for i in a:
    total = 0
    k = i.split('+')
    for j in k:
        total += int(j)
    arr.append(total)

result = arr[0]
for i in range(1, len(arr)):
    result -= arr[i]

print(result)
