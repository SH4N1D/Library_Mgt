from django.db import models


class login_table(models.Model):
    username = models.CharField(max_length=100)
    password = models.CharField(max_length=100)
    type = models.CharField(max_length=100)

class user_table(models.Model):
    LOGIN = models.ForeignKey(login_table, on_delete=models.CASCADE)
    fname = models.CharField(max_length=100)
    lname = models.CharField(max_length=100)
    profile=models.FileField()
    gender = models.CharField(max_length=100)
    place = models.CharField(max_length=100)
    phoneno = models.BigIntegerField()
    email= models.CharField(max_length=100)

class library_table(models.Model):
    LOGIN = models.ForeignKey(login_table, on_delete=models.CASCADE)
    name = models.CharField(max_length=100)
    place = models.CharField(max_length=100)
    email = models.CharField(max_length=100)
    phoneno = models.BigIntegerField()
    latitude = models.CharField(max_length=100)
    longitude = models.CharField(max_length=100)
    image =models.FileField()

class report(models.Model):
    USER = models.ForeignKey(user_table,on_delete=models.CASCADE)
    LIBRARY = models.ForeignKey(library_table,on_delete=models.CASCADE)
    reason = models.CharField(max_length=100)

class notificaton(models.Model):
    notification =models.CharField(max_length=100)
    date = models.DateField()

class complaint(models.Model):
    USER = models.ForeignKey(user_table, on_delete=models.CASCADE)
    complaint = models.CharField(max_length=100)
    date = models.DateField()
    reply = models.CharField(max_length=100)

class rating(models.Model):
    USER = models.ForeignKey(user_table, on_delete=models.CASCADE)
    rating = models.CharField(max_length=100)
    review = models.CharField(max_length=100)
    date = models.DateField()

class books(models.Model):
    LIBRARY = models.ForeignKey(library_table, on_delete=models.CASCADE)
    image = models.FileField()
    bookname = models.CharField(max_length=100)
    status = models.CharField(max_length=100)
    stock = models.CharField(max_length=100)
    details = models.CharField(max_length=100)
    author = models.CharField(max_length=100)
    review = models.CharField(max_length=100)
    category = models.CharField(max_length=100)
    type = models.CharField(max_length=100)


class order(models.Model):
    BOOK= models.ForeignKey(books,on_delete=models.CASCADE)
    USER = models.ForeignKey(user_table, on_delete=models.CASCADE)
    status= models.CharField(max_length=100)
    orderdate = models.DateField()
    duedate = models.DateField()

class donation(models.Model):
    USER = models.ForeignKey(user_table, on_delete=models.CASCADE)
    LIBRARY = models.ForeignKey(library_table, on_delete=models.CASCADE)
    image = models.FileField()
    bookname = models.CharField(max_length=100)
    stock = models.CharField(max_length=100)
    details = models.CharField(max_length=100)
    author = models.CharField(max_length=100)
    review = models.CharField(max_length=100)
    category = models.CharField(max_length=100)
    date = models.DateField()
    status=models.CharField(max_length=20)

class lnotification(models.Model):
    ORDER= models.ForeignKey(order,on_delete=models.CASCADE)
    notifcation= models.CharField(max_length=100)
    date= models.DateField()

class chat(models.Model):
    fromid = models.ForeignKey(login_table,on_delete=models.CASCADE,related_name="fid")
    toid = models.ForeignKey(login_table,on_delete=models.CASCADE,related_name="tid")
    message = models.CharField(max_length=100)
    date = models.DateField()
    time = models.TimeField()

class suggestion(models.Model):
    USER = models.ForeignKey(user_table, on_delete=models.CASCADE)
    LIBRARY = models.ForeignKey(library_table, on_delete=models.CASCADE)
    image = models.FileField()
    bookname = models.CharField(max_length=100)
    stock = models.CharField(max_length=100)
    details = models.CharField(max_length=100)
    author = models.CharField(max_length=100)
    review = models.CharField(max_length=100)
    category = models.CharField(max_length=100)
    date = models.DateField()

class plibrary(models.Model):
    BOOK = models.ForeignKey(books,on_delete=models.CASCADE)
    USER = models.ForeignKey(user_table, on_delete=models.CASCADE)
    date = models.DateField()

class levents(models.Model):
    LIBRARY = models.ForeignKey(library_table,on_delete=models.CASCADE)
    poster= models.FileField()
    date = models.DateField()

class authbrd(models.Model):
    LIBRARY = models.ForeignKey(library_table,on_delete=models.CASCADE)
    USER = models.ForeignKey(user_table, on_delete=models.CASCADE)
    post = models.FileField()
    date = models.DateField()
    status = models.CharField(max_length=100)

