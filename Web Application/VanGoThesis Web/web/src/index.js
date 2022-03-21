 // Import the functions you need from the SDKs you need
 import { initializeApp } from "https://www.gstatic.com/firebasejs/9.6.1/firebase-app.js";
 import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.6.1/firebase-analytics.js";
 import { getDatabase } from "https://www.gstatic.com/firebasejs/9.6.1/firebase-database.js";
 // TODO: Add SDKs for Firebase products that you want to use
 // https://firebase.google.com/docs/web/setup#available-libraries

 // Your web app's Firebase configuration
 // For Firebase JS SDK v7.20.0 and later, measurementId is optional
 const firebaseConfig = {
apiKey: "AIzaSyADIizDujSp7DQu8Znbwu-KK2P67ClK5Ow",
authDomain: "vango-9cacc.firebaseapp.com",
databaseURL: "https://vango-9cacc-default-rtdb.firebaseio.com",
projectId: "vango-9cacc",
storageBucket: "vango-9cacc.appspot.com",
messagingSenderId: "595098715715",
appId: "1:595098715715:web:483026257a0c936808f6f6",
measurementId: "G-VK3B73DCR3"
};

 // Initialize Firebase
 const app = initializeApp(firebaseConfig);
 const analytics = getAnalytics(app);
 // Get a reference to the database service
const database = getDatabase(app);
var bigOne = document.getElementById('bigOne');
var dbRef = firebase.database().ref().child('text');
dbRef.on('value', snap => bigOne.innerText = snap.val());