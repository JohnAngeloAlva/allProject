from django.db import models

# Create your models here.

class Student(models.Model):
    studentNumber = models.CharField(max_length=200)
    fullName = models.CharField(max_length=200)
    course = models.CharField(max_length=200)
    year= models.CharField(max_length=200)

    def __str__(self):
       return self.studentNumber
    
         
class Grades(models.Model):
    student = models.ForeignKey(Student, on_delete=models.CASCADE)
    subjectCode = models.CharField(max_length=200)
    grade = models.IntegerField(default=0)

    def __str__(self):
       return self.subjectCode
   
       