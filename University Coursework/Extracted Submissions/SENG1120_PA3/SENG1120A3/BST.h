#ifndef BST_H
#define BST_H
#include "Node.h"
#include <string>
#include <cstdlib>
#include <sstream>
using namespace std;

/*
 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
 * Course: SENG1120
 * BST.h
 */

namespace SENG1120ASSIGN
{
	template <class valueType>
	class BST
	{
		/*
		 * Overloaded Cout << Operator
		 * Returns the BST in the inOrder format
		 */
		friend std::ostream& operator << (std::ostream& output, const BST<valueType>& target);
		public:
			/*========================================CONSTRUCTORS========================================*/
			
			/*
			 * BST();
			 * Sets the rootNode to NULL and the number of nodes to zero
			 */
			BST();

			/*
			 * ~BST();
			 * Calls the clear() method to remove all nodes starting from the head (Root node)
			 */
			~BST();

			/*========================================ACCESSORS========================================*/
			
			/*
			 * std::size_t size();
			 * Pre-Condition: None
			 * Post-Condition: Returns the number of nodes in the BST
			 * @return - Returns the number of nodes in the BST
			 */
			std::size_t size();

			/*========================================MUTATORS========================================*/
			
			/*
			 * void insert(valueType* data);
			 * Pre-Condition: valueType must be valid
			 * Post-Condition: Calls a private helper to insert the data into a correct position in the BST
			 * @param data - Generified valueType to allow any data type to be inserted
			 */
			void insert(valueType* data);

			/*
			 * bool remove(valueType& deletionItem);
			 * Pre-Condition: The deletionItem must be a valid valueType that exists in the BST
			 * Post-Condition: Calls a private helper to remove the node containing the parameter in the BST
			 * @param data - Generified valueType to allow any data type to be removed
			 */
			bool remove(valueType& deletionItem);
			
			/*
			 * void clear();
			 * Pre-Condition: None
			 * Post-Condition: Deletes all nodes from the BST, sets the rootNode to NULL and the number of nodes to 0
			 */
			void clear();

			/*========================================QUERY========================================*/
			
			/*
			 * bool isEmpty();
			 * Pre-Condition: None
			 * Post-Condition: Returns a boolean value if the root node is set to NULL and the number of nodes is 0
			 * @return - Boolean value either true or false
			 */
			bool isEmpty();

			/*
			 * string inOrderPrint();
			 * Pre-Condition: None
			 * Post-Condition: Calls a private method to print out the nodes in the BST in inOrder Format
			 * @return - String containing all nodes in inOrder format
			 */
			string inOrderPrint();
			
			/*
			* string inOrderPrint() const;
			* Pre-Condition: None
			* Post-Condition: Calls a private method to print out the nodes in the BST in inOrder Format
			* @return - String containing all nodes in inOrder format
			*/
			string inOrderPrint() const;

			/*
			 * bool contains(valueType* item);
			 * Pre-Condition: Item must have a valid valueType
			 * Post-Condition: Returns a boolean if a node contains the specified valueType data
			 * @param item - Generified data that is used to search for a node in the BST
			 * @return - Boolean of either true or false if found in the BSt
			 */
			bool contains(valueType* item);

			/*========================================NON-GENERIC BST METHODS========================================*/
			//NOTE - I feel that this is wrong to implement these methods in the BST since it would be breaking its generic structure.
			//		 I have inserted these methods in order to comply with the specifications
			
			/*
			 * int findHD() const;
			 * Pre-Condition: Nodes must be using a Student valueType (string Name and float Grade) in order to find HD Grades
			 * Post-Condition: Returns an integer containing the number of Students that have obtained a HD grade
			 * @return - Integer containing number of students achieving a HD grade
			 */
			int findHD() const;

			/*
			 * float findAverage() const;
			 * Pre-Condition: Nodes must be using a Student valueType (string Name and float Grade) in order to find HD Grades
			 * Post-Condition: Returns a float containing the average grade of the Students
			 * @return - Float containing the average grade of the students
			 */
			float findAverage() const;

			/*
			 * deleteFailures();
			 * Pre-Condition: Nodes must be using a Student valueType (string Name and float Grade) in order to find HD Grades
			 * Post-Condition: Removes all students that obtained a grade lower than 50. Fixes the BST links entirely
			 */
			void deleteFailures();

		private:
			Node<valueType>* rootNode;
			std::size_t numberOfNodes;
			
			/*
			 * void add(Node<valueType>* parentNode, valueType* data);
			 * Pre-Condition: Parent node must be valid, as well as the data that is being inputted
			 * Post-Condition: Adds a new node in a respective position and updating links where necessary
			 * @param parentNode - Parent of the newly added node. Used to update links
			 * @param data - Generified data type that to be inserted into a node
			 */
			void add(Node<valueType>* parentNode, valueType* data);
			
			/*
			 * bool deleteNode(Node<valueType>* deleteNode);
			 * STARTING METHOD - Checks through with boolean cases to determine which delete method to use
			 * Pre-Condition: Parameter node must be a valid node in the BST
			 * Post-Condition: Deletes the node updates links where necessary
			 * @param deleteNode - Node to be deleted
			 * @return - Boolean value whether the node was deleted or not
			 */
			bool deleteNode(Node<valueType>* deleteNode);
			
			/*
			 * bool deleteNode(Node<valueType>* deleteNode);
			 * Checks whether the node to be deleted is the root node.
			 * Pre-Condition: Parameter node must be a valid node in the BST
			 * Post-Condition: Deletes the node updates links where necessary
			 * @param deleteNode - Node to be deleted
			 * @return - Boolean value of true or false if the node is the root node
			 */
			bool deletingRootNode(Node<valueType>* deleteNode);
			
			/*
			 * bool deleteLeafNode(Node<valueType>* deleteNode);
			 * Starter function has found that the node to be deleted is a leaf node.
			 * Pre-Condition: Parameter node must be a valid node in the BST
			 * Post-Condition: Deletes the node updates links where necessary
			 * @param deleteNode - Node to be deleted
			 */
			void deleteLeafNode(Node<valueType>* deleteNode);
			
			/*
			 * void deleteNodeOneChild(Node<valueType>* deleteNode);
			 * STARTER FUNCTION - The deleteNode() function has found that the node to be deleted is a node with one child.
			 * Pre-Condition: Parameter node must be a valid node in the BST
			 * Post-Condition: Deletes the node updates links where necessary
			 * @param deleteNode - Node to be deleted
			 */
			void deleteNodeOneChild(Node<valueType>* deleteNode);
			
			/*
			 * void deleteNodeOneChildRoot();
			 * Starter function has found that the node to be deleted is the root node.
			 * Pre-Condition: None
			 * Post-Condition: Deletes the root node updates links where necessary (Gets the left child's greatest right child)
			 */
			void deleteNodeOneChildRoot();

			/*
			 * void deleteNodeOneChildRight(Node<valueType>* deleteNode);
			 * Starter function has found that the node to be deleted is a node with one right child.
			 * Pre-Condition: Parameter node must be a valid node in the BST
			 * Post-Condition: Deletes the node updates links where necessary
			 * @param deleteNode - Node to be deleted
			 */
			void deleteNodeOneChildRight(Node<valueType>* deleteNode);
			
			/*
			 * void deleteNodeOneChildLeft(Node<valueType>* deleteNode);
			 * Starter function has found that the node to be deleted is a node with one left child.
			 * Pre-Condition: Parameter node must be a valid node in the BST
			 * Post-Condition: Deletes the node updates links where necessary
			 * @param deleteNode - Node to be deleted
			 */
			void deleteNodeOneChildLeft(Node<valueType>* deleteNode);

			/*
			 * void deleteNodeTwoChildren(Node<valueType>* deleteNode);
			 * Starter function has found that the node to be deleted is a node with two children.
			 * Pre-Condition: Parameter node must be a valid node in the BST
			 * Post-Condition: Deletes the node updates links where necessary
			 * @param deleteNode - Node to be deleted
			 */
			void deleteNodeTwoChildren(Node<valueType>* deleteNode);

			/*
			 * Node<valueType>* findLowestRightNode(Node<valueType>* leftRoot);
			 * Pre-Condition: Node must exist in the BST - CANNOT BE NULL
			 * Post-Condition: Returns the lowest node from a left child
			 * @param leftRoot - Node on the left side of the BST
			 * @return - The lowest node from the left child in the BST
			 */
			Node<valueType>* findLowestRightNode(Node<valueType>* leftRoot);

			/*
			 * int compare(const valueType* itemA, const valueType* itemB);
			 * Pre-Condition: valueTypes must be valid
			 * Post-Condition: Returns an integer determining the comparison between the two valueTypes
			 * @param itemA - valueType to be compared
			 * @param itemB - valueType to be compared
			 * @return - Integer determining the comparison (-1,0,1)
			 */
			int compare(const valueType* itemA, const valueType* itemB);

			/*
			 * bool contains(Node<valueType>* parentNode, valueType* item);
			 * Pre-Condition: Node must be in the existing BST
			 * Post-Condition: Returns a boolean value of true or false whether the valueType is in the BST
			 * @param parentNode - Used to find whether the node's data exists
			 * @param item - valueType to check against the BST
			 * @return - Boolean value of true or false whether it is in the BST
			 */
			bool contains(Node<valueType>* parentNode, valueType* item);

			/*
			 * bool search(Node<valueType>* parentNode, valueType* key);
			 * Pre-Condition: Node must be in the existing BST
			 * Post-Condition: Returns the Node that contains the valueType whether it is in the BST or not
			 * @param parentNode - Used to find whether the node's data exists
			 * @param key  - valueType to check against the BST
			 * @return - Node that contains the data
			 */
			Node<valueType>* search(Node<valueType>* parentNode, valueType* key);
			
			/*
			 * string inOrder(Node<valueType>* currentNode);
			 * Pre-Condition: None
			 * Post-Condition: Returns a string of the current node, recursing with each child until every node is collected
			 * @param currentNode - Node in the BST
			 * @return - String of the data in the node
			 */
			string inOrder(Node<valueType>* currentNode);
			
			/*
			* string inOrder(Node<valueType>* currentNode);
			* Pre-Condition: None
			* Post-Condition: Returns a string of the current node, recursing with each child until every node is collected
			* @param currentNode - Node in the BST
			* @return - String of the data in the node
			*/
			string inOrder(Node<valueType>* currentNode) const;

			/*
			 * int findHD(Node<valueType>* currentNode) const;
			 * Pre-Condition: None
			 * Post-Condition: Returns the number of HDs in the BST, recursing with each child until every node is checked
			 * @param currentNode - Node in the BST
			 * @return - Integer containing the number of HDs
			 */
			int findHD(Node<valueType>* currentNode) const;
			
			/*
			 * float sumGrades(Node<valueType>* currentNode) const;
			 * Pre-Condition: None
			 * Post-Condition: Returns the sum of the grades in the BST, recursing with each child until every node is collected
			 * @param currentNode - Node in the BST
			 * @return - float containing the sum of grades
			 */
			float sumGrades(Node<valueType>* currentNode) const;
			
			/*
			 * void deleteFailures(Node<valueType>* currentNode) const;
			 * Pre-Condition: None
			 * Post-Condition: Deletes every node with a grade below 50, recursing with each child until every node is checked
			 * @param currentNode - Node in the BST
			 */
			void deleteFailures(Node<valueType>* currentNode);

	};

	//#include "BST.template"


	/*
	 * Author: Jordan Haigh		StNo: 3256730		Lab: Thurs 11am
	 * Course: SENG1120
	 *  BST.template
	 */
	/*========================================CONSTRUCTORS========================================*/
	template<class valueType>
	BST<valueType>::BST()
	{
		rootNode = NULL;
		numberOfNodes = 0;
	}

	template<class valueType>
	BST<valueType>::~BST()
	{
		clear();
	}

	/*========================================ACCESSORS========================================*/
	template<class valueType>
	std::size_t BST<valueType>::size()
	{
		return numberOfNodes;
	}

	/*========================================MUTATORS========================================*/
	template<class valueType>
	void BST<valueType>::insert(valueType* data)
	{
		//If the BST is empty
		if (isEmpty())
		{
			//Insert the new node at the root of the tree, the new node will now be the root node of the tree
			this->rootNode = new Node<valueType>(data, NULL, NULL, NULL);
			numberOfNodes++;
		}
		else
			//Else, start to iterate through the BST
			add(rootNode, data);
	}

	template<class valueType>
	bool BST<valueType>::remove(valueType& deletionItem)
	{
		//If there are no nodes in the BST, return false
		if (isEmpty())
			return false;

		//If there are nodes in the BST, start iterating through the BST in order to find the node to be deleted
		//Once found node, call private delete method which will handle how the node will be deleted
		Node<valueType> foundNode = search(rootNode, deletionItem);
		//If foundNode is NULL, not found in BST
		if (foundNode == NULL)
			return false;

		//Have found the node in the BST - start deletion method
		deleteNode(foundNode);
		return true;
	}

	template<class valueType>
	void BST<valueType>::clear()
	{
		while (!isEmpty())
		{
			deleteNode(rootNode);
		}
		rootNode = NULL;
		numberOfNodes = 0;
	}

	/*========================================QUERY========================================*/
	template<class valueType>
	bool BST<valueType>::isEmpty()
	{
		return (rootNode == NULL && numberOfNodes == 0);
	}

	template<class valueType>
	string BST<valueType>::inOrderPrint()
	{
		return inOrder(rootNode);
	}

	template<class valueType>
	string BST<valueType>::inOrderPrint() const
	{
		return inOrder(rootNode);
	}

	template<class valueType>
	bool BST<valueType>::contains(valueType* item)
	{
		if (isEmpty())
			return false;
		
		return contains(rootNode, item);
	}

	/*========================================NON-GENERIC BST METHODS========================================*/
	
	template<class valueType>
	int BST<valueType>::findHD() const
	{
		if (rootNode == NULL)
			return 0;
		else
			return findHD(rootNode);
	}

	template<class valueType>
	float BST<valueType>::findAverage() const
	{
		if (rootNode == NULL)
			return 0;
		else
			return sumGrades(rootNode)/float(numberOfNodes);
	}

	template<class valueType>
	void BST<valueType>::deleteFailures()
	{
		deleteFailures(rootNode);
	}

	/*========================================PRIVATE HELPERS========================================*/
	template<class valueType>
	void BST<valueType>::add(Node<valueType>* parentNode, valueType* data)
	{
		//Check whether the data of the insertion node is less than,greater than or equal to the root node
		int relation = compare(data, parentNode->getData());
		//int relation = compare(parentNode->getData(), data);

		//If equal, replace the already positioned node with the new data
		if (relation == 0)
			parentNode->setData(data);
		//If less than, move down through the left side of the tree
		else if (relation < 0)
		{
			//Check if the next node in the tree is null
			if (parentNode->getLeft() == NULL)
			{
				//If null, create a new node and insert the new node there
				Node<valueType>* insertionNode = new Node<valueType>(data, parentNode, NULL, NULL);
				parentNode->setLeft(insertionNode);
				numberOfNodes++;
				return;
			}
			else
				//If not null, recursivey move down the tree until a null position is found
				add(parentNode->getLeft(), data);
		}
		//If greater than, move down through the right side of the tree
		else
		{
			//Check if the next node in the tree is null
			if (parentNode->getRight() == NULL)
			{
				//If null, create a new node and insert the new node there
				Node<valueType>* insertionNode = new Node<valueType>(data, parentNode, NULL, NULL);
				parentNode->setRight(insertionNode);
				numberOfNodes++;
				return;
			}
			//If not null, recursivey move down the tree until a null position is found
			else
				add(parentNode->getRight(), data);
		}
	}

	template<class valueType>
	bool BST<valueType>::deleteNode(Node<valueType>* deleteNode)
	{
		/*CASE 1 - IF THE DELETION NODE IS A LEAF NODE*/
		if (deleteNode->isLeaf())
			deleteLeafNode(deleteNode);
		/*CASE 2 - IF THE DELETION NODE HAS ONE CHILD*/
		else if (deleteNode->hasOneChild())
			deleteNodeOneChild(deleteNode);
		/*CASE 3 - IF THE DELETION NODE HAS TWO CHILDREN*/
		else
			deleteNodeTwoChildren(deleteNode);

		//Delete the node to avoid memory leaks
		numberOfNodes--;
		return true;
	}

	template <class valueType>
	bool BST<valueType>::deletingRootNode(Node<valueType>* deleteNode) 
	{
		return rootNode == deleteNode;
	}

	template<class valueType>
	void BST<valueType>::deleteLeafNode(Node<valueType>* deleteNode)
	{
		//Check whether the root node matches the input node's data
		if (deletingRootNode(deleteNode))
			//If matches, set rootNode to null
			rootNode = NULL;
		else
		{
			if (deleteNode->isRightChild())
				deleteNode->getParent()->setRight(NULL);
			else
				deleteNode->getParent()->setLeft(NULL);
		}
		delete deleteNode;
	}


	template<class valueType>
	void BST<valueType>::deleteNodeOneChild(Node<valueType>* deleteNode)
	{
		//Check whether the root node matches the delete nodes's data
		if (deletingRootNode(deleteNode))
			deleteNodeOneChildRoot();
		
		//If removing a node with a right child
		else if (deleteNode->hasRightChild())
			deleteNodeOneChildRight(deleteNode);
		
		//If removing a node with a left child
		else
			deleteNodeOneChildLeft(deleteNode);
		delete deleteNode;

	}

	template<class valueType>
	void BST<valueType>::deleteNodeOneChildRoot()
	{
		//If equal, Move to check whether the left and right children do not equal NULL
		if (rootNode->hasRightChild())
			rootNode = rootNode->getRight();
		else
			rootNode = rootNode->getLeft();

		rootNode->setParent(NULL);

	}

	template<class valueType>
	void BST<valueType>::deleteNodeOneChildRight(Node<valueType>* deleteNode)
	{
		//Reset the internal links in the tree
		Node<valueType>* myParent = deleteNode->getParent();
		Node<valueType>* myChild = deleteNode->getRight();

		myChild->setParent(myParent);
		if (deleteNode->isRoot())
		{
			rootNode = myChild;
		}
		else if (deleteNode->isRightChild())
			myParent->setRight(myChild);
		else
			myParent->setLeft(myChild);
	}

	template<class valueType>
	void BST<valueType>::deleteNodeOneChildLeft(Node<valueType>* deleteNode)
	{
		//Reset the internal links of the tree
		Node<valueType>* myParent = deleteNode->getParent();
		Node<valueType>* myChild = deleteNode->getLeft();

		myChild->setParent(myParent);

		if (deleteNode->isRoot())
		{
			rootNode = myChild;
		}
		else if (deleteNode->isRightChild())
			myParent->setRight(myChild);
		else
			myParent->setLeft(myChild);
	}

	template<class valueType>
	void BST<valueType>::deleteNodeTwoChildren(Node<valueType>* deleteNode)
	{
		//Find lowest right node in the BST
		Node<valueType>* lowestRightNode = findLowestRightNode(deleteNode->getLeft());

		//Consider the case when the farthest right node is not a leaf
		//I.E it only has a left child. We need to reset the connections for the left child so that the tree is not broken
		if (lowestRightNode == deleteNode->getLeft())
		{
			lowestRightNode->setRight(deleteNode->getRight());
			deleteNode->getRight()->setParent(lowestRightNode);

			deleteNodeOneChildLeft(deleteNode);
			return;
		}

		else if (lowestRightNode->hasLeftChild())
		{
			//Reset connections
			Node<valueType>* myParent = lowestRightNode->getParent();
			Node<valueType>* myLeftChild = lowestRightNode->getLeft();
			myParent->setRight(myLeftChild);
			myLeftChild->setParent(myParent);
		}

		else
			lowestRightNode->getParent()->setRight(NULL);

		valueType* temp1 = lowestRightNode->getData();
		valueType* temp2 = deleteNode->getData();

		//Swap the lowest right node and the delete node
		lowestRightNode->setData(temp2);
		deleteNode->setData(temp1);

		lowestRightNode->setParent(NULL);
		delete lowestRightNode;
	}

	template<class valueType>
	Node<valueType>* BST<valueType>::findLowestRightNode(Node<valueType>* leftRoot)
	{
		//Iterate down through the right subtree to find furthest right node
		// By definition, leftRoot cannot be null
		while (leftRoot->getRight() != NULL)
		{
			leftRoot = leftRoot->getRight();
		}
		return leftRoot;
	}


	template<class valueType>
	int BST<valueType>::compare(const valueType* itemA, const valueType* itemB)
	{
		//Dereference the pointers
		if (*itemA < *itemB)
			return -1;
		else if (*itemA == *itemB)
			return 0;
		else
			return 1;
	}

	template<class valueType>
	bool BST<valueType>::contains(Node<valueType>* parentNode, valueType* item)
	{
		if (parentNode == NULL)
			return false;

		int relation = compare(item, parentNode->getData());

		if (relation == 0)
			return true;
		else if (relation < 0)
			return contains(parentNode->getLeft(), item);
		else
			return contains(parentNode->getRight(), item);
	}


	template<class valueType>
	Node<valueType>* BST<valueType>::search(Node<valueType>* parentNode, valueType* key)
	{
		//If no data exists in the parent node
		if (parentNode == NULL)
			return NULL;

		//Iterate through the BST until found the key node
		int relation = compare(key, parentNode->getData());

		//If the compare function has found the node
		if (relation == 0)
			return parentNode;
		//If the compare function returns less than zero, recursively move down the left side of the tree
		else if (relation < 0)
			return search(parentNode->getLeft(), key);
		//If the compare function returns greater than zero, recursively move down the right side of the tree
		else
			return search(parentNode->getRight(), key);
	}


	template <class valueType>
	string BST<valueType>::inOrder(Node<valueType>* currentNode)
	{
		string output;
		ostringstream convert;
		
		if (currentNode == NULL)
			return "";
		
		//Move down the left side of the BST
		output.append(inOrder(currentNode->getLeft()));

		//Print details of each node
		convert << *currentNode->getData();
		output.append(convert.str());
		output.append(" ");

		//Move through the right subtrees afterwards
		output.append(inOrder(currentNode->getRight()));
		//TODO possible error here
		return output;

	}

	template <class valueType>
	string BST<valueType>::inOrder(Node<valueType>* currentNode) const
	{
		string output;
		ostringstream convert;

		if (currentNode == NULL)
			return "";

		//Move down the left side of the BST
		output.append(inOrder(currentNode->getLeft()));

		//Print details of each node
		convert << *currentNode->getData();
		output.append(convert.str());
		output.append(" ");

		//Move through the right subtrees afterwards
		output.append(inOrder(currentNode->getRight()));
		return output;

	}
	
	template<class valueType>
	int BST<valueType>::findHD(Node<valueType>* currentNode) const
	{
		int numberOfHDs = 0;
		
		if (currentNode == NULL)
			return 0;
		
		valueType* nodeData = currentNode->getData();
		if (nodeData->getGrade() >= 85)
			numberOfHDs++;

		numberOfHDs += findHD(currentNode->getLeft());
		numberOfHDs += findHD(currentNode->getRight());
		
		return numberOfHDs;
	}

	template<class valueType>
	float BST<valueType>::sumGrades(Node<valueType>* currentNode) const
	{
		int sumOfGrades = 0;

		if (currentNode == NULL)
			return 0;

		valueType* nodeData = currentNode->getData();
		sumOfGrades += nodeData->getGrade();

		sumOfGrades += sumGrades(currentNode->getLeft());
		sumOfGrades += sumGrades(currentNode->getRight());

		return sumOfGrades;
	}

	template<class valueType>
	void BST<valueType>::deleteFailures(Node<valueType>* currentNode)
	{
		bool hasBeenDeleted = false;
		if (currentNode == NULL)
			return;

		Node<valueType>* myLeft = currentNode->getLeft();
		Node<valueType>* myRight = currentNode->getRight();

		valueType* nodeData = currentNode->getData();

		if (nodeData->getGrade() < 50)
		{
			if (currentNode->isLeaf())
			{
				deleteNode(currentNode);
			}
			else
			{
				// Get my Parent
				Node<valueType>* myParent = currentNode->getParent();

				// Determine my relationship to my parent - am I a left or right child?
				bool leftChild = currentNode->isLeftChild();
				// Delete me
				deleteNode(currentNode);
				// call deteteFailures on myparents left/right child

				if (myParent == NULL)
					deleteFailures(rootNode);
				else if (leftChild)
					//Iterate through the BST again making sure the swapped node will be deleted
					deleteFailures(myParent->getLeft());
				else
					deleteFailures(myParent->getRight());
			}
			hasBeenDeleted = true;
		}

		if (!hasBeenDeleted)
		{
			deleteFailures(myLeft);
			deleteFailures(myRight);
		}
	}

	

	/*========================================OVERLOADED OPERATORS========================================*/
	template<class valueType>
	std::ostream& operator << (std::ostream& output, const BST<valueType>& target)
	{
		return output << target.inOrder();
	}
}
#endif
