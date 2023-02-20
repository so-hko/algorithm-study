#include<stdio.h>
#include<stdlib.h>

int max3(int x,int y,int z)
{
	if(x>=y && x>=z)
		return x;
	else if(y>=x && y>=z)
		return y;
	else
		return z;
}

int main(void)
{
	int N, M, **room=NULL;

	scanf("%d%d", &N, &M);
	room=(int **)malloc((N+1)*sizeof(int *));
	for(int n=0;n<=N;n++)
		room[n]=(int *)calloc(M+1,sizeof(int));

	for(int r=1;r<=N;r++)
		for(int c=1;c<=M;c++)
		{
			scanf("%d", &room[r][c]);
			room[r][c]+=max3(room[r-1][c],room[r][c-1],room[r-1][c-1]);
		}

	printf("%d\n", room[N][M]);
	for(int n=0;n<=N;n++)
		free(room[n]);
	free(room);
	return 0;
}