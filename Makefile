# Exemplu de Makefile pentru soluții scrise în Java.

.PHONY: build clean

build: Nostory.class Feribot.class Semnale.class Sushi.class

run-p1:
	java Feribot

run-p2:
	java Nostory
run-p3:
	java Sushi
run-p4:
	java Semnale

# Nu uitați să modificați numele surselor.
Feribot.class: Feribot.java
	javac $^

Nostory.class: Nostory.java
	javac $^

Sushi.class: Sushi.java
	javac $^

Semnale.class: Semnale.java
	javac $^

# Vom șterge fișierele bytecode compilate.
clean:
	rm -f *.class