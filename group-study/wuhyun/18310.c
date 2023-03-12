#include<stdio.h>
#include<stdlib.h>

int compare(const void *x,const void *y)
{
	return *(int *)x>*(int *)y?1:*(int *)x==*(int *)y?0:-1;
}

int main(void)
{
	int N, *house=NULL;

	scanf("%d", &N);
	house=(int *)malloc(N*sizeof(int));

	for(int n=0;n<N;n++)
		scanf("%d", &house[n]);

	qsort((void *)house,(size_t)N,sizeof(int),compare);

	printf("%d\n", house[(N-1)/2]);
	free(house);
	return 0;
}