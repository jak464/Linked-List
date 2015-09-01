package cs342_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LinkedList implements java.io.Serializable {
	
	private Node head;
	private int nodeCount;
	private boolean isRestored = false;
	private static final long serialVersionUID = 1L;
	private String restoredFilePath;
	
	public LinkedList() {
		head = null;
		nodeCount = 0;
	}
	
	public Node getHead() {
		return head;
	}
	
	public void setHead(Node head) {
		this.head = head;
	}
	
	public int getNodeCount() {
		return nodeCount;
	}
	
	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}
	
	public void addHeadNode(String fullName, String telephoneNumber, String emailAddress) {
		Node newNode = new Node();
		
		newNode.setFullName(fullName);
		newNode.setTelephoneNumber(telephoneNumber);
		newNode.setEmailAddress(emailAddress);
		
		head = newNode;
		head.setNext(null); 
		nodeCount++;
	}
	
	public void insertNode(String fullName, String emailAddress, String telephoneNumber) {

		//Head insert
		if(head == null){
			addHeadNode(fullName,telephoneNumber,emailAddress);
			return; 
		}
		
		//General insert
		Node newNode = new Node();
		
		newNode.setFullName(fullName);
		newNode.setTelephoneNumber(telephoneNumber);
		newNode.setEmailAddress(emailAddress);
		
		Node prev = null;
		Node cur = head;
		
		NameComparator lnComp = new NameComparator();
		int whileLoopCounter = 0;
		int comparisonResult = 0;
		
		while(cur != null) {
			whileLoopCounter++;
			
			comparisonResult = lnComp.compare(newNode, cur);

			//Negative Integer result means new node is less than current node
			if(comparisonResult < 0) {
				//if comparison result is < 0 and this is our first iteration
				//in the while loop, insert new node before head
				if(whileLoopCounter == 1) {
					Node tempHead = head;
					head = newNode;
					head.setNext(tempHead);
					nodeCount++;
					return;
				}
				//insert new node in between prev and current nodes
				else{
					prev.setNext(newNode);
					newNode.setNext(cur);
					nodeCount++;
					return;
				}
			}
		
			// Update our references
			prev = cur;
			cur = cur.getNext();	
			
			//Positive Integer result means new node is greater than current tail node
			//Tail Insert
			if(comparisonResult > 0 && cur == null) {
				prev.setNext(newNode);
				newNode.setNext(null);
				nodeCount++;
			}
			
		}
	}
	
	public void deleteHeadNode(){
		Node temp = head;
		head = head.getNext();
		
		temp.setNext(null);
		
		System.out.println("The entry has been deleted \n");
		nodeCount--;
	}
	
	public boolean deleteNode(String index) {
		
		int deleteIndex = Integer.parseInt(index);
		int localIndexCounter = 0;
		
		Node prev = null;
		Node cur = head;
		
		//Empty List
		if(head == null) {
			System.out.println("There is nothing to delete \n");
			return false;
		}
		
		//Removes Head Node
		if(deleteIndex == 1) {
			deleteHeadNode();
			return true;
		}
		else {
			while(cur != null) {
				localIndexCounter++;
				
				//If deleteIndex matches with current while loop iteration's index:
				//sets previous node to reference the node after current node &
				//sets current node's next to null
				if(deleteIndex == localIndexCounter){
					prev.setNext(cur.getNext());
					cur.setNext(null);
					System.out.println("The entry has been deleted\n");
					nodeCount--;
					return true;
				}
				
				//Update References
				prev = cur;
				cur = cur.getNext();
		}
			//Getting here means tail needs to be deleted
			//references have already been updated making prev the last node
			if(deleteIndex == nodeCount) {
				prev.setNext(null);
				return true;
			}
			
			//Getting here means the entry was not deleted
			System.out.println("Index not found. Entry not deleted \n");
		}
		return false;
	}
	
	//Returns the index entries that match the name argument.
	public String searchNodeName(String name) {
		
		String rtn = "";
		int indexCounter = 0;
		boolean isNameFound = false;
		
		Node t = head;

		//iterates through nodes and compares name to each node's name
		//if the name matches, adds it to the list to print
		while(t != null) {
			indexCounter++;
			int compare = name.compareToIgnoreCase(t.getFullName());
			
			if(compare == 0) {
				isNameFound = true;
				rtn += String.format("%-27s %-27s %-27s %-27s %-27s \n", indexCounter, t.getFullName(), t.getTelephoneNumber(), t.getEmailAddress(), t.getNext());
			}
			t = t.getNext();
		}
		
		//prints the result set if a name was found
		if(isNameFound) {
			String header = String.format("%-27s %-27s %-27s %-27s %-27s", "#", "Full Name", "Telephone Number", "Email Address", "Next");
			System.out.println(header);
			String divider = String.format("%-27s %-27s %-27s %-27s %-27s", "------------------", 
					"------------------", "------------------", "------------------", "------------------");
			System.out.println(divider);
			System.out.println(rtn);
		}
		else {
			System.out.println("That Name is Not in This Telephone List. Sorry. \n");
		}
		
		return rtn;
	}
	
	//Returns the index entries that match the email address
	public String searchNodeEmail(String emailAddress) {
		
		String rtn = "";
		int indexCounter = 0;
		boolean isEmailFound = false;
		
		Node t = head;

		//iterates through nodes and compares email to each node's email
		//if the email matches, adds it to the list to print
		while(t != null) {
			indexCounter++;
			int compare = emailAddress.compareToIgnoreCase(t.getEmailAddress());
			
			if(compare == 0) {
				isEmailFound = true;
				rtn += String.format("%-27s %-27s %-27s %-27s %-27s \n", indexCounter, t.getFullName(), t.getTelephoneNumber(), t.getEmailAddress(), t.getNext());
			}
			t = t.getNext();
		}
		
		//prints the result set if an email address was found
		if(isEmailFound) {
			String header = String.format("%-27s %-27s %-27s %-27s %-27s", "#", "Full Name", "Telephone Number", "Email Address", "Next");
			System.out.println(header);
			String divider = String.format("%-27s %-27s %-27s %-27s %-27s", "------------------", 
					"------------------", "------------------", "------------------", "------------------");
			System.out.println(divider);
			System.out.println(rtn);
		}
		else {
			System.out.println("That Email is Not in This Telephone List. Sorry.\n");
		}
		
		return rtn;
	}
	
	//Serializes the LL object and writes it to the specified file location
	public void writeDatabaseToFile(String filename, LinkedList ll){
		
		FileOutputStream fileOut;

		//uses the path that was read in from restore command
		if(isRestored) {
			try {
				fileOut = new FileOutputStream(restoredFilePath);
				ObjectOutputStream output = new ObjectOutputStream(fileOut);
				output.writeObject(ll);
				output.close();
				fileOut.close();
				System.out.println("The database is successfully written to the file " + restoredFilePath + " \n ");
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		//uses the pathname provided by the user
		else {
			try {
				fileOut = new FileOutputStream(filename);
				ObjectOutputStream output = new ObjectOutputStream(fileOut);
				output.writeObject(ll);
				output.close();
				fileOut.close();
				System.out.println("The database is successfully written to the file " + filename + " \n ");
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Accepts a file pathname to return a deserialized linked list object
	public LinkedList restoreDatabaseFromFile(String filePath){
		
		LinkedList deserializedLL = null;
		restoredFilePath = filePath; //store this path if we want to write to it again
		
		try {
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream input = new ObjectInputStream(fileIn);
			deserializedLL = (LinkedList)input.readObject();
			input.close();
			fileIn.close();
			System.out.println("Database has been restored from location:" + filePath + "\n");
			isRestored = true;
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return deserializedLL;
		
	}
	
	
	//Returns printed list of each node in the LinkedList
	public String print(){
		String rtn = "";
		int indexCounter = 0;
		
		if(nodeCount == 0) {
			rtn = "<Empty>";
		}
		else {
			String header = String.format("%-27s %-27s %-27s %-27s %-27s", "#", "Full Name", "Telephone Number", "Email Address", "Next");
			System.out.println(header);
			
			String divider = String.format("%-27s %-27s %-27s %-27s %-27s", "------------------", 
					"------------------", "------------------", "------------------", "------------------");
			System.out.println(divider);
	
			Node t = head;

			while(t != null) {
				indexCounter++;
				rtn += String.format("%-27s %-27s %-27s %-27s %-27s \n", indexCounter, t.getFullName(), t.getTelephoneNumber(), 
						t.getEmailAddress(), t.getNext());
				t = t.getNext();
			}
		}
		return rtn + "\n";
	}
}
