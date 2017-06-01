all:
	javac *.java piece/*.java

clean:
	rm -rf *.class
	rm -rf piece/*.class

run:
	java Teste
