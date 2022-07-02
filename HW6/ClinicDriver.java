import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * Provided Driver class.
 */
public class ClinicDriver {

    public static void main(String[] args) {
    	//clinic = Patients.txt file
    	//not sure why this is created?
        Clinic clinic = new Clinic("Patients.txt");
        String dayOneReport = "";
        try {
        	//dayOneReport = formatted Appointments.txt
            dayOneReport = clinic.nextDay("Appointments.txt");
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        //create an array of all formatted Appointments.txt
        String[] dayOneAppointments = dayOneReport.split("\\n");
        //goes through each Appointments.txt line
        for (String appointment : dayOneAppointments) {
        	//either adds the line to the end of Patients.txt
        	//or rewrites all of Patients.txt to append to one pet.
            if (!clinic.addToFile(appointment)) {
                System.out.println("Appointment could not be added to file!");
            }
        }
    }
}
