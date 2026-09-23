#ifndef STUDENT_H
#define STUDENT_H
#include <string>
#include <cstdlib>
#include <sstream>
using namespace std;

/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * Student.h
 */

namespace SENG1120ASSIGN
{
	class Student
	{
		/*
		 * Overloaded Cout << Operator
		 * Returns the name and grade of the student in the appropriate format
		 */
		friend std::ostream& operator<< (std::ostream &output, const Student &target);

		/*
		 * Overloaded < Operator
		 * Returns a boolean value whether the name of itemA is less than the name of itemB
		 */
		friend bool operator < (const Student& itemA, const Student& itemB);

		/*
		 * Overloaded == Operator
		 * Returns a boolean value whether the name of itemA is equal to the name of itemB
		 */
		friend bool operator == (const Student& itemA, const Student& itemB);

		public:
			/*========================================CONSTRUCTORS========================================*/
			
			/*
			 * Student();
			 * Sets the name of the student as a blank string. Sets the grade of the student  0.0
			 */
			Student();

			/*
			 * Student(std:string name, float grade);
			 * Sets the name of the student to the inputted 'name' and the grade to the inputted 'grade'
			 * @param name - String input of Student's name
			 * @param grade - Float input of Student's grade
			 */
			Student(std::string name, float grade);

			/*
			 * ~Student();
			 * Not used since the Student Class is not using pointers
			 */
			~Student();

			/*========================================ACCESSORS========================================*/
			
			/*
			 * string getName();
			 * Pre-Condition: Valid student name must exist
			 * Post-Condition: Returns the name of the student
			 * @return - String containing the name of the student
			 */
			string getName();
			
			/*
			 * string getName() const;
			 * Pre-Condition: Valid student name must exist
			 * Post-Condition: Returns the name of the student
			 * @return - String containing the name of the student
			 */
			string getName() const;

			/*
			* float getGrade();
			* Pre-Condition: Valid student grade must exist
			* Post-Condition: Returns the grade of the student
			* @return - Float containing the grade of the student
			*/
			float getGrade();

			/*
			* float getGrade() const;
			* Pre-Condition: Valid student grade must exist
			* Post-Condition: Returns the grade of the student
			* @return - Float containing the grade of the student
			*/
			float getGrade() const;

			//string getData(const Student& studentData);
			string getData(const Student& studentData) const;

			/*========================================MUTATORS========================================*/
			
			/*
			 * void setName(std::string name);
			 * Pre-Condition: Must be a valid string
			 * Post-Condition: Sets the name of the student to the inputted 'name'
			 * @param name - String that sets the value of the student's name
			 */
			void setName(std::string name);
			
			/*
			 * void setGrade(float grade);
			 * Pre-Condition: Must be a valid float
			 * Post-Condition: Sets the grade of the student to the inputted 'grade'
			 * @param grade - Float that sets the value of the student's grade
			 */
			void setGrade(float grade);

			
			/*========================================QUERY========================================*/
			
			/*
			* int compare(const Student& itemA, const Student& itemB);
			* Pre-Condition: Valid Students (itemA and itemB) must exist
			* Post-Condition: Returns an integer defining the comparison of the two items
			* @param itemA - Student Reference containing a name and grade
			* @param itemB - Student Reference containing a name and grade
			* @return - Integer (-1,0,1) determining the comparison of the two items
			*/
			int compare(const Student& itemA, const Student& itemB);


		private:
			std::string name;
			float grade;

	};
}
#endif


