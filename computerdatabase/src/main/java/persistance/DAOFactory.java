package persistance;


public class DAOFactory {
	
	private static DAOCompany daoCompany;
	private static DAOComputer daoComputer;
	
	public DAOFactory() {
		
	}
	
	
	public static DAOCompany createDAOcompany(){
		if(daoCompany == null) {
			daoCompany = new DAOCompany();
		}
		return daoCompany;
	}
	
	public static DAOComputer createDAOcomputer(){
		if(daoComputer == null) {
			daoComputer = new DAOComputer();
		}
		return daoComputer;
	}
	
	
}