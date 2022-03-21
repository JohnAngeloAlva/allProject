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
app.post('/api/employees', async function(req, res){

    try{

        const name = req.body.name;
        const designation = req.body.designation; 

        const sql = 'INSERT INTO tblemployee (name, designation) VALUES ?';
        const values = [
            [name,designation]
        ]
        
        con.query(sql, [values], function(err, result){

            if (err) res.status(500).json({
                message: process.env.ERROR_MESSAGE
            });

            console.log('Successfully register record');

            res.status(201).json({
                message: ' Successfully register record ',
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
app.get('/api/employees', async function(req, res){

    try{
        const search = '%' + req.query.searchkeyword + '%';

        const sql = 'SELECT * FROM tblemployee WHERE name like ? or designation like ? ';

        con.query(sql, [search,search], function(err, result){

            if (err) res.status(500).json({
                message: process.env.ERROR_MESSAGE
            });

            res.status(200).json({
                message: 'Successfully retrieved records',
                employees: result
            });

        });

    }catch(error){

        res.status(500).json({
            message: process.env.ERROR_MESSAGE,
        });
    }

});
//END GET DATA FUNCTION

app.get('/api/employees/:id', async function(req,res){

    try{

        const id = req.params.id;

        const sql = 'SELECT * FROM tblemployee WHERE id = ?';
        const values = [id];

        con.query(sql, values, function(err,result){
            if (err) res.status(500).json({
                message: process.env.ERROR_MESSAGE
            });

            res.status(200).json({
                message: 'Successfully retrieved records for id ' + id,
                employee:{
                    id: result[0].id,
                    name: result[0].name,
                    designation: result[0].designation
                }
            });

        });

    }catch(error){

        res.status(500).json({
            message: process.env.ERROR_MESSAGE,
        });
    }
});

//PUT DATA FUNCTION
app.put('/api/employees/:id', async function (req, res) {
    
    try{

        const id = req.params.id;

        const name = req.body.name;
        const designation = req.body.designation;

        const sql = 'UPDATE tblemployee SET name = ?, designation = ? WHERE id = ? ';
        const values = [name, designation, id];

        con.query(sql, values, function(err, result) {

            if(err) res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });

            console.log('Successfully updated record');

            res.status(201).json({
                message: 'Successfully updated record'
            });

        })

    } catch(error) {
        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE
        });
    }
    
});
//END PUT DATA FUNCTION

//DELETE DATA FUNCTION

app.delete('/api/employees/:id', async function(req,res){

    try{

        const id = req.params.id;

        const sql = 'DELETE FROM tblemployee WHERE id = ?';
        const values = [id];

        con.query(sql, values, function(err,result){

            if(err) res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });

            console.log('Successfully deleted record');

            res.status(201).json({
                message: 'Successfully deleted record'
            });

        });

    }catch(error){

        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE

        });
    }
});

//END DELETE DATA FUNCTION

//LISTENED TO PORT
app.listen(PORT, function(){
    console.log('Listening in port ' + PORT);
});