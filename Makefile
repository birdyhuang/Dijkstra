#Parameters

SOURCE = Dijkstra.java

TARGET = $(SOURCE:%.java=%.class)

#Compiler
JCC += javac

#Target
$(TARGET) : $(SOURCE)
	$(JCC) $(SOURCE)

clean:
	$(RM) *.class \
		lpath.txt \
		fpath.txt \
		leftOutput.txt \
		fiboOutput.txt
