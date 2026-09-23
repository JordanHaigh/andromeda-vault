#include "Card.h"

namespace SENG1120ASSIGN
{
	Card::Card()
	{
		face = ""; //Empty String
		value = 0; //Set to 0
		faceUp = false; //Default set to false
	}

	Card::Card(std::string face, int value, bool faceUp)
	{
		this->face = face;
		this->value = value;
		this->faceUp = faceUp;
	}

	Card::~Card()
	{
		//Not necessary since no variables are pointers
	}

	//Getters
	string Card::getFace()
	{
		return face;
	}

	string Card::getFace() const
	{
		return face;
	}

	int Card::getValue()
	{
		return value;
	}

	bool Card::getFaceUp()
	{
		return faceUp;
	}

	//Setters
	void Card::setFace(string face)
	{
		this->face = face;
	}
	void Card::setValue(int value)
	{
		this->value = value;
	}

	void Card::setFaceUp(bool faceUp)
	{
		this->faceUp = faceUp;
	}

	std::ostream& operator << (std::ostream& output, const Card& target)
	{
		//Return the face of the current card
		return output << target.getFace();
	}

	bool operator==(const Card & firstCard, const Card & secondCard)
	{
		//Check whether the face of the first card equals the second
		return (firstCard.getFace() == secondCard.getFace());
	}
}
