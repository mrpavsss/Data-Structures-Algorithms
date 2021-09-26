import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LongestWords
{
   public static ArrayList<String> wordList;
   public static ArrayList<String> sorted = new ArrayList<String>();
   public static ArrayList<String> DQ;
   public static ArrayList<String> sortinDQ = new ArrayList<String>();
   
      
   public static void main(String[] args) 
   {
      Scanner scan = new Scanner(System.in);
      String lineInput = scan.nextLine();
      lineInput = lineInput.toLowerCase();
      if (lineInput.length() > 9) 
      {
         System.out.println(lineInput);
         System.exit(1);
      }
      
      wordList = new ArrayList<String>();
		//File file = new File("words.txt"); // Use this on your machine.
		File file = new File("C:\\Users\\pavly\\Downloads\\words.txt");
		int maxWordLength = 0;
		try 
      {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) 
         {
				String line = scanner.next();
				wordList.add(line);
            int n = line.length();
            if(n>maxWordLength) maxWordLength = n;
			}
			scanner.close();
		} 
      catch (FileNotFoundException e) 
      {
			e.printStackTrace();
		}
      
    Deque<CState> q = new LinkedList<CState>();
    CState start = new CState("", lineInput);
    // use addFirst for stack, addLast for queuel
    DQ = new ArrayList<String>();
    q.addFirst(start);
    //q.addLast(start);
    while (!q.isEmpty()) {
      CState next = q.removeFirst(); // dequeue or pop (same)
      if (next.suff().length() > 0) {
        String newPrefix = next.pre() + next.suff().charAt(0);
        String newSuffix = next.suff().substring(1);
        DQ.add(newPrefix);
        CState n1 = new CState(newPrefix, newSuffix);
        CState n2 = new CState(next.pre(), newSuffix);
        // use addFirst for stack, addLast for queue
        // q.addFirst(n1);
        // q.addFirst(n2); 
           q.addLast(n2); //queue
           q.addLast(n1); 
      }
    }
    
    for (int i = DQ.size() - 1; i >= 0; --i) 
    {
      Perms(DQ.get(i), "");
      for (int j = 0; j < sortinDQ.size(); ++j) 
      {
         if (binarySearch(wordList, sortinDQ.get(j)))
         {
            if (!sorted.contains(sortinDQ.get(j)))
            {
            
               sorted.add(sortinDQ.get(j));
            }
         }
      }
      sortinDQ.clear();
    }
       
    if (sorted.size() > 0){
        
    String test = sorted.get(0);
    int maxlen = test.length();
    
    for (int i = 0; i < sorted.size(); ++i)
    {
      if (maxlen < sorted.get(i).length())
      {
          maxlen = sorted.get(i).length();
      }
    }
    ArrayList<String> finalList = new ArrayList<String>();
    for (int i = 0; i < sorted.size(); ++i) 
    {
      if (sorted.get(i).length() == maxlen)
      {
         finalList.add(sorted.get(i));
      }
    }
    if (finalList.size() > 1)
    {
      finalList = insertionSortList(finalList);
    }
    for (int i = 0; i < finalList.size(); ++i)
    {
      System.out.println(finalList.get(i));
    }
    }
}
      
   public static ArrayList<String> insertionSortList(ArrayList<String> organize) {    
      for (int i = 1; i < organize.size(); ++i) 
      {
         for (int a = i; a > 0 && organize.get(a).compareTo(organize.get(a - 1)) < 0; --a)
            {
               
               Collections.swap(organize, a, a-1);
            }
      }
      return organize;          
   }
   
   static void Perms(String str, String ans) 
   { 
        // If string is empty 
        if (str.length() == 0) 
        { 
            sortinDQ.add(ans); 
            return; 
        } 
        for (int i = 0; i < str.length(); ++i) 
        { 
            // ith character of str 
            char ch = str.charAt(i); 
            // Rest of the string after excluding  
            // the ith character 
            String ros = str.substring(0, i) + str.substring(i + 1); 
            // Recurvise call 
            Perms(ros, ans + ch); 
        }  
   }
   public static boolean binarySearch(ArrayList<String> words, String wording) 
   {
      int lowIndex = 0;
      int highIndex = words.size()-1;

      // Holds the position in array for given element
      // Initial negative integer set to be returned if no match was found on array
      

      // If lowIndex less than highIndex, there's still elements in the array
      while (lowIndex <= highIndex) 
      {
         int midIndex = (lowIndex + highIndex) / 2;
         if (wording.equals(words.get(midIndex))) 
         {
            return true;
         }
         else if (wording.compareTo(words.get(midIndex)) < 0) 
         {
            highIndex = midIndex-1;
         }
         else if (wording.compareTo(words.get(midIndex)) > 0) 
         {
            lowIndex = midIndex+1;
         }
      }
      return false;
   }

}










