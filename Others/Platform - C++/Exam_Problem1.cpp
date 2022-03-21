//
//Write a Little Man program that accepts three values as input and produces the largest of the three as output
//


#include <iostream>
#include <cstdlib>
#include <cstdio>

using namespace std;

int main(int nNameofArgs, char * pszArgs[]){
	
	//declaring variables
	int number1, number2, number3;
	
	//Input the three variable
	cout <<"Enter number 1: ";
	cin >>number1;
	cout <<"Enter number 2: ";
	cin >>number2;
	cout <<"Enter number 3: ";
	cin >>number3;
	
	//Decisioning if the value of number 1 is the largest number
	if (number1>=number2){
		if (number1>= number3){
			cout << "\n\nThe largest number is: "<<number1<<endl<<endl;
		}
	}
	
	//Decisioning if the value of number 2 is the largest number
	else if (number2 >= number3){
		if (number2 >= number1){
			cout << "\n\nThe largest number is: "<<number2<<endl<<endl;
		}
	}
	
	//Decisioning if the value of number 3 is the largest number
	else{
		if (number3 >= number1){
			if (number3>=number2){
			cout << "\n\nThe largest number is: "<<number3<<endl<<endl;
			}
		}
	}
	
	system("PAUSE");
	return 0;
	
}
