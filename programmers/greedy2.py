number = "4177252841"
k=4

def solution(number, k):
    if k == 0 :
        return answer.append(number)
    answer = ''
    tmp = len(number) -k -1
    answer = answer.append(max(number[:-tmp]))
    index = number.indexOf(answer)
    number = number[index+1:]
    return solution(number,k-1)

print(solution(number,k))


def solution1(number, k, answer1):
    if k == 0 :
        return answer1 + number

    tmp = len(number) -k -1
    if tmp == 0:
        answer1 = answer1 + max(number)
        index = number.find(number)
        return answer1
    else:
        answer1 = answer1+max(number[:-tmp])
        index = number.find(max(number[:-tmp]))
        return solution1(number[index+1:],k-index,answer1)

def solution(number, k):
    answer1 = ''
    return solution1(number,k,answer1)


def solution(number, k):
    answer = ''
    while True:
        if k <= 0:
            return answer + number
            break

        tmp = len(number) - k - 1
        if tmp == 0:
            answer = answer + max(number)
            index = number.find(number)
            return answer
            break
        else:
            answer = answer + max(number[:-tmp])
            index = number.find(max(number[:-tmp]))
            number = number[index + 1:]
            k = k - index


def solution(number, k):
    if k == 0 :
        return number
    if number[0] > number[1]:
        number = number[0] + number[2:]
        k = k-1
    elif number[0] < number[1]:
        number = number[1:]
        k= k-1
    else
        continue
    return solution(number,k)