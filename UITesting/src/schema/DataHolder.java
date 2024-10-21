package schema;

public class DataHolder {
    private String firstName; 
    private String lastName;  
    private String email;     
    private String phone;
    private String buildingName;
	private String buildingNumber;
    private String subBuildingName;
    private String addressline1;
	private String addressline2;
    private String addressline3;
    private String city;
    private String postalcode;
    private String search;
    
    // Getters and setters
    public String getFirstName() {
        return firstName; // Updated to match variable name
    }

    public void setFirstName(String firstName) {
         this.firstName = firstName;
    }

    public String getLastName() {
        return lastName; // Updated to match variable name
    }

    public void setLastName(String lastName) {
        this.lastName = lastName; // Updated to match variable name
    }

    public String getEmail() {
        return email; // Updated to match variable name
    }

    public void setEmail(String email) {
        this.email = email; // Updated to match variable name
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getSubBuildingName() {
		return subBuildingName;
	}

	public void setSubBuildingName(String subBuildingName) {
		this.subBuildingName = subBuildingName;
	}
    
    public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getAddressline3() {
		return addressline3;
	}

	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}
