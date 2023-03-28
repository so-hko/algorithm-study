#include<stdio.h>
#include<stdlib.h>

int N;

int simulate(int **board,int count)
{
	int max=0;

	if(count==5)
	{
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				max=board[i][j]>max?board[i][j]:max;

		return max;
	}

	int **next_board=malloc(N*sizeof(int *)), *stack=malloc(N*sizeof(int)), top, top2, state[4];
	for(int n=0;n<N;n++)
		next_board[n]=(int *)malloc(N*sizeof(int));

	for(int i=0;i<N;i++)
	{
		top=top2=0;

		for(int j=0;j<N;j++)
			if(board[i][j]!=0)
				stack[top++]=board[i][j];

		for(int j=1;j<top;j++)
			if(stack[j]==stack[j-1])
			{
				stack[j-1]<<=1;
				stack[j]=0;
			}

		for(int j=0;j<top;j++)
			if(stack[j]!=0)
				next_board[i][top2++]=stack[j];
		for(int j=top2;j<N;j++)
			next_board[i][j]=0;
	}
	state[0]=simulate(next_board,count+1);

	for(int i=0;i<N;i++)
	{
		top=top2=0;

		for(int j=N-1;j>=0;j--)
			if(board[i][j]!=0)
				stack[top++]=board[i][j];

		for(int j=1;j<top;j++)
			if(stack[j]==stack[j-1])
			{
				stack[j-1]<<=1;
				stack[j]=0;
			}

		for(int j=0;j<top;j++)
			if(stack[j]!=0)
				next_board[i][N-1-top2++]=stack[j];
		for(int j=top2;j<N;j++)
			next_board[i][N-j-1]=0;
	}
	state[1]=simulate(next_board,count+1);

	for(int i=0;i<N;i++)
	{
		top=top2=0;

		for(int j=0;j<N;j++)
			if(board[j][i]!=0)
				stack[top++]=board[j][i];

		for(int j=1;j<top;j++)
			if(stack[j]==stack[j-1])
			{
				stack[j-1]<<=1;
				stack[j]=0;
			}

		for(int j=0;j<top;j++)
			if(stack[j]!=0)
				next_board[top2++][i]=stack[j];
		for(int j=top2;j<N;j++)
			next_board[j][i]=0;
	}
	state[2]=simulate(next_board,count+1);

	for(int i=0;i<N;i++)
	{
		top=top2=0;

		for(int j=N-1;j>=0;j--)
			if(board[j][i]!=0)
				stack[top++]=board[j][i];

		for(int j=1;j<top;j++)
			if(stack[j]==stack[j-1])
			{
				stack[j-1]<<=1;
				stack[j]=0;
			}

		for(int j=0;j<top;j++)
			if(stack[j]!=0)
				next_board[N-1-top2++][i]=stack[j];
		for(int j=top2;j<N;j++)
			next_board[N-j-1][i]=0;
	}
	state[3]=simulate(next_board,count+1);

	for(int i=0;i<4;i++)
		max=state[i]>max?state[i]:max;

	return max;
}

int main(void)
{
	int **board=NULL;

	scanf("%d", &N);
	board=(int **)malloc(N*sizeof(int *));
	for(int i=0;i<N;i++)
	{
		board[i]=(int *)malloc(N*sizeof(int));
		for(int j=0;j<N;j++)
			scanf("%d", &board[i][j]);
	}

	printf("%d\n", simulate(board,0));
	return 0;
}