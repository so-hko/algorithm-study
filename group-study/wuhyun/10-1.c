#include<stdio.h>
#include<stdlib.h>

char* solution(const char* survey[], size_t survey_len, int choices[], size_t choices_len) {
    int RT=0, CF=0, JM=0, AN=0;
    
    char *answer=calloc(5,sizeof(char));
    
    for(int i=0;i<survey_len;i++)
        switch(survey[i][0])
        {
            case 'R':
                RT+=(choices[i]-4);
                break;
            case 'T':
                RT-=(choices[i]-4);
                break;
            case 'F':
                CF-=(choices[i]-4);
                break;
            case 'C':
                CF+=(choices[i]-4);
                break;
            case 'M':
                JM-=(choices[i]-4);
                break;
            case 'J':
                JM+=(choices[i]-4);
                break;
            case 'A':
                AN+=(choices[i]-4);
                break;
            case 'N':
                AN-=(choices[i]-4);
                break;
        }
    
    answer[0]=RT>0?'T':'R';
    answer[1]=CF>0?'F':'C';
    answer[2]=JM>0?'M':'J';
    answer[3]=AN>0?'N':'A';
    
    return answer;
}