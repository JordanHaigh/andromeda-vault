//     $Date: 2018-03-11 05:18:25 +1100 (Sun, 11 Mar 2018) $
// $Revision: 1206 $
//   $Author: Peter $

/*
 * ELEC3730 Assignment 1
 * Group: Jordan Haigh (c3256730) Evan Gresham(c3196094)
 * Tutorial Group: Friday 2pm-4pm
 * Task 2: Reading and Writing file
 * Reads a pcm wave file cross checking aspects of the header data. If it a valid wave file,
 * it will print out all header information.
 */
#include "Ass-01.h"

int read_pcm_wavefile(pcm_wavefile_header_t *header_p, char **data_p, char *filename)
{
	//Input file
		if(filename == NULL)
		{
			printf("File name empty\n");
			return -1;
		}

		FILE* inputFile = fopen(filename, "rb");

		if(!inputFile) //Errors in opening
		{
			printf("Error Filename not found. Closing...\n");
			fclose(inputFile);
			return -1;
		}

		//Retrieving file size
		fseek(inputFile,0,SEEK_END);//Move pointer to end of file
		long int sizeOfFile = ftell(inputFile);//Get the number of bytes travelled
		fseek(inputFile,0,SEEK_SET);//Return to start of file


		//Read header data
		if(fread(header_p, sizeof(pcm_wavefile_header_t),1,inputFile) != 1)
		{
			//Issues found whilst gathering header data
			printf("Error. Unable to gather header data\n");
			fclose(inputFile);
			return -1;
		}

		uint8_t errorsFound = 0; //Used in the event of multiple errors


		if(sizeOfFile != header_p->ChunkSize + 8)
		{
			printf("Error. Size of the actual file does not match metadata\n");
			errorsFound = 1;
		}


		//check if file has  RIFF, WAVE, fmt and data
		//cant use string compare as there are junk chars after the RIFF keyword "RIFFZ_?"
		if(!(header_p->ChunkID[0] == 'R' && header_p->ChunkID[1] == 'I' && header_p->ChunkID[2] == 'F' && header_p->ChunkID[3] == 'F'))
		{
			printf("Error. RIFF Descriptor is invalid\n");
			errorsFound = 1;

		}

		//Error check if chunksize matches the size of the subchunks
		//chunksize = 4 + (8+subchunk1size) + (8+subchunk2size)
		if(header_p->ChunkSize != sizeof(int) + sizeof(double) + header_p->Subchunk1Size + sizeof(double) + header_p->Subchunk2Size)
		{
			printf("Error. Subchunk sizes do not match total chunk size..\n");
			errorsFound = 1;

		}

		//Check WAVE keyword
		if(!(header_p->Format[0] == 'W' && header_p->Format[1] == 'A' && header_p->Format[2] == 'V' && header_p->Format[3] == 'E'))
		{
			printf("Error. Wave Descriptor is invalid\n");
			errorsFound = 1;

		}

		//Check fmt keyword and data keyword
		if(!(header_p->Subchunk1ID[0] == 'f' && header_p->Subchunk1ID[1] == 'm' && header_p->Subchunk1ID[2] == 't'
				&& header_p->Subchunk2ID[0] == 'd' && header_p->Subchunk2ID[1] == 'a' && header_p->Subchunk2ID[2] == 't' && header_p->Subchunk2ID[3] == 'a'))
		{
			printf("Error. One of the SubchunkIds are invalid\n");
			errorsFound = 1;

		}


		//Error check that Subchunk1Size is 16 for pcm format
		if(header_p->Subchunk1Size != 16)
		{
			printf("Error. Subchunk1Size is not in PCM Format\n");
			errorsFound = 1;

		}

		//Error check if AudioFormat is 1 for pcm format
		if(header_p->AudioFormat != 1)
		{
			printf("Error. AudioFormat is not in PCM Format\n");
			errorsFound = 1;

		}

		//Error check that the subchunk2 size is correct (needs to be the size of the entire file minus the header data)
		//Same size for data section
		if(header_p->Subchunk2Size != sizeOfFile - sizeof(pcm_wavefile_header_t))
		{
			printf("Error. Subchunk2 size is not correct\n");
			errorsFound = 1;
		}

		if(errorsFound == 1) //More than one error found
		{
			fclose(inputFile);
			return -1;
		}

		//need to use malloc to assign the current subchunk2size for data
		*data_p = (char*)malloc(sizeof(char) * (size_t)header_p->Subchunk2Size);
		if(!data_p) //if malloc request failed
		{
			printf("Error. Malloc request failed..\n");
			fclose(inputFile);
			return -1;
		}


		//read byte by byte for the size of subchunk2. store the number of elements into sizeOfData
		int sizeOfData = fread(*data_p, sizeof(char), header_p->Subchunk2Size, inputFile);



		//Size of data may be equal to or less than the subchunk2size
		//if it reaches the end of the file before it finishes reading
		if(sizeOfData < header_p->Subchunk2Size)
		{
			printf("Error. Size of the data was less than the Subchunk2Size\n");
			fclose(inputFile);
			return -1;
		}

		//Print out all wave file header data once it completes error checking
		//format specified in assignment
		printf("\n");
		printf("ChunkID: %c%c%c%c\n", header_p->ChunkID[0], header_p->ChunkID[1],header_p->ChunkID[2],header_p->ChunkID[3]);
		printf("ChunkSize: %d\n",header_p->ChunkSize);
		printf("Format: %c%c%c%c\n",header_p->Format[0], header_p->Format[1], header_p->Format[2], header_p->Format[3]);

		printf("\n");

		printf("Subchunk1ID: %c%c%c%c\n", header_p->Subchunk1ID[0], header_p->Subchunk1ID[1], header_p->Subchunk1ID[2], header_p->Subchunk1ID[3]);
		printf("Subchunk1Size: %d\n", header_p->Subchunk1Size);
		printf("AudioFormat: %d\n", header_p->AudioFormat);
		printf("NumChannels: %d\n", header_p->NumChannels);
		printf("SampleRate: %d\n", header_p->SampleRate);
		printf("ByteRate: %d\n", header_p->ByteRate);
		printf("BlockAlign: %d\n", header_p->BlockAlign);
		printf("Bits Per Sample: %d\n", header_p->BitsPerSample);

		printf("\n");

		printf("Subchunk2ID: %c%c%c%c\n", header_p->Subchunk2ID[0], header_p->Subchunk2ID[1], header_p->Subchunk2ID[2], header_p->Subchunk2ID[3]);
		printf("Subchunk2Size: %d\n", header_p->Subchunk2Size);
		printf("\n");

		return 0;
}

int write_pcm_wavefile(pcm_wavefile_header_t *header_p, char *data, char *filename)
{

	FILE* outputFile = fopen(filename, "wb");

	//Add header data to file
	fwrite(header_p, sizeof(*header_p),1,outputFile);
	//Add data section
	fwrite(data, sizeof(char),header_p->Subchunk2Size,outputFile);

	fclose(outputFile);

	return 0;
}
