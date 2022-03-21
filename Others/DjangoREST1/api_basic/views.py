from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from rest_framework.parsers import JSONParser
from .models import Article
from .serializer import ArticleSerializer

# Create your views here.

from django.views.decorators.csrf import csrf_exempt

@csrf_exempt

def article_list(request):

    if request.method ==  "GET":
        articles = Article.objects.all()
        serializer = ArticleSerializer(articles, many=True)
        return JsonResponse(serializer.data,safe=False)
    
    elif request.method == "POST":
        data = JSONParser().parse(request)
        serializer =ArticleSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data,status=201)
        return JsonResponse(serializer.errors,status=400)

def article_detail(request,pk):
    try:
        article = Article.objects.get(pk=pk)
    except Article.DoesNotExist:
        return HttpResponse(status=400)

    if request.method == "GET":
        serializer = ArticleSerializer(article)
        return JsonResponse(serializer.data)
    
    elif request.method == "PUT":
        data = JSONParser().parse(request)
        serializer =ArticleSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data,status=201)
        return JsonResponse(serializer.errors,status=400)
    
    elif request.method == "DELETE":
        article.delete()
        return HttpResponse(status=204)