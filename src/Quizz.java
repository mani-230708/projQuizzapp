
import java.util.Scanner;

	public class Quizz {

		public static void main(String[] args) {
			part p=new part();
			System.out.println("welcome to the quiz");
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your Name: ");
		    p.setName(sc.next());
			System.out.println("Enter your Age: ");
			p.setAge(sc.nextInt());
			System.out.println("Enter your Number: ");
			p.setNumber(sc.nextInt());
			System.out.println("Enter your Address: ");
			p.setAddress(sc.next());
			System.out.println("Enter your Gender: ");
			p.setGender(sc.next());
			System.out.println("Welcome, " + p.getName() + "!");
			System.out.println("Are you ready to play the quiz (yes/no)? ");
			String str=sc.next();
			if(str.equals("No")) {
				System.exit(0);
			}
			p.rules();
			p.playGame();
		}
	}