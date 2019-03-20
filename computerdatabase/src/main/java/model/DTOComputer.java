package model;


public class DTOComputer {

	public long id;
	public String name;
	public String introduced, discontinued;
	public long companyId;
	
	@Override
	public String toString() {
		return "id : "+id
				+ " name : " + name
				+ " introduced : "+introduced
				+ " diconstinued : "+discontinued
				+ " companyId : "+ companyId;
	}
}
