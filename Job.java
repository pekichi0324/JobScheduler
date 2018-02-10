/***************************************************************
* file: Job.java
* author: Jinjing Lee
* class: CS 431 Database Systems
*
* It defines Job object for Job Shceduler Project

* assignment: project1
* date last modified: 02/10/2018
**
****************************************************************/

import java.util.*;

public class Job
{
	private String name;
	private int burstTime;
	private ArrayList<Pair> waitingTime = new ArrayList<Pair>(); 
	private ArrayList<Pair> runningTime = new ArrayList<Pair>(); 

	public Job(String name, int burstTime)
	{
		this.name = name;
		this.burstTime = burstTime;
		
	}
	public void setName(String name)
	{
		this.name = name; 

	}
	public void addWaitingTime(int start, int end)
	{

		waitingTime.add(new Pair(start, end));

	}
	public void addRunningTime(int start, int end)
	{
		runningTime.add(new Pair(start, end));

	}
	public List<Pair> getWaitingTime()
	{
		return waitingTime;

	}
	public List<Pair> getRunningTime()
	{
		return runningTime;

	}
	public int getBurstTime()
	{
		return burstTime;
	}
	public void setBurstTime(int time)
	{
		burstTime = time;
	}
}

