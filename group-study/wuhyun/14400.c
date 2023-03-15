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
	int n, *x=NULL, *y=NULL;
	long long sum=0;

	scanf("%d", &n);
	x=(int *)malloc(n*sizeof(int));
	y=(int *)malloc(n*sizeof(int));

	for(int i=0;i<n;i++)
		scanf("%d%d", &x[i], &y[i]);

	qsort((void *)x,(size_t)n,sizeof(int),compare);
	qsort((void *)y,(size_t)n,sizeof(int),compare);

	for(int i=0;i<n;i++)
		sum+=abs(x[i]-x[(n-1)/2])+abs(y[i]-y[(n-1)/2]);

	printf("%lld\n", sum);
	free(x);
	free(y);
	return 0;
}