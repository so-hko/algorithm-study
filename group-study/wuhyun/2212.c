#include<stdio.h>
#include<stdlib.h>

int compare(const void *x,const void *y)
{
	return *(int *)x>*(int *)y?1:*(int *)x==*(int *)y?0:-1;
}

int main(void)
{
	int N, K, *sensor=NULL, *difference=NULL, sum=0;

	scanf("%d", &N);
	scanf("%d", &K);
	sensor=(int *)malloc(N*sizeof(int));
	difference=(int *)malloc((N-1)*sizeof(int));

	for(int n=0;n<N;n++)
		scanf("%d", &sensor[n]);
	qsort((void *)sensor,(size_t)N,sizeof(int),compare);

	for(int n=0;n<N-1;n++)
		difference[n]=sensor[n+1]-sensor[n];
	qsort((void *)difference,(size_t)N-1,sizeof(int),compare);

	for(int k=0;k<N-K;k++)
		sum+=difference[k];

	printf("%d\n", sum);
	free(difference);
	free(sensor);
	return 0;
}