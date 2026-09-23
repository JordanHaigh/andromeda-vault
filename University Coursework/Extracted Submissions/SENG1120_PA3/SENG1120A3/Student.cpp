#include "Student.h"

/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * Student.cpp
 */

namespace SENG1120ASSIGN
{
	/*========================================CONSTRUCTORS========================================*/
	Student::Student()
	{
		name = ""; //Empty String
		grade = 0.0; //Blank Grade
	}

	Student::Student(string name, float grade)
	{
		this->name = name; //Set name to the inputted 'name'
		this->grade = grade; //Set grade to the inputted 'grade'
	}

	Student::~Student()
	{
		//Not necessary since all variables are not pointers
	}

	/*========================================ACCESSORS========================================*/
	string Student::getName()
	{
		return name;
	}

	string Student::getName() const
	{
		return name;
	}

	float Student::getGrade()
	{
		return grade;
	}

	float Student::getGrade() const
	{
		return grade;
	}

	//string Student::getData(const Student& studentData)
	//{
	//	string output;
	//	ostringstream convert;
	//	output.append("(");
	//	convert << studentData.getName();
	//	output.append(convert.str());
	//	output.append(",");
	//	convert << studentData.getGrade();
	//	output.append(convert.str());
	//	output.append(") ");
	//	return output;
	//}

	string Student::getData(const Student& studentData) const
	{
		string output;
		ostringstream convertName;
		ostringstream convertGrade;
		output.append("(");

		convertName << studentData.getName();
		output.append(convertName.str());
		output.append(", ");
		
		convertGrade << studentData.getGrade();
		output.append(convertGrade.str());

		output.append(") ");
		return output;
	}
	/*========================================MUTATORS========================================*/
	void Student::setName(string name)
	{
		this->name = name;
	}

	void Student::setGrade(float grade)
	{
		this->grade = grade;
	}

	int Student::compare(const Student& itemA, const Student& itemB)
	{
		//Check the comparison of the names
		if (itemA.getName() < itemB.getName())
			return -1;
		else if (itemA.getName() == itemB.getName())
			return 0;
		else
			return 1;
	}


	/*========================================OVERLOADED OPERATORS========================================*/
	std::ostream& operator<< (std::ostream &output, const Student &target)
	{
		//Output in the specified format
		return output << target.getData(target);
	}
	
	bool operator < (const Student& itemA, const Student& itemB)
	{
		return itemA.getName() < itemB.getName();
	}
	
	bool operator == (const Student& itemA, const Student& itemB)
	{
		return itemA.getName() == itemB.getName();
	}

}