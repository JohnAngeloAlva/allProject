//
//Program that convert English Alphabet to Japanese Alphabet
//

#include <iostream>
#include<cstdlib>
#include<cstdio>
#include<string.h>
#include<cctype>

using namespace std;

//declaring the constant array string 
const string japAlpha[] = {"KA","ZU","Mi","TE","KU","LU","Ji","Ri","Ki","ZU","ME","TA","RiN","TO","MO","NO","KE","SHi","ARi","CHi","DO","RU","MEi","NA","FU","YA"," "};
const string engAlpha[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"," "};

//declaring the class and method and state the access-specifier to public
class MyJapaneseName{
	public:
		void myJapaneseName();

};

//declare the class method
void MyJapaneseName :: myJapaneseName(){
	
	//declare the varible string and used getline to get the space of the values
	string name;
	cout << "Enter your English Name: ";
	getline(cin,name);
	
	//declare the array charname and set the size according to the length of name 
	int sizeofname;
	while (name[sizeofname] != '\0'){
		sizeofname++;
   	}
   	char charname[sizeofname];
   	
   	//declare the int variable for loop and string for the japName 
	int sizeofengAlpha,i,e;
	string japName;
	
	//declare the output
	cout <<"\nYour Japanese Name is: ";
	
	//Loop the name according to the size, set the inputted value to upper case
	//pass the value of name to charname 
	for(i=0;i<sizeofname;i++){
		name[i] = toupper(name[i]);
		charname[i] = name[i];
		
		//convert the constant string to constant char and used a pointer character
		const char *engAlphaChar = engAlpha[e].c_str();
		
		//Count the length of string engAlpha
		while (engAlphaChar[sizeofengAlpha] != '\0'){
				sizeofengAlpha++;
   		}
   		
			//loop according to the size of engAlpha
			for(e=0;e<sizeofengAlpha;e++){
				
				//Used a decision statement to charname for the inputted character
				//Once the decision is true get the index value of japAlpha and pass it to japName
				//Once the value was found break the loop and continue to find the value  
				if(charname[i] == 'A'){
					japName = japAlpha[0];
					cout<< japName;
					break;
					continue; 
				}
				if(charname[i] == 'B'){
					japName = japAlpha[1];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'C'){
					japName = japAlpha[2];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'D'){
					japName = japAlpha[3];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'E'){
					japName = japAlpha[4];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'F'){
					japName = japAlpha[5];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'G'){
					japName = japAlpha[6];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'H'){
					japName = japAlpha[7];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'I'){
					japName = japAlpha[8];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'J'){
					japName = japAlpha[9];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'K'){
					japName = japAlpha[10];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'L'){
					japName = japAlpha[11];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'M'){
					japName = japAlpha[12];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'N'){
					japName = japAlpha[13];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'O'){
					japName = japAlpha[14];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'P'){
					japName = japAlpha[15];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'Q'){
					japName = japAlpha[16];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'R'){
					japName = japAlpha[17];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'S'){
					japName = japAlpha[18];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'T'){
					japName = japAlpha[19];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'U'){
					japName = japAlpha[20];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'V'){
					japName = japAlpha[21];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'W'){
					japName = japAlpha[22];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'X'){
					japName = japAlpha[23];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'Y'){
					japName = japAlpha[24];
					cout<< japName;
					break;
					continue;
				}
				if(charname[i] == 'Z'){
					japName = japAlpha[25];
					cout<< japName;
					break;
					continue;
			}
		}
	}
}


int main(int nNameofArgs, char * psz[]){
	
	//declare the variable for the class MyJapaneseName
	MyJapaneseName myObj; 
	
	//Calling the method of the class
	myObj.myJapaneseName();
	return 0;
	
}
 
