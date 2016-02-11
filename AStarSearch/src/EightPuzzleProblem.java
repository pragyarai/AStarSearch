import java.util.Scanner;

/**
 * @author Pragya Rai
 */

public class EightPuzzleProblem
{

	public static void main(String[] args)
	{
		
		String ans;
		Scanner input = new Scanner(System.in);
		System.out.println("If you want to enter the intial and final state press Y else N");
		ans=input.nextLine();
		
		int inputArray[] = new int[9]; 
		int goalState[] = new int[9];
		boolean inversionCheck =true;
		System.out.println(ans);
		if(ans=="y")
		{
			System.out.println("Enter the initial state");
			for (int i = 0 ; i < 9; i++ ) {
	           inputArray[i] = input.nextInt();
	        }
	        System.out.println("Enter the goal state");
			for (int i = 0 ; i < 9; i++ ) {
	        	goalState[i] = input.nextInt();
	        }
			
			inversionCheck=getInvCount(inputArray);
			if(inversionCheck==true)
			{
				System.out.println("The initial state you enter:");
				displayState(inputArray);
				System.out.println();
				System.out.println("The goal state you enter:");
				displayState(goalState);
				System.out.println();
				System.out.println();
				AStar.search(inputArray,goalState);
			}
			else
			{
				System.out.println("Not solvable!!");

			}
		}
		else
		{
			//inputArray = new int[]{2, 8, 3, 1, 6, 4, 7, 0, 5 };
			//goalState = new int[]{ 1, 2, 3, 8, 6, 4, 7, 5, 0 };
			//inputArray = new int[]{5, 4, 0, 6, 1, 8, 7, 3, 2 };
			//goalState = new int[]{ 1, 2, 3, 4, 0, 5, 6, 7, 8 };

			inputArray = new int[]{2, 7, 4, 5, 0, 6, 8, 3, 1 };
			goalState = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8 };
			inversionCheck=getInvCount(inputArray);
			System.out.println("The initial state :");
			displayState(inputArray);
			System.out.println();
			System.out.println("The goal state:");
			displayState(goalState);
			System.out.println();
			System.out.println();
			if(inversionCheck)
			{
				AStar.search(inputArray,goalState);
			}
			else
			{
				System.out.println("Not solvable!!");

			}
			
		}
		
		
			
		
	}
	
	public static void displayState(int []arrayState)
	{
		System.out.println(arrayState[0] + " | " + arrayState[1] + " | "
				+ arrayState[2]);
		System.out.println("---------");
		System.out.println(arrayState[3] + " | " + arrayState[4] + " | "
				+ arrayState[5]);
		System.out.println("---------");
		System.out.println(arrayState[6] + " | " + arrayState[7] + " | "
				+ arrayState[8]);

	}
	
	public static boolean getInvCount(int arr[])
	{
	    int inv_count = 0;
	    boolean check=false;
	    for (int i = 0; i < 9 - 1; i++)
	    {
	        for (int j = i+1; j < 9; j++)
	        {
	             // Value 0 is used for empty space
	             if (arr[i] > arr[j])
	             {
	            	
	                  inv_count++;
	             }
	        }
	        	    }
	    if(inv_count%2==0)
        {
        	//System.out.println("inside inversion");
        	check=true;
        }

	    //System.out.println("inv_count"+inv_count);

	    return check;
	}

	
}
