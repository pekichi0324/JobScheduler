/***************************************************************
* file: JobScheduler.java
* author: Jinjing Lee
* class: CS 431 Database Systems
*
* Program to simulate an operating system. 
* It handelesthe following CPU scheduling algorithms. 

* 	1.First-Come-First-Serve (FCFS)
* 	2.Shortest-Job-First (SJF)
* 	3.Round-Robin with time slice = 2 (RR-2)
* 	4.Round-Robin with time slice = 5 (RR-5)

* assignment: project1
* date last modified: 02/21/2018
**
****************************************************************/

import java.text.DecimalFormat;
import java.io.File;
import java.util.*;

public class JobScheduler
{
	
	public static void main(String[]args)
	{
		run();
	}

	public static void run(){

		ArrayList<Job> jobList = new ArrayList<Job>();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a test file number from 1 to 3.");
		int testNumber= sc.nextInt();
		String fileName= "";
		switch (testNumber)
		{
			case 1: 
				fileName = "testdata1.txt";
				break;
			case 2:
				fileName = "testdata2.txt";
				break;
			case 3:
				fileName = "testdata3.txt";
				break;
			default:
				System.out.println("Enter a number between 1 to 3.");
		}
		if (fileName != "")
		{
			jobList = readFile(fileName);
			chooseJobSchedulMethod(sc, jobList);
		}
	}
	public static void chooseJobSchedulMethod(Scanner sc, ArrayList<Job>jobList)
	{
		while (true)
		{
			System.out.println("Choose the Scheduling algorithms. Enter 1 to 5.");
			System.out.println("1. First Come First Searve");
			System.out.println("2. Shortest Job First");
			System.out.println("3. Round-Robin Time Slice = 2");
			System.out.println("4. Round-Robin Time Slice = 5");
			System.out.println("5. Test all methods");

			int choice = sc.nextInt();
		
			switch (choice)
			{
				case 1:
					firstComeFirstServe(jobList);
					break;
				case 2:
					shortestJobFirst(jobList);
					break;
				case 3:
					roundRobin(jobList,2);
					break;
				case 4:
					roundRobin(jobList,5);
					break;
				case 5:
					testAllSchedulers(jobList);
					break;
				default:
					System.out.println("\nPlease enter a number between 1 to 4.\n");

			}
		}

	}

	public static void testAllSchedulers(ArrayList<Job>jobList)
	{

		firstComeFirstServe(jobList);
		shortestJobFirst(jobList);
		roundRobin(jobList,2);
		roundRobin(jobList,5);

	}
	public static ArrayList<Job> readFile(String fileName)
	{
		ArrayList<Job> jobList = new ArrayList<Job>();

		try 
		{
				  
			Scanner input = new Scanner(System.in);
			File file = new File(fileName);
			input = new Scanner(file);
	  
			while (input.hasNextLine()) 
			{
				String name = input.nextLine();
				int time = Integer.parseInt(input.nextLine());
				jobList.add(new Job(name,time));
			}
			input.close();

		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();            
		} 
		
		return jobList;
	}   

	public static void firstComeFirstServe(ArrayList<Job> jobList)  
	{

		ArrayList<Job> copyList = clone(jobList);
		
		int currentTime = 0;

		for (Job job:copyList)
		{
			int remainingTime = job.getRemainingTime();

			// Set the running time 
			job.addRunningTime(currentTime, currentTime + remainingTime);

			// Set the time that the job is completed
			job.setJobFinishedTime(currentTime + remainingTime);

			// Increment waiting time
			currentTime += remainingTime;

			// Set burstTime to 0
			job.setRemainingTime(0);

		}
		System.out.println("\nFCFS result:\n" );
		printInTable(copyList);

	}   

	public static void shortestJobFirst(ArrayList<Job> jobList)
	{
		ArrayList<Job> copyList = clone(jobList);
		int listSize = copyList.size();
		int shortestIndex = 0;
		int currentTime = 0;

		for (int j = 0; j < listSize; j++)
		{

			int shortestBurstTime = Integer.MAX_VALUE;

			// Loop to find a shortest index
			for ( int i = 0; i < listSize; i++ )
			{
				if ((copyList.get(i).getBurstTime() < shortestBurstTime) && !(copyList.get(i).getIsDone()))
				{
					shortestIndex = i;
					shortestBurstTime = copyList.get(i).getRemainingTime();
				}
			}
			int burstTime = copyList.get(shortestIndex).getRemainingTime();

			// Set isDone to true
			copyList.get(shortestIndex).setIsDone(true);

			// Set finished time
			copyList.get(shortestIndex).setJobFinishedTime(currentTime + burstTime);
		
			// Set the running time period
			copyList.get(shortestIndex).addRunningTime(currentTime, currentTime + burstTime);

			// Increment current time
			currentTime+= burstTime;
		}
		System.out.println("\n\nShortest first result:\n");
		printInTable(copyList);
	}  

	// Round-Robin function
	public static void roundRobin(ArrayList<Job> jobList, int slice)
	{
		ArrayList<Job> copyList = clone(jobList);
		int listSize = copyList.size();
		int currentTime = 0;
		int numOfFinishedJobs = 0;

		while (numOfFinishedJobs < listSize)
		{
			for (Job job:copyList)
			{
				if (!job.getIsDone())
				{
					int currentRemaining = job.getRemainingTime();

					if (job.getRemainingTime() < slice)
					{

						// Set the running time period
						job.addRunningTime(currentTime, currentTime + currentRemaining);

						// Set JobFinishedTime
						job.setJobFinishedTime(currentTime + currentRemaining);

						// Increment current time
						currentTime+= currentRemaining;

						// Set remaining burst time to 0
						job.setRemainingTime(0);

						// The current Job is completed so set the boolean to true
						job.setIsDone(true);

						numOfFinishedJobs++;

					}
					else 
					{
						// Set the running time period
						job.addRunningTime(currentTime, currentTime + slice);

						// Update remaining burst time
						job.setRemainingTime( currentRemaining - slice);

						// Increment current time
						currentTime+= slice;

						// Check if the job is completed
						if ( job.getRemainingTime() == 0)
						{
							// Set JobFinishedTime
							job.setJobFinishedTime(currentTime);
							
							// The current Job is completed so set the boolean to true
							job.setIsDone(true);

							numOfFinishedJobs++;
							
						}
					}
				}	
			}
			
		}
		System.out.println("\n\nRound Robin with time slice " + slice + " result:\n");
		printInTable(copyList);
	}

	public static void print(ArrayList<Job> jobList)
	{
		int totalCompTime = 0;
		int totalWaitTime = 0;
		DecimalFormat df = new DecimalFormat("#.##");

		for (Job job: jobList)
		{
			System.out.println(job.getName() + ": Job finished at " + job.getJobFinishedTime());
			System.out.println("Running time is: " );

			for (Pair pair: job.getRunningTime())
				System.out.println(pair.getStartTime() + " to " + pair.getEndTime());

			System.out.println("");
			totalCompTime += job.getJobFinishedTime();
			totalWaitTime += (job.getJobFinishedTime() - job.getBurstTime());
		}
		double aveWaitingTime = totalWaitTime/ (double)jobList.size();
		double aveCompTime = totalCompTime/ (double)jobList.size();
		System.out.print("\nAverage waiting time: ");
		System.out.print(df.format(aveWaitingTime));
		System.out.print("\nAverage completion time: ");
		System.out.println( df.format(aveCompTime) + "\n");

	} 

	public static void printInTable(ArrayList<Job> jobList)
	{
		int totalCompTime = 0;
		int totalWaitTime = 0;
		DecimalFormat df = new DecimalFormat("#.##");

		System.out.print("----------------------------------------------------\n");
		System.out.printf("%-1s %-8s %-1s %-8s %-1s %-8s %-1s %-8s %-6s", "|", "Process", "|", "Start",  "|", "Finish",  "|" , " Time executed ", "|");
		System.out.print("\n|----------|----------|----------|-----------------|\n");
		for (Job job: jobList )
		{
			System.out.printf("%-1s %8s %1s %8d %1s %8d %1s ", "|", job.getName(), "|", job.getStartingTime(), "|", job.getJobFinishedTime() , "|");
			//System.out.print( "   " + job.getName() + "  " + job.getStartingTime() + "   " + job.getJobFinishedTime() +  "    ");

			totalCompTime += job.getJobFinishedTime();
			totalWaitTime += (job.getJobFinishedTime() - job.getBurstTime());

			for (int i = 0; i < job.getRunningTimeListSize(); i++)
			{
				if ( i == 0)
					System.out.printf("%3d %2s %3d %6s", job.getStartingTimeAt(i), "->",  job.getEndingTimeAt(i), "|");
				else 
				{
					System.out.println("");
					System.out.printf("%-1s %8s %1s %8s %1s %8s %1s %3d %2s %3d %6s", "|", " ", "|", " " , "|", " ", "|", job.getStartingTimeAt(i), "->",  job.getEndingTimeAt(i),"|");
				}
			}
			System.out.print("\n|----------|----------|----------|-----------------|\n");
		}
		double aveWaitingTime = totalWaitTime/ (double)jobList.size();
		double aveCompTime = totalCompTime/ (double)jobList.size();
		System.out.print("\nAverage waiting time: ");
		System.out.print(df.format(aveWaitingTime));
		System.out.print("\nAverage completion time: ");
		System.out.println( df.format(aveCompTime) + "\n");

	} 

	// Clone function to make a copy of original list
	public static ArrayList<Job> clone( ArrayList<Job> jobList)
	{
		ArrayList<Job> clone = new ArrayList<Job>();
		for (Job job: jobList)
		{
			clone.add(new Job(job.getName(), job.getBurstTime()));

		}
		return clone;
	}     
}
