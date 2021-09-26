import java.util.Scanner;

public class SumOfPrevious
{
   public static void main(String[] args) 
   {
      Scanner scan = new Scanner(System.in);
      int count = scan.nextInt();
      int[] array = new int[count];
      for (int i = 0; i < count; i++) 
      {
         array[i] = scan.nextInt();
      }
      
      System.out.println(match(array, count, 0, 0, 0));
      
   }
   public static boolean match(int[] arr, int length, int i, int j, int k) 
   {
      if (arr[i] == arr[j] + arr[k] || arr[i] == arr[j] + arr[j] || arr[i] == arr[k] + arr[k])
      {
         return true;
      }
      
      if (k + 1 < j)
      {
         return match(arr, length, i, j, k + 1);
      }
      else if(j + 1 < i)
      {
         return match(arr, length, i, j + 1, k);
      }
      else if(i + 1 < length)
      {
         return match(arr, length, i + 1, j, k);
      }
      else 
      {
         return false;
      }     
         
      
   }
}