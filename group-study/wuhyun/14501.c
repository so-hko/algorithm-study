#include<stdio.h>
#include<stdlib.h>

int counsel(int *T,int *P,int N,int current)
{
	int now=0, next=0;

	if(current>=N)
		return 0;

	if(current+T[current]<=N)
		now=P[current]+counsel(T,P,N,current+T[current]);
	next=counsel(T,P,N,current+1);

	return now>next?now:next;
}

int main(void)
{
	int N, *T=NULL, *P=NULL, max=0;

	scanf("%d", &N);
	T=(int *)malloc(N*sizeof(int));
	P=(int *)malloc(N*sizeof(int));

	for(int n=0;n<N;n++)
		scanf("%d %d", &T[n], &P[n]);

	printf("%d\n", counsel(T,P,N,0));
	free(P);
	free(T);
	return 0;
}
