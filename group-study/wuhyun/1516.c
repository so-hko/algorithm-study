#include<stdio.h>
#include<malloc.h>

int main(void)
{
	int N, *primitive_count=NULL, *definitive_count=NULL, **definitive=NULL, **primitive, *queue=NULL, front=0, rear=0, *construct_time=NULL, data, *left=NULL;

	scanf("%d", &N);
	primitive_count=(int *)calloc(N+1,sizeof(int));
	definitive_count=(int *)calloc(N+1,sizeof(int));
	primitive=(int **)malloc((N+1)*sizeof(int *));
	definitive=(int **)malloc((N+1)*sizeof(int *));
	queue=(int *)malloc(N*sizeof(int));
	construct_time=(int *)malloc((N+1)*sizeof(int));
	left=(int *)malloc((N+1)*sizeof(int));

	for(int n=1;n<=N;n++)
		definitive[n]=(int *)malloc(N*sizeof(int));

	for(int n=1;n<=N;n++)
	{
		scanf("%d", &construct_time[n]);
		primitive[n]=(int *)malloc(N*sizeof(int));
		for(int i=0;i<N;i++)
		{
			scanf("%d", &data);
			if(data==-1)
				break;
			primitive[n][primitive_count[n]++]=data;
			definitive[data][definitive_count[data]++]=n;
		}

		if(primitive_count[n]==0)
			queue[rear++]=n;
		left[n]=primitive_count[n];
	}

	while(front<rear)
	{
		int required=0;

		for(int i=0;i<primitive_count[queue[front]];i++)
			required=construct_time[primitive[queue[front]][i]]>required?construct_time[primitive[queue[front]][i]]:required;

		for(int i=0;i<definitive_count[queue[front]];i++)
		{
			left[definitive[queue[front]][i]]--;
			if(left[definitive[queue[front]][i]]==0)
				queue[rear++]=definitive[queue[front]][i];
		}

		construct_time[queue[front]]+=required;
		front++;
	}

	for(int n=1;n<=N;n++)
	{
		printf("%d\n", construct_time[n]);
		free(primitive[n]);
		free(definitive[n]);
	}
	free(primitive);
	free(definitive);
	free(primitive_count);
	free(definitive_count);
	free(queue);
	free(left);
	free(construct_time);
	return 0;
}