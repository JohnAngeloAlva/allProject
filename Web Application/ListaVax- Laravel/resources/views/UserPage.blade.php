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
    <title>User Page</title>
</head>
<body>
    <h1>UserPage</h1>
    <div class="form-group">

        @if(count($errors) > 0 )
            <div class="alert alert-danger">
                <ul>
                    @foreach($errors->all() as $error)
                        <li>{{ $error }}</li>
                    @endforeach
                </ul>
            </div>
        @endif

        @if(\Session::has('success'))
            <div class="alert alert-success">
                <p>{{ Session:: get('success') }}</p>
            </div>
        @endif

        @if(\Session::has('fail'))
            <div class="alert alert-danger">
                <p>{{ Session:: get('fail') }}</p>
            </div>
        @endif

        <form method="post" action="add">
            @csrf
            <label for="fname">First name:</label><br>
            <input type="text"class="form-control"  id="first_name" name="first_name" placeholder="Enter First Name" value="{{ old('first_name') }}"><br>
            <label for="lname">Last name:</label><br>
            <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Enter Last Name" value="{{ old('last_name') }}"><br>
            <label for="address">Address:</label><br>
            <input type="text"class="form-control"  id="address" name="address" placeholder="Enter Address" value="{{ old('address') }}"><br>
            <label for="age">Age:</label><br>
            <input type="text" class="form-control"  id="age" name="age" placeholder="Enter Age" value="{{ old('age') }}"><br>
            <label for="phone_number">Phone Number:</label><br>
            <input type="text" class="form-control"  id="phone_number" name="phone_number" placeholder="Enter Phone Number" value="{{ old('phone_number') }}"><br>
            <label for="date">Dose Date:</label>
            <input type="date" id="doseDate" name="dose_date"><br>
            <label for="dose">Dose:</label>
            <select name="dose" id="dose">
                <option value="1st Dose">1st Dose</option>
            </select><br>
            <label for="vaccine">Vaccine Type:</label>
            <select name="vaccine" id="vaccine">
                <option value="Pfizer">Pfizer</option>
                <option value="Moderna">Moderna</option>
                <option value="Janssen">Janssen (Johnson & Johnson)</option>
                <option value="Astrazeneca">AstraZeneca</option>
                <option value="Covishield">Covishield</option>
                <option value="Covaxin">Covaxin</option>
                <option value="Sinopharm">Sinopharm</option>
                <option value="Sinovac">Sinovac</option>
            </select><br>
            <label for="status">Vaccine Status:</label>
            <select name="status" id="status">
                <option value="Pending">Pending</option>
            </select><br><br>

            <input type="submit" value="Submit">
        </form> 
    </div>
</body>
</html>