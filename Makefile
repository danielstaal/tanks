CLASSPATH = acm.jar
CFLAGS = -cp $(CLASSPATH)

JAVA_FILES = Tanks.java
CLASS_FILES = $(JAVA_FILES:.java=.class)


all: breakout

breakout: Tanks.class

%.class: %.java
	javac $(CFLAGS) $<


submit: pset3.zip

pset3.zip: $(JAVA_FILES)
	zip $@ $^


clean:
	rm -f *.class
