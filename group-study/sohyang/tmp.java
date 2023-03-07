import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      int n = scan.nextInt();
      List<Integer> answerList = new ArrayList<>();
      List<Integer> list = new ArrayList<>();
      List<Integer> tmpList = new ArrayList<>();
      
      //(ㅎ)[1,2,3,4,5,...,n]로 초기화하는 부분
      for (int i=0;i<n;i++) {
         answerList.add(scan.nextInt());
         list.add(i+1);
      }
      
      int range = (int) Math.floor(Math.sqrt(n)); // n은 5일때 range는 2 n 1000이면? 9
      boolean flag = true;
      for (int kk1=1;kk1<=range;kk1++) {
         for(int kk2=1;kk2<=range;kk2++) {
            int k=kk1;
            
            System.out.println("<<List>>");
            System.out.println(list);
            // 첫번째
            int powK = (int) Math.pow(2, k);   //powK는 k가 1일때, 2 / powK는 k가 2일때, 4
            System.out.println(powK);
            
            //(ㅎ)이부분 왜 실행하는지 도무지 모르겠다
            if(powK >= n) {
               continue;
            }
            for (int fi=0;fi<powK;fi++) { //뒤에서 powK개 만큼 빼온다.
               int val = list.get(n-1-fi);   //가장 뒤에 것을 가져와 저장하고
               list.remove(n-1-fi);   //리스트에서 제거한 뒤
               tmpList.add(0, val);      //임시 리스트에 넣는다.
               System.out.println(list);
            }
            System.out.println("tmpList: "+tmpList);
            // 이후 i 단계   j는 임시 list(직전의 올린 카드)의 크기
            for (int j = powK;j>0;j=j/2) {
               for (int i=j/2-1;i>=0;i--) {   //직전의 올린 카드 중 밑에서 2^(k-i+1)을 제외한 카드를 손에서 버리고 list에 넣는다.
                  int tmpVal = tmpList.get(i);
                  tmpList.remove(i);
                  list.add(0, tmpVal);
               }
            }
            list.add(0, tmpList.get(0));
            tmpList.clear();
            System.out.println(">>result<<");
            System.out.println(list+"\n");
            
            k=kk2;
            
            System.out.println("<<List>>");
            System.out.println(list);
            // 첫번째
            powK = (int) Math.pow(2, k);   //powK는 k가 1일때, 2 / powK는 k가 2일때, 4
            System.out.println(powK);
            
            if(powK>=n) {
               continue;
            }
            
            for (int fi=0;fi<powK;fi++) { //뒤에서 powK개 만큼 빼온다.
               int val = list.get(n-1-fi);   //가장 뒤에 것을 가져와 저장하고
               list.remove(n-1-fi);   //리스트에서 제거한 뒤
               tmpList.add(0, val);      //임시 리스트에 넣는다.
               System.out.println(list);
            }
            System.out.println("tmpList: "+tmpList);
            // 이후 i 단계   j는 임시 list(직전의 올린 카드)의 크기
            for (int j = powK;j>0;j=j/2) {
               for (int i=j/2-1;i>=0;i--) {   //직전의 올린 카드 중 밑에서 2^(k-i+1)을 제외한 카드를 손에서 버리고 list에 넣는다.
                  int tmpVal = tmpList.get(i);
                  tmpList.remove(i);
                  list.add(0, tmpVal);
               }
            }
            list.add(0, tmpList.get(0));
            tmpList.clear();
            for(int i=0;i<list.size();i++) {
               if (list.get(i)!=answerList.get(i)) {
                  flag = false;
                  break;
               }
            }
            System.out.println(kk1+">>result<<"+kk2);
            System.out.println(list+"\n");
            if(flag) {
               System.out.println(kk1+" "+kk2);
               return;
            }
            
            //다시 초기화
            list.clear();
            flag = true;
            for (int i=0;i<n;i++) {
               list.add(i+1);
            }
         }
      }
   }

}

/* 1. 가능한 경우를 뽑는다.
 *  ex) N이 5인 경우 K는 2까지 가능하므로 가능한 경우의 수는 1,1 / 1,2 / 2,1 / 2,2 4가지 이다.
 * 2. 가능한 경우로 돌려본다..?
 * 
 */