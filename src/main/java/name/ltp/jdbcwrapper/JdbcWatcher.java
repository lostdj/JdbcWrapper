package name.ltp.jdbcwrapper;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class JdbcWatcher
{
	//
	public static JdbcWatcher i() {return i(new Options());}
	public static JdbcWatcher i(Options o) {if(instance == null) {instance = new JdbcWatcher(o);} return instance;}

	protected static JdbcWatcher i(JdbcWatcher i) {return instance == null ? instance = i : instance;}

	protected static JdbcWatcher instance;

	//
	public final Long schedule;

	public final Long watchConnections;
	public final Long watchStatements;
	public final Long watchResultSets;

	public static class Options
	{
		// Nullify to disable.

		//
		public Long schedule         = TimeUnit.MINUTES.toMillis(3);

		public Long watchConnections = TimeUnit.MINUTES.toMillis(2);
		public Long watchStatements  = TimeUnit.MINUTES.toMillis(1);
		public Long watchResultSets  = TimeUnit.SECONDS.toMillis(30);

		//
		public Options schedule(Long ms) {schedule = ms; return this;}

		public Options watchConnections(Long ms) {watchConnections = ms; return this;}
		public Options watchStatements(Long ms)  {watchStatements = ms; return this;}
		public Options watchResultSets(Long ms)  {watchResultSets = ms; return this;}

		//
		public Options() {}
	}

	//
	protected JdbcWatcher(Options o)
	{
		schedule = o.schedule;

		watchConnections = o.watchConnections;
		watchStatements  = o.watchStatements;
		watchResultSets  = o.watchResultSets;

		if(schedule != null)
			schedule(new ScheduleCheckTask(), schedule, schedule);
	}

	//
	final List<ConnectionWrapper> cons = Collections.synchronizedList(new LinkedList<ConnectionWrapper>());
	final List<StatementWrapper> sts = Collections.synchronizedList(new LinkedList<StatementWrapper>());
	final List<ResultSetWrapper> rss = Collections.synchronizedList(new LinkedList<ResultSetWrapper>());

	//
	private class ScheduleCheckTask implements Runnable
	{
		@Override
		public void run()
		{
			if(watchConnections != null)
				synchronized(cons)
				{
					Iterator<ConnectionWrapper> i = cons.iterator();
					while(i.hasNext())
					{
						ConnectionWrapper dci = i.next();
						if(dci.c != null)
							if(System.currentTimeMillis() - dci.time > watchConnections)
							{
								StringBuilder stack = new StringBuilder();
								for(StackTraceElement ste : dci.stack)
									if(ste != null)
									{
										stack.append(ste.toString());
										stack.append("\r\n  ");
									}

								Err.i().err("JdbcWatcher: Unclosed Connection \r\n  " + stack.toString());
							}
							else
								continue;

						i.remove();
					}
				}

			if(watchStatements != null)
				synchronized(sts)
				{
					Iterator<StatementWrapper> i = sts.iterator();
					while(i.hasNext())
					{
						StatementWrapper dci = i.next();
						if(dci.s != null)
							if(System.currentTimeMillis() - dci.time > watchStatements)
							{
								StringBuilder stack = new StringBuilder();
								for(StackTraceElement ste : dci.stack)
									if(ste != null)
									{
										stack.append(ste.toString());
										stack.append("\r\n  ");
									}

								Err.i().err("JdbcWatcher: Unclosed Statement at \r\n  " + stack.toString());
							}
							else
								continue;

						i.remove();
					}
				}

			if(watchResultSets != null)
				synchronized(rss)
				{
					Iterator<ResultSetWrapper> i = rss.iterator();
					while (i.hasNext())
					{
						ResultSetWrapper dci = i.next();
						if(dci.r != null)
							if(System.currentTimeMillis() - dci.time > watchResultSets)
							{
								StringBuilder stack = new StringBuilder();
								for(StackTraceElement ste : dci.stack)
									if(ste != null)
									{
										stack.append(ste.toString());
										stack.append("\r\n  ");
									}

								Err.i().err("JdbcWatcher: Unclosed ResultSet at \r\n  " + stack.toString());
							}
							else
								continue;

						i.remove();
					}
				}
		}
	}

	//
	protected ScheduledFuture schedule(Runnable r, long initialDelay, long period)
	{
		try
		{
			if(initialDelay < 0)
				initialDelay = 0;

			if(period < 0)
				period = 0;

			return threadPool().scheduleAtFixedRate(r, initialDelay, period, TimeUnit.MILLISECONDS);
		}
		catch(RejectedExecutionException e)
		{
			Err.i().err(e.toString());

			return null;
		}
	}

	protected ScheduledThreadPoolExecutor threadPool()
	{
		return new
			ScheduledThreadPoolExecutor(
				1,
				new JdbcWatcherThreadFactory("JdbcWatcherScheduledPool"));
	}

	protected static class JdbcWatcherThreadFactory implements ThreadFactory
	{
		public final String name;
		public final ThreadGroup group;

		public JdbcWatcherThreadFactory(String name)
		{
			this.name = name;
			group = new ThreadGroup(name);
		}

		@Override
		public Thread newThread(Runnable r)
		{
			Thread t = new Thread(group, r);
			t.setName(name);
			t.setPriority(Thread.NORM_PRIORITY);

			return t;
		}
	}
}

