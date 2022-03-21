from django.urls import path
from . import views

# import of urls by the name of function in views
urlpatterns = [
    path('', views.home, name='home'),
    path('employeeList', views.employeeList, name='employeeList'),
    path('addEmployee', views.addEmployee, name='Add'),
    path('updateEmployee/<int:id>', views.updateEmployee, name='Update'),
    path('deleteEmployee/<int:id>', views.deleteEmployee, name='Delete'),

    path('leaveFormList', views.leaveList, name='Leave List'),
    path('addForm', views.addForm, name='Add Form'),
    path('updateForm/<int:id>', views.updateForm, name='Update Form'),
    path('deleteForm/<int:id>', views.deleteForm, name='Delete Form'),

    path('traineeList', views.traineeList, name='Leave List'),
    path('addTrainee', views.addTrainee, name='Add Form'),
    path('updateTrainee/<int:id>', views.updateTrainee, name='Update Form'),
    path('deleteTrainee/<int:id>', views.deleteTrainee, name='Delete Form'),

    path('departmentList', views.departmentList, name='Leave List'),
    path('addDepartment', views.addDepartment, name='Add Form'),
    path('updateDepartment/<int:id>', views.updateDepartment, name='Update Form'),
    path('deleteDepartment/<int:id>', views.deleteDepartment, name='Delete Form'),
    
]