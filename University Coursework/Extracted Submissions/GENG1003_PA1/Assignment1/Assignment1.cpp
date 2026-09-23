/*
* Author: J Haigh	StNo: 3256730	Lab: 2pm Wed
* Task: GENG1003 Assignment 1
* Date Commenced: 27-August-2016	Date Completed: 6-September-2016
*
* This program was designed to allow the single roots of a quartic equation to be estimated using the bisection method
* The program uses an input data file to read and provide values to the coefficients of the quartic polynomial
* As well as an estimate of the ranges of values which the roots might be found
*
* Inputs:		FILE *fileInput - File to input into program
* Outputs:		String output of number of roots and their location
* Constants:	ACCURACY - Determines whether a root is within the range
				H - Increment value
*/
#include "stdafx.h"
#include <math.h>
#define ACCURACY 0.000001
#define H 0.1

/*
* double quarticEquation(double b, double c,double d, double e, double x)
* Function calculates the quartic equation of the the inputs and returns the equation
* Inputs: B,C,D,E (Double)- Coefficients for equation		X(Double) - X Value for equation
* Outputs: Equation(Double) - Quartic Equation
*/
double quarticEquation(double b, double c, double d, double e, double x)
{
	double equation = pow(x, 4) + (b*x*x*x) + (c*x*x) + (d*x) + e;
	return equation;
}

/*
* int signsAreDifferent(double lowerCurve, double upperCurve)
* Function checks whether the signs of the lower and upper curves are different
* Returns 1 (True) or 0 (False)
* Inputs: lowerCurve (Double) - One side of the curve in range		upperCurve (Double) - Other side of curve in range
* Outputs: Boolean value of true or false
*/
int signsAreDifferent(double lowerCurve, double upperCurve)
{
	if ((lowerCurve < 0 && upperCurve > 0) || (lowerCurve > 0 && upperCurve < 0))
		return 1;
	else
		return 0;
}

/*
* int signsAreSame(double lowerCurve, double upperCurve)
* Function checks whether the signs of the lower and upper curves are the same
* Returns 1 (True) is signs are the same, returns 0 (False) if not
* Inputs: lowerCurve (Double) - One side of the curve in range		upperCurve (Double) - Other side of curve in range
* Outputs: Int value of 1 (True) or 0 (False)
*/
int signsAreSame(double lowerCurve, double upperCurve)
{
	if ((lowerCurve < 0 && upperCurve < 0) || (lowerCurve > 0 && upperCurve > 0))
		return 1;
	else
		return 0;
}

/*
* double bisectionMethod(double xLowerBound, double xUpperBound, double b, double c, double d, double e)
* Function uses the bisection method to find the x Coordinate of a root.
* Inputs: xLowerBound (Double) - Lower xCoordinate of the range	xUpperBound (Double) - Upper xCoordinate of the range
*			B,C,D,E (Double) - Inputted coefficients from the input file
* Outputs: Returns the xCoordinate where the root is found
* Constants: ACCURACY (0.000001) Helps define whether a root is within accuracy of the assignment specification or not
*/
double bisectionMethod(double xLowerBound, double xUpperBound, double b, double c, double d, double e)
{
	/*Create Quartic Equation of the xLowerBound to determine Y value*/
	double yLowerCurve = quarticEquation(b, c, d, e, xLowerBound);
	/*Create Quartic Equation of the xUpperBound to determine Y value*/
	double yUpperCurve = quarticEquation(b, c, d, e, xUpperBound);

	/*Find midpoint between the xLowerBound and xUpperBound*/
	double xMidpoint = (xLowerBound + xUpperBound) / 2.0;
	/*Create the Equartic Equation of the xMidpoint to determine Y value*/
	double yMidpoint = quarticEquation(b, c, d, e, xMidpoint);


	/*If midpoint is within the accuracy of the assignment specification (0.000001)*/
	if (yMidpoint >= (ACCURACY *-1) && yMidpoint <= (ACCURACY))
	{
		/*Root is found within accuracy*/
		/*Return the x Coordinate that the root was found at*/
		/*printf("Root found within accuracy\n");*/
		return xMidpoint;
	}

	/*Check if midpoint is below 0*/
	/*Substitute for Boolean Values (True = 1, False = 0)*/
	else if (signsAreSame(yLowerCurve, yMidpoint) == 1)
	{
		/*Root not in range. Increment and recurse operation*/
		return bisectionMethod(xMidpoint, xUpperBound, b, c, d, e);
	}
	/*If midpoint is greater than 0*/
	/*Substitute for Boolean Values (True = 1, False = 0)*/
	else if (signsAreSame(yMidpoint, yUpperCurve) == 1)
	{
		/*Root not in range. Decrement and recurse operation*/
		return bisectionMethod(xLowerBound, xMidpoint, b, c, d, e);
	}
	else
	{
		/*Shouldn't be able to access this part of the if else statement*/
		printf("ERROR. Unhandled Bisection");
	}
}

int main(void)
{
	FILE *inputFile;					/*File that is to be inputted into the program*/
	int inputStatus;					/*Input status to determine whether the program is able to continue*/
	int numberOfInputs;					/*Number of Inputs collected from the input1.dat file*/
	int numberOfRoots = 0;				/*Current Number of Roots found in the current line*/
	double b, c, d, e;					/*Coefficients collected from the input1.dat file*/
	double x0, x1;						/*LowerBound and UpperBound collected from the input1.dat file*/
	double xLowerBound, xUpperBound;	/*Modified LowerBound and UpperBound to be used in calculating the roots*/
	double originalX0, originalX1;		/*Original lowerBound and upperBound inputs*/
	double root1, root2, root3, root4;	/*Double values to store the roots*/
	//double roots[4];					/*Array to store the number of roots and its data*/


	//inputStatus = fopen_s(&inputFile, "input1.dat", "r");
	inputStatus = fopen_s(&inputFile, "U:\\GENG1003\\input1.dat", "r");

	/*If the input status does not equal zero, this means there is a problem with opening the file*/
	if (inputStatus != 0)
	{
		printf("ERROR. Problem opening file");
		return -1;
	}
	/*If the input status is zero, continue with program*/
	else
	{
		numberOfInputs = fscanf_s(inputFile, "%lf%lf%lf%lf%lf%lf", &b, &c, &d, &e, &x0, &x1);

		/*Check if file does not contain inputs*/
		if (numberOfInputs == -1)
		{
			printf("ERROR. There is NO data to work with");
		}

		/*Number of inputs match the number of input variables required*/

		while (numberOfInputs == 6)
		{
			/*Store the original inputs to be remembered for the output string*/
			originalX0 = x0;
			originalX1 = x1;

			/*Loop inside the line*/
			while (x0 < x1)
			{
				xLowerBound = x0;
				if (x0 + H > x1)
					xUpperBound = x1;
				else
					xUpperBound = x0 + H;

				/*Create the quartic Equations of the LowerBound and UpperBound*/
				double lowerCurve = quarticEquation(b, c, d, e, xLowerBound);
				double upperCurve = quarticEquation(b, c, d, e, xUpperBound);

				/*Check the equations within the first upper and lowerBound*/
				if ((upperCurve >= (ACCURACY * -1)) && (upperCurve <= ACCURACY))
				{
					/*Root found, no need to use bisection method*/
					/*printf("Root found.\n");*/
					//roots[numberOfRoots] = xUpperBound;
					numberOfRoots++;
					/*Switch case used to determine which root will store the new value*/
					switch (numberOfRoots)
					{
						case 1: root1 = xUpperBound;
							break;
						case 2: root2 = xUpperBound;
							break;
						case 3: root3 = xUpperBound;
							break;
						case 4: root4 = xUpperBound;
							break;
						default: printf("\nError."); /*Shouldn't be able to access this section*/
					}
					/*Increment x0*/
					x0 += H;
				}

				/*Substitute for Boolean Values (True = 1, False = 0)*/
				else if (signsAreDifferent(lowerCurve, upperCurve) == 1)
				{
					/*Curve contains a root. Find out whether the curve is positive or negative*/
					double xCoordinate = bisectionMethod(xLowerBound, xUpperBound, b, c, d, e);
					//roots[numberOfRoots] = xCoordinate;
					numberOfRoots++;
					/*Switch case used to determine which root will store the new value*/
					switch (numberOfRoots)
					{
					case 1: root1 = xCoordinate;
						break;
					case 2: root2 = xCoordinate;
						break;
					case 3: root3 = xCoordinate;
						break;
					case 4: root4 = xCoordinate;
						break;
					default: printf("\nError."); /*Shouldn't be able to access this section*/
					}
				}

				else
				{
					/*No root found. Do nothing*/
				}
				/*Increment x0*/
				x0 += H;

			}

			/*Output Strings*/
			printf("\nFor quartic polynomial a= 1; b = %.5lf; c=%.5lf; d=%.5lf; e=%.5lf", b, c, d, e);
			printf("\nWith range from %lf to %lf", originalX0, originalX1);
			printf("\nGives F(x) = zero at the following values of x");
			/*Switch case used to determine which line to print out*/
			switch (numberOfRoots)
			{
			case 0: printf("");
				break;
			case 1: printf("\nx = %.5lf", root1);
				break;
			case 2: printf("\nx = %.5lf, %.5lf", root1, root2);
				break;
			case 3: printf("\nx = %.5lf, %.5lf, %.5lf", root1, root2, root3);
				break;
			case 4: printf("\nx = %.5lf, %.5lf, %.5lf, %.5lf", root1, root2, root3, root4);
				break;
			default: printf("\nError."); /*Shouldn't be able to access this section*/
			}

			//for (int i = 0; i < numberOfRoots; i++)
			//{
			//	printf("%.5lf", roots[i]);
			//	printf(", ");
			//}
			printf("\n%d distinct roots found", numberOfRoots);
			printf("\n\n");

			/*Prime the next number of inputs*/
			numberOfInputs = fscanf_s(inputFile, "%lf%lf%lf%lf%lf%lf", &b, &c, &d, &e, &x0, &x1);
			/*Reset numberOfRoots for the next file input*/
			numberOfRoots = 0;
		}
	}
	fclose(inputFile); /*Close the file*/

	printf("End of file reached. Program will now close.\n");

	return 0;
}

