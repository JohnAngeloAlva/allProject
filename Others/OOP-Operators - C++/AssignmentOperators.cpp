//
//Different types of Operators in C++
//
// Assignment operators are used to assign values to variables
// (=, +=, -=, *=, /=, %=)

#include <iostream>
using namespace std;


int main(){
	
	//declare the variable
	int a=5;
	
	//Equal Assignment(=)
	//The Equal Assignment is assigning the value to the variable
	cout << "EQUAL ASSIGNMENT\n"<<"The value that assigned to the variable is  "<<a <<endl<<endl;
	
	//Addition Assignment(+=)
	//The Addition Assignment is adding the second value(in right side) to the value that assigned to the variable(in left side) 
	a+=3;
	cout << "ADDITION ASSIGNMENT\n"<<"The result of adding the two value is  "<<a <<endl<<endl;
	
	//Subtraction Assignment(-=)
	//The Subtraction Assignment is subtracting the second value(in right side) to the value that assigned to the variable(in left side)
	int b=6;
	b-=3;
	cout << "SUBTRACTION ASSIGNMENT\n"<<"The result of subtracting the two value is  "<<b <<endl<<endl;
	
	//Multiplication Assignment(*=)
	//The Multiplication Assignment is multiplying the second value(in right side) to the value that assigned to the variable(in left side)
	int c=6;
	c*=3;
	cout << "MULTIPLICATION ASSIGNMENT\n"<<"The result of mumltiplying the two value is  "<<c <<endl<<endl;
	
	//Division Assignment(*=)
	//The Division Assignment is dividing the second value(in right side) to the value that assigned to the variable(in left side)
	int d=6;
	d/=3;
	cout << "DIVISION ASSIGNMENT\n"<<"The result of dividing the two value is  "<<d <<endl<<endl;
	
	//Modulus Assignment(*=)
	//The Modulus Assignment is getting the division remainder of the second value(in right side) to the value that assigned to the variable(in left side)
	int e=6;
	e%=3;
	cout << "MODULUS ASSIGNMENT\n"<<"The result of getting the remainder of the two value is  "<<e <<endl<<endl;
	
	
	system("pause");
	return 0;
}
