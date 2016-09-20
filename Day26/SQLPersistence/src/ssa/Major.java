package ssa;

public class Major {

	private int id;
	private String description;
	private int req_sat;
	
	public Major(){};
	
	public Major(String aDescription) {
		this.description = aDescription;
		this.req_sat=0;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		String msg = String.format("%3d %-20s",
				this.id,
				this.description);
		return msg; 
	}
	
	
}
