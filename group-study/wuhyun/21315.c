#include<stdio.h>

int main(void)
{
	int N, primary_K=1, secondary_K=1, card, index, initial;

	scanf("%d", &N);
	scanf("%d", &initial);

	for(int i=1;i<N;i++)
	{
		scanf("%d", &card);
		if(card==N)
		{
			index=i;
			break;
		}
	}

	while(1<<primary_K<N-initial)
		primary_K++;
	while(1<<secondary_K<index)
		secondary_K++;

	printf("%d %d\n", primary_K, secondary_K);
	return 0;
}
