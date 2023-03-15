n = int(input())
client = [list(map(int, input().split())) for _ in range(n)]
x2, y2 = 0, 0

client.sort(key=lambda x:x[0])              # x 정렬
x1 = client[(n-1)//2][0]                    # 중간값 찾기

client.sort(key=lambda x:(x[1], x[0]))      # y 좌표 정렬
y1 = client[(n-1)//2][1]

for i in range(n):
    x2 += abs(x1-client[i][0])
    y2 += abs(y1-client[i][1])

print(x2+y2)
