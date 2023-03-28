#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

int abs(int x)
{
	return x<0?-x:x;
}

int main(void)
{
	int N, **country=NULL, L, R, move[4][2]={{-1,0},{0,-1},{0,1},{1,0}}, count=0;

	scanf("%d%d%d", &N, &L, &R);
	country=(int **)malloc(N*sizeof(int *));
	for(int i=0;i<N;i++)
	{
		country[i]=(int *)malloc(N*sizeof(int));
		for(int j=0;j<N;j++)
			scanf("%d", &country[i][j]);
	}

	while(++count)
	{
		bool **visited=(bool **)malloc(N*sizeof(bool *)), changed=false;
		for(int n=0;n<N;n++)
			visited[n]=(bool *)calloc(N,sizeof(bool));

		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				if(!visited[i][j])
				{
					int *queue=(int *)malloc(N*N*sizeof(int)), front=0, rear=0, sum=0;
					queue[rear++]=100*i+j;
					visited[i][j]=true;

					while(front<rear)
					{
						int x=queue[front]/100, y=queue[front]%100;

						sum+=country[x][y];
						for(int k=0;k<4;k++)
							if(x+move[k][0]>=0 && x+move[k][0]<N && y+move[k][1]>=0 && y+move[k][1]<N && !visited[x+move[k][0]][y+move[k][1]] && abs(country[x][y]-country[x+move[k][0]][y+move[k][1]])>=L && abs(country[x][y]-country[x+move[k][0]][y+move[k][1]])<=R)
							{
								visited[x+move[k][0]][y+move[k][1]]=true;
								queue[rear++]=100*(x+move[k][0])+y+move[k][1];
							}
						front++;
					}

					if(rear>1)
						changed=true;
					sum/=rear;
					for(int k=0;k<rear;k++)
						country[queue[k]/100][queue[k]%100]=sum;
					free(queue);
				}


		for(int n=0;n<N;n++)
			free(visited[n]);
		free(visited);
		if(!changed)
			break;
	}

	printf("%d\n", count-1);
	for(int n=0;n<N;n++)
		free(country[n]);
	free(country);
	return 0;
}