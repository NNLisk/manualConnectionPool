# About # 
This is a personal learning project. I make a manual Connection
pool class that handles an int poolSize amount of concurrent
premade connections to database. This is a learning project so some
of it is inspired by various stackoverflow pages and sites like
factorizing-guru for the singleton pool feature.

ChatGPT has provided ideas for some good-to-have functionality
for a connection pool.

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

