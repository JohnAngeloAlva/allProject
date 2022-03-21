//DECLARE MODULES
require('dotenv').config()
const express = require('express');
const cors = require('cors');
const mysql = require('mysql2')

//DECLARE VARIABLES
const PORT = process.env.PORT;
const app = express();

//USE THE MODULES
app.use(cors());
app.use(express.json());

//CONNECTION TO DATABASE
const con = mysql.createConnection({
    host: process.env.MYSQL_HOST,
    user: process.env.MYSQL_USER,
    password: process.env.MYSQL_PASSWORD,
    database: process.env.MYSQL_DATABASE
});

con.connect(function (err){
    if (err){
        console.log('Unable to connect to MYSQL Database');
        process.exit(1);
    }
});

//POST DATA FUNCTION
app.post('/event/registration', async function(req, res){

    try{

        const name = req.body.Name;
        const seat_destination = req.body.Seat_Destination;
        const phone_num = req.body.Phone_Num;
        const num_of_people = req.body.Num_of_People;
        const gmail = req.body.Gmail;

        const sql = 'INSERT INTO tblregistrations (Name, Seat_Destination, Phone_Num, Num_of_People, Gmail) VALUES ?';
        
        const values =[
            [ name, seat_destination, phone_num, num_of_people, gmail]
        ];

        con.query(sql, [values], function(err, result){

            if (err) res.status(500).json({
                message: process.env.ERROR_MESSAGE
            });

            console.log('Successfully register the registration details');

            res.status(201).json({
                message: ' Successfully register the registration details ',
            });
        });

    }catch(error){

        res.status(500).json({
            message: process.env.ERROR_MESSAGE,
        });

    }

}),
//END POST DATA FUNCTION

//GET DATA FUNCTION
app.get('/event/registration', async function(req, res){

    try{

        const sql = 'SELECT * FROM tblregistrations';

        con.query(sql, function(err, result){

            if (err) res.status(500).json({
                message: process.env.ERROR_MESSAGE
            });

            res.status(200).json({
                message: 'Successfully retrieved records',
                registration: result
            });

        });

    }catch(error){

        res.status(500).json({
            message: process.env.ERROR_MESSAGE,
        });
    }

});
//END GET DATA FUNCTION

//LISTENED TO PORT
app.listen(PORT, function(){
    console.log('Listening in port ' + PORT);
});