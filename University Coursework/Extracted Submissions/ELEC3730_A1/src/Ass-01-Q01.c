//     $Date: 2018-03-11 05:18:25 +1100 (Sun, 11 Mar 2018) $
// $Revision: 1206 $
//   $Author: Peter $

// Question 1

/*
 * ELEC3730 Assignment 1
 * Group: Jordan Haigh (c3256730) Evan Gresham(c3196094)
 * Tutorial Group: Friday 2pm-4pm
 * Task 1: Filter Coefficient Reader
 * Reads a filter file identifying the number of coefficients and values stored every 8 bits
 * Cross checks that the filter file is not corrupted or does exist in the filepath
 */


#include "Ass-01.h"

int read_coefficients(int *coeff_num_p, double **coeff_values_p, char *filename)
{
	//Input filepath
	if(filename == NULL)
	{
		printf("file name empty\n");
		return -1;
	}

	FILE* inputFile = fopen(filename, "r");

	if(!inputFile)
	{
		printf("Error filename not found. Closing...\n");
		fclose(inputFile);
		return -1;
	}

	fseek(inputFile,0,SEEK_END);//move pointer to end of file
	long int sizeOfFile = ftell(inputFile);//get the number of bites travelled
	fseek(inputFile,0,SEEK_SET);//return to start of file
	//File has opened successfully,  andread to end of file - we can now start working

	//First four bytes contain integer n, stored in little endian
	//which represents the number of filter coefficients.
	fread(coeff_num_p,sizeof(int),1,inputFile); //storing in coeff_num_p, using size of integer to determine size(allocating 4 bytes, reading once
	//

	//cross check size of file
	if(*coeff_num_p * 8 + 4 != sizeOfFile )// //checking file size - 8 * coeff(from formula) + 4(for the integer defining numb of coeff)
	{
		printf("Error size of file does not match the header info\n");
		fclose(inputFile);
		return -1;
	}


//	The next 8n bytes contain an array of n floating point numbers stored as 8 byte double
//	precision which represent the filter coefficients

	//dont need to move using fseek, as we are already past the first four bytes
	//reading the 8n sections

	//allocate memory for the number of coefficients times 8 bytes (size of double)
	//cast to double pointer for storing in coeff_values_p
	*coeff_values_p = (double*)malloc((size_t)*coeff_num_p * sizeof(double));
	if(!coeff_values_p) //if the return from the malloc statement was null
	{
		printf("Error. Malloc request failed..\n");
		fclose(inputFile);
		return -1;
	}
	//need to free memory once finished or when we encounter an error and stop running binary file

	fread(*coeff_values_p, sizeof(double),*coeff_num_p,inputFile);

	//now that we have read through the whole file and stored values in positions, we need to print
	printf("\n");
	printf("File name: %s\n", filename);
	printf("Array size: %d\n", *coeff_num_p);



	//print out according to spec - line by line in groups of three
	for(int i = 0; i < *coeff_num_p; i++)
	{

		if(i%3 == 0)
			printf("\n");

		(i < *coeff_num_p) ? printf("%lf \t ", (*coeff_values_p)[i]) : printf(" \t ");
		//Whilst i is less than the number of coefficients, print out the value

	}
	printf("\n");

	return 0;
}
