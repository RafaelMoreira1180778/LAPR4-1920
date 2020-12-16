main: main.o
	gcc -Wall -g main.o -o main -lrt -lpthread -lssl -lcrypto

main.o: main.c 
	gcc -Wall -g -c main.c

run: main
	@echo ./main $$ARGS

certs:
	./certificates
	
gdb: main
	gdb -tui main
	
vg: main
	valgrind ./main

clean:
	rm *.o main
