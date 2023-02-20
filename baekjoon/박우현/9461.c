#include<stdio.h>

int main(void)
{
	long int length[101]={0,1,1,1,2,2,0,};
	int N, T;

	scanf("%d", &T);

	for(int i=5;i<100;i++)
		length[i+1]=length[i-1]+length[i-2];

	for(int t=0;t<T;t++)
	{
		scanf("%d", &N);
		printf("%ld\n", length[N]);
	}

	return 0;
}
