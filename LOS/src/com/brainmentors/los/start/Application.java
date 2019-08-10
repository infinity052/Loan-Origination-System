package com.brainmentors.los.start;
import com.brainmentors.los.operations.LOSProcess;
import com.brainmentors.los.utils.Utility;

public class Application {

	
	public static void main(String[] args) {
		LOSProcess process = new LOSProcess();
	  while(true) {
	  System.out.println("Do you have an application number or not(Enter 0) press -1 to exit?");
      int applicationNumber=Utility.scanner.nextInt();
      if(applicationNumber==-1) {
    	  System.out.println("Thanks for Using");
    	  System.exit(0);
      }
      if(applicationNumber==0) {
    	 process.sourcing();
      }
      else {
    	 process.checkStage(applicationNumber);
	}}
  }

}
