import { initializeApp } from "https://www.gstatic.com/firebasejs/9.6.1/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.6.1/firebase-analytics.js";
import { getDatabase, ref, child, onValue, get } from "https://www.gstatic.com/firebasejs/9.6.1/firebase-database.js";

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


const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
const db = getDatabase();

var comID = 0;
var comList = [];
var tbody = document.getElementById('tbody1');

function addItemtoTable(name, phoneNum, seat, email){
    let trow = document.createElement("tr");
    let td1 = document.createElement('td');
    let td2 = document.createElement('td');
    let td3 = document.createElement('td');
    let td4 = document.createElement('td');
    let td5 = document.createElement('td');
comList.push([name, phoneNum, seat, email]);
    td1.innerHTML= ++comID;
    td2.innerHTML= name;
    td3.innerHTML= phoneNum;
    td4.innerHTML= seat;
    td5.innerHTML = email;

    trow.appendChild(td1);
    trow.appendChild(td2);
    trow.appendChild(td3);
    trow.appendChild(td4);
    trow.appendChild(td5);
    tbody.appendChild(trow);

    var actionBtn = document.createElement('div');
   
    actionBtn.innerHTML += '<button id="fillBoxes" type="button" class="btn btn-primary" data-toggle="modal" >EDIT</button>';
    trow.appendChild(actionBtn);
    var colCount = $("#tbody1 tr").length;
    $('#commuter').text(colCount);
}
var modName = document.getElementById('fName');
var modPNum = document.getElementById('pNum');
var modSeat = document.getElementById('seatID');
var modEmail = document.getElementById('email');
var btn = document.getElementById('fillBoxes');
btn.addEventListener("click", function(){
    alert("tae");
});

function AddAllItems(TheUser){
    comID = 0;
    tbody.innerHTML="";
    TheUser.forEach(element => {
        addItemtoTable(element.fullName, element.phoneNum, element.seatID, element.email);

    });
}



function getAllDataOnce(){
    const dbRef = ref(db);
    get(child(dbRef, "Users"))
    .then((snapshot) => {
        var commuter = [];
        snapshot.forEach(childSnapshot =>{
            commuter.push(childSnapshot.val());
           
            
        });
        AddAllItems(commuter);
    });
    
}

window.onload = getAllDataOnce;
