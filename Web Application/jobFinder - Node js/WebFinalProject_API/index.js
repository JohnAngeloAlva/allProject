//DECLARE MODULES
require('dotenv').config()
const express = require('express');
const cors = require('cors');
const mysql = require('mysql2');
const bcrypt = require('bcrypt');

//DECLARE VARIABLES
const PORT = process.env.PORT;
const { body, validationResult, check } = require('express-validator');
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

con.connect (function(err){
    if(err){
        console.log('Unable to connect to MYSQL Database');
        process.exit(1);
    };
    console.log('Connected to MYSQL Database');
});

//POST HIRER ACCOUNT 
app.post('/registration/accountHirer',[
    check('Fname', 'First name must not be empty').not().isEmpty(),
    check('Lname', 'Last name must not be empty').not().isEmpty(),
    check('Email', 'Email must not be empty').not().isEmpty(),
    check('PhoneNum', 'Phone Number must not be empty').not().isEmpty(),
    check('Password', 'Password must not be empty').not().isEmpty()
], async function(req, res){
    const errors = validationResult(req)
    if (!errors.isEmpty()) {
        console.log(errors.mapped());
       return res.status(422).json({ errors: errors.array() })
    }
    try{

        const fname = req.body.Fname;
        const lname = req.body.Lname;
        const email = req.body.Email;
        const phoneNum = req.body.PhoneNum;
        const password = req.body.Password;
        
        const sql = 'INSERT INTO tblhirer_accounts (Fname, Lname, Email, PhoneNum, Password) VALUES ?';

        const values = [
            [fname, lname, email, phoneNum, password]
        ];

        con.query(sql, [values], function(err, result){

            if(err) res.status(500).json({
                message: process.env.ERROR_MESSAGE
            });

            console.log('Successfully register hirer accounts');

            res.status(201).json({
                message: 'Successfully register accounts'
            });
        });

    }catch(error){

        res.status(500).json({
            message: process.env.ERROR_MESSAGE,
        });
    };

});
//END POST HIRER ACCOUNT 


//GET HIRER ACCOUNT 
app.get('/registration/accountHirer', async function(req, res){

    try{

        const sql = 'SELECT * FROM tblhirer_accounts';

        con.query(sql, function(err, result){

            if(err) res.status(500).json({
                message: process.env.ERROR_MESSAGE
            });

            res.status(200).json({
                message: 'Successfully retrieved records',
                accounts: result
            });

        });

    }catch(error){

        res.status(500).json({
            message: process.env.ERROR_MESSAGE,
        });
    }
});
//END GET HIRER ACCOUNT 

//POST EMPLOYEE ACCOUNT 
app.post('/registration/accountEmployees',[
    check('FirstName', 'First name must not be empty').not().isEmpty(),
    check('LastName', 'Last name must not be empty').not().isEmpty(),
    check('Email', 'Email must not be empty').not().isEmpty(),
    check('PhoneNumber', 'Phone Number must not be empty').not().isEmpty(),
    check('Password', 'Password must not be empty').not().isEmpty()
], async function(req, res){
    const errors = validationResult(req)
    if (!errors.isEmpty()) {
        console.log(errors.mapped());
       return res.status(422).json({ errors: errors.array() })
    }

    try {

        const employeeFirstName = req.body.FirstName;
        const employeeLastName = req.body.LastName;
        const employeePhoneNumber = req.body.PhoneNumber;
        const employeeEmail = req.body.Email;
        const employeePassword = req.body.Password;

        const sql = 'INSERT INTO tblemployee_accounts (FirstName, LastName, PhoneNumber, Email, Password) VALUES ?';
        const values = [
            [employeeFirstName, employeeLastName, employeePhoneNumber, employeeEmail, employeePassword]
        ]

        con.query(sql, [values], function(err, result){

            if (err) res.status(500).json({
                message : process.env.ERROR_MESSAGE
            });

            console.log('Successfully register employee accounts');

            res.status(201).json({
                message : 'Successfully register employee accounts'
            });
        });


    } catch(error) {
        res.status(500).json({
            message : process.env.ERROR_MESSAGE
        });
    }
}); 
//END POST EMPLOYEE ACCOUNT 

//GET EMPLOYEE ACCOUNT 
app.get('/registration/accountEmployees', async function(req, res){

    try{

        const sql = 'SELECT * FROM tblemployee_accounts';

        con.query(sql, async function(err, result){

            if(err) res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });

            res.status(200).json({
                message: 'Successfully retrieved records',
                accounts: result
            });

        });

    }catch(error){

        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE,
        });
    }
});
//END GET EMPLOYEE ACCOUNT 

/*********************************************************************************************************/

//POST HIRER JOB
app.post('/registration/jobHirer',[
    check('JobTitle', 'Title is Required').not().isEmpty(),
    check('JobCategory', 'Category is Required').not().isEmpty(),
    check('JobDescription', 'Description is Required').not().isEmpty(),
    check('JobRequirements', 'Requirements is Required').not().isEmpty(),
    check('Salary', 'Salary is Required').not().isEmpty(),
    check('JobNature', 'Job Nature is Required').not().isEmpty(),
    check('JobExperience', 'Experience is Required').not().isEmpty(),
    check('Location', 'Location is Required').not().isEmpty(),
    check('CompanyName', 'Company Name is Required').not().isEmpty(),
    check('CompanyEmail', 'Company Email is Required').not().isEmpty(),
    check('CompanyDescription', 'Company Description is Required').not().isEmpty()
], async function(req, res){
    const errors = validationResult(req)
    if (!errors.isEmpty()) {
        console.log(errors.mapped());
       return res.status(422).json({ errors: errors.array() })
    }

    try{

        const jTitle = req.body.JobTitle;
        const jCategory = req.body.JobCategory;
        const jDescription = req.body.JobDescription;
        const jRequirements = req.body.JobRequirements;
        const salary = req.body.Salary;
        const jNature = req.body.JobNature;
        const jExperiecnce = req.body.JobExperience;
        const location = req.body.Location;
        const companyName = req.body.CompanyName;
        const companyEmail = req.body.CompanyEmail;
        const companyDescription = req.body.CompanyDescription;

        const sql = 'INSERT INTO tblhirer_jobs (JobTitle, JobCategory, JobDescription, JobRequirements, Salary, JobNature, JobExperience, Location, CompanyName, CompanyEmail, CompanyDescription) VALUES ?';
        const values = [
            [jTitle, jCategory, jDescription, jRequirements, salary, jNature, jExperiecnce, location, companyName, companyEmail ,companyDescription]
        ];

        con.query(sql, [values], function(err, result){

            if(err) res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });

            console.log('Successfully register job');

            res.status(201).json({
                message: 'Successfully register job'
            });

        });

    }catch(error){

        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE,
        });
    }

});
//END POST HIRER JOB

//GET HIRER JOB
app.get('/registration/jobHirer', async function(req,res){

    try{

        const search = '%' + req.query.searchkeyword + '%';

        const sql = 'SELECT * FROM tblhirer_jobs WHERE Location like ?';
        const values = [search,search]

        con.query(sql, values, function(err, result){

            if(err) res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });

            res.status(200).json({
                message: 'Successfully retrieved jobs',
                jobs: result
            });

        });

    }catch(error){

        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE,
        });
    }
});
//END GET HIRER JOB

//GET HIRER JOB BY ID
app.get('/registration/jobHirer/:id', async function(req,res){

    try{

        const id = req.params.id;

        const sql = 'SELECT * FROM tblhirer_jobs WHERE id= ?';
        const values = [id];

        con.query(sql, values, function(err,result){

            if(err) res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });

            res.status(200).json({
                message: 'Successfully retrieved jobs',
                jobs: {
                    id: result[0].id,
                    JobTitle: result[0].JobTitle,
                    JobCategory: result[0].JobCategory,
                    JobDescription: result[0].JobDescription,
                    JobRequirements: result[0].JobRequirements,
                    Salary: result[0].Salary,
                    JobNature: result[0].JobNature,
                    JobExperience: result[0].JobExperience,
                    Location: result[0].Location,
                    CompanyName: result[0].CompanyName,
                    CompanyEmail: result[0].CompanyEmail,
                    CompanyDescription: result[0].CompanyDescription
                    
                }

            });
        });

    }catch(error){

        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE,
        });

    }

});
//GET HIRER JOB BY ID

//PUT HIRER JOB
app.put('/registration/jobHirer/:id',[
    check('JobTitle', 'Title is Required').not().isEmpty(),
    check('JobCategory', 'Category is Required').not().isEmpty(),
    check('JobDescription', 'Description is Required').not().isEmpty(),
    check('JobRequirements', 'Requirements is Required').not().isEmpty(),
    check('Salary', 'Salary is Required').not().isEmpty(),
    check('JobNature', 'Job Nature is Required').not().isEmpty(),
    check('JobExperience', 'Experience is Required').not().isEmpty(),
    check('Location', 'Location is Required').not().isEmpty(),
    check('CompanyName', 'Company Name is Required').not().isEmpty(),
    check('CompanyEmail', 'Company Email is Required').not().isEmpty(),
    check('CompanyDescription', 'Company Description is Required').not().isEmpty()
], async function (req, res) {
    const errors = validationResult(req)
    if (!errors.isEmpty()) {
        console.log(errors.mapped());
       return res.status(422).json({ errors: errors.array() })
    }
    try{

        const id = req.params.id;

        const jTitle = req.body.JobTitle;
        const jCategory = req.body.JobCategory;
        const jDescription = req.body.JobDescription;
        const jRequirements = req.body.JobRequirements;
        const salary = req.body.Salary;
        const jNature = req.body.JobNature;
        const jExperiecnce = req.body.JobExperience;
        const location = req.body.Location;
        const companyName = req.body.CompanyName;
        const companyEmail = req.body.CompanyEmail;
        const companyDescription = req.body.CompanyDescription;

        const sql = 'UPDATE tblhirer_jobs SET JobTitle = ?, JobCategory = ?, JobDescription = ?, JobRequirements = ?, Salary = ?, JobNature = ?, JobExperience = ?, Location = ?, CompanyName = ?, CompanyEmail = ?, CompanyDescription = ? WHERE id = ? ';
        const values = [jTitle, jCategory, jDescription, jRequirements, salary, jNature, jExperiecnce, location, companyName, companyEmail,companyDescription,id];

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
//END PUT HIRER JOB

//DELETE HIRER JOB 
app.delete('/registration/jobHirer/:id', async function(req,res){

    try{

        const id = req.params.id;

        const sql = 'DELETE FROM tblhirer_jobs WHERE id = ?';
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
//END DELETE HIRER JOB

/*********************************************************************************************************/

//POST EMPLOYEE
app.post('/registration/employee',[
    check('FullName', 'Full Name is Required').not().isEmpty(),
    check('Age', 'Age is Required').not().isEmpty(),
    check('Email', 'Email is Required').not().isEmpty(),
    check('PhoneNumber', 'Phone Number is Required').not().isEmpty(),
    check('Code', 'Code is Required').not().isEmpty(),
    check('JobCategory', 'Job Category is Required').not().isEmpty()
], async function(req,res){
    const errors = validationResult(req)
    if (!errors.isEmpty()) {
        console.log(errors.mapped());
       return res.status(422).json({ errors: errors.array() })
    }
   try{
        const fullname = req.body.FullName;
        const age = req.body.Age;
        const email = req.body.Email;
        const phoneNum = req.body.PhoneNumber;
        const code = req.body.Code;
        const category= req.body.JobCategory;
        

        const sql = 'INSERT INTO tblemployee (FullName, Age, Email, PhoneNumber, Code, JobCategory) VALUES ? ';
        const values = [
            [fullname, age, email, phoneNum, code,category]
        ]

        con.query(sql,[values], function(err,result){

            if(err) return res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });

            console.log('Successfully register application');

            res.status(201).json({
                message: 'Successfully register application'
            });

         });

   }catch(erorr){

        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE

        });

   }
});
//END POST EMPLOYEE

//GET EMPLOYEE APPLICANT FOR THE JOB
app.get('/registration/employeeApplicant',async function(req,res){

    try{

        const search = '%' + req.query.searchkeyword + '%';

        const sql = 'SELECT * FROM tblemployee WHERE JobCategory like ?  ';
        const values = [search,search]

        con.query(sql, values,function(err,result){

            if(err) res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });


            res.status(200).json({
                message: 'Successfully retrieved employee applications',
                employee:result
                    
                
            });

        });

    }catch(error){

        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE

        });
    }
    
});
//END GET EMPLOYEE

//GET EMPLOYEE BY ID
app.get('/registration/employeeApplicant/:employee_id', async function(req,res){

    try{

        const employee_id = req.params.employee_id;

        const sql = 'SELECT * FROM tblemployee WHERE employee_id= ?';
        const values = [employee_id];

        con.query(sql, values, function(err,result){

            if(err) res.status(500).json({
                message: process.env.GENERIC_ERROR_MESSAGE
            });

            res.status(200).json({
                message: 'Successfully retrieved jobs',
                employee: {
                    employee_id: result[0].employee_id,
                    FullName: result[0].FullName,
                    Age: result[0].Age,
                    Email: result[0].Email,
                    PhoneNumber: result[0].PhoneNumber,
                    Code: result[0].Code,
                    JobCategory: result[0].JobCategory,
                }

            });
        });

    }catch(error){

        res.status(500).json({
            message: process.env.GENERIC_ERROR_MESSAGE,
        });

    }

});
//END GET EMPLOYEE BY ID

//LISTENED TO PORT
app.listen(PORT, function(){
    console.log('Listening in port ' + PORT);
});
