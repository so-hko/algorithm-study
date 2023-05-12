#include<stdio.h>
#include<malloc.h>

int size=1, count=1, **deserter=NULL;
long long *pq=NULL;

long long get(void)
{
	int index=1, next, right, left;
	long long value=pq[1], temp;
	pq[1]=pq[--size];

	while(index<size)
	{
		left=index<<1;
		right=index<<1|1;
		next=size>right?(pq[right]>>20)<(pq[left]>>20)?right:left:size==right?left:index;

		if(index==next)
			break;
		else
		{
			if((pq[index]>>20)>(pq[next]>>20))
			{
				temp=pq[index];
				pq[index]=pq[next];
				pq[next]=temp;
				index=next;
			}
			else
				break;
		}
	}

	return value;
}

void add(long long value)
{
	int index=size, next;
	long long temp;
	pq[size++]=value;

	while(index>1)
	{
		next=index>>1;
		if((pq[index]>>20)<(pq[next]>>20))
		{
			temp=pq[index];
			pq[index]=pq[next];
			pq[next]=temp;
			index=next;
		}
		else
			break;
	}
}

int deserter_pursuit(int state,int current)
{
	if(state==(1<<count)-1)
		return deserter[current][0];

	int min=1<<30, rotate;

	for(int i=1;i<count;i++)
		if((state&(1<<i))==0)
		{
			rotate=deserter[current][i]+deserter_pursuit(state|(1<<i),i);
			min=rotate<min?rotate:min;
		}

	return min;
}

int main(void)
{
	int N, **map=NULL, soilder[6], move[4][2]={{-1,0},{0,-1},{0,1},{1,0}}, **distance=NULL;

	scanf("%d", &N);
	pq=(long long *)malloc(N*N*10*sizeof(long long));
	map=(int **)malloc(N*sizeof(int *));
	for(int i=0;i<N;i++)
	{
		map[i]=(int *)malloc(N*sizeof(int));
		for(int j=0;j<N;j++)
		{
			scanf("%d", &map[i][j]);
			if(map[i][j]==-1)
			{
				map[i][j]=0;
				soilder[0]=i<<10|j;
			}
			else if(map[i][j]==0)
				soilder[count++]=i<<10|j;
		}
	}

	if(count==1)
	{
		printf("0\n");
		for(int n=0;n<N;n++)
			free(map[n]);
		free(map);
		return 0;
	}

	distance=(int **)malloc(N*sizeof(int *));
	for(int n=0;n<N;n++)
		distance[n]=(int *)malloc(N*sizeof(int));
	deserter=(int **)malloc(count*sizeof(int *));

	for(int i=0;i<count-1;i++)
	{
		for(int j=0;j<N;j++)
			for(int k=0;k<N;k++)
				distance[j][k]=536870912;

		distance[soilder[i]>>10][soilder[i]&1023]=0;
		size=1;
		add((long long)soilder[i]);

		while(size>1)
		{
			long long next=get();
			int x=next&1023;
			next>>=10;
			int y=next&1023;
			int value=next>>10;

			if(value==distance[y][x])
				for(int j=0;j<4;j++)
				{
					y+=move[j][0];
					x+=move[j][1];
					if(y>=0 && y<N && x>=0 && x<N && distance[y][x]>value+map[y][x])
					{
						distance[y][x]=value+map[y][x];
						add((long long)distance[y][x]<<20|y<<10|x);
					}
					y-=move[j][0];
					x-=move[j][1];
				}
		}

		deserter[i]=(int *)malloc(count*sizeof(int));
		for(int j=0;j<count;j++)
			deserter[i][j]=distance[soilder[j]>>10][soilder[j]&1023];
	}
	deserter[count-1]=(int *)calloc(count,sizeof(int));
	for(int i=0;i<count-1;i++)
		deserter[count-1][i]=deserter[i][count-1];

	for(int n=0;n<N;n++)
	{
		free(map[n]);
		free(distance[n]);
	}
	free(distance);
	free(map);
	printf("%d\n", deserter_pursuit(1,0));
	for(int i=0;i<count;i++)
		free(deserter[i]);
	free(deserter);
	free(pq);
	return 0;
}
