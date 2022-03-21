<?php

use Illuminate\Support\Facades\Route;
use Illuminate\Http\Request;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

//Home page 
Route::get('/registrationPage', function () {
    return view('registrationPage');
});

//Receipt Page
Route::post('/receiptPage', function (Request $request) {

    //Returning the value of form in registration page
    $dateIn = $request -> input('reservedIn');
    $dateOut = $request -> input('reservedOut');
    $dateNow = date("Y-m-d");
    $totalRoom = $request -> input('room');
    $roomRate = 1500;
    $firstName = $request -> input('firstName');
    $lastName = $request -> input('lastName');
    $email = $request -> input('email');
    $contactNum = $request -> input('contactNumber');

    //valdiation for dates In
    if ($dateIn < $dateNow){
        echo '<script>alert("Error!!  Your inputted date is less than to current date");history.go(-1);</script>';
    }
    else{
        $dateInFormat = date_create($dateIn);
        $reservedIn = date_format($dateInFormat,"m/d/Y ");
    }
    
    //validations for dates out
    if ($dateOut < $dateIn){
        echo '<script>alert("Error!! Your inputted date is less than to your start date");history.go(-1);</script>';
    }
    else{
        $dateOutFormat = date_create($dateOut);
        $reservedOut = date_format($dateOutFormat,"m/d/Y ");
    }

    //Computation of range Date 
    if($dateIn == $dateOut){
        $rangeDate = 1;
    }
    else{
        $datetimeIn = date_create($dateIn);
        $datetimeOut = date_create($dateOut);

        $dateformat = date_diff($datetimeIn, $datetimeOut);
         $rangeDate = $dateformat->format("%a");
    }

    //Computation for Total Amount
    if($dateIn == $dateOut){
        $totalDate  = 1;
        $totalAmount = 1500 * (int)$totalRoom;
        $totalAmountStay = number_format($totalAmount,2);

    }else{

        $datetimeIn = date_create($dateIn);
        $datetimeOut = date_create($dateOut);
                                
        $date = date_diff($datetimeIn, $datetimeOut);
        $totalDate  = $date->format("%a");
        $totalAmount = ((int)$totalDate * $roomRate) * (int)$totalRoom ;
                                
        $totalAmountStay = number_format($totalAmount, 2);
    }

    //Computation for discount
    if((int)$totalRoom>=3){
        $discount = ($totalAmount * 10) / 100;
        $discountFormat = number_format($discount, 2);
    }
    else{
        $discount = 0;
        $discountFormat = $discount;
    }

    //Computation Of Amount Due
    if($dateIn == $dateOut){
        $totalDate  = 1;
        $roomRate = 1500;
        $totalAmount = ($totalDate * $roomRate) * (int)$totalRoom ;

        $amountDue =  $totalAmount - $discount;
    }
    
    $amountDue =  $totalAmount - $discount;

    $totalAmountDue = number_format($amountDue, 2);
    
    //Data that returns to page
    $data = [
        'dateIn' => $reservedIn, 
        'dateOut' => $reservedOut, 
        'rangeDate' => $rangeDate,
        'totalRoom' => $totalRoom,
        'totalAmountStay' => $totalAmountStay,
        'discount' => $discountFormat,
        'totalAmountDue' => $totalAmountDue,
        'firstName' => $firstName, 
        'lastName' => $lastName,
        'email' => $email, 
        'contactNumber' => $contactNum, 
    ];


    return view('receiptPage', $data);
});
//End of Receipt Page
