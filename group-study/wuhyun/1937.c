#include<stdio.h>
#include<stdlib.h>

int n, **bamboo=NULL, move[4][2]={{-1,0},{0,-1},{0,1},{1,0}}, **check=NULL;

int simulate(int y,int x,int moved)
{
	int next[4]={0, }, max=moved;

	for(int i=0;i<4;i++)
	{
		if(y+move[i][0]>=0 && y+move[i][0]<n && x+move[i][1]>=0 && x+move[i][1]<n && bamboo[y+move[i][0]][x+move[i][1]]>bamboo[y][x])
			next[i]=check[y+move[i][0]][x+move[i][1]]>-1?moved+check[y+move[i][0]][x+move[i][1]]+1:simulate(y+move[i][0],x+move[i][1],moved+1);
		if(next[i]>max)
			max=next[i];
	}

	check[y][x]=max-moved;
	return max;
}

int main(void)
{
	int max=1, distance;

	scanf("%d", &n);
	bamboo=(int **)malloc(n*sizeof(int *));
	check=(int **)malloc(n*sizeof(int *));
	for(int i=0;i<n;i++)
	{
		bamboo[i]=(int *)malloc(n*sizeof(int));
		check[i]=(int *)malloc(n*sizeof(int));
		for(int j=0;j<n;j++)
		{
			scanf("%d", &bamboo[i][j]);
			check[i][j]=-1;
		}
	}

	for(int i=0;i<n;i++)
		for(int j=0;j<n;j++)
		{
			distance=simulate(i,j,1);
			if(distance>max)
				max=distance;
		}

	printf("%d\n", max);
	for(int i=0;i<n;i++)
	{
		free(bamboo[i]);
		free(check[i]);
	}
	free(bamboo);
	free(check);
	return 0;
}