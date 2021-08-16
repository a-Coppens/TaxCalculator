package codingTest;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.DateFormatSymbols;
import java.time.Year;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;



public class Payslip implements Serializable {
	
	/// Doesn't really matter here but gets rid of the warning
	private static final long serialVersionUID = 1L;

	/// Each Payslip object contains one Employee
	private Employee employee;
	
	/// 
	private List<TaxThreshold> taxThresholds;
	
	/// Calculated based on Employee
	private String fromDate;
	private String toDate;
	private int grossIncome;
	private int incomeTax;
	private int superAmount;
	private int netIncome;

	
	
	public Payslip() { 
		this.employee = new Employee();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			taxThresholds = Arrays.asList(mapper.readValue(Paths.get("src/main/resources/taxInput.json").toFile(), TaxThreshold[].class));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/// Constructor for manual tests
	public Payslip(Employee employee) {
		this.employee = new Employee(employee.getFirstName(), employee.getLastName(), 
						employee.getSalary(), employee.getSuperRate(), employee.getPaymentStartMonth());
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			taxThresholds = Arrays.asList(mapper.readValue(Paths.get("src/main/resources/taxInput.json").toFile(), TaxThreshold[].class));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	public void generatePayslip() {		
		double temp = mapTax().get().doubleValue();
		
		incomeTax =(int) Math.round(temp / 12);
		setPayPeriod();
		grossIncome = calcGrossIncome();
		netIncome = calcNetIncome();
		superAmount = calcSuper();
	}
	
	private void setPayPeriod()
	{
		int maxDayOfMonth;
		int payMonth = this.employee.getPaymentStartMonth();
		
		/// Converts an month int to month string where 0 = January, 11 = December
		String monthString = new DateFormatSymbols().getMonths()[payMonth];
		
		/// Creates a calendar based on the month given
		/// Assumes that the payslip is for the current year
		Calendar calendar = new GregorianCalendar(Year.now().getValue(), payMonth, 1);
		
		/// Finds the maximum number of days in a month based on our calendar
		/// Works for Leap years with the assumption made above
		maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		this.fromDate = "01 " + monthString;
		this.toDate = maxDayOfMonth + " " + monthString;
	}
	
	private int calcGrossIncome() {
		return Math.round(this.employee.getSalary() / 12);
	}
		
	public Optional<BigDecimal> mapTax() {
		BigDecimal income = new BigDecimal(this.employee.getSalary());
		Optional<BigDecimal> tax = taxThresholds.stream().map(t -> t.calculateTax(income)).filter(t -> t != null)
				.findFirst();

		return tax;
	}
	
	private int calcNetIncome() {
		return grossIncome - incomeTax;
	}

	private int calcSuper() {
		
		return (int) (grossIncome * this.employee.getSuperRate());
	}

	public Employee getEmployee() {
		return employee;
	}

	public String getFromDate() {
		return fromDate;
	}
	
	public String getToDate() {
		return toDate;
	}
	
	public int getGrossIncome() {
		return grossIncome;
	}

	public int getIncomeTax() {
		return incomeTax;
	}

	public int getNetIncome() {
		return netIncome;
	}

	public int getSuperAmount() {
		return superAmount;
	}
	
	/// Set Employee values, seems a bit verbose? probably fine?
	public void setFirstName(String firstName) {
		employee.setFirstName(firstName);
	}

	public void setLastName(String lastName) {
		employee.setLastName(lastName);
	}

	public void setSalary(int salary) {
		employee.setSalary(salary);
	}

	public void setPaymentStartDate(int paymentStartDate) {
		employee.setPaymentStartDate(paymentStartDate);
	}

	public void setSuperRate(double superRate) {
		employee.setSuperRate(superRate);
	}
	
	
}
