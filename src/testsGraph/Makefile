# modify the paths appropriately
JDKPATH = /usr/lib/jvm/java-7-openjdk-amd64
LIBPATH = .:..
CLASSPATH = $(LIBPATH)
BINPATH = $(JDKPATH)/bin
JAVAC = $(JDKPATH)/bin/javac -classpath $(CLASSPATH)
JAVA  = $(JDKPATH)/bin/java  -classpath $(CLASSPATH)


nodeinsert:BatchNodeInsert.java
	$(JAVAC) BatchNodeInsert.java

edgeinsert: BatchEdgeInsert.java
	$(JAVAC) BatchEdgeInsert.java

nodedelete:BatchNodeDelete.java
	$(JAVAC) BatchNodeDelete.java

edgedelete: BatchEdgeDelete.java
	$(JAVAC) BatchEdgeDelete.java

# test heapfile 

nodequery:nodequery.java
	$(JAVAC) nodequery.java

nodequery2:nodequery2.java
	$(JAVAC) nodequery2.java

edgequery: edgequery.java
	$(JAVAC) edgequery.java

graphquery: graphQuery.java
	$(JAVAC) graphQuery.java
