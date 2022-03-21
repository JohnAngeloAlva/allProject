from django.contrib import admin
from . models import Employee, leaveForm, trainee, department

# regiration of models in admin site
admin.site.register(Employee)
admin.site.register(leaveForm)
admin.site.register(trainee)
admin.site.register(department)