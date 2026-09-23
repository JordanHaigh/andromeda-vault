#include "LinkedList.h"

/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * LinkedList.cpp
 */

namespace SENG1120ASSIGN
{
    LinkedList::LinkedList()
    {
        //I have set up my linked list differently to the lecture slides
		//My head and tail pointers will be sentinels - they will always be NULL
		//All data will go between the head and the tail node

		//Initialise head and tail
		head = new Node();
        tail = new Node();

        //Link head and tail together
        head->setNext(tail);
        tail->setPrevious(head);

        //Initialise counter - Makes it easier to find size of linked list without traversing
        listLength = 0;
    }

    LinkedList::~LinkedList()
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

    bool LinkedList::isEmpty()
    {
        //If list length is less than 1 (Nothing between two sentinels), will return false
        return listLength<1;
    }

    void LinkedList::addAt(std::size_t index, valueType data)
    {
        //Assuming that user is inputting a new node in between two existing nodes
        //Adds node at before the specified index so that the new node becomes that index

        //Traverse to the index
		//Uses a counter variable to check whether the cursor is at the index or not
		//Double check first that the index entered by the user is within range
        std::size_t counter = 0;
        if(index < 0 || index >=size())
        {
            throw "ERROR. Index is out of range";
        }
		//If in range, continue to find the index
        for(Node* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
        {
            //If the counter has matched the index
			if(counter == index)
            {
                //Create new node
                Node* previousNode = cursor->getPrevious();
                //New Node
                Node* newNode = new Node(data, cursor, previousNode);
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

    LinkedList::valueType LinkedList::removeAt(std::size_t index)
    {
		//Uses a counter variable to check whether the cursor is at the index or not
		//Double check first that the index entered by the user is within range
		std::size_t counter = 0;
        if(index <0 || index >= size())
        {
            throw "ERROR: Index is out of range.";
        }

		//If in range, continue to find the index
        for(Node* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
        {
            //If counter matches with the index
			if(counter == index)
            {
                //Found index of Node
                //Create a previousNode and nextNode to fix the links when removing the specified Node
                Node* previousNode = cursor->getPrevious();
                Node* nextNode = cursor->getNext();

                previousNode->setNext(nextNode);
                nextNode->setPrevious(previousNode);
                valueType cursorData = cursor->getData();

				//Remove the links of the current cursor to avoid a stack overflow
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

    bool LinkedList::removeFirst(valueType data)
    {

        // Removes first instance of data
        for(Node* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
        {
            //Iterate until found data

            //if(data == NULL ? cursor->getData() == NULL : cursor->getData().equals(data))
            //This ternary could have worked, though because the typedef is not a pointer, this isn't achievable
            //TODO replace with object.equals at a later date

			//Uses compare method to compare the two data types (Similar to .equals in Java)
            if(cursor->getData().compare(data) == 0)
            {
                //If strings are equals, will result to 0 (Boolean)
                //Create nextNode and previousNode and update linkages
                Node* nextNode = cursor->getNext();
                Node* previousNode = cursor->getPrevious();

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

    LinkedList::valueType LinkedList::get(std::size_t index)
    {
		//Uses a counter variable to check whether the cursor is at the index or not
		//Double check first that the index entered by the user is within range
		std::size_t counter=0;
        if(index < 0 || index >= size())
        {
            throw "ERROR. Index is out of range";
        }
		//If in range, start to loop until found correct index
        for(Node* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
        {
            //If counter is equal to the index
			if(counter == index)
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

    void LinkedList::set(std::size_t index, valueType data)
    {
		//Uses a counter variable to check whether the cursor is at the index or not
		//Double check first that the index entered by the user is within range
		std::size_t counter = 0;
        if(index < 0 || index >= size())
        {
            throw "ERROR. Index in out of range";
        }
        //If in range, start to loop until found correct index
		for(Node* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
        {
            //If found correct index
			if(counter == index)
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

    std::size_t LinkedList::indexOf(valueType data)
    {
		//Start counter at position 0
		std::size_t counter = 0;
		//Start loop of the linked list
        for(Node* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
        {
            //Data has to be exactly the same (including capitals)
            //Need to use .compare to compare two strings as CPP doesn't include a .equals() alternative
            //TODO replace with object.equals at a later date
            
			//If data matches with the argument data
			if(cursor->getData().compare(data) == 0)
            {
                return counter;
            }
            //Increment counter until found
			else
                counter++;
        }
		//Return -1 if not found in the linked list
        return -1;
    }

    void LinkedList::addToHead(valueType& data)
    {
		//Create newNode that will be the next node after head
		Node* nextNode = head->getNext();
		//Use Node constructor to initialise data and links
        Node* newNode = new Node(data, nextNode, head);

        //Update links
        head->setNext(newNode);
        nextNode->setPrevious(newNode);
		//Increment list length
        listLength++;
    }

    void LinkedList::addToTail(valueType& data)
    {
        //If the linked list is empty, the previous node will be head
		//If the linked list is not empty, the previous node will be tail->getPrevious()
        Node* previousNode = tail->getPrevious();

        //Create new node with data and links
        Node* newNode = new Node(data,tail,previousNode);

        //Update links
        tail->setPrevious(newNode);
        previousNode->setNext(newNode);
		//Increment list length
        listLength++;
    }

    void LinkedList::removeFromHead()
    {
        //If the linked list is empty (No nodes between head and tail), just return
		if(listLength == 0)
            return;
        //If not, continue with the removal
		else
        {
            //Create two nodes for the removal: deleteNode (Node that will be removed)
			//									nextNode (The next node after the node to be deleted)
			Node* deleteNode = head->getNext();
            Node* nextNode = deleteNode->getNext();

            //Update links
            head->setNext(nextNode);
            nextNode->setPrevious(head);

			//Remove the links from deleteNode to avoid stack overflow
			deleteNode->setNext(NULL);
			deleteNode->setPrevious(NULL);
            // Delete node
            delete deleteNode; //Java API differs from this as it is meant to return data

            // Decrement size
            listLength--;
        }
    }

    void LinkedList::removeFromTail()
    {
        //If the linked list is empty (No nodes between head and tail), just return
		if(listLength == 0)
            return;
        //If not empty, continue with the method
		else
        {
			//Create two nodes for the removal: deleteNode (Node that will be removed)
			//									previousNode (The previous node before the node to be deleted)
			Node* deleteNode = tail->getPrevious();
            Node* previousNode = deleteNode->getPrevious();

            //Update links
            tail->setPrevious(previousNode);
            previousNode->setNext(tail);

			//Remove the links from the deleted node to avoid stack overflow
			deleteNode->setNext(NULL);
			deleteNode->setPrevious(NULL);
            // Delete node
            delete deleteNode;

            // Decrement size
            listLength--;
        }
    }

    std::size_t LinkedList::size()
    {
        //Return the current list length
		return listLength;
    }

    void LinkedList::clear()
    {
        //Clears the innards of the linked list without removing the head and tail sentinel nodes
        while(!isEmpty())
            removeFromHead();
    }

	LinkedList::valueType LinkedList::toString() const
	{
		valueType output;
		//Overloaded cout operator to print the node data in the linked list
		for (Node* cursor = head->getNext(); cursor != tail; cursor = cursor->getNext())
		{
			//iterate over all nodes and append to output stream
			output.append(cursor->getData());
			output.append(" ");
		}
		return output;
	}

    //Iterator methods
	//Methods not currently used in the program. May be implemented at a later date
    void LinkedList::iteratorReset()
    {
        //Reset the cursor to the first node between head and tail
		iteratorCursor = head->getNext();
    }

    void LinkedList::iteratorNext()
    {
        //Check whether there is another pointer beyond the current cursor
		//If so, move to the next Node in the linked list
		if(iteratorHasNext())
            iteratorCursor = iteratorCursor->getNext();
        //If not, throw an error
		else
            throw"ERROR. Iterator does not have next";
    }

    LinkedList::valueType LinkedList::iteratorData()
    {
        //Return the data of the current iterator
		return iteratorCursor->getData();
    }

    bool LinkedList::iteratorHasNext()
    {
        //Boolean result whether there is a next node that is not tail
		//If true, return true
		if(iteratorCursor->getNext()!=tail)
            return true;
        //Return false if not
		else
            return false;
    }

    std::ostream& operator << (std::ostream& output, const LinkedList& target)
    {

		return output << target.toString() << endl;
    }


}
