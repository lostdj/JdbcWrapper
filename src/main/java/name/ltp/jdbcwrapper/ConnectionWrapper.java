package name.ltp.jdbcwrapper;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

public class ConnectionWrapper implements Connection, AutoCloseable
{
	public Connection c;
	public final long time;
	public final StackTraceElement[] stack;

	public final List<StatementWrapper> statements = Collections.synchronizedList(new LinkedList<StatementWrapper>());

	protected ConnectionWrapper(Connection c)
	{
		this.c = c;
		time = System.currentTimeMillis();
		stack = Thread.currentThread().getStackTrace();

		synchronized(JdbcWatcher.i().cons)
		{
			JdbcWatcher.i().cons.add(this);
		}
	}

	public void close() throws java.sql.SQLException
	{
		if(statements.size() > 0)
			try
			{
				ListIterator<StatementWrapper> i = statements.listIterator(statements.size() - 1);
				if(i.hasNext())
					try
					{
						i.next().close();
						i.remove();
					}
					catch(Exception e)
					{Err.i().err(e.toString());}
				while(i.hasPrevious())
					try
					{
						i.previous().close();
						i.remove();
					}
					catch(Exception e)
					{Err.i().err(e.toString());}
			}
			catch(Exception e)
			{Err.i().err(e.toString());}

		if(c != null)
			c.close();

		c = null;
	}

	public void setAutoCommit(boolean autoCommit) throws java.sql.SQLException
	{
		c.setAutoCommit(autoCommit);
	}

	public boolean getAutoCommit() throws java.sql.SQLException
	{
		return c.getAutoCommit();
	}

	public void setCatalog(String catalog) throws java.sql.SQLException
	{
		c.setCatalog(catalog);
	}

	public String getCatalog() throws java.sql.SQLException
	{
		return c.getCatalog();
	}

	public boolean isClosed() throws SQLException
	{
		return c.isClosed();
	}

	public void setHoldability(int arg0) throws SQLException
	{
		c.setHoldability(arg0);
	}

	public int getHoldability() throws SQLException
	{
		return c.getHoldability();
	}

	public java.sql.DatabaseMetaData getMetaData() throws java.sql.SQLException
	{
		return c.getMetaData();
	}

	public void setReadOnly(boolean readOnly) throws java.sql.SQLException
	{
		c.setReadOnly(readOnly);
	}

	public boolean isReadOnly() throws java.sql.SQLException
	{
		return c.isReadOnly();
	}

	public java.sql.Savepoint setSavepoint() throws SQLException
	{
		return c.setSavepoint();
	}

	public java.sql.Savepoint setSavepoint(String arg0)
		throws SQLException
	{
		return c.setSavepoint(arg0);
	}

	public void setTransactionIsolation(int level) throws java.sql.SQLException
	{
		c.setTransactionIsolation(level);
	}

	public int getTransactionIsolation() throws java.sql.SQLException
	{
		return c.getTransactionIsolation();
	}

	public void setTypeMap(java.util.Map map) throws SQLException
	{
		c.setTypeMap(map);
	}

	public synchronized java.util.Map getTypeMap() throws SQLException
	{
		return c.getTypeMap();
	}

	public java.sql.SQLWarning getWarnings() throws java.sql.SQLException
	{
		return c.getWarnings();
	}

	public void clearWarnings() throws java.sql.SQLException
	{
		c.clearWarnings();
	}

	public void commit() throws java.sql.SQLException
	{
		c.commit();
	}

	public StatementWrapper createStatement(int resultSetType,
		int resultSetConcurrency) throws SQLException
	{
		StatementWrapper sw = new StatementWrapper(this, c.createStatement(resultSetType,
			resultSetConcurrency));
		statements.add(sw);
		return sw;
	}

	public StatementWrapper createStatement() throws SQLException
	{
		StatementWrapper sw = new StatementWrapper(this, c.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
			java.sql.ResultSet.CONCUR_READ_ONLY));
		statements.add(sw);
		return sw;
	}

	public StatementWrapper createStatement(int resultSetType,
		int resultSetConcurrency, int resultSetHoldability)
		throws SQLException
	{
		StatementWrapper sw = new StatementWrapper(this, c.createStatement(resultSetType, resultSetConcurrency));
		statements.add(sw);
		return sw;
	}

	public String nativeSQL(String sql) throws java.sql.SQLException
	{
		return c.nativeSQL(sql);
	}

	public java.sql.CallableStatement prepareCall(String sql)
		throws java.sql.SQLException
	{
		return c.prepareCall(sql);
	}

	public java.sql.CallableStatement prepareCall(String sql,
		int resultSetType, int resultSetConcurrency) throws SQLException
	{
		return c.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	public java.sql.CallableStatement prepareCall(String sql,
		int resultSetType, int resultSetConcurrency, int resultSetHoldability)
		throws SQLException
	{
		return c.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	public PreparedStatementWrapper prepareStatement(String sql)
		throws java.sql.SQLException
	{
		PreparedStatementWrapper sw = new PreparedStatementWrapper(this, c.prepareStatement(sql));
		statements.add(sw);
		return sw;
	}

	public PreparedStatementWrapper prepareStatement(String sql,
		int resultSetType, int resultSetConcurrency) throws SQLException
	{
		PreparedStatementWrapper sw = new PreparedStatementWrapper(this, c.prepareStatement(sql, resultSetType, resultSetConcurrency));
		statements.add(sw);
		return sw;
	}

	public PreparedStatementWrapper prepareStatement(String sql,
		int resultSetType, int resultSetConcurrency, int resultSetHoldability)
		throws SQLException
	{
		PreparedStatementWrapper sw = new PreparedStatementWrapper(this, c.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
		statements.add(sw);
		return sw;
	}

	public PreparedStatementWrapper prepareStatement(String sql,
		int autoGenKeyIndex) throws SQLException
	{
		PreparedStatementWrapper sw = new PreparedStatementWrapper(this, c.prepareStatement(sql, autoGenKeyIndex));
		statements.add(sw);
		return sw;
	}

	public PreparedStatementWrapper prepareStatement(String sql,
		int[] autoGenKeyIndexes) throws SQLException
	{
		PreparedStatementWrapper sw = new PreparedStatementWrapper(this, c.prepareStatement(sql, autoGenKeyIndexes));
		statements.add(sw);
		return sw;
	}

	public PreparedStatementWrapper prepareStatement(String sql,
		String[] autoGenKeyColNames) throws SQLException
	{
		PreparedStatementWrapper sw = new PreparedStatementWrapper(this, c.prepareStatement(sql, autoGenKeyColNames));
		statements.add(sw);
		return sw;
	}

	public void releaseSavepoint(Savepoint arg0) throws SQLException
	{
		c.releaseSavepoint(arg0);
	}

	public void rollback() throws java.sql.SQLException
	{
		c.rollback();
	}

	public void rollback(Savepoint arg0) throws SQLException
	{
		c.rollback(arg0);
	}

	public Blob createBlob() throws SQLException
	{
		return c.createBlob();
	}

	public NClob createNClob() throws SQLException
	{
		return c.createNClob();
	}

	public SQLXML createSQLXML() throws SQLException
	{
		return c.createSQLXML();
	}

	public boolean isValid(int timeout) throws SQLException
	{
		return c.isValid(timeout);
	}

	public void setClientInfo(String name, String value)
		throws SQLClientInfoException
	{
		c.setClientInfo(name, value);
	}

	public void setClientInfo(Properties properties)
		throws SQLClientInfoException
	{
		c.setClientInfo(properties);
	}

	public String getClientInfo(String name)
		throws SQLException
	{
		return c.getClientInfo(name);
	}

	public Properties getClientInfo()
		throws SQLException
	{
		return c.getClientInfo();
	}

	public Array createArrayOf(String typeName, Object[] elements) throws
		SQLException
	{
		return c.createArrayOf(typeName, elements);
	}

	public Struct createStruct(String typeName, Object[] attributes)
		throws SQLException
	{
		return c.createStruct(typeName, attributes);
	}

	public <T> T unwrap(java.lang.Class<T> iface) throws java.sql.SQLException
	{
		return c.unwrap(iface);
	}

	public boolean isWrapperFor(java.lang.Class<?> iface) throws java.sql.SQLException
	{
		return c.isWrapperFor(iface);
	}

	public Clob createClob() throws SQLException
	{
		return c.createClob();
	}

	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException
	{
		c.setNetworkTimeout(executor, milliseconds);
	}

	public int getNetworkTimeout() throws SQLException
	{
		return c.getNetworkTimeout();
	}

	public void abort(Executor executor) throws SQLException
	{
		c.abort(executor);
	}

	public void setSchema(String schema) throws SQLException
	{
		c.setSchema(schema);
	}

	public String getSchema() throws SQLException
	{
		return c.getSchema();
	}
}

