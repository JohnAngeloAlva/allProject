from django.shortcuts import render

# Create your views here.
from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse, HttpResponseRedirect
from django.urls import reverse
from django.views import generic


from .models import Hirer, Employee

class IndexViewHirer(generic.ListView):
    template_name = 'polls/index.html'
    context_object_name = 'latest_job_list'

    def get_queryset(self):
        return Hirer.objects.order_by('jobTitle')

def detail(request, job_id):

    student = get_object_or_404(Hirer, pk=job_id)
    return render (request, 'polls/detail.html',{
        'job':job,
    })
    return HttpResponseRedirect(reverse('polls:index'))