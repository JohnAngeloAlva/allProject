from django.db import models

# Model for employee list
class Employee(models.Model):
    fullname = models.CharField(max_length=200)
    email = models.EmailField(max_length=200)
    address = models.TextField()
    phoneNum = models.TextField()

    def __str__(self):
        return self.email

# Model for leave form list
class leaveForm(models.Model):
    fullName = models.CharField(max_length=200)
    date = models.DateField()
    letter = models.TextField()

    def __str__(self):
        return self.fullName

class trainee(models.Model):
    fullname = models.CharField(max_length=200)
    email = models.EmailField(max_length=200)
    address = models.TextField()
    dateJoin = models.DateField()

    def __str__(self):
        return self.fullname

class department(models.Model):
    departmentName = models.CharField(max_length=200)
    departmentLocation = models.CharField(max_length=200)
    departmentNumber = models.TextField()

    def __str__(self):
        return self.fullname
