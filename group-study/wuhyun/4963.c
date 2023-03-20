#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int w, h, **map=NULL, *queue=NULL, front, rear, count, move[8][2]={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

	scanf("%d%d", &w, &h);

	while(w!=0 || h!=0)
	{
		map=(int **)malloc(h*sizeof(int *));
		for(int i=0;i<h;i++)
		{
			map[i]=(int *)malloc(w*sizeof(int));
			for(int j=0;j<w;j++)
				scanf("%d", &map[i][j]);
		}
		front=rear=count=0;
		queue=(int *)malloc(w*h*sizeof(int));

		for(int i=0;i<h;i++)
			for(int j=0;j<w;j++)
				if(map[i][j]==1)
				{
					queue[rear++]=100*i+j;
					map[i][j]=0;
					while(front<rear)
					{
						int y=queue[front]/100, x=queue[front]%100;

						for(int k=0;k<8;k++)
							if(y+move[k][0]>=0 && y+move[k][0]<h && x+move[k][1]>=0 && x+move[k][1]<w && map[y+move[k][0]][x+move[k][1]]==1)
							{
								map[y+move[k][0]][x+move[k][1]]=0;
								queue[rear++]=100*(y+move[k][0])+x+move[k][1];
							}

						front++;
					}

					count++;
				}

		printf("%d\n", count);
		for(int i=0;i<h;i++)
			free(map[i]);
		free(map);
		free(queue);
		scanf("%d%d", &w, &h);
	}

	return 0;
}