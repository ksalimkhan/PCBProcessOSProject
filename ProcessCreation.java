import java.util.*;
import java.util.LinkedList;

public class ProcessCreation {
	//Global variables and structures
	int parent;
	LinkedList<Integer> children;
	static ProcessCreation[] PCBlist;
	
	public ProcessCreation() {}
	public static void main(String[] args) {
		System.out.println("Process creation and destruction");
		System.out.println("-------------------------------");
		System.out.println("1) Enter Parameters\n2) Create a new child process\n3) Destroy all descendants of a process\n4) Quit program and free memory\n");
		Scanner scanner = new Scanner(System.in);
		int input = -1;
		while(input != 4) {
			System.out.print("Enter selection: ");
			input = scanner.nextInt();
			switch(input) {
				case 1:
					optionOne(scanner);
					break;
				case 2:
					optionTwo(scanner);
					break;
				case 3:
					optionThree(scanner);
					break;
				case 4:
					optionFour();
					System.out.println("Qutting program...");
					System.exit(0);
					break;
				default:
					break;
					
			}
			if(input != 4) {
				System.out.println("Process creation and destruction");
				System.out.println("-------------------------------");
				System.out.println("1) Enter Parameters\n2) Create a new child process\n3) Destroy all descendants of a process\n4) Quit program and free memory\n");
				
			}
		}
		scanner.close();
		
	}
	
	public static void print() {
		for(int i = 0; i < PCBlist.length; ++i) {
			if(PCBlist[i].children != null && PCBlist[i].children.size() > 0 ) {
				System.out.print("PCB[" + i + "] is the parent of: ");
				LinkedList<Integer> head = PCBlist[i].children;
				for(int j = 0; j < head.size(); ++j) {
					System.out.print("PCB[" + head.get(j) + "] ");
					
				}
				System.out.println();
				
			}
		}
		System.out.println();
	
	}
	public static void optionOne(Scanner scanner) { //parameters method
		System.out.printf("Enter maximum number of processes: ");
		int index = scanner.nextInt();
		PCBlist = new ProcessCreation[index];
		for(int i = 0; i < index; i++) {
			PCBlist[i] = new ProcessCreation();
			PCBlist[i].parent = -1;
			PCBlist[i].children = new LinkedList<Integer>();
			
		}
		PCBlist[0].parent = 0;
		return;
	
	}
	public static void optionTwo(Scanner scanner) { //create child method
		int q = 0;
		int p;
		System.out.print("Enter the parent process index: ");
		p = scanner.nextInt();
		while(q < PCBlist.length && PCBlist[q] != null && PCBlist[q].parent != -1){
			++q;
			
		}
		PCBlist[q].parent = p;
		PCBlist[q].children = new LinkedList<Integer>();
		PCBlist[p].children.add(q);
		print();
		return;
	
	}
	public static void optionThree(Scanner scanner) { //destroy method
		int p;
		System.out.printf("Enter the index of the process whose descendants are to be destroyed: ");
		p = scanner.nextInt();
		destroy(p);
		print();
		return;
		
	}
	public static void destroy(int p) {
		Integer q;
		if(PCBlist[p].children.isEmpty()) {
			return;
		
		} else {
			for(int i = 0; i < PCBlist[p].children.size(); ++i) {
				q = PCBlist[p].children.get(i);
				ProcessCreation childProcess = PCBlist[q];
				childProcess.parent = -1;
				childProcess.children = null;
				PCBlist[p].children.remove(i);
				--i;
			
			}
		}
		return;
		
	}
	public static void optionFour() { //quit program method
		if(PCBlist != null) {
			if(PCBlist[0].children != null) {
				destroy(0);
				
			}
		}
		return;
		
	}
}
