//
//Different types of Operators in C++
//
// Arithmetic Opetor perform a mathematical operations
// (+, -, *, /, %, ++, --)

#include <iostream>
using namespace std;

#define A 10
#define B 5

int main(){
	
	//declare the variable
	int total;
	
	//Addition (+)
	//The plus operator Adds the two value of the variables
	total = A + B;
	cout << "ADDITION\n"<<"The result of "<<A << " + " <<B << " is "<<total <<endl<<endl;
	
	//Subtraction (-)
	//The subtract operator minus the two value of the variables
	total = A - B;
	cout << "SUBTRACTION\n"<<"The result of "<<A << " - " <<B << " is "<<total <<endl<<endl;
	
	//Multiplication (*)
	//The multiplication operator mumltiply the two value of the variables
	total = A * B;
	cout << "MULTIPLICATION\n"<<"The result of "<<A << " * " <<B << " is "<<total <<endl<<endl;
	
	//Division (/)
	//The divide operator divides the two value of the variables
	total = A / B;
	cout << "DIVISION\n"<<"The result of "<<A << " / " <<B << " is "<<total <<endl<<endl;
	
	//Modulus (%)
	//The modulus operator get the division remainder of the two value of the variables
	total = A % B;
	cout << "MODULUS\n"<<"The total of "<<A << " % " <<B << " is "<<total <<endl<<endl;
	
	//Increment (++)
	//The increment operator create a plus one value to the value of the variables
	int a = 6;
	total = ++a;
	cout << "INCREMENT\n"<<"The increment result of the value is  "<<total<<endl<<endl;
	
	//Decrement (--)
	//The decrement operator create a minus one value to the value of the variables
	int b = 6;
	total = --b;
	cout << "DECREMENT\n"<<"The decremetn result of the value is  "<<total<<endl<<endl;
	
	
	system("pause");
	return 0;
}

