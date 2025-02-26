package CruzeX.webapp.Dao;

import CruzeX.webapp.Dao.DbConnector;
import CruzeX.webapp.Dao.MySqlConnectorImpl;

import CruzeX.webapp.Dao.DbConnectorFactory;

public class MySqlDbConnectorFactoryImpl implements DbConnectorFactory{
	
	@Override
	public DbConnector getDbConnector() {
		
		return new MySqlConnectorImpl();
		
	}

}
