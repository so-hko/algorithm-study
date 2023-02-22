t = int(input())

def get_tri(num):
    arr = [1,1,1,2,2,3,4,5,7,9]
    for i in range(10,101):
        arr.append(arr[i-1]+arr[i-5])
    return arr[n-1]

for i in range(0,t):
    n = int(input())
    print(get_tri(n))

