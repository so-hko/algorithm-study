#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

int main(void)
{
	int M, fish_move[8][2]={{0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}}, shark_move[4][2]={{-1,0},{0,-1},{1,0},{0,1}}, S, x, y, d, shark_x, shark_y, eat, max_eat, state, smell[5][5]={0, }, fish_count[5][5][3]={0, }, ***fish=NULL, sum=0;
	bool moved;
	int visited[5][5]={0, };

	scanf("%d%d", &M, &S);
	fish=(int ***)malloc(26*sizeof(int **));
	for(int i=1;i<26;i++)
	{
		fish[i]=(int **)malloc(3*sizeof(int *));
		for(int j=0;j<3;j++)
			fish[i][j]=(int *)malloc(1000000*sizeof(int));
	}
	for(int m=0;m<M;m++)
	{
		scanf("%d%d%d", &y, &x, &d);
		fish[y*5+x][0][fish_count[y][x][0]++]=d-1;
	}
	scanf("%d%d", &shark_y, &shark_x);

	for(int s=0;s<S;s++)
	{
		int current=s%3, next=(s+1)%3, previous=(s+2)%3;
		for(int i=1;i<5;i++)
			for(int j=1;j<5;j++)
			{
				fish_count[i][j][previous]=fish_count[i][j][next]=fish_count[i][j][current];
				for(int k=0;k<fish_count[i][j][current];k++)
					fish[i*5+j][previous][k]=fish[i*5+j][next][k]=fish[i*5+j][current][k];
				fish_count[i][j][current]=0;
			}

		for(int i=1;i<5;i++)
			for(int j=1;j<5;j++)
				for(int k=0;k<fish_count[i][j][previous];k++)
				{
					moved=false;
					y=i;
					x=j;
					d=fish[i*5+j][previous][k];
					for(int l=0;l<8;l++)
					{
						y+=fish_move[d][0];
						x+=fish_move[d][1];
						if(y>0 && y<5 && x>0 && x<5 && (y!=shark_y || x!=shark_x) && (smell[y][x]&(1<<previous))==0 && (smell[y][x]&(1<<next))==0)
						{
							moved=true;
							fish[y*5+x][current][fish_count[y][x][current]++]=d;
							break;
						}
						else
						{
							y-=fish_move[d][0];
							x-=fish_move[d][1];
							d=(d+7)&7;
						}
					}
					if(!moved)
						fish[i*5+j][current][fish_count[i][j][current]++]=d;
				}

		max_eat=-1;
		state=0;
		for(int i=0;i<4;i++)
		{
			shark_y+=shark_move[i][0];
			shark_x+=shark_move[i][1];
			if(shark_y>0 && shark_y<5 && shark_x>0 && shark_x<5)
			{
				visited[shark_y][shark_x]|=1;
				for(int j=0;j<4;j++)
				{
					shark_y+=shark_move[j][0];
					shark_x+=shark_move[j][1];
					if(shark_y>0 && shark_y<5 && shark_x>0 && shark_x<5)
					{
						visited[shark_y][shark_x]|=2;
						for(int k=0;k<4;k++)
						{
							shark_y+=shark_move[k][0];
							shark_x+=shark_move[k][1];
							if(shark_y>0 && shark_y<5 && shark_x>0 && shark_x<5)
							{
								visited[shark_y][shark_x]|=4;
								eat=0;
								for(int sy=1;sy<5;sy++)
									for(int sx=1;sx<5;sx++)
										if(visited[sy][sx]>0)
											eat+=fish_count[sy][sx][current];
								if(eat>max_eat)
								{
									max_eat=eat;
									state=(shark_y-shark_move[k][0]-shark_move[j][0])<<15|(shark_x-shark_move[k][1]-shark_move[j][1])<<12|(shark_y-shark_move[k][0])<<9|(shark_x-shark_move[k][1])<<6|shark_y<<3|shark_x;
								}
								visited[shark_y][shark_x]^=4;
							}
							shark_y-=shark_move[k][0];
							shark_x-=shark_move[k][1];
						}
						visited[shark_y][shark_x]^=2;
					}
					shark_y-=shark_move[j][0];
					shark_x-=shark_move[j][1];
				}
				visited[shark_y][shark_x]^=1;
			}
			shark_y-=shark_move[i][0];
			shark_x-=shark_move[i][1];
		}
		shark_y=(state&56)>>3;
		shark_x=state&7;
		for(int i=0;i<3;i++)
		{
			y=(state&56)>>3;
			x=state&7;

			if(fish_count[y][x][current]>0)
			{
				fish_count[y][x][current]=0;
				smell[y][x]|=1<<current;
			}

			state>>=6;
		}
		for(int i=1;i<5;i++)
			for(int j=1;j<5;j++)
				if(smell[i][j]&(1<<next))
					smell[i][j]^=1<<next;

		for(int i=1;i<5;i++)
			for(int j=1;j<5;j++)
				for(int k=0;k<fish_count[i][j][current];k++)
					fish[i*5+j][next][fish_count[i][j][next]++]=fish[i*5+j][current][k];
	}

	for(int i=1;i<5;i++)
		for(int j=1;j<5;j++)
			sum+=fish_count[i][j][S%3];
	printf("%d\n", sum);
	for(int i=1;i<26;i++)
	{
		for(int j=0;j<3;j++)
			free(fish[i][j]);
		free(fish[i]);
	}
	free(fish);
	return 0;
}
