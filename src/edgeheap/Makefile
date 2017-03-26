JDKPATH = /usr/lib/jvm/java-7-openjdk-amd64
LIBPATH = /p/course/cs784-raghu/minibase_share/javaminibase/construction
CLASSPATH = $(JDKPATH)/lib/classes.zip:.:..:$(LIBPATH)
BINPATH = $(JDKPATH)/bin
JAVAC = $(JDKPATH)/bin/javac -classpath $(CLASSPATH)
JAVA  = $(JDKPATH)/bin/java  -classpath $(CLASSPATH)

PROGS = together

all: $(PROGS)

hf:EdgeHeapFile.java
	$(JAVAC) EdgeHeapFile.java

hp:EHFPage.java
	$(JAVAC) EHFPage.java

tuple:Edge.java
	$(JAVAC) Edge.java

scan:EScan.java
	$(JAVAC) EScan.java

together:*.java
	$(JAVAC) *.java

clean:
	\rm -f *.class *~ \#* core 
