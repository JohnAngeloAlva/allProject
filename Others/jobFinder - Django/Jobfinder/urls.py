from django.urls import path

from . import views

app_name='polls'

urlpatterns =[

    path('', views.IndexViewHirer.as_view(), name='index'),
    path('<int:job_id>/', views.detail, name='detail')

]