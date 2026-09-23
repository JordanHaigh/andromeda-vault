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
	//NODE NOW USES TEMPLATES TO MAKE IT MORE GENERIC
	template <class valueType>
	class Node
	{
	public:

		/*CONSTRUCTORS*/
		/*
		 * Overloaded Node() Constructor
		 * @param initialData - Initially set to NULL
		 * @param inputNext - Initially set to NULL
		 * @param inputPrevious - Initially set to NULL
		 */
		Node(valueType* initialData = NULL, Node<valueType>* inputNext = NULL, Node<valueType>* inputPrevious = NULL);

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
		valueType* getData() const;

		/*
		 * Node<valueType>* getNext()
		 * Pre-Condition: A 'Next' node must exist
		 * Post-Condition: Return the 'Next' node in the sequence
		 * @return - Next Node
		 */
		Node<valueType>* getNext();

		/*
		 * const Node<valueType>* getNext() const
		 * Pre-Condition: A 'Next' node must exist
		 * Post-Condition: Returns the 'Next' node in the sequence
		 * @return - Next Node
		 */
		const Node<valueType>* getNext() const;

		/*
		 * Node<valueType>* getPrevious()
		 * Pre-Condition: A 'Previous' node must exist
		 * Post-Condition: Returns the 'Previous' node in the sequence
		 * @return - Previous Node
		 */
		Node<valueType>* getPrevious();

		/*
		 * const Node<valueType>* getPrevious() const
		 * Pre-Condition: A 'Previous' node must exist
		 * Post-Condition: Returns the 'Previous' node in the sequence
		 * @return - Previous Node
		 */
		const Node<valueType>* getPrevious() const;

		/*MUTATOR METHODS*/
		/*
		 * void setData(const valueType* inputData)
		 * Pre-Condition: Must have a 'valueType*' input
		 * Post-Condition: Sets the parameter 'data' to the 'data' variable in the class
		 * @param data - valueType data defined as a card
		 */
		void setData(valueType* data);

		/*
		 * void setNext(Node<valueType>* next)
		 * Pre-Condition: Must have a 'Node<valueType>*' input
		 * Post-Condition: Sets the parameter 'next' to the 'next' variable in the class
		 * @param next - next as a Node pointer (Node<valueType>*)
		 */
		void setNext(Node<valueType>* next);

		/*
		 * void setPrevious(Node<valueType>* previous)
		 * Pre-Condition: Must have a 'Node<valueType>* input'
		 * Post-Condition: Sets the parameter 'previous' to the 'previous' variable in the class
		 * @param previous - previous as a Node pointer (Node<valueType>*)
		 */
		void setPrevious(Node<valueType>* previous);

	private:
		valueType* data;
		Node<valueType>* next;
		Node<valueType>* previous;


	};

	//TODO refactor out into .template file
	//TODO replace all the initialdata = data with this->data = data
	template <class valueType>
	Node<valueType>::Node(valueType* data, Node<valueType>* next, Node<valueType>* previous)
	{
		//Set 'data' to the memory address of 'initialData'
		this->data = data;
		//Set 'next' to the Node pointer of 'initialNext'
		this->next = next;
		//Set 'previous' to the Node pointer of 'initialPrevious'
		this->previous = previous;
	}


	template <class valueType>
	Node<valueType>::~Node()
	{
		delete data;
		//Remove the 'next' Node pointer from the heap
		delete next;
		//Remove the 'previous' Node pointer from the heap
		delete previous;
	}

	//Accessors
	//Two getter methods are required for a linked list (Const version and non const version)
	//Compiler will figure out which one is best to use
	template <class valueType>
	valueType* Node<valueType>::getData() const
	{
		return data;
	}

	template <class valueType>
	Node<valueType>* Node<valueType>::getNext()
	{
		return next;
	}

	template <class valueType>
	const Node<valueType>* Node<valueType>::getNext() const
	{
		return next;
	}

	template <class valueType>
	Node<valueType>* Node<valueType>::getPrevious()
	{
		return previous;
	}

	template <class valueType>
	const Node<valueType>* Node<valueType>::getPrevious() const
	{
		return previous;
	}

	//Mutators

	//setData requires the const valueType memory address of the argument 
	//So that the newly set 'data' will be persistent beyond its call
	template <class valueType>
	void Node<valueType>::setData(valueType* data)
	{
		this->data = data;
	}

	template <class valueType>
	void Node<valueType>::setNext(Node<valueType>* next)
	{
		//Sets the 'next' Node pointer to the argument
		this->next = next;
	}

	template <class valueType>
	void Node<valueType>::setPrevious(Node<valueType>* previous)
	{
		//Sets the 'previous' Node pointer to the argument
		this->previous = previous;
	}

	//#include "Node.template"

}
#endif
