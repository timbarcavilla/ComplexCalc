import java.util.ArrayList;
import java.util.List;

public class Complex {
   
    private final double realPart;
    private final double imagPart;
   
    // CONSTRUCTOR
    public Complex( double newReal, double newImag ){
		realPart = round100th(newReal);
		imagPart = round100th(newImag);
    }

    //rounding decimals to 100th place;

    //------------------HELPER-----------------

    public static double round100th(double x){
		double n = x * 100;
		double a = Math.round(n);
		return a / (double)100;
    }
    // overwritten toString (to tell computer how to print a Complex)
   
    public String toString(){
		String operation = "";
		double m = round100th(realPart);
		double n = round100th(imagPart);
		if (n >= 0)
			operation = "+";
		else
			operation = "-";
		if (n == 0)
			return m + "";
		else if (m == 0)
			return n + "i";
		else if (n == 1 || n == -1)
			return m + operation + "i";
		else
			return m + operation + Math.abs(n) + "i";
    }
    
   
   
    //-----------ACCESSORS----------------
   
    public double getReal(){ return realPart; }
    public double getImag(){ return imagPart; }
   
    //-------------------------------------------------

    //convert a complex number to polar form
    public Polar polarConverter(){
		double r, theta = 0;
		r = Math.sqrt(Math.pow(getReal(), 2.0) + Math.pow(getImag(), 2.0));
		if (getReal() != 0)
			theta = Math.atan(getImag()/getReal());
		else if (getImag() != 0)
			theta = Math.PI/2.0;
		if (getReal() < 0)
			theta += Math.PI;
		else if (getImag() < 0){
			if (getReal() == 0)
				theta += Math.PI;
			else
				theta += 2.0 * Math.PI;
		}

		return new Polar(r,theta);
    }
    
    //add
    public Complex add(Complex a){
		double newReal = a.getReal() + getReal();
		double newImag = a.getImag() + getImag();
		return new Complex(newReal,newImag);
    }
    
    //subtract
    public Complex subtract(Complex a){
		double newReal = getReal() - a.getReal();
		double newImag = getImag() - a.getImag();
		return new Complex(newReal,newImag);
    }

    //multiply
    public Complex multiply(Complex a){
		double newReal = (a.getReal() * getReal()) - (a.getImag() * getImag());
		double newImag = (a.getReal() * getImag()) + (a.getImag() * getReal());
		System.out.println(getImag());
		System.out.println(a.getImag());
		return new Complex(newReal,newImag);
    }

    //divide
    public Complex divide(Complex a){
		Complex n = new Complex(a.getReal(), a.getImag() * -1);
		Complex numerator = multiply(n);
		double denominator = Math.pow(a.getReal(),2.0) + Math.pow(a.getImag(),2.0);
		double newReal = numerator.getReal() / denominator;
		double newImag = numerator.getImag() / denominator;
		return new Complex(newReal,newImag);
    }

    // power function helper
    private Polar yahPower(double power){
       	Polar p = polarConverter();
		p.setMod(Math.pow(p.getMod(),power));
		double arg = p.getArg() * power;
		while (!(arg >= 0 && arg < 2 * Math.PI))
			arg -= 2 * Math.PI;
		p.setArg(arg);
		return p;
    }

    //find nth power
    public Complex powerUp(double power){
		Polar p = yahPower(power);
		return p.rectConverter();
    }

    //find nth roots
    public List<Complex> powerDown(double root){
		List<Complex> retL = new ArrayList<>();
		double angleIncre = Math.toRadians(360)/root;
		double power = 1.0/root;
		Polar p = yahPower(power);
		double currAngle = p.getArg();
		for (int x = 0; x < root; x++){
			Complex c = p.rectConverter();
			retL.add(c);
			currAngle += angleIncre;
			p.setArg(currAngle);
		}
		return retL;
    }

}//end class Complex
