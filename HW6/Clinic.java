import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.InputMismatchException;
public class Clinic {
	//declare patientFile variable
	private File patientFile;
	private int day;
	//constructors just create the input file as a file
	public Clinic(File file) {
		//patientFile = input file
		this.patientFile = file;
		this.day = 1;
	}
	public Clinic(String filename) {
		this(new File(filename));
	}
	
	public String nextDay(String fileName) throws FileNotFoundException {
        return nextDay(new File(fileName));
    }
	
	public String nextDay(File f) throws FileNotFoundException {
		//read file f (appointments.txt) that contains 
		//APPOINTMENTS FILE (input)
		//PATIENTS FILE (all Day X) (output)
		Scanner inFile = new Scanner(f);
		Scanner userIn = new Scanner(System.in);
		String nextDayOutput = "";
		while (inFile.hasNext()) {
			String lineIn = inFile.next();
			String[] appointmentArray = lineIn.split(",");
			String petName = appointmentArray[0];
			String petType = appointmentArray[1];
			double miceDrool = Double.parseDouble(appointmentArray[2]);
			int timeIn = Integer.parseInt(appointmentArray[3]);
			int timeOut = 0;
			int treatDuration = 0;
			int petPainLevel = 1;
			double petHealth = 0.0;
			if (!petType.equals("Cat") && !petType.equals("Dog")) {
				inFile.close();
				userIn.close();
				throw new InvalidPetException();
			}
			System.out.printf("Consultation for %s the %s at %d.\n", petName, petType, timeIn);
			boolean isANumber = false;
			while (!isANumber) {
				try {
					System.out.printf("What is the health of %s?\n", petName);
					petHealth = userIn.nextDouble();
					isANumber = true;
				}
				catch (InputMismatchException e){
					userIn.nextLine();
					System.out.println("Please enter  a number.");
				}
			}
			isANumber = false;
			while (!isANumber) {
				try {
					System.out.printf("On a scale of 1 to 10, how much pain is %s in right now?\n", petName);
					petPainLevel = userIn.nextInt();
					isANumber = true;
				}
				catch (InputMismatchException e){
					userIn.nextLine();
					System.out.println("Please provide a number.");
				}
			}
			if (petType.equals("Cat")) {
				Cat userCat = new Cat(petName, petHealth, petPainLevel, (int)miceDrool);
				userCat.speak();
				//I prefer this over having everything be 1,1.0
				petPainLevel = userCat.getPainLevel();
				petHealth = userCat.getHealth();
				treatDuration = userCat.treat();
			}
			else {
				Dog userDog = new Dog(petName, petHealth, petPainLevel, miceDrool);
				petPainLevel = userDog.getPainLevel();
				petHealth = userDog.getHealth();
				userDog.speak();
				treatDuration = userDog.treat();
			}
			timeOut = addTime(timeIn, treatDuration);
			nextDayOutput += String.format("%s,%s,%.2f,Day %s,%d,%d,%.2f,%d\n",
					petName,petType,miceDrool,day,timeIn,timeOut,petHealth,petPainLevel);
		}
		inFile.close();
		userIn.close();
		day++;
		return nextDayOutput;
	}
	public boolean addToFile(String patientInfo) {
		String totalFile = "";
		//patientInfo = formatted appointments.txt line
		System.out.println(patientInfo);
		String[] newPatient = patientInfo.split(",");
		String samePatient = String.format(",%s,%s,%s,%s,%s", 
				newPatient[3], newPatient[4], newPatient[5], 
				newPatient[6], newPatient[7]);
		boolean repeatPatient = false;
		try {
			//fileScan = one line of patients.txt
			Scanner fileScan = new Scanner(patientFile);
			while (fileScan.hasNextLine()) {
				//tempPatient = patients.txt line
				String tempPatient = fileScan.nextLine();
				//oldPatient = patients.txt array
				String[] oldPatient = tempPatient.split(",");
				//if name is same, go through every line and add it to a string
				//then append to the pet that has the same name
				//instead of only appending to the end of the file
				if (newPatient[0].equals(oldPatient[0])) {
					repeatPatient = true;
					totalFile += tempPatient + samePatient + "\n";
				}
				else {
					totalFile += tempPatient + "\n";
				}
			}
			fileScan.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		try {
			if (repeatPatient) {
				//rewrite whole file
				FileWriter writePatients = new FileWriter("Patients.txt");
				writePatients.write(totalFile);
				writePatients.close();
			}
			else {
				//append to file
				FileWriter writePatients = new FileWriter("Patients.txt", true);
				writePatients.write(patientInfo + "\n");
				writePatients.close();
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	private int addTime(int timeInAdd, int treatmentTime) {
		if ((timeInAdd % 100) + treatmentTime >= 60) {
			//if (1)23 + 52 >= 60:
			//(1)23 = (1)23 + 52 + 40 
			//(2)15
			timeInAdd += treatmentTime + 40;
		}
		else {
			timeInAdd += treatmentTime;
		}
		return timeInAdd;
	}
}
