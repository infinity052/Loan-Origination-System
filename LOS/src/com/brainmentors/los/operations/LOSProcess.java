package com.brainmentors.los.operations;
import com.brainmentors.los.customer.Customer;
import com.brainmentors.los.customer.LoanDetails;
import com.brainmentors.los.customer.PersonalInformation;
import com.brainmentors.los.utils.CommonConstants;
import com.brainmentors.los.utils.LoanConstants;
import com.brainmentors.los.utils.StageConstant;
import com.brainmentors.los.utils.Utility;

import static com.brainmentors.los.utils.Utility.scanner;
import static com.brainmentors.los.utils.Utility.serialCounter;

import java.util.ArrayList;

public class LOSProcess implements StageConstant,CommonConstants {
private ArrayList <Customer> customers = new ArrayList<>();

	public void qde(Customer customer) {
	customer.setStage(QDE);
	System.out.println("Your Application number is "+ customer.getId());
	System.out.println("Name "+customer.getPersonal().getFirstName()+" "+customer.getPersonal().getLastName());
	System.out.println("You Applied for a "+customer.getLoandetails().getType()+" "
			+ "Duration "+customer.getLoandetails().getDuration()+" Amount "
					+customer.getLoandetails().getAmount() );
	System.out.println("Enter the PanCard Number");
					String panCard=scanner.next();
					System.out.println("Enter Voter ID");
					String voterId=scanner.next();
					System.out.println("Enter the income");
					double income=scanner.nextDouble();
					System.out.println("Enter the Liability");
					double liability=scanner.nextDouble();
					System.out.println("Enter Phone number");
					String phone=scanner.next();
					System.out.println("Enter Email");
					String email=scanner.next();
		customer.getPersonal().setPanCard(panCard);
		customer.getPersonal().setVoterId(voterId);
		customer.getPersonal().setEmail(email);
		customer.getPersonal().setPhone(phone);
		customer.setIncome(income);
		customer.setLiability(liability);
		                                                  
		        
}

		public void moveToNextStage(Customer customer) {
	        while(true) {
			if(customer.getStage()==SOURCING) {
	        	
	        	System.out.println("Want to Move to the Next Stage Y/N");
				char choice = scanner.next().toUpperCase().charAt(0);
				if(choice==YES) {
					qde(customer);
					
				}
				else
					return;
	        	
	        }
			else	if(customer.getStage()==QDE) {
	
				System.out.println("Want to Move to the Next Stage Y/N");
				char choice = scanner.next().toUpperCase().charAt(0);
				if(choice==YES) {
					dedupe(customer);
				}
				else
					return;
				
				
			}
			
			else	if(customer.getStage()==DEDUPE) {
	        	
	        	System.out.println(" Dedupe Want to Move to the Next Stage Y/N");
				char choice = scanner.next().toUpperCase().charAt(0);
				if(choice==YES) {
					score(customer);
					
				}
				else
					return;
	        }
			
else	if(customer.getStage()==SCORING) {
	        	
	        	System.out.println("Scoring Want to Move to the Next Stage Y/N");
				char choice = scanner.next().toUpperCase().charAt(0);
				if(choice==YES) {
					approval(customer);
					
				}
				else
					return;
	        } 
		}
	        
		}
		
		public void score(Customer customer) {
			customer.setStage(SCORING);
			int score=0;
			double totalIncome= customer.getIncome()-customer.getLiability();
			if(customer.getPersonal().getAge()>=21 &&
					customer.getPersonal().getAge()<=35)
				score+=50;
			
			if(totalIncome>=200000) {
				score+=50;
			}
			
			customer.getLoandetails().setScore(score);
		}
		
		
		public void approval(Customer customer) {
			customer.setStage(APPROVAL);
			int score=customer.getLoandetails().getScore();
			System.out.println("id "+customer.getId());
			System.out.println("Name is "+customer.getPersonal().getFirstName()+""
					+ " "+customer.getPersonal().getLastName());
			System.out.println("Score is "+customer.getLoandetails().getScore());
			System.out.println("Loan "+customer.getLoandetails().getType()+" Amount"
					+ " "+customer.getLoandetails().getAmount()+
					" Duration "+customer.getLoandetails().getDuration());
			double approveAmount=customer.getLoandetails().getAmount()*(score/100);
			System.out.println("Loan Approve Amount is "+approveAmount);
			System.out.println("Do you want to bring this loan or not?");
			char choice=scanner.next().toUpperCase().charAt(0);
			if(choice==NO) {
				customer.setStage(REJECT);
				customer.setRemarks("Customer Denied the approved amount of "+approveAmount);
				
				return;
			}
			
			else {
				showEMI(customer);
			}
			
		}
		
		private void showEMI(Customer customer) {
			//System.out.println("EMI is ");
			if(customer.getLoandetails().getType().equalsIgnoreCase(LoanConstants.HOME_LOAN)) {
				customer.getLoandetails().setRoi(LoanConstants.HOME_LOAN_ROI);
				}
			if(customer.getLoandetails().getType().equalsIgnoreCase(LoanConstants.AUTO_LOAN)) {
				customer.getLoandetails().setRoi(LoanConstants.AUTO_LOAN_ROI);
				}
			if(customer.getLoandetails().getType().equalsIgnoreCase(LoanConstants.PERSONAL_LOAN)) {
				customer.getLoandetails().setRoi(LoanConstants.PERSONAL_LOAN_ROI);
				}
		double perMonthPrinciple=customer.getLoandetails().getAmount()/customer.getLoandetails().getDuration();
		double interest=perMonthPrinciple*customer.getLoandetails().getRoi();
		double totalEmi=perMonthPrinciple+interest;
		System.out.println("Your Emi is "+totalEmi);
		
		}
		
		public void dedupe(Customer customer) {
			customer.setStage(DEDUPE);
		  System.out.println("Inside Dedupe");
		  boolean isNegetiveFound=false;
		  for(Customer negetiveCustomer : DB.getNegetiveCustomers()) {
			  int negetiveScore=isNegetive(customer,negetiveCustomer);
			 if(negetiveScore>0) {	  
			System.out.println("Customer record found in dedupe "+negetiveScore);
			  isNegetiveFound=true;
			
			  break;
			 }
		  }
		  if(isNegetiveFound) {
			  System.out.println("Do u want to proceed this loan "+customer.getId());
			  char choice=scanner.next().toUpperCase().charAt(0);
			  if(choice==NO) {
				  customer.setRemarks("Loan has been rejected , Due to High score in dedupe check");
			      customer.setStage(REJECT);
			      return;
			  }
		  }
		}
		private int isNegetive(Customer customer, Customer negetive) {
		int percentageMatch=0;
		if(customer.getPersonal().getPhone().equals(negetive.getPersonal().getPhone()))
		percentageMatch+=10;
		if(customer.getPersonal().getEmail().equals(negetive.getPersonal().getEmail()))
			percentageMatch+=10;
		if(customer.getPersonal().getVoterId().equals(negetive.getPersonal().getVoterId()))
			percentageMatch+=10;
		if(customer.getPersonal().getPanCard().equals(negetive.getPersonal().getPanCard()))
			percentageMatch+=10;
		if((customer.getPersonal().getAge()==(negetive.getPersonal().getAge())) && 
				(customer.getPersonal().getFirstName().equalsIgnoreCase(
						negetive.getPersonal().getFirstName())))
			percentageMatch+=20;
		
			return percentageMatch;
		
	}
		
		public void sourcing() {
	Customer customer = new Customer();
	customer.setId(serialCounter);
	customer.setStage(SOURCING);

	System.out.println("Enter your First name");
	String firstName=scanner.next();
	System.out.println("Enter the Last name");
	String lastName=scanner.next();
	System.out.println("Enter age");
	int age=scanner.nextInt();
	System.out.println("Enter loan type HL,AL,PL");
	String type=scanner.next();
	System.out.println("Enter Amount");
	double amount = scanner.nextDouble();
	System.out.println("Duration of Loan");
	int duration = scanner.nextInt();
	PersonalInformation pd= new PersonalInformation();
	pd.setFirstName(firstName);
	pd.setLastName(lastName);
	pd.setAge(age);
	customer.setPersonal(pd);
	LoanDetails loandetails = new LoanDetails();
	loandetails.setAmount(amount);
	loandetails.setType(type);
	loandetails.setDuration(duration);
	customer.setLoandetails(loandetails);
	customers.add(customer);
	serialCounter++;
	System.out.println("Sourcing Done.....");
	
}

		public void checkStage(int applicationNumber) {
	boolean isStageFound=false;
    if(customers!=null && customers.size()>0)
	for(Customer customer : customers) {
		if(customer.getId()==applicationNumber) {
			System.out.println("You are on "+Utility.getStageName(customer.getStage()));
		isStageFound=true;
		moveToNextStage(customer);
		break;
		}
	}
    if(!isStageFound)
    	System.out.println("Invalid Application Number");
}
		


}
