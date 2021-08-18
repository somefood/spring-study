package chap07;

public class MainProxy {

	public static void main(String[] args) {
		Calculator impeCal = new ImpeCalculator();
		Calculator reCal = new RecCalculator();
		ExeTimeCalculator exe1 = new ExeTimeCalculator(impeCal);
		ExeTimeCalculator exe2 = new ExeTimeCalculator(reCal);
		
		System.out.println(exe1.factorial(20));
		System.out.println(exe2.factorial(20));
	}

}
