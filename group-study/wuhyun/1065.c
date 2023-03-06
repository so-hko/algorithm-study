#include<stdio.h>

int function(int N)
{
	if(N<100)
		return N;
	else
	{
		int count=99;

		for(int i=100;i<=N;i++)
			count+=(i/100-(i/10)%10)==((i/10)%10-i%10);

		return count;
	}
}

int main(void)
{
	int N;

	scanf("%d", &N);

	printf("%d", function(N));

	return 0;
}
