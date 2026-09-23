#ifndef CARD_H
#define CARD_H
#include <iostream>
#include <string>
using namespace std;

namespace SENG1120ASSIGN
{
	class Card
	{
		/*
		 * Overloaded cout << operator
		 * Returns the face of the current card
		 */
		friend std::ostream& operator << (std::ostream& output, const Card& target);
		/*
		 * Overloaded == operator
		 * Checks whether the face of the first and second card are the same.
		 */
		friend bool operator == (const Card &firstCard, const Card &secondCard);

		public:
			/*CONSTRUCTORS*/
			/*
			 * Default Constructor
			 * Creates new card with empty values
			 */
			Card();

			/*
			 * Overloaded Constructor
			 * @param face - String that defines the card suit and position
			 * @param value - Integer that is coupled to the cards value in Blackjack
			 * @param faceUp - Boolean value to determine whether the card is face up or not
			 */
			Card(std::string face, int value, bool faceUp);
			
			/*
			 * Destructor
			 * Deletes the card
			 */
			~Card();

			/*ACCESSOR METHODS*/
			/*
			 * string getFace();
			 * Pre-Condition: A valid string face must exist
			 * Post-Condition: Returns the face of a card
			 * @return - Returns a string of the face of the card
			 */
			string getFace();

			/*
			 * string getFace() const;
			 * Pre-Condition: A valid string face must exist
			 * Post-Condition: Returns a const version of the face of a card
			 * @return - Returns a const version of the face of the card (String)
			 */
			string getFace() const;

			/*
			 * int getValue();
			 * Pre-Condition: A valid string face must exist with the respective value
			 * Post-Condition: Returns the value of a specific card
			 * @return - Returns an integer of the value of the card
			 */
			int getValue();

			/*
			 * bool getFaceUp();
			 * Pre-Condition: A valid string face must exist with the respective boolean faceUp value
			 * Post-Condition: Returns true or false depending of whether the card is face up or not
			 * @return - Returns a boolean value whether the card is face up or not
			 */
			bool getFaceUp();

			/*MUTATOR METHODS*/
			/*
			 * void setFace(string face);
			 * Pre-Condition: Input must be a valid string
			 * Post-Condition: Sets the face of the card to the input
			 * @param face - String that sets the face of a card
			 */
			void setFace(string face);
			
			/*
			* void setValue(int value);
			* Pre-Condition: Input must be a valid integer
			* Post-Condition: Sets the value of the card to the input
			* @param value - Integer that sets the value of a card
			*/
			void setValue(int value);

			/*
			* void setFaceUp(bool faceUp);
			* Pre-Condition: Input must be a valid boolean value
			* Post-Condition: Sets the faceUp of the card to the input
			* @param faceUp - Boolean value that sets the face of a card
			*/
			void setFaceUp(bool faceUp);

		private:
			string face;
			int value;
			bool faceUp;
	};

}
#endif