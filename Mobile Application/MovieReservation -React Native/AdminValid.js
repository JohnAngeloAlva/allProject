import React, { Component } from "react";
import { View, TextInput, Text, StyleSheet, Image, TouchableOpacity, ScrollView } from "react-native";

export default class ListData extends Component {
  render() {
    return (
      <ScrollView>
    <View style={styles.container}>
         <View style={styles.header}>
            <View style={styles.headerContent}>
                <Text style={styles.name}>Movie Buff</Text>
                <Text style={styles.descrip}>Jhon Angelo Alva</Text>
                <Text style={styles.descrip}>Gulliver Corpuz</Text>
                <Text style={styles.descrip}>Maria Nepomuceno</Text>
                <Text style={styles.descrip}>Maria Jessica Plinky  Sigue</Text>
            </View>
        </View>
        <View style={styles.inputContainer}>
           <TextInput style={styles.inputs}
               placeholder="Password"
               secureTextEntry={true}
               label = 'Password'
               underlineColorAndroid='transparent'
               />
           <Image style={styles.inputIcon} source={{uri: 'https://img.icons8.com/color/40/000000/password.png'}}/>
         </View>

         <TouchableOpacity style={[styles.buttonContainer, styles.loginButton]} onPress={() =>{ this.props.navigation.navigate('ListData') }}>
           <Text style={styles.loginText}>CONTINUE</Text>
         </TouchableOpacity>
      
      <TouchableOpacity onPress={()=> {this.props.navigation.goBack()}}>
      <Text style={{ marginLeft: 10, marginTop: 90 }}>Back to home</Text>
      </TouchableOpacity>
     </View>
    </ScrollView>

    );
  }
}
const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 20,
        backgroundColor: '#f4f1de'
    },
    header:{
        backgroundColor: "#370617",
      },
      headerContent:{
        padding:20,
        alignItems: 'center',
      },
      avatar: {
        width: 100,
        height: 100,
        borderRadius: 63,
        borderWidth: 3,
        borderColor: "white",
        marginBottom: 4,
      },
      name:{
        fontSize: 20,
        color:"#FFFFFF",
        fontWeight:'600',
        textAlign: 'center'
      },
      descrip: {
        fontSize: 15,
        color:"#FFFFFF",
        fontWeight:'300',
        marginTop: 5,
        textAlign: 'justify',
      },
      inputContainer: {
        borderColor: '#03071e',
        backgroundColor: '#F6EEE0',
        borderRadius:30,
        borderBottomWidth: 1,
        width:370,
        height:50,
        marginTop: 150,
        marginBottom:90,
        flexDirection: 'row',
        alignItems:'center',
      },
      inputs:{
        height:45,
        marginLeft:16,
        borderBottomColor: 'black',
        borderRightWidth: 2,
        flex:1,
      },
      inputIcon:{
        width:30,
        height:30,
        marginRight:15,
        justifyContent: 'center'
      },
      buttonContainer: {
        height: 50,
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        marginTop: 15,
        width: 200,
        borderRadius: 15,
        backgroundColor:'transparent',
        marginLeft: 80
        
      },
      loginButton: {
        backgroundColor: "#2F5061",
        shadowColor: "#808080",
        shadowOffset: {
          width: 0,
          height: 9,
        },
        shadowOpacity: 0.50,
        shadowRadius: 15,
        elevation: 19,
      },

      loginText: {
        color: 'white',
        fontSize: 15,
        fontWeight: 'bold'
      }



})

