
#include "stdafx.h"
#include <stdlib.h>
#include <windows.h>
#include <math.h>
#include <string.h>
#include <time.h>
#include <string.h>

#define CHARARRAYSIZE 20
#define WIN 101

typedef struct
{
	char name[20];
	int definedNumber;	//Risk factor to stop iterating
	int numTimesDiceThrown = 0; //Actual number of dice throws in favour of one dot rule
	int numberRollsToReachDefinedNumber = 0;
	int successfulTimesToDefinedNumber = 0;
	int accumValue = 0;
	int totalPoints = 0;	//Total number of points over all games
	int count1 = 0;
	int count2 = 0;
	int count3 = 0;
	int count4 = 0;
	int count5 = 0;
	int count6 = 0;
	int averageRoll;
	int hasReached101 = 0;


}player;

void playGutsy();
void diceThrown(player *player);
int playerTurn(player *player, int *numTimes101, int *totalDiceRolls);
void playerPrint(int playerNumber, player *player);
void displayCurrentGameTable(int gameNumber, int numPlayers, player players[]);
void displayFinalGameTable(int currentGameNumber, int gameNumber, int numPlayers, player players[], player winner, int average101);
void statistics(int currentGameNumber, int gameNumber, int numPlayers, player players[], int average101);
int getDiceThrow();
void bubbleSort(int numPlayers, player players[])
{
	for (int i = numPlayers - 1; i > 0; i--)
	{
		int swappedElement = 0;

		for (int j = 0; j < i; j++)
		{
			if (strcmp(players[j].name, (players)[j + 1].name) > 0)
			{
				player temp = (players)[j + 1];
				(players)[j + 1] = (players)[j];
				(players)[j] = temp;
				swappedElement = 1;
			}
		}
		if (swappedElement == 0)
			break;
	}
}






int main()
{
	playGutsy();
    return 0;
}

void playGutsy()
{
	player players[20]; //Instantiate to 20 to start

	//Prompt the user for the number of players and the number of games they will play
	
	int numPlayers;
	int numGames;
	printf("Enter the number of Players\n");
	scanf_s("%d", &numPlayers);

	printf("Enter number of games to play\n");
	scanf_s("%d", &numGames);

	if (numPlayers < 2 || numGames < 1)
	{
		printf("Error. Not enough players/Games to play \n");
		exit(0);
	}

	//Enter names and risk factors for each person
	for (int i = 0; i < numPlayers; i++)
	{
		printf("Player %d\n", i + 1);
		printf("Name: ");
		scanf_s("%s", players[i].name, CHARARRAYSIZE);
		

		//To lowercase
		for (int j = 0; players[i].name[j]; j++) {
			players[i].name[j] = toupper(players[i].name[j]);
		}



		printf("Miminum number to stop in a turn (Risk Factor): ");
		scanf_s("%d", &players[i].definedNumber);
	
	}

	printf("\n\t\t\tGAME STARTING\n");

	int determinePlayerWon[20] = { 0 }; //Initialise to 20, but iterate up to num players
	int winnerFound = 0; //Boolean
	int numTimes101 = 0;
	int totalDiceRolls101 = 0;
	int average101;
	player firstPlace;



	//iterate for each player and their defined number
	for (int j = 0; j < numGames; j++)
	{
		printf("***************************************************************\n");
		printf("Game %d\n", j + 1);
		//Each player
		for (int i = 0; i < numPlayers; i++)
		{
			determinePlayerWon[i] = playerTurn(&players[i], &numTimes101, &totalDiceRolls101);

			playerPrint(i + 1, &players[i]);

			if (determinePlayerWon[i] == 1 && winnerFound == 0) //Player has won
			{
				firstPlace = players[i];
				winnerFound = 1;
			}
		}

		if (winnerFound == 1)
		{
			average101 = totalDiceRolls101 / (1.0 * numTimes101);

			displayFinalGameTable(j+1, numGames, numPlayers, players, firstPlace, average101);
			winnerFound = 2; //Now will never loop inside here again
		}
		else
			displayCurrentGameTable(j + 1, numPlayers, players);

	}



	//After all games and there was no winner
	if (winnerFound == 0) //Didn't find winner
	{
		//Needs to keep playing until a winner is found
		while (winnerFound == 0)
		{
			numGames++;

			printf("***************************************************************\n");
			printf("Game %d\n", numGames);
			//Each player
			for (int i = 0; i < numPlayers; i++)
			{
				determinePlayerWon[i] = playerTurn(&players[i], &numTimes101, &totalDiceRolls101);

				playerPrint(i + 1, &players[i]);

				if (determinePlayerWon[i] == 1 && winnerFound == 0) //Player has won
				{
					firstPlace = players[i];
					winnerFound = 1;
				}
			}

			if (winnerFound == 1)
			{
				average101 = totalDiceRolls101 / (1.0 * numTimes101);

				displayFinalGameTable(numGames, numGames, numPlayers, players, firstPlace, average101);
				winnerFound = 2; //Now will never loop inside here again
			}
			else
				displayCurrentGameTable(numGames, numPlayers, players);
		}
	}


	//Print table at end of program
	//NOW SORTED ALPHABETICALLY
	bubbleSort(numPlayers, players);
		
	average101 = totalDiceRolls101 / (1.0 * numTimes101);

	printf("***************************************************************\n");
	displayFinalGameTable(numGames, numGames, numPlayers, players, firstPlace, average101);


}


/*
	Function throws a dice several times and accumulates values in order to read/pass defined number and returns:
		* the accumulated value or zero
		* number times thrown
		* counters for statistics
*/
void diceThrown(player *player)
{
	int diceNumber;
	(*player).accumValue = 0; //Reset to zero

	//Iterate until one is accumulated value is greater than the defined number or a one is rolled.
	while ((*player).accumValue < (*player).definedNumber)
	{
		diceNumber = getDiceThrow();

		if (diceNumber == 1) //One dot rule. No points added this round
		{
			(*player).accumValue = 0;
			(*player).count1++;
			(*player).numTimesDiceThrown++;
			break;
		}

		else
		{
			switch (diceNumber)
			{
				case 2: (*player).count2++;
						break;	

				case 3: (*player).count3++;
						break;	

				case 4: (*player).count4++;
						break;	

				case 5: (*player).count5++;
						break;	

				case 6: (*player).count6++;
						break;
				default: printf("Invalid Number");
			}

			(*player).accumValue += diceNumber;
			(*player).numTimesDiceThrown++;
			(*player).numberRollsToReachDefinedNumber++;
		}
		//Update total points with the accum value
	}
	if((*player).accumValue != 0)
	{
		(*player).successfulTimesToDefinedNumber++;
		(*player).totalPoints += (*player).accumValue;
	}
}

//Returns 1 if player accumValue is equal to or over 101
int playerTurn(player *player, int *numTimes101, int *totalDiceRolls)
{
	diceThrown(player);
 	if ((*player).totalPoints >= WIN && (*player).hasReached101 == 0)
	{
		(*player).hasReached101 = 1; 
		*numTimes101 += 1;
		*totalDiceRolls += (*player).numTimesDiceThrown;
		return 1;
	}
	else
	{
		return 0;
	}
}

void playerPrint(int playerNumber, player *player)
{
	printf("\tPlayer %d: %s\n", playerNumber, (*player).name);
	printf("\t\tMinimum number to stop in a turn: %d\n", (*player).definedNumber);
	printf("\t\tPoints obtained: %d\n", (*player).accumValue);
	printf("\t\tTotal Points: %d\n", (*player).totalPoints);
}

void displayCurrentGameTable(int gameNumber, int numPlayers, player players[])
{
	//End Current game table
	printf("\nGame %d - Table\n", gameNumber);
	printf("\tPlayer \t Turn Points \t Total Points\n");
	player highestScoringPlayer = players[0]; //Initialise
	for (int i = 0; i < numPlayers; i++)
	{
		if (players[i].totalPoints > highestScoringPlayer.totalPoints)
			highestScoringPlayer = players[i];

		printf("\t%s \t %d \t\t\t %d\n", players[i].name, players[i].accumValue, players[i].totalPoints);
	}
	printf("\t Current Winner: %s\n\n", highestScoringPlayer.name);

}

void displayFinalGameTable(int currentGameNumber, int gameNumber, int numPlayers, player players[], player winner, int average101)
{
	printf("Game %d - Table\n", currentGameNumber);
	printf("\tPlayer \t Turn Points \t Total Points\n");
	for (int i = 0; i < numPlayers; i++)
	{
		printf("\t%s \t %d \t\t\t %d\n", players[i].name, players[i].accumValue, players[i].totalPoints);
	}

	printf("\t\t ***** Winner: %s *****\n", winner.name);

	statistics(currentGameNumber, gameNumber, numPlayers, players, average101);
}

void statistics(int currentGameNumber, int gameNumber, int numPlayers, player players[], int average101)
{
	double totalRolls = 0.0; //Gets around the int division issue
	int totalOnes = 0;
	int totalTwos = 0;
	int totalThrees = 0;
	int totalFours = 0;
	int totalFives = 0;
	int totalSixes = 0;

	for (int i = 0; i < numPlayers; i++)
	{
		totalRolls += players[i].numTimesDiceThrown;
		totalOnes += players[i].count1;
		totalTwos += players[i].count2;
		totalThrees += players[i].count3;
		totalFours += players[i].count4;
		totalFives += players[i].count5;
		totalSixes += players[i].count6;
	}

	double onesProbability = totalOnes / totalRolls;
	double twosProbability = totalTwos / totalRolls;
	double threesProbability = totalThrees / totalRolls;
	double foursProbability = totalFours / totalRolls;
	double fivesProbability = totalFives / totalRolls;
	double sixesProbability = totalSixes / totalRolls;

	printf("Game %d - Statistics\n", currentGameNumber);
	printf("\t\t P(1) = %.4lf\n", onesProbability);
	printf("\t\t P(2) = %.4lf\n", twosProbability);
	printf("\t\t P(3) = %.4lf\n", threesProbability);
	printf("\t\t P(4) = %.4lf\n", foursProbability);
	printf("\t\t P(5) = %.4lf\n", fivesProbability);
	printf("\t\t P(6) = %.4lf\n", sixesProbability);

	//Individual player average
	for (int i = 0; i < numPlayers; i++)
	{	
		players[i].averageRoll = players[i].numberRollsToReachDefinedNumber / (1.0 * players[i].successfulTimesToDefinedNumber);
		printf("\t\t %s - A(%d) = %d\n", players[i].name, players[i].definedNumber, players[i].averageRoll);
	}

	//Average number of times dice is thrown to get 101
	printf("\t\t A(101) = %d\n", average101);

}

int getDiceThrow()
{
	int x;
	time_t seconds;
	time(&seconds);
	srand((unsigned int)seconds);
	Sleep(1000); //To allow program to generate some ACTUAL random numbers using timestamp
	x = rand() % 6 + 1;

	return x;
}



