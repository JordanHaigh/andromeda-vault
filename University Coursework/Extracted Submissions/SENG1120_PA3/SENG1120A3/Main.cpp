#include "BST.h"
#include "Student.h"
#include <iostream>
#include <string>
#include <array>
#include <ctime>
#include <cstdlib>
using namespace std;
using namespace SENG1120ASSIGN;
/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * Main.cpp
 */

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



//http://stackoverflow.com/a/17156297
template<std::size_t SIZE>
void shuffle(std::array<string, SIZE> &studentNames)
{
	int index;
	//String variable used to store the data of the node to help with the swapping section
	string temp1;
	string temp2;
	for (int i = studentNames.size()-1; i > 0; i--) //TODO find a function that finds the total number of elements in a string array
	{
		//Get random position from the size of the array
		index = rand() % (i + 1);
		//Create a temporary variable to be swapped later
		temp1 = studentNames[i];
		//Create second temporary variable to be swapped later
		temp2 = studentNames[index];
		//Swap indexes with the temporary data
		studentNames[index] = temp1;
		studentNames[i] = temp2;
	}
}


int main(int numberOfArguments, char* argumentList[])
{
	BST<Student>* tree = new BST<Student>;
	setRandSeed(numberOfArguments, argumentList);

	/*Setting up the students names. Randomised using a shuffle method*/
	std::array<string, 50> studentNames = {"Adam","Adrian","Alexander","Andrew","Ashley", 
		"Benjamin","Bradley","Brobie","Callan","Callum","Cameron","Chris",
		"Damian","David","Dillon","Dylan","Ethan","Frederik","Hong","Hugh","Jackson",
		"Jacob","James","Jared","Jodi","Jonathan","Joshua","Julius","Kelly","Kenias","KiSoon",
		"Lance","Liam","Madison","Magdalena", "Marcus","Mark","Melanie","Min","Mitchell",
		"Nicholas","Ryan","Sang","Shane","Simon","Thomas","Timothy","Trent","Troy","Zaanif"};
	shuffle(studentNames);

	float studentGrade;

	/*Inserting students into the BST*/
	for (int i = 0; i < studentNames.size(); i++)
	{
		//Need to use 101 so that Students have the possibility of getting a grade of 100
		studentGrade = rand() % 101; 
		Student* newStudent = new Student(studentNames[i], studentGrade);
		tree->insert(newStudent);
	}

	/*Print out the tree (Name,Grade)*/
	cout << tree->inOrderPrint() << endl;

	/*Find total number of HD's (Grade greater than or equal to 85)*/
	int numberHDs = tree->findHD();
	cout << "HDs: " << numberHDs << endl;

	/*Find average - check whether it should be float or int*/
	int average = tree->findAverage();
	cout << "Average: " << average << endl;

	//Remove this later
	cout << "\n\n\n" << endl;

	/*Remove grades less than 50*/
	tree->deleteFailures();

	/*Redo steps 3,4,5*/
	/*Print out the tree (Name,Grade)*/
	cout << tree->inOrderPrint() << endl;

	/*Find total number of HD's (Grade greater than or equal to 85)*/
	numberHDs = tree->findHD();
	cout << "HDs: " << numberHDs << endl;

	/*Find average - check whether it should be float or int*/
	average = tree->findAverage();
	cout << "Average: " << average << endl;

	delete tree;

	return 0;

}