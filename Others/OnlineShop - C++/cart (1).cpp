#include <iostream>
#include <fstream>
#include <string>
#include <cstdio>
#include <cstdlib>
#include <stdlib.h>
using namespace std;

// class to show product list and data of account
class UserProduct{
	public:
		int item(int product){
			int choice;
			int buyOption;
				
				// conditional statement for the product
				switch(product){
					case 1:{
						// for opening and reading the text file
						ifstream file("Bench.txt");
						// looping for the end of the file and print it
						while(!file.eof()){
							getline(file, temp);
							cout << temp << endl;
						}
						// to close the file
						file.close();
					break;
					}
					case 2:{
						ifstream file("Penshoppe.txt");
						while(!file.eof()){
							getline(file, temp);
							cout << temp << endl;
						}
						
						file.close();
					break;
					}
					case 3:{
						ifstream file("Levis.txt");
						while(!file.eof()){
							getline(file, temp);
							cout << temp << endl;
						}
						
						file.close();
						break;
					}
					case 4:{
						ifstream file("Macbeth.txt");
						while(!file.eof()){
							getline(file, temp);
							cout << temp << endl;
						}
						
						file.close();
						break;
					}
					case 5:{
						ifstream file("Adidas.txt");
						while(!file.eof()){
							getline(file, temp);
							cout << temp << endl;
						}
						file.close();
						break;
					}
					case 6:{
						system("pause");
						system("cls");
						break;
					}
						
				}
			
		}
		
		// function to show the account
		int data(){
			// for opening and reading the text file
			ifstream file("User-account.txt");
			// looping until the end of file
			while(!file.eof()){
				getline(file, temp);
				cout << temp << endl;
			}
		}
	protected:
		string temp;
		string userName, pass;
		
		
};

// based class for buying the product
class Transaction{
	protected:
		string productCode, temp, clothes;
		int operation;
		int brands;
	public:
	int search(int);
	
	int purchaseItem(int item){
		
		brands = item;
	    
		// conditional for what product you will choose
		if(brands == 1 ){
			cout<<"\n***************************** PRODUCTS OF BENCH **************************************************\n\n";
			
			// for opening and reading the text file
			ifstream file("Bench.txt");
			while(getline(file,clothes)){
					cout << clothes <<endl;
			}
			cout<<"\n";
			file.close();
			
			// conditional for the brand you choose
			if(brands == 1){
				cout<<"\n\t( "<<"Make sure that your product code is the same to the product that you want to purchase"<<" ) \n";
				cout<<"\n***************************** BENCH BRAND **************************************************\n";
				cout<<"Enter Product code to Purchase: ";
				cin>> productCode;
				
				// for opening and reading the text file
				ifstream file ( "Bench.txt" );
				while(!file.eof()){
					
					// for getting the line of the file and store it to the temp
					getline(file, temp);
					
					// conditional statement if the product code is equal to the string of txt file
					// it will print the data
					if(((temp.find(productCode))) != string :: npos){
						system("cls"); 
							cout<<"\n***************************** Product that you want to Purchase**************************************************\n";
							cout <<temp;
							
							// confirmation of the product you want to purchase
							cout<<"\n\n***************************** Confirmation of the product **************************************************\n";
							cout<<"\n\t 1 | Yes""\n\t 2 | No";
							cout<<"\n\n************************************************************************************************";
							
							cout<< "\n\nAre You sure this is the Product that you want to Purchase?: ";
							cin>> operation;
							system("cls");
						while(operation != 2){
							
							// condition to return to the item list
							if(operation == 1){
								system("cls");
								cout<<"\n***************************** Product that you want to Purchased **************************************************\n";
								cout <<temp << endl;
								system("pause");
								system("cls");
								break;
						
							}
							else if(operation == 2){
								system("cls");
								break;
								
							}
						}
					}
				}
				file.close();
			}
		}
		
		
		else if(brands == 2 ){
			cout<<"\n***************************** PRODUCTS OF PENSHOPPE **************************************************\n\n";
			ifstream file("Penshoppe.txt");
			while(getline(file,clothes)){
					cout << clothes <<endl;
			}
			cout<<"\n";
			file.close();	
			
			
			if(brands == 2){
				cout<<"\n\t( "<<"Make sure that your product code is the same to the product that you want to purchase"<<" ) \n";
				cout<<"\n***************************** PENSHOPPE BRAND **************************************************\n";
				cout<<"Enter Product code to Purchase: ";
				cin>> productCode;
				ifstream file ( "Penshoppe.txt" );
				while(!file.eof()){
					getline(file, temp);
					if(((temp.find(productCode))) != string :: npos){
						system("cls"); 
							cout<<"\n***************************** Product that you want to Purchase**************************************************\n";
							cout <<temp;
							cout<<"\n\n***************************** Confirmation of the product **************************************************\n";
							cout<<"\n\t 1 | Yes"
							  "\n\t 2 | No";
							cout<<"\n\n************************************************************************************************";
							cout<< "\n\nAre You sure this is the Product that you want to Purchase?: ";
							cin>> operation;
							system("cls");
						while(true){
							if(operation == 1){
								system("cls");
								cout<<"\n***************************** Product that you want to Purchased **************************************************\n";
								cout <<temp;
						
							}
							else if(operation == 2){
								system("cls");
								break;
								
							}
							continue;
						}
					}
				}
				file.close();
			}
		}
		else if(brands == 3 ){
			cout<<"\n***************************** PRODUCTS OF LEVIS **************************************************\n\n";
			ifstream file("Levis.txt");
			while(getline(file,clothes)){
					cout << clothes <<endl;
			}
			cout<<"\n";
			file.close();	
			
			
			if(brands == 3){
				cout<<"\n\t( "<<"Make sure that your product code is the same to the product that you want to pirchase"<<" ) \n";
				cout<<"\n***************************** LEVIS BRAND **************************************************\n";
				cout<<"Enter Product code to Purchase: ";
				cin>> productCode;
				ifstream file ( "Levis.txt" );
				while(!file.eof()){
					getline(file, temp);
					if(((temp.find(productCode))) != string :: npos){
						system("cls"); 
							cout<<"\n***************************** Product that you want to Purchase**************************************************\n";
							cout <<temp;
							cout<<"\n\n***************************** Confirmation of the product **************************************************\n";
							cout<<"\n\t 1 | Yes"
							  "\n\t 2 | No";
							cout<<"\n\n************************************************************************************************";
							cout<< "\n\nAre You sure this is the Product that you want to Purchase?: ";
							cin>> operation;
							system("cls");
						while(true){
							if(operation == 1){
								system("cls");
								cout<<"\n***************************** Product that you want to Purchased **************************************************\n";
								cout <<temp;
						
							}
							else if(operation == 2){
								system("cls");
								break;
								
							}
							continue;
						}
					}
				}
				file.close();
			}
		}
		
		
		else if(brands == 4 ){
			cout<<"\n***************************** PRODUCTS OF MACBETH **************************************************\n\n";
			ifstream file("Macbeth.txt");
			while(getline(file,clothes)){
					cout << clothes <<endl;
			}
			cout<<"\n";
			file.close();	
			if(brands == 4){
				cout<<"\n\t( "<<"Make sure that your product code is the same to the product that you want to purchase"<<" ) \n";
				cout<<"\n***************************** MACBETH BRAND **************************************************\n";
				cout<<"Enter Product code to Purchase: ";
				cin>> productCode;
				ifstream file ( "Macbeth.txt" );
				while(!file.eof()){
					getline(file, temp);
					if(((temp.find(productCode))) != string :: npos){
						system("cls"); 
							cout<<"\n***************************** Product that you want to Purchase**************************************************\n";
							cout <<temp;
							cout<<"\n\n***************************** Confirmation of the product **************************************************\n";
							cout<<"\n\t 1 | Yes"
							  "\n\t 2 | No";
							cout<<"\n\n************************************************************************************************";
							cout<< "\n\nAre You sure this is the Product that you want to Purchase?: ";
							cin>> operation;
							system("cls");
						while(true){
							if(operation == 1){
								system("cls");
								cout<<"\n***************************** Product that you want to Purchased **************************************************\n";
								cout <<temp;
						
							}
							else if(operation == 2){
								system("cls");
								break;
								
							}
							continue;
						}
					}
				}
				file.close();
			}
		}
		else if(brands == 5 ){
			cout<<"\n***************************** PRODUCTS OF ADIDAS **************************************************\n\n";
			ifstream file("Adidas.txt");
			while(getline(file,clothes)){
					cout << clothes <<endl;
			}
			cout<<"\n";
			file.close();	
			
			
			if(brands == 5){
				cout<<"\n\t( "<<"Make sure that your product code is the same to the product that you want to purchase"<<" ) \n";
				cout<<"\n***************************** ADIDAS BRAND **************************************************\n";
				cout<<"Enter Product code to Purchase: ";
				cin>> productCode;
				ifstream file ( "Adidas.txt" );
				while(!file.eof()){
					getline(file, temp);
					if(((temp.find(productCode))) != string :: npos){
						system("cls"); 
							cout<<"\n***************************** Product that you want to Purchase**************************************************\n";
							cout <<temp;
							cout<<"\n\n***************************** Confirmation of the product **************************************************\n";
							cout<<"\n\t 1 | Yes"
							  "\n\t 2 | No";
							cout<<"\n\n************************************************************************************************";
							cout<< "\n\nAre You sure this is the Product that you want to Purchase?: ";
							cin>> operation;
							system("cls");
						while(true){
							if(operation == 1){
								system("cls");
								cout<<"\n***************************** Product that you want to Purchased **************************************************\n";
								cout <<temp;
						
							}
							else if(operation == 2){
								system("cls");
								break;
								
							}
							continue;
						}
					}
				}
				file.close();
			}
		}
	
	}

 
};

// derived class for the userProduct for login
class Login_User: public UserProduct{
	public:
		int logIn(){
			string temp; 
			while (true){
				cout<<"\nEnter UserName: ";
				cin>>userName;
			
				cout<<"Enter Password: ";
				cin>>pass;
				// for opening and reading the text file
				// and checking if username and pass is equal to the string in txt file
				ifstream file("User-account.txt");
				while(!file.eof()){
					getline(file, temp);
						if(((temp.find(pass))) != string :: npos && ((temp.find(userName))) != string :: npos){
							cout << "Log in Successfully..\n";
							break;
						}
						
						if(((temp.find(userName))) != string :: npos && !((temp.find(pass))) != string :: npos){
							cout << "Wrong Password..\n";
							break;
						}
						if(!((temp.find(userName))) != string :: npos && ((temp.find(pass))) != string :: npos){
							cout << "Wrong Username..\n";
							break;
						}
			}
				file.close();
				if(((temp.find(pass))) != string :: npos && ((temp.find(userName))) != string :: npos){
				break;
				}
			}
		cout<<"\nLog in Successful.....\n\n";
		
		}
}; 

// derived class for the sign up
class SignUp_User: public UserProduct{
	public:
		int signUp(){
			cout<<"\nEnter UserName: ";
			cin>>userName;
			cout<<"Enter Password: ";
			cin>>pass;
			
			// for opening and reading the text file		
			ofstream file("User-account.txt", ios::app);
			file<<"Username: " <<userName<<" "<<"Password: "<<pass<<endl<<"\n";
			file.close();
			cout << "\n\nSign up Successfully..\n\n";
			}
};

// derived class for transaction for search
int Transaction::search(int brandSearch){
	string temp;
	// conditional for wich brand you want to choose
	if(brandSearch == 1){
		cout<<"\n\t( "<<"Make sure that your product code is the same to the product that you want to Search"<<" ) \n";
		cout<<"\n***************************** BENCH BRAND **************************************************\n";
		cout<<"Enter Product code to Purchase: ";
		cin>> productCode;
		
		// to open and read the data or txt file
		ifstream file ( "Bench.txt" );
			while(!file.eof()){
				
			// for getting the line of the file and store it to the temp
			getline(file, temp);
			
			// conditional statement if the product code is equal to the string of txt file
			// it will print the data
			if(((temp.find(productCode))) != string :: npos){
				cout<<"\n***************************** Product Found**************************************************\n";
				cout <<temp;
				cout << endl;
				system("pause");
				system("cls");
			}
		}
				file.close();
		
	}
	
	if(brandSearch == 2){
		cout<<"\n\t( "<<"Make sure that your product code is the same to the product that you want to Search"<<" ) \n";
		cout<<"\n***************************** PENSHOPPE BRAND **************************************************\n";
		cout<<"Enter Product code to Purchase: ";
		cin>> productCode;
		
		ifstream file ( "Penshoppe.txt" );
			while(!file.eof()){
				
			// for getting the line of the file and store it to the temp
			getline(file, temp);
			
			// conditional statement if the product code is equal to the string of txt file
			// it will print the data
			if(((temp.find(productCode))) != string :: npos){
				cout<<"\n***************************** Product Found**************************************************\n";
				cout <<temp;
				cout << endl;
				system("pause");
				system("cls");
			}
			
			}
				file.close();
			
	}
	
	if(brandSearch == 3){
		cout<<"\n\t( "<<"Make sure that your product code is the same to the product that you want to Search"<<" ) \n";
		cout<<"\n***************************** LEVIS BRAND **************************************************\n";
		cout<<"Enter Product code to Purchase: ";
		cin>> productCode;
		
		ifstream file ( "Levis.txt" );
			while(!file.eof()){
				
			// for getting the line of the file and store it to the temp
			getline(file, temp);
			
			// conditional statement if the product code is equal to the string of txt file
			// it will print the data
			if(((temp.find(productCode))) != string :: npos){
				cout<<"\n***************************** Product Found**************************************************\n";
				cout <<temp;
				cout << endl;
				system("pause");
				system("cls");
			}
		}
				file.close();
		
	}
	
	if(brandSearch == 4){
		cout<<"\n\t( "<<"Make sure that your product code is the same to the product that you want to Search"<<" ) \n";
		cout<<"\n***************************** MACBETH BRAND **************************************************\n";
		cout<<"Enter Product code to Purchase: ";
		cin>> productCode;
		
		ifstream file ( "Macbeth.txt" );
			while(!file.eof()){
				
			// for getting the line of the file and store it to the temp
			getline(file, temp);
			
			// conditional statement if the product code is equal to the string of txt file
			// it will print the data
			if(((temp.find(productCode))) != string :: npos){
				cout<<"\n***************************** Product Found**************************************************\n";
				cout <<temp;
				cout << endl;
				system("pause");
				system("cls");
			}
		}
				file.close();
			
	}
	
	if(brandSearch == 5){
		cout<<"\n\t( "<<"Make sure that your product code is the same to the product that you want to Search"<<" ) \n";
		cout<<"\n***************************** ADIDAS BRAND **************************************************\n";
		cout<<"Enter Product code to Purchase: ";
		cin>> productCode;
		
		ifstream file ( "Adidas.txt" );
			while(!file.eof()){
				
			// for getting the line of the file and store it to the temp
			getline(file, temp);
			
			// conditional statement if the product code is equal to the string of txt file
			// it will print the data
			if(((temp.find(productCode))) != string :: npos){
				cout<<"\n***************************** Product Found**************************************************\n";
				cout <<temp;
				cout << endl;
				system("pause");
				system("cls");
			}
		}
				file.close();
			
	}
};
//Based class Seller
class Seller{
	//set the variable to protected specifier
	protected:
		string userName, pass;
	//set the methods to public specifier
	public:
};

//Derived class for the signUp of Seller
class SignUp : public Seller{
	public:
		void signUp();
		void RegisterProduct();
		void AddProduct();
		void ListProduct();
};

//Sighnup definition outside the class 
void SignUp :: signUp(){
	
	cout<<"\nEnter UserName: ";
	cin>>userName;
					
	cout<<"Enter Password: ";
	cin>>pass;
	
	//Using ofstream to access and write to the textfile		
	ofstream file("Accounts.txt", ios::app);
	file<<"Username: " <<userName<<" "<<"Password: "<<pass<<endl<<"\n";
	file.close();
			
	cout << "\n\nLog in Successfully..\n\n";
	system("Pause");
	system("cls");
}
//END OF SIGNUP METHOD

//Register Product definition outside the class 
void SignUp :: RegisterProduct(){
	string seller_name, productname, productcode,productsize, productdetail,productcolor,clothes;
	int brand, product_input,i,productprize;
	
	cout<<"***************************** REGISTER PRODUCT **************************************************\n";
	cout<<"\nHow many product do you want to Add: ";
	cin>>product_input;
	
	//Loop the Inputting values based on the product that you want to add
	for(i=0;i<product_input;i++){
		cout<<"\n***************************** BRAND MENU **************************************************\n";
		
		//Using ifstream to access and read the content of the textfile
		ifstream file("Brand.txt");
		string clothes;
		
		//loop and get all the values by line
		while(getline(file,clothes)){
			cout << clothes <<endl;
		}	
		file.close();
		
		cout<<"\nSelect Brand to Register Product: ";
		cin>>brand;
		
		system("cls");
		
		//conditional statement based on your chosen Brand
		//Bench Brands
		if(brand == 1){
			cout<<"\n***************************** BENCH BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Bench text file	
			ofstream file("Bench.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
		
			cout<<"\n\nYour Product/s is successfully added\n";
			
		}
		//Penshoppe Brand
		else if(brand == 2){
			cout<<"\n***************************** PENSHOPPE BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Penshoppe text file	
			ofstream file("Penshoppe.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
			
		}
		//Levis Brand
		else if(brand == 3){
			cout<<"\n***************************** LEVIS BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Levis text file	
			ofstream file("Levis.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
			
		}
		//Macbeth Brand
		else if(brand == 4){
			cout<<"\n***************************** MACBETH BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Macbeth text file	
			ofstream file("Macbeth.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
			
		}
		//Adidas Brand
		else if(brand == 5){
			cout<<"\n***************************** ADIDAS BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Macbeth text file	
			ofstream file("Adidas.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
		}
	}
	system("Pause");
	system("cls");
}
//END OF REGISTER PRODUCT METHOD

////Add Product definition outside the class 
void SignUp :: AddProduct(){
	string seller_name, productname, productcode, productdetail,productcolor,productsize,clothes;
	int brand, product_input,i,productprize;
	
	cout<<"***************************** ADD PRODUCT **************************************************\n";
	cout<<"\nHow many product do you want to Add: ";
	cin>>product_input;
	
	//Loop the Inputting values based on the product that you want to add
	for(i=0;i<product_input;i++){
		
		cout<<"\n***************************** BRAND MENU **************************************************\n";
		//Using ifstream to access and read the content of the textfile
		ifstream file("Brand.txt");
		string clothes;
		
		//loop and get all the values by line
		while(getline(file,clothes)){
			cout << clothes <<endl;
		}	
		file.close();
		
		cout<<"\nSelect Brand to ADD Product: ";
		cin>>brand;
		
		system("cls");
		
		//conditional statement based on your chosen Brand
		//Bench Brands
		if(brand == 1){
			cout<<"\n***************************** BENCH BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Bench text file	
			ofstream file("Bench.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
		
			cout<<"\n\nYour Product/s is successfully added\n";
			
		}
		//Penshoppe Brand
		else if(brand == 2){
			cout<<"\n***************************** PENSHOPPE BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Penshoppe text file	
			ofstream file("Penshoppe.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
			
		}
		//Levis Brand
		else if(brand == 3){
			cout<<"\n***************************** LEVIS BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Levis text file	
			ofstream file("Levis.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
			
		}
		//Macbeth Brand
		else if(brand == 4){
			cout<<"\n***************************** MACBETH BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Macbeth text file	
			ofstream file("Macbeth.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
			
		}
		//Adidas Brand
		else if(brand == 5){
			cout<<"\n***************************** ADIDAS BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Macbeth text file	
			ofstream file("Adidas.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
		}
	}
	system("Pause");
	system("cls");
}
//END OF REGISTER PRODUCT METHOD

//List Product definition outside the class 
void SignUp :: ListProduct(){
	string clothes;
	int brands,list_operation;
	
	//loop the selection using Infinite loop
		while(true){
			cout<<"***************************** LIST OF PRODUCTS **************************************************\n";
			cout<<"\n\t 1 | Select Brand"
			  "\n\t 2 | Exit Program";
			cout<<"\n\n************************************************************************************************";
		
			cout<<"\nSelect operation: ";
			cin>>list_operation;
			
			//Conditonal statement based on the your chosen operation
			if (list_operation == 1){
				cout<<"\n***************************** BRAND MENU **************************************************\n";
				
				//using ifstream access and read the brand textfile
				ifstream file("Brand.txt");
				
				//loop and get all the values by line
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}	
				file.close();
				
				cout<<"\nSelect Brand to View all the Products: ";
				cin>>brands;

				system("cls");
				
				//Conditonal statement based on the your chosen Brand to view the products
				//Product of Bench
				if(brands == 1){
					cout<<"\n***************************** PRODUCTS OF BENCH **************************************************\n\n";
					
					//using ifstream access and read the textfile
					ifstream file("Bench.txt");
					
					//loop and get all the values by line in Bench texfile
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}
					cout<<"\n";
					file.close();	
				}
				//Product of Penshoppe
				else if(brands == 2){
					cout<<"\n***************************** PRODUCTS OF PENSHOPPE **************************************************\n\n";
					//using ifstream access and read the textfile
					ifstream file("Penshoppe.txt");
					
					//loop and get all the values by line in Penshoppe texfile
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}
					cout<<"\n";
					file.close();	
				}
				//Products if Levis
				else if(brands == 3){
					cout<<"\n***************************** PRODUCTS OF LEVIS **************************************************\n\n";
					//using ifstream access and read the textfile
					ifstream file("Levis.txt");
					
					//loop and get all the values by line in Levis texfile
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}
					cout<<"\n";
					file.close();	
				}
				//Product of Macbeth
				else if(brands == 4){
					cout<<"\n***************************** PRODUCTS OF MACBETH **************************************************\n\n";
					
					//using ifstream access and read the textfile
					ifstream file("Macbeth.txt");
					
					//loop and get all the values by line in Macbeth texfile
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}
					cout<<"\n";
					file.close();	
				}
				//Products  of Adidas
				else if(brands == 5){
					cout<<"\n***************************** PRODUCTS OF ADIDAS **************************************************\n\n";
					
					//using ifstream access and read the textfile
					ifstream file("Adidas.txt");
					
					//loop and get all the values by line in Macbeth texfile
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}
					cout<<"\n";
					file.close();	
				}
			system("Pause");
			system("cls");
			}
			//Exit the List Program
			else if (list_operation == 2){
				break;
				system("Pause");
				system("cls");
			}
		}
	system("cls");	
}
//END Of List Products

// END OF SIGNUP DERIVED CLASS

//START OF LOGIN DERIVED CLASS
class Login : public Seller{
	public:
		void login();
		void AddProduct();
		void ListProduct();	
};

//Login definition outside the class 
void Login :: login(){
	
	string temp;
	
	//Using Infinite loop for logging in 
	while (true){
		cout<<"\nEnter UserName: ";
		cin>>userName;
			
		cout<<"Enter Password: ";
		cin>>pass;
		
		//ifstream to access and read the account textfile
		ifstream file("Accounts.txt");
		while(!file.eof()){
			//getting the values by line
			getline(file, temp);
			
			//conditional statement if the inputted username and password line is match to the line of textfile  
			if(((temp.find(pass))) != string :: npos && ((temp.find(userName))) != string :: npos){
				cout << "Log in Successfully..\n";
				break;
			}
			//if username is correct and password is wrong
			if(((temp.find(userName))) != string :: npos && !((temp.find(pass))) != string :: npos){
				cout << "Wrong Password..\n";
				break;
			}
			//if username is wrong and password is correct
			if(!((temp.find(userName))) != string :: npos && ((temp.find(pass))) != string :: npos){
				cout << "Wrong Username..\n";
				break;
			}
		}
		file.close();
		//if the conditon is true break the loop;
		if(((temp.find(pass))) != string :: npos && ((temp.find(userName))) != string :: npos){
			break;
		}
	}
	cout<<"\nProceed to MENU.....\n\n";
	system("Pause");
	system("cls");		
}
//End of login Method

//Add Product definition outside the class 
void Login :: AddProduct(){
	string seller_name, productname, productcode, productdetail,productcolor,productsize,clothes;
	int brand, product_input,i,productprize;
	
	cout<<"***************************** ADD PRODUCT **************************************************\n";
	cout<<"\nHow many product do you want to Add: ";
	cin>>product_input;
	
	//Loop the Inputting values based on the product that you want to add
	for(i=0;i<product_input;i++){
		
		cout<<"\n***************************** BRAND MENU **************************************************\n";
		//Using ifstream to access and read the content of the textfile
		ifstream file("Brand.txt");
		string clothes;
		
		//loop and get all the values by line
		while(getline(file,clothes)){
			cout << clothes <<endl;
		}	
		file.close();
		
		cout<<"\nSelect Brand to ADD Product: ";
		cin>>brand;
		
		system("cls");
		
		//conditional statement based on your chosen Brand
		//Bench Brands
		if(brand == 1){
			cout<<"\n***************************** BENCH BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Bench text file	
			ofstream file("Bench.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
		
			cout<<"\n\nYour Product/s is successfully added\n";
		}
		//Penshoppe Brand
		else if(brand == 2){
			cout<<"\n***************************** PENSHOPPE BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Penshoppe text file	
			ofstream file("Penshoppe.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
		}
		//Levis Brand
		else if(brand == 3){
			cout<<"\n***************************** LEVIS BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Levis text file	
			ofstream file("Levis.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
		}
		//Macbeth Brand
		else if(brand == 4){
			cout<<"\n***************************** MACBETH BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Macbeth text file	
			ofstream file("Macbeth.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
		}
		//Adidas Brand
		else if(brand == 5){
			cout<<"\n***************************** ADIDAS BRAND **************************************************\n";
			cout<<"\nEnter Seller Name: ";
			cin.ignore();
			getline(cin, seller_name);
			
			cout<<"Enter Product Code (Abbreviation of Brand): ";
			cin>>productcode;
			
			cout<<"Enter Product Name: ";
			cin.ignore();
			getline(cin, productname);
			
			cout<<"Enter Product Color: ";
			cin.ignore();
			getline(cin, productcolor);
			
			cout<<"Enter Product Size: ";
			cin>>productsize;
			
			cout<<"Enter Product Details: ";
			cin.ignore();
			getline(cin, productdetail);
			
			cout<<"Enter Product Price: ";
			cin>>productprize;
			
			//Using ofstream to access and write one line of values to the Adidas text file	
			ofstream file("Adidas.txt", ios::app);
			file<<"\nSeller Name: " <<seller_name<<" "<<"Product Code: ("<<productcode<<")  "<<"Product Name: "<<productname<<" " <<"Product Color: "<<productcolor<<" "<<"Product Details: " <<productdetail<<" "<<"Product Prize: " <<productprize<<"\n";
			file.close();
			
			cout<<"\n\nYour Product/s is successfully added\n";
		}
	}
	system("Pause");
	system("cls");
}
//End of Add Product

//List Product definition outside the class 
void Login:: ListProduct(){
	string clothes;
	int brands,list_operation;
	
	//loop the selection using Infinite loop
		while(true){
			cout<<"***************************** List OF PRODUCTS **************************************************\n";
			cout<<"\n\t 1 | Select Brand"
			  "\n\t 2 | Exit Program";
			cout<<"\n\n************************************************************************************************";
		
			cout<<"\nSelect operation: ";
			cin>>list_operation;
	
	//Conditonal statement based on the your chosen operation	
			if (list_operation == 1){
				cout<<"\n***************************** BRAND MENU **************************************************\n";
				ifstream file("Brand.txt");
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}	
				file.close();
				
				cout<<"\nSelect Brand to View all the Products: ";
				cin>>brands;

				system("cls");
			//Conditonal statement based on the your chosen operation	
				if(brands == 1){
					cout<<"\n***************************** PRODUCTS OF BENCH **************************************************\n\n";
					//using ifstream access and read the textfile
					ifstream file("Bench.txt");
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}
					cout<<"\n";
					file.close();	
				}
				else if(brands == 2){
					cout<<"\n***************************** PRODUCTS OF PENSHOPPE **************************************************\n\n";
					//using ifstream access and read the textfile
					ifstream file("Penshoppe.txt");
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}
					cout<<"\n";
					file.close();	
				}
				else if(brands == 3){
					cout<<"\n***************************** PRODUCTS OF LEVIS **************************************************\n\n";
					//using ifstream access and read the textfile
					ifstream file("Levis.txt");
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}
					cout<<"\n";
					file.close();	
				}
				else if(brands == 4){
					cout<<"\n***************************** PRODUCTS OF MACBETH **************************************************\n\n";
					//using ifstream access and read the textfile
					ifstream file("Macbeth.txt");
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}
					cout<<"\n";
					file.close();	
				}
				else if(brands == 5){
					cout<<"\n***************************** PRODUCTS OF ADIDAS **************************************************\n\n";
					//using ifstream access and read the textfile
					ifstream file("Adidas.txt");
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}
					cout<<"\n";
					file.close();	
				}
			system("Pause");
			system("cls");
			}
			else if (list_operation == 2){
				break;
				system("Pause");
				system("cls");
			}
		}
	system("cls");
}
//End of List Product




// start of main
int main(){
	int account;
	
	
	
	do{
	cout << "***\n";
	cout << "    *\n";
	cout << "     **********************\n";
	cout << "      *                  *\n";
	cout << "       *   ECloth-Shop  *\n";
	cout << "        *              *\n";
	cout << "         **************\n";
	cout << "          *\n";
	cout << "           **********\n";
	cout << "           **      **\n";
	cout << "************************\n";
	cout << "   1 - Seller Account   \n";
	cout << "   2 - User Account     \n";
	cout << "   3 - Exit Program     \n";
	cout << "************************\n\n";
	cout << "What type of account you want: ";
	cin >> account;
	cout << endl;
	system("pause");
	system("cls");
// start of seller
	if (account == 1){
		
	SignUp signup;
	Login login;
	int operation, signUpMenu_operation, logInMenu_operation;
	while(true){
	
		cout<<"***************************** ACCOUNT MENU **************************************************\n";
		cout<<"\n\t 1 | Sign Up Account"
		"\n\t 2 | Login Account"
		"\n\t 3 | Exit Program";
		cout<<"\n\n************************************************************************************************";
			
		cout<<"\nEnter operation: ";
		cin>>operation;
		
		//Conditional statement for the operation
		
		//Accesing the Signup Derived Class
		if (operation == 1 ){
			signup.signUp();
			signup.RegisterProduct();
			while (true){
				cout<<"***************************** SELLER MENU **************************************************\n";
				cout<<"\n\t 1 | Add Product"
					  "\n\t 2 | List of Products"
				  	  "\n\t 3 | Logout";
				cout<<"\n\n************************************************************************************************";
				cout<<"\nEnter Operation: ";
				cin>>signUpMenu_operation;
				
				system("cls");
				
				if (signUpMenu_operation == 1){
					signup.AddProduct();
				}
				else if (signUpMenu_operation == 2){
					signup.ListProduct();
				}
				//logging out
				else if (signUpMenu_operation == 3){
					cout<<"\n\n\tThank you for being a seller of our Business\n\n\n";
					system("Pause");
					system("cls");
					break;
				}
			}
		}
		//Accesing the Signup Derived Class
		else if (operation == 2 ){
			login.login();
			while(true){
				cout<<"***************************** SELLER MENU **************************************************\n";
				cout<<"\n\t 1 | Add Product"
					  "\n\t 2 | List of Products"
				  	  "\n\t 3 | Logout";
				cout<<"\n\n************************************************************************************************";
				cout<<"\nEnter Operation: ";
				cin>>logInMenu_operation;
				
				system("cls");
				
				if (logInMenu_operation == 1){
					login.AddProduct();
				}
				else if (logInMenu_operation == 2){
					login.ListProduct();
				}
				//logging out
				else if (logInMenu_operation == 3){
					cout<<"\n\n\tThank you for being a seller of our Business\n\n\n";
					system("Pause");
					system("cls");
					break;
				}
			}
		}
		else if (operation == 3 ){
			cout<<"\n\n\tThank you for being a seller of our Business\n\n\n";
			system("Pause");
			system("cls");
			break;
		}
	}
}
// end of seller



// start of user
	else if(account == 2){
		
	int itemChoice, prod;
	int menu;
	Login_User loginAccount;
	UserProduct producList;
	SignUp_User signUpAccount[5];
	Transaction itemReceipt;
	do{
	
	
	cout << " *****************************\n";
	cout << "       1 - List Item        \n";
	cout << "       2 - Log in/Sign Up   \n";
	cout << "       3 - Show Account     \n";
	cout << "       4 - Exit             \n";
	cout << " *****************************\n";
	
	cout << " Enter your Choice: ";
	cin >> menu;
	system("cls");
	// conditional statement for menu
	switch (menu){
		
		// to show item
		case 1:
			do{
			
			cout << "*****************************\n";
			cout << "         List Item         \n";
			cout << "                           \n";
			cout << "     1 - Brand of Item     \n";
			cout << "     2 - Buy of Item       \n";
			cout << "     3 - Search Item       \n";
			cout << "     4 - Exit              \n";
			cout << "*****************************\n\n";
			cout << "Enter your choice: ";
			cin >> itemChoice;
			
			
			if (itemChoice == 1){
				do{
					ifstream file("Brand.txt");
					string clothes;
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}	
					file.close();
		
					cout<<"\nEnter Brand to display product: ";
					cin >> prod;
					producList.item(prod);
					system("pause");
					system("cls");
				}while(prod!=6);
			}
			else if (itemChoice == 2){
				int itemBrand;
				string itemID;
				ifstream file("Brand.txt");
					string clothes;
					while(getline(file,clothes)){
						cout << clothes <<endl;
					}	
					file.close();
					cout << "Enter what Brand you want: ";
					cin >> itemBrand;
					itemReceipt.purchaseItem(itemBrand);
					
			}
			else if(itemChoice == 3){
				int brandSearch;
				ifstream file("Brand.txt");
				string clothes;
				while(getline(file,clothes)){
				cout << clothes <<endl;
				}	
				file.close();
				cout << "Which brand you want to search: ";
				cin >> brandSearch;
				itemReceipt.search(brandSearch);
			}
			else if (itemChoice == 4){
				cout << endl;
				system("pause");
				system("cls");
			}
			
		}while(itemChoice != 4);
			break;
		
		// to log in
		case 2:
			int accChoice;
			do{
		
			cout << "*****************************\n";
			cout << "                           \n";
			cout << "         Account           \n";
			cout << "                           \n";
			cout << "     1 - Login             \n";
			cout << "     2 - Sign/up           \n";
			cout << "     3 - Exit              \n";
			cout << "*****************************\n\n";
			cout << "Enter choice: ";
			cin >> accChoice;
			if (accChoice == 1){
				
				loginAccount.logIn();
				cout << endl;
				system("pause");
				system("cls");
				
			}
			else if (accChoice == 2){
				int account;
				cout << "Enter how many account (maximum of 5): ";
				cin >> account;
				for(int i =0 ; i < account; i++){
					signUpAccount[i].signUp();
				}
				
				cout << endl;
				system("pause");
				system("cls");
			}
			
			else if (accChoice == 3){
				system("pause");
				system("cls");
				break;
			}
			else if(accChoice >3 && accChoice < 0){
				cout << "Wront Input please try again..";
				system("cls");
			}
			}while(accChoice != 3);
			
			break;
		
			
		// to show account
		case 3:
			producList.data();
			system("PAUSE");
			system("CLS");
			break;
			
		// to exit
		case 4:
			cout << "Thank for using our program as a Customer\n";
			system("pause");
			system("cls");
			break;
		default:
			cout << "Wrong Number";
	}

	}while(menu != 4);
	}
	else if(account == 3){
		cout << endl << endl;
		cout << "********************************************************\n";
		cout << "*                                                      *\n";
		cout << "*        Thank you for using our program.              *\n";
		cout << "*  BY: JOHN ANGELO ALVA || XENA PERICAS || CJAE LIMOS  *\n";
		cout << "*                                                      *\n";
		cout << "********************************************************\n";
}
 }while(account != 3);
	
}
