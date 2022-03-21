
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

    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css">
    <title>Admin Page</title>
</head>
<body>
    <h1>AdminPage</h1>

    <div>
        <table id="datatable" class="table table-hover">
            <thead>
                <th>ID Number</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Address</th>
                <th>Age</th>
                <th>Phone Number</th>
                <th>Dose Date</th>
                <th>Dose</th>
                <th>Vaccine</th>
                <th>Status</th>
                <th>Actions</th>
            </thead>
            <tbody>

                @foreach ($patientList as $list)
                <tr>
                    <td>{{ $list->id }}</td>
                    <td>{{ $list->first_name }}</td>
                    <td>{{ $list->last_name }}</td>
                    <td>{{ $list->address }}</td>
                    <td>{{ $list->age }}</td>
                    <td>{{ $list->phone_number }}</td>
                    <td>{{ $list->dose_date }}</td>
                    <td>{{ $list->dose }}</td>
                    <td>{{ $list->vaccine }}</td>
                    <td>{{ $list->status }}</td>
                    <td>
                        <a href="edit/{{ $list->id }}"class="btn btn-primary btn-xs" >Complete 1st Dose</a>
                        <a href="editStatus/{{ $list->id }}"class="btn btn-secondary btn-xs" >Fully Vaccinated</a>
                        <a href="delete/{{ $list->id }}"class="btn btn-danger btn-xs " >Delete</a> 
                        
                    </td>
                </tr>
                @endforeach

            </tbody>
        </table>

    </div>

    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>

    <script>
        $(document).ready(function () {

            var table = $('#datatable').DataTable();

        });
    </script>

</body>
</html>