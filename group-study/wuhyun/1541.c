#include<stdio.h>
#include<string.h>
#include<stdbool.h>

int main(void)
{
	char str[51]={'\0', }, op[51]={'\0', };
	int num[50]={0, }, count=0, sum=0;
	bool minusappeared=false;

	scanf("%s", str);

	for(int i=0;i<strlen(str);i++)
		if(str[i]>='0'&&str[i]<='9')
		{
			num[count]*=10;
			num[count]+=str[i]-'0';
		}
		else
			op[++count]=str[i];

	for(int i=0;i<=count;i++)
		if(minusappeared)
			sum-=num[i];
		else if(op[i]=='-')
		{
			minusappeared=true;
			sum-=num[i];
		}
		else
			sum+=num[i];

	printf("%d\n", sum);
	return 0;
}