n = int(input())

for _ in range(n):
    x1, y1, r1, x2, y2, r2 = map(int, input().split())
    distance = ((x1-x2)**2 + (y1-y2)**2)**0.5
    if distance == 0 and r1 == r2:  # 동심원 and 반지름 같을 때
        print(-1)
    elif abs(r1-r2) == distance or r1 + r2 == distance:  # 내접, 외접일 때
        print(1)
    elif abs(r1-r2) < distance < (r1+r2):  # 두 원의 교점이 두개 일 때
        print(2)
    elif abs(r1-r2) > distance or abs(r1+r2) < distance: #교점 0개 (두 원은 서로 밖에 있어서 만나지 않거나 다른원의 내부에 있어서 만나지않을 때)
        print(0)