#include <stdio.h>
#include <stdlib.h>

int solution(int queue1[], size_t queue1_len, int queue2[], size_t queue2_len) {
    int answer = 0, q1_front=0, q2_front=0, q1_rear=queue1_len, q2_rear=queue2_len, size=2*queue1_len;
    int *q1=malloc(size*sizeof(int)), *q2=malloc(size*sizeof(int));
    long q1_sum=0, q2_sum=0;
    
    for(int i=0;i<queue1_len;i++)
    {
        q1[i]=queue1[i];
        q2[i]=queue2[i];
        q1_sum+=q1[i];
        q2_sum+=q2[i];
    }
    
    if((q1_sum+q2_sum)&2==1)
    {
        free(q1);
        free(q2);
        return -1;
    }

    while(q1_sum!=q2_sum && q1_front<q1_rear && q2_front<q2_rear && answer<size+2)
    {
        answer++;
        if(q1_sum<q2_sum)
        {
            q1[(q1_rear++)%size]=q2[(q2_front++)%size];
            q1_sum+=q1[(q1_rear-1)%size];
            q2_sum-=q2[(q2_front-1)%size];
        }
        else
        {
            q2[(q2_rear++)%size]=q1[(q1_front++)%size];
            q2_sum+=q2[(q2_rear-1)%size];
            q1_sum-=q1[(q1_front-1)%size];
        }
    }
    
    free(q1);
    free(q2);
    
    if(q1_sum!=q2_sum)
        return -1;
    
    return answer;
}