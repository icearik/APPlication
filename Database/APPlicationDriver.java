import java.util.Scanner;

import src.Database.DataObjects.*;
public class APPlicationDriver {

	static Scanner sc = new Scanner(System.in);
	
	public static void add() {
		System.out.println("In add");
	}
	public static void search() {
		System.out.println("In search");
	}
	public static void view() {
		System.out.println("In view");
	}
	
	public static void main(String[] args) {
		System.out.println("Hello welcome to the APPlication");
		while(true) {
			System.out.print("What would you like to do today?\n1. View All Apps\n2. Search Apps\n3. View An App\n4. Quit\nChoice: ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1: 
				add();
				break;
			case 2: 
				search();
				break;
			case 3: 
				view();
				break;
			case 4: 
				System.exit(0);
				break;
			}
		}

	}

}
