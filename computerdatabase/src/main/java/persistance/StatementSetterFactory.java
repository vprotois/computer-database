package persistance;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;

import model.Computer;

public class StatementSetterFactory {
	
	private static Logger log= LoggerFactory.getLogger(StatementSetterFactory.class);

	static PreparedStatementSetter statementId(Long id) {
		return new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1,id);
			}
		};
	}

	static PreparedStatementSetter statementUpdateComputer(Computer c) throws SQLException {
		return new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,c.getName());
				ps.setTimestamp(2,c.getIntroduced());
				ps.setTimestamp(3,c.getDiscontinued());
				if(c.getCompanyId() != null) {
					ps.setLong(4,c.getCompanyId());					
				}else {
					ps.setNull(4, Types.BIGINT);
				}
				if(c.getId() != null) {
					ps.setLong(5,c.getId());					
				}else {
					ps.setNull(5, Types.BIGINT);
				}
			}
		};
	}

	static PreparedStatementSetter statementInsertComputer(Computer c) throws SQLException {
		return new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				log.debug("STATEMENT FACTORY"+c);
				if(c.getId() != null) {
					ps.setLong(1,c.getId());					
				}else {
					ps.setNull(1, Types.BIGINT);
				}
				ps.setString(2,c.getName());
				ps.setTimestamp(3,c.getIntroduced());
				ps.setTimestamp(4,c.getDiscontinued());
				if(c.getCompanyId() != null) {
					ps.setLong(5,c.getCompanyId());					
				}else {
					ps.setNull(5, Types.BIGINT);
				}
			}

		};
	}

}
