#include "Node.h"

/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * Node.cpp
 */

namespace SENG1120ASSIGN
{
    
	Node::Node(const valueType& initialData, Node* initialNext, Node* initialPrevious)
    {
        //Set 'data' to the memory address of 'initialData'
		data = initialData;
		//Set 'next' to the Node pointer of 'initialNext'
		next = initialNext;
		//Set 'previous' to the Node pointer of 'initialPrevious'
        previous = initialPrevious;
    }


    Node::~Node()
    {
		//Remove the 'next' Node pointer from the heap
		delete next;
		//Remove the 'previous' Node pointer from the heap
        delete previous;
    }

    //Accessors
    //Two getter methods are required for a linked list (Const version and non const version)
	//Compiler will figure out which one is best to use
	Node::valueType Node::getData() const
    {
        return data;
    }

    Node* Node::getNext()
    {
        return next;
    }

    const Node* Node::getNext() const
    {
        return next;
    }

    Node* Node::getPrevious()
    {
        return previous;
    }

    const Node* Node::getPrevious() const
    {
            return previous;
    }

    //Mutators

	//setData requires the const valueType memory address of the argument 
	//So that the newly set 'data' will be persistent beyond its call
    void Node::setData(const valueType& inputData)
    {
        data = inputData;
    }

    void Node::setNext(Node* inputNext)
    {
        //Sets the 'next' Node pointer to the argument
		next = inputNext;
    }

    void Node::setPrevious(Node* inputPrevious)
    {
        //Sets the 'previous' Node pointer to the argument
		previous = inputPrevious;
    }

}
