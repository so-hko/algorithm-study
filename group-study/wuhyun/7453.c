#include<stdio.h>
#include<stdlib.h>

int compare(const void *x,const void *y)
{
	return *(int *)x>*(int *)y?1:*(int *)x==*(int *)y?0:-1;
}

int main(void)
{
	int n, *A=NULL, *B=NULL, *C=NULL, *D=NULL, *AB=NULL, *CD=NULL;
	long long sum=0;

	scanf("%d", &n);
	A=(int *)malloc(n*sizeof(int));
	B=(int *)malloc(n*sizeof(int));
	C=(int *)malloc(n*sizeof(int));
	D=(int *)malloc(n*sizeof(int));
	AB=(int *)malloc(n*n*sizeof(int));
	CD=(int *)malloc(n*n*sizeof(int));

	for(int i=0;i<n;i++)
		scanf("%d%d%d%d", &A[i], &B[i], &C[i], &D[i]);

	for(int i=0;i<n;i++)
		for(int j=0;j<n;j++)
		{
			AB[i*n+j]=A[i]+B[j];
			CD[i*n+j]=C[i]+D[j];
		}

	qsort((void *)CD,(size_t)n*n,sizeof(int),compare);

	for(int i=0;i<n*n;i++)
	{
		int left=0, right=n*n-1, mid=(left+right)/2;

		while(left<right)
		{
			if(AB[i]+CD[mid]==0)
			{
				while(left<n*n && AB[i]+CD[left]<0)
					left++;
				while(right>=0 && AB[i]+CD[right]>0)
					right--;
				sum+=right-left;
				break;
			}
			else if(AB[i]+CD[mid]>0)
				right=mid-1;
			else
				left=mid+1;
			mid=(left+right)/2;
		}

		if(AB[i]+CD[mid]==0)
			sum++;
	}

	printf("%lld\n", sum);
	free(AB);
	free(CD);
	free(A);
	free(B);
	free(C);
	free(D);
	return 0;
}