//
//Different types of Operators in C++
//
// Logical operators are used to determine the logic between variables or values
// (&&, ||, !)

#include <iostream>
using namespace std;

#define A 30
#define B 20

int main(){
	
	//declare the variable
	int result;
	
	//
	// If the result is 0 it is "False" and if 1 it is "True"
	//
	
	
	//Logical and(&&)
	//The AND operator returns the value true when the both statement is true
	result = A>B && B<A;
	cout << "LOGICAL AND\n"<<"The result of the logical operator AND is "<<result <<endl<<endl;
	
	//Logical or(||)
	//The OR operator returns the value true if one statement is true
	result = A>B || A<B;
	cout << "LOGICAL OR\n"<<"The result of the logical operator OR is "<<result <<endl<<endl;
	
	//Logical not(!)
	//The OR operator reverses the logical statement if the condition is true then the return value is false
	result = !(A>B && B<A);
	cout << "LOGICAL NOT\n"<<"The result of the logical operator NOT is "<<result <<endl<<endl;
	
	system("pause");
	return 0;
}
