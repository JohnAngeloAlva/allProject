require('dotenv').config();

const express = require('express');
const cors = require('cors');
const mysql = require('mysql2');

const PORT = process.env.PORT;

const app = express();

app.use(cors());
app.use(express.json());

const con = mysql.createConnection({
    host: process.env.MYSQL_HOST,
    user: process.env.MYSQL_USER,
    password: process.env.MYSQL_PASSWORD,
    database: process.env.MYSQL_DATABASE
});

con.connect(function(err){
    if (err) {
        console.log('Unable to connect to mysql database');
        process.exit(1);
    }
});

app.post('/api/customers', async function (req, res){

    try{

        const sql = 'INSERT INTO customerdetails_tbl (name, number_of_tickets, date, movie_name) VALUES (\'Angelah Garcia\', \'2\', \'2021-04-23\', \'Vincenzo\')';

        con.query(sql, function(err, result){

            if(err) res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });

            console.log('Successfully created record');

            res.status(201).json({
                message: 'Successfully created a record'
            });
        })


    } catch(error){
        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE
        });
    }
    
});

app.listen(PORT, function(){
    console.log('Listening at port ' + PORT);
});