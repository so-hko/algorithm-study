#include<stdio.h>
#include<stdlib.h>

int abs(int x)
{
	return x>0?x:-x;
}

int compare(const void *x,const void *y)
{
	return *(int *)x>*(int *)y?1:*(int *)x==*(int *)y?0:-1;
}

int main(void)
{
	int N, *x=NULL, *y=NULL, *min_move=NULL;

	scanf("%d", &N);
	x=(int *)malloc(N*sizeof(int));
	y=(int *)malloc(N*sizeof(int));
	min_move=(int *)malloc((N+1)*sizeof(int));

	for(int n=0;n<N;n++)
	{
		scanf("%d%d", &x[n], &y[n]);
		min_move[n+1]=1000000000;
	}

	min_move[1]=0;
	for(int n=2;n<=N;n++)
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
			{
				int *distance=malloc(N*sizeof(int)), temp=0;

				for(int k=0;k<N;k++)
					distance[k]=abs(x[i]-x[k])+abs(y[j]-y[k]);

				qsort((void *)distance,(size_t)N,sizeof(int),compare);

				for(int k=0;k<n;k++)
					temp+=distance[k];
				free(distance);
				if(temp<min_move[n])
					min_move[n]=temp;
			}

	for(int n=1;n<=N;n++)
		printf("%d ", min_move[n]);
	free(min_move);
	free(y);
	free(x);
	return 0;
}