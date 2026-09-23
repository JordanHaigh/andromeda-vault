#ifndef DECKOFCARDS_H
#define DECKOFCARDS_H
#include "LinkedList.h"
#include <string>
#include <iostream>
#include <cstdlib>
#include <cmath>
#include <sstream>
using namespace std;

/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * DeckOfCards.h
 */

namespace SENG1120ASSIGN
{
    class DeckOfCards
    {
        //Friend Functions
        friend std::ostream& operator << (std::ostream& output, const DeckOfCards& target);
		
        public:
            /*CONSTRUCTORS*/
            /*
             * DeckOfCards() Constructor
			 *
             */
            DeckOfCards(); //Constructor
            
			/*
			 * !DeckOfCards() Destructor
			 *
			 */
			~DeckOfCards();

			/*OTHER METHODS*/
			/*
			 * void shuffle()
			 * Precondition: Nodes must exist in the linkedlist
			 * Postcondition: Shuffles entire linked list according to Yates Algorithm
			 */
            void shuffle();

			/*
			 * int length()
			 * Precondition: None
			 * Postcondition: Returns the value of the current amount of nodes between the head and tail
			 * @return - Integer value of the number of Nodes (Excluding head and tail)
			 */
            int length();
            
			/*
			* bool empty()
			* Precondition: None
			* Postcondition: Checks if list is empty. Returns true or false depending whether the list is empty or not
			* @return - 1 or 0 (True || False)
			*/
			bool empty();

			/*
			* int position(string input)
			* Precondition: 'input' must be a valid input for the method
			* Postcondition: Returns the integer position of the specified node in the linked list
			* @param input - String input specifying a certain node
			* @return - Returns the integer position of the specified node
			*/
            int position(string input);

			/*
			* string value()
			* Precondition: 
			* Postcondition:
			* @return - 
			*/
			std::string value();
			std::string value() const;

			/*
			* bool remove(string input)
			* Precondition: 'input' must be a valid string in the Linked List
			* Postcondition: Removes the specified node from the Linked List
			* @param input - string input that determines the specified node
			* @return - Returns boolean value (True || False) depending on whether the node was removed or not
			*/
            bool remove(string input);



        private:
            LinkedList* LList;
            enum CARDSUIT {S, H, C, D};
            enum CARDROYAL {J, Q, K, A};
            
			/*
			* string suitToString(int suitInt)
			* Precondition: 'suitInt' must be a valid integer in the enumerated list 'CARDSUIT'
			* Postcondition: Returns the specified suit into a string format to be printed as a card later
			* @param suitInt - Integer that equates to its position in the enum CARDSUIT
			* @return - Returns string of the card suit
			*/
			string suitToString(int suitInt);

			/*
			* string royalToString(int royalInt)
			* Precondition: 'royalInt' must be a valid integer in the enumerated list 'CARDROYAL'
			* Postcondition: Returns the specified Royal card into a string format to be printed as a card later
			* @param suitInt - Integer that equates to its position in the enum CARDROYAL
			* @return - Returns string of a royal card
			*/
            string royalToString(int royalInt);

			/*
			* int randomNumberGenerator(int min, int max)
			* Precondition: None
			* Postcondition: Returns a randomly selected number within the range 'min' to 'max'
			* @param min - Low value of int for the randomiser
			* @param max - High value of int for the randomiser
			* @return - Returns randomly selected integer from with the range 'min' to 'max'
			*/
			int randomNumberGenerator(int min, int max);

    };

}
#endif
