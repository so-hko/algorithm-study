#include<stdio.h>
#include<stdlib.h>

int N, M, **lab=NULL;

int simulate()
{
	int queue[64], front=0, rear=0, **test=(int **)malloc(N*sizeof(int *)), count=0, move[4][2]={{-1,0},{1,0},{0,-1},{0,1}};

	for(int n=0;n<N;n++)
	{
		test[n]=(int *)malloc(M*sizeof(int));

		for(int m=0;m<M;m++)
		{
			test[n][m]=lab[n][m];
			if(test[n][m]==2)
				queue[rear++]=10*n+m;
			else if(test[n][m]==0)
				count++;
		}
	}

	while(front<rear)
	{
		int previous_rear=rear;

		while(front<previous_rear)
		{
			for(int i=0;i<4;i++)
			{
				int x=queue[front]/10+move[i][0],  y=queue[front]%10+move[i][1];

				if(x>=0 && x<N && y>=0 && y<M && test[x][y]==0)
				{
					count--;
					queue[rear++]=10*x+y;
					test[x][y]=2;
				}
			}
			front++;
		}
	}

	for(int n=0;n<N;n++)
		free(test[n]);
	free(test);
	return count;
}

int main(void)
{
	int max=0;

	scanf("%d%d", &N, &M);
	lab=(int **)malloc(N*sizeof(int *));
	for(int n=0;n<N;n++)
	{
		lab[n]=(int *)malloc(M*sizeof(int));

		for(int m=0;m<M;m++)
			scanf("%d", &lab[n][m]);
	}

	for(int i=0;i<N*M;i++)
		if(lab[i/M][i%M]==0)
		{
			lab[i/M][i%M]=1;
			for(int j=i+1;j<N*M;j++)
				if(lab[j/M][j%M]==0)
				{
					lab[j/M][j%M]=1;
					for(int k=j+1;k<N*M;k++)
						if(lab[k/M][k%M]==0)
						{
							lab[k/M][k%M]=1;
							int temp=simulate();
							if(temp>max)
								max=temp;
							lab[k/M][k%M]=0;
						}
					lab[j/M][j%M]=0;
				}
			lab[i/M][i%M]=0;
		}

	printf("%d\n", max);
	for(int n=0;n<N;n++)
		free(lab[n]);
	free(lab);
	return 0;
}