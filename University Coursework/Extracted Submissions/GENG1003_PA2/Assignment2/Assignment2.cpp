/*
 * Author: J.Haigh	StNo:3256730	Lab: 2pm Wed
 * Task: Programming Assignment 2	Date Commenced: 19 September 2016
 * 
 * The program works with two data files to extract Home Team Player statistics from a basketball game. The program checks against specific action keywords
 * to validate the data entry into the program. The first file is used specifically for the Home Team Shirt Numbers and stores them into an array.
 * The second file reads each line as an action associated with their player number. Validation is important in the second file to be certain that the data is genuine.
 * Values are stored into partial arrays as the second file is read. Once the operation has completed, the program displays a table with each players statistics and team statistics
 * Inputs - File* input2aFile - First .dat file that stores the shirt numbers of the players
 *	      - File* input2bFile - Second .dat file that stores the actions associated with the players
 * Outputs - Individual Player Information (homeTeam[i](Int),points[i](Int),assists[i](Int),shootPercentage[i](Double), freeThrowPercentage[i](Double), rebounds[i](Int), fouls[i](Int))
 *		   - Team Information (teamPoints(Int), teamAssists(Int), teamShootPercentage(Double), teamFreeThrowPercentage(Double), teamRebounds(Int), teamFouls(Int))
 * Constants -  FIRSTFILEINPUTSUCCESS - Checks for only one file input
 *			 -	SECONDFILEMINIMUMINPUTS - Minimum number of inputs must be one (In the case of 'end')
 *			 -	SECONDFILEMAXIMUMINPUTS - Maximum number of inputs must be three (In the case of an 'assist' action)
 *			 -	HOMETEAMSIZE - Starting size of the array for the Home team.
 *			 -	CHARARRAYSIZE - Maximum size of the character array for strings
 *			 -	MAXNUMBERFOULS - Maximum number of fouls that a player can obtain before being removed from the game
 *			 -  ACTIONS (FOR2,FOR3,MISS,ASSIST,FREE,FTMISS,REBOUND,FOUL,END) - Constant keywords for comparing the string inptu
 */

#include "stdafx.h"
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define FIRSTFILEINPUTSUCCESS 1
#define SECONDFILEMINIMUMINPUTS 1
#define SECONDFILEMAXIMUMINPUTS 2
#define HOMETEAMSIZE 15
#define CHARARRAYSIZE 10
#define MAXNUMBERFOULS 5
#define ACTIONFOR2 "for2"
#define ACTIONFOR3 "for3"
#define ACTIONMISS "miss"
#define ACTIONASSIST "assist"
#define ACTIONFREE "free"
#define ACTIONFTMISS "ftmiss"
#define ACTIONREBOUND "rebound"
#define ACTIONFOUL "foul"
#define ACTIONEND "end"

/*
 * int calculatePoints(int freeThrow, int for2Score, int for3Score)
 * This function calculates the total number of points for an individual player. The function adds the number of 
 * for2 and for3 points (multiplying respectively) and free throws and returns the number of points
 * Inputs - int freeThrow (Number of Free Throws by a player), int for2Score (Number of for2 Points), int for3Score (Number of for3 Points)
 * Outputs - int Points (Total number of points)
 */
int calculatePoints(int freeThrow, int for2Score, int for3Score)
{
	int points = freeThrow + (2 * for2Score) + (3 * for3Score);
	return points;
}

/*
 * double calculateShootingPercentage(int for2Score, int for3Score, int miss)
 * This function calculates the shooting percentage for an individual player. The function adds the number of
 * for2 and for3 points (multiplying respectively) and misses. Depending on whether the denominator is 0.0, the function will return 0.0(Due to divide by zero exception errors)
 * If that is not the case, the function will divide and return the shooting in percentage suitable format
 * Inputs - int for2Score (Number of for2 Points), int for3Score (Number of for3 Points), int miss (Number of Misses)
 * Outputs - double shootingPercentage (Percentage of successful shots)
 */
double calculateShootingPercentage(int for2Score, int for3Score, int miss)
{
	double denominator = for2Score + for3Score + miss;
	if (denominator == 0.0)
		return 0.0;
	double shootingPercentage = ((for2Score + for3Score) / denominator) * 100.0;
	return shootingPercentage;
}

/*
 * double calculateTeamShootPercentage(int totalSuccessfulShotsOnGoal, int totalShotsOnGoal)
 * This function calculates the shooting percentage for the team. Depending on whether the totalShotsOnGoal is 0, the function will return 0.0(Due to divide by zero exception errors)
 * If that is not the case, the function will divide and return the Team Shooting in percentage suitable format
 * Inputs - int totalSuccessfulShotsOnGoal (Number of successful shots on goal), int totalShotsOnGoal (Total number of shots on the goal)
 * Outputs - double result (Percentage of successful shots as a team)
 */
double calculateTeamShootPercentage(int totalSuccessfulShotsOnGoal, int totalShotsOnGoal)
{
	if (totalShotsOnGoal == 0)
		return 0.0;
	else
	{
		double result = ((double)totalSuccessfulShotsOnGoal / (double) totalShotsOnGoal) * 100;
		return result;
	}
}

/*
 * double calculatefreeThrowPercentage(int free, int ftmiss)
 * This function calculates the free throw percentage for an individual player. The function adds the number of
 * successful and unsuccessful free throws for the denominator. Depending on whether the denominator is 0.0, the function will return 0.0(Due to divide by zero exception errors)
 * If that is not the case, the function will divide and return the number of sucessful free throws in percentage suitable format
 * Inputs - int free (Number of successful free throws), int ftmiss (Number of unsuccessful free throws)
 * Outputs - double freeThrowPercentage (Percentage of successful free throws)
 */
double calculatefreeThrowPercentage(int free, int ftmiss)
{
	double denominator = free + ftmiss;
	if (denominator == 0.0)
		return 0.0;
	double freeThrowPercentage = (free / denominator) * 100.0;
	return freeThrowPercentage;
}

/*
 * double calculateTeamFreeThrowPercentage(int totalSuccessfulFreeShots, int totalFreeShots)
 * This function calculates the free throw percentage for the team. Depending on whether the totalFreeShots is 0, the function will return 0.0(Due to divide by zero exception errors)
 * If that is not the case, the function will divide and return the Team free throw in percentage suitable format
 * Inputs - int totalSuccessfulFreeShots (Number of successful free throws), int totalFreeShots (Total number of free throw and free throw misses)
 * Outputs - double result (Percentage of successful free thows as a team)
 */
double calculateTeamFreeThrowPercentage(int totalSuccessfulFreeShots, int totalFreeShots)
{
	if (totalFreeShots == 0)
		return 0.0;
	else
	{
		double result = ((double)totalSuccessfulFreeShots / (double)totalFreeShots) * 100;
		return result;
	}
}


/*
 * int checkforTeam(int inputShirtNumber)
 * Function checks whether the inputted shirt number is within the range of 1-25 or 31-55.
 * If the shirt is within the first range, the function checks whether the shirt is a valid home team shirt number. Returns 1 if true (0 if false).
 * If the shirt is within the  second range, the function checks whether the shirt is a valid away team shirt number. Returns 2 if true (0 if false).
 * If the input shirt number doesn't fit either ranges, it returns 0.
 * Inputs: int inputShirtNumber - Integer that is used for the inputted shirt number entered from a line in the program
 * Outputs: integer value defining either a team or a false shirt number.
 */
int checkforTeam(int inputShirtNumber)
{
	if (inputShirtNumber >= 1 && inputShirtNumber <= 25)
	{
		if (inputShirtNumber > 5 && inputShirtNumber < 11)
			return 0;
		else if (inputShirtNumber > 15 && inputShirtNumber < 21)
			return 0;
		else
			/*Number is a valid home shirt number*/
			return 1;
	}
	else if (inputShirtNumber >= 31 && inputShirtNumber <= 55)
	{
		if (inputShirtNumber > 35 && inputShirtNumber < 41)
			return 0;
		else if (inputShirtNumber > 45 && inputShirtNumber < 51)
			return 0;
		else
			/*Number is a valid away shirt number*/
			return 2;
	}
	else
		/*Input shirt doesn't belong to a team*/
		return 0;
}

/*
 * int checkPlayerIsRostered(int inputShirtNumber, int numberOfHomePlayers, int homeTeam[])
 * Function traverses through the homeTeam Array  and checks whether the inputted shirt number 
 * is equal to appropriate element in the array. Returns 1 if found.
 * Inputs: int inputShirtNumber - Integer read from the file line, int numberOfHomePlayers - Actual number of players rostered, int homeTeam[] - homeTeam Array
 * Outputs: Integer either 1 (True) or 0 (False)
 */
int checkPlayerIsRostered(int inputShirtNumber, int numberOfHomePlayers, int homeTeam[])
{
	for (int i = 0; i < numberOfHomePlayers; i++)
	{
		if (inputShirtNumber == homeTeam[i])
			return 1;
	}
	return 0;
}

/*
 * int findPositionOfPlayer(int inputShirtNumber, int numberOfHomePlayers, int homeTeam[])
 * Function searches through the homeTeam Array and checks whether the inputted shirt number matches the element in the array
 * If the shirt element is found, it will return the element number in the array
 * Inputs - int inputShirtNumber - Inputted Shirt number in the program, int numberOfHomePlayers - Actual count of the number of players entered into the program, int homeTeam[] - Home team array used to search through
 * Outputs - Either the element number of the valid shirt or 0 if not found in the array
 */
int findPositionOfPlayer(int inputShirtNumber, int numberOfHomePlayers, int homeTeam[])
{
	for (int i = 0; i < numberOfHomePlayers; i++)
	{
		if (inputShirtNumber == homeTeam[i])
			return i;
	}
	return 0;
}

/*
 * int stringComparisonCheck(char firstInputAction[CHARARRAYSIZE], char secondInputAction[CHARARRAYSIZE])
 * Function compares the string in the firstInputAction parameter and confirms whether the string is equal or not
 * If the string is equal, it will return a boolean value. If not equal, it will report the action and ignore it from the program
 * Inputs - char firstInputAction[CHARARRAYSIZE] (First action recorded), char secondInputAction[CHARARRAYSIZE] (Second action recorded)
 */
int stringComparisonCheck(char firstInputAction[CHARARRAYSIZE], int inputShirtNumber)
{
	if (strcmp(firstInputAction, ACTIONEND) == 0)
		return 1;
	else if (strcmp(firstInputAction, ACTIONFOR2) == 0)
		return 1;
	else if (strcmp(firstInputAction, ACTIONFOR3) == 0)
		return 1;
	else if (strcmp(firstInputAction, ACTIONMISS) == 0)
		return 1;
	else if (strcmp(firstInputAction, ACTIONASSIST) == 0)
		/*At the moment we are only checking if the word assist is valid*/
		return 1;
	else if (strcmp(firstInputAction, ACTIONFREE) == 0)
		return 1;
	else if (strcmp(firstInputAction, ACTIONFTMISS) == 0)
		return 1;
	else if (strcmp(firstInputAction, ACTIONREBOUND) == 0)
		return 1;
	else if (strcmp(firstInputAction, ACTIONFOUL) == 0)
		return 1;
	else
		return 0;
}


int main()
{
	FILE *input2aFile;					/*Home team numbers file to be entered into an array*/
	int input2aStatus;					/*Input status to determine whether the program is able to continue*/
	int numberOfInputs2a;				/*Number of inputs gathered from a line in the file*/
	int homeTeam[HOMETEAMSIZE];			/*Array storing the number of players on the home team*/
	int inputShirtNumber;				/*Variable to check whether the integer is valid for the array*/
	int numberOfHomePlayers = 0;		/*Actual Number of players from the input file*/

	FILE *input2bFile;					/*Data file to be used in the program*/
	int input2bStatus;					/*Input status to determine whether the program is able to continue*/
	int numberOfInputs2b;				/*Number of inputs gathered from a line in the file*/

	int for2Score[HOMETEAMSIZE] = {0};	/*Array used to store the number of for2Scores for individiual Home Team players (Default at zero) - Can be used partially later*/
	int for3Score[HOMETEAMSIZE] = {0};	/*Array used to store the number of for3Scores for individiual Home Team players (Default at zero) - Can be used partially later*/
	int points[HOMETEAMSIZE] = {0};		/*Array used to store the number of points for individiual Home Team players (Default at zero) - Can be used partially later*/
	int assists[HOMETEAMSIZE] = {0};	/*Array used to store the number of assists for individiual Home Team players (Default at zero) - Can be used partially later*/

	int misses[HOMETEAMSIZE] = {0};					/*Array used to store the number of misses for individiual Home Team players (Default at zero) - Can be used partially later*/
	double shootPercentage[HOMETEAMSIZE] = {0.0};	/*Array used to store the shooting percentage for individiual Home Team players (Default at zero) - Can be used partially later*/

	int freeThrowScore[HOMETEAMSIZE] = {0};				/*Array used to store the number of successful free throws for individiual Home Team players (Default at zero) - Can be used partially later*/
	int freeThrowMissScore[HOMETEAMSIZE] = {0};			/*Array used to store the number of unsuccesful for individiual Home Team players (Default at zero) - Can be used partially later*/
	double freeThrowPercentage[HOMETEAMSIZE] = {0.0};	/*Array used to store the free throw percentage for individiual Home Team players (Default at zero) - Can be used partially later*/

	int rebounds[HOMETEAMSIZE] = {0};		/*Array used to store the number of rebounds for individiual Home Team players (Default at zero) - Can be used partially later*/
	int fouls[HOMETEAMSIZE] = {0};			/*Array used to store the number of fouls for individiual Home Team players (Default at zero) - Can be used partially later*/

	int teamPoints = 0;						/*Integer used to store the sum of the team's points scored*/
	int teamAssists = 0;					/*Integer used to store the sum of the team's assists*/
	double teamShootPercentage = 0.0;			/*Double used to store the sum of the teams shooting percentage.*/
	double teamFreeThrowPercentage = 0.0;		/*Double used to store the sum of the teams free throw percentage.*/
	int teamRebounds = 0;					/*Integer used to store the sum of the team's rebounds*/
	int teamFouls = 0;						/*Integer used to store the sum of the team's fouls*/

	int totalShotsOnGoal = 0;				/*Integer storing the total number of shots on the goal*/
	int totalSuccessfulShotsOnGoal = 0;		/*Integer storing the total number of successful shots on the goal*/
	int totalFreeShots = 0;					/*Integer storing the total number of free shots on the goal*/
	int totalSuccessfulFreeShots = 0;		/*Integer storing the number of successful free shots*/

	int playerPosition;							/*Integer storing either the player position in the homeTeam Array or 0 if not found*/
	int boolCheckRosteredPlayer;				/*Integer storing a boolean check whether the player is rostered to play or not*/
	int shirtCheck;								/*Integer storing 1 for the homeTeam, 2 for the awayTeam or 0 if not found*/
	int boolStringComparison;					/*Integer to check whether two strings are identical*/
	char inputAction[CHARARRAYSIZE];			/*Char Array used to store the first action.*/
	char followingInputAction[CHARARRAYSIZE];	/*Char Array used to store the following action in the event of an assist action*/
	int followingInputShirtNumber;				/*Integer storing the following shirt number in the event of an assist action*/


	/*ENTERING THE HOME TEAM SHIRT NUMBERS*/
	input2aStatus = fopen_s(&input2aFile, "input2a.dat", "r");
	//input2aStatus = fopen_s(&inputFile, "U:\\GENG1003\\input2a.dat", "r");

	/*If the input status does not equal zero, this means there is a problem with opening the file*/
	if (input2aStatus != 0)
	{
		printf("ERROR. Problem opening file");
		return -1;
	}

	/*If the input status is zero, continue with program*/
	else
	{
		numberOfInputs2a = fscanf_s(input2aFile, "%d", &inputShirtNumber);

		/*Check if file does not contain inputs*/
		if (numberOfInputs2a == -1)
		{
			printf("ERROR. There is NO data to work with");
		}

		/*Number of inputs match the number of input variables required*/
		while (numberOfInputs2a == FIRSTFILEINPUTSUCCESS)
		{
			for (int i = 0; i < HOMETEAMSIZE; i++)
			{
				shirtCheck = checkforTeam(inputShirtNumber);
				if (shirtCheck == 1)
				{
					/*Valid Home Shirt*/
					/*If file reaches end before 15 numbers*/
					if (inputShirtNumber == homeTeam[i - 1])
						break;

					/*If number hasn't already been inputted*/
					else if (inputShirtNumber != homeTeam[i])
					{
						homeTeam[i] = inputShirtNumber;
						numberOfHomePlayers++;
					}

					/*Shouldn't be able to access this area*/
					else
					{
						/*Do nothing*/
					}
				}

				else
				{
					/*Invalid Input*/
					printf("ERROR. NUMBER IS NOT RECOGNISED AS A HOME TEAM SHIRT. CLOSING PROGRAM.");
					exit(0);
				}

				numberOfInputs2a = fscanf_s(input2aFile, "%d", &inputShirtNumber);	/*Prime the next line*/
			}
		}
		fclose(input2aFile); /*Close the file*/
	}






	/*INPUTTING THE GAME INFORMATION*/
	input2bStatus = fopen_s(&input2bFile, "input2b.dat", "r");
	//input2bStatus = fopen_s(&input2bFile, "U:\\GENG1003\\input2b.dat", "r");

	/*If the input status does not equal zero, this means there is a problem with opening the file*/
	if (input2bStatus != 0)
	{
		printf("ERROR. Problem opening file");
		return -1;
	}

	/*If the input status is zero, continue with program*/
	else
	{
		numberOfInputs2b = fscanf_s(input2bFile, "%s %d", inputAction, CHARARRAYSIZE, &inputShirtNumber);

		/*Check if file does not contain inputs*/
		if (numberOfInputs2b == -1)
		{
			printf("ERROR. There is NO data to work with");
		}

		/*Number of inputs match the number of input variables required*/
		while (numberOfInputs2b >= SECONDFILEMINIMUMINPUTS && numberOfInputs2b <= SECONDFILEMAXIMUMINPUTS)
		{
			/*Checking for valid action input from file*/
			/*If valid, move to check*/
			boolStringComparison = stringComparisonCheck(inputAction, inputShirtNumber);

			if (boolStringComparison == 1)
			{
				/*Check if the inputted player shirt is from the away team*/
				shirtCheck = checkforTeam(inputShirtNumber);
				if (shirtCheck == 2)
				{
					/*Player is on the away team - Ignored from program*/
					/*Prime next line*/
					numberOfInputs2b = fscanf_s(input2bFile, "%s %d", inputAction, CHARARRAYSIZE, &inputShirtNumber);
					continue;
				}
				else
				{
					/*Check for valid home player*/
					for (int i = 0; i < numberOfHomePlayers; i++)
					{
						boolCheckRosteredPlayer = checkPlayerIsRostered(inputShirtNumber, numberOfHomePlayers, homeTeam);
						if (boolCheckRosteredPlayer == 1)
						{
							/*Check if the player is rostered*/
							/*Check for invalid player shirts*/
							shirtCheck = checkforTeam(inputShirtNumber);
							/*If false*/
							if (shirtCheck == 0)
							{
								printf("%s %d - Error: Invalid Shirt Number\n", inputAction, inputShirtNumber);
								break;
							}


							if (inputShirtNumber == homeTeam[i])
							{
								/*Shirt number is valid*/
								/*The if statement will check if they are also rostered.*/
								/*Move on to record action.*/
								if (strcmp(inputAction, ACTIONEND) == 0)
								{
									//printf("%s \n", firstInputAction);
									fclose(input2bFile); /*Close the file*/
									break;
								}

								else if (fouls[i] == MAXNUMBERFOULS)
								{
									/*Reported but ignored*/
									printf("%s %d - Error: Player is out of the game (Fouls 5)\n", inputAction, inputShirtNumber);
									break;
								}

								else if (strcmp(inputAction, ACTIONFOR2) == 0)
								{
									/*Store value*/
									for2Score[i]++;
									/*Report action*/
									//printf("%s %d\n", firstInputAction, inputShirtNumber);
									break;
								}

								else if (strcmp(inputAction, ACTIONFOR3) == 0)
								{
									/*Store value*/
									for3Score[i]++;
									/*Report action*/
									//printf("%s %d\n", firstInputAction, inputShirtNumber);
									break;
								}

								else if (strcmp(inputAction, ACTIONMISS) == 0)
								{
									/*Store value*/
									misses[i]++;
									/*Report action*/
									//printf("%s %d\n", firstInputAction, inputShirtNumber);
									break;

								}

								else if (strcmp(inputAction, ACTIONASSIST) == 0)
								{
									/*The first keyword is assist, which is valid.*/
									/*Need to check for the next keywords*/
									/*Check next line for valid for2 or for3 score*/
									/*Use temporary variables*/
									fscanf_s(input2bFile, "%s %d", followingInputAction, CHARARRAYSIZE, &followingInputShirtNumber);
									/*Iterate over the home team array*/
									if (followingInputShirtNumber != inputShirtNumber)
									{
										/*Check which team shirt belongs to*/
										shirtCheck = checkforTeam(followingInputShirtNumber);
										if (shirtCheck == 1)
										{
											//Home Team
											//Check if player 1 is on the roster
											boolCheckRosteredPlayer = checkPlayerIsRostered(inputShirtNumber, numberOfHomePlayers, homeTeam);
											if (boolCheckRosteredPlayer == 1)
											{
												//Check both Players are rostered
												boolCheckRosteredPlayer = checkPlayerIsRostered(followingInputShirtNumber, numberOfHomePlayers, homeTeam);
												if (boolCheckRosteredPlayer == 1)
												{
													playerPosition = findPositionOfPlayer(followingInputShirtNumber, numberOfHomePlayers, homeTeam);
													/*Check whether the player who scored is not fouled off*/
													if (fouls[playerPosition] == MAXNUMBERFOULS)
													{
														printf("%s %d - Error: Invalid Assist (Player scoring is fouled off)\n", inputAction, inputShirtNumber);
														//Since we have already read the next line, we need to feed it back to the top of the loop
														strcpy_s(inputAction, followingInputAction);
														inputShirtNumber = followingInputShirtNumber;
														//Reset the loop to start from the beginning
														i = -1;
														continue;
													}
													/*Check for action*/
													else if (strcmp(followingInputAction, ACTIONFOR2) == 0)
													{
														/*Check whether the next shirt is valid*/
														/*Valid for2 assist*/
														assists[i]++;
														//printf("%s %d\n", firstInputAction, inputShirtNumber);

														/*Valid next player and action*/
														/*Assign the temporary variables to be used in the next loop*/
														strcpy_s(inputAction, followingInputAction);
														inputShirtNumber = followingInputShirtNumber;
														/*Resetting the for loop i to loop from the start again*/
														i = -1;
														continue;
													}
													else if (strcmp(followingInputAction, ACTIONFOR3) == 0)
													{
														/*Valid for3 assist*/
														assists[i]++;
														//printf("%s %d\n", firstInputAction, inputShirtNumber);

														/*Valid next player and action*/
														/*Assign the temporary variables to be used in the next loop*/
														strcpy_s(inputAction, followingInputAction);
														inputShirtNumber = followingInputShirtNumber;
														/*Resetting the for loop i to loop from the start again*/
														i = -1;
														continue;
													}
													else
													{
														/*Invalid assist - Ignored*/
														printf("%s %d - Error: Invalid Assist (Not followed by a valid action)\n", inputAction, inputShirtNumber);
														/*Check whether the next input is a valid shirt*/
														/*Assign the temporary variables to be used in the next loop*/
														strcpy_s(inputAction, followingInputAction);
														inputShirtNumber = followingInputShirtNumber;
														/*Resetting the for loop i to loop from the start again*/
														i = -1;
														continue;
													}
												}
												else
												{
													//Second player is not rostered
													printf("%s %d - Error: Invalid Assist (Second player is not rostered on.)\n", inputAction, inputShirtNumber);
													//Assign variables to be dealt with
													strcpy_s(inputAction, followingInputAction);
													inputShirtNumber = followingInputShirtNumber;
													/*Resetting the for loop i to loop from the start again*/
													i = -1;
													continue;
												}
											}
											else
											{
												//Invalid assist
												printf("%s %d - Error: Invalid Assist (Player is not rostered)\n", inputAction, inputShirtNumber);
												/*Work with the next line to determine whether it is another action*/
												strcpy_s(inputAction, followingInputAction);
												inputShirtNumber = followingInputShirtNumber;
												i = -1;
												continue;
											}
										}
										else if (shirtCheck == 2)
										{
											//Away Team - Ignored From program
											printf("%s %d - Error: Invalid Assist (Other Player is on the other team)\n", inputAction, inputShirtNumber);
											/*Work with the next line to determine whether it is another action*/
											strcpy_s(inputAction, followingInputAction);
											inputShirtNumber = followingInputShirtNumber;
											i = -1;
											continue;
										}
										else
										{
											//Invalid Assist
											printf("%s %d - Error: Invalid Assist (Player is not a valid shirt number)\n", inputAction, inputShirtNumber);
											/*Work with the next line to determine whether it is another action*/
											strcpy_s(inputAction, followingInputAction);
											inputShirtNumber = followingInputShirtNumber;
											i = -1;
											continue;
										}

									}
									else
									{
										//Assist is wrong
										printf("%s %d - Error: Invalid Assist (Player cannot assist and score)\n", inputAction, inputShirtNumber);
										/*Work with the next line to determine whether it is another action*/
										strcpy_s(inputAction, followingInputAction);
										inputShirtNumber = followingInputShirtNumber;
										i = -1;
										continue;
									}
								}

								else if (strcmp(inputAction, ACTIONFREE) == 0)
								{
									/*Store value*/
									freeThrowScore[i]++;
									/*Report action*/
									//printf("%s %d\n", firstInputAction, inputShirtNumber);
									break;
								}

								else if (strcmp(inputAction, ACTIONFTMISS) == 0)
								{
									/*Store value*/
									freeThrowMissScore[i]++;
									/*Report action*/
									//printf("%s %d\n", firstInputAction, inputShirtNumber);
									break;
								}

								else if (strcmp(inputAction, ACTIONREBOUND) == 0)
								{
									/*Store value*/
									rebounds[i]++;
									/*Report action*/
									//printf("%s %d\n", firstInputAction, inputShirtNumber);
									break;
								}

								else if (strcmp(inputAction, ACTIONFOUL) == 0)
								{
									/*Store value*/
									fouls[i]++;
									/*Report action*/
									//printf("%s %d\n", firstInputAction, inputShirtNumber);
									if (fouls[i] == MAXNUMBERFOULS)
									{
										/*Player is out of the game*/
										printf("Number %d is out of the game!\n", inputShirtNumber);
										break;
									}
								}

								else
								{
									printf("%s %d - Error: Player is not rostered\n", inputAction, inputShirtNumber);
									break;
								}
							}
							else
							{
								//Do nothing
							}
						}
						else
						{
							/*Player is not rostered.*/
							if (strcmp(inputAction, ACTIONEND) == 0)
							{
								//printf("%s \n", firstInputAction);
								fclose(input2bFile); /*Close the file*/
								break;
							}
							else if (shirtCheck == 2)
							{
								/*Check whether the action is valid*/
								boolStringComparison = stringComparisonCheck(inputAction, inputShirtNumber);
								/*If valid string*/
								if (boolStringComparison == 1)
									break;
								else
								{
									printf("%s %d - Error: Invalid Action.\n", inputAction, inputShirtNumber);
									break;
								}
							}
							else if (checkforTeam(inputShirtNumber) == 1)
							{
								printf("%s %d - Error: Invalid Action. Player is not rostered\n", inputAction, inputShirtNumber);
								break;
							}
							else
							{
								printf("%s %d - Error: Player is not a valid shirt number.\n", inputAction, inputShirtNumber);
								break;
							}
							
						}
							
					}
				}
			}
			else
			{
				/*Report with player and ignore (Regardless of player number)*/
				printf("%s %d - Error: Invalid Action\n", inputAction, inputShirtNumber);
			}
			/*Clear Temporary Variables*/
			followingInputShirtNumber = 0;


			/*Prime the next line*/
			numberOfInputs2b = fscanf_s(input2bFile, "%s %d", inputAction, CHARARRAYSIZE, &inputShirtNumber);
		}
		
		

		/*Calculate Results*/
		for (int i = 0; i < numberOfHomePlayers; i++)
		{
			/*Individual Results*/
			points[i] = calculatePoints(freeThrowScore[i], for2Score[i], for3Score[i]);
			shootPercentage[i] = calculateShootingPercentage(for2Score[i], for3Score[i], misses[i]);
			freeThrowPercentage[i] = calculatefreeThrowPercentage(freeThrowScore[i], freeThrowMissScore[i]);
			

			/*Team Results*/
			teamPoints += points[i];
			teamAssists += assists[i];

			totalShotsOnGoal += (for2Score[i] + for3Score[i] + misses[i]);
			totalSuccessfulShotsOnGoal += (for2Score[i] + for3Score[i]);
			
			totalFreeShots += (freeThrowScore[i] + freeThrowMissScore[i]);
			totalSuccessfulFreeShots += freeThrowScore[i];

			teamRebounds += rebounds[i];
			teamFouls += fouls[i];
		}
		teamShootPercentage = calculateTeamShootPercentage(totalSuccessfulShotsOnGoal, totalShotsOnGoal);
		teamFreeThrowPercentage = calculateTeamFreeThrowPercentage(totalSuccessfulFreeShots, totalFreeShots);

		/*WRITE THE OUTPUT*/
		printf("\n\nGame Statistics\n");
		printf("Shirt No \t Points \t Assists \t %%Shtg \t\t %%Free \t\t Reb \t\t Fouls\n");
		printf("==============================================================================================================\n");
		for (int i = 0; i < numberOfHomePlayers; i++)
		{
			printf("%d \t\t %d \t\t %d \t\t %.1lf \t\t %.1lf \t\t %d \t\t %d\n",homeTeam[i],points[i],assists[i],shootPercentage[i], freeThrowPercentage[i], rebounds[i], fouls[i]);
		}
		printf("==============================================================================================================\n");
		printf("Team: \t\t %d \t\t %d \t\t %.1lf \t\t %.1lf \t\t %d \t\t %d\n", teamPoints, teamAssists, teamShootPercentage, teamFreeThrowPercentage, teamRebounds, teamFouls);
		printf("");
	}
	return 0;
}

