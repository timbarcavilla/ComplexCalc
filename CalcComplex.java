import userInput.Keyboard;
import java.util.List;

public class CalcComplex{

    private boolean exit; //boolean value to control the exiting of the game
    private Complex currC; //current number
    private boolean isDegree; //boolean value to control the angle mode

    //constructor
    public CalcComplex(){
		exit = false;
		isDegree = false;
    }//end constructor

    //helper method to round a number to hundreth place
    public static double round100th(double x){
		double n = x * 100;
		double a = Math.round(n);
		return a / (double)100;
    }//end round100th(double x)

    //helper method to change a radian measure to degrees. Input in radians
    public static double toDegrees(double theta){
		return round100th(theta * 180.0/Math.PI);
    }//end toDegrees(double theta)

    //helper method to change a degree measure to radians. Input in degrees
    public static double toRadians(double theta){
		return round100th(theta * Math.PI/180.0);
    }//end toRadians(double theta)

    //private method to print msg if user chooses to input number in rectangular form.
    private Complex rectInput(){
		String msg = "";
		msg += "Input your real part:";
		System.out.println(msg);
		double real = Keyboard.readDouble();
		msg = "\nInput your imaginary part:";
		System.out.println(msg);
		double imag = Keyboard.readDouble();
		return new Complex(real,imag);
    }//end rectInput()

    //in polar form
    private Polar polarInput(){
		String msg = "";
		msg += "Input your modulus (radius): ";
		System.out.println(msg);
		double modulus = Keyboard.readDouble();
		double argument;
		if (!isDegree){
			msg = "\nDo you wish to input your angle in terms of pi?\n";
			msg += "\t1) Yes\n";
			msg += "\t2) No\n";
			System.out.println(msg);
			int mode = 0;
			while (mode == 0){
				mode = Keyboard.readInt();
				if (mode < 1 || mode > 2)
					mode = 0;
			}
			msg = "\nInput your argument (angle in RADIANS):";
			System.out.println(msg);
			if (mode == 1)
				argument = round100th(Keyboard.readDouble() * Math.PI);
			else
				argument = round100th(Keyboard.readDouble());
		}
		else{
			msg = "\nInput your argument (angle in DEGREES): ";
			System.out.println(msg);
			argument = toRadians(Keyboard.readDouble());
		}
		return new Polar(modulus,argument);
    }//end polarInput()

    //first set of messages when the program is executed
    public void setup(){
	
		int mode1 = 0;
		System.out.println("In what form is your input number gonna be?");
		String msg = "";
		msg += "\t1) Rectangular\n";
		msg += "\t2) Polar\n";
		System.out.println(msg);
		while (mode1 == 0){
			mode1 = Keyboard.readInt();
			if (mode1 == 1){
				currC = rectInput();
			}
			else if (mode1 == 2){
				currC = polarInput().rectConverter();
				System.out.println("Your number has been automatically converted to rectangular form.");
			}
			else{
				mode1 = 0;
				System.out.println("Invalid Input. Try again:");
			}
		}
    }//end setup

    //the main menu
    public void action(){
		while (!exit) {
			System.out.println("\n\n//---------------------");
			System.out.println("YOUR NUMBER IS " + currC);
			if (isDegree)
				System.out.println("You are currently in degree mode.");
			else
				System.out.println("You are currently in radian mode.");
			System.out.println("//---------------------");
			System.out.println("\nWhich operation would you like to do?");

			String msg = "";

			msg += "\t1) Convert to Polar form\n";
			msg += "\t2) Addition\n";
			msg += "\t3) Subtraction\n";
			msg += "\t4) Multiplication\n";
			msg += "\t5) Division\n";
			msg += "\t6) Find nth power\n";
			msg += "\t7) Find nth root(s)\n";
			msg += "\t8) Reset your number\n";
			msg += "\t9) Change angle unit\n";
			msg += "\t10) Exit\n";
			System.out.println(msg);
			int mode = Keyboard.readInt(); //user's input

			switch (mode) {
				case 1:
					convertPolar();
					break;
				case 2:
					add();
					break;
				case 3:
					subtract();
					break;
				case 4:
					multiply();
					break;
				case 5:
					divide();
					break;
				case 6:
					power();
					break;
				case 7:
					root();
					break;
				case 8:
					setup();
					System.out.println("Reset successfully.");
					break;
				case 9:
					isDegree = !isDegree;
					System.out.println("Success.");
					break;
				case 10:
					exit = true;
					break;
				default:
					System.out.println("Invalid input.");
			}
		}
	


    }//end action()

	private void convertPolar(){
		System.out.println("Converting...100%");
		Polar retP = currC.polarConverter();
		if (isDegree)
			retP.setArg(toDegrees(retP.getArg()));
		System.out.println("The polar form of your number is\t" + retP);
	}

	private void add(){
		Complex num1,num2;

		num1 = currC;
		num2 = rectInput();

		System.out.println("Your number is\t" + num1 );
		System.out.println("You are trying to add\t" + num2);
		System.out.println("Adding...100%");
		num1 = num1.add(num2);

		currC = num1;
		System.out.println("Your answer is\t" + currC);
		System.out.println("Your number has been set to the answer.");
	}

	private void subtract(){
		Complex num1,num2;

		num1 = currC;
		num2 = rectInput();

		System.out.println("Your number is\t" + num1 );
		System.out.println("You are trying to subtract\t" + num2);
		System.out.println("Subtracting...100%");
		num1 = num1.subtract(num2);

		currC = num1;
		System.out.println("Your answer is\t" + currC);
		System.out.println("Your number has been set to the answer.");
	}

	private void multiply(){
		Complex num1,num2;

		num1 = currC;
		num2 = rectInput();

		System.out.println("Your number is\t" + num1 );
		System.out.println("You are trying to multiply by\t" + num2);
		System.out.println("Multipling...100%");
		num1 = num1.multiply(num2);

		currC = num1;
		System.out.println("Your answer is\t" + currC);
		System.out.println("Your number has been set to the answer.");
	}

	private void divide(){
		Complex num1,num2;

		num1 = currC;
		num2 = rectInput();

		System.out.println("Your number is\t" + num1 );
		System.out.println("You are trying to divide by\t" + num2);
		System.out.println("Dividing...100%");
		num1 = num1.divide(num2);

		currC = num1;
		System.out.println("Your answer is\t" + currC);
		System.out.println("Your number has been set to the answer.");
	}

	private void power(){
		Complex num;
		double power;
		num = currC;

		System.out.println("To what power?");
		power = Keyboard.readDouble();
		System.out.println("Powering up...100%");
		num = num.powerUp(power);

		currC = num;
		System.out.println("Your answer is\t" + currC);
		System.out.println("Your number has been set to the answer.");
	}

	private void root(){
		List<Complex> roots;
		double power;
		Complex num = currC;

		System.out.println("To what root?");
		power = Keyboard.readDouble();
		System.out.println("Powering down...100%");
		roots = num.powerDown(power);

		System.out.println("Your answer is\t" + roots);
	}

    //main method
    public static void main(String[] args){

		CalcComplex c = new CalcComplex();

		System.out.println("Loading... 100%");
		System.out.println("Welcome to Complex Number Calculator");

		c.setup();
		c.action();

		System.out.println("Exiting...100%\nHave a good day.");
    }//end main

}//end class CalcComplex
    
