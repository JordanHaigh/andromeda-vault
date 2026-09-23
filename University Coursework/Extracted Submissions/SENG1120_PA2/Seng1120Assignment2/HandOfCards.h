#ifndef HANDOFCARDS_H
#define HANDOFCARDS_H
#include "LinkedList.h"
#include "Card.h"
#include <cstdlib>
#include <string>
using namespace std;

namespace SENG1120ASSIGN
{
	class HandOfCards
	{
		friend std::ostream& operator << (std::ostream& output, const HandOfCards& target);

		public:
			/*
			 * Default Constructor
			 * HandOfCards();
			 * Creates an empty Hand of Cards
			 */
			HandOfCards();

			/*
			 * Destructor
			 * ~HandOfCards();
			 * Deletes a Hand of Cards
			 */
			~HandOfCards();

			/*
			 * int count();
			 * Pre-Condition: None
			 * Post-Condition: Counts the values of all cards with the faceUp as true
			 * @return - Integer sum of all values of cards facing up
			 */
			int count();

			/*
			* int countAll();
			* Pre-Condition: None
			* Post-Condition: Counts the values of all cards regardless of faceUp
			* @return - Integer sum of all values of cards
			*/
			int countAll();

			/*
			 * string value();
			 * Pre-Condition: LinkedList must contain valid nodes
			 * Post-Condition: Creates and returns a string of the faces of all cards in the players hand
			 * @return - String of faces of cards in players hand
			 */
			string value();

			/*
			 * string value() const;
			 * Pre-Condition: LinkedList must contain valid nodes
			 * Post-Condition: Creates and returns a string of the faces of all cards in the players hand
			 * @return - Const String of faces of cards in players hand
			 */
			string value() const;

			/*
			 * void faceUp();
			 * Pre-Condition: LinkedList must contain valid nodes
			 * Post-Condition: Iterates through the linked list and sets all cards faceUp as true
			 */
			void faceUp();

			/*
			* void add(Card* inputCard, bool faceUp)
			* Pre-Condition: inputCard must contain valid values
			* Post-Condition: Adds a card to the tail of the linked list
			* @param inputCard - Card that contains valid values
			* @param faceUp - Boolean value determining whether the card is face up or not
			*/
			void add(Card* inputCard, bool faceUp);
		private:
			LinkedList<Card>* LList;
			int genericCount(bool onlyCountFacingUp);

	};
}


#endif