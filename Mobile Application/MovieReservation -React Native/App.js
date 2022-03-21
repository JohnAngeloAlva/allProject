import React, { Component } from "react";

import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { NavigationContainer } from "@react-navigation/native";
//import the application
import MovieHome from "./screens/MovieHome";
import MovieTitle from "./screens/MovieTitle"
import MovieChoice from "./screens/MovieChoice"
import SavedData from "./screens/SavedData"
import AdminValid from "./screens/AdminValid";
import ListData from "./screens/ListData";

const Stack = createNativeStackNavigator();

export default class Home extends Component {

  render() {
    return (
      //create a navigation menu bar of the application
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen 
          name="Home"
          component={ MovieHome }
          options={{ title: 'Movie Buff', headerStyle: {
            backgroundColor: '#370617', }, headerTintColor: 'white'}}
          />
          <Stack.Screen
          name="MovieTitle"
          component={ MovieTitle }
          options={{ title: 'Movies', headerStyle: {
            backgroundColor: '#370617', }, headerTintColor: 'white'}}
          />
          <Stack.Screen
          name="MovieChoice"
          component={ MovieChoice }
          options={{ title: 'Reservation Details', headerStyle: {
            backgroundColor: '#370617', }, headerTintColor: 'white'}}
          />
          <Stack.Screen
          name="AdminValid"
          component={ AdminValid }
          options={{ title: 'Admin Page', headerStyle: {
            backgroundColor: '#370617', }, headerTintColor: 'white' }}
          />
        <Stack.Screen
          name="SavedData"
          component={ SavedData }
          options={{ title: 'Receipt', headerStyle: {
            backgroundColor: '#370617', }, headerTintColor: 'white'}}
          />
          <Stack.Screen
          name="ListData"
          component={ ListData }
          options={{ title: 'LIST OF ALL DATA', headerStyle: {
            backgroundColor: '#370617', }, headerTintColor: 'white'}}
          />
        </Stack.Navigator>
      </NavigationContainer>
    );
  }

}



