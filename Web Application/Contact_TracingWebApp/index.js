require('dotenv').config();


const express = require('express');
const cors = require('cors');
const mysql = require('mysql2');

const { request } = require('express');

//this is the port of our server
const PORT = process.env.PORT;
console.log(PORT);

//declaring an express object
const app = express();

app.use(cors());
app.use(express.json());

const con = mysql.createConnection({
    host: process.env.MYSQL_HOST,
    user: process.env.MYSQL_USER,
    password: process.env.MYSQL_PASSWORD,
    database: process.env.MYSQL_DATABASE,
});

con.connect(function(err) {
    if(err) {
        console.log('Unable to connect to MYSQL Database');
        process.exit(1);
    }
    console.log('Connected to MYSQL Database');
})

app.post('/api/covidtracing', async function(req, res){

    try{

        const name = req.body.Name;
        const age = req.body.Age;
        const locations = req.body.Location;
        const symptoms = req.body.Symptoms;
        const contactnumber = req.body.ContactNumber;
        
        const sql = 'INSERT INTO positivetbl (Name, Age, Location, Symptoms, ContactNumber ) VALUES ?';

        const values = [
            [name, age, locations, symptoms, contactnumber]
        ];

        con.query(sql, [values], function(err, result){

            if(err) return res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });

            console.log('Successfully register');

            res.status(201).json({
                message: 'Successfully register '
            });
        });

    }catch(error){

        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE,
        });
    };

});
//k
app.get('/api/covidtracing', async function (req, res) {

    try {
        const search = '%' + req.query.searchkeyword + '%';

        const sql = 'SELECT * FROM positivetbl WHERE Name like ? ';
        const values = [search,search]

        con.query(sql, values, function(err, result) {
            if(err) return res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });

            res.status(200).json({
                message: 'Successfully retrieved records',
                covidtracing: result
            });

        });

    } catch(err) {
        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE
        });
    }
    
});


app.listen(PORT, () => {
    console.log('Listening at port ' + PORT);
});