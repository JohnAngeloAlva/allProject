import React, { Component } from "react";
import { TextInput, View, Text, StyleSheet, TouchableOpacity, Image, ScrollView } from "react-native";
import {Picker} from '@react-native-picker/picker';

export default class MovieChoice extends Component {  
  state = {
    MovieName: '',
  }
  PickerData = () => {
  const { MovieName }  = this.state
  }
  render() {
    return (
    <ScrollView style={styles.container}>
        <Text style={styles.text}>MOVIE RESERVATIONS</Text>

        <View style={styles.inputContainer}>
           <TextInput style={styles.inputs}
               placeholder="Full Name"
               underlineColorAndroid='transparent'
               label = 'Full Name'               
               />
         <Image style={styles.inputIcon} source={{uri: 'https://img.icons8.com/color/40/000000/circled-user-male-skin-type-3.png'}}/>
         </View>

        <View style={styles.inputContainer}>
           <TextInput style={styles.inputs}
               placeholder="User Name"
               underlineColorAndroid='transparent'
               label = 'User Name'               
               />
         <Image style={styles.inputIcon} source={{uri: 'https://img.icons8.com/color/40/000000/circled-user-male-skin-type-3.png'}}/>
         </View>

         <View style={styles.inputContainer}>
           <TextInput style={styles.inputs}
               placeholder="Mobile Number"
               keyboardType="number-pad"
               underlineColorAndroid='transparent'
               />
       <Image style={styles.inputIcon} source={{uri: 'https://img.favpng.com/16/1/11/computer-icons-telephone-number-mobile-phones-portable-network-graphics-png-favpng-BdUZq82Z4is5G9aMhu5rkhZDC.jpg'}}/>
         </View>

       <View style={styles.pickerStyle}>
        
        <Picker selectedValue={this.state.MovieName}
        onValueChange={(itemValue, itemIndex) =>
          this.setState({MovieName: itemValue})} mode = "dropdown">
            
        <Picker.Item label="..Select Movie Name.." value="unknown" />
        <Picker.Item label="Red Notice" value="Movie1" />
        <Picker.Item label="Cinderella" value="Movie2" />
        <Picker.Item label="The Conjuring" value="Movie3" />
        <Picker.Item label="BlackPink The Movie" value="Movie4" />
        <Picker.Item label="Black Christmas" value="Movie5" />
        <Picker.Item label="Army of the Dead" value="Movie6" />
        <Picker.Item label="Ocean's 8" value="Movie7" />
      </Picker>
        </View>

        <View style={styles.inputContainer}>
           <TextInput style={styles.inputs}
               placeholder="Date (MM/DD/YYYY)"
               underlineColorAndroid='transparent'
               label = 'Date'
               />
           <Image style={styles.inputIcon} source={{uri: 'https://image.flaticon.com/icons/png/512/1182/1182778.png'}}/>
         </View>

         <View style={styles.inputContainer}>
           <TextInput style={styles.inputs}
               placeholder="Payment"
               keyboardType="number-pad"
               underlineColorAndroid='transparent'
               />
       <Image style={styles.inputIcon} source={{uri: 'https://img.favpng.com/16/1/11/computer-icons-telephone-number-mobile-phones-portable-network-graphics-png-favpng-BdUZq82Z4is5G9aMhu5rkhZDC.jpg'}}/>
         </View>

      <TouchableOpacity onPress={() =>{ this.props.navigation.navigate('SavedData') }}>
             <Text style={ styles.button }>SAVE</Text> 
        </TouchableOpacity>
    
        <TouchableOpacity onPress={()=> {this.props.navigation.goBack()}}>
        <Text style={{ marginLeft: 10, marginBottom: 10 }}>Back to home</Text>
      </TouchableOpacity>
    </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
      flex: 1,
      padding: 20,
      backgroundColor: '#f4f1de',
      
  },
  
  text: {
    color:'#540b0e',
    fontSize: 22,
    fontWeight: 'bold',
    fontStyle: 'italic',
    textAlign: 'center',  
 },
 
 item: {
  margin: 10,
  marginTop: 25,
  padding: 20,
  height: 80,
  textAlign: 'center',
  backgroundColor: '#9e2a2b',
  fontSize: 25,
  color: '#ffff',
  fontWeight: 'bold',
},
inputContainer: {
  borderBottomColor: '#03071e',

  backgroundColor: '#F6EEE0',
  borderRadius:30,
  borderBottomWidth: 1,
  width:350,
  height:60,
  marginBottom: 10,
  marginTop: 10,
  flexDirection: 'row',
  alignItems:'center',
},
inputs:{
  height:45,
  marginLeft:16,
  borderBottomColor: '#FFFFFF',
  flex:1,
  fontSize: 18,
},
inputIcon:{
  width:30,
  height:30,
  marginRight:15,
  justifyContent: 'center'
},
pickerStyle: {
  alignSelf: 'stretch',
  height: 40,
  marginBottom: 30,
  color: '#fff',
  borderBottomColor: '#f8f8f8',
  borderBottomWidth: 1,
},

button: {
  marginTop: 10,
  marginLeft: 130,
  padding: 10,
  height: 45,
  width: 100,
  textAlign: 'center',
  backgroundColor: '#9e2a2b',
  fontSize: 15,
  color: 'white',
  borderRadius: 15,

}
})

