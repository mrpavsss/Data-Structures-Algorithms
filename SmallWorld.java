//Vraj Shah
//Partner: Somebody but don't know yet

import java.io.*;
import java.util.*;

public class SmallWorld {

	public static void main(String[] args) throws Exception {
		//PART 1 -------------------------------------------------------------------
		//Create Objects -------------

		String file1 = "actresses.list.gz";
      String file2 = "actors.list.gz";
		//String fname = "shortestActors.list.gz";
		//String fname1 = System.getenv("LIB")+"/public/" + file1;
      //String fname2 = System.getenv("LIB")+"/public/" + file2;

		ArrayList<ActorRecord> act = new ArrayList<ActorRecord>(); //ALL INFORMATION IN THIS ARRAYLIST<------
		//RetrieveActors ra = new RetrieveActors(fname);
		RetrieveActors ra1 = new RetrieveActors("C:\\Users\\pavly\\Documents\\All things HW\\CS 114 Work\\Everything PS 11\\actors.list.gz");
      //RetrieveActors ra2 = new RetrieveActors("C:\\Users\\pavly\\Documents\\All things HW\\CS 114 Work\\Everything PS 11\\actresses.list.gz");
		String content;
		String[] tkn;
		//int count = 1;
		//Start Reading the file and the ActorRecords, filtering out some. ---------
		//long start = System.currentTimeMillis();
		while ((content = ra1.getNext()) != null) 
		{
			tkn = content.split("@@@");
			ActorRecord ar = new ActorRecord(tkn[0]);
			for (int i = 1; i < tkn.length; ++i)
				if(tkn[i].substring(0, 2).equals("FM")) // only keep films series
					ar.addMovie(tkn[i].substring(2));
			act.add(ar);
		}
		ra1.close();
      
      // while ((content = ra2.getNext()) != null) 
// 		{
// 			tkn = content.split("@@@");
// 			ActorRecord ar = new ActorRecord(tkn[0]);
// 			for (int i = 1; i < tkn.length; ++i)
// 				if(tkn[i].substring(0, 2).equals("FM")) // only keep films series
// 					ar.addMovie(tkn[i].substring(2));
// 			act.add(ar);
// 		}
// 		ra2.close();

		//long end = System.currentTimeMillis();
		//System.out.println("Read in "+act.size()+" actresses in time "+(end-start)/1000.00+" sec.");

		//Part 2 -------------------------------------------------------------------
		//Create the graph
		int n = act.size();
		Graphl actorGraph = new Graphl(n);
		for(int i = 0; i<act.size();i++)
		{
			ArrayList<String> mov1 = act.get(i).movies;
			for(int j = i+1; j<act.size(); j++)
			{
				ArrayList<String> mov2 = act.get(j).movies;
				for(int k = 0; k<mov2.size(); k++)
				{
					if(mov1.contains(mov2.get(k)))
					{
						actorGraph.setEdge(i, j, 1);
						actorGraph.setEdge(j, i, 1);
					}
				}
			}
		}

		//Part 3 -------------------------------------------------------------------
		//Get user input
		int source = 1;
		int destination = 1;
		Scanner reader = new Scanner (System.in);
		while(source*destination > 0)
		{
			System.out.println("Enter source and destination indices: ");
			source= reader.nextInt();
			destination = reader.nextInt();
			if(source <=0 && destination <=0)
				break;
			for(int i=0; i<actorGraph.n();++i)
				actorGraph.setMark(i, -1);
			BFS(actorGraph, source);
			Deque<Integer> shortestPath = new ArrayDeque<Integer>();

		//Part 4 -------------------------------------------------------------------
		//Output
			shortestPath.clear();
			shortestPath.push(destination);
			int tempD = destination;
			int beforeD = actorGraph.getMark(tempD);
			while(beforeD != -2)
			{
				for(String s : act.get(tempD).movies) 
				{
					if(act.get(beforeD).appearedIn(s))
					{
						break;
					}
				}
				tempD = beforeD;
				shortestPath.push(tempD);
				beforeD = actorGraph.getMark(tempD);
			}

			tempD = shortestPath.pop();
			System.out.print("Shortest path between ");
			System.out.println(act.get(source).name+" and "+act.get(destination).name);
			System.out.println("Distance: "+(shortestPath.size())+"; the chain is:");
			while(!shortestPath.isEmpty())
			{
				beforeD = shortestPath.pop();
				String actor1 = act.get(tempD).name;
				String actor2 = act.get(beforeD).name;
				System.out.print(actor1+" appeared with "+actor2);
				String movie = "";
				for(String m : act.get(tempD).movies) 
				{
					if(act.get(beforeD).appearedIn(m))
					{
						movie = m;
						break;
					}
				}
				System.out.println(" in "+movie);
				tempD = beforeD;
			}
			System.out.println();
		}
		reader.close();
	}

	public static void BFS(Graphl graph, int source) 
	{
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addLast(source);
		graph.setMark(source, -2);
		while (list.size() > 0)
		{
			int vertex = list.removeFirst();
			for (int edge : graph.neighbors(vertex)) 
			{
				if (graph.getMark(edge) == -1) 
				{
					graph.setMark(edge, vertex);
					list.addLast(edge);
				}
			}
		}
	}
}





