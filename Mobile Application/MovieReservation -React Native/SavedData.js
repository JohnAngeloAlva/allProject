

import React, { Component } from "react";
import { View, Text, StyleSheet, Image, FlatList, TouchableOpacity } from "react-native";

export default class MovieHome extends Component {
    
  render() {
    return (
     <View style={ styles.container }>
       
      <TouchableOpacity onPress={()=> {this.props.navigation.goBack()}}>
            <Text>BACK TO HOME</Text>
      </TouchableOpacity>
     </View>
     
    );
  }
}

const styles = StyleSheet.create({
 container: {
     flex: 1,
     padding: 20,
     backgroundColor: '#f4f1de'
 }
})


