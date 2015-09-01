package cs342_1;

import java.util.Scanner;

public class LinkedListDriver {
	
	private static Scanner scanny = new Scanner(System.in);
	private LinkedList ll = new LinkedList();
	private String restoredFilePath;
	private boolean isRestored = false;
	
	public static void main(String[] args) {
		
		boolean isQuit = false;
		LinkedListDriver lld = new LinkedListDriver();
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to the Interactive Telephone List. \n");
		
		//displays the menu for the user to select an action
		while(!isQuit) {
			displayMenu();

			char userChoice = input.nextLine().charAt(0);
		
			lld.chooseAction(userChoice);
		}
	
	}
	
	public void chooseAction(char userChoice) {
		
		switch(userChoice) {
		case 'a':
			addEntry();
			break;
		case 'p':
			System.out.println(ll.print());
			break;
		case 's': 
			searchName();
			break;
		case 'e': 
			searchEmail();
			break;
		case 'd':
			deleteEntry();
			break;
		case 'w':
			writeTelephoneList();
			break;
		case 'r':
			restoreSavedTelephoneList();
			break;
		case 'q':
			System.out.println("Do You Really Want to Quit? Y/N");
			char quit = scanny.next().toUpperCase().charAt(0);
			if(quit == 'Y') {
				System.out.println("List Closed");
				System.exit(0);
			}
			
		}
	}
	
	public static void displayMenu() {
		System.out.println("\nEnter a letter from the list below to perform an action");
		System.out.println("'a': Add a Phone Number to the list \n'p': Print the Entire List \n's': "
				+ "Search for Name \n'e': Search for Email Address \n'd': Delete an Entry \n'w':"
				+ " Write the Database to a File \n'r': Restore a Saved Database \n'q': Quit Application");
	}
	
	//prompts user for entry information
	public void addEntry(){
		
		System.out.println("Enter a Full Name in the format of 'Fname Lname'");
		String fullName = scanny.nextLine().toUpperCase();

		System.out.println("Enter a Phone Number in the format of '8003334444'");
		String phoneNumber = scanny.nextLine();
		
		System.out.println("Enter an Email Address in the format of 'mail@aol.com'");
		String email = scanny.nextLine().toUpperCase();

		ll.insertNode(fullName, email, phoneNumber);
	}
	
	//prompts user for index they want to delete
	public void deleteEntry(){
		
		System.out.println("Please enter the Index # of the entry you wish to delete");
		String userIndexForDeletion = scanny.nextLine();
		ll.deleteNode(userIndexForDeletion);
	}
	
	//prompts user for name they want to find
	public void searchName(){

		System.out.println("Enter the name that you wish to find");
		String searchedName = scanny.nextLine().toUpperCase();
		ll.searchNodeName(searchedName);
	}
	
	//prompts user for email they want to find
	public void searchEmail(){
		
		System.out.println("Enter the email address that you wish to find");
		String searchedEmailAddress = scanny.nextLine().toUpperCase();
		ll.searchNodeEmail(searchedEmailAddress);
	}
	
	//reads in file path and populates telephone list
	public void restoreSavedTelephoneList(){

		System.out.println("Please enter a pathname to the data file in the form of /home/jackie/fileName");
		restoredFilePath = scanny.nextLine();
		ll = ll.restoreDatabaseFromFile(restoredFilePath);
		isRestored = true;
	}
	
	//prompts for pathname to data file or uses the path that was restored 
	//and writes telephone list to the file path. 
	public void writeTelephoneList(){
		
		if(isRestored) {
			ll.writeDatabaseToFile(restoredFilePath, ll);
		}
		else {
			System.out.println("Please enter a pathname to the data file in the form of /home/jackie/fileName");
			String fileName = scanny.nextLine();
			ll.writeDatabaseToFile(fileName, ll);
		}
		
	}


}
