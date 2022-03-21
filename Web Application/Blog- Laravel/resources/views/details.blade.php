<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="css/detailsPage.css" rel="stylesheet" type="text/css" >
    <title>Favorite foods List</title>
</head>
<body>
    
    <!--Return the value in the controller array-->

    <table style="width:100%">
        <caption> My Top 10 Favorite Foods</caption>
            <tr>
                <th colspan="1">Dish Name</th>
                <th>Description</th>
            </tr>
            @isset($foods)
                @foreach ($foods as $food)
                <tr>
                    <td>
                        <img src="{{ $food['image'] }}" alt="Dish Picture" width="300" height="300"> 
                        <p> {{$food['id'] }} . {{ $food['name'] }} </p> 
                    </td>
                    <td><p>{{ $food['description'] }}</p></td>

                </tr>
                @endforeach
            @endisset
    </table>

    <!--End Return value in the controller array-->
   
</body>
</html>