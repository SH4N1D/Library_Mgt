import smtplib
from datetime import datetime
from email.mime.text import MIMEText

from django.contrib import auth
from django.contrib.auth.decorators import login_required
from django.core.files.storage import FileSystemStorage
from django.db.models import Q
from django.http import HttpResponse
from django.shortcuts import render, redirect
from datetime import datetime, date, timedelta
from libraryapp.models import *
import cv2
def logout(request):
    auth.logout(request)
    return render(request,'login_index.html')

def main(request):
    return render(request,'login_index.html')

# def maincode(request):
#     return render(request,'login_index.html')

def login_action(request):
    try:
        username1=request.POST['textfield']
        password1=request.POST['password']
        login_obj=login_table.objects.get(username=username1,password=password1)
        print(login_obj,"kkkkkkkkkkkkk")
        if login_obj.type == "admin":
            ob1= auth.authenticate(username='admin', password='123')
            if ob1 is not None :
                auth.login(request,ob1)
                request.session['lid']= login_obj.id
            return HttpResponse('''<script> alert("LOGIN SUCCESSFUL");window.location="/linklist"</script>''')
        else:
            return HttpResponse('''<script> alert("jjjjj");window.location="/linklist"</script>''')
    except Exception as e:
        return HttpResponse('''<script> alert("INVALID USERNAME OR PASSWORD");window.location="/"</script>''')

@login_required(login_url='/')
def linklist(request):
    return render(request, 'img.html')

@login_required(login_url='/')
def home(request):
    return render(request, 'img.html')

@login_required(login_url='/')
def library(request):
    ob=library_table.objects.all()
    return render(request,'LIBRARY.html',{'val':ob})

@login_required(login_url='/')
def librarysearch(request):
    libname=request.POST['textfield']
    ob=library_table.objects.filter(name__icontains=libname)
    return render(request, 'LIBRARY.html', {'val': ob})

@login_required(login_url='/')
def accept(request,id):
    ob=login_table.objects.get(id=id)
    ob.type='library'
    ob.save()
    return HttpResponse('''<script>alert("ACCEPTED");window.location="/library#about"</script>''')

@login_required(login_url='/')
def reject(request,id):
    ob=login_table.objects.get(id=id)
    ob.type='reject'
    ob.save()
    return HttpResponse('''<script> alert("REJECTED"); window.location="/library#about"</script>''')

@login_required(login_url='/')
def block(request,id):
    ob=login_table.objects.get(id=id)
    ob.type='block'
    ob.save()
    return HttpResponse('''<script>alert("BLOCKED");window.location="/blockunblock#about"</script>''')

@login_required(login_url='/')
def unblock(request,id):
    ob=login_table.objects.get(id=id)
    ob.type='user'
    ob.save()
    return HttpResponse('''<script>alert("UNBLOCKED");window.location="/blockunblock#about"</script>''')


@login_required(login_url='/')
def complainttable(request):
    ob = complaint.objects.all()
    return render(request,'COMPLAINT TABLE.html',{'val':ob})

@login_required(login_url='/')
def complaintsearch(request):
    comdate=request.POST['textfield']
    ob=complaint.objects.filter(date=comdate)
    return render(request,'COMPLAINT TABLE.html', {'val': ob})

@login_required(login_url='/')
def reply(request,id):
    request.session['cid']=id
    return render(request,'REPLY.html')

@login_required(login_url='/')
def creply(request):
    coreply = request.POST['textfield2']
    ob = complaint.objects.get(id=request.session['cid'])
    ob.reply = coreply
    ob.save()
    return HttpResponse('''<script>alert("REPLIED");window.location="/complainttable#about"</script>''')

@login_required(login_url='/')
def notificationupdate(request):
    ob = notificaton.objects.all()
    return render(request,'NOTIFICATION UPDATE.html',{'val':ob})

@login_required(login_url='/')
def notificationsearch(request):
    notfydate=request.POST['textfield']
    ob=notificaton.objects.filter(date=notfydate)
    return render(request, 'NOTIFICATION UPDATE.html', {'val': ob})

@login_required(login_url='/')
def sendnotification(request):
    ob=notificaton.objects.all()
    return render(request,'SEND NOTIFICATION.html',{'val':ob})

@login_required(login_url='/')
def addnotfy(request):
    notfy=request.POST['textfield2']
    ob=notificaton()
    ob.notification=notfy
    ob.date=datetime.today()
    ob.save()
    return HttpResponse('''<script>alert("NOTIFICATION SENT");window.location="/notificationupdate#about"</script>''')

@login_required(login_url='/')
def notdel(request,id):
    ob=notificaton.objects.get(id=id)
    ob.delete()
    return HttpResponse('''<script>alert("SUCCESSFULLY DELETED");window.location="/notificationupdate#about"</script>''')

@login_required(login_url='/')
def ratings(request):
    ob = rating.objects.all()
    return render(request,'RATINGS.html',{'val':ob})

@login_required(login_url='/')
def ratingsearch(request):
    ratingdate=request.POST['textfield']
    ob=rating.objects.filter(date=ratingdate)
    return render(request, 'RATINGS.html', {'val': ob})

@login_required(login_url='/')
def blockunblock(request):
    ob = report.objects.all()
    return render(request,'blockunblock.html',{'val':ob})

@login_required(login_url='/')
def reportsearch(request):
    reportname=request.POST['textfield']
    ob=report.objects.filter(USER__fname__icontains=reportname)
    return render(request, 'blockunblock.html', {'val': ob})


# @login_required(login_url='/')
# def feed_back(request):
#     ob = feedback.objects.all()
#     return render(request,'FEEDBACK.html',{'val':ob})
#
# @login_required(login_url='/')
# def feedbacksearch(request):
#     fdbkdate=request.POST['textfield']
#     ob=feedback.objects.filter(date=fdbkdate)
#     return render(request, 'FEEDBACK.html', {'val': ob})





from django.core import serializers
import json
from django.http import JsonResponse

def android_logincode(request):
     print(")))))))))))))))))",request.POST)
     un=request.POST['uname']
     pwd=request.POST['pass']
     print(un,pwd)
     try:
     # if un == un:
         ob = login_table.objects.get(username=un,password=pwd)
         if ob is None:
                data = {"task": "invalid"}
                r = json.dumps(data)
         else:
                img=""
                if(ob.type=='user'):
                    obu=user_table.objects.get(LOGIN__id=ob.id)
                    img=obu.profile.url
                elif ob.type=="library":
                    obl=library_table.objects.get(LOGIN__id=ob.id)
                    img=obl.image.url
                data = {"task":"valid","type":ob.type,"id":ob.id,"img":img}
                r = json.dumps(data)
         print (r)
         return HttpResponse(r)
     except:
         data = {"task": "invalid"}
         r = json.dumps(data)
         print(r)
         return HttpResponse(r)




#library


def library_register(request):


    uname = request.POST['uname']
    pswd = request.POST['pswd']

    name=request.POST['name']
    place=request.POST['place']
    email=request.POST['email']
    phoneno=request.POST['phoneno']
    latitude=request.POST['latitude']
    longitude=request.POST['longitude']
    image=request.FILES['file']
    fs =FileSystemStorage()
    fsave =fs.save(image.name,image)

    lob = login_table()
    lob.username = uname
    lob.password = pswd
    lob.type = 'pending'
    lob.save()

    ob= library_table()
    ob.name=name
    ob.place=place
    ob.email=email
    ob.phoneno=phoneno
    ob.latitude=latitude
    ob.longitude=longitude
    ob.image=fsave
    ob.LOGIN = lob
    ob.save()

    # img = cv2.imread(r"C:\Users\shani\PycharmProjects\librarymnt\media/" + fsave)
    # stretch_near = cv2.resize(img, (780, 540),
    #                           interpolation=cv2.INTER_LINEAR)
    # cv2.imwrite(r"C:\Users\shani\PycharmProjects\librarymnt\media/" + fsave, stretch_near)


    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)

def view_chat(request):
    ob=user_table.objects.all()
    data = []
    for i in ob:
        row = {"uid":i.LOGIN.id,"pr":i.profile.url,"fn":i.fname,"ln":i.lname}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def view_chat_search(request):
    name=request.POST['name']
    ob=user_table.objects.filter(fname__istartswith=name)
    data = []
    for i in ob:
        row = {"uid":i.LOGIN.id,"pr":str(i.profile.url),"fn":i.fname,"ln":i.lname}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)
#________________________________________________________lib to user______

def lib_insert_chat(request):
    fromid=request.POST["fid"]
    toid=request.POST["tid"]
    msg=request.POST["msg"]

    ob=chat()
    ob.fromid=login_table.objects.get(id=fromid)
    ob.toid=login_table.objects.get(id=toid)
    ob.message=msg
    ob.date=datetime.now().strftime("%Y-%m-%d")
    ob.time=datetime.now()

    ob.save()

    return JsonResponse({"task":"ok"})

def view_message2(request):
    print("wwwwwwwwwwwwwwww")

    fromid = request.POST['fid']
    print(fromid)
    toid = request.POST['toid']
    print(toid)
    lmid = request.POST['lastmsgid']
    print("msgggggggggggggggggggggg" + lmid)
    obr=chat.objects.filter(Q(fromid__id=fromid,toid__id=toid,id__gt=lmid)|Q(fromid__id=toid,toid__id=fromid,id__gt=lmid)).order_by("id")
    res=[]
    for i in obr:
        r={"msgid":i.id,"date":str(i.date),"message":i.message,"fromid":i.fromid.id}
        res.append(r)
    if len(res)>0:
        return JsonResponse({'status':'ok', 'res1':res})
    else:
        return JsonResponse({'status':'not found'})


#______________________________________________________________________
# def lib_chat(request):
#     fid=request.POST['fid']
#     tid=request.POST['tid']
#     msg=request.POST['msg']
#     date=datetime.today()
#     time = datetime.now()
#     current_time = time.strftime("%H:%M:%S")
#
#
#     ob = chat()
#     ob.fromid=fid
#     ob.toidid=tid
#     ob.message=msg
#     ob.date=date
#     ob.time=current_time
#     ob.save()
#     data = {"task": "success"}
#     r = json.dumps(data)
#     print(r)
#     return HttpResponse(r)


def view_book(request):
    lid=request.POST['lid']
    ob = books.objects.filter(LIBRARY__LOGIN__id=lid,status='available')
    data = []
    for i in ob:
        row = {"bkid":i.id,"libid":i.LIBRARY.id,"bookname": i.bookname,"image": str(i.image.url), "details": i.details,"stock": i.stock, "author": i.author, "review": i.review,"category": i.category,"type":i.type}
        data.append(row)
    r = json.dumps(data)
    print(r,"GGGGGGGGGGGGGGGG")
    return HttpResponse(r)


def addbook(request):
    print(request.POST,"JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ")
    print(request.FILES,"JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ")
    libid=request.POST['lid']
    book=request.POST['name']
    image1=request.FILES['file']
    fs = FileSystemStorage()
    fsave = fs.save(image1.name, image1)
    detail=request.POST['details']
    stock=request.POST['stock']
    author=request.POST['author']
    review=request.POST['review']
    category=request.POST['category']
    type=request.POST['type']
    ob = books()
    ob.LIBRARY=library_table.objects.get(LOGIN__id=libid)
    ob.bookname=book
    ob.image=fsave
    ob.details=detail
    ob.status='available'
    ob.stock=stock
    ob.author=author
    ob.review=review
    ob.category=category
    ob.type=type
    ob.save()

    # img=cv2.imread(r"C:\Users\shani\PycharmProjects\librarymnt\media/"+fsave)
    # stretch_near = cv2.resize(img, (780, 540),
    #                           interpolation=cv2.INTER_LINEAR)
    # cv2.imwrite(r"C:\Users\shani\PycharmProjects\librarymnt\media/"+fsave,stretch_near)

    if(type=='Soft Copy'):
        image1 = request.FILES['file1']
        fs = FileSystemStorage()
        fsave = fs.save(str(ob.id)+".pdf", image1)
        print(fsave,"=================================================")
        print(fsave,"=================================================")
        print(fsave,"=================================================")
        print(fsave,"=================================================")
        print(fsave,"=================================================")
        print(fsave,"=================================================")


    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)

def  view_book_search(request):
    print(request.POST)
    bnam=request.POST['bname']
    # anam=request.POST['aname']
    libid=request.POST['lid']
    print(libid,"==============================")
    ob=books.objects.filter(Q(bookname__istartswith=bnam,LIBRARY__LOGIN__id=libid,status='available')|Q(author__istartswith=bnam,LIBRARY__LOGIN__id=libid,status='available'))
    data = []
    for i in ob:
        row = {"bkid":i.id,"libid":i.LIBRARY.id,"bookname": i.bookname,"image": str(i.image.url), "details": i.details,"stock": i.stock, "author": i.author, "review": i.review,"category": i.category,"type":i.type}
        data.append(row)
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)

def  del_book(request):
    bid=request.POST['bkid']
    ob=books.objects.get(id=bid)
    ob.delete()
    data = {"task": "valid"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)


def  view_order(request):
    libid=request.POST['lid']
    ob = order.objects.filter(status='pending',BOOK__LIBRARY__LOGIN__id=libid)
    data = []
    for i in ob:
        row = {"oid":i.id,"bookname": i.BOOK.bookname,"image": str(i.BOOK.image.url), "userf": i.USER.fname ,"userl": i.USER.lname,"author":i.BOOK.author,"status":i.status, "stock": i.BOOK.stock,"category": i.BOOK.category}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def  view_order_search(request):
    bnam=request.POST['bname']
    # unam=request.POST['uname']
    libid=request.POST['lid']
    ob = order.objects.filter(Q(BOOK__bookname__istartswith=bnam,status='pending',BOOK__LIBRARY__LOGIN__id=libid)|Q(USER__fname__istartswith=bnam,status='pending',BOOK__LIBRARY__LOGIN__id=libid))
    data = []
    for i in ob:
        row = {"oid":i.id,"bookname": i.BOOK.bookname,"image": str(i.BOOK.image.url), "userf": i.USER.fname ,"userl": i.USER.lname,"author":i.BOOK.author, "status": i.status,"category": i.BOOK.category}
        data.append(row)
    r = json.dumps(data)
    print(r,"=================================================",bnam)
    return HttpResponse(r)

def view_library_events(request):
    libid=request.POST['lid']
    ob = levents.objects.filter(LIBRARY__LOGIN__id=libid)
    data = []
    for i in ob:
        row = {"poster":i.poster.url,"date" :str(i.date)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def  view_library_events_search(request):
    dates=request.POST['datea']
    libid=request.POST['lid']
    ob = levents.objects.filter(date=dates,LIBRARY__LOGIN__id=libid)
    data = []
    for i in ob:
        row = {"poster":i.poster.url,"date" :str(i.date)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def  view_admnotftn(request):
    ob = notificaton.objects.all()
    data = []
    for i in ob:
        row = {"notification": i.notification, "date": str(i.date)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def  view_admnotftn_search(request):
    dates=request.POST['datea']
    ob = notificaton.objects.filter(date=dates)
    data = []
    for i in ob:
        row = {"notification": i.notification, "date": str(i.date)}
        data.append(row)
    r = json.dumps(data)
    print(data)
    return HttpResponse(r)

def  view_donatn(request):
    libid=request.POST['lid']
    ob = donation.objects.filter(LIBRARY__LOGIN__id=libid)
    data = []
    for i in ob:
        row = {"userf": i.USER.fname ,"userl": i.USER.lname, "bookname": i.bookname, "date": str(i.date),"image": str(i.image.url),"stock":i.stock,"details":i.details,"author":i.author,"review":i.review,"category":i.category,"status":i.status,"id":i.id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def  view_donatn_more(request):
    libid=request.POST['lid']
    did=request.POST['did']
    ob = donation.objects.filter(LIBRARY__LOGIN__id=libid,id=did)
    data = []
    for i in ob:
        row = {"userf": i.USER.fname ,"userl": i.USER.lname, "bookname": i.bookname, "date": str(i.date),"image": str(i.image.url),"stock":i.stock,"details":i.details,"author":i.author,"review":i.review,"category":i.category,"status":i.status,"id":i.id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)



def  view_donatn_search(request):
    print(request.POST,"===================")
    print(request.POST,"===================")
    print(request.POST,"===================")
    print(request.POST,"===================")
    dates=request.POST['date']
    libid=request.POST['lid']
    ob = donation.objects.filter(date=dates,LIBRARY__LOGIN__id=libid)
    data = []
    for i in ob:
        row = {"userf": i.USER.fname ,"userl": i.USER.lname, "bookname": i.bookname, "date": str(i.date),"image": str(i.image.url),"stock":i.stock,"details":i.details,"author":i.author,"review":i.review,"category":i.category,"status":i.status,"id":i.id}
        data.append(row)
    r = json.dumps(data)
    print(data)
    return HttpResponse(r)


def  view_suggestbk(request):
    libid =request.POST['lid']
    ob = suggestion.objects.filter(LIBRARY__LOGIN__id=libid)
    data = []
    for i in ob:
        row = {"id":i.id ,"userf": i.USER.fname ,"userl": i.USER.lname,"bookname": i.bookname,  "author": i.author, "date": str(i.date) ,"image":i.image.url,"stock":i.stock,"details":i.details,"review":i.review,"category":i.category}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def  view_suggestbk_more(request):
    libid =request.POST['lid']
    sid =request.POST['sid']
    ob = suggestion.objects.filter(LIBRARY__LOGIN__id=libid,id=sid)
    data = []
    for i in ob:
        row = {"id":i.id ,"userf": i.USER.fname ,"userl": i.USER.lname,"bookname": i.bookname,  "author": i.author, "date": str(i.date) ,"image":i.image.url,"stock":i.stock,"details":i.details,"review":i.review,"category":i.category}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def  view_suggestbk_search(request):
    dates=request.POST['date']
    libid =request.POST['lid']
    ob = suggestion.objects.filter(date=dates,LIBRARY__LOGIN__id=libid)
    data = []
    for i in ob:
        row = {"id":i.id ,"userf": i.USER.fname ,"userl": i.USER.lname,"bookname": i.bookname,  "author": i.author, "date": str(i.date) ,"image":i.image.url,"stock":i.stock,"details":i.details,"review":i.review,"category":i.category}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def orderbook(request):
    print(request.POST)
    # libid=request.POST['lid']
    uid=request.POST['uid']
    bid=request.POST['bkid']
    ddate=request.POST['ddt']

    ob = order()
    ob.USER=user_table.objects.get(LOGIN=uid)
    ob.BOOK=books.objects.get(id=bid)
    # ob.LIBRARY=library_table.objects.get(LOGIN__id=libid)
    ob.status='accepted'
    ob.orderdate=datetime.today()
    ob.duedate=ddate
    ob.save()
    obb=books.objects.get(id=bid)
    obb.stock=int(obb.stock)-1
    data = {"task": "valid"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)


def placeorder_new(request):
    ob=user_table.objects.all()
    data = []
    for i in ob:
        row = {"uid":i.LOGIN.id,"pr":i.profile.url,"fn":i.fname,"ln":i.lname}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)



def placeorder_new_search(request):
    name = request.POST['name']
    ob = user_table.objects.filter(fname__istartswith=name)
    data = []
    for i in ob:
        row = {"uid":i.LOGIN.id,"pr":i.profile.url,"fn":i.fname,"ln":i.lname}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)







def ddtnotfy(request):
   noti=request.POST['noti']
   oid=request.POST['oid']

   ob=lnotification()
   ob.ORDER=order.objects.get(id=oid)
   ob.notifcation=noti
   ob.date=datetime.today()
   ob.save()
   data = {"task": "success"}
   r = json.dumps(data)
   print(r)
   return HttpResponse(r)

def  view_authboard(request):
    print(request.POST)
    libid=request.POST['lid']
    ob = authbrd.objects.filter(LIBRARY__LOGIN__id=libid)
    data = []
    for i in ob:
        row = {"uf": i.USER.fname,"ul": i.USER.lname,"post":i.post.url,"date":str(i.date),"status":i.status,"id":i.id}
        data.append(row)
    r = json.dumps(data)
    print(data)
    return HttpResponse(r)

def  view_authboard_search(request):
    dates=request.POST['datea']
    libid=request.POST['lid']
    ob = authbrd.objects.filter(date=dates,LIBRARY__LOGIN__id=libid)
    data = []
    for i in ob:
        row = {"uf": i.USER.fname,"ul": i.USER.lname,"post":i.post.url,"date":str(i.date),"status":i.status,"id":i.id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def  view_bookstaken(request):
    libid=request.POST['lid']
    ob = order.objects.filter(status='accepted',BOOK__LIBRARY__LOGIN__id=libid)
    data = []
    for i in ob:
        row = {"oid":i.id,"uid":i.USER.id,"libid":i.BOOK.LIBRARY.id,"userf": i.USER.fname ,"userl": i.USER.lname, "bookname": i.BOOK.bookname, "image":i.USER.profile.url, "author": i.BOOK.author,"orderdate":str( i.orderdate), "duedate": str(i.duedate),"category": i.BOOK.category}
        data.append(row)
    r = json.dumps(data)
    print(data)
    return HttpResponse(r)

def  view_bookstaken_search(request):
    print(request.POST)
    # uname=request.POST['bnamea']
    bname=request.POST['bnamea']
    libid=request.POST['lid']
    ob = order.objects.filter(Q(USER__fname__istartswith=bname,BOOK__LIBRARY__LOGIN__id=libid,status='accepted')|Q(BOOK__bookname__istartswith=bname,BOOK__LIBRARY__LOGIN__id=libid,status='accepted'))
    data = []
    for i in ob:
        row = {"oid":i.id,"uid":i.USER.id,"libid":i.BOOK.LIBRARY.id,"userf": i.USER.fname ,"userl": i.USER.lname, "bookname": i.BOOK.bookname, "image": i.USER.profile.url, "author": i.BOOK.author,"orderdate": str(i.orderdate), "duedate":str( i.duedate),"category": i.BOOK.category}
        data.append(row)
    r = json.dumps(data)
    print(data,"==")
    return HttpResponse(r)

def addevnt(request):
    libid=request.POST['lid']
    poster = request.FILES['file']
    fs = FileSystemStorage()
    fsave = fs.save(poster.name, poster)
    date=datetime.today()

    ob = levents()
    ob.LIBRARY=library_table.objects.get(LOGIN__id=libid)
    ob.poster=fsave
    ob.date=date
    ob.save()
    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)


def view_duedate(request):
    print(request.POST)
    libid=request.POST['lid']
    ob=order.objects.filter(BOOK__LIBRARY__LOGIN__id=libid,duedate=datetime.today())
    print(ob)
    data = []
    for i in ob:
        row = {"oid":i.id,"userf": i.USER.fname ,"userl": i.USER.lname, "bookname": i.BOOK.bookname, "image": i.USER.profile.url, "author": i.BOOK.author,"orderdate": str(i.orderdate), "duedate": str(i.duedate),"category": i.BOOK.category}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def view_duedate_search(request):
    print(request.POST)

    libid=request.POST['lid']
    dates=request.POST['date']
    ob=order.objects.filter(BOOK__LIBRARY__LOGIN__id=libid,duedate=dates)
    data = []
    for i in ob:
        row = {"userf": i.USER.fname ,"userl": i.USER.lname, "bookname": i.BOOK.bookname, "image": i.USER.profile.url, "author": i.BOOK.author,"orderdate": str(i.orderdate), "duedate": str(i.duedate),"category": i.BOOK.category}
        data.append(row)
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)


def report_user(request):
    libid = request.POST['libid']
    uid = request.POST['uid']
    reason = request.POST['rsn']

    ob = report()
    ob.LIBRARY = library_table.objects.get(LOGIN__id=libid)
    ob.USER = user_table.objects.get(LOGIN__id=uid)
    ob.reason = reason
    ob.save()
    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)

def extendduedate(request):
    oid=request.POST['oid']
    dates=request.POST['date']

    ob= order.objects.get(id=oid)
    ob.duedate=dates
    ob.save()
    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)


def accept_writing(request):
    authorid=request.POST['aid']

    ob=authbrd.objects.get(id=authorid)
    ob.status='accepted'
    ob.save()
    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)


def rjct_writing(request):
        authorid = request.POST['aid']

        ob = authbrd.objects.get(id=authorid)
        ob.status = 'rejected'
        ob.save()
        data = {"task": "success"}
        r = json.dumps(data)
        print(r)
        return HttpResponse(r)

def accept_order(request):

        oid = request.POST['oid']
        ob = order.objects.get(id=oid)
        ob.status = "accepted"
        kk=datetime.today()
        jj=kk + timedelta(days=10)
        print(jj,"====================")
        ob.duedate =jj
        ob.save()

        obb = books.objects.get(id=ob.BOOK.id)
        obb.stock = int(obb.stock) - 1
        if (obb.stock == 0):
            obb.status = "not available"
        else:
            obb.status = "available"
        obb.save()
        data = {"task": "valid"}
        r = json.dumps(data)
        print(r)
        return HttpResponse(r)

def  reject_order(request):
    oid = request.POST['oid']
    ob = order.objects.get(id=oid)
    ob.delete()
    data = {"task": "valid"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)

def return_book(request):


            oid = request.POST['oid']
            ob = order.objects.get(id=oid)
            ob.status = "returned"
            ob.save()

            bkid = ob.BOOK.id
            obb = books.objects.get(id=bkid)
            obb.stock = int(obb.stock) + 1

            obb.status = "available"
            obb.save()
            data = {"task": "success"}
            r = json.dumps(data)
            print(r)
            return HttpResponse(r)

def ordspin(request):
    ob = user_table.objects.all()
    data = []
    for i in ob:
        row = {"uid":i.id,"userf": i.fname, "userl": i.lname}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def addbook_fromdonations(request):
        libid = request.POST['lid']
        book = request.POST['name']
        image1 = request.POST['file']
        image1=image1.split("/")
        print("-------------------------------",image1[2])
        detail = request.POST['details']
        stock = request.POST['stock']
        author = request.POST['author']
        review = request.POST['review']
        category = request.POST['category']
        did=request.POST['did']
        obd=donation.objects.get(id=did)
        obd.status='completed';
        obd.save()
        ob = books()
        ob.LIBRARY = library_table.objects.get(LOGIN__id=libid)
        ob.bookname = book
        ob.image = image1[2]
        ob.details = detail
        ob.status = 'available'
        ob.stock = stock
        ob.author = author
        ob.review = review
        ob.category = category
        ob.type = 'Hard Copy'
        ob.save()
        data = {"task": "success"}
        r = json.dumps(data)
        print(r)
        return HttpResponse(r)

def lib_more(request):
    print(request.POST,"#$%^%$#@")
    bid=request.POST['bkid']
    ob= books.objects.filter(id=bid)
    data = []
    for i in ob:
        row = {"bkid": i.id, "libid": i.LIBRARY.id, "bookname": i.bookname, "image": str(i.image.url),
               "details": i.details, "stock": i.stock, "author": i.author, "review": i.review, "category": i.category,
               "type": i.type}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)



            #user




def user_reg(request):

    uname =request.POST['uname']
    pswd = request.POST['pswd']

    fname =request.POST['name']
    lname =request.POST['lname']
    profile =request.FILES['file']
    fs = FileSystemStorage()
    fsave = fs.save(profile.name,profile)
    gender =request.POST['gender']
    place =request.POST['place']
    phoneno =request.POST['phoneno']
    email =request.POST['email']

    lob = login_table()
    lob.username=uname
    lob.password=pswd
    lob.type = 'user'
    lob.save()

    ob = user_table()
    ob.fname= fname
    ob.lname=lname
    ob.profile=fsave
    ob.gender=gender
    ob.place=place
    ob.phoneno=phoneno
    ob.email=email
    ob.LOGIN=lob
    ob.save()

    # img = cv2.imread(r"C:\Users\shani\PycharmProjects\librarymnt\media/" + fsave)
    # stretch_near = cv2.resize(img, (780, 540),
    #                           interpolation=cv2.INTER_LINEAR)
    # cv2.imwrite(r"C:\Users\shani\PycharmProjects\librarymnt\media/" + fsave, stretch_near)



    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)



def user_vlibraries(request):
    ob = library_table.objects.filter(LOGIN__type="library")
    print(ob,"hhhhhhhhhhhhhhhh")
    data = []
    for i in ob:
        row = {"libid":i.id,"image":i.image.url, "name": i.name,"place":i.place,"phoneno":i.phoneno,"logid":i.LOGIN.id,"loc":"http://maps.google.com?q="+str(i.latitude)+","+str(i.longitude)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def user_libsearch(request):
    lnam=request.POST['lname']
    # plac=request.POST['placea']
    ob = library_table.objects.filter(Q(name__istartswith=lnam)|Q(place__istartswith=lnam))
    data = []
    for i in ob:
        row = {"libid":i.id,"image":i.image.url, "name": i.name,"place":i.place,"phoneno":i.phoneno,"logid":i.LOGIN.id,"loc":"http://maps.google.com?q="+str(i.latitude)+","+str(i.longitude)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def user_vcustomlibry(request):
    lid=request.POST['lid']
    ob = plibrary.objects.filter(USER__LOGIN__id=lid)
    data = []
    for i in ob:
        row = {"eid":i.id,"bookname": i.BOOK.bookname,"image": str(i.BOOK.image.url), "details": i.BOOK.details, "author": i.BOOK.author,"category": i.BOOK.category,"bid":i.BOOK.id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def user_vcustomlibry_search(request):
    bnam=request.POST['bnamo']
    lid=request.POST['lid']
    ob = plibrary.objects.filter(BOOK__bookname__istartswith=bnam,USER__LOGIN__id=lid)
    data = []
    for i in ob:
        row = {"bookname": i.BOOK.bookname,"image": str(i.BOOK.image.url), "details": i.BOOK.details, "author": i.BOOK.author,"category": i.BOOK.category,"eid":i.id,"bid":i.BOOK.id}
        data.append(row)
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)


def user_vcomplaints(request):
    lid = request.POST['lid']
    ob = complaint.objects.filter(USER__LOGIN__id=lid)
    data = []
    for i in ob:
        row = {"complaint":i.complaint,"date":str(i.date),"reply":i.reply}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def user_vcomplaints_search(request):
    dates = request.POST['dateo']
    lid = request.POST['lid']
    ob = complaint.objects.filter(date=dates,USER__LOGIN__id=lid)
    data = []
    for i in ob:
        row = {"complaint":i.complaint,"date":str(i.date),"reply":i.reply}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def user_vlibbooks(request):
    library=request.POST['libid']
    ob =books.objects.filter(LIBRARY__id=library,status="available")
    print(ob,"gggggg")
    data = []
    for i in ob:
        row ={"libid":i.LIBRARY.id,"bkid":i.id, "bookname": i.bookname,"image": i.image.url, "details": i.details,"stock": i.stock, "author": i.author, "review": i.review,"category": i.category,"type":i.type}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def user_vlibbooks_search(request):
    library=request.POST['libid']
    bnam=request.POST['bnama']
    # author=request.POST['autha']
    # categor=request.POST['cato']
    ob =books.objects.filter( Q(bookname__istartswith=bnam,LIBRARY__id=library,status="available") | Q(author__istartswith=bnam,LIBRARY__id=library,status="available") |Q(category__istartswith=bnam,LIBRARY__id=library,status="available") )
    data = []
    for i in ob:
        row ={"libid":i.LIBRARY.id,"bkid":i.id, "bookname": i.bookname,"image": i.image.url, "details": i.details,"stock": i.stock, "author": i.author, "review": i.review,"category": i.category,"type":i.type}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def user_vbooklog(request):
    print(request.POST)
    userlog=request.POST['lid']
    libid=request.POST['libid']
    print(request.POST,"kjjjj")
    ob=order.objects.filter(USER__LOGIN__id=userlog,BOOK__LIBRARY__id=libid)
    data = []
    for i in ob:
       row = {"bookname": i.BOOK.bookname, "image": str(i.BOOK.image.url), "details": i.BOOK.details,
              "author": i.BOOK.author, "review": i.BOOK.review, "category": i.BOOK.category, "orderdat": str(i.orderdate),
              "duedat": str(i.duedate)}


       data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)



def user_vbooklog_search(request):
   userlog=request.POST['lid']
   libid=request.POST['libid']
   bnam=request.POST['bnamo']
   print(request.POST)
   ob=order.objects.filter(Q(BOOK__bookname__istartswith=bnam,USER__LOGIN__id=userlog,BOOK__LIBRARY__id=libid) |Q(BOOK__author__istartswith=bnam,USER__LOGIN__id=userlog,BOOK__LIBRARY__id=libid))
   data = []
   for i in ob:
       row = {"bookname": i.BOOK.bookname, "image": str(i.BOOK.image.url), "details": i.BOOK.details,
              "author": i.BOOK.author, "review": i.BOOK.review, "category": i.BOOK.category, "orderdat": str(i.orderdate),
              "duedat": str(i.duedate)}
       data.append(row)
   print(data)
   return JsonResponse(data,safe=False)


def user_libevnt(request):
   libry=request.POST['libid']
   ob=levents.objects.filter(LIBRARY__id=libry)
   data = []
   for i in ob:
       row = {"poster": str(i.poster.url), "date": str(i.date)}
       data.append(row)
   print(data)
   return JsonResponse(data,safe=False)

def user_libevnt_search(request):
   libry=request.POST['libid']
   dt=request.POST['datea']
   ob=levents.objects.filter(date=dt,LIBRARY__id=libry)
   data = []
   for i in ob:
       row = {"poster": str(i.poster.url), "date": str(i.date)}
       data.append(row)

   return JsonResponse(data,safe=False)

def user_vauthbrd(request):
   libry=request.POST['libid']
   ob=authbrd.objects.filter(LIBRARY__id=libry,status='accepted')
   data = []
   for i in ob:
       row = {"name":i.USER.fname,"post":i.post.url,"date":str(i.date)}
       data.append(row)
   print(data)
   return JsonResponse(data,safe=False)

def user_vauthbrd_search(request):
   libry=request.POST['libid']
   dt=request.POST['dato']
   ob=authbrd.objects.filter(date=dt,LIBRARY__id=libry,status='accepted')
   data = []
   for i in ob:
       row = {"name":i.USER.fname,"post":i.post.url,"date":str(i.date)}
       data.append(row)
   r = json.dumps(data)
   return HttpResponse(r)

def user_vadmnotfy(request):
   ob=notificaton.objects.all()
   data = []
   for i in ob:
       row = {"notfy":i.notification,"dt":str(i.date)}
       data.append(row)
   r = json.dumps(data)
   return HttpResponse(r)

def user_vadmnotfy_search(request):
   dates=request.POST['dato']
   ob=notificaton.objects.filter(date=dates)
   data = []
   for i in ob:
       row = {"notfy":i.notification,"dt":str(i.date)}
       data.append(row)
   r = json.dumps(data)
   return HttpResponse(r)

def user_vlibnotfy(request):
   print(request.POST,"KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK")
   usr=request.POST['lid']
   ob=lnotification.objects.filter(ORDER__USER__LOGIN__id=usr)
   data = []
   for i in ob:
       row = {"library":i.ORDER.BOOK.LIBRARY.name,"notfy":i.notifcation,"dt":str(i.date)}
       data.append(row)
   r = json.dumps(data)
   return HttpResponse(r)

def user_vlibnotfy_search(request):
   usr=request.POST['lid']
   dt=request.POST['dates']
   ob=lnotification.objects.filter(ORDER__USER__LOGIN__id=usr,date=dt)
   data = []
   for i in ob:
       row = {"library":i.ORDER.BOOK.LIBRARY.name,"notfy":i.notifcation,"dt":str(i.date)}
       data.append(row)
   r = json.dumps(data)
   return HttpResponse(r)


def user_sendcomplaint(request):
    cmplt = request.POST['cmplnt']
    usr = request.POST['lid']
    dates = datetime.today()

    ob = complaint()
    ob.USER = user_table.objects.get(LOGIN__id=usr)
    ob.complaint = cmplt
    ob.reply='pending'
    ob.date = dates
    ob.save()
    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)


def user_addwriting(request):
    print('=========================================================================================');
    libid = request.POST['libid']
    usr = request.POST['lid']
    post = request.FILES['file']
    fs = FileSystemStorage()
    fsave = fs.save(post.name, post)
    dates = datetime.today()

    ob = authbrd()
    ob.USER = user_table.objects.get(LOGIN__id=usr)
    ob.LIBRARY = library_table.objects.get(id=libid)
    ob.post=fsave
    ob.date = dates
    ob.status='pending'
    ob.save()

    # img = cv2.imread(r"C:\Users\shani\PycharmProjects\librarymnt\media/" + fsave)
    # stretch_near = cv2.resize(img, (780, 540),
    #                           interpolation=cv2.INTER_LINEAR)
    # cv2.imwrite(r"C:\Users\shani\PycharmProjects\librarymnt\media/" + fsave, stretch_near)

    data = {"task": "valid"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)

def user_placeorder(request):
    uid=request.POST['lid']
    bkid=request.POST['bkid']
    print(request.POST,"jjjjjjjjjjjjjjj")
    ob= order()
    ob.BOOK=books.objects.get(id=bkid)
    ob.USER=user_table.objects.get(LOGIN__id=uid)
    ob.status='pending'
    ob.orderdate=datetime.today()
    ob.duedate=datetime.today()
    ob.save()
    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)

def user_donate(request):
    type=request.POST['type']
    print(type)
    if type == "Donate":
        uid=request.POST['lid']
        libid=request.POST['libid']
        image = request.FILES['file']
        fs = FileSystemStorage()
        fsave = fs.save(image.name, image)
        bknm=request.POST['bknm']
        stk=request.POST['stk']
        dtl=request.POST['dtl']
        authr=request.POST['authr']
        rvw=request.POST['rvw']
        ctgry=request.POST['ctgry']
        dt=datetime.today()
        print(libid)

        ob = donation()
        ob.USER =user_table.objects.get(LOGIN__id=uid)
        ob.LIBRARY=library_table.objects.get(id=libid)
        ob.image=fsave
        ob.bookname=bknm
        ob.stock=stk
        ob.details=dtl
        ob.author=authr
        ob.review=rvw
        ob.category=ctgry
        ob.date=dt
        ob.status='pending'
        ob.save()
        data = {"task": "success"}
        r = json.dumps(data)
        print(r)
        return HttpResponse(r)
    else:
        uid = request.POST['lid']
        libid = request.POST['libid']
        image = request.FILES['file']
        fs = FileSystemStorage()
        fsave = fs.save(image.name, image)
        bknm = request.POST['bknm']
        stk = request.POST['stk']
        dtl = request.POST['dtl']
        authr = request.POST['authr']
        rvw = request.POST['rvw']
        ctgry = request.POST['ctgry']
        print(libid)
        dt = datetime.today()
        ob = suggestion()
        ob.USER = user_table.objects.get(LOGIN__id=uid)
        ob.LIBRARY = library_table.objects.get(id=libid)
        ob.image = fsave
        ob.bookname = bknm
        ob.stock = stk
        ob.details = dtl
        ob.author = authr
        ob.review = rvw
        ob.category = ctgry
        ob.date = dt
        ob.status = 'pending'
        ob.save()
        data = {"task": "success"}
        r = json.dumps(data)
        print(r)
        return HttpResponse(r)

def user_suggest(request):
    uid = request.POST['lid']
    libid = request.POST['libid']
    image = request.FILES['file']
    fs = FileSystemStorage()
    fsave = fs.save(image.name, image)
    bknm = request.POST['bknm']
    stk = request.POST['stk']
    dtl = request.POST['dtl']
    authr = request.POST['authr']
    rvw = request.POST['rvw']
    ctgry = request.POST['ctgry']
    # dt = request.POST['dt']
    ob = suggestion()
    ob.USER = user_table.objects.get(LOGIN__id=uid)
    ob.LIBRARY = library_table.objects.get(LOGIN__id=libid)
    ob.image = fsave
    ob.bknm = bknm
    ob.stock = stk
    ob.details = dtl
    ob.author = authr
    ob.review = rvw
    ob.category = ctgry
    ob.date = datetime.today()
    ob.save()
    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)


def user_addtocustomlby(request):
    bkid=request.POST['bkid']
    uid=request.POST['lid']

    ob = plibrary()
    ob.BOOK=books.objects.get(id=bkid)
    ob.USER =user_table.objects.get(LOGIN__id=uid)
    ob.date = datetime.today()
    ob.save()
    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)


def user_addratrvw(request):
    uid=request.POST['lid']
    rtng=request.POST['rtng']
    rvw=request.POST['rvw']

    ob=rating()
    ob.USER=user_table.objects.get(LOGIN__id=uid)
    ob.rating=rtng
    ob.review=rvw
    ob.date=datetime.today()
    ob.save()
    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)

def user_more(request):
    bid=request.POST['bkid']
    ob =books.objects.filter(id=bid)
    data = []
    for i in ob:
        row ={"bkid":i.id, "bookname": i.bookname,"image": i.image.url, "details": i.details,"stock": i.stock, "author": i.author, "review": i.review,"category": i.category,"type":i.type}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)

def  del_ebook(request):
    eid = request.POST['eid']
    ob = plibrary.objects.get(id=eid)
    ob.delete()
    data = {"task": "success"}
    r = json.dumps(data)
    print(r)
    return HttpResponse(r)

def  forgot_password(request):
    uname = request.POST['uname']
    email=request.POST['ema']
    try:
        obl=login_table.objects.get(username=uname)
        if obl.type=='library':
            ob=library_table.objects.get(LOGIN__id=obl.id,email=email)

            try:


                    try:
                        gmail = smtplib.SMTP('smtp.gmail.com', 587)
                        gmail.ehlo()
                        gmail.starttls()
                        gmail.login('shanidpsha@gmail.com', 'nbvtcbpheaozrngy')
                        print("login=======")
                    except Exception as e:
                        print("Couldn't setup email!!" + str(e))
                    msg = MIMEText("Your new password id : " + str(obl.password))
                    print(msg)
                    msg['Subject'] = 'librarymnt'
                    msg['To'] = email
                    msg['From'] = 'hibamuhsinakm8005@gmail.com'

                    print("ok====")

                    try:
                        gmail.send_message(msg)
                    except Exception as e:
                        return  JsonResponse({"task": "valid"})
                    return JsonResponse({"task": "valid"})
            except Exception as e:
                print(e)
                return JsonResponse({"task": "invalid"})
        if obl.type=='user':
            ob=user_table.objects.get(LOGIN__id=obl.id,email=email)
            try:
                    try:
                        gmail = smtplib.SMTP('smtp.gmail.com', 587)
                        gmail.ehlo()
                        gmail.starttls()
                        gmail.login('shanidpsha@gmail.com', 'nbvtcbpheaozrngy')
                        print("login=======")
                    except Exception as e:
                        print("Couldn't setup email!!" + str(e))
                    msg = MIMEText("Your new password id : " + str(obl.password))
                    print(msg)
                    msg['Subject'] = 'librarymnt'
                    msg['To'] = email
                    msg['From'] = 'hibamuhsinakm8005@gmail.com'

                    print("ok====")

                    try:
                        gmail.send_message(msg)
                    except Exception as e:
                        return  JsonResponse({"task": "valid"})
                    return JsonResponse({"task": "valid"})
            except Exception as e:
                print(e)
                return JsonResponse({"task": "invalid"})
    except Exception as e:
        print("============")
        print(e)
        return JsonResponse({"task":"invalid"})