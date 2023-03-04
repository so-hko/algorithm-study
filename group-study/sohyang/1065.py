"""
어떤 양의 정수 X의 각 자리가 등차수열을 이룬다면, 그 수를 한수라고 한다. 
등차수열은 연속된 두 개의 수의 차이가 일정한 수열을 말한다. 
N이 주어졌을 때, 1보다 크거나 같고, N보다 작거나 같은 한수의 개수를 출력하는 프로그램을 작성하시오. 
ex) 110 => 99
"""

n = int(input())
count = 0
for i in range(1,n+1):
    if 0 < i < 10:
        count = count + 1
    elif 10 <= i <= 99:
        count = count + 1
    elif 100 <= i <= 999:
        one = i // 100 
        two = (i-(one*100)) // 10
        three = i - ((one * 100) + (two * 10))
        if (one-two) == (two-three) :
            count = count + 1
        else:
            continue
    elif i == 1000:
        continue

print(count)
        