package name.ltp.jdbcwrapper;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Calendar;

public class PreparedStatementWrapper extends StatementWrapper implements PreparedStatement
{
	public int seti = 1;

	protected PreparedStatementWrapper(ConnectionWrapper c, PreparedStatement s)
	{
		super(c, s);
	}

	@Override public
	ResultSetWrapper executeQuery() throws SQLException
	{
		ResultSetWrapper rsw = new ResultSetWrapper(this, ((PreparedStatement)s).executeQuery());
		resultsets.add(rsw);
		return rsw;
	}

	@Override public
	int executeUpdate() throws SQLException
	{
		return ((PreparedStatement)s).executeUpdate();
	}

	@Override public
	void setNull(int parameterIndex, int sqlType) throws SQLException
	{
		((PreparedStatement)s).setNull(parameterIndex, sqlType);
	}

	public
	void setNull(int sqlType) throws SQLException
	{
		setNull(seti++, sqlType);
	}

	@Override public
	void setBoolean(int parameterIndex, boolean x) throws SQLException
	{
		((PreparedStatement)s).setBoolean(parameterIndex, x);
	}

	public
	void set(boolean x) throws SQLException
	{
		setBoolean(seti++, x);
	}

	@Override public
	void setByte(int parameterIndex, byte x) throws SQLException
	{
		((PreparedStatement)s).setByte(parameterIndex, x);
	}

	public
	void set(byte x) throws SQLException
	{
		setByte(seti++, x);
	}

	@Override public
	void setShort(int parameterIndex, short x) throws SQLException
	{
		((PreparedStatement)s).setShort(parameterIndex, x);
	}

	public
	void set(short x) throws SQLException
	{
		setShort(seti++, x);
	}

	@Override public
	void setInt(int parameterIndex, int x) throws SQLException
	{
		((PreparedStatement)s).setInt(parameterIndex, x);
	}

	public
	void set(int x) throws SQLException
	{
		setInt(seti++, x);
	}

	@Override public
	void setLong(int parameterIndex, long x) throws SQLException
	{
		((PreparedStatement)s).setLong(parameterIndex, x);
	}

	public
	void set(long x) throws SQLException
	{
		setLong(seti++, x);
	}

	@Override public
	void setFloat(int parameterIndex, float x) throws SQLException
	{
		((PreparedStatement)s).setFloat(parameterIndex, x);
	}

	public
	void set(float x) throws SQLException
	{
		setFloat(seti++, x);
	}

	@Override public
	void setDouble(int parameterIndex, double x) throws SQLException
	{
		((PreparedStatement)s).setDouble(parameterIndex, x);
	}

	public
	void set(double x) throws SQLException
	{
		setDouble(seti++, x);
	}

	@Override public
	void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException
	{
		((PreparedStatement)s).setBigDecimal(parameterIndex, x);
	}

	public
	void set(BigDecimal x) throws SQLException
	{
		setBigDecimal(seti++, x);
	}

	@Override public
	void setString(int parameterIndex, String x) throws SQLException
	{
		((PreparedStatement)s).setString(parameterIndex, x);
	}

	public
	void set(String x) throws SQLException
	{
		setString(seti++, x);
	}

	@Override public
	void setBytes(int parameterIndex, byte x[]) throws SQLException
	{
		((PreparedStatement)s).setBytes(parameterIndex, x);
	}

	public
	void set(byte x[]) throws SQLException
	{
		setBytes(seti++, x);
	}

	@Override public
	void setDate(int parameterIndex, java.sql.Date x)
		throws SQLException
	{
		((PreparedStatement)s).setDate(parameterIndex, x);
	}

	public
	void set(java.sql.Date x)
		throws SQLException
	{
		setDate(seti++, x);
	}

	@Override public
	void setTime(int parameterIndex, java.sql.Time x)
		throws SQLException
	{
		((PreparedStatement)s).setTime(parameterIndex, x);
	}

	public
	void set(java.sql.Time x)
		throws SQLException
	{
		setTime(seti++, x);
	}

	@Override public
	void setTimestamp(int parameterIndex, java.sql.Timestamp x)
		throws SQLException
	{
		((PreparedStatement)s).setTimestamp(parameterIndex, x);
	}

	public
	void set(java.sql.Timestamp x)
		throws SQLException
	{
		setTimestamp(seti++, x);
	}

	public
	void setCurTime()
		throws SQLException
	{
		setTimestamp(seti++, new Timestamp(System.currentTimeMillis()));
	}

	public
	void setTime(long ms)
		throws SQLException
	{
		setTimestamp(seti++, new Timestamp(ms));
	}

	@Override public
	void setAsciiStream(int parameterIndex, java.io.InputStream x, int length)
		throws SQLException
	{
		((PreparedStatement)s).setAsciiStream(parameterIndex, x, length);
	}

	public
	void setAsciiStream(java.io.InputStream x, int length)
		throws SQLException
	{
		setAsciiStream(seti++, x, length);
	}

	@Override public
	void setUnicodeStream(int parameterIndex, java.io.InputStream x,
		int length) throws SQLException
	{
		((PreparedStatement)s).setUnicodeStream(parameterIndex, x, length);
	}

	public
	void setUnicodeStream(java.io.InputStream x,
		int length) throws SQLException
	{
		setUnicodeStream(seti++, x, length);
	}

	@Override public
	void setBinaryStream(int parameterIndex, java.io.InputStream x,
		int length) throws SQLException
	{
		((PreparedStatement)s).setBinaryStream(parameterIndex, x, length);
	}

	public
	void setBinaryStream(java.io.InputStream x,
		int length) throws SQLException
	{
		setBinaryStream(seti++, x, length);
	}

	@Override public
	void clearParameters() throws SQLException
	{
		((PreparedStatement)s).clearParameters();
	}

	@Override public
	void setObject(int parameterIndex, Object x, int targetSqlType)
		throws SQLException
	{
		((PreparedStatement)s).setObject(parameterIndex, x, targetSqlType);
	}

	public
	void setObject(Object x, int targetSqlType)
		throws SQLException
	{
		setObject(seti++, x, targetSqlType);
	}

	@Override public
	void setObject(int parameterIndex, Object x) throws SQLException
	{
		((PreparedStatement)s).setObject(parameterIndex, x);
	}

	public
	void setObject(Object x) throws SQLException
	{
		setObject(seti++, x);
	}

	@Override public
	boolean execute() throws SQLException
	{
		return ((PreparedStatement)s).execute();
	}

	@Override public
	void addBatch() throws SQLException
	{
		((PreparedStatement)s).addBatch();
	}

	@Override public
	void setCharacterStream(int parameterIndex,
		java.io.Reader reader,
		int length) throws SQLException
	{
		((PreparedStatement)s).setCharacterStream(parameterIndex, reader, length);
	}

	@Override public
	void setRef (int parameterIndex, Ref x) throws SQLException
	{
		((PreparedStatement)s).setRef(parameterIndex, x);
	}

	@Override public
	void setBlob (int parameterIndex, Blob x) throws SQLException
	{
		((PreparedStatement)s).setBlob(parameterIndex, x);
	}

	@Override public
	void setClob (int parameterIndex, Clob x) throws SQLException
	{
		((PreparedStatement)s).setClob(parameterIndex, x);
	}

	@Override public
	void setArray (int parameterIndex, Array x) throws SQLException
	{
		((PreparedStatement)s).setArray(parameterIndex, x);
	}

	@Override public
	ResultSetMetaData getMetaData() throws SQLException
	{
		return ((PreparedStatement)s).getMetaData();
	}

	@Override public
	void setDate(int parameterIndex, java.sql.Date x, Calendar cal)
		throws SQLException
	{
		((PreparedStatement)s).setDate(parameterIndex, x, cal);
	}

	@Override public
	void setTime(int parameterIndex, java.sql.Time x, Calendar cal)
		throws SQLException
	{
		((PreparedStatement)s).setTime(parameterIndex, x, cal);
	}

	@Override public
	void setTimestamp(int parameterIndex, java.sql.Timestamp x, Calendar cal)
		throws SQLException
	{
		((PreparedStatement)s).setTimestamp(parameterIndex, x, cal);
	}

	@Override public
	void setNull (int parameterIndex, int sqlType, String typeName)
		throws SQLException
	{
		((PreparedStatement)s).setNull(parameterIndex, sqlType, typeName);
	}

	@Override public
	void setURL(int parameterIndex, java.net.URL x) throws SQLException
	{
		((PreparedStatement)s).setURL(parameterIndex, x);
	}

	@Override public
	ParameterMetaData getParameterMetaData() throws SQLException
	{
		return ((PreparedStatement)s).getParameterMetaData();
	}

	@Override public
	void setRowId(int parameterIndex, RowId x) throws SQLException
	{
		((PreparedStatement)s).setRowId(parameterIndex, x);
	}

	@Override public
	void setNString(int parameterIndex, String value) throws SQLException
	{
		((PreparedStatement)s).setNString(parameterIndex, value);
	}

	@Override public
	void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException
	{
		((PreparedStatement)s).setNCharacterStream(parameterIndex, value, length);
	}

	@Override public
	void setNClob(int parameterIndex, NClob value) throws SQLException
	{
		((PreparedStatement)s).setNClob(parameterIndex, value);
	}

	@Override public
	void setClob(int parameterIndex, Reader reader, long length)
		throws SQLException
	{
		((PreparedStatement)s).setClob(parameterIndex, reader, length);
	}

	@Override public
	void setBlob(int parameterIndex, InputStream inputStream, long length)
		throws SQLException
	{
		((PreparedStatement)s).setBlob(parameterIndex, inputStream, length);
	}

	@Override public
	void setNClob(int parameterIndex, Reader reader, long length)
		throws SQLException
	{
		((PreparedStatement)s).setNClob(parameterIndex, reader, length);
	}

	@Override public
	void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException
	{
		((PreparedStatement)s).setSQLXML(parameterIndex, xmlObject);
	}

	@Override public
	void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength)
		throws SQLException
	{
		((PreparedStatement)s).setObject(parameterIndex, x, targetSqlType, scaleOrLength);
	}

	@Override public
	void setAsciiStream(int parameterIndex, java.io.InputStream x, long length)
		throws SQLException
	{
		((PreparedStatement)s).setAsciiStream(parameterIndex, x, length);
	}

	@Override public
	void setBinaryStream(int parameterIndex, java.io.InputStream x,
		long length) throws SQLException
	{
		((PreparedStatement)s).setBinaryStream(parameterIndex, x, length);
	}

	@Override public
	void setCharacterStream(int parameterIndex,
		java.io.Reader reader,
		long length) throws SQLException
	{
		((PreparedStatement)s).setCharacterStream(parameterIndex, reader, length);
	}

	@Override public
	void setAsciiStream(int parameterIndex, java.io.InputStream x)
		throws SQLException
	{
		((PreparedStatement)s).setAsciiStream(parameterIndex, x);
	}

	@Override public
	void setBinaryStream(int parameterIndex, java.io.InputStream x)
		throws SQLException
	{
		((PreparedStatement)s).setBinaryStream(parameterIndex, x);
	}

	@Override public
	void setCharacterStream(int parameterIndex,
		java.io.Reader reader) throws SQLException
	{
		((PreparedStatement)s).setCharacterStream(parameterIndex, reader);
	}

	@Override public
	void setNCharacterStream(int parameterIndex, Reader value) throws SQLException
	{
		((PreparedStatement)s).setNCharacterStream(parameterIndex, value);
	}

	@Override public
	void setClob(int parameterIndex, Reader reader)
		throws SQLException
	{
		((PreparedStatement)s).setClob(parameterIndex, reader);
	}

	@Override public
	void setBlob(int parameterIndex, InputStream inputStream)
		throws SQLException
	{
		((PreparedStatement)s).setBlob(parameterIndex, inputStream);
	}

	@Override public
	void setNClob(int parameterIndex, Reader reader)
		throws SQLException
	{
		((PreparedStatement)s).setNClob(parameterIndex, reader);
	}
}

