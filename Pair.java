/***************************************************************
* file: Pair.java
* author: Jinjing Lee
* class: CS 431 Database Systems
*
* It defines Pair object to handele each job running time and waiting time

* assignment: project1
* date last modified: 02/10/2018
**
****************************************************************/

import java.util.*;

public class Pair {

    private int startTime;
    private int endTime;
    private int duration;


    public Pair(int theStartTime, int theEndTime) {
        startTime = theStartTime;
        endTime = theEndTime;
    }

    public int getStartTime() 
    {
        return startTime;
    }

    public  int getEndTime() 
    {
        return endTime;
    }
    public int getDuration()
    {
        return endTime - startTime;
    }

}