package codingTest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class PayslipController {
	
	@Autowired
	PayslipService payslipService;
	
	/// Controller For the Employee Class
	@GetMapping("/")
	public String index() {
		return "Hello from the index page, this endpoint has no function for now... try /generate to generate payslips!";
	}
	
	@GetMapping("/generate") 
	@ResponseBody
	public ArrayList<Payslip> generatePayslips(HttpEntity<String> myEntity) throws JsonMappingException, JsonProcessingException {
		return payslipService.getAllPayslips(myEntity);
	}
}
