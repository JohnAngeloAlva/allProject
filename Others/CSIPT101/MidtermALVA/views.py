from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse, HttpResponseRedirect
from django.urls import reverse
from django.views import generic
from django.template import loader

from .models import Student, Grades


class IndexView(generic.ListView):
    template_name = 'polls/index.html'
    context_object_name = 'latest_student_list'

    def get_queryset(self):
        return Student.objects.order_by('fullName')

def detail(request, student_id):

    student = get_object_or_404(Student, pk=student_id)
    return render (request, 'polls/detail.html',{
        'student': student,  
    })
    return HttpResponseRedirect(reverse('polls:index'))
  
 



