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
	private int remainingTime;
	private int jobFinishedTime;
	private boolean isDone;
	private ArrayList<Pair> runningTime = new ArrayList<Pair>(); 

	public Job(String name, int burstTime)
	{
		this.name = name;
		this.burstTime = burstTime;
		this.remainingTime = burstTime;
		this.isDone = false;
		this.jobFinishedTime = Integer.MAX_VALUE;
		
	}
	public void setName(String name)
	{
		this.name = name; 

	}
	public String getName()
	{
		return name; 
	}
	public int getBurstTime()
	{
		return burstTime;
	}
	public void setRemainingTime(int time)
	{
		remainingTime = time;
	}
	public int getRemainingTime()
	{
		return remainingTime;
	}
	public void setIsDone(boolean value)
	{
		this.isDone = value; 

	}
	public boolean getIsDone()
	{
		return isDone; 
	}
	public void setJobFinishedTime(int time)
	{
		jobFinishedTime = time;
	}
	public int getJobFinishedTime()
	{
		return jobFinishedTime;
	}
	
	public void setBurstTime(int time)
	{
		burstTime = time;
	}
	public void addRunningTime(int start, int end)
	{
		runningTime.add(new Pair(start, end));
	}
	public List<Pair> getRunningTime()
	{
		return runningTime;
	}
	
}

