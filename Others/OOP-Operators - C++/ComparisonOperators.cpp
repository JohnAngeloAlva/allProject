//
//Different types of Operators in C++
//
// Comparison operators are used to compare two values of the variables.
// (==, !=, >, <, >=, <=)

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
	
	
	//Equal to(==)
	//The Equal to operator is comparing the value of the two variable if they are equal to each other
	result = A == B;
	cout << "EQUAL TO\n"<<"The two values are equal to each other?: "<<result <<endl<<endl;
	
	//Not Equal to(!=)
	//The not Equal to operator is comparing the value of the two variable if they are not equal to each other
	result = A != B;
	cout << "NOT EQUAL TO\n"<<"The two values are not equal to each other?: "<<result <<endl<<endl;
	
	//Greater Than (>)
	//The Greater Than operator is comparing the value of the two variable if the first value is greater than to the second value
	result = A>B;
	cout << "GREATER THAN\n"<<"The first value is greater than to the second value?:   "<<result <<endl<<endl;
	
	//Less Than (<)
	//The Less Than operator is comparing the value of the two variable if the first value is less than to the second value
	result = A<B;
	cout << "LESS THAN\n"<<"The first value is less than to the second value?:   "<<result <<endl<<endl;
	
	//Greater Than (>=)
	//The Greater Than or Equal operator is comparing the value of the two variable if the first value is greater than or equal to the second value
	result = A>=B;
	cout << "GREATER THAN OR EQUAL\n"<<"The first value is greater than or equal to the second value?:   "<<result <<endl<<endl;
	
	//Less Than or equal (<=)
	//The Less than or equal operator is comparing the value of the two variable if the first value is less than or equal to the second value
	result = A<=B;
	cout << "LESS THAN OR EQUAL\n"<<"The first value is less than or equal to the second value?:   "<<result <<endl<<endl;
	

	
	system("pause");
	return 0;
	
	
}
