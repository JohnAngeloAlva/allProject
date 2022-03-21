from django.shortcuts import render, redirect
from . models import Employee, leaveForm, trainee, department
from django.contrib import messages

# Function for the homepage of the web app

def home(request):
    return render(request, '1home.html')

# Function to display list of employee and past it to the html
def employeeList(request):
    if 'search' in request.GET:
        search = request.GET['search']
        employee = Employee.objects.filter(fullname__icontains=search)
    else:
        employee = Employee.objects.all()
    return render(request, 'employeeList.html', {'employee':employee})

# Function for adding employee by getting all records
def addEmployee(request):
    if request.method == 'POST':
       fullname = request.POST['fullname']
       email = request.POST['email']
       address = request.POST['address']
       phoneNum = request.POST['phoneNum']
       Employee.objects.create(fullname=fullname, email=email, address=address, phoneNum=phoneNum)
       messages.success(request, "Data has been added")
    return render(request, 'addEmployee.html')

# Function for updateing employee by id
def updateEmployee(request, id):
    if request.method == 'POST':
       fullname = request.POST['fullname']
       email = request.POST['email']
       address = request.POST['address']
       phoneNum = request.POST['phoneNum']
       Employee.objects.filter(id=id).update(fullname=fullname, email=email, address=address, phoneNum=phoneNum)
       messages.success(request, "Data has been Updated")
    employee = Employee.objects.get(id = id)
    return render(request, 'updateEmployee.html', {'employee':employee})

# Function for the deleting employee by id
def deleteEmployee(request, id):
    Employee.objects.filter(id=id).delete()
    return redirect('/employeeList')


# Function to display list of Leave forms of employee and past it to the html
def leaveList(request):
    if 'search' in request.GET:
        search = request.GET['search']
        form = leaveForm.objects.filter(fullName__icontains=search)
    else:
        form = leaveForm.objects.all()
    form = leaveForm.objects.all()
    return render(request, 'leaveFormList.html', {'form':form})

# Function to add leave form of the employee
def addForm(request):
    if request.method == 'POST':
       fullName = request.POST['fullName']
       date = request.POST['date']
       letter = request.POST['letter']
       leaveForm.objects.create(fullName=fullName, date=date, letter=letter)
       messages.success(request, "Data has been added")
    return render(request, 'addForm.html')

# Function to update leave form of the employee by id
def updateForm(request, id):
    if request.method == 'POST':
       fullName = request.POST['fullName']
       date = request.POST['date']
       letter = request.POST['letter']
       leaveForm.objects.filter(id=id).update(fullName=fullName, date=date, letter=letter)
       messages.success(request, "Data has been added")
    form = leaveForm.objects.get(id = id)
    return render(request, 'updateForm.html', {'form':form})

# Function to delete leave form of the employee by id
def deleteForm(request, id):
    leaveForm.objects.filter(id=id).delete()
    return redirect('/leaveFormList')

# Function for Trainee List
def traineeList(request):
    if 'search' in request.GET:
        search = request.GET['search']
        Trainee = trainee.objects.filter(fullname__icontains=search)
    else:
        Trainee = trainee.objects.all()
    Trainee = trainee.objects.all()
    return render(request, 'traineeList.html', {'Trainee':Trainee})

# Function for adding Trainee 
def addTrainee(request):
    if request.method == 'POST':
       fullname = request.POST['fullname']
       email = request.POST['email']
       address = request.POST['address']
       dateJoin = request.POST['dateJoin']
       trainee.objects.create(fullname=fullname, email=email, address=address, dateJoin=dateJoin)
       messages.success(request, "Data has been added")
    return render(request, 'addTrainee.html')

# Function for updateing Trainee by id
def updateTrainee(request, id):
    if request.method == 'POST':
       fullname = request.POST['fullname']
       email = request.POST['email']
       address = request.POST['address']
       dateJoin = request.POST['dateJoin']
       trainee.objects.filter(id=id).update(fullname=fullname, email=email, address=address, dateJoin=dateJoin)
       messages.success(request, "Data has been Updated")
    Trainee = trainee.objects.get(id = id)
    return render(request, 'updateTrainee.html', {'Trainee':Trainee})

# Function for the deleting Trainee by id
def deleteTrainee(request, id):
    trainee.objects.filter(id=id).delete()
    return redirect('/traineeList')

# Function for Department List
def departmentList(request):
    if 'search' in request.GET:
        search = request.GET['search']
        Department = department.objects.filter(departmentName__icontains=search)
    else:
        Department = department.objects.all()
    Department = department.objects.all()
    return render(request, 'departmentList.html', {'Department':Department})

def addDepartment(request):
    if request.method == 'POST':
       departmentName = request.POST['departmentName']
       departmentLocation = request.POST['departmentLocation']
       departmentNumber = request.POST['departmentNumber']
       department.objects.create(departmentName=departmentName, departmentLocation=departmentLocation, departmentNumber=departmentNumber)
       messages.success(request, "Data has been added")
    return render(request, 'addDepartment.html')

# Function for updateing employee by id
def updateDepartment(request, id):
    if request.method == 'POST':
       departmentName = request.POST['departmentName']
       departmentLocation = request.POST['departmentLocation']
       departmentNumber = request.POST['departmentNumber']
       department.objects.filter(id=id).update(departmentName=departmentName, departmentLocation=departmentLocation, departmentNumber=departmentNumber)
       messages.success(request, "Data has been Updated")
    Department = department.objects.get(id = id)
    return render(request, 'updateDepartment.html', {'Department':Department})

# Function for the deleting employee by id
def deleteDepartment(request, id):
    department.objects.filter(id=id).delete()
    return redirect('/departmentList')
