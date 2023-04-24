#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int **tomato=NULL, M, N, days=0, count=0, previous_count=0, empty=0, *queue=NULL, front=0, rear=0, move[4][2]={{-1,0},{0,-1},{0,1},{1,0}};

	scanf("%d%d", &M, &N);
	queue=(int *)malloc(N*M*sizeof(int));
	tomato=(int **)malloc(N*sizeof(int *));
	for(int i=0;i<N;i++)
	{
		tomato[i]=(int *)malloc(M*sizeof(int));
		for(int j=0;j<M;j++)
		{
			scanf("%d", &tomato[i][j]);
			if(tomato[i][j]==1)
			{
				count++;
				queue[rear++]=1000*i+j;
			}
			else if(tomato[i][j]==-1)
				empty++;
		}
	}

	while(previous_count<count && N*M-empty>count)
	{
		int previous_rear=rear;
		previous_count=count;

		while(front<previous_rear)
		{
			for(int i=0;i<4;i++)
			{
				int x=queue[front]/1000+move[i][0], y=queue[front]%1000+move[i][1];

				if(x>=0 && x<N && y>=0 && y<M && tomato[x][y]==0)
				{
					queue[rear++]=1000*x+y;
					tomato[x][y]=1;
					count++;
				}
			}
			front++;
		}

		if(count==previous_count && N*M-empty!=count)
		{
			days=-1;
			break;
		}
		days++;
	}

	printf("%d\n", count==0?-1:days);
	for(int i=0;i<N;i++)
		free(tomato[i]);
	free(tomato);
	free(queue);
	return 0;
}