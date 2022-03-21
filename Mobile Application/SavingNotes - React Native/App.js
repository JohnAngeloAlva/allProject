import React, { Component } from 'react';
import { View,Text,TextInput,StyleSheet, Modal, Button, FlatList, TouchableOpacity, ScrollView} from 'react-native';
import { openDatabase } from "react-native-sqlite-storage";

const db = openDatabase({
    name: 'DBTask5',
    location: 'default'
  })

export default class SemiFinalActivity2 extends Component {

    state={
        setModalOpen: false,
        todoList: "",
        date: "",
        todoListArray: [],
       
      }

      componentDidMount = () => {
        this.createTable();
        this.getItems();
      }

      //Creating Table 
      createTable = () => {
        db.transaction(txn => {
          txn.executeSql(
            'CREATE TABLE IF NOT EXISTS tblListTask (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255), date VARCHAR(255) )',
            [],
            (sqlTxn, res) => {
              console.log("Table Successfully created");
            },
            error => {
              console.log("An error occured" + error.message);
            }
          );
        });
      }


      //Returning the List from the Table 
      getItems = () => {
        db.transaction(txn => {
          txn.executeSql(
            'SELECT * FROM tblListTask',
            [],
            (sqlTxn, res ) =>  {
    
              let len = res.rows.length;
    
              if (len > 0) {
                let results = [];
                for(let i = 0; i < len; i++ ) {
                  let list = res.rows.item(i);
                  results.push({key: list.id, name: list.name, date: list.date});
                }
    
                this.setState({
                    todoListArray: results
                });
    
              }
    
            },
            error => {
              console.log("An error occured" + error.message);
            }
          );
        });
      }

      //Adding Item to the List
      addItem = () => {
        if (!this.state.todoList) {
          alert("Please enter Note");
          return false;
        }
        if (!this.state.date) {
            alert("Please enter date");
            return false;
          }
    
        db.transaction(txn => {
          txn.executeSql(
            'INSERT INTO tblListTask (name, date) VALUES (?,?) ',
            [this.state.todoList, this.state.date],
            (sqlTxn, res) => {
              this.getItems();
              alert("Successfully Added Notes");
              this.setState({
                todoList: '',
                date:''
              })
            },
            error => {
              console.log("An error occured" + error.message);
            }
          );
        });
    
      }
    
    render(){
        return(
            
            <View style = { styles.mainContainer }>
                
                {/* Start Modal Add Notes*/}

                <Modal visible={this.state.setModalOpen} animationType = "slide">
                    <View style = { styles.modalContainer }>
                        
                        <Text style={ styles.ListName } >ADD NOTE</Text>   
                        <TextInput 
                            placeholder = 'Add to do List'
                            style = { styles.inputStyle }
                            onChangeText = {
                            txtList => this.setState({
                                todoList: txtList
                            })
                            }
                        />
                        <TextInput 
                            placeholder = 'Add Date'
                            style = { styles.inputStyle }
                            keyboardType = 'number-pad'
                            onChangeText = {
                            txtDate => this.setState({
                                date: txtDate
                            })
                            }
                        />
  
                        <TouchableOpacity onPress = { () =>{
                            this.addItem()
                            this.setState({ setModalOpen: this.state.setModalOpen = false })}}>
                          <Text style={ styles.addItem }>Add</Text>
                        </TouchableOpacity>

                        <TouchableOpacity onPress = { () =>this.setState({ setModalOpen: this.state.setModalOpen = false }) }>
                          <Text style={ styles.ClosedModal }>Cancel</Text>
                        </TouchableOpacity>

                    </View>
                        
                </Modal>

                {/* End Modal Add Notes*/}

                {/* Start Container for the List*/}

                <View style={ styles.ItemContainer }>
                
                    <Text style={ styles.ListName } >MY NOTES</Text>   

                    <FlatList 
                        data={ this.state.todoListArray }
                        renderItem = {({ item }) => (
                          <Text style={ styles.list }>{ item.date } ---- { item.name }</Text>
                        )}
                    />
                    
                    {/* Add Button for the List*/}
                    <TouchableOpacity onPress = { () =>this.setState({ setModalOpen: this.state.setModalOpen = true }) }>
                      <Text style={ styles.AddList } >+</Text>
                    </TouchableOpacity>
                    
                </View>
                {/* Start Container for the List*/}
                
            </View>
            //End Main Container

        )
    }

}

const styles = StyleSheet.create({

    // Main Container
    mainContainer:{
        flex: 1,
        backgroundColor: 'black',
        
    },

    // Button Add
    AddList:{
      fontSize: 70,
      backgroundColor: 'orange',
      borderRadius: 200,
      color:'black',
      width:100,
      height:100,
      textAlign: 'center',
      marginLeft:296,
      marginTop:20,
      marginBottom:20
      
    },

    //Style for the returned list of Notes
    ItemContainer:{
      flex:1,
      borderColor: 'orange',
      borderWidth: 5
    },
    ListName:{
      fontSize: 60,
      backgroundColor: 'orange',
      color: 'black',
      textAlign: 'center',
    },
    list: {
      marginTop:15,
      marginBottom:3,
      margin: 20,
      padding: 10,
      color: 'black',
      backgroundColor: 'white',
      fontSize: 20,
      borderRadius: 5,
      borderWidth: 1,
    },
    //End Style for the returned list of Notes

    //Modal Style
    addItem:{
      fontSize: 30,
      backgroundColor: 'blue',
      color:'white',
      borderRadius: 10,
      height: 50,
      textAlign: 'center',
      marginTop:20,
    },
    ClosedModal:{
      fontSize: 30,
      backgroundColor: 'red',
      borderRadius: 10,
      color:'white',
      height: 50,
      textAlign: 'center',
      marginTop:20,

    },
    modalContainer:{
      flex:1,
      backgroundColor: 'black',
      borderColor: 'orange',
      borderWidth: 5
    },
    inputStyle:{
      borderWidth:1,
      borderRadius:5,
      height: 50,
      margin: 10,
      backgroundColor: 'white',
      color: 'black',
      fontSize: 20,
      textAlign: 'center',
      marginTop:20
    },

    //End Modal Style

})