import React, { Component } from "react";
import { View, Text, StyleSheet, Image, TouchableOpacity, ScrollView } from "react-native";

export default class ListData extends Component {
  render() {
    return (
      <ScrollView>
    <View style={styles.container}>
        
        </View>

        <TouchableOpacity onPress={()=> {this.props.navigation.goBack()}}>
            <Text>BACK TO HOME</Text>
      </TouchableOpacity>
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
   
})

