
from django.contrib import admin

# Register your models here.
from .models import HirerAccounts, EmployeeAccounts, Employee, Hirer


admin.site.register(HirerAccounts)
admin.site.register(EmployeeAccounts)
admin.site.register(Hirer)
admin.site.register(Employee)