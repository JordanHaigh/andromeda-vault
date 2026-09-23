#include "HandOfCards.h"

namespace SENG1120ASSIGN
{

	HandOfCards::HandOfCards()
	{
		LList = new LinkedList<Card>();
	}

	HandOfCards::~HandOfCards()
	{
		delete LList;
	}

	int HandOfCards::count()
	{
		return genericCount(true);
	}
	
	int HandOfCards::countAll()
	{
		return genericCount(false);
	}

	int HandOfCards::genericCount(bool onlyCountFacingUp)
	{
		int totalValue = 0;
		//Reset the linkedlist to the head
		LList->iteratorReset();
		while (LList->iteratorHasNext())
		{
			LList->iteratorNext();
			Card* c = LList->iteratorData();
			//Check whether the card is facing up or not
			if ((!onlyCountFacingUp) || (onlyCountFacingUp && c->getFaceUp()))
			{
				//Count value of hand
				totalValue += c->getValue();
			}
		}
		return totalValue;
	}

	std::string HandOfCards::value()
	{
		string output;
		//Reset the iterator to the head
		LList->iteratorReset();
		while (LList->iteratorHasNext())
		{
			LList->iteratorNext();
			//Store card data in a temporary variable
			Card* c = LList->iteratorData();
			if (!c->getFaceUp())
				//Append to the output string
				output.append("?-? ");
			else
			{
				//Append to the output string
				output.append(c->getFace());
				output.append(" ");
			}
		}
		return output;
	}

	std::string HandOfCards::value() const
	{
		string output;
		//Reset the iterator to the head
		LList->iteratorReset();
		while (LList->iteratorHasNext())
		{
			LList->iteratorNext();
			//Store card in a temporary variable
			Card* c = LList->iteratorData();
			if (!c->getFaceUp())
				//Append to the output string
				output.append("?-? ");
			else
			{
				//Append to the output string
				output.append(c->getFace());
				output.append(" ");
			}
		}
		return output;
	}
	
	void HandOfCards::faceUp()
	{
		//Reset iterator to the head of the linked list
		LList->iteratorReset();
		while(LList->iteratorHasNext())
		{
			LList->iteratorNext();
			//Set card faceUp to true
			LList->iteratorData()->setFaceUp(true);
		}
	}

	void HandOfCards::add(Card* inputCard, bool faceUp)
	{
		//Set the faceUp value of inputCard to the argument
		inputCard->setFaceUp(faceUp);
		//Add the new card to the tail of the linked list
		LList->addToTail(inputCard);
	}

	std::ostream& operator << (std::ostream& output, const HandOfCards& target)
	{
		//Overloading the cout << operator to print the Hand of cards
		return output << target.value() << endl;
	}

}

