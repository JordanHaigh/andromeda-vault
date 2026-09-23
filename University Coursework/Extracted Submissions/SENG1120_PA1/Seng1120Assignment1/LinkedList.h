//LinkedList.h
#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include "Node.h"
#include <string>
#include <iostream>
using namespace std;

/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * LinkedList.h
 */

namespace SENG1120ASSIGN
{
    class LinkedList
    {
        friend std::ostream& operator << (std::ostream& output, const LinkedList& target);

        public:
            typedef Node::valueType valueType;

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
			* void addAt(std::size_t, valueType data)
			* Precondition: 'index' must be a valid index in the Linked List
			* Postcondition: Adds new node at the specified index with the inputted data
			* @param index - Defines the integer value of the index for insertion
			* @param data - Defines valueType (typedef Node::valueType) of data to be entered
			*/
            void addAt(std::size_t index, valueType data);
           
			/*
			 * valueType removeAt(std::size_t index)
			 * Precondition: 'index' must be a valid index in the Linked List
			 * Postcondition: Removes node from that specific index and fixes Node linkage
			 * @param index - Defines the integer value of the index for insertion
			 * @return - Returns data of the node that was removed
			 */
			valueType removeAt(std::size_t index);
            
			/*
			 * bool removeFirst(valueType data)
			 * Precondition: 'data' must be identical to the data found in the Linked List
			 * Postcondition: Removes the first node with the specific data entry and fixes Node linkage
			 * @param data - Defines the valueType (typedef Node::valueType) of data to be entered
			 * @return - Returns 1 or 0 (True || False) whether the Node was removed or not
			 */
			bool removeFirst(valueType data);

			/*
			 * valueType get(std::size_t index)			 
			 * Precondition: 'index' must be a valid index in the Linked List
			 * Postcondition: Gets the node data at that specific index
			 * @param index - Defines the integer value of the index for insertion
			 * @return - Returns the data of the specific Node if found
			 */
            valueType get(std::size_t index);

			/*
			 * void set(std::size_t index, valueType data)
			 * Precondition: 'index' must be a valid index in the Linked List
			 * Postcondition: Edits the data at the specified index with new data
			 * @param index - Defines the integer value of the index for insertion
			 * @param data - Defines the valueType (typedef Node::valueType) of data to be entered
			 */
            void set(std::size_t index, valueType data);

			/*
			 * std::size_t indexOf(valueType data)
			 * Precondition: The inputted 'data' must be equal to the valueType data in the LinkedList
			 * Postcondition: Returns the index of that specified data entry
			 * @param data - Defines the valueType (typedef Node::valueType) of data to be entered
			 * @return - Returns the index of the Node
			 */
            std::size_t indexOf(valueType data);

			/*
			 * void addToHead(valueType& data)
			 * Precondition: None
			 * Postcondition: Adds new node after the Head Node
			 * @param data - Defines the valueType (typedef Node::valueType) of data to be entered
			 */
            void addToHead(valueType& data);

			/*
			 * void addToTail(valueType& data)
			 * Precondition: None
			 * Postcondition: Adds new node before the tail Node
			 * @param data - Defines the valueType (typedef Node::valueType) of data to be entered
			 */
            void addToTail(valueType& data);

			/*
			 * void removeFromHead()
			 * Precondition: Node must exist before Tail and after Head
			 * Postcondition: Removes the first Node after Head
			 */
            void removeFromHead();

			/*
			 * void removeFromTail()
			 * Precondition: Node must exist before Tail and after Head
			 * Postcondition: Removes the first Node before Tail
			 */
            void removeFromTail();

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
			* valueType toString() const
			* Precondition: Node must contain data and be within the linked list range
			* Postcondition: Returns specific Node data as a string
			* @return - valueType (string) containing Node data
			*/
			valueType toString() const;


			//TODO Implement swap method
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
            valueType iteratorData();

			/*
			* bool iteratorHasNext()
			* Precondition: None
			* Postcondition: Checks whether there is a node that is not null next in the sequence
			* @return - 0 or 1 (True || False) whether there is another node in the sequence
			*/
            bool iteratorHasNext();

        private:
            Node* head;
            Node* tail;
            std::size_t listLength;

            //Iterator data
            Node* iteratorCursor;

    };

}
#endif
