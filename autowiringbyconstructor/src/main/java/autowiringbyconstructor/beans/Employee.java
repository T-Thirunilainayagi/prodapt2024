package autowiringbyconstructor.beans;

public class Employee {
	private String empName;
	private Address address;
	//@Autowired
	public Employee (Address address, String empName) {
		this.address = address;
		this.empName = empName;
	}
	public String getEmpName() {
		return empName;
	}
	public Address getAddress() {
		return address;
	}
}
