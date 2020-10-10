public class Polar{

    private double modulus;
    private double argument;

    public Polar(double newMod, double newArg){
        modulus = newMod;
        argument = newArg;
    }

    public static double round100th(double x){
        double n = x * 100;
        double a = Math.round(n);
        return a / (double)100;
    }

    public String toString(){
       	return round100th(modulus)+ "cis(" + round100th(argument) + ")";
    }

    public double getMod(){ return modulus; }
    public double getArg(){ return argument; }
    public void setMod( double newMod ){ modulus = newMod; }
    public void setArg( double newArg ){ argument = newArg; }

    public Complex rectConverter(){
        double a = getMod() * Math.cos(getArg());
	    double b = getMod() * Math.sin(getArg());
        return new Complex(a,b);
    }

   

}
