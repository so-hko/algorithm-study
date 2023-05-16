#include<stdio.h>
#include<malloc.h>
#include<stdbool.h>

int main(void)
{
	int N, M, *m=NULL, *c=NULL, *memory=NULL;
	bool **deactivated=NULL;

	scanf("%d%d", &N, &M);
	m=(int *)malloc(N*sizeof(int));
	c=(int *)malloc(N*sizeof(int));
	memory=(int *)malloc((M+1)*sizeof(int));
	deactivated=(bool **)malloc((M+1)*sizeof(bool *));

	for(int n=0;n<N;n++)
		scanf("%d", &m[n]);
	for(int n=0;n<N;n++)
		scanf("%d", &c[n]);

	for(int i=0;i<N;i++)
		for(int j=i+1;j<N;j++)
			if(c[i]*m[j]>c[j]*m[i])
			{
				int temp=c[i];
				c[i]=c[j];
				c[j]=temp;
				temp=m[i];
				m[i]=m[j];
				m[j]=temp;
			}

	memory[0]=0;
	deactivated[0]=(bool *)calloc(N,sizeof(bool));
	for(int i=1;i<=M;i++)
	{
		memory[i]=1000000000;
		deactivated[i]=(bool *)calloc(N,sizeof(bool));
		for(int j=0;j<N;j++)
			if(i-m[j]>=0 && !deactivated[i-m[j]][j] && memory[i]>memory[i-m[j]]+c[j])
			{
				for(int k=0;k<N;k++)
					deactivated[i][k]=deactivated[i-m[j]][k];
				deactivated[i][j]=true;
				memory[i]=memory[i-m[j]]+c[j];
			}
	}

	printf("%d\n", memory[M]);
	for(int i=0;i<M;i++)
		free(deactivated[i]);
	free(deactivated);
	free(memory);
	free(c);
	free(m);
	return 0;
}