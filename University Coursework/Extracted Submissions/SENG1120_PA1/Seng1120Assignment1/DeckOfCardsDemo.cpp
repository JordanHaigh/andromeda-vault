#include "DeckOfCards.h"
#include <iostream>
#include <string>
#include <ctime>
#include <cstdlib>

using namespace std;
using namespace SENG1120ASSIGN;
/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * DeckOfCardsDemo.cpp
 */


//Code acquired from Trent Houliston
int main(int numberOfArguments, char* argumentList[]) 
{

	// Make sure we have at least two arguments
	// The first argument is Program Name e.g. myCode.exe
	// The second argument will be the seed for the random number generator
	if (numberOfArguments >= 2) 
	{
		// Use atoi (ascii to integer) to convert our argument to a number
		// Note that if the user enters an invalid number this will be 0
		int seed = atoi(argumentList[1]);

		// Use that number to seed the random number generator
		srand(seed);
	}
	// If we don't have at least two arguments seed the random number generator using the current time
	else 
	{
		// Get the current time as an integer
		int seed = time(NULL);

		// Use that number to seed the random number generator
		srand(seed);
	}
	//Acquired Code ends here

	
	//Creating new Deck of Cards
	DeckOfCards* DCards = new DeckOfCards();

	//Printing the deck of cards
	cout << DCards->value() << endl;


	//Shuffling the deck of cards
	DCards->shuffle();
	//cout << DCards->value() << endl;
	cout << *DCards;


	//Print position of specific cards
	cout << DCards->position("4-H") << " " << DCards->position("10-S") << " " << DCards->position("Q-C") << " " << DCards->position("A-D") << endl;

	//Print total number of cards in Deck of Cards
	cout << DCards->length() << endl;

	//Remove specific cards
	DCards->remove("4-H");
	DCards->remove("10-S");

	//Print position of specific cards again
	cout << DCards->position("4-H") << " " << DCards->position("10-S") << " " << DCards->position("Q-C") << " " << DCards->position("A-D") << endl;

	//Print total number of cards in Deck of Cards again
	cout << DCards->length() << endl;

	////6120 Work - Reverse list
	//cout << "**************************************************" << endl;
	//cout << "Reverse the shuffled list" << endl;
	//cout << "************************ **************************" << endl;
	//cout << DCards->reverse() << endl;
	//cout << endl;
	
	//Delete Deck of Cards
	delete DCards;

    return 0;
}
