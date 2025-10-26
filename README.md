# About # 
Singleton connection pool manager for JDCB and PostgreSQL database. Improves efficiency of database operations by reusing established connections in an array.

# How it Works #
+ Manages an Arraylist of predefined connections to a database
+ functions to lend out and return functions to the list
+ Tracks the pool state between (INITIALIZING, RUNNING, SHUTTING_DONW,
  SHUTDOWN)
+ Shutdown method waits untill all connections are returned i.e.
  database processes have finished

# Usage # 
+ private constructor, Connection pool initialization happens through
  the class, by calling ConnectionPool.makeConnPool() with following
  parameters
  + Database URL : String
  + Database User : String
  + User password : String
  + poolSize : int
+ poolSize defines how many simultaneous connections are allowed

