#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int ***tomato=NULL, M, N, H, days=0, count=0, previous_count=0, empty=0, *queue=NULL, front=0, rear=0, move[6][3]={{-1,0,0},{1,0,0},{0,-1,0},{0,1,0},{0,0,-1},{0,0,1}};

	scanf("%d%d%d", &H, &M, &N);
	queue=(int *)malloc(N*M*H*sizeof(int));
	tomato=(int ***)malloc(N*sizeof(int **));
	for(int i=0;i<N;i++)
	{
		tomato[i]=(int **)malloc(M*sizeof(int *));
		for(int j=0;j<M;j++)
		{
			tomato[i][j]=(int *)malloc(H*sizeof(int));

			for(int k=0;k<H;k++)
			{
				scanf("%d", &tomato[i][j][k]);
				if(tomato[i][j][k]==1)
				{
					count++;
					queue[rear++]=10000*i+100*j+k;
				}
				else if(tomato[i][j][k]==-1)
					empty++;
			}
		}
	}

	while(previous_count<count && N*M*H-empty>count)
	{
		int previous_rear=rear;
		previous_count=count;

		while(front<previous_rear)
		{
			for(int i=0;i<6;i++)
			{
				int x=queue[front]/10000+move[i][0], y=queue[front]/100%100+move[i][1], z=queue[front]%100+move[i][2];

				if(x>=0 && x<N && y>=0 && y<M &&  z>=0 && z<H && tomato[x][y][z]==0)
				{
					queue[rear++]=10000*x+100*y+z;
					tomato[x][y][z]=1;
					count++;
				}
			}
			front++;
		}

		if(count==previous_count && N*M*H-empty!=count)
		{
			days=-1;
			break;
		}
		days++;
	}

	printf("%d\n", count==0?-1:days);
	for(int i=0;i<N;i++)
	{
		for(int j=0;j<M;j++)
			free(tomato[i][j]);
		free(tomato[i]);
	}
	free(tomato);
	free(queue);
	return 0;
}