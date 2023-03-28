n = int(input())
arr = list(map(int, input().split()))
arr.sort()

left, right = 0, n-1
num = abs(arr[left] + arr[right])
result = [arr[left], arr[right]]

while left < right:
    sum_ = arr[left] + arr[right]

    if num > abs(sum_):
        num = abs(sum_)
        result = [arr[left], arr[right]]
        if result == 0:
            break

    if arr[left] + arr[right] < 0:
        left += 1
    else:
        right -= 1

print(result[0], result[1])
