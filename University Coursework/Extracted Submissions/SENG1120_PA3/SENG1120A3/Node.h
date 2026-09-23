#ifndef NODE_H
#define NODE_H
#include <string>
#include <cstdlib>
using namespace std;

/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * Node.h
 */

namespace SENG1120ASSIGN
{
	template <class valueType>
	class Node
	{
		/*
		 * Overloaded Cout << Operator
		 * Returns the targets data
		 */
		friend std::ostream& operator << (std::ostream& output, const Node<valueType>& target);
		
		/*
		 * Overloaded < Operator
		 * Returns a boolean value whether the data of itemA is less than the data of itemB
		 */
		friend bool operator < (const Node<valueType>& itemA, const Node<valueType>& itemB);
		
		/*
		 * Overloaded == Operator
		 * Returns a boolean value whether the data of itemA is equal to the data of itemB
		 */
		friend bool operator == (const Node<valueType>& itemA, const Node<valueType>& itemB);
		public:
			/*========================================CONSTRUCTORS========================================*/
			
			/*
			 * Node();
			 * Sets the Node pointers (Parent, Left, Right) to NULL and the data to NULL
			 */
			Node();
			
			/*
			 * Node(valueType* data = NULL, Node<valueType>* parent = NULL, Node<valueType>* left= NULL, Node<valueType>* right = NULL);
			 * Sets the Node pointers (Parent, Left, Right) to its inputted data and the data to the inputted 'data'
			 */
			Node(valueType* data = NULL, Node<valueType>* parent = NULL, Node<valueType>* left= NULL, Node<valueType>* right = NULL);
			
			/*
			 * ~Node();
			 * Calls the clear method to clean up the Nodes and its data, then deleting for safe measures
			 */
			~Node();

			/*========================================ACCESSORS========================================*/
			
			/*
			 * valueType* getData();
			 * Pre-Condition: Valid Node must contain data
			 * Post-Condition: Returns the data inside that node
			 * @return - Returns data inside that node
			 */
			valueType* getData();

			/*
			 * valueType* getData() const;
			 * Pre-Condition: Valid Node must contain data
			 * Post-Condition: Returns the data inside that node
			 * @return - Returns data inside that node
			 */
			valueType* getData() const;
			
			/*
			* Node<valueType>* getParent();
			* Pre-Condition: Valid Node must contain data (Parent can be NULL)
			* Post-Condition: Returns the parent of the node
			* @return - Returns the parent of the node
			*/
			Node<valueType>* getParent();

			/*
			 * Node<valueType>* getParent() const;
			 * Pre-Condition: Valid Node must contain data (Parent can be NULL)
			 * Post-Condition: Returns the parent of the node
			 * @return - Returns the parent of the node
			 */
			Node<valueType>* getParent() const;
			
			/*
			 * Node<valueType>* getLeft();
			 * Pre-Condition: Valid Node must contain data (Left can be NULL)
			 * Post-Condition: Returns the left child of the node
			 * @return - Returns the left child of the node
			 */
			Node<valueType>* getLeft();

			/*
			* Node<valueType>* getLeft() const;
			* Pre-Condition: Valid Node must contain data (Left can be NULL)
			* Post-Condition: Returns the left child of the node
			* @return - Returns the left child of the node
			*/
			const Node<valueType>* getLeft() const;
			
			/*
			 * Node<valueType>* getRight();
			 * Pre-Condition: Valid Node must contain data (Right can be NULL)
			 * Post-Condition: Returns the right child of the node
			 * @return - Returns the right child of the node
			 */
			Node<valueType>* getRight();

			/*
			 * Node<valueType>* getRight() const;
			 * Pre-Condition: Valid Node must contain data (Right can be NULL)
			 * Post-Condition: Returns the right child of the node
			 * @return - Returns the right child of the node
			 */
			const Node<valueType>* getRight() const;
			
			/*========================================MUTATORS========================================*/
			
			/*
			 * void setData(valueType* data);
			 * Pre-Condition: valueType must be valid
			 * Post-Condition: Sets the data of the Node to the generified valueType'
			 * @param data - Generified valueType to allow any data type to be inserted
			 */
			void setData(valueType* data);
			
			/*
			 * void setParent(Node<valueType>* parent);
			 * Pre-Condition: Must be a valid Node
			 * Post-Condition: Sets the parent of the current Node to the inputted 'parent'
			 * @param data - Generified parent to allow any parent with a data type to be inserted
			 */
			void setParent(Node<valueType>* parent);
			
			/*
			 * void setLeft(Node<valueType>* left);
			 * Pre-Condition: Must be a valid Node
			 * Post-Condition: Sets the left child of the current Node to the inputted 'left'
			 * @param data - Generified left child to allow any left child with a data type to be inserted
			 */
			void setLeft(Node<valueType>* left);
			
			/*
			 * void setRight(Node<valueType>* right);
			 * Pre-Condition: Must be a valid Node
			 * Post-Condition: Sets the right child of the current Node to the inputted 'right'
			 * @param data - Generified right child to allow any right child with a data type to be inserted
			 */
			void setRight(Node<valueType>* right);


			/*========================================QUERY========================================*/

			/*
			 * bool isLeaf() const;
			 * Pre-Condition: None
			 * Post-Condition: Returns a boolean value whether the current node is a leaf node
			 * @return - Boolean value either true or false
			 */
			bool isLeaf() const;

			/*
			 * bool hasOneChild() const;
			 * Pre-Condition: None
			 * Post-Condition: Returns a boolean value whether the current node has one child
			 * @return - Boolean value either true or false
			 */
			bool hasOneChild() const;
			
			/*
			 * bool hasTwoChildren() const;
			 * Pre-Condition: None
			 * Post-Condition: Returns a boolean value whether the current node has two children
			 * @return - Boolean value either true or false
			 */
			bool hasTwoChildren() const;

			/*
			 * bool hasRightChild() const;
			 * Pre-Condition: None
			 * Post-Condition: Returns a boolean value whether the current node has a right child
			 * @return - Boolean value either true or false
			 */
			bool hasRightChild() const;
			
			/*
			 * bool hasLeftChild() const;
			 * Pre-Condition: None
			 * Post-Condition: Returns a boolean value whether the current node has a left child
			 * @return - Boolean value either true or false
			 */
			bool hasLeftChild() const;
			
			/*
			 * bool isRightChild() const;
			 * Pre-Condition: None
			 * Post-Condition: Returns a boolean value whether the current node is a right child
			 * @return - Boolean value either true or false
			 */
			bool isRightChild() const;

			/*
			 * bool isLeftChild() const;
			 * Pre-Condition: None
			 * Post-Condition: Returns a boolean value whether the current node is a left child()
			 * @return - Boolean value either true or false
			 */
			bool isLeftChild() const;

			/*
			* bool hasLeftChild() const;
			* Pre-Condition: None
			* Post-Condition: Returns a boolean value whether the current node has a left child
			* @return - Boolean value either true or false
			*/
			bool isRoot() const;

		
		private:
			valueType* data;
			Node<valueType>* parent;
			Node<valueType>* left;
			Node<valueType>* right;
			
			/*
			 * void clear();
			 * Pre-Condition: None
			 * Post-Condition: Sets all node data to NULL
			 */
			void clear();

	};
	//#include "Node.template"


	/*
	 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
	 * Course: SENG1120
	 * Node.template
	 */
	/*========================================CONSTRUCTORS========================================*/
	template <class valueType>
	Node<valueType>::Node()
	{
		data = NULL;
		parent = NULL;
		left = NULL;
		right = NULL;
	}

	template <class valueType>
	Node<valueType>::Node(valueType* data, Node<valueType>* parent, Node<valueType>* left, Node<valueType>* right)
	{
		//Set 'data' to the valueType pointer of inputted 'data'
		this->data = data;
		//Set 'parent' to the Node pointer of the inputted 'parent'
		this->parent = parent;
		//Set 'left' to the Node pointer of inputted 'left'
		this->left = left;
		//Set 'right' to the Node pointer of inputted 'right'
		this->right = right;
	}

	template<class valueType>
	Node<valueType>::~Node()
	{
		clear();
		//Failsafe to remove node data
		delete data;
		delete parent;
		delete left;
		delete right;
	}

	/*========================================ACCESSORS========================================*/
	template <class valueType>
	valueType* Node<valueType>::getData()
	{
		return data;
	}


	template<class valueType>
	valueType* Node<valueType>::getData() const
	{
		return data;
	}

	template<class valueType>
	Node<valueType>* Node<valueType>::getParent()
	{
		return parent;
	}


	template<class valueType>
	Node<valueType>* Node<valueType>::getParent() const
	{
		return parent;
	}

	template<class valueType>
	Node<valueType>* Node<valueType>::getLeft()
	{
		return left;
	}

	template<class valueType>
	const Node<valueType>* Node<valueType>::getLeft() const
	{
		return left;
	}

	template<class valueType>
	Node<valueType>* Node<valueType>::getRight()
	{
		return right;
	}

	template<class valueType>
	const Node<valueType>* Node<valueType>::getRight() const
	{
		return right;
	}
	/*========================================MUTATORS========================================*/
	template<class valueType>
	void Node<valueType>::setData(valueType* data)
	{
		this->data = data;
	}

	template<class valueType>
	void Node<valueType>::setParent(Node<valueType>* parent)
	{
		this->parent= parent;
	}

	template<class valueType>
	void Node<valueType>::setLeft(Node<valueType>* left)
	{
		this->left = left;
	}

	template<class valueType>
	void Node<valueType>::setRight(Node<valueType>* right)
	{
		this->right = right;
	}

	/*========================================QUERY========================================*/
	template<class valueType>
	bool Node<valueType>::isLeaf() const
	{
		return (left == NULL) && (right == NULL);
	}

	template<class valueType>
	bool Node<valueType>::hasOneChild() const
	{
		return(hasRightChild() && !hasLeftChild()) ||
			(!hasRightChild() && hasLeftChild());
	}
	template<class valueType>
	bool Node<valueType>::hasTwoChildren() const
	{
		return(hasRightChild() && hasLeftChild());
	}


	template<class valueType>
	bool Node<valueType>::hasRightChild() const
	{
		return(right != NULL);
	}

	template<class valueType>
	bool Node<valueType>::hasLeftChild() const
	{
		return(left != NULL);
	}

	template<class valueType>
	bool Node<valueType>::isRightChild() const
	{
		if (isRoot())
			return false;
		else
			return this == parent->getRight(); 
	}

	template<class valueType>
	bool Node<valueType>::isLeftChild() const
	{
		if (isRoot())
			return false;
		else
			return this == parent->getLeft();
	}

	template<class valueType>
	bool Node<valueType>::isRoot() const
	{
		return parent == NULL;
	}

	template<class valueType>
	void Node<valueType>::clear()
	{
		data = NULL;
		parent = NULL;
		left = NULL;
		right = NULL;
	}

	/*========================================OVERLOADED OPERATORS========================================*/
	template<class valueType>
	std::ostream& operator << (std::ostream& output, const Node<valueType>& target)
	{
		return output << *target.getData();
	}

	template<class valueType>
	bool operator < (const Node<valueType>& itemA, const Node<valueType>& itemB)
	{
		return itemA.getData() < itemB.getData();
	}
	template<class valueType>
	bool operator == (const Node<valueType>& itemA, const Node<valueType>& itemB)
	{
		return itemA.getData() == itemB.getData();
	}

}
#endif



