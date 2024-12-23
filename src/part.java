
	import java.util.Random;
	import java.util.Scanner;
	import java.util.InputMismatchException;

	
	public class part {
	    private String Name;
	    private int Age;
	    private int Number;
	    private String Address;
	    private String Gender;
	    private int score = 0;
	    private boolean isFiftyFiftyUsed = false;
	    private boolean isAudiencePollUsed = false;
	    public void setName(String Name) {
	        this.Name = Name;
	    }

	    public String getName() {
	        return Name;
	    }

	    public void setAge(int Age) {
	        this.Age = Age;
	    }

	    public int getAge() {
	        return Age;
	    }

	    public void setNumber(int Number) {
	        this.Number = Number;
	    }

	    public int getNumber() {
	        return Number;
	    }

	    public void setAddress(String Address) {
	        this.Address = Address;
	    }

	    public String getAddress() {
	        return Address;
	    }

	    public void setGender(String Gender) {
	        this.Gender = Gender;
	    }

	    public String getGender() {
	        return Gender;
	    }
	    public void rules() {
	        System.out.println("1. Each question has four options: A, B, C, and D. Choose the correct answer by typing the corresponding letter.");
	        System.out.println("2. For every correct answer, you will earn 10 points. There are no points for incorrect answers.");
	        System.out.println("3. You have two lifelines to assist you during the game:");
	        System.out.println("   - The 50-50 Lifeline removes two incorrect options, leaving one correct and one incorrect option.");
	        System.out.println("   - The Audience Poll Lifeline shows audience voting percentages for each option to help you decide.");
	        System.out.println("4. Each lifeline can only be used once during the game.");
	        System.out.println("5. If you answer incorrectly, the game ends immediately, and your final score will be displayed.");
	        System.out.println("6.Welcome to the Quiz Game! Your goal is to answer all the questions correctly and score the highest points.");
	        System.out.println("-Try your best and enjoy the game! Good luck!-");
	    }

	    public void useFiftyFifty(String[] options, char correctAnswer) {
	        if (isFiftyFiftyUsed) {
	            System.out.println("The 50-50 lifeline is no longer available. Choose a different option.");
	            return;
	        }

	        Random random = new Random();
	        int correctIndex = correctAnswer - 'A';
	        boolean[] displayed = new boolean[options.length];
	        displayed[correctIndex] = true; 
	        int removed = 0;

	        while (removed < 1) { 
	            int randomIndex = random.nextInt(options.length);
	            if (randomIndex != correctIndex && !displayed[randomIndex]) {
	                displayed[randomIndex] = true;
	                removed++;
	            }
	        }

	        System.out.println("50-50 Lifeline activated! Here are your remaining choices:");
	        for (int i = 0; i < options.length; i++) {
	            if (displayed[i]) {
	                System.out.println("Option " + (char) ('A' + i) + ": " + options[i]);
	            }
	        }

	        isFiftyFiftyUsed = true;
	    }
	    public void useAudiencePoll(String[] options, char correctAnswer) {
	        if (isAudiencePollUsed) {
	            System.out.println("Sorry, the Audience Poll option is no longer available.");
	            return;
	        }

	        Random random = new Random();
	        int correctIndex = correctAnswer - 'A';
	        int[] poll = new int[options.length];
	        int remaining = 100;

	        for (int i = 0; i < poll.length; i++) {
	            if (i == correctIndex) {
	                poll[i] = 50 + random.nextInt(21);
	            } else {
	                poll[i] = random.nextInt(remaining / 2);
	            }
	            remaining -= poll[i];
	        }

	        poll[correctIndex] += remaining;

	        System.out.println("Audience Poll Results:");
	        for (int i = 0; i < options.length; i++) {
	            System.out.println("Option " + (char) ('A' + i) + ": " + poll[i] + "%");
	        }

	        isAudiencePollUsed = true;
	    }
	    public void useLifeline(String[] options, char correctAnswer, Scanner scanner) {
	    	try {
	        System.out.println("\nSelect a lifeline:");
	        System.out.println("1. 50-50");
	        System.out.println("2. Audience Poll");
	        String choice = scanner.nextLine().trim();

	        if (choice.equals("1")) {
	            useFiftyFifty(options, correctAnswer);
	        } else if (choice.equals("2")) {
	            useAudiencePoll(options, correctAnswer);
	        } else {
	            System.out.println("Invalid choice. No lifeline used.");
	        }
	    }
	    	catch (Exception e) {
	            System.out.println("An error occurred while using a lifeline: " + e.getMessage());
	        }
	    }
	    public void askQuestion(String question, String[] options, char correctAnswer, Scanner scanner) {
	    	try {
	        System.out.println("\n" + question);
	        for (int i = 0; i < options.length; i++) {
	            System.out.println("Option " + (char) ('A' + i) + ": " + options[i]);
	        }
	        System.out.println("Option E: Lifeline");
	        System.out.println("Option F: Quit");

	        System.out.println("\nEnter your answer (A, B, C, D, E, or F):");
	        String answer = scanner.nextLine().trim();

	        if (answer.equalsIgnoreCase("F")) {
	            System.out.println("You chose to quit the game.");
	            System.out.println("Your Final score is: " + score);
	            System.exit(0);
	        } else if (answer.equalsIgnoreCase("E")) {
	            useLifeline(options, correctAnswer, scanner);
	            askQuestion(question, options, correctAnswer, scanner);
	        } else if (answer.equalsIgnoreCase(String.valueOf(correctAnswer))) {
	            System.out.println("Fantastic!You’ve got the right answer2");
	            score += 10;
	            System.out.println("you're one step closer to your goal! your score is " + score);
	        } else {
	            System.out.println("Better luck next time! The correct answer was: Option " + correctAnswer);
	            System.out.println("Game Over.You wrapped up with a final score of:" + score+"well played!");
	            System.exit(0);
	        }
	    }
	    	catch (Exception e) {
	            System.out.println("An error occurred while processing your answer: " + e.getMessage());
	        }
	    }
	    public void playGame() {
	    	try {
	    	Scanner scanner = new Scanner(System.in);
	        askQuestion("Who made the highest centuries in cricket?", 
	                    new String[]{"Virat Kohli", "Dhoni", "Rohit Sharma", "Sachin"}, 'A',scanner);
	        askQuestion("Who won the national award in 2024?", 
	                    new String[]{"Mahesh Babu", "NTR", "Allu Arjun", "Prabhas"}, 'C',scanner);
	        askQuestion("Who has the most goals in football history?", 
	                    new String[]{"Cristiano Ronaldo", "Lionel Messi", "Pelé", "Diego Maradona"},'A',scanner);
	        askQuestion("Who is known as the King of Pop?", 
	                    new String[]{"Michael Jackson", "Elvis Presley", "Freddie Mercury", "Justin Timberlake"},'A',scanner);
	        askQuestion("Who was the first woman to win the Nobel Prize?", 
	                    new String[]{"Marie Curie", "Dorothy Crowfoot Hodgkin", "Rosalind Franklin", "Ada Lovelace"},'A',scanner);
	        askQuestion("Who won the 2023 IPL title?", 
	                    new String[]{"Royal Challengers Bengaluru", "Gujarat Titans", "Chennai Super Kings", "Rajasthan Royals"},'B',scanner);
	 	}
	    	catch (Exception e) {
	            System.out.println("An error occurred during the game: " + e.getMessage());
	        
	    	}
	    }
	}
