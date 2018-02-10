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
* date last modified: 02/10/2018
**
****************************************************************/

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
		}
		System.out.println(jobList.size());
	}
	public static ArrayList<Job> readFile(String fileName)
	{
		ArrayList<Job> jobList = new ArrayList<Job>();

		try {
				  
			Scanner input = new Scanner(System.in);
			File file = new File(fileName);
			input = new Scanner(file);
	  
			while (input.hasNextLine()) 
			{
				
				String name = input.nextLine();
				int time = Integer.parseInt(input.nextLine());
				System.out.println(name + ":" + time);
				
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
}
