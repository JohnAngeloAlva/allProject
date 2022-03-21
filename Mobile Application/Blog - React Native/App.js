
import React, {useState} from "react";
import {
  Text, View, 
  Image, StyleSheet,
  relative,absolute, TouchableHighlight
} from 'react-native';


const Profile = () => {

  const [followers, AddFollower ] = useState(0)

  return (
    <View>

      {/* Header */}

      <View>
        
        <Image
          source={require('./Image/cover.png')}  
          style={styles.bg} 
        />
        <Image
          source={require('./Image/me.jpg')}  
          style={styles.profilePic} 
        />
        <Text style = {styles.name}>
          John Angelo Alva (Jonas)
        </Text>
      </View>

      {/* End of Header */}

      {/* Credentials */}

      <View style={styles.credentialsContainer} >
        <Image
          source={require('./Image/email.png')}  
          style={styles.credentials} 
        />
        <Text style={styles.credentialsEmail}>
          j.alva@mseuf.edu.ph
        </Text>
        <Image
          source={require('./Image/phones.jpg')}  
          style={styles.credentials} 
        />
        <Text style={styles.credentialsEmail}>
          #09682992727
        </Text>
        <Image
          source={require('./Image/location.png')}  
          style={styles.credentials} 
        />
        <Text style={styles.credentialsLocation}>
          Heidelberg St. University Site Ibabang Dupay Lucena City
        </Text>
        <TouchableHighlight onPress={() => {AddFollower(followers + 1)}}>
            <Image 
              source={require('./Image/follow.png')}  
              style={styles.followersImage} />
        </TouchableHighlight>
        <Text style={styles.followers}>
          My Followers is {followers}
        </Text>
      </View>

      {/* Credentials */}

      <View>
      <Image
          source={require('./Image/bg.jpeg')}  
        />
      </View>
      
    </View>
    
    
    
  )
}
const styles = StyleSheet.create ({
  
  bg:{
    width: 450, 
    height: 270,
    position: relative
  },
  profilePic: {
    position: absolute,
    top: -60,
    left: 110,
    width: 200, 
    height: 200, 
    borderRadius: 400/ 2,
  },

  name:{
    fontSize: 25,
    marginTop: -40,
    marginLeft: 60,
    fontFamily: "Cochin",
    fontWeight: "bold"
  },
  credentials : {
    
    width: 50, 
    height: 60, 
  },
  credentialsContainer : {
    marginTop:20,
    marginLeft: 60
  },
  credentialsEmail : {
    position: absolute,
    top: -45,
    left: 70,
    fontSize: 20,
    fontFamily: "Cochin",
    fontWeight: "bold"
  },
  credentialsPhone: {
    position: absolute,
    top: -45,
    left: 70,
    fontSize: 20,
    fontFamily: "Cochin",
    fontWeight: "bold"
  },
  credentialsLocation: {
    position: absolute,
    top: -53,
    left: 70,
    marginRight: 50,
    fontSize: 20,
    fontFamily: "Cochin",
    fontWeight: "bold"
  },
  followersImage : {
    marginTop: -20,
    marginLeft: 5,
    width: 40, 
    height: 40, 
  },
  followers: {
    position: absolute,
    top: -35,
    left: 70,
    marginRight: 50,
    fontSize: 20,
    fontFamily: "Cochin",
    fontWeight: "bold"
  },


});

export default Profile;
