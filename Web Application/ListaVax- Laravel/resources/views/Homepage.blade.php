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
    <title>Homepage</title>
</head>
<body>
    <h1>Homepage</h1>
    <h2>Login as</h2>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Admin</button>

    <a href = "patients" ><button type="button" class="btn btn-primary">User</button></a>

        <!-- The Modal -->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
        <div class="modal-content">
        
            <!-- Modal Header -->
            <div class="modal-header">
            <h4 class="modal-title">Modal Heading</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            
            <!-- Modal body -->
            <div class="modal-body">

                <h2>Login As Admin</h2>

                <label>Username</label>
                <input type="text" id="username" name="username" placeholder="Enter username"><br />
                <label>Password</label>
                <input type="text" id="password" name="password" placeholder="Enter password"><br />


                </div>
            
            <!-- Modal footer -->
            <div class="modal-footer">
            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            <a href = "" onclick = "this.href='admin?auth='+document.getElementById('username').value+document.getElementById('password').value" ><button type="button" class="btn btn-primary">Admin</button></a>
            
            </div>
            
        </div>
        </div>
    </div>
</body>
</html>
