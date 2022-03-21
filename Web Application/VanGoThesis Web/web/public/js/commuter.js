
// !IMPORTANT: REPLACE WITH YOUR OWN CONFIG OBJECT BELOW


// Initialize Firebase
const config = {
    apiKey: "AIzaSyADIizDujSp7DQu8Znbwu-KK2P67ClK5Ow",
    authDomain: "vango-9cacc.firebaseapp.com",
    databaseURL: "https://vango-9cacc-default-rtdb.firebaseio.com",
    projectId: "vango-9cacc",
    storageBucket: "vango-9cacc.appspot.com",
    messagingSenderId: "595098715715",
    appId: "1:595098715715:web:483026257a0c936808f6f6",
    measurementId: "G-VK3B73DCR3"
};



firebase.initializeApp(config);

// Firebase Database Reference and the child
const dbRef = firebase.database().ref();

const usersRef = dbRef.child('Users');

// --------------------------
// LOGOUT
// --------------------------
logout.addEventListener('click', (e) => {
	firebase.auth().signOut().then(() => {
		alert('User Logout');
		window.location.href = "login.html";
	  }).catch((error) => {
		alert('asd');
	  });
});

	readUserData(); 
	

// --------------------------
// READ
// --------------------------
function readUserData() {

	const userListUI = document.getElementById("user-list");

	usersRef.on("value", snap => {

		userListUI.innerHTML = ""

		snap.forEach(childSnap => {

			let key = childSnap.key,
				value = childSnap.val()
  			
			let $li = document.createElement("li");

			// edit icon
			let editIconUI = document.createElement("i");
			editIconUI.class = "edit-user";
			editIconUI.id = "edit-button"
			editIconUI.setAttribute("data-toggle", "tooltip")
			editIconUI.setAttribute("title", "Edit")
			editIconUI.className = 'material-icons';
			editIconUI.innerHTML = "&#xE254;";
			editIconUI.setAttribute("userid", key);
			editIconUI.addEventListener("click", editButtonClicked)

			// delete icon
			let deleteIconUI = document.createElement("i");
			deleteIconUI.id = "delete-button"
			deleteIconUI.setAttribute("data-toggle", "tooltip")
			deleteIconUI.setAttribute("title", "Delete")
			deleteIconUI.className = 'material-icons';
			deleteIconUI.innerHTML = "&#xE872;";
			deleteIconUI.setAttribute("userid", key);
			deleteIconUI.addEventListener("click", deleteButtonClicked)
			
			$li.innerHTML = value.fullName;
			$li.append(editIconUI);
			$li.append(deleteIconUI);

			$li.setAttribute("user-key", key);
			$li.addEventListener("click", userClicked)
			userListUI.append($li);

 		});


	})

}


// --------------------------
// SHOW DATA
// --------------------------
function userClicked(e) {


		var userID = e.target.getAttribute("user-key");

		const userRef = dbRef.child('Users/' + userID);
		const userDetailUI = document.getElementById("user-detail");

		userRef.on("value", snap => {

			userDetailUI.innerHTML = ""

			snap.forEach(childSnap => {
				var $p = document.createElement("p");
				$p.innerHTML = childSnap.key  + " - " +  childSnap.val();
				userDetailUI.append($p);
			})

		});
	

}





/**

 ADD


function addUserBtnClicked() {

	const usersRef = dbRef.child('Users');

	const addUserInputsUI = document.getElementsByClassName("user-input");

 	// this object will hold the new user information
    let newUser = {};

    // loop through View to get the data for the model 
    for (let i = 0, len = addUserInputsUI.length; i < len; i++) {

        let key = addUserInputsUI[i].getAttribute('data-key');
        let value = addUserInputsUI[i].value;
        newUser[key] = value;
    }

	usersRef.push(newUser)


}
*/


// --------------------------
// DELETE
// --------------------------
function deleteButtonClicked(e) {
	
		document.getElementById('delete-user-module').style.display = "block";
		document.querySelector(".delete-userid").value = e.target.getAttribute("userid");
		e.stopPropagation();

		const userRef = dbRef.child('Users/' + e.target.getAttribute("userid"));
		
		const deleteBtn = document.querySelector("#delete-user-btn");
		deleteBtn.addEventListener("click", deleteUserBtnClicked)
	
}

function deleteUserBtnClicked(e){
	const userID = document.querySelector(".delete-userid").value;
	const userRef = dbRef.child('Users/' + userID);
	
	userRef.remove();
	document.getElementById('delete-user-module').style.display = "none";
}

// --------------------------
// EDIT
// --------------------------
function editButtonClicked(e) {
	
	
	
	document.getElementById('edit-user-module').style.display = "block";
	
	//set user id to the hidden input field
	document.querySelector(".edit-userid").value = e.target.getAttribute("userid");

	const userRef = dbRef.child('Users/' + e.target.getAttribute("userid"));

	// set data to the user field
	const editUserInputsUI = document.querySelectorAll(".form-control");


	userRef.on("value", snap => {

		for(var i = 0, len = editUserInputsUI.length; i < len; i++) {

			var key = editUserInputsUI[i].getAttribute("data-key");
					editUserInputsUI[i].value = snap.val()[key];
		}

	});


	const saveBtn = document.querySelector("#edit-user-btn");
	saveBtn.addEventListener("click", saveUserBtnClicked)
}

function saveUserBtnClicked(e) {
 
	const userID = document.querySelector(".edit-userid").value;
	const userRef = dbRef.child('Users/' + userID);

	var editedUserObject = {}

	const editUserInputsUI = document.querySelectorAll(".form-control");

	editUserInputsUI.forEach(function(textField) {
		let key = textField.getAttribute("data-key");
		let value = textField.value;
  		editedUserObject[textField.getAttribute("data-key")] = textField.value
	});



	userRef.update(editedUserObject);

	document.getElementById('edit-user-module').style.display = "none";


}



        








