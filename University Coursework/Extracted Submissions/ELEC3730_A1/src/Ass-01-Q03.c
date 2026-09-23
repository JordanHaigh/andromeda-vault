//     $Date: 2017-03-13 08:12:38 +1100 (Mon, 13 Mar 2017) $
// $Revision: 821 $
//   $Author: Peter $

/*
 * ELEC3730 Assignment 1
 * Group: Jordan Haigh (c3256730) Evan Gresham(c3196094)
 * Tutorial Group: Friday 2pm-4pm
 * Task 3: Applying filter to wave file
 * this function reads filter coefficients from a file and data from the wave file
 * it then applies the filter to the data and then writes the filtered data to a file
 *
 */

#include "Ass-01.h"

int filter(char *filter_filename, char *input_wavefilename, char *output_wavefilename)
{


	int numberOfCoeffs;
	double *filterCoeff;//array of coefficient values


	//read in data for coefficients
	if(read_coefficients(&numberOfCoeffs, &filterCoeff, filter_filename) !=0){//if error occurred while reading filter file
		printf("error in reading file\n");//error message
		return -1;

	}

	pcm_wavefile_header_t header; //header file storing waveFile info
	char* data;//the wave data stored in the wavefile

	//read in data from the waveFile
	if(read_pcm_wavefile(&header, &data, input_wavefilename) != 0 ){//if error occurred while reading wave file
		printf("error in reading waveFile\n");//error message
		free(filterCoeff);//free memory
		free(data);
		return -1;
	}


	int sizeOfData = header.Subchunk2Size;//store the amount of data locally


	/*
	 * input data is given in 16 bit so store it as a short
	 * filter coefficient  is given in 8 bit so store it as a char
	 */

	short *filteredData = (short*)malloc((size_t)(sizeof(short)* sizeOfData));//allocate memory (sizeOfData number of shorts) for the filtered data

	if(!filteredData){////if malloc request failed
		printf("Error. Malloc request failed..\n");
		//free memory
		free(data);
		return -1;
	}


	short *dataIn16bit = (short*)data; //cast data to shorts

	printf("filter the inputs...\n");
	for(int k = 0 ; k< sizeOfData/2;k++){//for each output value
		filteredData[k] = 0;
		for(int n = 0 ; n < numberOfCoeffs;n++){//sum the product of coeff values and the input data to get the filter
			if(k>=n){//since no circular buffer is required then ignore any filter coefficients which  are out of bounds

				filteredData[k] += (filterCoeff[n] * dataIn16bit[k-n]);

			}
		}
	}

	char *filteredDataChar = (char*)filteredData; // cast to char for use as argument
	printf("-> Done\n\n");
	printf("Write output wavefile %s...\n", output_wavefilename);
	if(write_pcm_wavefile(&header, filteredDataChar, output_wavefilename ) != 0){//if error occurred while writing to file
		printf("error in writing to file\n");//error message

		//free memory before returning
		free(filteredData);
		free(data);
		free(filterCoeff);
		return -1;
	}

	free(filteredData);
	free(data);
	free(filterCoeff);

	printf("-> Done. \n\n");

	return 0;
}
