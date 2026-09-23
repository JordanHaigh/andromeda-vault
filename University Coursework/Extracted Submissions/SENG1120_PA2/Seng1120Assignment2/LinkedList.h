//LinkedList.h
#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include "Node.h"
#include <string>
#include <iostream>
#include <sstream>
using namespace std;

/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * LinkedList.h
 */

namespace SENG1120ASSIGN
{
	template<class valueType>
	class LinkedList
    {
		/*
		* Overloaded cout << operator
		* Returns the face of the current card
		*/
		friend std::ostream& operator << (std::ostream& output, const LinkedList<valueType>& target);

        public:
			/*CONSTRUCTORS*/
			/*
			 * LinkedList Constructor
			 */
			LinkedList();
            
			/*
			 * LinkedList Destructor
			 */
			~LinkedList();
            
			/*OTHER METHODS*/
			/*
			 * bool isEmpty()
			 * Precondition: None
			 * Postcondition: 1 or 0 value if list is empty
			 * @return - Boolean value if empty or not (True || False)
			 */
			bool isEmpty();

			/*
			* void addAt(std::size_t index, valueType* data)
			* Precondition: 'index' must be a valid index in the Linked List
			* Postcondition: Adds new node at the specified index with the inputted data
			* @param index - Defines the integer value of the index for insertion
			* @param data - Defines valueType* of data to be entered
			*/
            void addAt(std::size_t index, valueType* data);
           
			/*
			 * valueType* removeAt(std::size_t index)
			 * Precondition: 'index' must be a valid index in the Linked List
			 * Postcondition: Removes node from that specific index and fixes Node linkage
			 * @param index - Defines the integer value of the index for insertion
			 * @return - Returns data of the node that was removed
			 */
			valueType* removeAt(std::size_t index);
            
			/*
			 * bool removeFirst(valueType* data)
			 * Precondition: 'data' must be identical to the data found in the Linked List
			 * Postcondition: Removes the first node with the specific data entry and fixes Node linkage
			 * @param data - Defines the valueType* of data to be entered
			 * @return - Returns 1 or 0 (True || False) whether the Node was removed or not
			 */
			bool removeFirst(valueType* data);

			/*
			 * valueType* get(std::size_t index)			 
			 * Precondition: 'index' must be a valid index in the Linked List
			 * Postcondition: Gets the node data at that specific index
			 * @param index - Defines the integer value of the index for insertion
			 * @return - Returns the data of the specific Node if found
			 */
            valueType* get(std::size_t index);

			/*
			 * void set(std::size_t index, valueType* data)
			 * Precondition: 'index' must be a valid index in the Linked List
			 * Postcondition: Edits the data at the specified index with new data
			 * @param index - Defines the integer value of the index for insertion
			 * @param data - Defines the valueType* of data to be entered
			 */
            void set(std::size_t index, valueType* data);

			/*
			 * std::size_t indexOf(valueType* data)
			 * Precondition: The inputted 'data' must be equal to the valueType data in the LinkedList
			 * Postcondition: Returns the index of that specified data entry
			 * @param data - Defines the valueType* of data to be entered
			 * @return - Returns the index of the Node
			 */
            int indexOf(valueType* data);

			/*
			 * void addToHead(valueType* data)
			 * Precondition: None
			 * Postcondition: Adds new node after the Head Node
			 * @param data - Defines the valueType* of data to be entered
			 */
            void addToHead(valueType* data);

			/*
			 * void addToTail(valueType* data)
			 * Precondition: None
			 * Postcondition: Adds new node before the tail Node
			 * @param data - Defines the valueType* of data to be entered
			 */
            void addToTail(valueType* data);

			/*
			 * valueType* removeFromHead()
			 * Precondition: Node must exist before Tail and after Head
			 * Postcondition: Removes the first Node after Head
			 * @return - Returns the valueType* data that was removed
			 */
            valueType* removeFromHead();

			/*
			 * valueType* removeFromTail()
			 * Precondition: Node must exist before Tail and after Head
			 * Postcondition: Removes the first Node before Tail
			 * @return - Returns the valueType* data that was removed
			 */
            valueType* removeFromTail();

			/*
			 * std::size_t size()
			 * Precondition: None
			 * Postcondition: Returns size of the Linked List
			 * @return - Size of the Linked List
			 */
            std::size_t size();

			/*
			 * void clear()
			 * Precondition: List must not be empty
			 * Postcondition: Removes all Nodes from the Linked List
			 */
            void clear();

			/*
			* std::string toString() const
			* Precondition: Node must contain data and be within the linked list range
			* Postcondition: Returns specific Node data as a string
			* @return - string containing Node data
			*/
			std::string toString() const;


            /*ITERATOR METHODS*/
			/*
			 * void iteratorReset()
			 * Precondition: None
			 * Postcondition: Resets the Node* iteratorCursor back to head->next
			 */
			void iteratorReset();
            
			/*
			 * void iteratorNext()
			 * Precondition: None
			 * Postcondition: Sets the Node* iteratorCursor to be the next node in the sequence
			 */
			void iteratorNext();

			/*
			* void iteratorData()
			* Precondition: None
			* Postcondition: Returns the data in the current iteratorCursor
			* @return - valueType data in the current iteratorCursor
			*/
            valueType* iteratorData();

			/*
			* bool iteratorHasNext()
			* Precondition: None
			* Postcondition: Checks whether there is a node that is not null next in the sequence
			* @return - 0 or 1 (True || False) whether there is another node in the sequence
			*/
            bool iteratorHasNext();

        private:
            Node<valueType>* head;
            Node<valueType>* tail;
            std::size_t listLength;

            //Iterator data
            Node<valueType>* iteratorCursor;

    };

	template<class valueType>
	LinkedList<valueType>::LinkedList()
	{
		//I have set up my linked list differently to the lecture slides
		//My head and tail pointers will be sentinels - they will always be NULL
		//All data will go between the head and the tail node

		//Initialise head and tail
		head = new Node<valueType>();
		tail = new Node<valueType>();

		//Link head and tail together
		head->setNext(tail);
		tail->setPrevious(head);

		//Initialise counter - Makes it easier to find size of linked list without traversing
		listLength = 0;
	}

	template<class valueType>
	LinkedList<valueType>::~LinkedList()
	{
		//Call clear to remove the innards
		clear();

		//No more data inside the head and the tail. Set the linkages for the head and tail nodes
		head->setNext(NULL);
		tail->setPrevious(NULL);

		//Delete head and tail to remove the sentinel nodes
		delete head;
		delete tail;
	}

	template<class valueType>
	bool LinkedList<valueType>::isEmpty()
	{
		//If list length is less than 1 (Nothing between two sentinels), will return false
		return listLength<1;
	}


	template<class valueType>
	void LinkedList<valueType>::addAt(std::size_t index, valueType* data)
	{
		//Assuming that user is inputting a new node in between two existing nodes
		//Adds node at before the specified index so that the new node becomes that index

		//Traverse to the index
		//Uses a counter variable to check whether the cursor is at the index or not
		//Double check first that the index entered by the user is within range
		std::size_t counter = 0;
		if (index < 0 || index >= size())
		{
			throw "ERROR. Index is out of range";
		}
		//If in range, continue to find the index
		for (Node<valueType>* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
		{
			//If the counter has matched the index
			if (counter == index)
			{
				//Create new node
				Node<valueType>* previousNode = cursor->getPrevious();
				//New Node
				Node<valueType>* newNode = new Node<valueType>(data, cursor, previousNode);
				//Update links
				cursor->setPrevious(newNode);
				previousNode->setNext(newNode);
				listLength++;
			}
			//If not, increment and continue to loop
			else
				counter++;
		}
	}

	template<class valueType>
	valueType* LinkedList<valueType>::removeAt(std::size_t index)
	{
		//Uses a counter variable to check whether the cursor is at the index or not
		//Double check first that the index entered by the user is within range
		std::size_t counter = 0;
		if (index <0 || index >= size())
		{
			throw "ERROR: Index is out of range.";
		}

		//If in range, continue to find the index
		for (Node<valueType>* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
		{
			//If counter matches with the index
			if (counter == index)
			{
				//Found index of Node
				//Create a previousNode and nextNode to fix the links when removing the specified Node
				Node<valueType>* previousNode = cursor->getPrevious();
				Node<valueType>* nextNode = cursor->getNext();

				previousNode->setNext(nextNode);
				nextNode->setPrevious(previousNode);
				valueType* cursorData = cursor->getData();

				//Remove the links of the current cursor to avoid a stack overflow
				cursor->setData(NULL);
				cursor->setNext(NULL);
				cursor->setPrevious(NULL);
				//Delete the current cursor
				delete cursor;

				// Decrement size
				listLength--;

				return cursorData;
			}
			//If not, increment the counter and continue to loop
			else
			{
				counter++;
			}
		}

		//Shouldn't be able to reach this section due to the boundaries check
		return NULL;
	}

	template<class valueType>
	bool LinkedList<valueType>::removeFirst(valueType* data)
	{

		// Removes first instance of data
		for (Node<valueType>* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
		{
			//Iterate until found data

			//if(data == NULL ? cursor->getData() == NULL : cursor->getData().equals(data))
			//This ternary could have worked, though because the typedef is not a pointer, this isn't achievable

			//TODO replace with object.equals at a later date
			//Uses compare method to compare the two data types (Similar to .equals in Java)
			if (cursor->getData() == data)
			{
				//If strings are equals, will result to 0 (Boolean)
				//Create nextNode and previousNode and update linkages
				Node<valueType>* nextNode = cursor->getNext();
				Node<valueType>* previousNode = cursor->getPrevious();

				previousNode->setNext(nextNode);
				nextNode->setPrevious(previousNode);

				//Remove the pointers of the cursor to avoid a stack overflow
				cursor->setNext(NULL);
				cursor->setPrevious(NULL);
				//Remove cursor from the linked list
				delete cursor;

				// Decrement size
				listLength--;
				return true;
			}
		}
		return false;
	}
	template<class valueType>
	valueType* LinkedList<valueType>::get(std::size_t index)
	{
		//Uses a counter variable to check whether the cursor is at the index or not
		//Double check first that the index entered by the user is within range
		std::size_t counter = 0;
		if (index < 0 || index >= size())
		{
			throw "ERROR. Index is out of range";
		}
		//If in range, start to loop until found correct index
		for (Node<valueType>* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
		{
			//If counter is equal to the index
			if (counter == index)
			{
				return cursor->getData();
			}
			//If not, increment and continue to loop
			else
				counter++;
		}
		//should never be returned due to bounds check
		return NULL;
	}

	template<class valueType>
	void LinkedList<valueType>::set(std::size_t index, valueType* data)
	{
		//Uses a counter variable to check whether the cursor is at the index or not
		//Double check first that the index entered by the user is within range
		std::size_t counter = 0;
		if (index < 0 || index >= size())
		{
			throw "ERROR. Index in out of range";
		}
		//If in range, start to loop until found correct index
		for (Node<valueType>* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
		{
			//If found correct index
			if (counter == index)
			{
				//Set new cursor data
				cursor->setData(data);
				return;
			}
			//If not found, increment and continue to loop
			else
				counter++;
		}
	}
	template<class valueType>
	int LinkedList<valueType>::indexOf(valueType* data)
	{
		//Start counter at position 0
		std::size_t counter = 0;
		//Start loop of the linked list
		for (Node<valueType>* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
		{
			//Data has to be exactly the same (including capitals)

			//If data matches with the argument data
			if (cursor->getData() == data)
			{
				return (int)counter;
			}
			//Increment counter until found
			else
				counter++;
		}
		//Return -1 if not found in the linked list
		return -1;
	}
	template<class valueType>
	void LinkedList<valueType>::addToHead(valueType* data)
	{
		//Create newNode that will be the next node after head
		Node<valueType>* nextNode = head->getNext();
		//Use Node constructor to initialise data and links
		Node<valueType>* newNode = new Node<valueType>(data, nextNode, head);

		//Update links
		head->setNext(newNode);
		nextNode->setPrevious(newNode);
		//Increment list length
		listLength++;
	}
	template<class valueType>
	void LinkedList<valueType>::addToTail(valueType* data)
	{
		//If the linked list is empty, the previous node will be head
		//If the linked list is not empty, the previous node will be tail->getPrevious()
		Node<valueType>* previousNode = tail->getPrevious();

		//Create new node with data and links
		Node<valueType>* newNode = new Node<valueType>(data, tail, previousNode);

		//Update links
		tail->setPrevious(newNode);
		previousNode->setNext(newNode);
		//Increment list length
		listLength++;
	}
	template<class valueType>
	valueType* LinkedList<valueType>::removeFromHead()
	{
		//If the linked list is empty (No nodes between head and tail), just return
		if (listLength == 0)
			throw "Error. List is empty";
		//If not, continue with the removal
		else
		{
			//Create two nodes for the removal: deleteNode (Node that will be removed)
			//nextNode (The next node after the node to be deleted)
			Node<valueType>* deleteNode = head->getNext();
			Node<valueType>* nextNode = deleteNode->getNext();

			//Update links
			head->setNext(nextNode);
			nextNode->setPrevious(head);

			//Remove the links from deleteNode to avoid stack overflow
			valueType* deleteNodeData = deleteNode->getData();
			deleteNode->setData(NULL);
			deleteNode->setNext(NULL);
			deleteNode->setPrevious(NULL);
			// Delete node
			delete deleteNode;
			// Decrement size
			listLength--;
			return deleteNodeData;
		}
	}
	template<class valueType>
	valueType* LinkedList<valueType>::removeFromTail()
	{
		//If the linked list is empty (No nodes between head and tail), just return
		if (listLength == 0)
			return;
		//If not empty, continue with the method
		else
		{
			//Create two nodes for the removal: deleteNode (Node that will be removed)
			//previousNode (The previous node before the node to be deleted)
			Node<valueType>* deleteNode = tail->getPrevious();
			Node<valueType>* previousNode = deleteNode->getPrevious();

			//Update links
			tail->setPrevious(previousNode);
			previousNode->setNext(tail);

			//Remove the links from the deleted node to avoid stack overflow
			valueType* deleteNodeData = deleteNode->getData();
			deleteNode->setData(NULL);
			deleteNode->setNext(NULL);
			deleteNode->setPrevious(NULL);
			
			// Delete node
			delete deleteNode;
			
			// Decrement size
			listLength--;
			return deleteNodeData;
		}
	}
	template<class valueType>
	std::size_t LinkedList<valueType>::size()
	{
		//Return the current list length
		return listLength;
	}
	template<class valueType>
	void LinkedList<valueType>::clear()
	{
		//Clears the innards of the linked list without removing the head and tail sentinel nodes
		while (!isEmpty())
			removeFromHead();
	}
	template<class valueType>
	std::string LinkedList<valueType>::toString() const
	{
		string output;
		//Overloaded cout operator to print the node data in the linked list
		for (Node<valueType>* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
		{
			//iterate over all nodes and append to output stream
			valueType* cursorData = cursor->getData();

			ostringstream convert;
			convert << *cursorData;
			output.append(convert.str());
			output.append(" ");
		}
		return output;
	}

	//Iterator methods
	//Methods not currently used in the program. May be implemented at a later date
	template<class valueType>
	void LinkedList<valueType>::iteratorReset()
	{
		//Reset the cursor to the first node between head and tail
		iteratorCursor = head;
	}

	template<class valueType>
	void LinkedList<valueType>::iteratorNext()
	{
		//Check whether there is another pointer beyond the current cursor
		//If so, move to the next Node in the linked list
		if (iteratorHasNext())
			iteratorCursor = iteratorCursor->getNext();
		//If not, throw an error
		else
			throw"ERROR. Iterator does not have next";
	}

	template<class valueType>
	valueType* LinkedList<valueType>::iteratorData()
	{
		//Return the data of the current iterator
		return iteratorCursor->getData();
	}
	template<class valueType>
	bool LinkedList<valueType>::iteratorHasNext()
	{
		//Boolean result whether there is a next node that is not tail
		//If true, return true
		if (iteratorCursor->getNext() != tail)
			return true;
		//Return false if not
		else
			return false;
	}
	template<class valueType>
	std::ostream& operator << (std::ostream& output, const LinkedList<valueType>& target)
	{

		return output << target.toString() << endl;
	}

	//#include LinkedList.template
}
#endif
