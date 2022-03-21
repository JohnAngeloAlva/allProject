<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Edit Vaccinated Status</title>
</head>
<body>
    <h1>{{ $Title }}</h1>
    <div class="form-group">

        <form method="get" action="{{route('vaccinated')}}">
            @csrf
            <input type="hidden"class="form-control"  id="id" name="id" value="{{ $Info->id }}"><br>

            <label for="fname">First name:</label><br>
            <input type="text"class="form-control"  id="first_name" name="first_name" placeholder="Enter First Name" value="{{ $Info->first_name }}"><br>
            <label for="lname">Last name:</label><br>
            <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Enter Last Name" value="{{ $Info->last_name }}"><br>
            <label for="address">Address:</label><br>
            <input type="text"class="form-control"  id="address" name="address" placeholder="Enter Address" value="{{ $Info->address }}"><br>
            <label for="age">Age:</label><br>
            <input type="text" class="form-control"  id="age" name="age" placeholder="Enter Age" value="{{ $Info->age }}"><br>
            <label for="phone_number">Phone Number:</label><br>
            <input type="text" class="form-control"  id="phone_number" name="phone_number" placeholder="Enter Phone Number" value="{{ $Info->phone_number }}"><br>
            <label for="date">Dose Date:</label>
            <input type="date" id="doseDate" name="dose_date" ><br>
            <label for="dose">Dose:</label>
            <select name="dose" id="dose" > 
                <option value="{{ $Info->dose }}">{{ $Info->dose }}</option>
                <option value="2nd Dose">2nd Dose</option>
            </select><br>
            <label for="vaccine">Vaccine Type:</label>
            <select name="vaccine" id="vaccine">
                <option value="{{ $Info->vaccine }}">{{ $Info->vaccine }}</option>
            </select><br>
            <label for="status">Vaccine Status:</label>
            <select name="status" id="status">
                <option value="{{ $Info->status }}">{{ $Info->status }}</option>
                <option value="Completed">Fully Vaccinated</option>
            </select><br><br>

            <input type="submit" value="Fully Vaccinated">
        </form> 
    </div>
</body>
</html>