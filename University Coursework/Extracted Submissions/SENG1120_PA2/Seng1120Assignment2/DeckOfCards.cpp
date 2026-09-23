#include "DeckOfCards.h"

/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * DeckOfCards.cpp
 */

namespace SENG1120ASSIGN
{
    DeckOfCards::DeckOfCards()
    {
		//Initialise Linked List (LList)
		LList = new LinkedList<Card>();
		//For loop will print values for Hearts, Diamonds, Clubs and Spaces.
		for (int card = S; card <= D; card++)
		{
			//Numbers 2-10
			for (int number = 2; number <= 10; number++)
			{
				//Requires to use C++11 standard for to_string method
				//The included makefile in this assignment submission has been altered to include the C++ 11 Standard
				string cardNumber = std::to_string(number);
				//ostringstream convert;
				//convert << number;
				//string cardNumber = convert.str();

				string cardValue = cardNumber + "-" + suitToString(card);
				Card* newCard = new Card(cardValue, number, false);
				//Add the new card to the tail of the linked list
				this->push(newCard);
			}
			//Royal cards J,Q,K,A
			for (int royal = J; royal < A; royal++)
			{
				string cardValue = royalToString(royal) + "-" + suitToString(card);
				Card* newCard = new Card(cardValue, 10, false);
				//Add the new card to the tail of the linked list
				this->push(newCard);
			}
			//Create Ace cards separately due to the point count
			string cardValue = "A-" + suitToString(card);
			Card* newCard = new Card(cardValue, 11, false);
			//Add the new card to the tail of the linked list
			this->push(newCard);
		}
    }

    DeckOfCards::~DeckOfCards()
    {
		//Remove the linked list
		delete LList;
    }

	Card* DeckOfCards::pop()
	{
		//Remove the first card from the head of the linked list
		return LList->removeFromHead();
	}

	void DeckOfCards::push(Card* inputCard)
	{
		//Add the parameter card to the head
		LList->addToHead(inputCard);
	}

    void DeckOfCards::shuffle()
    {
		int index;
		//String variable used to store the data of the node to help with the swapping section
		Card* temp1;
		Card* temp2;
		for (size_t i = LList->size()-1; i > 0; i--)
		{
			//index = randomNumberGenerator(0, i+1);

			index = rand() % (i+1);
			//Traverses from head up to i and stores data
			temp1 = LList->get(i);
			//Traverses again from head up to index and stores data
			temp2 = LList->get(index);
			//Traverse from head and set index with temp1 data
			LList->set(index, temp1);
			//Traverse from head again and set i with temp2 data
			LList->set(i, temp2);
			//Process will swap data effectively, though will be slow
		}
    }

    size_t DeckOfCards::length()
    {
		//Return the size of the linked list (Number of nodes between the head and tail sentinels)
		return LList->size();
    }

    bool DeckOfCards::empty()
    {
		//Check whether the Linked List is empty or not
		if (LList->isEmpty())
			return true;
		else
			return false;
    }

    int DeckOfCards::position(string input)
    {
		//Create dummy card to check if the face is equal
		Card* newCard = new Card(input,0,false);
		int i = positionCard(newCard);
		delete newCard;
		return i;
	}

	int DeckOfCards::positionCard(Card* input)
	{
		//Create integer variable to find the specific index of the input argument
		int position = LList->indexOf(input);
		//Return the position (-1 if not in the Linked list)
		return position;
	}


    std::string DeckOfCards::value()
    {
		//Return the current node as a stirng
		return LList->toString();
    }

	std::string DeckOfCards::value() const
	{
		//Return the current node as a stirng
		return LList->toString();
	}


    bool DeckOfCards::remove(string input)
    {
		//Create dummy card to check against the string input
		Card* newCard = new Card(input, 0, false);
		bool b = LList->removeFirst(newCard);
		delete newCard;
		//Return a boolean value whether the card was removed or not
		return b;
    }

    std::string DeckOfCards::suitToString(int suitInt)
    {
        //Since enums work with integers, by using the static cast, we can
        //input the actual suit of the card. This works the same for royal
        CARDSUIT suit = static_cast<CARDSUIT>(suitInt);

		//Use a switch case to return the specific suit
        switch(suit)
        {
            case CARDSUIT::S: return "S";
            case CARDSUIT::H: return "H";
            case CARDSUIT::C: return "C";
            case CARDSUIT::D: return "D";
            default: throw "ERROR. Invalid Argument";
        }
    }

    std::string DeckOfCards::royalToString(int royalInt)
    {
        CARDROYAL royal = static_cast<CARDROYAL>(royalInt);

		//Use a switch case to return the specific royal type
        switch(royal)
        {
            case CARDROYAL::J: return "J";
            case CARDROYAL::Q: return "Q";
            case CARDROYAL::K: return "K";
            case CARDROYAL::A: return "A";
            default: throw "ERROR. Invalid Argument";
        }
    }

	//Note: I discovered that using the modulo operator to randomise numbers can be biased
	//		Therefore I had to find another way to implement the randomiser without this bias
	//		The solution that I found was altered from a Stack Overflow article
	//		The article can be found here: http://stackoverflow.com/a/19553318

	//Using this function so that modulo is not used in the randomiser
	//int DeckOfCards::randomNumberGenerator(int min, int max)
	//{
	//	//Allows for number between min and slightly less than max
	//	//Includes min up to max
	//	//Returns a number to floor
	//	double randNum = rand();
	//	double randMax = RAND_MAX;
	//	double tempRand = randNum / (randMax + 1.0);

	//	double number = min + tempRand * (max - min);
	//	return floor(number);
	//}

   std::ostream& operator << (std::ostream& output, const DeckOfCards& target)
    {
       //Overloading the cout << operator to print the linked list 
	   return output << target.value() << endl;
    }

}
