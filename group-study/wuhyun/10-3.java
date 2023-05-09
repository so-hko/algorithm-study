class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int size=problems.length, alpMax=0, copMax=0, alpPlusMax=0, copPlusMax=0, min=1000000000, max;
        
        for(int i=0;i<size;i++) {
            if(problems[i][0]>alpMax)
                alpMax=problems[i][0];
            if(problems[i][1]>copMax)
                copMax=problems[i][1];
        }
        for(int i=0;i<size;i++) {
            if(alpPlusMax<(alpMax+problems[i][2]))
                alpPlusMax=(alpMax+problems[i][2]);
            if(copPlusMax<(copMax+problems[i][3]))
                copPlusMax=(copMax+problems[i][3]);
        }

        max=2*(alpPlusMax+copPlusMax+3);
        int[][] knapsack=new int[max+1][max+1];
        for(int i=0;i<=max;i++)
            for(int j=0;j<=max;j++) {
                if((i<=alp) && (j<=cop))
                    knapsack[i][j]=0;
                else
                    knapsack[i][j]=100000000;

                if((i>0) && (knapsack[i][j]>(knapsack[i-1][j]+1)))
                    knapsack[i][j]=(knapsack[i-1][j]+1);
                if((j>0) && (knapsack[i][j]>(knapsack[i][j-1]+1)))
                    knapsack[i][j]=(knapsack[i][j-1]+1);
                
                for(int k=0;k<size;k++)
                    if(((i-problems[k][2])>=problems[k][0]) && ((j-problems[k][3])>=problems[k][1]) && (knapsack[i][j]>(knapsack[i-problems[k][2]][j-problems[k][3]]+problems[k][4])))
                        knapsack[i][j]=(knapsack[i-problems[k][2]][j-problems[k][3]]+problems[k][4]);
            }
        
        for(int i=alpMax;i<=max;i++)
            for(int j=copMax;j<=max;j++)
                if(knapsack[i][j]<min)
                    min=knapsack[i][j];
        
        return min;
    }
}