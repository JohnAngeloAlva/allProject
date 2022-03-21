//
//Apply the Object-Oriented concept of C++ in this program 
//Basic ATM Program
//OpenAccount, ShowAccount, SerchAccount, Deposit into Account, Withdraw in the Account

#include <iostream>
#include <cstdio>
#include <cstdlib>

using namespace std;

// Declaring the class bank
class Bank{
	//set the variable to private specifier
	private:
		int numbers, bals,i;
		string names;
	// set the methods into public specifier
	public:
		
		//Declaring All the methods and Parameters
		void OpenAccount(int number, string name, int bal);
		void ShowAccount();
		int SearchAccount(int search);
		void Deposit();
		void Withdraw();
		
};
//OpenAccount definition outside the class
void Bank :: OpenAccount(int number, string name, int bal){
	//passing all the value that accepted in the main function
	numbers = number;
	names = name;
	bals = bal;
}
//ShowAccount definition outside the class
void Bank :: ShowAccount(){
	//printing all the values 
	cout << "Number: " <<numbers<< endl;
    cout << "Name: " << names<< endl;
    cout << "Balance: " << bals<< endl;
    cout << "\n";
}
//Derive the search method to return the result
int Bank :: SearchAccount(int search){
	//if the values are attained in the showAccount method return and print the value
	if(numbers == search){
		ShowAccount();
		//returning 1 value
		return(1);
		}
	return (0);
}
//Deposit definition outside the class
void Bank :: Deposit(){
	//Declare variable
	int amount;
	//Enter the Deposit amount into searched account
	cout<<"Enter Amount that you want to deposit: ";
	cin >> amount;
	
	//Add the amount value in the remaining balance
	bals+=amount;
	cout<<"\n*******************************************************************************";
	cout<<"\n\t\tYOUR AMOUNT IS SUCCESSFULLY DEPOSITED";
	cout<<"\n*******************************************************************************\n";
}
//Wthdraw definition outside the class
void Bank :: Withdraw(){
	//Declare variable
	int amount;
	//Enter the Withdraw amount into searched account
	cout<<"Enter Amount that you want to withdraw: ";
	cin >> amount;
	
	//Using Decision statement if the amount is greater than in balance
	if(amount>bals){
		cout<<"\n*******************************************************************************";
		cout<<"\n\t\tYOU DON'T HAVE INSUFFICIENT BAlANCE";
		cout<<"\n*******************************************************************************\n";
	}
	else{
		//Minus the inputted amount in the balance
		bals-=amount;	
		cout<<"\n*******************************************************************************";
		cout<<"\n\t\tYOUR AMOUNT IS SUCCESSFULLY WITHDROWED";
		cout<<"\n*******************************************************************************\n";
	}
}


int main (int nNameofArgs, char * pzs[]){
	//Create an Array object of the bank class 
	Bank bank[3];
	//declaring the variables
	int number, bal,i,search,locate;
	string name;
	int chosen;
	
	cout<<"\tENTER ACCOUNT\n\n";
	
	//Enter the values in 3 loop
	for(i=0; i<3; i++){
		cout << "Enter Number: ";
		cin >> number;
		cout << "Enter Name: ";
		cin >> name;
		cout << "Enter Balance: ";
		cin >> bal;
		cout << "\n";
		//store the values in the array object and pass into the OpenAccount method
		bank[i].OpenAccount(number,name,bal);
	}
		system("pause");
		system("cls");

	//loop the Operations and terminate the program when the user enter 5
	while (true){
		cout<<"*******************************************************************************";
		cout<<"\n\t 1 | DISPLAY ALL ACCOUNT"
		  "\n\t 2 | SHOW ACCOUNT BY ACCOUNT NUMBER"
          "\n\t 3 | DEPOSIT"
          "\n\t 4 | WITHDRAW"
		  "\n\t 5 | EXIT";
		cout<<"\n*******************************************************************************";
	
		cout<<"\nChoose Operation: ";
		cin >> chosen;
		cout<<"\n";
		
		//using decision statement for the operations
		if(chosen == 1){
			//loop all the values and access the SchowAccount method
			for(i=0; i<3; i++){
				bank[i].ShowAccount();
			}
			system("pause");
			system("cls");
		}
		else if(chosen == 2){
			//Enter the value to search
			cout <<"Enter Account Number to Search: ";
			cin >> search;
			
			for(i=0; i<3; i++){
				//Locating the value that has been serched in the method and pass to the variable
				locate = bank[i].SearchAccount(search);
				//if the searched value is attained break the serching function
				if (locate) {
                    break;
            	}
			}
			if(!locate){
                	cout<<"\n*******************************************************************************";
					cout<<"\n\t\tRECORD NOT FOUND";
					cout<<"\n*******************************************************************************\n";
       		}
			system("pause");
			system("cls");
		}
		else if (chosen == 3){
			//Enter the value to search
			cout <<"Enter Account Number to Deposit: ";
			cin >> search;
			
			for(i=0; i<3; i++){
				//Locating the value that has been serched in the method and pass to the variable
				locate = bank[i].SearchAccount(search);
				//if the searched value is attained access the Deposit method to do the task of depositing amount
				if (locate) {
					bank[i].Deposit();
                    break;
            	}
			}
			if(!locate){
                	cout<<"\n*******************************************************************************";
					cout<<"\n\t\tRECORD NOT FOUND";
					cout<<"\n*******************************************************************************\n";
       		}
			system("pause");
			system("cls");
		}
		else if (chosen == 4){
			//Enter the value to search
			cout <<"Enter Account Number to Withdraw: ";
			cin >> search;
			
			//Locating the value that has been serched in the method and pass to the variable	
			for(i=0; i<3; i++){
				locate = bank[i].SearchAccount(search);
				//if the searched value is attained access the Withdraw method to do the task of withrowing amount
				if (locate) {
					bank[i].Withdraw();
	                break;
	            }
			}
			if(!locate){
	            cout<<"\n*******************************************************************************";
				cout<<"\n\t\tRECORD NOT FOUND";
				cout<<"\n*******************************************************************************\n";
	       	}
			system("pause");
			system("cls");
		}
		//if the user enters 5 the program will be terminated
		else if(chosen== 5){
			cout<<"\n*******************************************************************************";
			cout<<"\n\t\tTHANK YOU";
			cout<<"\n*******************************************************************************\n";
			break;
			system("pause");
		}
		//if the user enters wrong operation number print and back to the selections
		else{
			cout<<"WRONG INPUT!! \nPLEASE TRY AGAIN\n\n";
			system("pause");
			system("cls");
		}
	}
	return(0);
}
