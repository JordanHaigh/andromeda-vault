#ifndef NODE_H
#define NODE_H
#include <string>
using namespace std;

/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * Node.h
 */

namespace SENG1120ASSIGN
{
    class Node
    {
        public:
            //Create typedef
            typedef string valueType;

			/*CONSTRUCTORS*/
			/*
			 * Overloaded Node() Constructor
			 * @param initialData - Initially set to be the constructor for valueType
			 * @param inputNext - Initially set to NULL
			 * @param inputPrevious - Initially set to NULL
			 */
            Node(const valueType& initialData = valueType(), Node* inputNext = NULL, Node* inputPrevious = NULL);

			/*
			 * Node Destructor
			 * Destroys a specific Node when the 'delete' keyword is called
			 */
            ~Node();

            /*ACCESSOR METHODS*/
			/*
			 * valueType getData() const
			 * Pre-Condition: Data must exist in the Node
			 * Post-Condition: Returns the data of the node
			 * @return - 
			 */
            valueType getData() const;

			/*
			 * Node* getNext()
			 * Pre-Condition: A 'Next' node must exist
			 * Post-Condition: Return the 'Next' node in the sequence
			 * @return - Next Node
			 */
            Node* getNext();

			/*
			 * const Node* getNext() const
			 * Pre-Condition: A 'Next' node must exist
			 * Post-Condition: Returns the 'Next' node in the sequence
			 * @return - Next Node
			 */
            const Node* getNext() const;

			/*
			 * Node* getPrevious()
			 * Pre-Condition: A 'Previous' node must exist
			 * Post-Condition: Returns the 'Previous' node in the sequence
			 * @return - Previous Node
			 */
            Node* getPrevious();

			/*
			 * const Node* getPrevious() const
			 * Pre-Condition: A 'Previous' node must exist
			 * Post-Condition: Returns the 'Previous' node in the sequence
			 * @return - Previous Node 
			 */
            const Node* getPrevious() const;

            /*MUTATOR METHODS*/
			/*
			 * void setData(const valueType& inputData)
			 * Pre-Condition: Must have a 'valueType' input
			 * Post-Condition: Sets the 'inputData' to the 'data' variable in the class
			 * @param inputData - valueType data as a string (typedef string)
			 */
            void setData(const valueType& inputData);
            
			/*
			 * void setNext(Node* inputNext)
			 * Pre-Condition: Must have a 'Node*' input
			 * Post-Condition: Sets 'inputNext' to the 'next' variable in the class
			 * @param inputNext - inputNext as a Node pointer (Node*)
			 */
			void setNext(Node* inputNext);

			/*
			 * void setPrevious(Node* inputPrevious)
			 * Pre-Condition: Must have a 'Node* input'
			 * Post-Condition: Sets 'inputPrevious' to the 'previous' variable in the class
			 * @param inputPrevious - inputPrevious as a Node pointer (Node*)
			 */
            void setPrevious(Node* inputPrevious);

        private:
			valueType data;
            Node* next;
            Node* previous;


    };
}
#endif
