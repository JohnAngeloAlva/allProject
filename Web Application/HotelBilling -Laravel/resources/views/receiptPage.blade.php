<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Receipt</title>
    
    <link rel = "icon" href = "image/icon.jpeg" type = "image/x-icon">

    <link href="css/receiptPage.css" rel="stylesheet" type="text/css" >

</head>
<body>

    <!-- Navigation Panel -->
    <div>
        <ul>
            <li><a >News</a></li>
            <li><a >Contact</a></li>
            <li><a>About</a></li>
            <li><a class="active" >Receipt</a></li>
            <li class="iconPosition"><img class="icon" src="image/icon.jpeg" /> </li>
            <p class="iconName">The Glory Hotel</p>
        </ul>
    </div>

    <!-- Warning Text -->
    <div class="alert">
        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
        <strong>Successfully Reserved !!</strong>  your reservation details
    </div>

    <!-- Receipt Cotainer-->
    <div class="receipt">
        <h2>Receipt</h2>

        <table>
            <tr class="row">

                <!-- Function For Start Date-->
                <td>
                    <label>	Start Date </label>      
                </td>
                <td>
                    @isset($dateIn)
                        <p class="receiptValue"> {{ $dateIn }} </p>
                     @endisset
                </td> 
            </tr>

            <!-- Function For End Date-->                
            <tr>
                <td>
                    <label>	End Date </label>      
                </td>
                <td>
                    @isset($dateOut)
                        <p class="receiptValue">{{ $dateOut }} </p>
                    @endisset
                </td> 
            </tr>
            
            <!-- Function For Computation of Date-->
            <tr>
                <td>
                    <label>	No. day/s </label>      
                </td>
                <td>
                    @isset($rangeDate)
                        <p class="receiptValue">{{ $rangeDate }} </p>
                    @endisset
                </td> 
            </tr>
            
            <!-- Function For Rooms -->
            <tr>
                <td>
                    <label>	Room/s </label>      
                </td>
                <td>
                    @isset($totalRoom)
                        <p class="receiptValue">{{ $totalRoom }} </p>
                    @endisset
                </td> 
            </tr>
            
            <!-- Function For Computation of Total Amount-->
            <tr>
                <td>
                    <label>	Total Amount </label>      
                </td>
                <td>
                    @isset($totalAmountStay)
                        <p class="receiptValue">{{ $totalAmountStay }} </p>
                    @endisset
                </td> 
            </tr>
            
            <!-- Function For Computation of Discount-->
            <tr>
                <td>
                    <label>	Discount </label>      
                </td>
                <td>
                    @isset($discount)
                        <p class="receiptValue">{{ $discount }} </p>
                    @endisset
                </td> 
            </tr>
            
            <!-- Function For Computation of Amount Due-->
            <tr>
                <td>
                    <label>	Amount Due </label>      
                </td>
                <td>
                    @isset($totalAmountDue)
                        <p class="receiptValue">{{ $totalAmountDue }} </p>
                    @endisset
                </td> 
            </tr>

            <tr>
                <td>
                    @isset($firstName)
                        <label>{{ $firstName }}</label>
                    @endisset
                    @isset($lastName)
                        <label>{{ $lastName }}</label>
                    @endisset
                </td> 
                <td>
                    @isset($email)
                        <p class="receiptValue">{{ $email }} </p>
                    @endisset
                    @isset($contactNumber)
                        <p class="receiptValue">{{ $contactNumber }} </p>
                    @endisset
                </td> 
            </tr>

        </table>

        <a href="registrationPage"> <input type="submit" value="Done"/></a>

    </div>
    <!-- End Receipt Cotainer-->   
           
</body>
</html>