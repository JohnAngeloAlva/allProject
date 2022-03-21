from django.db import models

# Create your models here.

class HirerAccounts(models.Model):
    fname = models.CharField(max_length=200)
    lname = models.CharField(max_length=200)
    phoneNum= models.CharField(max_length=200)
    email = models.EmailField(max_length=100)
    password = models.CharField(max_length=200)

    def __str__(self):
        return self.email

class EmployeeAccounts(models.Model):
    fname = models.CharField(max_length=200)
    lname = models.CharField(max_length=200)
    phoneNum= models.CharField(max_length=200)
    email = models.EmailField(max_length=100)
    password = models.CharField(max_length=200)

    def __str__(self):
        return self.email

class Hirer(models.Model):
    jobTitle = models.CharField(max_length=200)
    jobCategory = models.CharField(max_length=200)
    jobDescription = models.TextField()
    jobRequirements = models.TextField()
    salary = models.CharField(max_length=200)
    jobNature = models.CharField(max_length=200)
    jobExperience = models.TextField()
    location = models.CharField(max_length=200)
    companyName = models.CharField(max_length=200)
    companyEmail = models.EmailField(max_length=200)

    def __str__(self):
        return self.jobTitle    

class Employee(models.Model):
    fullname = models.CharField(max_length=200)
    email = models.EmailField(max_length=200)
    letter = models.TextField()
    applicants = models.ForeignKey(Hirer, on_delete=models.CASCADE)

    def __str__(self):
        return self.fullname
  
