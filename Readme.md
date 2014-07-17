## Features
* Resource auto-closing
* PreparedStatement argument setters with auto-indexing
* Unclosed resources watcher

### Resource auto-closing
Replace your Connection instances with ConnectionWrapper and place it in try():
```java
try(Connection con = DbPool.con())
{
	// Create (Prepared)Statements and ResultSets here.

	// Do some work.
	
	// They will be closed automatically in the right order,
	// upon leaving this block.
}
```

### PreparedStatement argument setters with auto-indexing
```java
PreparedStatementWrapper st = con.prepareStatement("SELECT * FROM something WHERE something=? AND whatever LIKE ?");

st.set(1337); // No index argument needed here.

st.set("%HAX%"); // And here.
```

### Unclosed resources watcher
And just in case, there's a resource watcher, checking for unclosed Connections, (Prepared)Statements and ResultSets.
```java
name.ltp.jdbcwrapper.JdbcWatcher.i(
	new name.ltp.jdbcwrapper.JdbcWatcher.Options()
		.schedule(10000) // Run a task to check for unclosed resources every 10 seconds.

		.watchConnections(30000) // If connection is opened for more than
		                         // 30 seconds, you will be notified.
		                         // See name.ltp.jdbcwrapper.Err class.
		.watchStatements(15000)
		.watchResultSets(null)   // Don't check for unclosed ResultSets.
	);
```

## Usage
Just make this repo as a submodule in your project and start using ConnectionWrapper.

Also you can override `name.ltp.jdbcwrapper.JdbcWatcher` and `name.ltp.jdbcwrapper.Err` class methods, by subclassing them and passing an instance of your class to static i() methods in these classes:
```java
protected static JdbcWatcher i(JdbcWatcher i) {...}

protected static Err i(Err i) {...}
```

## License
Licensed under the CC0 1.0: http://creativecommons.org/publicdomain/zero/1.0/

