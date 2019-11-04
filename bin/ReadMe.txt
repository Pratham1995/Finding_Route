Name: Prathamesh Berde
UTA ID: 1001628221

What programming language is used?
Java

How code is structured?
1)node class-In they sit contains the parent node, node and distance between the two nodes which is used in the linked list to get the connection between them.
2)addlist class- In this class the nodes are added to the fringes and uniformed cost search is used to calculate the distance between the nodes.
3)route class- In this class we use the closed linked list to find the route from source to the destination.
4)main class- In this class we read the input file and split it line by line using spaces and add it to the array list set.

How to run the code?
Change the directory to src folder from  findroute_Assignment1 project.
To compile all the java files in src provide following comment: javac *.java 
Note: Make sure all .class in src are deleted before compiling
To run the code:
For Non-heuristic:
java findroute uninf Input1.txt source destination
For heuristic:
java findroute inf Input1.txt source destination
ex: 	java FindRoute Input1.txt Bremen London h_kassel.txt


References:
Similar code found in following websites:
To read the line: https://stackoverflow.com/questions/4574041/read-next-word-in-java
How to split the line with space: https://www.geeksforgeeks.org/split-string-java-examples/
ArrayList Usage: https://www.geeksforgeeks.org/arraylist-in-java/
Uniform Cost logic: https://stackoverflow.com/questions/46132592/java-uniform-cost-search-implementation-with-txt-file-input
Node and ConnectingPath DataStructure: https://stackoverflow.com/questions/20635394/uniform-cost-search-implementation
PrintPath Implemenation: https://github.com/raymondchua/algorithms/blob/master/UniformCostSearchAlgo.java
https://stackoverflow.com/questions/8816001/how-to-get-distinct-values-from-an-array-in-c
https://www.javatpoint.com/java-string-equalsignorecase
https://stackoverflow.com/questions/4574041/read-next-word-in-java
https://algorithmicthoughts.wordpress.com/2012/12/15/artificial-intelligence-uniform-cost-searchucs/
Github Links used as reference:
https://github.com/ebullientcreed/find_route/blob/master/find_route.java
https://github.com/T-Nawaz/CSE-440_Uniform-Cost-Search/blob/master/Node.java

