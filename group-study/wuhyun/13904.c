#include<stdio.h>
#include<malloc.h>
#include<stdbool.h>

int main(void)
{
	int N, *d=NULL, *w=NULL, max=0, sum=0;
	bool *full=NULL;

	scanf("%d", &N);
	d=(int *)malloc(N*sizeof(int));
	w=(int *)malloc(N*sizeof(int));

	for(int n=0;n<N;n++)
	{
		scanf("%d%d", &d[n], &w[n]);
		max=d[n]>max?d[n]:max;
	}
	full=(bool *)calloc(max+1,sizeof(bool));

	for(int i=0;i<N;i++)
		for(int j=i+1;j<N;j++)
			if(w[i]<w[j] || w[i]==w[j]&&d[i]>d[j])
			{
				int temp=w[i];
				w[i]=w[j];
				w[j]=temp;
				temp=d[i];
				d[i]=d[j];
				d[j]=temp;
			}

	for(int i=0;i<N;i++)
		for(int j=d[i];j>0;j--)
			if(!full[j])
			{
				full[j]=true;
				sum+=w[i];
				break;
			}

	printf("%d\n", sum);
	free(full);
	free(w);
	free(d);
	return 0;
}