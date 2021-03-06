import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


/**
 * @author Pragya Rai
 */

public class AStar
{
	
	public static void search(int[] board,int []goalState)
	{
		int totalNodeSize=1;
		int iteration=0;
		Search rootNode = new Search(new EightPuzzle(board,goalState));
		
		Queue<Search> nodeList = new LinkedList<Search>();
		ArrayList<Search> openList = new ArrayList<Search>();
		nodeList.add(rootNode);
		
		
		while (!nodeList.isEmpty()) 
		{
			Search closedList = (Search) nodeList.poll();
			//System.out.println(closedList);
			// check if current state is not goal state
			if (!closedList.getCurState().isGoalState())
			{
				// generate children of current node
				
				ArrayList<State> tempChildren = closedList.getCurState()
						.genChildren();
				ArrayList<Search> allChildren = new ArrayList<Search>();
				iteration++;				
				//find the cost of the children and add the in the list
				
				for (int i = 0; i < tempChildren.size(); i++)
				{
					
					Search checkedNode;
					
					checkedNode = new Search(closedList,tempChildren.get(i), closedList.getCost() + tempChildren.get(i).findCost(),
								((EightPuzzle) tempChildren.get(i)).getNotInPlaced());				
					if (!repeatedState(checkedNode))
					{
						
						allChildren.add(checkedNode);
						openList.add(checkedNode);
					}
				}
				
				totalNodeSize=totalNodeSize+allChildren.size();

				if (allChildren.size() == 0)
					continue;

				Search minCostNode = allChildren.get(0);
				
				// Find the minimum cost
				
				for (int i = 0; i < allChildren.size(); i++)
				{
					
					if (minCostNode.getFCost() > allChildren.get(i).getFCost())
					{
						
						minCostNode = allChildren.get(i);
						
					}
				}

				int minCost = (int) minCostNode.getFCost();
				//System.out.println("minCost "+minCost);

				// Adds any nodes that have that same lowest value.
				for (int i = 0; i < allChildren.size(); i++)
				{
					
					if (allChildren.get(i).getFCost() == minCost)
					{
						
						nodeList.add(allChildren.get(i));
						
					}
					
				}
							}
			else
			// Path to goal state

			{
				//System.err.println(closedList.toString());
				System.out.println("Final path chosen to reach the goal state");
				Stack<Search> finalPath = new Stack<Search>();
				finalPath.push(closedList);
				closedList = closedList.getParentState();

				while (closedList.getParentState() != null)
				{
					finalPath.push(closedList);
					closedList = closedList.getParentState();
				}
				finalPath.push(closedList);

				int loopSize = finalPath.size();

				for (int i = 0; i < loopSize; i++)
				{
					closedList = finalPath.pop();
					closedList.getCurState().displayCurrent();
					System.out.println();
					System.out.println();
				}
				System.out.println("The number of nodes expanded:" + iteration);
				System.out.println("The total  number of nodes generated:"+totalNodeSize);
				System.exit(0);
			}
		}

		// This should never happen with our current puzzles.
		System.err.println("Error! No solution found!");

	}

	private static boolean repeatedState(Search n)
	{
		boolean stateVal = false;
		Search repeatState = n;

		while (n.getParentState() != null && !stateVal)
		{
			if (n.getParentState().getCurState().checkEqual(repeatState.getCurState()))
			{
				stateVal = true;
			}
			n = n.getParentState();
		}

		return stateVal;
	}

}
