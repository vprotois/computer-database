package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mapper.CompanyMapper;
import model.Company;

@Component
public class DAOCompany {

	private static Logger log = LoggerFactory.getLogger(DAOCompany.class);
	
	@Autowired
	private DataSource dataSource;

	private static final String selectAll = "SELECT id,name FROM company;";
	private static final String selectId = "SELECT id,name FROM company WHERE id = ?;";
	private static final String deleteFromId = "DELETE FROM company WHERE id = ?;";
	private static final String deleteComputers = "DELETE FROM computer WHERE company_id = ?;";

	public DAOCompany() {
	}

	public Optional<Company> getCompany(Long id) {
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(selectId);
			stmt.setLong(1, id);
			ResultSet results = stmt.executeQuery();

			return CompanyMapper.mapSingleCompany(results);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			log.error("Error when getting Company :" + id);
		}
		return Optional.empty();
	}

	public Optional<List<Company>> getCompanies() {
		try (Connection conn = dataSource.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(selectAll);
			ResultSet results = stmt.executeQuery();
			return Optional.of(CompanyMapper.mapCompaniesList(results));
		} catch (SQLException e) {
			log.error("Error when getting company list :" + e.getMessage());
		}
		return Optional.empty();
	}

	public void deleteCompany(Long id) {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			try {

				conn.setAutoCommit(false);
				deleteComputerUpdate(id, conn);
				deleteCompanyUpdate(id, conn);
				conn.commit();

			} catch (SQLException e) {
				if (conn != null) {
					conn.rollback();
				}
				log.error("Error when deleting company : " + e.getMessage());
			} finally {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void deleteCompanyUpdate(Long id, Connection conn) throws SQLException {
		PreparedStatement stmt;
		stmt = conn.prepareStatement(deleteFromId);
		stmt.setLong(1, id);
		stmt.executeUpdate();
	}

	private void deleteComputerUpdate(Long id, Connection conn) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(deleteComputers);
		stmt.setLong(1, id);
		stmt.executeUpdate();
	}

}
