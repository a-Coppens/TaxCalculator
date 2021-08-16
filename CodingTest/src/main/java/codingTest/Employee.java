package codingTest;

public class Employee {

	/// User Input Variables
	private String firstName;
	private String lastName;
	private int salary;
	private int paymentStartMonth;
	private double superRate; 

	
	/// Constructor for manual tests
	public Employee(String fName, String lName, int salary, double superRate, int startMonth) {
		this.firstName = fName;
		this.lastName = lName;
		this.salary = salary;
		this.paymentStartMonth = startMonth;
		this.superRate = superRate;
	}
	
	public Employee() {};

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getSalary() {
		return salary;
	}

	public int getPaymentStartMonth() {
		return paymentStartMonth;
	}

	public double getSuperRate() {
		return superRate;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setPaymentStartDate(int paymentStartDate) {
		this.paymentStartMonth = paymentStartDate;
	}

	public void setSuperRate(double superRate) {
		this.superRate = superRate;
	}

}
