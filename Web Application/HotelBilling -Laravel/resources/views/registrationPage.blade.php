<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Page</title>

    <link rel = "icon" href = "image/icon.jpeg" type = "image/x-icon">

	<link href="css/reservationPage.css" rel="stylesheet" type="text/css" >

</head>
<body>
    
    <!-- Navigation Panel -->
    <div>

        <ul>
            <li><a>About</a></li>
            <li><a >Contact</a></li>
            <li><a >Home</a></li>
            <li><a class="active" >Reservation Page</a></li>
            <li class="iconPosition"><img class="icon" src="image/icon.jpeg" /> </li>
            <p class="iconName">The Glory Hotel</p>
            
        </ul>

    </div>
    
    <!-- Warning Text -->
    <div class="alert">
        Warning !!! <br /><br />
        Check in and Check out dates should be valid dates, if you click the reserved button the validation for the dates will be process and if the dates are not valid the page will be automatically return to the reservation page 
        <span class="closebtn" onclick="this.parentElement.style.display='none';">Click here to close</span> 
        
    </div>

    <!-- Reservation Cotainer-->
    <div class="reservationContainer" >
        <div class="leftContainer">
            <form action="receiptPage" method="post">
				@csrf
                <div>
                    <h2>Reservation Details</h2>
                    <!-- TABLE FOR DATE AND TIME -->
                    <table>
                        <tr>
                            <td>
                                <label>	Start Date </label>
                                <input class="date" type="date" name="reservedIn" required>
                            </td>
                            <td>
                                <label>End Date  </label>
                                <input class="date" type="date" name="reservedOut" required>
                            </td> 
                        </tr>
                    </table>

                    <label>No. of Rooms</label>
                    <input class="rooms" type="number" min="1" placeholder="Rooms" name="room" required><br>

                    <!-- TABLE FOR INFORMATION OF USER -->
                    <h2 id="head">Personal Details</h2>
                    <table>
                        <tr>
                            <td>
                                <label>First Name</label>
                                <input class="personalDetails" title="Letters Only" type="text" name="firstName" pattern="[a-zA-Z ]+" placeholder="First Name" required></input>
                            </td>
                            <td>
                                <label>Last Name</label>
                                <input class="personalDetails" title="Letters Only" type="text" name="lastName" placeholder="Last Name" pattern="[a-zA-Z ]+" required></input>
                            </td>
                        </tr>
                    </table>

                    <label>Address Name</label>
                    <input class="personalDetailsAddress" type="text" name="address" placeholder="Address" required><br>

                    <!-- TABLE FOR CONTACT INFORMATION OF USER -->
                    <h2 id="head">Contact Details</h2>
                    <table>
                        <tr>
                            <td>
                                <label>Email</label>
                                <input class="contactDetails" type="email" name="email"  placeholder="Email" required>
                            </td>
                            <td>
                                <label>Contact Number</label>
                                <input class="contactDetails" type="text" name="contactNumber" title="Must be 11 digits" pattern="[0-9]{11}" placeholder="Contact Number" required>
                            </td>
                        </tr>
                    </table>
                </div>

                <input class= "btn_submit" type="submit" value="Reserved Now">
            </form>
        </div>

        <div class="rightContainer">
            <img class="image" src="image/front.jpeg">
        </div>

    </div>
    <!-- End Reservation Cotainer-->

    <!-- Footer Cotainer-->
    <footer class="footer">
        <div class="row">
            <div class="column1" >
                <img src="image/logo.jpeg "class="logo"/>
            </div>
            <div class="column2" >
                <h2>Support</h2>
                <p>Contact Us</p>
                <p>About Us</p>
                <p>FAQ</p>
                <p>Book a Room</p>
                <p>John Angelo Alva</p>
                <div class="column" >
                    <p>Help</p>
                    <p>Customer Care</p>
                </div>
            </div>
            <div class="column3" >
                <p><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3872.147266065963!2d121.61630221435304!3d13.94983569643305!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x33bd4cafbd3891dd%3A0x7ad44c26a4586378!2sHeidelberg%20St%2C%20Lucena%2C%204301%20Quezon!5e0!3m2!1sen!2sph!4v1632927093361!5m2!1sen!2sph" width="400" height="323" allowfullscreen="" loading="lazy"></iframe></p>
            </div>
        </div>
    </footer>
    <!-- End Footer Cotainer-->

</body>
</html>