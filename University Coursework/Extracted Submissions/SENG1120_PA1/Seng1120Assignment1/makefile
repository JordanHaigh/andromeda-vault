CC=g++
CFLAGS=-c -Wall -std=c++11
LDFLAGS=
SOURCES=Node.cpp LinkedList.cpp DeckOfCards.cpp DeckOfCardsDemo.cpp
OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=Demo

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

%.o : %.cpp
	$(CC) $(CFLAGS) -c $<

clean:
	rm -rf *.o core
