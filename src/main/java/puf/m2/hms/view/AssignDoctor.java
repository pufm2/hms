package puf.m2.hms.view;

import java.awt.Component;
import java.util.Date;

import puf.m2.hms.model.Patient;
import puf.m2.hms.model.Physician;

public class AssignDoctor extends Component {

	
	private static final long serialVersionUID = 1L;
	
	public AssignDoctor(){
		
	}
	
	public int assign(int patientID, int physicianID, Date dateAffect){
		/*
		 * Return 
		 * -1 if either patientID does not exist
		 * -2 if physicianID does not exist
		 * -3 if physicianID exist, but is not a doctor
		 * -4 if physicianID exist, physician is a doctor, but not available
		 * 1 if patientID exist, and PhysicianID is doctor => insert into database
		 * 
		 */
		int result = 0;
		
		Patient patient = new Patient(patientID);
		if (patient.isExist()==false){
			return -1;
			// Add more statement to notice client
		}
		else {
			Physician physician = new Physician(physicianID); 
			if (physician.isExist()==false){
				return -2;
				// Add more statement to notice client
			}
			else if (physician.isDoctor()==false){
				return -3;
				// Add more statement to notice client
			}
			else if (physician.isAvaiable(dateAffect)==false){
				return -4;
				// Add more statement to notice client
			}
			else {
				// Insert into Assign table
			}
		}
		
		return result;
	}
}
