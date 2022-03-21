//
//Program accept 3 values
//Getting the Sum and Product
//Determine the largest value and identify if it is odd or even number
//

#include <iostream>
#include <cstdlib>
#include <cstdio>

using namespace std;

int main (int nNameofArgs, char * pszArgs[]){
	
	//declaring the variables
	int fnumber, snumber, tnumber, sum, product;
	
	//Input the value of the three number 
	cout << "Enter the First Number: ";
	cin >> fnumber;
	cout << "Enter the Second Number: ";
	cin >> snumber;
	cout << "Enter the Third Number: ";
	cin >> tnumber;
	
	cout<<"\n\n.......................................................................";
	//Adding solution and print the result
	sum = fnumber + snumber + tnumber;
	cout << "\nThe sum of the three number is: "<<sum<<endl;
	
	//Product solution and print the result
	product= fnumber * snumber * tnumber;
	cout << "\nThe product of the three number is: "<<product<<endl;
	
	//identifying the Largest number
	if (fnumber >= snumber){
		if(fnumber >= tnumber){
			cout <<"\nThe Largest number is "<<fnumber<<endl;
			if(fnumber % 2 == 0){
   				cout<<endl<<fnumber<<" is an even"<<endl<<endl;
   			}
   			else{
   				cout<<endl<<fnumber<<" is an odd"<<endl<<endl;
			}
		}
	}
	else if (snumber >= tnumber){
		if(snumber >= fnumber){
			cout <<"\nThe Largest number is "<<snumber<<endl;
			if(snumber % 2 == 0){
   				cout<<endl<<snumber<<" is an even"<<endl<<endl;
   			}
   			else{
   				cout<<endl<<snumber<<" is an odd"<<endl<<endl;
			}
		}
	}
	else if (tnumber >= fnumber){
		if(tnumber >= snumber){
			cout <<"\nThe Largest number is "<<tnumber<<endl;
			if(tnumber % 2 == 0){
   				cout<<endl<<tnumber<<" is an even"<<endl<<endl;
   			}
   			else{
   				cout<<endl<<tnumber<<" is an odd"<<endl<<endl;
			}
		}
	}
	
	system("pause");
	return 0;
	
	
}
