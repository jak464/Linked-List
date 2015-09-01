package cs342_1;

import java.util.Comparator;

public class NameComparator implements Comparator<Node> {

	@Override
	public int compare(Node nodeOne, Node nodeTwo) {
	
		//get node full names
		String nodeOneFullName = nodeOne.getFullName();
		String nodeTwoFullName = nodeTwo.getFullName();
		
		//grabs last name to add the node to the list sorted alphabetically 
		String nodeOneLastName = nodeOneFullName.substring(nodeOneFullName.lastIndexOf(" ") + 1);
		String nodeTwoLastName = nodeTwoFullName.substring(nodeTwoFullName.lastIndexOf(" ") + 1);

		String nodeOneFirstName = nodeOneFullName.substring(0, nodeOneFullName.indexOf(" "));
		String nodeTwoFirstName = nodeTwoFullName.substring(0, nodeOneFullName.indexOf(" "));
		
/*		compares node last names
		returns neg int if nodeOne is < nodeTwo, returns 0 if equal
		returns pos int if nodeOne is > nodetwo*/
		int result = nodeOneLastName.compareToIgnoreCase(nodeTwoLastName);
		
		//if last names match, need to compare first names
		if(result == 0) {
			result = nodeOneFirstName.compareTo(nodeTwoFirstName);
		}
		
		return result;
	}

}
