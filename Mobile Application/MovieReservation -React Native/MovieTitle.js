
import React, { Component } from "react";
import { View, Text, StyleSheet, TouchableOpacity, Image, ScrollView } from "react-native";

export default class MovieTitle extends Component {
  render() {
    return (
    <ScrollView style={styles.container}>
        
     <View>
         <Image source={{uri: 'https://media1.popsugar-assets.com/files/2011/03/09/2/192/1922283/5366bd8ca4b2b20d_MarchMoviesPolll.jpg' }}
          style={{width: 420, height: 250}} />
          <Text style={styles.descrip}>Our movie tickets are now at your fingertips wherever you go. Book a ticket and watch the latest movies that can find here in MovieBuff.</Text> 
     </View>
      
     <View style={styles.photosCard}>   
            <View style={styles.photosContainer}>
              <Text style={ styles.line }>__________________________________________</Text>
              <Image style={styles.photo} source={{uri: "https://m.media-amazon.com/images/M/MV5BZmRjODgyMzEtMzIxYS00OWY2LTk4YjUtMGMzZjMzMTZiN2Q0XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg"}} />

              <Text style={ styles.title }>Red Notice</Text>
              <TouchableOpacity onPress={() =>{ this.props.navigation.navigate('MovieChoice') }}>
              <Text style={{marginLeft: 110, width: 60, height: 40, marginTop: 60, paddingRight: 10, padding: 12, backgroundColor: '#9e2a2b', fontSize: 14, color: '#ffff', borderRadius: 10}}>Book</Text> 
              </TouchableOpacity>

              <Text style={ styles.line }>__________________________________________</Text>
              <Image style={styles.photo} source={{uri: "https://m.media-amazon.com/images/M/MV5BZTk3ZTEzNGUtZTcwYy00NmRmLWFhMGYtZjA4NWY1ZWI4MzMyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1000_.jpg"}} />
              <Text style={ styles.title }>Cinderella</Text>
              <TouchableOpacity onPress={() =>{ this.props.navigation.navigate('MovieChoice') }}>
              <Text style={{marginLeft: 110, width: 60, height: 40, marginTop: 60, paddingRight: 10, padding: 12, backgroundColor: '#9e2a2b', fontSize: 14, color: '#ffff', borderRadius: 10}}>Book</Text> 
              </TouchableOpacity>

              <Text style={ styles.line }>__________________________________________</Text>
              <Image style={styles.photo} source={{uri: "https://m.media-amazon.com/images/M/MV5BOWRkOTYzZTQtMzQwNi00NDYwLTk4NjUtN2FjYTI4Y2UzM2RjXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_.jpg"}} />
              <Text style={ styles.title }>The Conjuring</Text>
              <TouchableOpacity onPress={() =>{ this.props.navigation.navigate('MovieChoice') }}>
              <Text style={{marginLeft: 75, width: 60, height: 40, marginTop: 60, paddingRight: 10, padding: 12, backgroundColor: '#9e2a2b', fontSize: 14, color: '#ffff', borderRadius: 10}}>Book</Text> 
              </TouchableOpacity>

              <Text style={ styles.line }>__________________________________________</Text>
              <Image style={styles.photo} source={{uri: "https://www.allkpop.com/upload/2021/08/content/220109/1629608968-e3-s1bbvoamvydo.jpg"}} />
              <Text style={ styles.title }>BlackPink The Movie </Text>
              <TouchableOpacity onPress={() =>{ this.props.navigation.navigate('MovieChoice') }}>
              <Text style={{marginLeft: 10, width: 60, height: 40, marginTop: 60, paddingRight: 10, padding: 12, backgroundColor: '#9e2a2b', fontSize: 14, color: '#ffff', borderRadius: 10}}>Book</Text> 
              </TouchableOpacity>

              <Text style={ styles.line }>__________________________________________</Text>
              <Image style={styles.photo} source={{uri: "https://www.uphe.com/sites/default/files/styles/scale__344w_/public/2020/01/BlackChristmas_PosterArt.jpg"}} />
              <Text style={ styles.title }>Black Christmas</Text>
              <TouchableOpacity onPress={() =>{ this.props.navigation.navigate('MovieChoice') }}>
              <Text style={{marginLeft: 50, width: 60, height: 40, marginTop: 60, paddingRight: 10, padding: 12, backgroundColor: '#9e2a2b', fontSize: 14, color: '#ffff', borderRadius: 10}}>Book</Text> 
              </TouchableOpacity>

              <Text style={ styles.line }>__________________________________________</Text>
              <Image style={styles.photo} source={{uri: "https://m.media-amazon.com/images/M/MV5BNGY0NzgzYzctYWQwMC00MzM2LThjNGMtZjFjMWUyNzg0ZmM0XkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg"}} />
              <Text style={ styles.title }>Army of the Dead</Text>
              <TouchableOpacity onPress={() =>{ this.props.navigation.navigate('MovieChoice') }}>
              <Text style={{marginLeft: 45, width: 60, height: 40, marginTop: 60, paddingRight: 10, padding: 12, backgroundColor: '#9e2a2b', fontSize: 14, color: '#ffff', borderRadius: 10}}>Book</Text> 
              </TouchableOpacity>

              <Text style={ styles.line }>__________________________________________</Text>
              <Image style={styles.photo} source={{uri: "https://images.moviesanywhere.com/59afcf857fe47db733e38867da349f46/a726b292-d20d-4cb6-b19a-dcb962874f4a.jpg"}} />
              <Text style={ styles.title }>Ocean's 8</Text>
              <TouchableOpacity onPress={() =>{ this.props.navigation.navigate('MovieChoice') }}>
              <Text style={{marginLeft: 100, width: 60, height: 40, marginTop: 60, paddingRight: 10, padding: 12, backgroundColor: '#9e2a2b', fontSize: 14, color: '#ffff', borderRadius: 10}}>Book</Text> 
              </TouchableOpacity>
              <Text style={ styles.line }>__________________________________________</Text>
            </View>
            
      </View>
    
        <TouchableOpacity onPress={()=> {this.props.navigation.goBack()}}>
            <Text style={{ padding: 10, marginLeft: 10, color: 'white'}}>Back to home</Text>
      </TouchableOpacity>
    </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
      flex: 1,
      padding: 0,
      backgroundColor: '#03071e'
  },

  head: {
      color: 'white',
      fontWeight: '500',
      fontSize:20,
      marginBottom:5,
      textAlign: 'center',
      textAlign: 'center',
      
  },
  descrip: {
    color: 'black',
    textAlign: 'center',
    paddingLeft:20,
    paddingRight:20,
    paddingTop:15,
    marginTop:10,
    fontSize: 15,
    fontStyle: 'italic',
    color: 'white'
},
  photosCard:{
    marginTop: 5,
    paddingLeft: 10,
    paddingRight: 10,
    
  },
  photosContainer:{
    flexDirection: 'row',
    flexWrap: 'wrap',
    height: 'auto',
    
  },
  photo:{
    height: 120,
    width: 100,
    marginTop:10,
    marginBottom: 5,
    marginLeft: 5
  },

  title: {
    paddingTop: 10,
    marginTop: 50,
    marginLeft:20,
    fontWeight: 'bold',
    fontSize: 18,
    color: 'white'
  },

  line: {
    paddingTop: 5,
    marginLeft:10,
    fontSize: 18,
    color: 'white'
  },

  description: {
    marginTop: 5,
    margin:10,
    fontSize: 18,
    color: 'white'
  },

  text: {
    color: '#540b0e',
    fontSize: 22,
    fontWeight: 'bold',
    fontStyle: 'italic',
    textAlign: 'center',  
    margin: 15,
    color: 'white'
 },
 item: {
  margin: 10,
  marginTop: 15,
  padding: 20,
  height: 80,
  textAlign: 'center',
  backgroundColor: '#9e2a2b',
  fontSize: 25,
  color: '#ffff',
  fontWeight: 'bold',
},

Button: {
  marginTop: 60,
  paddingRight: 10,
  padding: 12,
  height: 40,
  width: 60,
  backgroundColor: '#9e2a2b',
  fontSize: 14,
  color: '#ffff',
  borderRadius: 10,

}
})


