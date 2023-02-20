#include<stdio.h>
#include<stdlib.h>

int max2(int x,int y)
{
	return x>y?x:y;
}

int main(void)
{
	int n, **triangle=NULL, max=0;

	scanf("%d", &n);
	triangle=(int **)malloc((n+1)*sizeof(int *));
	for(int i=0;i<=n;i++)
		triangle[i]=(int *)calloc(i+2,sizeof(int));

	for(int i=1;i<=n;i++)
		for(int j=1;j<=i;j++)
		{
			scanf("%d", &triangle[i][j]);
			triangle[i][j]+=max2(triangle[i-1][j],triangle[i-1][j-1]);
		}

	for(int i=1;i<=n;i++)
		max=max2(triangle[n][i],max);

	printf("%d\n", max);
	for(int i=0;i<=n;i++)
		free(triangle[i]);
	free(triangle);
	return 0;
}