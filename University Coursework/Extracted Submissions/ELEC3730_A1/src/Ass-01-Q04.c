//     $Date: 2018-03-11 05:18:25 +1100 (Sun, 11 Mar 2018) $
// $Revision: 1206 $
//   $Author: Peter $
/*
 * ELEC3730 Assignment 1
 * Group: Jordan Haigh (c3256730) Evan Gresham(c3196094)
 * Tutorial Group: Friday 2pm-4pm
 * Task 4: String Parser
 * Reads a string from input and determines the number of words that were read
 * Allocates the right amount of memory for each word and each character inside the word
 * Adds an additional byte of memory for null terminators at the end of a word
 */
#include "Ass-01.h"

int string_parser(char *inp, char **array_of_words_p[])
{
	//Structure is to read through the first time and find the total number of words
	//Allocate memory for the number of words
	//Run through a second time and and determine the length of each word
	//		This time, we want to allocate memory for every letter in each word
	//Run through a third and final time and add every letter of every word to its allocated index
	//Return the total number of words found

	//"Don't be too concerned about novel extensions to your code. There will be opportunities to do this in the next two assignments." - Peter Stepien
	//Program has some functionality with using backspaces but not all unit tests are able to be covered in the timespan of this assignment

	//Get the number of words found in the input char array
	int numberOfWords = 0;
	int currentWordLength = 0;
	char previousChar = ' '; //Set to a space character at the beginning. This is in the event of a space char at the beginning of a string


	//If input is an empty string, returns straight away
	if(inp[0] == '\0')
		return 0;



	int lengthOfInput = 0;
	for(int i = 0; inp[i] != '\0';i++)
	{
		lengthOfInput++;
	}
	lengthOfInput++; //for null terminator

	//Allocate memory for new char array called converted input
	//This array will take into account backspaces and at what positions they occur
	char* convertedInput = (char*)malloc((size_t)lengthOfInput * sizeof(char)); //null terminator


	int reader = 0; //Reading inp variable
	int writer = 0; //Writing to converted Input
	while(inp[reader] != '\0')
	{
		if(inp[reader] == 127 || inp[reader] == 8) //backspace char or delete char
		{
			if(writer > 0) //taken into account so the writer isn't being subtracted to a negative index
				writer--;
		}
		else
		{
			convertedInput[writer] = inp[reader]; //store char from reader into writer
			writer++;

		}

		reader++; //always incrementing writer
	}
	convertedInput[writer] = '\0'; //add null terminator

	//Now we have a string that has had backspaces catered for. We now start using this instead of inp*
	for(int i = 0; convertedInput[i] != '\0'; i++) //Iterate until end of the input string
	{
		if(convertedInput[i] != ' ')
		{
			currentWordLength++;

			//Found a valid character
			previousChar = convertedInput[i];
			//printf("Debug1: current char %c\n", previousChar);
			if(convertedInput[i+1] == '\0') //If the next char is the end of the file
			{
				//This gets around the edge case of the for loop stopping when it reaches '\0'
				if(currentWordLength > 0)
					numberOfWords++;
				currentWordLength = 0; //Reset
				//previousChar = inp[i];
				continue;
			}

		}
		else
		{
			//Invalid Character
			if(previousChar == ' ' || previousChar == 127 || previousChar == 8)
			{
				//printf("Debug1: space\n");
				//In the event of double spaces - move on.
				previousChar = convertedInput[i];
				continue;
			}
			else
			{
				//word is finished
				//Previous char was not a space, indicates end of a word
				//printf("Debug1: End of word - Space \n");
				if(currentWordLength > 0)
					numberOfWords++;
				currentWordLength = 0;

				previousChar = convertedInput[i];
				//printf("Debug1: \t\tCurrent word length is %d\n", currentWordLength);
			}
		}
	}


	//printf("Debug1: number of words found is %d\n", numberOfWords);


	//use malloc to allocate the right amount of memory
	(*array_of_words_p) = (char**)malloc(sizeof(char*) * (size_t)numberOfWords);

	//Run second for loop - reset variables
	currentWordLength = 0;
	previousChar = ' ';
	int wordIndex = 0; //Used in array indexes
	for(int i = 0; convertedInput[i] != '\0'; i++)
	{
		//printf("Debug2: Current char is %c or in ascii %d\n", inp[i], inp[i]);
		if(convertedInput[i] != ' ')
		{
			//not a space - valid character
			currentWordLength++;
			previousChar = convertedInput[i];
			if(convertedInput[i+1] == '\0' && currentWordLength > 0)
			{
				//next character is the end, it would not pick up the end of the word
				//printf("Debug2: \tI have found a word of size %d\n", currentWordLength);
				(*array_of_words_p)[wordIndex] = (char*)malloc(sizeof(char) * (size_t)(currentWordLength + 1)); //Plus 1 is for null terminator
				currentWordLength = 0;
			}
		}
		else
		{
			//Invalid Character
			if(previousChar == ' ' || previousChar == 127 || previousChar == 8)
			{
				//printf("Debug2: space\n");
				//In the event of double spaces - move on.
				previousChar = convertedInput[i];
				continue;
			}
			else
			{
				//End of word
				//printf("Debug2: \tI have found a word of size %d\n", currentWordLength);
				previousChar = convertedInput[i];
				(*array_of_words_p)[wordIndex] = (char*)malloc(sizeof(char) * (size_t)(currentWordLength)+1); //Plus 1 is for null terminator
				wordIndex++;
				currentWordLength = 0;

			}
		}
	}

	//Loop third and final time to add letters to array indexes
	//Reset variables

	currentWordLength = 0;
	previousChar = ' ';
	wordIndex = 0;
	int letterIndex = 0;

	for(int i = 0; convertedInput[i] != '\0'; i++)
	{
		if(convertedInput[i] != ' ')
		{
			//valid character
			(*array_of_words_p)[wordIndex][letterIndex] = convertedInput[i];
			//printf("Debug3: WordIndex: %d LetterIndex: %d Letter: %c\n", wordIndex, letterIndex, inp[i]);

			previousChar = convertedInput[i];
			letterIndex++;
			if(convertedInput[i+1] == '\0') //End of string
			{
				(*array_of_words_p)[wordIndex][letterIndex] = '\0';
				wordIndex++;
				letterIndex = 0;
			}
		}
		else
		{
			//Invalid Character
			if(previousChar == ' ' || previousChar == 127 || previousChar == 8)
			{
				//printf("Debug2: space\n");
				//In the event of double spaces - move on.
				previousChar = convertedInput[i];
				continue;
			}
			else
			{
				//reached end of word
				previousChar = convertedInput[i];

					(*array_of_words_p)[wordIndex][letterIndex] = '\0';
					//printf("Debug3: \t I have created a word: %s\n", (*array_of_words_p)[wordIndex]);
					wordIndex++;
					letterIndex = 0;
			}
		}
	}

	//free the converted input as we do not need it anymore
	free(convertedInput);

	return numberOfWords;
}
