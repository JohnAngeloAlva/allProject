

import React, { Component } from "react";
import { View, Text, StyleSheet, Image, FlatList, TouchableOpacity } from "react-native";

export default class MovieHome extends Component {
    
  render() {
    return (
     <View style={ styles.container }>
        <Image source={{uri: 'https://static.vecteezy.com/system/resources/previews/001/254/680/non_2x/cinema-background-concept-vector.jpg' }}
          style={{width: 420, height: 220, marginTop: 1}} />
        <View>
            <Text style={styles.textTitle }>Movie Buffs</Text>
        </View>
        <View>
            <Text style={styles.descript }>Get movie tickets booked on the go with Movie Buffs. Reserve your tickets now!</Text>
            <Text style={ styles.text }>Sign in as: </Text> 
        </View>

        <TouchableOpacity onPress={() =>{ this.props.navigation.navigate('MovieTitle') }}>
             <Text style={ styles.buttons }>USER</Text> 
        </TouchableOpacity>

        <TouchableOpacity onPress={() =>{ this.props.navigation.navigate('AdminValid') }}>
            <Text style={ styles.text }>Or </Text> 
             <Text style={ styles.buttons }>ADMIN</Text> 
        </TouchableOpacity>
         
     </View>
     
    );
  }
}

const styles = StyleSheet.create({
 container: {
     flex: 1,
     backgroundColor: '#f4f1de',
     padding: 0,
     alignItems: "center",

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
 descript: {
    color: 'black',
    marginTop: 5,
    fontWeight: '400',
    textAlign: 'justify',
    fontSize: 18,
    fontStyle: 'italic',
    padding: 10,
    textAlign: 'center',
},
 textTitle: {
    color: '#540b0e',
    fontSize: 25,
    fontWeight: 'bold',
    textAlign: 'center',
    marginTop: 15,
 },

 text: {
  color: '#540b0e',
  fontSize: 15,
  fontWeight: 'bold',
  fontStyle: 'italic',
  textAlign: 'center',
  marginTop: 5,
},

 buttons: {
     marginTop: 15,
     padding: 15,
     height: 60,
     width: 250,
     textAlign: 'center',
     backgroundColor: 'white',
     fontSize: 20,
     color: '#03071e',
     fontWeight: 'bold',
     borderRadius: 15,
     borderWidth: 2,
     borderColor: '#9e2a2b',  

 }
})

