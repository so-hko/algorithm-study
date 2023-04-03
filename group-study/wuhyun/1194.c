#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

int main(void)
{
	int N, M, *queue=NULL, front=0, rear=0, count=0, x, y, state, move[4][2]={{-1,0},{0,-1},{0,1},{1,0}};
	char **map=NULL;
	bool ***visited=NULL, found=false;

	scanf("%d%d", &N, &M);
	map=(char **)malloc(N*sizeof(char *));
	queue=(int *)malloc(64*N*M*sizeof(int));
	visited=(bool ***)malloc(N*sizeof(bool **));
	for(int n=0;n<N;n++)
	{
		map[n]=(char *)calloc(M+1,sizeof(char));
		visited[n]=(bool **)malloc(M*sizeof(bool *));
		scanf("%s", map[n]);
		for(int m=0;m<M;m++)
		{
			visited[n][m]=(bool *)calloc(64,sizeof(bool));
			if(map[n][m]=='0')
			{
				queue[rear++]=n<<16|m<<8;
				map[n][m]='.';
				visited[n][m][0]=true;
			}
		}
	}

	while(front<rear)
	{
		int previous_rear=rear;

		while(front<previous_rear)
		{
			state=queue[front]&255;
			queue[front]>>=8;
			x=queue[front]&255;
			y=queue[front]>>8;

			if(map[y][x]=='1')
			{
				found=true;
				break;
			}

			for(int i=0;i<4;i++)
				if(y+move[i][0]>=0 && y+move[i][0]<N && x+move[i][1]>=0 && x+move[i][1]<M)
					switch(map[y+move[i][0]][x+move[i][1]])
					{
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							if(!visited[y+move[i][0]][x+move[i][1]][state|1<<(map[y+move[i][0]][x+move[i][1]]-'a')])
							{
								visited[y+move[i][0]][x+move[i][1]][state|1<<(map[y+move[i][0]][x+move[i][1]]-'a')]=true;
								queue[rear++]=((y+move[i][0])<<16)|((x+move[i][1])<<8)|(state|(1<<(map[y+move[i][0]][x+move[i][1]]-'a')));
							}
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							if((state&(1<<(map[y+move[i][0]][x+move[i][1]]-'A')))!=0 && !visited[y+move[i][0]][x+move[i][1]][state])
							{
								visited[y+move[i][0]][x+move[i][1]][state]=true;
								queue[rear++]=((y+move[i][0])<<16)|((x+move[i][1])<<8)|state;
							}
							break;
						case '.':
							if(!visited[y+move[i][0]][x+move[i][1]][state])
							{
								visited[y+move[i][0]][x+move[i][1]][state]=true;
								queue[rear++]=((y+move[i][0])<<16)|((x+move[i][1])<<8)|state;
							}
							break;
						case '1':
							queue[rear++]=((y+move[i][0])<<16)|((x+move[i][1])<<8)|state;
							break;
						default:
							break;
					}
			front++;
		}
		if(found)
			break;
		count++;
	}

	printf("%d\n", found?count:-1);
	for(int n=0;n<N;n++)
	{
		for(int m=0;m<M;m++)
			free(visited[n][m]);
		free(visited[n]);
		free(map[n]);
	}
	free(visited);
	free(map);
	free(queue);
	return 0;
}