n = int(input())
schedule = [list(map(int, input().split())) for _ in range(n)]
#maxPay
maxPay = 0
def maximumProfit(schedule, i, pay):
    global maxPay
    if i > n-1:
        if maxPay <= pay : maxPay = pay
        return maxPay
    
    if i + schedule[i][0] > n:
        pass
    else:
        maximumProfit(schedule, i+schedule[i][0], pay+schedule[i][1])

    maximumProfit(schedule, i+1, pay)

maximumProfit(schedule, 0, 0)
print(maxPay)
    