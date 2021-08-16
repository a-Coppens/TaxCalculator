package codingTest;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service 
public class PayslipService {
	
	public ArrayList<Payslip> getAllPayslips(HttpEntity<String> jsonBody) throws JsonMappingException, JsonProcessingException {
			
		String json = jsonBody.getBody();
		ArrayList<Payslip> listOfPayslips = new ObjectMapper().readValue(json, new TypeReference<ArrayList<Payslip>>() {});
		
		for(Payslip payslip : listOfPayslips) {
			payslip.generatePayslip(); 
		}
		return listOfPayslips;
	}
	
}
