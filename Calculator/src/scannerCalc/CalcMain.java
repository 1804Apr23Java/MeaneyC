package scannerCalc;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CalcMain {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("What is the desired input type? ");
		String inputType = sc.nextLine();		

		List<Number> numbers = new ArrayList<Number>();

	
		while (numbers.size() < 2) {
			System.out.println("Input a number: ");
			Number num1;
			switch (inputType) {
			case "short":
				Short short1 = sc.nextShort();
				numbers.add(short1);
				break;
			case "int":
				Integer integer1 = sc.nextInt();
				numbers.add(integer1);
				break;
			case "long":
				Long long1 = sc.nextLong();
				numbers.add(long1);
				break;
			case "float":
				Float float1 = sc.nextFloat();
				numbers.add(float1);
				break;
			case "double":
				Double double1 = sc.nextDouble();
				numbers.add(double1);
				break;
			default:
				try {
					throw new InvalidInputType();
				} catch (InvalidInputType e) {
					// TODO Auto-generated catch block
					//System.out.println(e.getMessage());
					System.exit(0);
				}
			}
			
		}
		
		System.out.println("Please enter an operation: ");
		
		String operation = sc.next();
		
		
		switch (operation) {
		case "+":
			switch (inputType) {
			case "short":
				System.out.println( numbers.get(0).shortValue() + numbers.get(1).shortValue());
				break;
			case "int":
				System.out.println( numbers.get(0).intValue() + numbers.get(1).intValue());
				break;
			case "long":
				System.out.println( numbers.get(0).longValue() + numbers.get(1).longValue());
				break;
			case "float":
				System.out.println( numbers.get(0).floatValue() + numbers.get(1).floatValue());
				break;
			case "double":
				System.out.println( numbers.get(0).doubleValue() + numbers.get(1).doubleValue());
				break;}
		break;
			case "-":
				switch (inputType) {
				case "short":
					System.out.println( numbers.get(0).shortValue() - numbers.get(1).shortValue());
					break;
				case "int":
					System.out.println( numbers.get(0).intValue() - numbers.get(1).intValue());
					break;
				case "long":
					System.out.println( numbers.get(0).longValue() - numbers.get(1).longValue());
					break;
				case "float":
					System.out.println( numbers.get(0).floatValue() - numbers.get(1).floatValue());
					break;
				case "double":
					System.out.println( numbers.get(0).doubleValue() - numbers.get(1).doubleValue());
					break;}
			break;
				case "*":
					switch (inputType) {
					case "short":
						System.out.println( numbers.get(0).shortValue() * numbers.get(1).shortValue());
						break;
					case "int":
						System.out.println( numbers.get(0).intValue() * numbers.get(1).intValue());
						break;
					case "long":
						System.out.println( numbers.get(0).longValue() * numbers.get(1).longValue());
						break;
					case "float":
						System.out.println( numbers.get(0).floatValue() * numbers.get(1).floatValue());
						break;
					case "double":
						System.out.println( numbers.get(0).doubleValue() * numbers.get(1).doubleValue());
						break;}
				break;
					case "/":
						switch (inputType) {
						case "short":
							System.out.println( numbers.get(0).shortValue() / numbers.get(1).shortValue());
							break;
						case "int":
							System.out.println( numbers.get(0).intValue() / numbers.get(1).intValue());
							break;
						case "long":
							System.out.println( numbers.get(0).longValue() / numbers.get(1).longValue());
							break;
						case "float":
							System.out.println( numbers.get(0).floatValue() / numbers.get(1).floatValue());
							break;
						case "double":
							System.out.println( numbers.get(0).doubleValue() / numbers.get(1).doubleValue());
							break;}
					break;
			}
		sc.close();
		}
	}

