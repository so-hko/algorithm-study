#include<stdio.h>
#include<stdlib.h>

int compare(const void *x,const void *y)
{
	return *(int *)x>*(int *)y?1:*(int *)x==*(int *)y?0:-1;
}

int abs(int x)
{
	return x<0?-x:x;
}

int main(void)
{
	int N, *liquid=NULL, neutrality[2]={2100000000,0}, left=0, right;

	scanf("%d", &N);
	liquid=(int *)malloc(N*sizeof(int));

	for(int n=0;n<N;n++)
		scanf("%d", &liquid[n]);

	qsort((void *)liquid,(size_t)N,sizeof(int),compare);
	right=N-1;

	while(left<right)
	{
		if(abs(liquid[left]+liquid[right])<abs(neutrality[0]+neutrality[1]))
		{
			neutrality[0]=liquid[left];
			neutrality[1]=liquid[right];
		}

		if(liquid[left]+liquid[right]==0)
			break;
		else if(liquid[left]+liquid[right]<0)
			left++;
		else
			right--;
	}

	printf("%d %d\n", neutrality[0], neutrality[1]);
	free(liquid);
	return 0;
}