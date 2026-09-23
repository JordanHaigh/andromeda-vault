#include "DeckOfCards.h"
#include "HandOfCards.h"
#include "Card.h"
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

DeckOfCards* DCards;
HandOfCards* player1;
HandOfCards* dealer;


void setRandSeed(int numberOfArguments, char* argumentList[])
{
	//Code acquired from Trent Houliston
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
		time_t seed = time(NULL);

		// Use that number to seed the random number generator
		srand((int)seed);
	}
	//Acquired Code ends here
}

void dealFirstHand()
{
	//Give one card to the player facing up
	player1->add(DCards->pop(), true);
	dealer->add(DCards->pop(), true);
	player1->add(DCards->pop(), true);
	dealer->add(DCards->pop(), false);

	//Display hands content
	cout << "Player: " << player1->value() << "(" << player1->count() << " points)" << endl;
	cout << "Dealer: " << dealer->value() << "(" << dealer->count() << " points)" << endl;
}

bool gameIsOver(bool forceEndGame)
{
	//Check if player or dealer have a count of 21
	//If both have a count of 21
	if (player1->count() == 21 && dealer->countAll() == 21)
	{
		cout << "Tie!" << endl;
		return true;
	}
	//If the player has a count of 21
	else if (player1->count() == 21)
	{
		cout << "The player is the winner!" << endl;
		return true;
	}
	//If the dealer has a count of 21
	else if (dealer->countAll() == 21)
	{
		cout << "The Dealer is the winner!" << endl;
		return true;
	}
	//If not, continue with the game
	else if (player1->count() > 21)
	{
		//Player has busted. Game ends
		cout << "Player has busted! Game Over!" << endl;
		return true;
	}
	else if (dealer->countAll() > 21)
	{
		//Dealer has busted
		cout << "Dealer has busted! Player is the winner!" << endl;
		return true;
	}
	else if (forceEndGame == true)
	{
		if (player1->count() > dealer->count())
			cout << "The Player is the winner!" << endl;
		else if (player1->count() < dealer->count())
			cout << "The Dealer is the winner!" << endl;
		else
			cout << "Tie! There are no winners!" << endl;
		return true;
	}
	else
	{
		return false;
	}
}

int getPlayerHitStand()
{
	bool invalidInput = true;
	int playerInput;

	//Checking for invalid inputs from the user
	while (invalidInput)
	{
		cout << "Player, do you want to Hit (1) or Stand (2)?" << endl;
		cin >> playerInput;
		if (cin.fail())
		{
			cout << "ERROR. Integer not entered." << endl;
			return -1; // TODO: ASK LAUREN WHAT TO DO HERE
		}
		else if (playerInput == 1 || playerInput == 2)
			invalidInput = false;
		else
			cout << "ERROR. Enter an integer (1 or 2)" << endl;
	}

	return playerInput;
}

int main(int numberOfArguments, char* argumentList[]) 
{
	setRandSeed(numberOfArguments, argumentList);
	

	//Creating new Deck of Cards
	DCards = new DeckOfCards();

	//Shuffling the deck of cards
	DCards->shuffle();

	//Creating two instances of HandOfCards
	player1 = new HandOfCards();
	dealer = new HandOfCards();
	
	dealFirstHand();

	//Looping through player and dealer
	bool gameEnd = false;
	int playerInput;

	while (!gameEnd)
	{
		if (gameIsOver(false))
		{
			gameEnd = true;
		}
		else
		{
			
			playerInput = getPlayerHitStand();
			//If player entered an invalid value (Non integer)
			if (playerInput == -1) 
				return -1;

			//If input has been entered correctly
			//If player chose to Hit (1)
			if (playerInput == 1)
			{
				//Give player another card from the deck, face up.
				player1->add(DCards->pop(), true);
				cout << "Player: " << player1->value() << "(" << player1->count() << " points)" << endl;
				cout << "Dealer: " << dealer->value() << "(" << dealer->count() << " points)" << endl;

			}
			else
			{
				//Player has stood in the last loop
				//Dealers play
				//If dealer has a count less than or equal to 17, keep giving cards
				while(dealer->countAll() <=16)
				{
					//Give dealer new card, facing up
					dealer->add(DCards->pop(), true);
				}

				//Make all cards in dealers hand face up
				dealer->faceUp();
				//Display content of all hands
				cout << "Player: " << player1->value() << "(" << player1->count() << " points)" << endl;
				cout << "Dealer: " << dealer->value() << "(" << dealer->count() << " points)" << endl;

				//Count contents of each hand
				//int playerCount = player1->count();
				//int dealerCount = dealer->count();

				//Display contents of hands in format (P=X) (D=X)
				//cout << "(P=" << playerCount << ")" << "(D=" << dealerCount << ")" << endl;
				gameEnd = gameIsOver(true);
			}
		}
	}

	//Delete Deck of Cards
	delete DCards;
	//Delete Player1
	delete player1;
	//Delete Dealer
	delete dealer;
    return 0;
}
