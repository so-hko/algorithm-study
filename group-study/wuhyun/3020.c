#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int N, H, *up=NULL, *down=NULL, obstacle, min=200000, min_count=1, stalagmite, stalactite=0;

	scanf("%d%d", &N, &H);
	up=(int *)calloc(H+1,sizeof(int));
	down=(int *)calloc(H+1,sizeof(int));

	stalagmite=N/2;
	for(int n=0;n<N;n++)
	{
		scanf("%d", &obstacle);
		if(n%2==0)
			down[obstacle]++;
		else
			up[obstacle]++;
	}

	for(int h=0;h<H;h++)
	{
		stalagmite-=down[h];
		stalactite+=up[H-h];

		if(stalagmite+stalactite<min)
		{
			min=stalagmite+stalactite;
			min_count=1;
		}
		else if(stalagmite+stalactite==min)
			min_count++;
	}

	printf("%d %d\n", min, min_count);
	free(up);
	free(down);
	return 0;
}