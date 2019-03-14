package persistance;

public class DAOFactory {

	public DAOFactory() {
		
	}
	
	public static DAOEntity create(String entity) {
		switch (entity) {
		case "computer":
			return createDAOcompany();
		case "campany":
			return createDAOcomputer();
		default:
			return null;
		}
	}
	
	private static DAOCompany createDAOcompany(){
		return new DAOCompany();
	}
	
	private static DAOComputer createDAOcomputer(){
		return new DAOComputer();
	}
	
}