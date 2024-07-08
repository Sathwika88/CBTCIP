import java.util.Random;
class main{
    static java.util.Scanner sc=new java.util.Scanner(System.in);
    public static void main(String[] args){
        Random rand=new Random();
        int secret_no=rand.nextInt(100);
        System.out.println("Lets start the game");
         
        int total_attempts=5;
        int attempts=0;
        boolean won=false;
        int count=5;
        
        while(attempts<total_attempts){
            attempts+=1;
            int guessed_number=validNum();
            String result=check(guessed_number, secret_no);
            if (result=="won"){
                System.out.println("Sucesss!!!You WON");
				System.out.println("Guessed it right in "+attempts +" attempts");
                System.out.println("You scored "+count*10+" points");
                won=true;
                break;
            }
            else if(result=="low") {
                System.out.println("The number you guessed is low");
            }
            else if(result=="high"){
                System.out.println("The number you guessed is high");
            }
            count--;
        }
        if(!won){
            System.out.println("Sorry, attempts completed!");
            System.out.println("The actual number was "+secret_no);
        }
    }
    public static int validNum(){
            System.out.println("Guess the number between 1 and 100");
            int guess_no=sc.nextInt();
            if(guess_no>100 || guess_no<1){
                System.out.println("Invalid range number selection, try again!!");
                validNum();
            }
            return guess_no; 
    }
    public static String check(int guessed_no, int secret_no){
        if(guessed_no==secret_no){
                return "won";
            }
            else if(guessed_no < secret_no){
                return "low";
        }
            else if(guessed_no> secret_no){
                    return "high";
                }
        return "";
    }
}