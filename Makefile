all:
	javac *.java piece/*.java jogador/*.java model/*.java gui/*.java MVC/*.java
 
clean:
	rm -rf *.class
	rm -rf piece/*.class
	rm -rf model/*.class
	rm -rf jogador/*.class
	rm -rf gui/*.class

run:
	java Teste
