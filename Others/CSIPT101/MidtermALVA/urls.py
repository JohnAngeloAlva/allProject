from django.urls import path
from . import views

app_name = 'polls'
urlpatterns = [
    
    path('', views.IndexView.as_view(), name='index'),
    
    path('<int:student_id>/', views.detail, name='detail'),

]
